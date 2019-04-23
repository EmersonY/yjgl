package com.kingtopinfo.activiti.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.activiti.mapper.TblFlowTaskRoleMappingMapper;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblFlowTaskRoleMappingService
 * @Description TBL_FLOW_TASK_ROLE_MAPPING表服务类
 * @author cyf
 * @date 2017-08-21 15:05:42
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblFlowTaskRoleMappingService {
	
	@Autowired
	private TblFlowTaskRoleMappingMapper tblFlowTaskRoleMappingMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblFlowTaskRoleMappingEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */
	public int getCount(TblFlowTaskRoleMappingEntity e){
		return tblFlowTaskRoleMappingMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblFlowTaskRoleMappingEntity实体
	 * @param rowBounds 分页实体
	 * @return TblFlowTaskRoleMappingEntity集合
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */
	public List<TblFlowTaskRoleMappingEntity> selectPagination(TblFlowTaskRoleMappingEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblFlowTaskRoleMappingMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblFlowTaskRoleMappingEntity集合
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	public List<TblFlowTaskRoleMappingEntity> select(){
		return tblFlowTaskRoleMappingMapper.select();
	}
	
	/**
	 * @Description 按flow_task_role查询TblFlowTaskRoleMappingEntity信息
	 * @param flow_task_role 主键flow_task_role
	 * @return TblFlowTaskRoleMappingEntity实体
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */		
	public TblFlowTaskRoleMappingEntity getByPkey(String flow_task_role){
		return tblFlowTaskRoleMappingMapper.getByPkey(flow_task_role);
	}
	
	/**
	 * @Description 添加TblFlowTaskRoleMappingEntity信息
	 * @param e TblFlowTaskRoleMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	public int insert(TblFlowTaskRoleMappingEntity e){
		e.setFlowtaskroleid(IDUtil.getId());
		e.setUpdatetime(new Date());
		e.setDxlx("1");
		return tblFlowTaskRoleMappingMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblFlowTaskRoleMappingEntity信息
	 * @param e TblFlowTaskRoleMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	public int update(TblFlowTaskRoleMappingEntity e){
		return tblFlowTaskRoleMappingMapper.update(e);
	}
	
	/**
	 * @Description 按flow_task_role删除TblFlowTaskRoleMappingEntity信息
	 * @param flow_task_role
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	public int delete(String flow_task_role){
		return tblFlowTaskRoleMappingMapper.delete(flow_task_role);
	}
	
	/**
	 * @Description 按flow_task_role集合批量删除TblFlowTaskRoleMappingEntity信息
	 * @param idArray flow_task_role集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
	
	public List<TblFlowTaskRoleMappingEntity> selectByProcessidAndTaskid(String processid, String taskid) {
		TblFlowTaskRoleMappingEntity e = new TblFlowTaskRoleMappingEntity();
		e.setProcessid(processid);
		e.setTaskid(taskid);
		return tblFlowTaskRoleMappingMapper.selectByProcessidAndTaskid(e);
	}
	
	public int deleteByRoleidAndProcessidAndTaskid(String roleid, String processid, String taskid) {
		TblFlowTaskRoleMappingEntity e = new TblFlowTaskRoleMappingEntity();
		e.setProcessid(processid);
		e.setTaskid(taskid);
		e.setRoleid(roleid);
		return tblFlowTaskRoleMappingMapper.deleteByRoleidAndProcessidAndTaskid(e);
	}

}