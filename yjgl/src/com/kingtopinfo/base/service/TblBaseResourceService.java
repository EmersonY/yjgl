package com.kingtopinfo.base.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseResourceEntity;
import com.kingtopinfo.base.mapper.TblBaseResourceMapper;
import com.kingtopinfo.base.mapper.TblBaseRoleResourceMappingMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblBaseResourceService
 * @Description TBL_BASE_RESOURCE表服务类
 * @author cyf
 * @date 2017-07-27 14:45:09
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseResourceService {
	
	@Autowired
	private TblBaseResourceMapper tblBaseResourceMapper;
	@Autowired
	private TblBaseRoleResourceMappingMapper	tblBaseRoleResourceMappingMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblBaseResourceEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */
	public int getCount(TblBaseResourceEntity e){
		return tblBaseResourceMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblBaseResourceEntity实体
	 * @param rowBounds 分页实体
	 * @return TblBaseResourceEntity集合
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */
	public List<TblBaseResourceEntity> selectPagination(TblBaseResourceEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseResourceMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblBaseResourceEntity集合
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	public List<TblBaseResourceEntity> select(){
		return tblBaseResourceMapper.select();
	}
	
	/**
	 * @Description 按baseresourceid查询TblBaseResourceEntity信息
	 * @param baseresourceid 主键baseresourceid
	 * @return TblBaseResourceEntity实体
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */		
	public TblBaseResourceEntity getByPkey(String baseresourceid){
		return tblBaseResourceMapper.getByPkey(baseresourceid);
	}
	
	/**
	 * @Description 添加TblBaseResourceEntity信息
	 * @param e TblBaseResourceEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	public int insert(TblBaseResourceEntity e){
		e.setBaseresourceid(IDUtil.getId());
		e.setUpdater(UserInfoUtil.getUserName());
		return tblBaseResourceMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblBaseResourceEntity信息
	 * @param e TblBaseResourceEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	public int update(TblBaseResourceEntity e){
		TblBaseResourceEntity tblBaseResourceEntity = getByPkey(e.getBaseresourceid());
		tblBaseResourceEntity.setUpdater(UserInfoUtil.getUserName());
		tblBaseResourceEntity.setName(e.getName());
		tblBaseResourceEntity.setStatus(e.getStatus());
		tblBaseResourceEntity.setRemark(e.getRemark());
		tblBaseResourceEntity.setMenuname(e.getMenuname());
		tblBaseResourceEntity.setBasemenuid(e.getBasemenuid());
		return tblBaseResourceMapper.update(tblBaseResourceEntity);
	}
	
	/**
	 * @Description 按baseresourceid删除TblBaseResourceEntity信息
	 * @param baseresourceid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	public int delete(String baseresourceid){
		return tblBaseResourceMapper.delete(baseresourceid);
	}
	
	/**
	 * @Description 按baseresourceid集合批量删除TblBaseResourceEntity信息
	 * @param idArray baseresourceid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			tblBaseRoleResourceMappingMapper.deleteByResorceId(id);
			row += delete(id);
		}
		return row;
	}

}