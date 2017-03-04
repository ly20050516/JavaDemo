package com.ly.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONObject;

public class JsonMap implements Cloneable {

	private JSONObject mJsonObject;

	public JsonMap() {
		mJsonObject = new JSONObject();
	}

	public JsonMap(JSONObject jsonObject) {
		mJsonObject = jsonObject;
		if (null == mJsonObject) {
			mJsonObject = new JSONObject();
		}
	}

	public JsonMap(String jsonString) {
		parseFromJsonString(jsonString);
	}
	
	public<T> JsonMap(HashMap<String, T> map) {
		parseFromHasMap(map);
	}
	
	public JsonMap(JsonMap other) {
		if(other != null) {
			parseFromJsonString(other.toString());
		}else {
			mJsonObject = new JSONObject();
		}
	}
	
	private static boolean putValue(JSONObject jsonObject, String key, Object val) {

		if (null != jsonObject) {
			try {
				jsonObject.put(key, val);
			} catch (Throwable t) {
				t.printStackTrace();
				return false;
			}
		}
		return true;

	}

	public void putBoolean(String key, boolean val) {
		putValue(mJsonObject, key, val);
	}

	public boolean getBoolean(String key, boolean def) {

		if (mJsonObject != null) {
			return mJsonObject.optBoolean(key, def);			
		}
		return def;
	}

	public void putDouble(String key, double val) {
		putValue(mJsonObject, key, val);
	}

	public double getDouble(String key, double def) {

		if (mJsonObject != null) {
			return mJsonObject.optDouble(key, def);
		}
		return def;
	}

	public void putLong(String key, long val) {
		putValue(mJsonObject, key, val);
	}

	public long getLong(String key, long def) {

		if (mJsonObject != null) {
			return mJsonObject.optLong(key, def);
		}
		return def;
	}

	public void putInt(String key, int val) {
		putValue(mJsonObject, key, val);
	}

	public int getInt(String key, int def) {
		if (mJsonObject != null) {
			return mJsonObject.optInt(key, def);
		}
		return def;
	}


	public void putString(String key, String val) {
		putValue(mJsonObject, key, val);
	}

	public String getString(String key, String def) {
		if (mJsonObject != null) {
			mJsonObject.optString(key, def);
		}
		return def;
	}

	public boolean parseFromJsonString(String jsonStr) {
		
		if(jsonStr != null && !jsonStr.equals("")) {
			JSONObject jsonObject = null;
			try{
				jsonObject = new JSONObject(jsonStr);
			}catch(Throwable t) {
				t.printStackTrace();
				return false;
			}
			mJsonObject = jsonObject;
			return true;
		}
		return false;
	}
	
	public<T> void addHashMap(HashMap<String, T> map){
		if(null != map && !map.isEmpty()){
			if(null == mJsonObject) {
				mJsonObject = new JSONObject();
			}
			
			for(Entry<String, T> entry : map.entrySet()) {
				putValue(mJsonObject, entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
	}
	
	public<T> void parseFromHasMap(HashMap<String, T> map) {
		mJsonObject = new JSONObject();
		addHashMap(map);
	}
	
	@Override
	public String toString() {
		
		StringBuilder tmpStr = new StringBuilder();
		tmpStr.append("{");
		
		if(mJsonObject != null) {
			Iterator<String> it = mJsonObject.keys();
			while(it.hasNext()) {
				String key = it.next();
				String val = mJsonObject.optString(key);
				
				tmpStr.append("\"");
				tmpStr.append(key);
				tmpStr.append("\":\"");
				tmpStr.append(val);
				tmpStr.append("\",");
			}
			
			tmpStr.deleteCharAt(tmpStr.length() - 1);
		}
		
		tmpStr.append("}");
		return tmpStr.toString();
	}
	
	@Override
	protected Object clone() {
		
		String content = toString();
		JsonMap jsonMap = new JsonMap(content);
		return jsonMap;
	}
}
