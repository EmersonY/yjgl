package com.kingtopinfo.activiti.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblFlowTaskMenuMappingEntity;

/**
 * @ClassName mapper.TblFlowTaskMenuMappingMapper
 * @Description TBL_FLOW_TASK_MENU_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-09-20 09:39:46
 * @version 1.0
 * @remark create by generator
 */
public interface TblFlowTaskMenuMappingMapper {
	
	int getCount(TblFlowTaskMenuMappingEntity e);
	List<TblFlowTaskMenuMappingEntity> selectPagination(TblFlowTaskMenuMappingEntity e,RowBounds rowBounds);
	List<TblFlowTaskMenuMappingEntity> select();
	TblFlowTaskMenuMappingEntity getByPkey(String flowtaskmenumappingid);
	int insert(TblFlowTaskMenuMappingEntity e);
	int update(TblFlowTaskMenuMappingEntity e);
	int delete(String flowtaskmenumappingid);
	int deleteMenuMappingByMenuId(String flowmenuid);
	List<TblFlowTaskMenuMappingEntity> selectByProcessidAndTaskid(TblFlowTaskMenuMappingEntity tblFlowMenuMappingEntity);
	TblFlowTaskMenuMappingEntity selectByMenuidAndProcess(TblFlowTaskMenuMappingEntity e);
}