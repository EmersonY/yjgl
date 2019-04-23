package com.kingtopinfo.yjg.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.yjg.entity.YjgXmlcEntity;

/**
 * @ClassName mapper.YjgXmlcMapper
 * @Description YJG_XMLC表数据库操作接口
 * @author cyf
 * @date 2017-09-21 11:09:29
 * @version 1.0
 * @remark create by generator
 */
public interface YjgXmlcMapper {
	
	int getCount(YjgXmlcEntity e);
	List<YjgXmlcEntity> selectPagination(YjgXmlcEntity e,RowBounds rowBounds);
	List<YjgXmlcEntity> select();
	YjgXmlcEntity getByPkey(String xmlcid);
	int insert(YjgXmlcEntity e);
	int update(YjgXmlcEntity e);
	int delete(String xmlcid);
	YjgXmlcEntity selectInstanceBySjdjid(String sjdjid);

}