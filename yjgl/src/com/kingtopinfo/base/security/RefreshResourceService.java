package com.kingtopinfo.base.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

/**
 * @ClassName com.kingtopinfo.base.service.RefreshResourceService.java
 * @Description: 刷新资源拦截器数据缓存，主要用于更新数据库权限资源列表后。
 * @author dzb@kingtopinfo.com
 * @date 2016年3月28日 上午9:52:27
 * @version 1.0
 */

@Service("RefreshResourceService")
public class RefreshResourceService extends ApplicationObjectSupport{

	public boolean refreshCache(){
		ApplicationContext ctx =  getApplicationContext();
		ResourceMetadataSource oMyInvocationSecurityMetadataSource = (ResourceMetadataSource)ctx.getBean("resourceMetadataSource");
		oMyInvocationSecurityMetadataSource.loadResourceDefine();
		return true;
	}
	
}
