package com.kingtopinfo.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseUserEntity;

public interface TblBaseUserMapper {

	Integer count(TblBaseUserEntity e);
	Integer getCountByRoleid(TblBaseUserEntity e);
	Integer unsecondCount(TblBaseUserEntity e);
	List<TblBaseUserEntity> selectPagination(TblBaseUserEntity e,RowBounds rowBounds);
	List<TblBaseUserEntity> selectByRoleidPagination(TblBaseUserEntity e, RowBounds rowBounds);
	List<TblBaseUserEntity> selectUnsecondPagination(TblBaseUserEntity e, RowBounds rowBounds);
	int insert(TblBaseUserEntity e);
	int update(TblBaseUserEntity e);
	TblBaseUserEntity selectByAccount(String account);
	TblBaseUserEntity selectUserByAccount(String account);
	TblBaseUserEntity selectByAccountapp(String account);
	int delete(String baseuserid);
	TblBaseUserEntity getByPkey(String baseuserid);
	List<TblBaseUserEntity> getByPbaseuserid(String pbaseuserid);
	int updatePassword(TblBaseUserEntity e);
	List<TblBaseUserEntity> selectPaginationByRoleId(TblBaseUserEntity tBaseUserEntity, RowBounds rowBounds);
	Integer selectCountByRoleId(TblBaseUserEntity tBaseUserEntity);
	List<TblBaseUserEntity> selectUnPaginationByRoleId(TblBaseUserEntity tBaseUserEntity, RowBounds rowBounds);
	Integer selectUnCountByRoleId(TblBaseUserEntity tBaseUserEntity);
	List<Map<String, Object>> selectUserMsgById(String baseuserid);
	List<String> selectRoleIdByAccount(String account);
	Integer getCountByuserId(TblBaseUserEntity e);
	List<TblBaseUserEntity> selectByuserIdPagination(TblBaseUserEntity e, RowBounds rowBounds);
	List<TblBaseUserEntity> selectByUnUserIdPagination(TblBaseUserEntity e, RowBounds rowBounds);
	Integer getCountByUnUserId(TblBaseUserEntity e);
	int deletePuser(TblBaseUserEntity tblBaseUserEntity);
}
