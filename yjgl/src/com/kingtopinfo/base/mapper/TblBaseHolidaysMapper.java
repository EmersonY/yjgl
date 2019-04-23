package com.kingtopinfo.base.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseHolidaysEntity;

/**
 * @ClassName mapper.TblBaseHolidaysMapper
 * @Description TBL_BASE_HOLIDAYS表数据库操作接口
 * @author cyf
 * @date 2018-01-12 09:14:28
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseHolidaysMapper {
	
	int getCount(TblBaseHolidaysEntity e);
	List<TblBaseHolidaysEntity> selectPagination(TblBaseHolidaysEntity e,RowBounds rowBounds);
	List<TblBaseHolidaysEntity> select();
	TblBaseHolidaysEntity getByPkey(String holidaysid);
	int insert(TblBaseHolidaysEntity e);
	int update(TblBaseHolidaysEntity e);
	int delete(String holidaysid);
	List<TblBaseHolidaysEntity> selectByStartAndEnd(@Param("timeStart") Date timeStart, @Param("timeEnd") Date timeEnd);

}