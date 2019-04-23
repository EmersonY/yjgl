package com.kingtopinfo.activiti.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblFlowTaskTimeEntity;

/**
 * @ClassName mapper.TblFlowTaskTimeMapper
 * @Description TBL_FLOW_TASK_TIME表数据库操作接口
 * @author cyf
 * @date 2017-09-19 14:51:08
 * @version 1.0
 * @remark create by generator
 */
public interface TblFlowTaskTimeMapper {
	
	int getCount(TblFlowTaskTimeEntity e);
	List<TblFlowTaskTimeEntity> selectPagination(TblFlowTaskTimeEntity e,RowBounds rowBounds);
	List<TblFlowTaskTimeEntity> select();
	TblFlowTaskTimeEntity getByPkey(String id);
	int insert(TblFlowTaskTimeEntity e);
	int update(TblFlowTaskTimeEntity e);
	int delete(String id);
	TblFlowTaskTimeEntity selectByProcessid(String processid);

}