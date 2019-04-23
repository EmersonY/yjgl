package com.kingtopinfo.yjg.mapper;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.yjg.entity.YjgGglEntity;


/**
 * @ClassName mapper.YjgGglMapper
 * @Description YJG_GGL表数据库操作接口
 * @author cyf
 * @date 2017-12-06 09:07:28
 * @version 1.0
 * @remark create by generator
 */
public interface YjgGglMapper {
	
	List<YjgGglEntity> selectGg();
	int getCount(YjgGglEntity e);
	List<YjgGglEntity> selectPagination(YjgGglEntity e,RowBounds rowBounds);
	List<YjgGglEntity> select();
	YjgGglEntity getByPkey(String gglid);
	int insert(YjgGglEntity e);
	int update(YjgGglEntity e);
	int delete(String gglid);

}