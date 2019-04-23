package com.kingtopinfo.activiti.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.service.ActDeploymentService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.form.ActDeployment;
import com.kingtopinfo.base.form.ActProcessDef;
import com.kingtopinfo.base.form.WorkflowBean;

/**
 * @ClassName com.kingtopinfo.base.action.TblBaseLogAction
 * @Description TBL_BASE_LOG表Action类
 * @author cyf
 * @date 2017-03-08 16:57:11
 * @version 1.0
 * @remark create by generator
 */

@Controller
@RequestMapping("/ActDeploymentAction")
public class ActDeploymentAction extends BaseValidAction {
	
	@Autowired
	private ActDeploymentService actDeploymentService;
	
	/**
	 * @Description 添加部署
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/newdeploy", method = RequestMethod.POST)
	@ResponseBody
	public Object newdeploy(WorkflowBean workflowBean, HttpServletRequest request) {
		try {
			actDeploymentService.saveNewDeploye(workflowBean, request);
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 查询部署
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/findDeploymentList", method = RequestMethod.POST)
	@ResponseBody
	public Object findDeploymentList() {
		try {
			List<ActDeployment> list = new ArrayList<ActDeployment>();
			PaginationEntity<ActDeployment> o = new PaginationEntity<ActDeployment>();
			List<Deployment> depList = actDeploymentService.findDeploymentList();
			for (Deployment deployment : depList) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ActDeployment actDeployment = new ActDeployment();
				actDeployment.setDeploymentTime(simpleDateFormat.format(deployment.getDeploymentTime()));
				actDeployment.setId(deployment.getId());
				actDeployment.setName(deployment.getName());
				list.add(actDeployment);
			}
			o.setRows(list);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @Description 查询流程实例
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/findProcessDefinitionList", method = RequestMethod.POST)
	@ResponseBody
	public Object findProcessDefinitionList() {
		try {
			List<ActProcessDef> list = new ArrayList<ActProcessDef>();
			PaginationEntity<ActProcessDef> o = new PaginationEntity<ActProcessDef>();
			List<ProcessDefinition> pdList = actDeploymentService.findProcessDefinitionList();
			for (ProcessDefinition processDefinition : pdList) {
				ActProcessDef actProcessDef = new ActProcessDef();
				actProcessDef.setDeploymentId(processDefinition.getDeploymentId());
				actProcessDef.setDiagramResourceName(processDefinition.getDiagramResourceName());
				actProcessDef.setId(processDefinition.getId());
				actProcessDef.setKey(processDefinition.getKey());
				actProcessDef.setName(processDefinition.getName());
				actProcessDef.setResourceName(processDefinition.getResourceName());
				actProcessDef.setVersion(processDefinition.getVersion());
				list.add(actProcessDef);
			}
			o.setRows(list);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 删除部署信息
	 */
	@RequestMapping(value = "/delDeployment", method = RequestMethod.POST)
	@ResponseBody
	public Object delDeployment(WorkflowBean workflowBean) {
		try {
			// 1：获取部署对象ID
			String deploymentId = workflowBean.getDeploymentId();
			// 2：使用部署对象ID，删除流程定义
			actDeploymentService.deleteProcessDefinitionByDeploymentId(deploymentId);
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 查看流程图
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewImage", method = RequestMethod.GET)
	public String viewImage(WorkflowBean workflowBean, HttpServletResponse response) throws Exception {
		// 1：获取页面传递的部署对象ID和资源图片名称
		// 部署对象ID
		String deploymentId = workflowBean.getDeploymentId();
		// 资源图片名称
		String imageName = workflowBean.getImageName();
		// 2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
		InputStream in = actDeploymentService.findImageInputStream(deploymentId, imageName);
		// 3：从response对象获取输出流
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		// 4：将输入流中的数据读取出来，写到输出流中
		for (int b = -1; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();
		// 将图写到页面上，用输出流写
		return null;
	}

}