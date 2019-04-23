package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseRoleResourceMappingEntity;

/**
 * @ClassName mapper.TblBaseRoleResourceMappingMapper
 * @Description TBL_BASE_ROLE_RESOURCE_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-07-28 10:54:04
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseRoleResourceMappingMapper {
	
	int getCount(TblBaseRoleResourceMappingEntity e);
	List<TblBaseRoleResourceMappingEntity> selectPagination(TblBaseRoleResourceMappingEntity e,RowBounds rowBounds);
	List<TblBaseRoleResourceMappingEntity> select();
	List<TblBaseRoleResourceMappingEntity> selectByResourceId(String baseresourceid);
	List<TblBaseRoleResourceMappingEntity> selectByRoleId(String baseroleid);
	TblBaseRoleResourceMappingEntity getByPkey(String baseroleresourceid);
	int insert(TblBaseRoleResourceMappingEntity e);
	int update(TblBaseRoleResourceMappingEntity e);
	int delete(String baseroleresourceid);
	int deleteByResorceId(String baseresourceid);
	int deleteByRoleid(String roleid);

}