package com.kingtopinfo.app.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kingtopinfo.app.action.Memory;
import com.kingtopinfo.app.entity.YjgAppversionEntity;
import com.kingtopinfo.app.entity.YjgJgxxappEntity;
import com.kingtopinfo.app.mapper.YjgAppSjdjMapper;
import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.entity.TblBaseImageEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserExtEntity;
import com.kingtopinfo.base.mapper.TblBaseFileMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.FileUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.Md5;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.video.VideoThumbTaker;
import com.kingtopinfo.yjg.entity.YjgAppTypeEntity;
import com.kingtopinfo.yjg.entity.YjgGrxxEntity;
import com.kingtopinfo.yjg.entity.YjgSjczEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgTxlEntity;


/**
 * @ClassName service.YjgSjdjService
 * @Description YJG_SJDJ表服务类
 * @author cyf
 * @date 2017-08-24 10:56:59
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgAppSjdjService {
	
	
	@Autowired
	private YjgAppSjdjMapper yjgAppSjdjMapper;
	@Autowired
	private TblBaseFileMapper tblBaseFileMapper;
	@Autowired
	private Memory memory;
	
	private static final String appKey = "dd1066407b044738b6479275";
	private static final String masterSecret = "e8cc9a76d5b7a580859bcfa7";
	
	/**
	 * @Description 按条件分页查询
	 * @param e YjgSjdjEntity实体
	 * @param rowBounds 分页实体
	 * @return YjgSjdjEntity集合
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int editJgxx(YjgJgxxappEntity jgxx){
		jgxx.setCzrj(new Date());
		return yjgAppSjdjMapper.editJgxx(jgxx);
	}
	public int insertJgxx(YjgJgxxappEntity jgxx){
		jgxx.setCzrj(new Date());
		return yjgAppSjdjMapper.insertJgxx(jgxx);
	}
	public YjgAppTypeEntity getTypelist(){
//		String baseuserid = UserInfoUtil.getBaseuserid();
//		System.out.println(baseuserid);
		YjgAppTypeEntity en = new YjgAppTypeEntity();
		en.setJglxlist(yjgAppSjdjMapper.getTypename("JGLX"));
		en.setJgxzlist(yjgAppSjdjMapper.getTypename("JGXZ"));
		en.setJgczlist(yjgAppSjdjMapper.getTypename("JGCZ"));
		en.setJgztlist(yjgAppSjdjMapper.getTypename("JGZT"));
		en.setJggglist(yjgAppSjdjMapper.getTypename("JGGG"));
		en.setSsdllist(yjgAppSjdjMapper.getTypename("SSDL"));
		en.setYzjblist(yjgAppSjdjMapper.getTypename("YZJB"));
		en.setSfzwlist(yjgAppSjdjMapper.getTypename("SFZW"));
		en.setJngjlist(yjgAppSjdjMapper.getTypename("JNGJ"));
		en.setQsjslxlist(yjgAppSjdjMapper.getTypename("QSJSLX"));
		return en;
	}
	public int deleteJgxx(YjgJgxxappEntity jgxx){
		return yjgAppSjdjMapper.deleteJgxx(jgxx);
	}
	public List<YjgJgxxappEntity> selectJgxx(YjgJgxxappEntity jgxx){
		return yjgAppSjdjMapper.selectJgxx(jgxx);
	}
	public TblBaseUserEntity getUsername(String baseuserid) {		
		return yjgAppSjdjMapper.getUsername(baseuserid);
	}
	public int deleteFile(String filepath){
		return yjgAppSjdjMapper.deleteFile(filepath);
	}
	public List<YjgSjdjEntity> selectPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgAppSjdjMapper.selectPagination(e, rowBounds);
	}
	public List<YjgTxlEntity> selectbwlforTimeanduser(YjgTxlEntity e){
		return yjgAppSjdjMapper.selectbwlforTimeanduser(e);
	}
	public YjgSjdjEntity getJbEntity(YjgSjdjEntity e) {
		return yjgAppSjdjMapper.getJbEntity(e);
	}
	public List<TblBaseImageEntity> getJbxxZp(YjgSjdjEntity e) {
		return yjgAppSjdjMapper.getJbxxZp(e);
	}
	
	public List<YjgTxlEntity> getTxlList(String baseuserid) {
		return yjgAppSjdjMapper.getTxlList(baseuserid);
	}
	public List<String> getTypeValue(String code) {
		return yjgAppSjdjMapper.getTypeValue(code);
	}
	public List<String> getTypename(String code) {
		return yjgAppSjdjMapper.getTypename(code);
	}
	public List<YjgTxlEntity> getBwlList(String baseuserid) {
		return yjgAppSjdjMapper.getBwlList(baseuserid);
	}
	public int SaveOrUpdateGrxx(TblBaseUserExtEntity e) {
		e.setUpdatetime(new Date());
		e.setBaseuserextid(IDUtil.getId());
		return yjgAppSjdjMapper.SaveOrUpdateGrxx(e);
	}
	
	
	public List<YjgSjdjEntity> selectTaskPagination(YjgSjdjEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgAppSjdjMapper.selectTaskPagination(e, rowBounds);
	}
	public List<YjgSjczEntity> getczlist(String sjdjid, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgAppSjdjMapper.getczlist(sjdjid, rowBounds);
	}
	public List<YjgSjczEntity> getczlist(String sjdjid) {
		return yjgAppSjdjMapper.getczlist(sjdjid);
	}
	public int getCzCount(String sjdjid) {
		return yjgAppSjdjMapper.getCzCount(sjdjid);
	}
	public int updatetxl(YjgTxlEntity e) {
		return yjgAppSjdjMapper.updatetxl(e);
	}
	public int updatebwl(YjgTxlEntity e) {
		return yjgAppSjdjMapper.updatebwl(e);
	}
	public int inserttxl(YjgTxlEntity e) {
		e.setTxlid(IDUtil.getId());
		return yjgAppSjdjMapper.inserttxl(e);
	}
	public int insertbwl(YjgTxlEntity e) {
		e.setBwlzt("0");
		e.setBwlid(IDUtil.getId());
		return yjgAppSjdjMapper.insertbwl(e);
	}
	public int updateps(TblBaseUserEntity e) {
		e.setPassword(Md5.md5Digest(e.getPassword()));
		return yjgAppSjdjMapper.updateps(e);
	}
	
	public YjgGrxxEntity getGrxx(String baseuserid) {
		return yjgAppSjdjMapper.getGrxx(baseuserid);
	}
	
	/**
	 * @Description 按sjdjid删除YjgSjdjEntity信息
	 * @param sjdjid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int deletetxl( YjgTxlEntity e) {
		return yjgAppSjdjMapper.deletetxl(e);
	}
	public int deletebwl( YjgTxlEntity e) {
		return yjgAppSjdjMapper.deletebwl(e);
	}
	public int updatebwlzt( YjgTxlEntity e) {
		return yjgAppSjdjMapper.updatebwlzt(e);
	}
	
	/**
	 * @Description 按条件查询总条数
	 * @param e YjgSjdjEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	public int getCount(YjgSjdjEntity e) {
		return yjgAppSjdjMapper.getCount(e);
	}
	public int getTaskCount(YjgSjdjEntity e) {
		return yjgAppSjdjMapper.getTaskCount(e);
	}
	public TblBaseUserEntity selectuserbyid(String baseuserid) {
		return yjgAppSjdjMapper.selectuserbyid(baseuserid);
	}
	
	/**
	 * @Description 按sjdjid删除YjgSjdjEntity信息
	 * @param sjdjid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */	
	public int delete(String sjdjid){
		return yjgAppSjdjMapper.delete(sjdjid);
	}
	
	public List<TblBaseImageEntity>  addImg(String type,String username,String sjid,MultipartFile[] multipartFile, HttpServletRequest request, List<TblBaseImageEntity> list)
			throws Exception {
		if(multipartFile!=null&&multipartFile.length>0){
			String url= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
			if("vedio".equals(type)){
				String id = IDUtil.getId();
				TblBaseFileEntity e = new TblBaseFileEntity();
				e.setFileid(id);
				e.setCjr(username);
				e.setOperaid(sjid);
				e.setState(1);
				e.setOperatype("YJG_VEDIO_FILE");
				for(int i = 0;i<multipartFile.length;i++){
					MultipartFile mfile = multipartFile[i]; 
					String extendName = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf("."));
					String yjgImgRelativePath = FilePathUtil.getFilePath("yjg_Vedio_Path");
					String reg = ".+(rm|rmvb|wmv|mp4|3gp|mkv|avi)$";
					boolean isMatch = Pattern.matches(reg, extendName);
					
					String dstPath = FilePathUtil.getFilePath("disk_Path") + yjgImgRelativePath;
					// 创建文件存放路径
					FileUtil.creatPath(dstPath);
					// 创建目标文件对象
					File dstFile = new File(dstPath + "/" + id + extendName);
					// 文件已存在（上传了同名的文件）
					if (dstFile.exists()) {
						dstFile.delete();
						dstFile = new File(dstPath + "/" + id + extendName);
					}
					CommonsMultipartFile cf = (CommonsMultipartFile) mfile;
					DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
					File file = fi.getStoreLocation();
					FileUtil.copy(file, dstFile);
					String filePath = yjgImgRelativePath + "/" + dstFile.getName();
					if(isMatch == true){
						if (!extendName.equals(".mp4")) {
							filePath = yjgImgRelativePath + "/" + id + ".mp4";
							String ffmpegPath = FilePathUtil.getFilePath("ffmpeg_Path");
							VideoThumbTaker videoThumbTaker = new VideoThumbTaker(ffmpegPath);
							String oldfilepath = dstPath + id + extendName.replace("/", "\\");
							String newfilepath = (dstPath + id + ".mp4").replace("/", "\\");
							videoThumbTaker.processMp4(oldfilepath, ffmpegPath, newfilepath);
						}
						e.setExtend(extendName);
						e.setFilepath(filePath);
						e.setFilename(dstFile.getName());
						e.setFilesize(file.length());
					}else{
						e.setRemark(filePath);
					}
				}

				int row = tblBaseFileMapper.insert(e);
				if(row == 1){
					TblBaseImageEntity en= new TblBaseImageEntity();
					en.setMimeType(id);
					en.setName(e.getRemark());
					en.setPath(url+e.getFilepath());
					list.add(en);
				}
			}else{
				for(int i = 0;i<multipartFile.length;i++){ 
					MultipartFile mfile = multipartFile[i]; 
					list.add(saveFile(type,username,sjid,mfile,request));
			    } 
			}
		
		}
		return list;

	}
	public TblBaseImageEntity saveLogo(String baseuserid,String username,MultipartFile multipartFile, HttpServletRequest request ) throws IOException {
		String url= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
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
			dstFile = new File(dstPath + "/" + id + extendName);
		}
		CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
		File file = fi.getStoreLocation();
		FileUtil.copy(file, dstFile);
		TblBaseFileEntity e = new TblBaseFileEntity();
		String filePath = yjgImgRelativePath + "/" + dstFile.getName();
		e.setFileid(id);
		e.setExtend(extendName);
		e.setOperatype("YJG_IMG_FILE");
		e.setFilepath(filePath);
		e.setCjr(username);
		e.setFilesize(file.length());
		e.setFilename(dstFile.getName());
		e.setOperaid(baseuserid);
		e.setState(1);
		int row = tblBaseFileMapper.saveOrupdatelogo(e);
		TblBaseImageEntity en = new TblBaseImageEntity();
		if(row == 1){
			en.setPath(url+filePath);
			en.setMimeType(id);
			return en;
		}
		else{
			return en;
		}


	}
	public YjgAppversionEntity getversion(){
		return yjgAppSjdjMapper.getversion();
	}
	public TblBaseImageEntity saveFile(String type, String username, String sjid, MultipartFile multipartFile, HttpServletRequest request) throws Exception {
		String url= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
		String extendName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String id = IDUtil.getId();
		String yjgImgRelativePath = FilePathUtil.getFilePath("yjg_Img_Path");
		if ("YJG_IMG_NBJG_FILE".equals(type)) {
			yjgImgRelativePath = FilePathUtil.getFilePath("sjcj_Img_Path");
		} else if ("YJG_IMG_JJJG_FILE".equals(type)) {
			yjgImgRelativePath = FilePathUtil.getFilePath("sjcj_Img_Path");
		} else if ("YJG_IMG_YJJG_FILE".equals(type)) {
			yjgImgRelativePath = FilePathUtil.getFilePath("sjcj_Img_Path");
		} else {
			yjgImgRelativePath = FilePathUtil.getFilePath("yjg_Img_Path");
		}
		String dstPath = FilePathUtil.getFilePath("disk_Path") + yjgImgRelativePath;
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
//		
		TblBaseFileEntity e = new TblBaseFileEntity();
		String filePath = yjgImgRelativePath + "/" + dstFile.getName();
		e.setFileid(id);
		e.setExtend(extendName);
		if ("YJG_IMG_NBJG_FILE".equals(type)) {
			yjgImgRelativePath = FilePathUtil.getFilePath("sjcj_Img_Path");
			filePath = yjgImgRelativePath + "/" + dstFile.getName();
			e.setOperatype(type);
		}
		else if ("YJG_IMG_JJJG_FILE".equals(type)) {
			yjgImgRelativePath = FilePathUtil.getFilePath("sjcj_Img_Path");
			filePath = yjgImgRelativePath + "/" + dstFile.getName();
			e.setOperatype(type);
		}
		else if ("YJG_IMG_YJJG_FILE".equals(type)) {
			yjgImgRelativePath = FilePathUtil.getFilePath("sjcj_Img_Path");
			filePath = yjgImgRelativePath + "/" + dstFile.getName();
			e.setOperatype(type);
		}
		else{
			e.setOperatype("YJG_IMG_FILE");
		}
		e.setFilepath(filePath);
		e.setCjr(username);
		e.setFilesize(file.length());
		e.setFilename(dstFile.getName());
		e.setOperaid(sjid);
		e.setState(1);
		int row = tblBaseFileMapper.insert(e);
		TblBaseImageEntity en = new TblBaseImageEntity();
		if(row == 1){
			en.setPath(url+filePath);
			en.setMimeType(id);
			return en;
		}
		else{
			return en;
		}


	}
	
}