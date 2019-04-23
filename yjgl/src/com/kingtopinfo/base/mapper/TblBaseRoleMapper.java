package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseRoleEntity;
/**
 * @ClassName mapper.TblBaseRoleMapper
 * @Description TBL_BASE_ROLE表数据库操作接口
 * @author cyf
 * @date 2017-06-05 14:21:42
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseRoleMapper {
	
	int getCount(TblBaseRoleEntity e);
	List<TblBaseRoleEntity> selectPagination(TblBaseRoleEntity e, RowBounds rowBounds);
	TblBaseRoleEntity getByPkey(String baseroleid);
	int insert(TblBaseRoleEntity e);
	int delete(String baseroleid);
	List<TblBaseRoleEntity> selectRoleTree();
	int update(TblBaseRoleEntity e);
	List<TblBaseRoleEntity> selectByPid(String baseroleid);
	int move(TblBaseRoleEntity e);
	List<TblBaseRoleEntity> selectAddedMenuRoles(String menuid);
	List<TblBaseRoleEntity> select();
	List<TblBaseRoleEntity> selectMKMenuRoles(String menuid);
	String selectSuperUserRoleid();
	TblBaseRoleEntity selectRoleidByUserid(String baseuserid);
	List<TblBaseRoleEntity> selectSecondRole(TblBaseRoleEntity e);
	TblBaseRoleEntity selectRoleByUserid(String baseuserid);

}