package com.kingtopinfo.yjg.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.mapper.TblBaseFileMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.entity.YjgSjczEntity;
import com.kingtopinfo.yjg.mapper.YjgSjczMapper;

/**
 * @ClassName service.YjgSjczService
 * @Description YJG_SJCZ表服务类
 * @author cyf
 * @date 2017-09-12 08:50:55
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgSjczService {
	
	@Autowired
	private YjgSjczMapper		yjgSjczMapper;
	@Autowired
	private TblBaseFileMapper	tblBaseFileMapper;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            YjgSjczEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	public int getCount(YjgSjczEntity e) {
		return yjgSjczMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgSjczEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgSjczEntity集合
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	public List<YjgSjczEntity> selectPagination(YjgSjczEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjczMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 按sjczid查询YjgSjczEntity信息
	 * @param sjczid
	 *            主键sjczid
	 * @return YjgSjczEntity实体
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	public YjgSjczEntity getByPkey(String sjczid) {
		return yjgSjczMapper.getByPkey(sjczid);
	}
	
	public int insert(YjgSjczEntity e, String czsjStr, String imageIds, String vedio, String sjdjid, int type) throws ParseException {
		int rows = 0;
		if (e.getCzzt().equals("2")) {
			yjgSjczMapper.resetCzzt(sjdjid);
		}
		
		if (type == 1) {
			e.setCzrxm(e.getUsername());
			e.setSjczid(e.getSjczid());
			e.setCzr(e.getUsername());
			e.setCzsj(new Date());
		} else {
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
			e.setSjczid(uuid);
			e.setCzr(UserInfoUtil.getUserName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			e.setCzsj(sdf.parse(czsjStr));
			
		}
		e.setIsdel(1);
		rows += yjgSjczMapper.insert(e);
		return rows;
	}
	
	/**
	 * @Description 修改YjgSjczEntity信息
	 * @param e
	 *            YjgSjczEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @param czsjStr
	 * @throws ParseException
	 * @date 2017-09-12 08:50:55
	 */
	public int update(YjgSjczEntity e, String czsjStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		YjgSjczEntity yjgSjczEntity = yjgSjczMapper.getByPkey(e.getSjczid());
		yjgSjczEntity.setCzrxm(e.getCzrxm());
		yjgSjczEntity.setBz(e.getBz());
		yjgSjczEntity.setCzgcms(e.getCzgcms());
		yjgSjczEntity.setCzsj(sdf.parse(czsjStr));
		return yjgSjczMapper.update(yjgSjczEntity);
	}
	
	/**
	 * @Description 按sjczid删除YjgSjczEntity信息
	 * @param sjczid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	public int delete(String sjczid) {
		return yjgSjczMapper.delete(sjczid);
	}
	
	public int deleteByState(String sjczid) {
		return yjgSjczMapper.deleteByState(sjczid);
	}
	
	/**
	 * @Description 删除处置进度
	 * @param idArray
	 *            sjczid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	public int deleteBatch(String[] idArray, HttpServletRequest request) {
		int row = 0;
		for (String id : idArray) {
			TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
			tblBaseFileEntity.setOperaid(id);
			List<TblBaseFileEntity> list = tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
			for (TblBaseFileEntity e : list) {
				String dstPath = FilePathUtil.getFilePath("disk_Path") + FilePathUtil.getFilePath("yjg_Vedio_Path") + "/";
				tblBaseFileService.deleteFile(e.getFileid(), request, dstPath);
			}
			row += deleteByState(id);
		}
		return row;
	}
	
	public List<YjgSjczEntity> selectBySjdjId(YjgSjczEntity e) {
		return yjgSjczMapper.selectBySjdjId(e);
	}
	
	public List<YjgSjczEntity> selectByCzzt(YjgSjczEntity e) {
		return yjgSjczMapper.selectByCzzt(e);
	}
}