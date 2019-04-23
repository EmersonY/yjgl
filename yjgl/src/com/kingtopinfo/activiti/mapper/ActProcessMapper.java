package com.kingtopinfo.activiti.mapper;

import java.util.List;

import com.kingtopinfo.activiti.entity.ActTaskEntity;

/**
 * com.kingtopinfo.base.mapper.ProcessMapper
 * Description :流程关联查询
 * @author lxc 
 * Create at 2016年11月23日 上午11:35:11
 */
public interface ActProcessMapper {
	List<ActTaskEntity> selectHistoryActByInstanceid(String instanceid);
	List<ActTaskEntity> selectHistoryActBySjdjid(String sjdjid);
}
