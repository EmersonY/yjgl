package com.kingtopinfo.base.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseRoleResourceMappingEntity;
import com.kingtopinfo.base.mapper.TblBaseRoleResourceMappingMapper;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblBaseRoleResourceMappingService
 * @Description TBL_BASE_ROLE_RESOURCE_MAPPING表服务类
 * @author cyf
 * @date 2017-07-28 10:39:35
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseRoleResourceMappingService {
	
	@Autowired
	private TblBaseRoleResourceMappingMapper tblBaseRoleResourceMappingMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblBaseRoleResourceMappingEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */
	public int getCount(TblBaseRoleResourceMappingEntity e){
		return tblBaseRoleResourceMappingMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblBaseRoleResourceMappingEntity实体
	 * @param rowBounds 分页实体
	 * @return TblBaseRoleResourceMappingEntity集合
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */
	public List<TblBaseRoleResourceMappingEntity> selectPagination(TblBaseRoleResourceMappingEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseRoleResourceMappingMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblBaseRoleResourceMappingEntity集合
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */	
	public List<TblBaseRoleResourceMappingEntity> select(){
		return tblBaseRoleResourceMappingMapper.select();
	}
	
	/**
	 * @Description 利用ResourseId查询关联
	 * @return TblBaseRoleResourceMappingEntity集合
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */
	public List<TblBaseRoleResourceMappingEntity> selectByResourceId(String baseresourceid) {
		return tblBaseRoleResourceMappingMapper.selectByResourceId(baseresourceid);
	}
	
	/**
	 * @Description 利用roleid查询关联
	 * @return TblBaseRoleResourceMappingEntity集合
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */
	public List<TblBaseRoleResourceMappingEntity> selectByRoleId(String baseroleid) {
		return tblBaseRoleResourceMappingMapper.selectByRoleId(baseroleid);
	}
	
	/**
	 * @Description 按baseroleresuorceid查询TblBaseRoleResourceMappingEntity信息
	 * @param baseroleresuorceid
	 *            主键baseroleresuorceid
	 * @return TblBaseRoleResourceMappingEntity实体
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */		
	public TblBaseRoleResourceMappingEntity getByPkey(String baseroleresuorceid){
		return tblBaseRoleResourceMappingMapper.getByPkey(baseroleresuorceid);
	}
	
	/**
	 * @Description 添加TblBaseRoleResourceMappingEntity信息
	 * @param e TblBaseRoleResourceMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */	
	public int insert(String[] idArray, String baseresourceid) {
		int row = 0;
		row += tblBaseRoleResourceMappingMapper.deleteByResorceId(baseresourceid);
		for (String id : idArray) {
			TblBaseRoleResourceMappingEntity e = new TblBaseRoleResourceMappingEntity();
			e.setBaseroleresourceid(IDUtil.getId());
			e.setBaseroleid(id);
			e.setBaseresourceid(baseresourceid);
			row += tblBaseRoleResourceMappingMapper.insert(e);
		}
		return row;
	}
	
	/**
	 * @Description 修改TblBaseRoleResourceMappingEntity信息
	 * @param e TblBaseRoleResourceMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */	
	public int update(TblBaseRoleResourceMappingEntity e){
		return tblBaseRoleResourceMappingMapper.update(e);
	}
	
	/**
	 * @Description 按baseroleresuorceid删除TblBaseRoleResourceMappingEntity信息
	 * @param baseroleresuorceid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */	
	public int delete(String baseroleresuorceid){
		return tblBaseRoleResourceMappingMapper.delete(baseroleresuorceid);
	}
	
	/**
	 * @Description 按baseroleresuorceid集合批量删除TblBaseRoleResourceMappingEntity信息
	 * @param idArray baseroleresuorceid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}

	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: TODO
	* @author cyf    
	* @date 2018年1月30日 下午3:37:00
	 */
	public int insertByRole(String[] idArray, String baseroleid) {
		int row = 0;
		row += tblBaseRoleResourceMappingMapper.deleteByRoleid(baseroleid);
		for (String id : idArray) {
			TblBaseRoleResourceMappingEntity e = new TblBaseRoleResourceMappingEntity();
			e.setBaseroleresourceid(IDUtil.getId());
			e.setBaseroleid(baseroleid);
			e.setBaseresourceid(id);
			row += tblBaseRoleResourceMappingMapper.insert(e);
		}
		return row;
	}
	
}