package com.kingtopinfo.base.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseHolidaysEntity;
import com.kingtopinfo.base.mapper.TblBaseHolidaysMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblBaseHolidaysService
 * @Description TBL_BASE_HOLIDAYS表服务类
 * @author cyf
 * @date 2018-01-12 09:14:28
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseHolidaysService {
	
	@Autowired
	private TblBaseHolidaysMapper tblBaseHolidaysMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e TblBaseHolidaysEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */
	public int getCount(TblBaseHolidaysEntity e){
		return tblBaseHolidaysMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e TblBaseHolidaysEntity实体
	 * @param rowBounds 分页实体
	 * @return TblBaseHolidaysEntity集合
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */
	public List<TblBaseHolidaysEntity> selectPagination(TblBaseHolidaysEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseHolidaysMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblBaseHolidaysEntity集合
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */	
	public List<TblBaseHolidaysEntity> select(){
		return tblBaseHolidaysMapper.select();
	}
	
	/**
	 * @Description 按holidaysid查询TblBaseHolidaysEntity信息
	 * @param holidaysid 主键holidaysid
	 * @return TblBaseHolidaysEntity实体
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */		
	public TblBaseHolidaysEntity getByPkey(String holidaysid){
		return tblBaseHolidaysMapper.getByPkey(holidaysid);
	}
	
	/**
	 * @Description 添加TblBaseHolidaysEntity信息
	 * @param e TblBaseHolidaysEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */	
	public int insert(TblBaseHolidaysEntity e){
		e.setCreateperson(UserInfoUtil.getUserName());
		e.setCreatetime(new Date());
		e.setHolidaysid(IDUtil.getId());
		return tblBaseHolidaysMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblBaseHolidaysEntity信息
	 * @param e TblBaseHolidaysEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */	
	public int update(TblBaseHolidaysEntity e){
		TblBaseHolidaysEntity tblBaseHolidaysEntity = getByPkey(e.getHolidaysid());
		tblBaseHolidaysEntity.setUpdateperson(UserInfoUtil.getUserName());
		tblBaseHolidaysEntity.setUpdatetime(new Date());
		tblBaseHolidaysEntity.setYear(e.getYear());
		tblBaseHolidaysEntity.setHolidayname(e.getHolidayname());
		tblBaseHolidaysEntity.setHolidaydate(e.getHolidaydate());
		tblBaseHolidaysEntity.setHolidaytype(e.getHolidaytype());
		tblBaseHolidaysEntity.setIsholiday(e.getIsholiday());
		tblBaseHolidaysEntity.setRemark(e.getRemark());
		return tblBaseHolidaysMapper.update(tblBaseHolidaysEntity);
	}
	
	/**
	 * @Description 按holidaysid删除TblBaseHolidaysEntity信息
	 * @param holidaysid
	 * @return 影响条数
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */	
	public int delete(String holidaysid){
		return tblBaseHolidaysMapper.delete(holidaysid);
	}
	
	/**
	 * @Description 按holidaysid集合批量删除TblBaseHolidaysEntity信息
	 * @param idArray holidaysid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}

}