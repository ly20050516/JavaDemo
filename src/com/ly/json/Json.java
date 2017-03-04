package com.ly.json;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
