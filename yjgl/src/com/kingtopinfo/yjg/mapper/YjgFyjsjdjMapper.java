package com.kingtopinfo.yjg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.yjg.entity.YjgFyjsjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;

/**
 * @ClassName mapper.YjgFyjsjdjMapper
 * @Description YJG_FYJSJDJ表数据库操作接口
 * @author cyf
 * @date 2017-08-25 15:02:06
 * @version 1.0
 * @remark create by generator
 */
public interface YjgFyjsjdjMapper {
	
	int getCount(YjgFyjsjdjEntity e);
	List<YjgFyjsjdjEntity> selectPagination(YjgFyjsjdjEntity e,RowBounds rowBounds);
	List<YjgFyjsjdjEntity> select();
	YjgFyjsjdjEntity getByPkey(String fyjsjdjid);
	int insert(YjgFyjsjdjEntity e);
	int update(YjgFyjsjdjEntity e);
	int delete(String fyjsjdjid);
	List<YjgSjdjEntity> listChildSjdj(@Param("sjdjid") String sjdjid);
	int separate(String fyjsjdjid);
	int getCountBySjlx(YjgFyjsjdjEntity yjgFyjsjdjEntity);

}