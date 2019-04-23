package com.kingtopinfo.base.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kingtopinfo.app.entity.YjgAppversionEntity;
import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.mapper.TblBaseFileMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.CompressImgFile;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.FileUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.sjwh.entity.YjgJgxxEntity;
import com.kingtopinfo.video.VideoThumbTaker;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;

/**
 * @ClassName service.TblBaseFileService
 * @Description TBL_BASE_FILE表服务类
 * @author cyf
 * @date 2017-08-18 14:04:49
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseFileService {
	
	@Autowired
	private TblBaseFileMapper tblBaseFileMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            TblBaseFileEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public int getCount(TblBaseFileEntity e) {
		return tblBaseFileMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            TblBaseFileEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return TblBaseFileEntity集合
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public List<TblBaseFileEntity> selectPagination(TblBaseFileEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseFileMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return TblBaseFileEntity集合
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public List<TblBaseFileEntity> select() {
		return tblBaseFileMapper.select();
	}
	
	/**
	 * @Description 按fileid查询TblBaseFileEntity信息
	 * @param fileid
	 *            主键fileid
	 * @return TblBaseFileEntity实体
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public TblBaseFileEntity getByPkey(String fileid) {
		return tblBaseFileMapper.getByPkey(fileid);
	}
	
	/**
	 * @Description 添加TblBaseFileEntity信息
	 * @param e
	 *            TblBaseFileEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public int insert(TblBaseFileEntity e) {
		return tblBaseFileMapper.insert(e);
	}
	
	/**
	 * @Description 添加TblBaseFileEntity信息
	 * @param e
	 *            TblBaseFileEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public int insertJgFileEntity(TblBaseFileEntity e2) {
		TblBaseFileEntity e = new TblBaseFileEntity();
		e.setCjr(UserInfoUtil.getUserName());
		e.setExtend(e2.getExtend());
		e.setFileid(IDUtil.getId());
		e.setFilesize(e2.getFilesize());
		e.setGxsj(new Date());
		e.setOperatype(e2.getOperatype());
		e.setFilename(e2.getFilename());
		e.setFilepath(e2.getFilepath());
		e.setOperaid(e2.getOperaid());
		return tblBaseFileMapper.insert(e);
	}
	
	/**
	 * @Description 修改TblBaseFileEntity信息
	 * @param e
	 *            TblBaseFileEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public int update(TblBaseFileEntity e) {
		return tblBaseFileMapper.update(e);
	}
	
	/**
	 * @Description 按fileid删除TblBaseFileEntity信息
	 * @param fileid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public int delete(String fileid) {
		return tblBaseFileMapper.delete(fileid);
	}
	
	/**
	 * @Description 按fileid集合批量删除TblBaseFileEntity信息
	 * @param idArray
	 *            fileid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-18 14:04:49
	 */
	public int deleteBatch(String[] idArray) {
		int row = 0;
		for (String id : idArray) {
			row += delete(id);
		}
		return row;
	}
	
	public List<TblBaseFileEntity> selectByOperatypeAndOperaid(String operatype, String operaid) {
		TblBaseFileEntity e = new TblBaseFileEntity();
		e.setOperatype(operatype);
		e.setOperaid(operaid);
		return tblBaseFileMapper.selectByOperatypeAndOperaid(e);
	}
	
	public void addImg(MultipartFile multipartFile, HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity, YjgSjdjEntity yjgSjdjEntity, Map<String, Object> resultMap,
			String operatype) throws Exception {
		String extendName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String id = IDUtil.getId();
		String yjgImgRelativePath = FilePathUtil.getFilePath("yjg_Img_Path");
		String dstPath = FilePathUtil.getFilePath("disk_Path") + yjgImgRelativePath;
		// 创建文件存放路径
		FileUtil.creatPath(dstPath);
		// 创建目标文件对象
		File dstFile = new File(dstPath + "/" + id + extendName);
		
		// 文件已存在（上传了同名的文件）
		if (dstFile.exists()) {
			dstFile.delete();
			dstFile = new File(dstPath);
		}
		CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
		File file = fi.getStoreLocation();
		FileUtil.copy(file, dstFile);
		CompressImgFile.reduceImg(dstFile, request);
		TblBaseFileEntity e = new TblBaseFileEntity();
		String filePath = yjgImgRelativePath + "/" + dstFile.getName();
		e.setFileid(id);
		e.setExtend(extendName);
		e.setOperatype(operatype);
		e.setFilepath(filePath);
		e.setCjr(UserInfoUtil.getUserName());
		e.setRemark("");
		e.setFilesize(file.length());
		e.setFilename(dstFile.getName());
		if (yjgSjdjEntity.getSjdjid() != null) {
			e.setOperaid(yjgSjdjEntity.getSjdjid());
		}
		if (yjgSjdjEntity.getFyjsjdjid() != null) {
			e.setOperaid(yjgSjdjEntity.getFyjsjdjid());
		}
		if (yjgSjdjEntity.getSjczid() != null) {
			e.setOperaid(yjgSjdjEntity.getSjczid());
		}
		e.setState(1);
		tblBaseFileMapper.insert(e);
		resultMap.put("filePath", FilePathUtil.getFileRealpath(filePath, request));
		resultMap.put("fileid", id);
	}
	
	public void addVedio(MultipartFile multipartFile, HttpServletRequest request, YjgSjdjEntity yjgSjdjEntity, Map<String, Object> resultMap, String operatype)
			throws IOException, InterruptedException {
		String extendName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String id = IDUtil.getId();
		String yjgImgRelativePath = FilePathUtil.getFilePath("yjg_Vedio_Path");
		String dstPath = FilePathUtil.getFilePath("disk_Path") + yjgImgRelativePath;
		String ffmpegPath = FilePathUtil.getFilePath("ffmpeg_Path");
		// 创建文件存放路径
		FileUtil.creatPath(dstPath);
		// 创建目标文件对象
		File dstFile = new File(dstPath + "/" + id + extendName);
		
		// 文件已存在（上传了同名的文件）
		if (dstFile.exists()) {
			dstFile.delete();
			dstFile = new File(dstPath + "/" + id + extendName);
		}
		CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
		File file = fi.getStoreLocation();
		FileUtil.copy(file, dstFile);
		
		File videoFile = new File(dstPath + "/" + id + extendName);
		File pngFile = new File(dstPath + "/" + id + ".png");
		String filePath = yjgImgRelativePath + "/" + id + ".mp4";
		String pngPath = yjgImgRelativePath + "/" + pngFile.getName();
		
		VideoThumbTaker videoThumbTaker = new VideoThumbTaker(ffmpegPath);
		videoThumbTaker.getThumb(dstFile.getPath(), dstPath + "/" + id + ".png", 800, 600, 0, 0, 1);
		
		if (!extendName.equals(".mp4")) {
			String newfilepath = (dstPath + "/" + id + ".mp4").replace("/", "\\");
			String oldfilepath = dstPath + "/" + id + extendName.replace("/", "\\");
			videoThumbTaker.processMp4(oldfilepath, ffmpegPath, newfilepath);
		}
		
		TblBaseFileEntity e = new TblBaseFileEntity();
		
		e.setFileid(id);
		e.setExtend(extendName);
		e.setOperatype(operatype);
		e.setFilepath(filePath);
		e.setCjr(UserInfoUtil.getUserName());
		e.setRemark(pngPath);
		e.setFilesize(file.length());
		e.setFilename(videoFile.getName());
		e.setState(1);
		if (yjgSjdjEntity.getSjdjid() != null) {
			e.setOperaid(yjgSjdjEntity.getSjdjid());
		}
		if (yjgSjdjEntity.getFyjsjdjid() != null) {
			e.setOperaid(yjgSjdjEntity.getFyjsjdjid());
		}
		if (yjgSjdjEntity.getSjczid() != null) {
			e.setOperaid(yjgSjdjEntity.getSjczid());
		}
		tblBaseFileMapper.insert(e);
		
		resultMap.put("fileid", id);
		resultMap.put("vedioFilePath", FilePathUtil.getFileRealpath(e.getRemark(), request));
	}
	
	public List<TblBaseFileEntity> getByOperaid(TblBaseFileEntity tblBaseFileEntity) {
		return tblBaseFileMapper.getByOperaid(tblBaseFileEntity);
	}
	
	public int deleteFile(String fileid, HttpServletRequest request, String dstPath) {
		int row = 0;
		TblBaseFileEntity tblBaseFileEntity = tblBaseFileMapper.getByPkey(fileid);
		File file = new File(dstPath + tblBaseFileEntity.getFilename());
		if (file.exists()) {
			file.delete();
		}
		String reg = ".+(rm|rmvb|wmv|mp4|3gp|mkv|avi)$";
		if (Pattern.matches(reg, tblBaseFileEntity.getExtend())) {
			file = new File(dstPath + tblBaseFileEntity.getFileid() + ".png");
			if (file.exists()) {
				file.delete();
			}
			file = new File(dstPath + tblBaseFileEntity.getFileid() + ".mp4");
			if (file.exists()) {
				file.delete();
			}
		}
		row += delete(fileid);
		return row;
	}
	
	public void updateVedio(TblBaseFileEntity tblBaseFileEntity, HttpServletRequest request, YjgSjdjEntity yjgSjdjEntity, Map<String, Object> resultMap, String operatype)
			throws IOException, InterruptedException {
		if (yjgSjdjEntity.getSjdjid() != null || yjgSjdjEntity.getFyjsjdjid() != null || yjgSjdjEntity.getSjczid() != null) {
			TblBaseFileEntity e = new TblBaseFileEntity();
			e.setOperatype("YJG_VEDIO_FILE");
			if (yjgSjdjEntity.getSjdjid() != null) {
				e.setOperaid(yjgSjdjEntity.getSjdjid());
			} else if (yjgSjdjEntity.getFyjsjdjid() != null) {
				e.setOperaid(yjgSjdjEntity.getFyjsjdjid());
			} else if (yjgSjdjEntity.getSjczid() != null) {
				e.setOperaid(yjgSjdjEntity.getSjczid());
			}
			List<TblBaseFileEntity> list = tblBaseFileMapper.selectByOperatypeAndOperaid(e);
			String dstPath = FilePathUtil.getFilePath("disk_Path") + FilePathUtil.getFilePath("yjg_Vedio_Path") + "/";
			for (TblBaseFileEntity tblBaseFileEntity2 : list) {
				deleteFile(tblBaseFileEntity2.getFileid(), request, dstPath);
			}
		}
		addVedio(tblBaseFileEntity.getVediofile(), request, yjgSjdjEntity, resultMap, operatype);
	}
	
	public int delegeJgImg(String jgid, HttpServletRequest request, int type) {
		String dstPath = request.getSession().getServletContext().getRealPath("") + "/";
		int row = 0;
		TblBaseFileEntity e = new TblBaseFileEntity();
		e.setOperaid(jgid);
		if (type == 1) {
			e.setOperatype("YJG_IMG_NBJG_FILE");
		} else if (type == 2) {
			e.setOperatype("YJG_IMG_JJJG_FILE");
		} else if (type == 3) {
			e.setOperatype("YJG_IMG_YJJG_FILE");
		}
		List<TblBaseFileEntity> list = tblBaseFileMapper.getByOperaid(e);
		File file = new File(dstPath + list.get(0).getFilepath());
		if (file.exists()) {
			file.delete();
		}
		row += delete(list.get(0).getFileid());
		return row;
	}
	
	// 保存内部图片
	public String saveNbImg(YjgJgxxEntity e, HttpServletRequest request, String jgbh, String jgId, Map<String, Object> resultMap) throws Exception {
		String sjwhImgPath = FilePathUtil.getFilePath("sjwh_Img_Path");
		jgbh = jgbh.substring(jgbh.length() - 7, jgbh.length());
		MultipartFile nbImageFile = e.getNbImageFile();
		if (nbImageFile.getSize() != 0) {
			String jnId = IDUtil.getId();
			String extendName = nbImageFile.getOriginalFilename().substring(nbImageFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			String dstPath = FilePathUtil.getFilePath("disk_Path") + sjwhImgPath + "/";
			FileUtil.creatPath(dstPath);
			// 文件名称
			String filename = jgbh + "-NB" + extendName;
			String filePath = sjwhImgPath + "/" + filename;
			File dstFile = new File(dstPath + filename);
			if (dstFile.exists()) {
				dstFile.delete();
				dstFile = new File(dstPath + filename);
			}
			CommonsMultipartFile cf = (CommonsMultipartFile) nbImageFile;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
			File file = fi.getStoreLocation();
			FileUtil.copy(file, dstFile);
			CompressImgFile.reduceImg(dstFile, request);
			TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
			tblBaseFileEntity.setCjr(UserInfoUtil.getUserName());
			tblBaseFileEntity.setExtend(extendName);
			tblBaseFileEntity.setFileid(jnId);
			tblBaseFileEntity.setFilename(filename);
			tblBaseFileEntity.setFilepath(filePath);
			tblBaseFileEntity.setFilesize(file.length());
			tblBaseFileEntity.setGxsj(new Date());
			tblBaseFileEntity.setOperaid(jgId);
			tblBaseFileEntity.setOperatype("YJG_IMG_NBJG_FILE");
			tblBaseFileEntity.setState(1);
			resultMap.put("nbPathName", filename);
			tblBaseFileMapper.insert(tblBaseFileEntity);
			return filePath;
		}
		return "";
	}
	
	// 保存近景图片
	public String saveJjImg(YjgJgxxEntity e, HttpServletRequest request, String jgbh, String jgid, Map<String, Object> resultMap) throws Exception {
		String sjwhImgPath = FilePathUtil.getFilePath("sjwh_Img_Path");
		jgbh = jgbh.substring(jgbh.length() - 7, jgbh.length());
		MultipartFile jjImageFile = e.getJjImageFile();
		if (jjImageFile.getSize() != 0) {
			String jjId = IDUtil.getId();
			String extendName = jjImageFile.getOriginalFilename().substring(jjImageFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			String dstPath = FilePathUtil.getFilePath("disk_Path") + sjwhImgPath + "/";
			// 文件名称
			String filename = jgbh + "-JJ" + extendName;
			String filePath = sjwhImgPath + "/" + filename;
			File dstFile = new File(dstPath + filename);
			if (dstFile.exists()) {
				dstFile.delete();
				dstFile = new File(dstPath + filename);
			}
			CommonsMultipartFile cf = (CommonsMultipartFile) jjImageFile;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
			File file = fi.getStoreLocation();
			FileUtil.copy(file, dstFile);
			CompressImgFile.reduceImg(dstFile, request);
			TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
			tblBaseFileEntity.setCjr(UserInfoUtil.getUserName());
			tblBaseFileEntity.setExtend(extendName);
			tblBaseFileEntity.setFileid(jjId);
			tblBaseFileEntity.setFilename(filename);
			tblBaseFileEntity.setFilepath(filePath);
			tblBaseFileEntity.setFilesize(file.length());
			tblBaseFileEntity.setGxsj(new Date());
			tblBaseFileEntity.setOperaid(jgid);
			tblBaseFileEntity.setOperatype("YJG_IMG_JJJG_FILE");
			tblBaseFileEntity.setState(1);
			tblBaseFileMapper.insert(tblBaseFileEntity);
			resultMap.put("jjPathName", filename);
			return filePath;
		}
		return "";
	}
	
	// 保存远景图片
	public String saveYjImg(YjgJgxxEntity e, HttpServletRequest request, String jgbh, String jgid, Map<String, Object> resultMap) throws Exception {
		String sjwhImgPath = FilePathUtil.getFilePath("sjwh_Img_Path");
		jgbh = jgbh.substring(jgbh.length() - 7, jgbh.length());
		MultipartFile yjImageFile = e.getYjImageFile();
		if (yjImageFile.getSize() != 0) {
			String yjId = IDUtil.getId();
			String extendName = yjImageFile.getOriginalFilename().substring(yjImageFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			String dstPath = FilePathUtil.getFilePath("disk_Path") + sjwhImgPath + "/";
			
			// 文件名称
			String filename = jgbh + "-YJ" + extendName;
			String filePath = sjwhImgPath + "/" + filename;
			File dstFile = new File(dstPath + filename);
			if (dstFile.exists()) {
				dstFile.delete();
				dstFile = new File(dstPath + filename);
			}
			CommonsMultipartFile cf = (CommonsMultipartFile) yjImageFile;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
			File file = fi.getStoreLocation();
			FileUtil.copy(file, dstFile);
			CompressImgFile.reduceImg(dstFile, request);
			TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
			tblBaseFileEntity.setCjr(UserInfoUtil.getUserName());
			tblBaseFileEntity.setExtend(extendName);
			tblBaseFileEntity.setFileid(yjId);
			tblBaseFileEntity.setFilename(filename);
			tblBaseFileEntity.setFilepath(filePath);
			tblBaseFileEntity.setFilesize(file.length());
			tblBaseFileEntity.setGxsj(new Date());
			tblBaseFileEntity.setOperaid(jgid);
			tblBaseFileEntity.setOperatype("YJG_IMG_YJJG_FILE");
			tblBaseFileEntity.setState(1);
			tblBaseFileMapper.insert(tblBaseFileEntity);
			resultMap.put("yjPathName", filename);
			return filePath;
		}
		return "";
	}
	
	/**
	 * @Package com.kingtopinfo.base.service
	 * @Description: 保存APP
	 * @author cyf
	 * @param appfile
	 * @throws IOException
	 * @date 2017年12月8日 上午9:20:40
	 */
	public void saveApp(HttpServletRequest request, YjgAppversionEntity e, MultipartFile appfile) throws IOException {
		String appPath = FilePathUtil.getFilePath("app_Path");
		String dstPath = FilePathUtil.getFilePath("disk_Path") + appPath;
		File dirfile = new File(dstPath);
		CommonsMultipartFile cf = (CommonsMultipartFile) appfile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
		File file = fi.getStoreLocation();
		String extendName = appfile.getOriginalFilename().substring(appfile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
		String filename = "APP-" + e.getVersioncode() + extendName;
		String newestname = "APP" + extendName;
		if (!dirfile.exists()) {
			dirfile.mkdirs();
		}
		File dstFile = new File(dstPath + "/" + filename);
		File newestFile = new File(dstPath + "/" + newestname);
		if (dstFile.exists()) {
			dstFile.delete();
			dstFile = new File(dstPath + "/" + filename);
		}
		if (newestFile.exists()) {
			newestFile.delete();
			newestFile = new File(dstPath + "/" + newestname);
		}
		FileUtil.copy(file, dstFile);
		FileUtil.copy(file, newestFile);
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setCjr(UserInfoUtil.getUserName());
		tblBaseFileEntity.setExtend(extendName);
		tblBaseFileEntity.setFileid(IDUtil.getId());
		tblBaseFileEntity.setFilename(filename);
		tblBaseFileEntity.setFilepath(appPath + "/" + filename);
		tblBaseFileEntity.setFilesize(file.length());
		tblBaseFileEntity.setGxsj(new Date());
		tblBaseFileEntity.setOperaid(e.getId());
		tblBaseFileEntity.setOperatype("APP_FILE");
		tblBaseFileEntity.setState(1);
		tblBaseFileMapper.insert(tblBaseFileEntity);
	}
	
	public void downloadApp(HttpServletRequest request, HttpServletResponse response, YjgAppversionEntity e,int type) {
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setOperaid(e.getId());
		tblBaseFileEntity.setOperatype("APP_FILE");
		List<TblBaseFileEntity> list = tblBaseFileMapper.selectByOperatypeAndOperaid(tblBaseFileEntity);
		String filepath = list.get(0).getFilepath();
		File file = new File(FilePathUtil.getFilePath("disk_Path") + filepath);
		System.out.println("-----------------------1");
		if (file.exists()) {
			System.out.println("-----------------------2");

			if(type == 1){
				response.setContentType("application/force-download");
				response.setContentLength((int) file.length());
			}else{
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.setContentLength((int) file.length());
			}
			response.addHeader("Content-Disposition", "attachment;fileName=" + list.get(0).getFilename());// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e1) {
				System.out.println("-------------yc-----------------");
				e1.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
			}
		}
	}
}