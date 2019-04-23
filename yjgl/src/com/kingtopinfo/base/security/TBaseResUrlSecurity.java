package com.kingtopinfo.base.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kingtopinfo.base.entity.TblBaseMenuEntity;
import com.kingtopinfo.base.service.TblBaseMenuService;

public class TBaseResUrlSecurity{
	
	@Autowired
	private TblBaseMenuService tblBaseMenuService;

	/**
	 * @Description:通过角色编号获取对应的资源信息
	 * @param roleid
	 * @return
	 */
	public List<String> getResUrl(String roleid){
		
		return tblBaseMenuService.getResUrl(roleid);
	}
	
	public List<TblBaseMenuEntity> getAllUrl(){
		
		return tblBaseMenuService.selectAllUrl();
	}
	
	public List<String> getRoleByUrl(String url){
		
		List<String> result = new ArrayList<String>();
		List<TblBaseMenuEntity> list = tblBaseMenuService.selectByUrl(url);
		if(list != null && !list.isEmpty()){
			for(TblBaseMenuEntity each:list){
				TblBaseMenuEntity t = tblBaseMenuService.selectByKey(each.getBasemenupid());
				if(t != null){
					if(t.getSrc() != null){
						result.addAll(tblBaseMenuService.selectRoleByUrl(t.getSrc()));
					}
				}
			}
		}
		return result;
	}
	
	/**
	public List<TblBaseRoleEntity> getRoleid(){
		
		return tblBaseRoleService.select();
	}
	
	
	
	
	
	
	public List<String> getRoleByUrl(String url){
		if(url.indexOf(".action")<0)
			return tblBaseMenuService.selectRoleByUrl(url);
		else{
			List<String> result = new ArrayList<String>();
			List<TblBaseMenuEntity> list = tblBaseMenuService.selectByUrl(url);
			if(list != null && !list.isEmpty()){
				for(TblBaseMenuEntity each:list){
					TblBaseMenuEntity t = tblBaseMenuService.selectByKey(each.getPid());
					if(t != null)
						result.addAll(tblBaseMenuService.selectRoleByUrl(t.getUrl()));
				}
			}
			return result;
		}
			
	}

	public TblBaseMenuEntity getByMenuId(String menuId){
		return tblBaseMenuService.selectByKey(menuId);
	}
	**/
}
