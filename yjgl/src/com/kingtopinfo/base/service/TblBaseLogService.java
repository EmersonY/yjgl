package com.kingtopinfo.base.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseLogEntity;
import com.kingtopinfo.base.mapper.TblBaseLogMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName com.kingtopinfo.base.service.TblBaseLogService
 * @Description TBL_BASE_LOG表服务类
 * @author cyf
 * @date 2017-03-08 16:57:11
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseLogService {
	
	@Autowired
	private TblBaseLogMapper tblBaseLogMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param TblBaseLogEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	public int selectCount(TblBaseLogEntity e) {
		return tblBaseLogMapper.selectCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param TblBaseLogEntity实体
	 * @param rowBounds
	 * @return TblBaseLogEntity集合
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	public List<TblBaseLogEntity> selectPagination(TblBaseLogEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseLogMapper.selectPagination(e, rowBounds);
	}
	
	
	/**
	 * @Description 添加TblBaseLogEntity信息
	 * @param TblBaseLogEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */	
	public int insertLog(String module, String content, String result) {
		int row = 0;
		TblBaseLogEntity log = new TblBaseLogEntity();
		try {
			log.setLogid(IDUtil.getId());
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datetime = tempDate.format(new java.util.Date());
			log.setLogtime(datetime);
			log.setModule(module);
			log.setResult(result);
			log.setContent(content);	
			log.setUserid(UserInfoUtil.getBaseuserid());
			log.setUsername(UserInfoUtil.getUserName());
			row = tblBaseLogMapper.insert(log);
		} catch (Exception ex) {
			log.setResult("操作失败");
			tblBaseLogMapper.insert(log);
			return 0;
		}
		return row;
	}

}