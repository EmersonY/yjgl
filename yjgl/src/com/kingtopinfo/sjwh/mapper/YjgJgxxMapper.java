package com.kingtopinfo.sjwh.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.sjwh.entity.YjgJgxxEntity;

/**
 * @ClassName mapper.YjgJgxxMapper
 * @Description YJG_JGXX表数据库操作接口
 * @author cyf
 * @date 2017-10-19 16:18:13
 * @version 1.0
 * @remark create by generator
 */
public interface YjgJgxxMapper {
	
	int getCount(YjgJgxxEntity e);
	List<YjgJgxxEntity> selectPagination(YjgJgxxEntity e, RowBounds rowBounds);
	List<YjgJgxxEntity> selectPaginationByrtzt(YjgJgxxEntity e, RowBounds rowBounds);
	YjgJgxxEntity getByJgbh(String jgbh);
	YjgJgxxEntity getByPkey(String jgid);
	int insert(YjgJgxxEntity e);
	int update(YjgJgxxEntity e);
	int delete(String jgid);
	
}