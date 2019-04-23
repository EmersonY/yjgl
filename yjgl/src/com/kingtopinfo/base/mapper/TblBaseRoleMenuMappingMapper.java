package com.kingtopinfo.base.mapper;

import com.kingtopinfo.base.entity.TblBaseRoleMenuMappingEntity;
/**
 * @ClassName mapper.TblBaseRoleMenuMappingMapper
 * @Description TBL_BASE_ROLE_MENU_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-06-05 14:20:01
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseRoleMenuMappingMapper {
	
	int insert(TblBaseRoleMenuMappingEntity e);
	int deleteByMenuid(String menuid);
	int deleteByRoleid(String roleid);
	int deleteByRoleidAndMenuid(TblBaseRoleMenuMappingEntity e);

}