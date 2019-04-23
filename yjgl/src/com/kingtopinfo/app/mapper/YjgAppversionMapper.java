package com.kingtopinfo.app.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.app.entity.YjgAppversionEntity;

/**
 * @ClassName mapper.YjgAppversionMapper
 * @Description YJG_APPVERSION表数据库操作接口
 * @author cyf
 * @date 2018-01-02 09:51:19
 * @version 1.0
 * @remark create by generator
 */
public interface YjgAppversionMapper {
	
	int getCount(YjgAppversionEntity e);
	List<YjgAppversionEntity> selectPagination(YjgAppversionEntity e,RowBounds rowBounds);
	List<YjgAppversionEntity> select();
	YjgAppversionEntity getByPkey(String id);
	int insert(YjgAppversionEntity e);
	int update(YjgAppversionEntity e);
	int delete(String id);
	int selectMaxVersioncode();
}