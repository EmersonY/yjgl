package com.kingtopinfo.activiti.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;


/**
 * @ClassName mapper.TblFlowTaskRoleMappingMapper
 * @Description TBL_FLOW_TASK_ROLE_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-08-21 15:05:42
 * @version 1.0
 * @remark create by generator
 */
public interface TblFlowTaskRoleMappingMapper {
	
	int getCount(TblFlowTaskRoleMappingEntity e);
	List<TblFlowTaskRoleMappingEntity> selectPagination(TblFlowTaskRoleMappingEntity e,RowBounds rowBounds);
	List<TblFlowTaskRoleMappingEntity> select();
	TblFlowTaskRoleMappingEntity getByPkey(String flow_task_role);
	int insert(TblFlowTaskRoleMappingEntity e);
	int update(TblFlowTaskRoleMappingEntity e);
	int delete(String flow_task_role);
	int deleteByRoleid(String roleid);
	List<TblFlowTaskRoleMappingEntity> selectByProcessidAndTaskid(TblFlowTaskRoleMappingEntity e);
	int deleteByRoleidAndProcessidAndTaskid(TblFlowTaskRoleMappingEntity e);
}