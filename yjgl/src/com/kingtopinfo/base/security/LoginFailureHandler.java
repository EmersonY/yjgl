package com.kingtopinfo.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @ClassName com.kingtopinfo.base.security.LoginFailureHandler.java
 * @Description: 用户登录失败有助手类，用于记录日志及做页面跳转。
 * @author dzb@kingtopinfo.com
 * @date 2016年3月25日 下午4:16:55
 * @version 1.0
 */

public class LoginFailureHandler implements AuthenticationFailureHandler {

	private String defaultTargetUrl;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {

		this.redirectStrategy.sendRedirect(request, response, this.defaultTargetUrl);
		
	}
	
	public void setDefaultTargetUrl(String defaultTargetUrl) {  
        this.defaultTargetUrl = defaultTargetUrl;  
    } 

}
