package com.kingtopinfo.base.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 *@Title:用户信息助手类
 *@Description:结合Spring-Security框架，提供用户信息及扩展信息查询（可自行扩展方法）
 *@Author:dzb@kingtopinfo.com
 *@Since:2017年5月26日下午3:09:09
 *@Version:1.0
 */

public class UserInfoUtil {

	private static UserInfoEntity		userInfoEntity	= null;
	private WebAuthenticationDetails wauth = null;
	
	/**
	 * @Description:获取用户账号
	 * @return
	 */
	public static String getAccount() {
		UserInfoEntity userDetails = getUserInfo();
		return userDetails.getAccount();
	}
	

	/**
	 * 获取登录用户名信息
	 * 
	 * @return
	 */
	public static String getUserName() {
		UserInfoEntity userDetails = getUserInfo();
		return userDetails.getUsername();
	}
	
	/**
	 * @Description:获取用户权限
	 * @return
	 */
	public static Collection<GrantedAuthority> getAuthorities() {
		UserInfoEntity userDetails = getUserInfo();
		return userDetails.getAuthorities();
	}
	
	/**
	 * @Description:获取用户ID
	 * @return
	 */
	public static String getBaseuserid() {
		UserInfoEntity userDetails = getUserInfo();
		return userDetails.getBaseuserid();
	}
	
	/**
	 * @Description:获取客户端IP地址
	 * @return
	 */
	public String getRemoteAddress(){
		return wauth.getRemoteAddress();
	}
	
	/**
	 * 获取客户端IP地址信息
	 * @param authentication
	 * @return
	 */
	public static String getRemoteAddress(Authentication authentication) {
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) authentication.getDetails();
		return webAuthenticationDetails.getRemoteAddress();
	}

	public UserInfoUtil(){
		userInfoEntity = (UserInfoEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		wauth = (WebAuthenticationDetails) auth.getDetails();
	}
	
	/**
	 * 获取用户信息
	 */
	public static UserInfoEntity getUserInfo() {
		UserInfoEntity userDetails = (UserInfoEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}
	
}
