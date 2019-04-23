package com.kingtopinfo.yjg.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.yjg.entity.YjgSjczEntity;

/**
 * @ClassName mapper.YjgSjczMapper
 * @Description YJG_SJCZ表数据库操作接口
 * @author cyf
 * @date 2017-09-12 08:50:55
 * @version 1.0
 * @remark create by generator
 */
public interface YjgSjczMapper {
	
	int getCount(YjgSjczEntity e);
	List<YjgSjczEntity> selectPagination(YjgSjczEntity e,RowBounds rowBounds);
	List<YjgSjczEntity> selectByCzzt(YjgSjczEntity e);
	YjgSjczEntity getByPkey(String sjczid);
	int insert(YjgSjczEntity e);
	int update(YjgSjczEntity e);
	int updateCzzt(String sjdjid);
	int resetCzzt(String resetCzzt);
	int delete(String sjczid);
	List<YjgSjczEntity> selectBySjdjId(YjgSjczEntity e);
	List<YjgSjczEntity> selectByCondition(YjgSjczEntity e);
	int deleteByState(String sjczid);

}