package com.kingtopinfo.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.app.entity.YjgAppversionEntity;
import com.kingtopinfo.app.mapper.YjgAppversionMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.YjgAppversionService
 * @Description YJG_APPVERSION表服务类
 * @author cyf
 * @date 2018-01-02 09:51:19
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgAppversionService {
	
	@Autowired
	private YjgAppversionMapper yjgAppversionMapper;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e YjgAppversionEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */
	public int getCount(YjgAppversionEntity e){
		return yjgAppversionMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e YjgAppversionEntity实体
	 * @param rowBounds 分页实体
	 * @return YjgAppversionEntity集合
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */
	public List<YjgAppversionEntity> selectPagination(YjgAppversionEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgAppversionMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgAppversionEntity集合
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */	
	public List<YjgAppversionEntity> select(){
		return yjgAppversionMapper.select();
	}
	
	/**
	 * @Description 按id查询YjgAppversionEntity信息
	 * @param id 主键id
	 * @return YjgAppversionEntity实体
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */		
	public YjgAppversionEntity getByPkey(String id){
		return yjgAppversionMapper.getByPkey(id);
	}
	
	/**
	 * @Description 添加YjgAppversionEntity信息
	 * @param e
	 *            YjgAppversionEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @throws IOException
	 * @date 2018-01-02 09:51:19
	 */	
	public int insert(HttpServletRequest request, YjgAppversionEntity e) throws IOException {
		YjgAppversionEntity yjgAppversionEntity = new YjgAppversionEntity();
		yjgAppversionEntity.setId(IDUtil.getId());
		yjgAppversionEntity.setSfqzgx(e.getSfqzgx());
		yjgAppversionEntity.setUpdateperson(UserInfoUtil.getUserName());
		yjgAppversionEntity.setUpdatetime(new Date());
		yjgAppversionEntity.setVersioncode(yjgAppversionMapper.selectMaxVersioncode() + 1);
		yjgAppversionEntity.setVersionname(e.getVersionname());
		if (e.getAppfile() != null) {
			tblBaseFileService.saveApp(request, yjgAppversionEntity, e.getAppfile());
		}
		return yjgAppversionMapper.insert(yjgAppversionEntity);
	}
	
	/**
	 * @Description 修改YjgAppversionEntity信息
	 * @param e YjgAppversionEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */	
	public int update(YjgAppversionEntity e){
		return yjgAppversionMapper.update(e);
	}
	
	/**
	 * @Description 按id删除YjgAppversionEntity信息
	 * @param id
	 * @return 影响条数
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */	
	public int delete(String id){
		return yjgAppversionMapper.delete(id);
	}
	
	/**
	 * @Description 按id集合批量删除YjgAppversionEntity信息
	 * @param idArray id集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}

}