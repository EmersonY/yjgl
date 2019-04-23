package com.kingtopinfo.sjwh.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.sjwh.entity.YjgHisjgxxEntity;
import com.kingtopinfo.sjwh.entity.YjgJgxxEntity;
import com.kingtopinfo.sjwh.mapper.YjgHisjgxxMapper;

/**
 * @ClassName service.YjgHisjgxxService
 * @Description YJG_HISJGXX表服务类
 * @author cyf
 * @date 2017-10-19 16:19:36
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgHisjgxxService {
	
	@Autowired
	private YjgHisjgxxMapper yjgHisjgxxMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            YjgHisjgxxEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public int getCount(YjgHisjgxxEntity e) {
		return yjgHisjgxxMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgHisjgxxEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgHisjgxxEntity集合
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public List<YjgHisjgxxEntity> selectPagination(YjgHisjgxxEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgHisjgxxMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgHisjgxxEntity集合
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public List<YjgHisjgxxEntity> select() {
		return yjgHisjgxxMapper.select();
	}
	
	/**
	 * @Description 按hisjgid查询YjgHisjgxxEntity信息
	 * @param hisjgid
	 *            主键hisjgid
	 * @return YjgHisjgxxEntity实体
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public YjgHisjgxxEntity getByPkey(String hisjgid) {
		return yjgHisjgxxMapper.getByPkey(hisjgid);
	}
	
	/**
	 * @Description 添加YjgHisjgxxEntity信息
	 * @param e
	 *            YjgHisjgxxEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public int insert(YjgJgxxEntity yjgJgxxEntity) {
		YjgHisjgxxEntity yjgHisjgxxEntity = new YjgHisjgxxEntity();
		yjgHisjgxxEntity.setHisczr(UserInfoUtil.getUserName());
		yjgHisjgxxEntity.setHisczsj(new Date());
		yjgHisjgxxEntity.setHisdljssj(yjgJgxxEntity.getDljssj());
		yjgHisjgxxEntity.setHisgldw(yjgJgxxEntity.getGldw());
		yjgHisjgxxEntity.setHisjgbh(yjgJgxxEntity.getJgbh());
		yjgHisjgxxEntity.setHisjgcz(yjgJgxxEntity.getJgcz());
		yjgHisjgxxEntity.setHisjggg(yjgJgxxEntity.getJggg());
		yjgHisjgxxEntity.setHisjgid(IDUtil.getId());
		yjgHisjgxxEntity.setHisjglx(yjgJgxxEntity.getJglx());
		yjgHisjgxxEntity.setHisjgxz(yjgJgxxEntity.getJgxz());
		yjgHisjgxxEntity.setHisjgzt(yjgJgxxEntity.getJgzt());
		yjgHisjgxxEntity.setHisjngj(yjgJgxxEntity.getJngj());
		yjgHisjgxxEntity.setHisqsdw(yjgJgxxEntity.getQsdw());
		yjgHisjgxxEntity.setHissfzw(yjgJgxxEntity.getSfzw());
		yjgHisjgxxEntity.setHisssdl(yjgJgxxEntity.getSsdl());
		yjgHisjgxxEntity.setHisxzb(yjgJgxxEntity.getXzb());
		yjgHisjgxxEntity.setHisxzqh(yjgJgxxEntity.getXzqh());
		yjgHisjgxxEntity.setHisyzb(yjgJgxxEntity.getYzb());
		return yjgHisjgxxMapper.insert(yjgHisjgxxEntity);
	}
	
	/**
	 * @Description 修改YjgHisjgxxEntity信息
	 * @param e
	 *            YjgHisjgxxEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public int update(YjgHisjgxxEntity e) {
		return yjgHisjgxxMapper.update(e);
	}
	
	/**
	 * @Description 按hisjgid删除YjgHisjgxxEntity信息
	 * @param hisjgid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public int delete(String hisjgid) {
		return yjgHisjgxxMapper.delete(hisjgid);
	}
	
	/**
	 * @Description 按hisjgid集合批量删除YjgHisjgxxEntity信息
	 * @param idArray
	 *            hisjgid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	public int deleteBatch(String[] idArray) {
		int row = 0;
		for (String id : idArray) {
			row += delete(id);
		}
		return row;
	}
	
}