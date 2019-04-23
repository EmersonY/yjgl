package com.kingtopinfo.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseRoleMenuMappingEntity;
import com.kingtopinfo.base.mapper.TblBaseRoleMenuMappingMapper;
import com.kingtopinfo.base.util.IDUtil;

/**
 * @ClassName service.TblBaseRoleMenuMappingService
 * @Description TBL_BASE_ROLE_MENU_MAPPING表服务类
 * @author cyf
 * @date 2017-06-08 17:03:12
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseRoleMenuMappingService {
	
	@Autowired
	private TblBaseRoleMenuMappingMapper tblBaseRoleMenuMappingMapper;
	
	/**
	 * @Description 添加TblBaseRoleMenuMappingEntity信息
	 * @param e TblBaseRoleMenuMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-06-08 17:03:12
	 */	
	public int insert(TblBaseRoleMenuMappingEntity e){
		e.setBaserolemenumid(IDUtil.getId());
		return tblBaseRoleMenuMappingMapper.insert(e);
	}
	
	/**
	 * @Description:删除菜单中角色
	 * @author:cyf
	 * @time:2017年6月8日 下午5:48:39
	 */
	public int deleteByRoleidAndMenuid(TblBaseRoleMenuMappingEntity e) {
		return tblBaseRoleMenuMappingMapper.deleteByRoleidAndMenuid(e);
	}

}