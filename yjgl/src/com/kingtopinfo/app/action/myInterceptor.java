package com.kingtopinfo.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.kingtopinfo.base.util.mapUtil;

public class myInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private Memory memory;
	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token  =request.getHeader("tokenid");
    	String seed = request.getHeader("seed");  
    	String tokenid =(String)memory.getValue(seed);
    	if(tokenid != null && "".equals(tokenid) == false){
	    	if(tokenid.equals(token)){
//	    		String json = JSONObject.toJSONString(mapUtil.setMaputil403("该账号已经在别处登录！",null));
//				response.setContentType("text/html;charset=UTF-8");
//				response.getWriter().write(json);
	    		return true;
	    	}else{
	    		String json = JSONObject.toJSONString(mapUtil.setMaputil403("该账号已经在别处登录，请重新登录！",null));
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(json);
	    		return false;
	    	}
    	}else{
    		String json = JSONObject.toJSONString(mapUtil.setMaputil403("账号已过期需要重新登陆！",null));
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(json);
    		return false;
    	}
    }
    
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler, ModelAndView modelAndView) throws Exception {  
    }  
//

}