package com.kingtopinfo.base.security;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 *@Title:CSRF拦截器类
 *@Description:设置不被CSRF管理的页面，在applicationContext-security.xml配置文件中配置
 *@Author:dzb@kingtopinfo.com
 *@Since:2017年7月7日上午9:37:38
 *@Version:1.0
 */

public class CsrfSecurityRequestMatcher implements RequestMatcher {
	
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|PUT|DELETE|TRACE|CONNECT|OPTIONS)$");
	
	private List<String> execludeUrls;

	public List<String> getExecludeUrls() {
		return execludeUrls;
	}

	public void setExecludeUrls(List<String> execludeUrls) {
		this.execludeUrls = execludeUrls;
	}

	@Override
	public boolean matches(HttpServletRequest request) {

		if (execludeUrls != null && execludeUrls.size() > 0) {
			String servletPath = request.getServletPath();
			for (String url : execludeUrls) {
				if (servletPath.contains(url)) {
					return false;
				}
			}
		}
		return !allowedMethods.matcher(request.getMethod()).matches();
	}

}
