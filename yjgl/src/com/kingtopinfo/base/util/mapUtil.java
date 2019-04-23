package com.kingtopinfo.base.util;

import java.util.HashMap;
import java.util.Map;

public class mapUtil {

	public static Map<String, Object> setMaputil(int code,String massage,String token,Object data) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("errorCode", code);
		map.put("msg", massage);
		map.put("token", token);
		map.put("data", data);
		return map;
	}
	public static Map<String, Object> setMaputil200(String massage,String token,Object data) {
		return setMaputil(200,massage,token,data);
	}
	public static Map<String, Object> setMaputil402(String massage ,Object data) {
		return setMaputil(402,massage,null,data);
	}
	public static Map<String, Object> setMaputil400(String massage,Object data) {
		return setMaputil(400,massage,null,data);
	}
	public static Map<String, Object> setMaputil403(String massage,Object data) {
		return setMaputil(403,massage,null,data);
	}
	public static Map<String, Object> setMaputil401(String massage,Object data) {
		return setMaputil(401,massage,null,data);
	}
	public static Map<String, Object> setMaputil500(String massage,Object data) {
		return setMaputil(500,massage,null,data);
	}
	

}
