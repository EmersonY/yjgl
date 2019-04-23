package com.kingtopinfo.yjg.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.entity.YjgXmlcEntity;
import com.kingtopinfo.yjg.mapper.YjgXmlcMapper;

/**
 * @ClassName service.YjgXmlcService
 * @Description YJG_XMLC表服务类
 * @author cyf
 * @date 2017-09-21 10:51:48
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgXmlcService {
	
	@Autowired
	private YjgXmlcMapper yjgXmlcMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e YjgXmlcEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-21 10:51:48
	 */
	public int getCount(YjgXmlcEntity e){
		return yjgXmlcMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e YjgXmlcEntity实体
	 * @param rowBounds 分页实体
	 * @return YjgXmlcEntity集合
	 * @author cyf
	 * @date 2017-09-21 10:51:48
	 */
	public List<YjgXmlcEntity> selectPagination(YjgXmlcEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgXmlcMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgXmlcEntity集合
	 * @author cyf
	 * @date 2017-09-21 10:51:48
	 */	
	public List<YjgXmlcEntity> select(){
		return yjgXmlcMapper.select();
	}
	
	/**
	 * @Description 按xmlcid查询YjgXmlcEntity信息
	 * @param xmlcid 主键xmlcid
	 * @return YjgXmlcEntity实体
	 * @author cyf
	 * @date 2017-09-21 10:51:48
	 */		
	public YjgXmlcEntity getByPkey(String xmlcid){
		return yjgXmlcMapper.getByPkey(xmlcid);
	}
	
	/**
	 * @Description 添加YjgXmlcEntity信息
	 * @param e
	 *            YjgXmlcEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @param instanceid
	 * @date 2017-09-21 10:51:48
	 */	
	public int insert(YjgXmlcEntity e, String instanceid, String sjdjid,String username) {
		e.setCjr(username);
		e.setCjsj(new Date());
		e.setInstanceid(instanceid);
		e.setState(1);
		e.setSjdjid(sjdjid);
		e.setXmlcid(IDUtil.getId());
		return yjgXmlcMapper.insert(e);
	}
	
	/**
	 * @Description 修改YjgXmlcEntity信息
	 * @param e YjgXmlcEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-21 10:51:48
	 */	
	public int update(YjgXmlcEntity e){
		return yjgXmlcMapper.update(e);
	}
	
	/**
	 * @Description 按xmlcid删除YjgXmlcEntity信息
	 * @param xmlcid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-21 10:51:48
	 */	
	public int delete(String xmlcid){
		return yjgXmlcMapper.delete(xmlcid);
	}
	
	/**
	 * @Description 按xmlcid集合批量删除YjgXmlcEntity信息
	 * @param idArray xmlcid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-21 10:51:48
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 利用案件ID查询实例ID
	 * @author cyf
	 * @date 2017年10月9日 下午3:23:03
	 */
	public YjgXmlcEntity selectInstanceBySjdjid(String sjdjid) {
		return yjgXmlcMapper.selectInstanceBySjdjid(sjdjid);
	}

}