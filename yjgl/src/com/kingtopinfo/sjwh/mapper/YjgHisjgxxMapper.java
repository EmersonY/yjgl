package com.kingtopinfo.sjwh.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.sjwh.entity.YjgHisjgxxEntity;

/**
 * @ClassName mapper.YjgHisjgxxMapper
 * @Description YJG_HISJGXX表数据库操作接口
 * @author cyf
 * @date 2017-10-19 16:19:36
 * @version 1.0
 * @remark create by generator
 */
public interface YjgHisjgxxMapper {
	
	int getCount(YjgHisjgxxEntity e);
	List<YjgHisjgxxEntity> selectPagination(YjgHisjgxxEntity e, RowBounds rowBounds);
	List<YjgHisjgxxEntity> select();
	YjgHisjgxxEntity getByPkey(String hisjgid);
	int insert(YjgHisjgxxEntity e);
	int update(YjgHisjgxxEntity e);
	int delete(String hisjgid);
	
}