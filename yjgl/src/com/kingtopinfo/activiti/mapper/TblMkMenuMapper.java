package com.kingtopinfo.activiti.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblMkMenuEntity;
import com.kingtopinfo.base.entity.TblBaseMenuEntity;

/**
 * @ClassName mapper.TblMkMenuMapper
 * @Description TBL_MK_MENU表数据库操作接口
 * @author cyf
 * @date 2017-09-18 09:25:29
 * @version 1.0
 * @remark create by generator
 */
public interface TblMkMenuMapper {
	
	int getCount(TblMkMenuEntity e);
	List<TblMkMenuEntity> selectPagination(TblMkMenuEntity e,RowBounds rowBounds);
	List<TblMkMenuEntity> select();
	TblMkMenuEntity getByPkey(String id);
	int insert(TblMkMenuEntity e);
	int update(TblMkMenuEntity e);
	int delete(String id);
	int selectMaxSequ();
	List<TblBaseMenuEntity> selectByPid(String id);

}