package com.kingtopinfo.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.kingtopinfo.base.entity.TblBaseMenuEntity;

/**
 * @ClassName com.kingtopinfo.base.security.FilterMetadataSource.java
 * @Description: 拦截元数据管理器，用于配置需要拦截的资源及刷新资源（拦截列表代码缓存）。
 * @author dzb@kingtopinfo.com
 * @date 2016年3月28日 上午9:30:51
 * @version 1.0
 */

public class ResourceMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private TBaseResUrlSecurity tBaseResUrlSecurity;
	
	public ResourceMetadataSource(TBaseResUrlSecurity tBaseResUrlSecurity){

		this.tBaseResUrlSecurity = tBaseResUrlSecurity;
		loadResourceDefine();
	}

	public void loadResourceDefine() {

		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("base");
		atts.add(ca);
		//resourceMap.put("/base/*", atts);
		resourceMap.put("/main/index", atts);
		List<TblBaseMenuEntity> urlList = tBaseResUrlSecurity.getAllUrl();
		for(int t=0; t<urlList.size(); t++){
			String url = urlList.get(t).getSrc();
			List<String> list = tBaseResUrlSecurity.getRoleByUrl(url);	
			atts = new ArrayList<ConfigAttribute>();
			for(int i=0; i<list.size(); i++){
				String roleid = list.get(i);
				ca = new SecurityConfig(roleid);
				atts.add(ca);
			}
			url = url.replace("../", "/");
			resourceMap.put(url, atts);
		}
	}
	
	public Collection<ConfigAttribute> getAttributes(Object object){
		
		FilterInvocation filterInvocation = (FilterInvocation) object;
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
		    if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
		        return resourceMap.get(resURL);
		    }
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
