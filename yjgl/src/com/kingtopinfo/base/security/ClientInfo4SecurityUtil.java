package com.kingtopinfo.base.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @ClassName com.kingtopinfo.base.security.ClientInfo4SecurityUtil.java
 * @Description: 通过Security框架获取客户端信息
 * @author dzb@kingtopinfo.com
 * @date 2016年3月28日 上午10:28:45
 * @version 1.0
 */

public class ClientInfo4SecurityUtil {
	
	public static String getRemoteAddress(Authentication authentication){
		
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails)authentication.getDetails();
		return webAuthenticationDetails.getRemoteAddress();
	}
	
	public static String getSessionId(Authentication authentication){
		
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails)authentication.getDetails();
		return webAuthenticationDetails.getSessionId();
	}
}
