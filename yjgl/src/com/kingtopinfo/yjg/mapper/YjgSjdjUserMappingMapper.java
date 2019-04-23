package com.kingtopinfo.yjg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;

/**
 * @ClassName mapper.YjgSjdjUserMappingMapper
 * @Description YJG_SJDJ_USER_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-09-21 17:44:54
 * @version 1.0
 * @remark create by generator
 */
public interface YjgSjdjUserMappingMapper {
	
	int getCount(YjgSjdjUserMappingEntity e);
	List<YjgSjdjUserMappingEntity> selectPagination(YjgSjdjUserMappingEntity e, RowBounds rowBounds);
	List<YjgSjdjUserMappingEntity> select();
	YjgSjdjUserMappingEntity getByPkey(String yjgsjdjusermappingid);
	List<YjgSjdjUserMappingEntity> getBySjdjId(String yjgsjdjusermappingid);
	List<YjgSjdjUserMappingEntity> getByUserIdAndSjdjId(@Param("sjdjid") String sjdjid, @Param("type") String type);
	YjgSjdjUserMappingEntity getByUserIdAndSjdjIdAndType(@Param("sjdjid") String sjdjid, @Param("baseuserid") String baseuserid,
			@Param("type") String type);
	List<YjgSjdjUserMappingEntity> selectWqdsj(YjgSjdjUserMappingEntity e);
	int insert(YjgSjdjUserMappingEntity e);
	int update(YjgSjdjUserMappingEntity e);
	int delete(String yjgsjdjusermappingid);
	List<YjgSjdjUserMappingEntity> selectUnUserPagination(TblBaseUserEntity e, RowBounds rowBounds);
	int getUnUserCount(TblBaseUserEntity e);
	List<TblBaseUserEntity> selectUsered(TblBaseUserEntity e);
	List<TblBaseUserEntity> selectRefuseUsered(TblBaseUserEntity e);
	
	
}