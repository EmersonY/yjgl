package com.kingtopinfo.sjwh.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.entity.TblBaseTypeEntity;
import com.kingtopinfo.base.mapper.TblBaseFileMapper;
import com.kingtopinfo.base.mapper.TblBaseTypeMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.service.TblBaseTypeService;
import com.kingtopinfo.base.util.CompressImgFile;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.FileUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.sjwh.entity.YjgJgxxEntity;
import com.kingtopinfo.sjwh.entity.YjgLsjgxxEntity;
import com.kingtopinfo.sjwh.mapper.YjgJgxxMapper;
import com.kingtopinfo.sjwh.mapper.YjgLsjgxxMapper;

/**
 * @ClassName service.YjgLsjgxxService
 * @Description YJG_LSJGXX表服务类
 * @author cyf
 * @date 2017-10-19 16:19:44
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgLsjgxxService {
	
	@Autowired
	private YjgLsjgxxMapper		yjgLsjgxxMapper;
	@Autowired
	private YjgJgxxService		yjgJgxxService;
	@Autowired
	private YjgHisjgxxService	yjgHisjgxxService;
	@Autowired
	private TblBaseTypeService tblBaseTypeService;
	@Autowired
	private YjgJgxxMapper		yjgJgxxMapper;
	@Autowired
	private TblBaseTypeMapper	tblBaseTypeMapper;
	@Autowired
	private TblBaseFileMapper	tblBaseFileMapper;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            YjgLsjgxxEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public int getCount(YjgLsjgxxEntity e) {
		return yjgLsjgxxMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgLsjgxxEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgLsjgxxEntity集合
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public List<YjgLsjgxxEntity> selectPagination(YjgLsjgxxEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgLsjgxxMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgLsjgxxEntity集合
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public List<YjgLsjgxxEntity> select() {
		return yjgLsjgxxMapper.select();
	}
	
	/**
	 * @Description 按lsjgid查询YjgLsjgxxEntity信息
	 * @param lsjgid
	 *            主键lsjgid
	 * @return YjgLsjgxxEntity实体
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public YjgLsjgxxEntity getByPkey(String lsjgid) {
		return yjgLsjgxxMapper.getByPkey(lsjgid);
	}
	
	/**
	 * @Description 添加YjgLsjgxxEntity信息
	 * @param e
	 *            YjgLsjgxxEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public int insert(YjgLsjgxxEntity e) {
//		e.setLsjgid(IDUtil.getId());
		e.setSbsj(new Date());
		e.setBglx("0");
		e.setShzt("0");
		e.setLsdljssj(tblBaseTypeService.getssdlDate(e.getLsssdl()));
		return yjgLsjgxxMapper.insert(e);
	}
	public List<YjgLsjgxxEntity> getSjcjList(YjgLsjgxxEntity e) {
		return yjgLsjgxxMapper.getSjcjList(e);
	}
	
	/**
	 * @Description 修改YjgLsjgxxEntity信息
	 * @param e
	 *            YjgLsjgxxEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public int update(YjgLsjgxxEntity e) {
		e.setBglx("1");
		e.setLsdljssj(tblBaseTypeService.getssdlDate(e.getLsssdl()));
		return yjgLsjgxxMapper.update(e);
	}
	
	/**
	 * @Description 按lsjgid删除YjgLsjgxxEntity信息
	 * @param lsjgid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public int delete(String lsjgid) {
		return yjgLsjgxxMapper.delete(lsjgid);
	}
	
	/**
	 * @Description 按lsjgid集合批量删除YjgLsjgxxEntity信息
	 * @param idArray
	 *            lsjgid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	public int deleteBatch(String[] idArray) {
		int row = 0;
		for (String id : idArray) {
			row += delete(id);
		}
		return row;
	}
	
	public int refuse(YjgLsjgxxEntity e) {
		YjgLsjgxxEntity yjgLsjgxxEntity = getByPkey(e.getLsjgid());
		yjgLsjgxxEntity.setShzt("2");
		yjgLsjgxxEntity.setShrxm(UserInfoUtil.getUserName());
		yjgLsjgxxEntity.setShsj(new Date());
		return update(yjgLsjgxxEntity);
	}
	
	public int pass(YjgLsjgxxEntity e, Map<String, Object> resultMap, HttpServletRequest request) throws Exception {
		String uuid = IDUtil.getId();
		YjgLsjgxxEntity yjgLsjgxxEntity = getByPkey(e.getLsjgid());
		YjgJgxxEntity yjgJgxxEntity = new YjgJgxxEntity();
		TblBaseTypeEntity tblBaseTypeEntity = new TblBaseTypeEntity();
		tblBaseTypeEntity.setName(yjgLsjgxxEntity.getLsssdl());
		tblBaseTypeEntity.setCode("SSDL");
		tblBaseTypeEntity = tblBaseTypeMapper.selectValueByCodeAndName(tblBaseTypeEntity);
		yjgJgxxEntity.setDljssj(yjgLsjgxxEntity.getLsdljssj());
		yjgJgxxEntity.setGldw(yjgLsjgxxEntity.getLsgldw());
		yjgJgxxEntity.setJgcz(yjgLsjgxxEntity.getLsjgcz());
		yjgJgxxEntity.setJggg(yjgLsjgxxEntity.getLsjggg());
		yjgJgxxEntity.setJglx(yjgLsjgxxEntity.getLsjglx());
		yjgJgxxEntity.setJgxz(yjgLsjgxxEntity.getLsjgxz());
		yjgJgxxEntity.setJgzt(yjgLsjgxxEntity.getLsjgzt());
		yjgJgxxEntity.setJngj(yjgLsjgxxEntity.getLsjngj());
		yjgJgxxEntity.setQsdw(yjgLsjgxxEntity.getLsqsdw());
		yjgJgxxEntity.setSfzw(yjgLsjgxxEntity.getLssfzw());
		yjgJgxxEntity.setSsdl(yjgLsjgxxEntity.getLsssdl());
		yjgJgxxEntity.setXzb(yjgLsjgxxEntity.getLsxzb());
		yjgJgxxEntity.setYzb(yjgLsjgxxEntity.getLsyzb());
		yjgJgxxEntity.setJgid(uuid);
		yjgJgxxEntity.setCzr(UserInfoUtil.getUserName());
		yjgJgxxEntity.setRtzt("0");
		yjgJgxxEntity.setCzsj(new Date());
		yjgJgxxEntity.setJgsl(yjgLsjgxxEntity.getLsjgsl());
		yjgJgxxEntity.setJs(yjgLsjgxxEntity.getLsjs());
		String jgbh = yjgJgxxService.createJgbh(5, yjgJgxxEntity);
		yjgJgxxEntity.setJgbh(jgbh);
		yjgJgxxMapper.insert(yjgJgxxEntity);
		yjgLsjgxxEntity.setLsjgbh(yjgJgxxEntity.getJgbh());
		yjgLsjgxxEntity.setShzt("1");
		yjgLsjgxxEntity.setShrxm(UserInfoUtil.getUserName());
		yjgLsjgxxEntity.setShsj(new Date());
		int rows = update(yjgLsjgxxEntity);
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setOperaid(yjgLsjgxxEntity.getLsjgid());
		tblBaseFileEntity.setOperatype("YJG_IMG_NBJG_FILE");
		List<TblBaseFileEntity> list = tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
		if (list.size() > 0) {
			updateImgFile(request, list.get(0), jgbh, 1, uuid);
		}
		tblBaseFileEntity.setOperatype("YJG_IMG_JJJG_FILE");
		list = tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
		if (list.size() > 0) {
			updateImgFile(request, list.get(0), jgbh, 2, uuid);
		}
		tblBaseFileEntity.setOperatype("YJG_IMG_YJJG_FILE");
		list = tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
		if (list.size() > 0) {
			updateImgFile(request, list.get(0), jgbh, 3, uuid);
		}
		resultMap.put("sec", true);
		resultMap.put("rows", rows);
		return rows;
	}
	
	public void updateImgFile(HttpServletRequest request, TblBaseFileEntity e2, String jgbh, int type, String uuid) throws Exception {
		String sjwhImgPath = FilePathUtil.getFilePath("sjwh_Img_Path");
		String sjcjImgPath = FilePathUtil.getFilePath("sjcj_Img_Path");
		String dstPath = FilePathUtil.getFilePath("disk_Path") + sjwhImgPath + "/";
		String sjczDstPath = FilePathUtil.getFilePath("disk_Path") + sjcjImgPath + "/";
		File file = new File(sjczDstPath + e2.getFilename());
		String filename = "";
		if (type == 1) {
			filename = jgbh + "-NB.jpg";
		} else if (type == 2) {
			filename = jgbh + "-JJ.jpg";
		} else if (type == 3) {
			filename = jgbh + "-YJ.jpg";
		}
		
		String filePath = sjwhImgPath + "/" + filename;
		File dstFile = new File(dstPath + filename);
		if (dstFile.exists()) {
			dstFile.delete();
			dstFile = new File(dstPath + filename);
		}
		FileUtil.copy(file, dstFile);
		CompressImgFile.reduceImg(dstFile, request);
		e2.setFilename(filename);
		e2.setFilepath(filePath);
		e2.setOperaid(uuid);
		tblBaseFileService.insertJgFileEntity(e2);
	}
	
	public Map<String, Object> findjgImg(String operaid, Map<String, Object> resultMap, HttpServletRequest request) {
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setOperaid(operaid);
		tblBaseFileEntity.setOperatype("YJG_IMG_NBJG_FILE");
		List<TblBaseFileEntity> list = tblBaseFileMapper.selectByOperatypeAndOperaid(tblBaseFileEntity);
		if (list.size() > 0 && list.get(0).getFilename() != null) {
			resultMap.put("nbPathName", FilePathUtil.getFileRealpath(list.get(0).getFilepath(), request));
		}
		tblBaseFileEntity.setOperatype("YJG_IMG_JJJG_FILE");
		list = tblBaseFileMapper.selectByOperatypeAndOperaid(tblBaseFileEntity);
		if (list.size() > 0 && list.get(0).getFilename() != null) {
			resultMap.put("jjPathName", FilePathUtil.getFileRealpath(list.get(0).getFilepath(), request));
		}
		tblBaseFileEntity.setOperatype("YJG_IMG_YJJG_FILE");
		list = tblBaseFileMapper.selectByOperatypeAndOperaid(tblBaseFileEntity);
		if (list.size() > 0 && list.get(0).getFilename() != null) {
			resultMap.put("yjPathName", FilePathUtil.getFileRealpath(list.get(0).getFilepath(), request));
		}
		return resultMap;
	}
}