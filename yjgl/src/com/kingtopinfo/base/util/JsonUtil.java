package com.kingtopinfo.base.util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	public static final String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 将json字符串转化成对象 (默认日期时间格式为：yyyy-MM-dd HH:mm:ss)
	 * 注：	1.支持Map、List、Set、任意类型数组、Object;
	 * 		2.除了任意类型数组和Object外，不支持容器类型中放入javaBean实体，例如:List<JavaBean>、Set<JavaBean>等。但支持容器中放入Map对象，例如：:List<Map<String,Object>>、Set<Map<String,Object>>）
	 * @param json 要转化的json字符串
	 * @param valueType 结果类型的类
	 * @param datePattern 日期（日期+时间）格式
	 * @return 
	 * @author 陈海彬 chenhb@kingtopinfo.com 2014-12-03
	 */
	public static <t>t toObject(String json, Class<t> valueType) {
		return toObject(json,valueType,defaultDatePattern);
	}	
	
	/**
	 * 将json字符串转化成对象 
	 * 注：	1.支持Map、List、Set、任意类型数组、Object;
	 * 		2.除了任意类型数组和Object外，不支持容器类型中放入javaBean实体，例如:List<JavaBean>、Set<JavaBean>等。但支持容器中放入Map对象，例如：:List<Map<String,Object>>、Set<Map<String,Object>>）
	 * @param json 要转化的json字符串
	 * @param valueType 结果类型的类
	 * @param datePattern 日期（日期+时间）格式
	 * @return 
	 * @author 陈海彬 chenhb@kingtopinfo.com 2014-12-03
	 */
	@SuppressWarnings("unchecked")
	public static <t>t toObject(String json, Class<t> valueType,String datePattern) {
		if(json == null)
			return null;
		Object result = null;
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat(datePattern));
		try {
			result = objectMapper.readValue(json,valueType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (t)result;
	}
	
	/**
	 * 将java对象转成json字符串(默认日期时间格式为：yyyy-MM-dd HH:mm:ss)
	 * @param object 要转化的对象
	 * @return 
	 * @author 陈海彬 chenhb@kingtopinfo.com 2014-12-03
	 */
	public static String toJson(Object object) {
		return toJson(object,defaultDatePattern);
	}	
	
	/**
	 * 将java对象转成json字符串
	 * @param object 要转化的对象
	 * @param datePattern 日期（日期+时间）格式
	 * @return 
	 * @author 陈海彬 chenhb@kingtopinfo.com 2014-12-03
	 */
	public static String toJson(Object object,String datePattern) {
		if(object == null)
			return null;
		String result = "";
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat(datePattern));
		try {
			result = objectMapper.writeValueAsString(object);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
