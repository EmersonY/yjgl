package com.kingtopinfo.activiti.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowTaskConfigureEntity;
import com.kingtopinfo.activiti.entity.TblFlowTaskTimeEntity;
import com.kingtopinfo.activiti.mapper.TblFlowTaskConfigureMapper;
import com.kingtopinfo.activiti.mapper.TblFlowTaskTimeMapper;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblFlowTaskConfigureService
 * @Description Tbl_FLOW_TASK_CONFIGURE表服务类
 * @author cyf
 * @date 2017-09-19 14:22:08
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblFlowTaskConfigureService {
	
	@Autowired
	private TblFlowTaskConfigureMapper	tblFlowTaskConfigureMapper;
	@Autowired
	private TblFlowTaskTimeMapper		tblFlowTaskTimeMapper;
	@Autowired
	private TblFlowButtonMappingService	tblFlowButtonMappingService;
	/**
	 * @Description 按条件查询总条数
	 * @param e TblFlowTaskConfigureEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */
	public int getCount(TblFlowTaskConfigureEntity e){
		return tblFlowTaskConfigureMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblFlowTaskConfigureEntity实体
	 * @param rowBounds 分页实体
	 * @return TblFlowTaskConfigureEntity集合
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */
	public List<TblFlowTaskConfigureEntity> selectPagination(TblFlowTaskConfigureEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblFlowTaskConfigureMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblFlowTaskConfigureEntity集合
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */	
	public List<TblFlowTaskConfigureEntity> select(){
		return tblFlowTaskConfigureMapper.select();
	}
	
	/**
	 * @Description 按id查询TblFlowTaskConfigureEntity信息
	 * @param id 主键id
	 * @return TblFlowTaskConfigureEntity实体
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */		
	public TblFlowTaskConfigureEntity getByPkey(String id){
		return tblFlowTaskConfigureMapper.getByPkey(id);
	}
	
	/**
	 * @Description 添加TblFlowTaskConfigureEntity信息
	 * @param e TblFlowTaskConfigureEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */	
	public int insert(TblFlowTaskConfigureEntity e){
		return tblFlowTaskConfigureMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblFlowTaskConfigureEntity信息
	 * @param e TblFlowTaskConfigureEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */	
	public int update(TblFlowTaskConfigureEntity e){
		return tblFlowTaskConfigureMapper.update(e);
	}
	
	/**
	 * @Description 按id删除TblFlowTaskConfigureEntity信息
	 * @param id
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */	
	public int delete(String id){
		return tblFlowTaskConfigureMapper.delete(id);
	}
	
	/**
	 * @Description 按id集合批量删除TblFlowTaskConfigureEntity信息
	 * @param idArray id集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
	
	/**
	 * @Description 按id查询TFlowTaskConfigureEntity信息
	 * @author yaozp@kingtopinfo.com
	 * @param id
	 *            主键id
	 * @param string
	 * @return TFlowTaskConfigureEntity实体
	 */
	public TblFlowTaskConfigureEntity selectByProcessidAndTaskid(String processid, String taskid) {
		TblFlowTaskConfigureEntity e = new TblFlowTaskConfigureEntity();
		e.setProcessid(processid);
		e.setTaskid(taskid);
		return tblFlowTaskConfigureMapper.selectByProcessidAndTaskid(e);
	}
	
	/**
	 * 页面保存(包含更新按钮权限)
	 */
	public int save(TblFlowTaskConfigureEntity e, TblFlowTaskTimeEntity flow) {
		int row = 0;
		e.setMaxtimes(e.getMaxtimes() * 24);
		if (e.getFlowtaskconfigureid() != null && !"".equals(e.getFlowtaskconfigureid())) {
			row = update(e);
		} else {
			e.setFlowtaskconfigureid(IDUtil.getId());
			row = insert(e);
		}
		flow.setProcessid(e.getProcessid());
		if (flow.getId() != null && !"".equals(flow.getId())) {
			tblFlowTaskTimeMapper.update(flow);
		} else {
			flow.setId(IDUtil.getId());
			tblFlowTaskTimeMapper.insert(flow);
		}
		
		// 页面按钮权限
		tblFlowButtonMappingService.save(e.getProcessid(), e.getTaskid(), e.getButtonid());
		return row;
	}

}