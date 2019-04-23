package com.kingtopinfo.activiti.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowTaskTimeEntity;
import com.kingtopinfo.activiti.mapper.TblFlowTaskTimeMapper;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblFlowTaskTimeService
 * @Description TBL_FLOW_TASK_TIME表服务类
 * @author cyf
 * @date 2017-09-19 14:51:08
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblFlowTaskTimeService {
	
	@Autowired
	private TblFlowTaskTimeMapper tblFlowTaskTimeMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblFlowTaskTimeEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */
	public int getCount(TblFlowTaskTimeEntity e){
		return tblFlowTaskTimeMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblFlowTaskTimeEntity实体
	 * @param rowBounds 分页实体
	 * @return TblFlowTaskTimeEntity集合
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */
	public List<TblFlowTaskTimeEntity> selectPagination(TblFlowTaskTimeEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblFlowTaskTimeMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblFlowTaskTimeEntity集合
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */	
	public List<TblFlowTaskTimeEntity> select(){
		return tblFlowTaskTimeMapper.select();
	}
	
	/**
	 * @Description 按id查询TblFlowTaskTimeEntity信息
	 * @param id 主键id
	 * @return TblFlowTaskTimeEntity实体
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */		
	public TblFlowTaskTimeEntity getByPkey(String id){
		return tblFlowTaskTimeMapper.getByPkey(id);
	}
	
	/**
	 * @Description 添加TblFlowTaskTimeEntity信息
	 * @param e TblFlowTaskTimeEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */	
	public int insert(TblFlowTaskTimeEntity e){
		return tblFlowTaskTimeMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblFlowTaskTimeEntity信息
	 * @param e TblFlowTaskTimeEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */	
	public int update(TblFlowTaskTimeEntity e){
		return tblFlowTaskTimeMapper.update(e);
	}
	
	/**
	 * @Description 按id删除TblFlowTaskTimeEntity信息
	 * @param id
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */	
	public int delete(String id){
		return tblFlowTaskTimeMapper.delete(id);
	}
	
	/**
	 * @Description 按id集合批量删除TblFlowTaskTimeEntity信息
	 * @param idArray id集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}

	public TblFlowTaskTimeEntity selectByProcessid(TblFlowTaskTimeEntity e) {
		return tblFlowTaskTimeMapper.selectByProcessid(e.getProcessid());
	}
	
}