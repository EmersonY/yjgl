package com.kingtopinfo.yjg.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.entity.TblBaseUserExtEntity;
import com.kingtopinfo.base.mapper.TblBaseFileMapper;
import com.kingtopinfo.base.mapper.TblBaseUserExtMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.entity.YjgFyjsjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.mapper.YjgFyjsjdjMapper;

/**
 * @ClassName service.YjgFyjsjdjService
 * @Description YJG_FYJSJDJ表服务类
 * @author cyf
 * @date 2017-08-25 11:31:28
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgFyjsjdjService {
	
	@Autowired
	private YjgFyjsjdjMapper yjgFyjsjdjMapper;
	@Autowired
	private YjgSjdjService			yjgSjdjService;
	@Autowired
	private TblBaseUserExtMapper	tblBaseUserExtMapper;
	@Autowired
	private TblBaseFileMapper		tblBaseFileMapper;
	@Autowired
	private TblBaseFileService		tblBaseFileService;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e YjgFyjsjdjEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-08-25 11:31:28
	 */
	public int getCount(YjgFyjsjdjEntity e){
		return yjgFyjsjdjMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e YjgFyjsjdjEntity实体
	 * @param rowBounds 分页实体
	 * @return YjgFyjsjdjEntity集合
	 * @author cyf
	 * @date 2017-08-25 11:31:28
	 */
	public List<YjgFyjsjdjEntity> selectPagination(YjgFyjsjdjEntity e, Integer rows, Integer page){
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgFyjsjdjMapper.selectPagination(e,rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgFyjsjdjEntity集合
	 * @author cyf
	 * @date 2017-08-25 11:31:28
	 */	
	public List<YjgFyjsjdjEntity> select(){
		return yjgFyjsjdjMapper.select();
	}
	
	/**
	 * @Description 按fyjsjdjid查询YjgFyjsjdjEntity信息
	 * @param fyjsjdjid 主键fyjsjdjid
	 * @return YjgFyjsjdjEntity实体
	 * @author cyf
	 * @date 2017-08-25 11:31:28
	 */		
	public YjgFyjsjdjEntity getByPkey(String fyjsjdjid){
		return yjgFyjsjdjMapper.getByPkey(fyjsjdjid);
	}
	
	/**
	 * @Description 添加YjgFyjsjdjEntity信息
	 * @param e
	 *            YjgFyjsjdjEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @param imageIds
	 * @param request
	 * @param vedio
	 * @throws IOException
	 * @date 2017-08-25 11:31:28
	 */	
	public int insert(YjgFyjsjdjEntity e, String imageIds, HttpServletRequest request, String vedio) throws IOException {
		String uuid = IDUtil.getId();
		if (!imageIds.equals("")) {
			imageIds = imageIds.substring(0, imageIds.length() - 1);
			String[] split = imageIds.split(",");
			for (String id : split) {
				TblBaseFileEntity tblBaseFileEntity = tblBaseFileMapper.getByPkey(id);
				tblBaseFileEntity.setOperaid(uuid);
				tblBaseFileMapper.update(tblBaseFileEntity);
			}
		}
		if (!vedio.equals("")) {
			TblBaseFileEntity tblBaseFileEntity = tblBaseFileMapper.getByPkey(vedio);
			tblBaseFileEntity.setOperaid(uuid);
			tblBaseFileMapper.update(tblBaseFileEntity);
		}
		TblBaseUserExtEntity tblBaseUserExtEntity = tblBaseUserExtMapper.getByUserId(UserInfoUtil.getBaseuserid());
		e.setFyjsjdjid(uuid);
		e.setSqzt(7);
		e.setScsj(new Date());
		e.setBaseuserid(UserInfoUtil.getBaseuserid());
		e.setSbrxm(UserInfoUtil.getUserName());
		if (tblBaseUserExtEntity != null) {
			e.setSbrdh(tblBaseUserExtEntity.getTel());
		}
		e.setIsdel(1);
		e.setJssj(new Date());
		String sjdjdh = yjgSjdjService.hqSjdjdh(4, uuid, e.getSjlx(), e.getXzqh());
		e.setSjdjdh(sjdjdh);
		return yjgFyjsjdjMapper.insert(e);
	}
	
	/**
	 * @Description 修改YjgFyjsjdjEntity信息
	 * @param e YjgFyjsjdjEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-25 11:31:28
	 */	
	public int update(YjgFyjsjdjEntity e){
		YjgFyjsjdjEntity yjgFyjsjdjEntity = yjgFyjsjdjMapper.getByPkey(e.getFyjsjdjid());
		yjgFyjsjdjEntity.setSbrxm(e.getSbrxm());
		yjgFyjsjdjEntity.setSbrdh(e.getSbrdh());
		yjgFyjsjdjEntity.setXxly(e.getXxly());
		yjgFyjsjdjEntity.setWzms(e.getWzms());
		yjgFyjsjdjEntity.setBz(e.getBz());
		yjgFyjsjdjEntity.setSjlx(e.getSjlx());
		yjgFyjsjdjEntity.setUpdator(UserInfoUtil.getUserName());
		yjgFyjsjdjEntity.setUpdatetime(new Date());
		yjgFyjsjdjEntity.setUpdateuserid(UserInfoUtil.getBaseuserid());
		yjgFyjsjdjEntity.setXzb(e.getXzb());
		yjgFyjsjdjEntity.setYzb(e.getYzb());
		yjgFyjsjdjEntity.setXzqh(e.getXzqh());
		yjgFyjsjdjEntity.setCkqx(e.getCkqx());
		// yjgFyjsjdjEntity.setSsdl(e.getSsdl());
		String xzqh = yjgSjdjService.findSzqhDhSx(e.getXzqh());
		String substring = e.getSjdjdh().substring(3, e.getSjdjdh().length());
		yjgFyjsjdjEntity.setSjdjdh(xzqh + substring);
		return yjgFyjsjdjMapper.update(yjgFyjsjdjEntity);
	}
	
	/**
	 * @Description 按fyjsjdjid删除YjgFyjsjdjEntity信息
	 * @param fyjsjdjid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-25 11:31:28
	 */	
	public int delete(String fyjsjdjid){
		return yjgFyjsjdjMapper.delete(fyjsjdjid);
	}
	
	/**
	 * @Description 按fyjsjdjid集合批量删除YjgFyjsjdjEntity信息
	 * @param idArray fyjsjdjid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-25 11:31:28
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
	 * @Description: 合并事件
	 * @author cyf
	 * @date 2017年8月29日 下午2:28:25
	 */
	public int merge(List<YjgSjdjEntity> list, String yjgSjdjId) {
		int rows = 0;
		for (YjgSjdjEntity yjgSjdjEntity : list) {
			String sjdjid = yjgSjdjEntity.getFyjsjdjid();
			if (!yjgSjdjId.equals(sjdjid)) {
				YjgFyjsjdjEntity e = yjgFyjsjdjMapper.getByPkey(sjdjid);
				e.setCssjdjpid(yjgSjdjId);
				List<YjgSjdjEntity> listChildSjdj = yjgFyjsjdjMapper.listChildSjdj(e.getFyjsjdjid());
				if (listChildSjdj.size() > 0) {
					merge(listChildSjdj, yjgSjdjId);
				}
				rows += yjgFyjsjdjMapper.update(e);
			}
		}
		return rows;
	}
	
	/**
	 * @Description 查询从属子事件
	 * @return YjgSjdjEntity
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public List<YjgSjdjEntity> listChildSjdj(YjgSjdjEntity e) {
		return yjgFyjsjdjMapper.listChildSjdj(e.getCssjdjpid());
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description:批量脱离
	 * @author cyf
	 * @date 2017年8月30日 下午5:55:15
	 */
	public int separate(String[] idArray) {
		int row = 0;
		for (String id : idArray) {
			row += yjgFyjsjdjMapper.separate(id);
		}
		return row;
	}
	
}