package com.kingtopinfo.activiti.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.TblFlowChartEntity;
import com.kingtopinfo.activiti.service.TblProcessDiagramService;
import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.FilePathUtil;

@Controller
@RequestMapping("/TblProcessDiagramAction")
public class TblProcessDiagramAction extends BaseValidAction {

	@Autowired
	private TblProcessDiagramService processDiagramService;
	@Autowired
	private TblBaseFileService		tBaseFileService;
	private String taskid;
	

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	@ResponseBody
	public Object location(HttpSession httpSession, String operatype, String operaid) {
		List<TblBaseFileEntity> list = tBaseFileService.selectByOperatypeAndOperaid(operatype, operaid);
		if(list != null && !list.isEmpty()){
			String path = FilePathUtil.getFilePath("disk_Path") + list.get(0).getFilepath();
			List<TblFlowChartEntity> flowChartLocation = processDiagramService.flowChartLocation(path);
			return flowChartLocation;
		} else {
			return null;
		}
	}
	
	/**
	 * Description :查询所有节点
	 * 
	 * @author lxc Create at @2016年11月2日.下午3:31:33
	 */
	@RequestMapping(value = "/selectAllNodes", method = RequestMethod.POST)
	@ResponseBody
	public Object selectAllNodes(HttpSession httpSession, String operatype, String operaid) {
		List<TblBaseFileEntity> list = tBaseFileService.selectByOperatypeAndOperaid(operatype, operaid);
		if (list != null && !list.isEmpty()) {
			String path = httpSession.getServletContext().getRealPath("") + list.get(0).getFilepath();
			return processDiagramService.selectAllNodes(path, taskid);
		} else {
			return null;
		}
	}
	//
	// /**
	// * Description :根据任务id查看流程图
	// * @author lxc
	// * Create at @2016年11月25日.下午4:40:55
	// */
	// public void showDiagramByTaskid(){
	//
	// try {
	// System.out.println("------------------");
	// BufferedOutputStream out = null;
	// InputStream ips = null;
	// try {
	// //获取图片存放路径
	//
	// TaskEntity task =workFlowService.getTaskByTaskId(taskid);
	// ips = workFlowService.getDiagram(task.getInstanceid());
	// response.setContentType("multipart/form-data;charset=utf8");
	// out = new BufferedOutputStream(response.getOutputStream());
	// //读取文件流
	// int len = 0;
	// byte[] buffer = new byte[1024];
	// while ((len = ips.read(buffer)) != -1){
	// out.write(buffer,0,len);
	// }
	// out.flush();
	// }catch (Exception e){
	// e.printStackTrace();
	// }finally {
	// out.close();
	// ips.close();
	// }
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	//// OutputStream out = null;
	//// try {
	//// TaskEntity task =workFlowService.getTaskByTaskId(taskid);
	//// InputStream is = workFlowService.getDiagram(task.getInstanceid());
	//// response.setContentType("multipart/form-data;charset=utf8");
	//// out = response.getOutputStream();
	//// out.write(ImageUtil.getImgByte(is));
	//// out.flush();
	//// }catch (Exception e) {
	//// e.printStackTrace();
	//// } finally {
	//// try {
	//// out.close();
	//// } catch (Exception e) {
	//// }
	//// }
	//// return null;
	// }
	//
	// /**
	// * Description :根据流程实例id查询流程图
	// * @author lxc
	// * Create at @2016年11月30日.下午4:59:07
	// */
	// public String showDiagramByInstanceId(){
//		OutputStream out = null;
//		try {
	// InputStream is = workFlowService.getDiagram(this.processInstanceId);
//			response.setContentType("multipart/form-data;charset=utf8");
//	        out = response.getOutputStream();
//	        out.write(ImageUtil.getImgByte(is));
//	        out.flush();
//		}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				out.close();
//			} catch (Exception e) {
//			}
//		}
//		return null;
	// }
	//
	// @Override
	// public void setServletRequest(HttpServletRequest request) {
	// this.request=request;
	// }
	//
	// @Override
	// public void setServletResponse(HttpServletResponse response) {
	// this.response = response;
	// }
}

