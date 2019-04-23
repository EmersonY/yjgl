package com.kingtopinfo.activiti.action;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.activiti.service.ActTaskService;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;

/**
 * @Package com.kingtopinfo.activiti.action
 * @Description: TODO
 * @author cyf
 * @date 2017年9月21日 下午3:11:18
 */
@Controller
@RequestMapping("/ActTaskAction")
public class ActTaskAction extends BaseValidAction {
	
	@Autowired
	private ActTaskService actTaskService;
	
	// 查找第一个任务
	@RequestMapping(value = "/selectFirstTasks", method = RequestMethod.POST)
	@ResponseBody
	public Object selectFirstTasks(String definitionkey, String starteventkey) {
		List<ActTaskEntity> list = actTaskService.selectFirstTasks(definitionkey, starteventkey);
		return list;
	}
	
	// 查找下一个任务
	@RequestMapping(value = "/selectNextKey", method = RequestMethod.POST)
	@ResponseBody
	public Object selectNextTasks(@Param("taskid") String taskid) {
		List<ActTaskEntity> list = actTaskService.selectNextKey(taskid);
		return list;
	}
	
	// 查找下一个列表任务
	@RequestMapping(value = "/selectNextTasksTable", method = RequestMethod.POST)
	@ResponseBody
	public Object selectNextTasksTable(@Param("taskid") String taskid, @Param("idArray") String taskkey, @Param("username") String username, @Param("sjdjid") String sjdjid) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		List<ActTaskEntity> list = actTaskService.selectNextTasks(taskid, null, username, 2, sjdjid);
		if (taskkey.equals("sjpd")) {
			o.setRows(list.get(0).getCandidates());
		} else if (taskkey.equals("sjrl")) {
			o.setRows(list.get(0).getCandidates());
		} else if (taskkey.equals("cxpd")) {
			o.setRows(list.get(1).getCandidates());
		} else {
			o.setRows(list.get(0).getCandidates());
		}
		return o;
	}
}
