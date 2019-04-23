package com.kingtopinfo.activiti.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowTaskMenuMappingEntity;
import com.kingtopinfo.activiti.mapper.TblFlowTaskMenuMappingMapper;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblFlowTaskMenuMappingService
 * @Description TBL_FLOW_TASK_MENU_MAPPING表服务类
 * @author cyf
 * @date 2017-09-20 09:39:46
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblFlowTaskMenuMappingService {
	
	@Autowired
	private TblFlowTaskMenuMappingMapper tblFlowTaskMenuMappingMapper;
	/**
	 * @Description 按条件查询总条数
	 * @param e TblFlowTaskMenuMappingEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */
	public int getCount(TblFlowTaskMenuMappingEntity e){
		return tblFlowTaskMenuMappingMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblFlowTaskMenuMappingEntity实体
	 * @param rowBounds 分页实体
	 * @return TblFlowTaskMenuMappingEntity集合
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */
	public List<TblFlowTaskMenuMappingEntity> selectPagination(TblFlowTaskMenuMappingEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblFlowTaskMenuMappingMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblFlowTaskMenuMappingEntity集合
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */	
	public List<TblFlowTaskMenuMappingEntity> select(){
		return tblFlowTaskMenuMappingMapper.select();
	}
	
	/**
	 * @Description 按flowtaskmenumappingid查询TblFlowTaskMenuMappingEntity信息
	 * @param flowtaskmenumappingid 主键flowtaskmenumappingid
	 * @return TblFlowTaskMenuMappingEntity实体
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */		
	public TblFlowTaskMenuMappingEntity getByPkey(String flowtaskmenumappingid){
		return tblFlowTaskMenuMappingMapper.getByPkey(flowtaskmenumappingid);
	}
	
	/**
	 * @Description 添加TblFlowTaskMenuMappingEntity信息
	 * @param e TblFlowTaskMenuMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */	
	public int insert(TblFlowTaskMenuMappingEntity e){
		e.setFlowtaskmenumappingid(IDUtil.getId());
		return tblFlowTaskMenuMappingMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblFlowTaskMenuMappingEntity信息
	 * @param e TblFlowTaskMenuMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */	
	public int update(TblFlowTaskMenuMappingEntity e){
		return tblFlowTaskMenuMappingMapper.update(e);
	}
	
	/**
	 * @Description 按flowtaskmenumappingid删除TblFlowTaskMenuMappingEntity信息
	 * @param flowtaskmenumappingid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */	
	public int delete(String flowtaskmenumappingid){
		return tblFlowTaskMenuMappingMapper.delete(flowtaskmenumappingid);
	}
	
	/**
	 * @Description 按flowtaskmenumappingid集合批量删除TblFlowTaskMenuMappingEntity信息
	 * @param idArray flowtaskmenumappingid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
	
	/**
	 * @Description 按flowmenumappingid删除TblFlowTaskMenuMappingEntity信息
	 * @param flowmenumappingid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-15 17:04:28
	 */
	public int delete(TblFlowTaskMenuMappingEntity e) {
		return tblFlowTaskMenuMappingMapper.delete(tblFlowTaskMenuMappingMapper.selectByMenuidAndProcess(e).getFlowtaskmenumappingid());
		
	}
	
	/**
	 * Description :根据流程菜单id 删除数据
	 */
	public int deleteMenuMappingByMenuId(String menuid) {
		return tblFlowTaskMenuMappingMapper.deleteMenuMappingByMenuId(menuid);
	}
	
	/**
	 * Description :根据流程实例id和任务id
	 * 
	 * @author lxc Create at @2016年11月24日.上午10:55:41
	 */
	public List<TblFlowTaskMenuMappingEntity> selectByProcessidAndTaskid(String processid, String taskid) {
		TblFlowTaskMenuMappingEntity tFlowMenuMappingEntity = new TblFlowTaskMenuMappingEntity();
		tFlowMenuMappingEntity.setProcessid(processid);
		tFlowMenuMappingEntity.setTaskid(taskid);
		return tblFlowTaskMenuMappingMapper.selectByProcessidAndTaskid(tFlowMenuMappingEntity);
	}

}