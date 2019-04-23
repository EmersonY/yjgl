package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseLogEntity;

/**
 * @ClassName com.kingtopinfo.base.mapper.TblBaseLogMapper
 * @Description T_BASE_LOG表数据库操作接口
 * @author CYF
 * @date 2017-06-20 09:12:37
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseLogMapper {
	
	int selectCount(TblBaseLogEntity e);
	List<TblBaseLogEntity> selectPagination(TblBaseLogEntity e, RowBounds rowBounds);
	int insert(TblBaseLogEntity e);

}