package com.kingtopinfo.activiti.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblMkMenuMappingEntity;
import com.kingtopinfo.activiti.mapper.TblMkMenuMappingMapper;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblMkMenuMappingService
 * @Description TBL_MK_MENU_MAPPING表服务类
 * @author cyf
 * @date 2017-09-18 10:07:05
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblMkMenuMappingService {
	
	@Autowired
	private TblMkMenuMappingMapper tblMkMenuMappingMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblMkMenuMappingEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */
	public int getCount(TblMkMenuMappingEntity e){
		return tblMkMenuMappingMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblMkMenuMappingEntity实体
	 * @param rowBounds 分页实体
	 * @return TblMkMenuMappingEntity集合
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */
	public List<TblMkMenuMappingEntity> selectPagination(TblMkMenuMappingEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblMkMenuMappingMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblMkMenuMappingEntity集合
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */	
	public List<TblMkMenuMappingEntity> select(){
		return tblMkMenuMappingMapper.select();
	}
	
	/**
	 * @Description 按id查询TblMkMenuMappingEntity信息
	 * @param id 主键id
	 * @return TblMkMenuMappingEntity实体
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */		
	public TblMkMenuMappingEntity getByPkey(String id){
		return tblMkMenuMappingMapper.getByPkey(id);
	}
	
	/**
	 * @Description 添加TblMkMenuMappingEntity信息
	 * @param e TblMkMenuMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */	
	public int insert(TblMkMenuMappingEntity e){
		return tblMkMenuMappingMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblMkMenuMappingEntity信息
	 * @param e TblMkMenuMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */	
	public int update(TblMkMenuMappingEntity e){
		return tblMkMenuMappingMapper.update(e);
	}
	
	/**
	 * @Description 按id删除TblMkMenuMappingEntity信息
	 * @param id
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */	
	public int delete(String id){
		return tblMkMenuMappingMapper.delete(id);
	}
	
	/**
	 * @Description 按id集合批量删除TblMkMenuMappingEntity信息
	 * @param idArray id集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 10:07:05
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
	
	public int deleteByMenuid(String menuid) {
		return tblMkMenuMappingMapper.deleteByMenuid(menuid);
	}

}