package com.kingtopinfo.yjg.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.yjg.entity.YjgBwlEntity;

/**
 * @ClassName mapper.YjgBwlMapper
 * @Description YJG_BWL表数据库操作接口
 * @author cyf
 * @date 2018-01-11 10:32:10
 * @version 1.0
 * @remark create by generator
 */
public interface YjgBwlMapper {
	
	YjgBwlEntity getByPkey(String bwlid);
	int update(YjgBwlEntity e);
	int insert(YjgBwlEntity e);
	int delete(String bwlid);
	List<YjgBwlEntity> selectBwl(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("baseuserid") String baseuserid);
	int getBwlCount(@Param("bwlwz") String bwlwz, @Param("bwlnr") String bwlnr, @Param("startDate") Date startDate, @Param("lastDay") Date lastDay,
			@Param("baseuserid") String baseuserid);
	List<YjgBwlEntity> selectBwlPagination(@Param("bwlwz") String bwlwz, @Param("bwlnr") String bwlnr, @Param("startDate") Date startDate, @Param("lastDay") Date lastDay,
			@Param("baseuserid") String baseuserid, RowBounds rowBounds);
	int remindBwl(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("baseuserid") String baseuserid);
}