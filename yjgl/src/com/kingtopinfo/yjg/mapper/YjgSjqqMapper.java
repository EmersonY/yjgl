package com.kingtopinfo.yjg.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.yjg.entity.YjgSjqqEntity;

/**
 * @ClassName mapper.YjgSjqqMapper
 * @Description YJG_SJQQ表数据库操作接口
 * @author cyf
 * @date 2017-09-07 11:06:59
 * @version 1.0
 * @remark create by generator
 */
public interface YjgSjqqMapper {
	
	int getCount(YjgSjqqEntity e);
	List<YjgSjqqEntity> selectPagination(YjgSjqqEntity e,RowBounds rowBounds);
	YjgSjqqEntity getByPkey(String sjqsid);
	int insert(YjgSjqqEntity e);
	int update(YjgSjqqEntity e);
	int delete(String sjqsid);
	YjgSjqqEntity findSjqqBySjdjid(String sjdjid);
	int deleteBySjdjid(String sjdjid);

}