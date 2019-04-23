package com.kingtopinfo.base.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName com.kingtopinfo.base.security.MyAccessDecisionManager.java
 * @Description: 资源权限判定管理，用于判定用户访问的资源是否有权限。
 * @author dzb@kingtopinfo.com
 * @date 2016年3月25日 下午4:19:50
 * @version 1.0
 */

public class UrlDecisionManager implements AccessDecisionManager {

	/**
	 * 资源权限配合拦截，不满足要求的资源抛出异常
	 * authentication 用户拥有的权限
	 * object 拦截对象
	 * configAttributes	资源需要的权限
	 * 后续添加行为审计
	 */
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes){
		
        if(configAttributes == null){
            return;
        }else{
        	
        	 Iterator<ConfigAttribute> ite = configAttributes.iterator();
             while(ite.hasNext()){
             	
                 ConfigAttribute ca = ite.next();
                 String needRole = ((SecurityConfig)ca).getAttribute();
                 Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
                 authentication.getAuthorities();
                 for(GrantedAuthority ga:auth){
                     if(needRole.equals(ga.getAuthority())){
                         return;
                     }
                 }
             }
             throw new AccessDeniedException("没有足够的权限访问资源");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
