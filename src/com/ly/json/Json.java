package com.ly.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Json {

	private static List<Field> iteraterFieldList(Class<?> c) {

		List<Field> fieldList = new ArrayList<>();
		Class<?> curClass = c;
		do {
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fieldList.add(fields[i]);
			}

			curClass = curClass.getSuperclass();
		} while (curClass != Object.class);

		return fieldList;
	}

	public static String toString(Object object) {

		JSONObject jsonObject = new JSONObject();

		try {
			List<Field> fieldList = iteraterFieldList(object.getClass());
			for (Field field : fieldList) {
				field.setAccessible(true);
				String key = parseJsonName(field);
				Object fieldItem = field.get(object);
				jsonObject.put(key, parseJsonItemFromObject(fieldItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	public static String toString(List<?> list) {
		JSONArray jsonArray = new JSONArray();
		try {
			for (Object object : list) {
				jsonArray.put(parseJsonItemFromObject(object));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray.toString();
	}

	public static String toString(Map<String,?> map) {
		JSONObject jsonObject = new JSONObject();
		try{
			for(Map.Entry<String, ?> entry : map.entrySet()) {
				Object value = parseJsonItemFromObject(entry.getValue());
				jsonObject.put(entry.getKey(), value);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
	private static void validateJsonMapType(Class[] type) throws Exception {
		if(null == type || type.length != 2) {
			throw new Exception("invalid map key-value type " + Arrays.toString(type));
		}
		
		if(!type[0].equals(String.class)) {
			throw new Exception("invalid map key type " + type[0]);
		}
	}

	public static <T> List<T> toList(String json,Class<T> typeClass){
		List<T> list = new ArrayList<>();
		try{
			JSONArray jsonArray = new JSONArray(json);
			for(int i = 0;i < jsonArray.length();i ++) {
				Object object = jsonArray.get(i);
				if(object instanceof JSONObject) {
					T listObject = toObject(object.toString(),typeClass);
					list.add(listObject);
				}else if(isPrimitive(object)){
					list.add((T) object);
				}else {
					throw new Exception("toList Exception: unsupportedType");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public static <T> Map<String,T> toMap(String json,Class<T> typeClass) {
		Map<String,T> map = new HashMap<>();
		try{
			JSONObject jsonObject = new JSONObject(json);
			Iterator<String> iterator = jsonObject.keys();
			while(iterator.hasNext()) {
				String key = iterator.next();
				Object value = jsonObject.get(key);
				T valueObject;
				if(value instanceof JSONObject){
					valueObject = toObject(value.toString(), typeClass);
				} else if(isPrimitive(value)) {
					valueObject = (T) value;
				} else {
					throw new Exception("unsurpport type");
				}
				
				map.put(key, valueObject);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	public static <T> T toObject(String json,Class<T> typeClass) {
		if(json == null || json.equals("") || json.equals("null")) {
			return null;
		}
		
		T instance = null;
		
		try{
			JSONObject jsonObject = new JSONObject(json);
			Constructor<T> constructor = typeClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			instance = constructor.newInstance();
			List<Field> fieldList = iteraterFieldList(typeClass);
			for(Field field : fieldList) {
				field.setAccessible(true);
				String key = parseJsonName(field);
				Object value = jsonObject.opt(key);
				if(value != null) {
					if(value instanceof JSONArray){
						List list = toList(value.toString(),getListFieldType(field));
						field.set(instance, list);
					} else if(isPrimitive(value)) {
						field.set(instance, value);
					} else {
						if(Map.class == field.getType()){
							Class[] kvType = getMapFieldType(field);
							validateJsonMapType(kvType);
							Map map = toMap(value.toString(), kvType[1]);
							field.set(instance, map);
						} else {
							Object valueObject = toObject(value.toString(),field.getType());
							field.set(instance, valueObject);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return instance;
	}
	
	private static Class getListFieldType(Field field) throws Exception {
		if(List.class != field.getType() && !List.class.isAssignableFrom(field.getType())) {
			throw new Exception("not a list,array list filed @ " + field);
		}
		
		Class classType = parseFieldListParameterType(field);
		if(classType == null) {
			Type type = field.getGenericType();
			ParameterizedType paramTypes = (ParameterizedType) type;
			Type paramType = paramTypes.getActualTypeArguments()[0];
			classType = (Class) paramType;
		}
		return classType;
	}
	
	private static Class[] getMapFieldType(Field field) throws Exception{
		if(Map.class != field.getType()) {
			throw new Exception("not a Map Field");
		}
		
		Type fieldType = field.getGenericType();
		ParameterizedType paramTypes = (ParameterizedType) fieldType;
		Class[] kvtype = new Class[2];
		kvtype[0] = (Class) paramTypes.getActualTypeArguments()[0];
		kvtype[1] = (Class) paramTypes.getActualTypeArguments()[1];
		
		return kvtype;
	}
	
	private static String parseJsonName(Field field) {
		String key;
		JsonName annation = field.getAnnotation(JsonName.class);
		if (annation == null) {
			key = field.getName();
		} else {
			key = annation.value();
		}

		return key;
	}

	private static Class parseFieldListParameterType(Field field) {
		Class type = null;
		JsonName jsonName = field.getAnnotation(JsonName.class);
		if (jsonName != null) {
			type = jsonName.listParameterType();
		}
		return type;
	}

	private static boolean isPrimitive(Object object) {
		return object instanceof String || object instanceof Integer || object instanceof Boolean
				|| object instanceof Long || object instanceof Float;
	}

	private static Object parseJsonItemFromObject(Object object) throws Exception {

		if (object == null) {
			return null;
		}

		if (object instanceof List<?>) {
			String s = toString(object);
			return new JSONArray(s);
		} else if (object instanceof Map<?, ?>) {
			String s = toString(object);
			return new JSONObject(s);
		} else if (isPrimitive(object)) {
			return object;
		} else {
			if (isNotSupportList(object)) {
				return new JSONObject();
			}
			String s = toString(object);
			return new JSONObject(s);
		}
	}

	private static boolean isNotSupportList(Object object) {
		return object instanceof byte[];
	}
}
