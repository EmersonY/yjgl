package com.kingtopinfo.activiti.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblFlowButtonMappingEntity;

/**
 * @ClassName mapper.TblFlowButtonMappingMapper
 * @Description TBL_FLOW_BUTTON_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-09-19 15:07:18
 * @version 1.0
 * @remark create by generator
 */
public interface TblFlowButtonMappingMapper {
	
	int getCount(TblFlowButtonMappingEntity e);
	List<TblFlowButtonMappingEntity> selectPagination(TblFlowButtonMappingEntity e,RowBounds rowBounds);
	List<TblFlowButtonMappingEntity> select();
	TblFlowButtonMappingEntity getByPkey(String id);
	int insert(TblFlowButtonMappingEntity e);
	int update(TblFlowButtonMappingEntity e);
	int delete(String id);
	List<TblFlowButtonMappingEntity> selectByProcessidAndTaskid(TblFlowButtonMappingEntity e);
	int deleteByProcessidAndTaskid(TblFlowButtonMappingEntity e);

}