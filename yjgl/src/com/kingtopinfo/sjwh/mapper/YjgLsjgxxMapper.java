package com.kingtopinfo.sjwh.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.sjwh.entity.YjgLsjgxxEntity;

/**
 * @ClassName mapper.YjgLsjgxxMapper
 * @Description YJG_LSJGXX表数据库操作接口
 * @author cyf
 * @date 2017-10-19 16:19:44
 * @version 1.0
 * @remark create by generator
 */
public interface YjgLsjgxxMapper {
	
	int getCount(YjgLsjgxxEntity e);
	List<YjgLsjgxxEntity> selectPagination(YjgLsjgxxEntity e, RowBounds rowBounds);
	List<YjgLsjgxxEntity> select();
	YjgLsjgxxEntity getByPkey(String lsjgid);
	int insert(YjgLsjgxxEntity e);
	List<YjgLsjgxxEntity> getSjcjList(YjgLsjgxxEntity e);
	int update(YjgLsjgxxEntity e);
	int delete(String lsjgid);
	
}