package com.kingtopinfo.yjg.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.BlobUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.entity.YjgGglEntity;
import com.kingtopinfo.yjg.mapper.YjgGglMapper;

/**
 * @ClassName service.YjgGglService
 * @Description YJG_GGL表服务类
 * @author cyf
 * @date 2017-12-06 09:07:28
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgGglService {
	
	@Autowired
	private YjgGglMapper yjgGglMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e YjgGglEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */
	public int getCount(YjgGglEntity e){
		return yjgGglMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e YjgGglEntity实体
	 * @param rowBounds 分页实体
	 * @return YjgGglEntity集合
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */
	public List<YjgGglEntity> selectPagination(YjgGglEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		List<YjgGglEntity> gglList = yjgGglMapper.selectPagination(e,rowBounds);
		for (YjgGglEntity gl : gglList) {
			gl.setGglnr(BlobUtil.blobToString(gl.getGglnr()));
		}
		return gglList;
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgGglEntity集合
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	public List<YjgGglEntity> select(){
		List<YjgGglEntity> gglList = yjgGglMapper.select();
		for (YjgGglEntity gl : gglList) {
			gl.setGglnr(BlobUtil.blobToString(gl.getGglnr()));
		}
		return gglList;
	}
	/**
	 * @Description 查询全部信息
	 * @return YjgGglEntity集合
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	public List<YjgGglEntity> selectGg(){
		List<YjgGglEntity> gglList = yjgGglMapper.selectGg();
		return gglList;
	}
	/**
	 * @Description 按gglid查询YjgGglEntity信息
	 * @param gglid 主键gglid
	 * @return YjgGglEntity实体
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */		
	public YjgGglEntity getByPkey(String gglid){
		YjgGglEntity gglentity = yjgGglMapper.getByPkey(gglid);
		gglentity.setGglnr(BlobUtil.blobToString(gglentity.getGglnr()));				
		return gglentity;
	}
	
	 
	/**
	 * @Description 添加YjgGglEntity信息
	 * @param e YjgGglEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	public int insert(YjgGglEntity e){
		e.setCzrxm(UserInfoUtil.getUserName());
		e.setCzsj(new Date());
		e.setGglnr(BlobUtil.objToByte(e.getGglnr()));
		return yjgGglMapper.insert(e);
	}
	
	/**
	 * @Description 修改YjgGglEntity信息
	 * @param e YjgGglEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	public int update(YjgGglEntity e){
		e.setCzrxm(UserInfoUtil.getUserName());
		e.setCzsj(new Date());
		e.setGglnr(BlobUtil.objToByte(e.getGglnr()));
		return yjgGglMapper.update(e);
	}
	
	/**
	 * @Description 按gglid删除YjgGglEntity信息
	 * @param gglid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	public int delete(String gglid){
		return yjgGglMapper.delete(gglid);
	}
	
	/**
	 * @Description 按gglid集合批量删除YjgGglEntity信息
	 * @param idArray gglid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}

}