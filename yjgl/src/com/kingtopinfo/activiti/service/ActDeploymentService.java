package com.kingtopinfo.activiti.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.form.WorkflowBean;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.FileUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.ZipUtil;

/**
 * @ClassName com.kingtopinfo.base.service.ActDeploymentService
 * @author cyf
 * @date 2017-03-08 16:57:11
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class ActDeploymentService {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	
	/** 查询部署对象信息，对应表（act_re_deployment） */
	public List<Deployment> findDeploymentList() {
		List<Deployment> list = repositoryService.createDeploymentQuery()// 创建部署对象查询
				.orderByDeploymenTime().asc()//
				.list();
		return list;
	}
	
	/**
	 * 部署流程定义
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public void saveNewDeploye(WorkflowBean workflowBean, HttpServletRequest request) throws Exception {
		String bpmnRelativePath = FilePathUtil.getFilePath("bpmn_Path");
		String dstPath = FilePathUtil.getFilePath("disk_Path") + bpmnRelativePath;
		// 创建文件存放路径
		FileUtil.creatPath(dstPath);
		// 创建目标文件对象
		File dstFile = new File(dstPath + "/" + workflowBean.getFilename());
		
		// 文件已存在（上传了同名的文件）
		if (dstFile.exists()) {
			dstFile.delete();
			dstFile = new File(dstPath);
		}
		
		// 1：获取页面上传递的zip格式的文件，格式是File类型
		MultipartFile multipartFile = workflowBean.getFile();
		CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem(); // 文件名称
		File file = fi.getStoreLocation();
		String filename = workflowBean.getFilename(); // 完成部署
		FileUtil.copy(file, dstFile);
		
		// 2：将File类型的文件转化成ZipInputStream流
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
		Deployment deployment = repositoryService.createDeployment()// 创建部署对象
				.name(filename)// 添加部署名称
				.addZipInputStream(zipInputStream)
				.deploy();// 完成部署
		zipInputStream.closeEntry();
		
		// 3: 上传png和bpmn文件
		if (deployment != null) {
			String str = IDUtil.getId();
			ZipUtil.unZip(dstFile.getPath(), dstPath + "temp/" + str + "/");
			File[] fileArray = new File(dstPath + "temp/" + str + "/").listFiles();
			if (fileArray != null && fileArray.length > 0) {
				for (File each : fileArray) {
					TblBaseFileEntity e = new TblBaseFileEntity();
					if (each.getName().endsWith(".png") || each.getName().endsWith(".PNG")) {
						File pngfile = new File(dstPath+"/" + IDUtil.getId() + ".png");
						FileUtil.copy(each, pngfile);
						e.setFileid(IDUtil.getId());
						e.setExtend(each.getName().substring(each.getName().lastIndexOf(".")));
						e.setOperatype("BPMN_PNG");
						e.setFilepath(bpmnRelativePath + "/" + pngfile.getName());
						e.setCjr(UserInfoUtil.getUserName());
						e.setRemark("");
						e.setFilesize(each.length());
						e.setFilename(pngfile.getName());
						e.setOperaid(deployment.getId());
						e.setState(1);
						e.setGxsj(new Date());
						tblBaseFileService.insert(e);
						each.delete();
					} else if (each.getName().endsWith(".bpmn") || each.getName().endsWith(".BPMN")) {
						File bpmnfile = new File(dstPath+"/" + IDUtil.getId() + ".bpmn");
						FileUtil.copy(each, bpmnfile);
						e.setFileid(IDUtil.getId());
						e.setExtend(each.getName().substring(each.getName().lastIndexOf(".")));
						e.setOperatype("BPMN_BPMN");
						e.setFilepath(bpmnRelativePath + "/" + bpmnfile.getName());
						e.setCjr(UserInfoUtil.getUserName());
						e.setRemark("");
						e.setFilesize(each.length());
						e.setFilename(bpmnfile.getName());
						e.setOperaid(deployment.getId());
						e.setState(1);
						e.setGxsj(new Date());
						tblBaseFileService.insert(e);
						each.delete();
					}
				}
			}
			FileUtil.deleteFolder(dstPath + "temp");
			dstFile.delete();
		}
		zipInputStream.close();
		file.delete();
	}
	
	/** 查询流程定义的信息，对应表（act_re_procdef） */
	public List<ProcessDefinition> findProcessDefinitionList() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()// 创建流程定义查询
				.orderByProcessDefinitionVersion().asc()//
				.list();
		return list;
	}
	
	/** 使用部署对象ID，删除流程定义 */
	public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}
	
	/** 使用部署对象ID和资源图片名称，获取图片的输入流 */
	public InputStream findImageInputStream(String deploymentId, String imageName) {
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}
}
