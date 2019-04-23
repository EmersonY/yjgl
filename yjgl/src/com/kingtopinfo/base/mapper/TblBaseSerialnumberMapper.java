package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseSerialnumberEntity;

/**
 * @ClassName mapper.TblBaseSerialnumberMapper
 * @Description TBL_BASE_SERIALNUMBER表数据库操作接口
 * @author cyf
 * @date 2017-09-11 11:28:17
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseSerialnumberMapper {
	
	int getCount(TblBaseSerialnumberEntity e);
	List<TblBaseSerialnumberEntity> selectPagination(TblBaseSerialnumberEntity e,RowBounds rowBounds);
	List<TblBaseSerialnumberEntity> select();
	TblBaseSerialnumberEntity getByPkey(String serialnumberid);
	int insert(TblBaseSerialnumberEntity e);
	int update(TblBaseSerialnumberEntity e);
	int delete(String serialnumberid);
	List<String> findMaxSeqByDateStr(String dateStr);
	List<String> findMaxSeqByType(String type);
	

}