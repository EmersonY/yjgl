package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseResourceEntity;

/**
 * @ClassName mapper.TblBaseResourceMapper
 * @Description TBL_BASE_RESOURCE表数据库操作接口
 * @author cyf
 * @date 2017-07-27 14:45:09
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseResourceMapper {
	
	int getCount(TblBaseResourceEntity e);
	List<TblBaseResourceEntity> selectPagination(TblBaseResourceEntity e,RowBounds rowBounds);
	List<TblBaseResourceEntity> select();
	TblBaseResourceEntity getByPkey(String baseresourceid);
	int insert(TblBaseResourceEntity e);
	int update(TblBaseResourceEntity e);
	int delete(String baseresourceid);
	List<TblBaseResourceEntity> selectInRoleId(List<String> roleIdList);
	
}