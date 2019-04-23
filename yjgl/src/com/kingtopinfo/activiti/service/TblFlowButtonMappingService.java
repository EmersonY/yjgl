package com.kingtopinfo.activiti.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowButtonMappingEntity;
import com.kingtopinfo.activiti.mapper.TblFlowButtonMappingMapper;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblFlowButtonMappingService
 * @Description TBL_FLOW_BUTTON_MAPPING表服务类
 * @author cyf
 * @date 2017-09-19 15:07:18
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblFlowButtonMappingService {
	
	@Autowired
	private TblFlowButtonMappingMapper tblFlowButtonMappingMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblFlowButtonMappingEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */
	public int getCount(TblFlowButtonMappingEntity e){
		return tblFlowButtonMappingMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblFlowButtonMappingEntity实体
	 * @param rowBounds 分页实体
	 * @return TblFlowButtonMappingEntity集合
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */
	public List<TblFlowButtonMappingEntity> selectPagination(TblFlowButtonMappingEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblFlowButtonMappingMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblFlowButtonMappingEntity集合
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */	
	public List<TblFlowButtonMappingEntity> select(){
		return tblFlowButtonMappingMapper.select();
	}
	
	/**
	 * @Description 按id查询TblFlowButtonMappingEntity信息
	 * @param id 主键id
	 * @return TblFlowButtonMappingEntity实体
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */		
	public TblFlowButtonMappingEntity getByPkey(String id){
		return tblFlowButtonMappingMapper.getByPkey(id);
	}
	
	/**
	 * @Description 添加TblFlowButtonMappingEntity信息
	 * @param e TblFlowButtonMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */	
	public int insert(TblFlowButtonMappingEntity e){
		return tblFlowButtonMappingMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblFlowButtonMappingEntity信息
	 * @param e TblFlowButtonMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */	
	public int update(TblFlowButtonMappingEntity e){
		return tblFlowButtonMappingMapper.update(e);
	}
	
	/**
	 * @Description 按id删除TblFlowButtonMappingEntity信息
	 * @param id
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */	
	public int delete(String id){
		return tblFlowButtonMappingMapper.delete(id);
	}
	
	/**
	 * @Description 按id集合批量删除TblFlowButtonMappingEntity信息
	 * @param idArray id集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}

	public List<TblFlowButtonMappingEntity> selectByProcessidAndTaskid(TblFlowButtonMappingEntity e) {
		return tblFlowButtonMappingMapper.selectByProcessidAndTaskid(e);
	}
	
	/**
	 * 保存按钮的时候先删除记录再根据buttonid插入
	 */
	public int save(String processid, String taskid, String buttonid) {
		int row = 0;
		deleteByProcessidAndTaskid(processid, taskid);
		String[] buttonidArray = null;
		if (buttonid != null)
			buttonidArray = buttonid.split(",");
		for (String array : buttonidArray) {
			if (array != "") {
				TblFlowButtonMappingEntity e = new TblFlowButtonMappingEntity();
				e.setProcessid(processid);
				e.setTaskid(taskid);
				e.setButtonid(array.trim());
				e.setId(IDUtil.getId());
				row += insert(e);
			}
		}
		
		return row;
	}
	
	public int deleteByProcessidAndTaskid(String processid, String taskid) {
		TblFlowButtonMappingEntity e = new TblFlowButtonMappingEntity();
		e.setProcessid(processid);
		e.setTaskid(taskid);
		return tblFlowButtonMappingMapper.deleteByProcessidAndTaskid(e);
	}
	
}