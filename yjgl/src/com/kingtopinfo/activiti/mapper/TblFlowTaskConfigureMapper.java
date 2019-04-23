package com.kingtopinfo.activiti.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblFlowTaskConfigureEntity;

/**
 * @ClassName mapper.TblFlowTaskConfigureMapper
 * @Description Tbl_FLOW_TASK_CONFIGURE表数据库操作接口
 * @author cyf
 * @date 2017-09-19 14:22:08
 * @version 1.0
 * @remark create by generator
 */
public interface TblFlowTaskConfigureMapper {
	
	int getCount(TblFlowTaskConfigureEntity e);
	List<TblFlowTaskConfigureEntity> selectPagination(TblFlowTaskConfigureEntity e,RowBounds rowBounds);
	List<TblFlowTaskConfigureEntity> select();
	TblFlowTaskConfigureEntity getByPkey(String id);
	int insert(TblFlowTaskConfigureEntity e);
	int update(TblFlowTaskConfigureEntity e);
	int delete(String id);
	TblFlowTaskConfigureEntity selectByProcessidAndTaskid(TblFlowTaskConfigureEntity e);


}