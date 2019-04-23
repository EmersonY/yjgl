package com.kingtopinfo.activiti.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblMkMenuEntity;
import com.kingtopinfo.activiti.mapper.TblMkMenuMapper;
import com.kingtopinfo.base.entity.TblBaseMenuEntity;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblMkMenuService
 * @Description TBL_MK_MENU表服务类
 * @author cyf
 * @date 2017-09-18 09:25:29
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblMkMenuService {
	
	@Autowired
	private TblMkMenuMapper tblMkMenuMapper;
	@Autowired
	private TblMkMenuMappingService	tblMkMenuMappingService;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblMkMenuEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */
	public int getCount(TblMkMenuEntity e){
		return tblMkMenuMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblMkMenuEntity实体
	 * @param rowBounds 分页实体
	 * @return TblMkMenuEntity集合
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */
	public List<TblMkMenuEntity> selectPagination(TblMkMenuEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblMkMenuMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblMkMenuEntity集合
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */	
	public List<TblMkMenuEntity> select(){
		return tblMkMenuMapper.select();
	}
	
	/**
	 * @Description 按id查询TblMkMenuEntity信息
	 * @param id 主键id
	 * @return TblMkMenuEntity实体
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */		
	public TblMkMenuEntity getByPkey(String id){
		return tblMkMenuMapper.getByPkey(id);
	}
	
	/**
	 * @Description 添加TblMkMenuEntity信息
	 * @param e TblMkMenuEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */	
	public int insert(TblMkMenuEntity e){
		e.setId(IDUtil.getId());
		e.setType("MENU");
		e.setUpdatetime(new Date());
		int maxSequ = tblMkMenuMapper.selectMaxSequ();
		e.setSequ(maxSequ + 1);
		return tblMkMenuMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblMkMenuEntity信息
	 * @param e TblMkMenuEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */	
	public int update(TblMkMenuEntity e){
		TblMkMenuEntity tblMkMenuEntity = getByPkey(e.getId());
		tblMkMenuEntity.setName(e.getName());
		tblMkMenuEntity.setUrl(e.getUrl());
		tblMkMenuEntity.setState(e.getState());
		return tblMkMenuMapper.update(tblMkMenuEntity);
	}
	
	/**
	 * @Description 按id删除TblMkMenuEntity信息
	 * @param id
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */	
	public int delete(String id){
		int rows = 0;
		List<TblBaseMenuEntity> list = tblMkMenuMapper.selectByPid(id);
		if (list != null && !list.isEmpty())
			for (TblBaseMenuEntity each : list)
				delete(each.getId());
		rows += tblMkMenuMapper.delete(id);
		tblMkMenuMappingService.deleteByMenuid(id);
		return rows;
	}
	
	/**
	 * @Description 按id集合批量删除TblMkMenuEntity信息
	 * @param idArray id集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}

}