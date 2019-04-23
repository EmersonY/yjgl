package com.kingtopinfo.activiti.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.activiti.service.TblFlowTaskRoleMappingService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;

/**
 * @ClassName action.TblFlowTaskRoleMappingAction
 * @Description TBL_FLOW_TASK_ROLE_MAPPING表Action类
 * @author cyf
 * @date 2017-08-21 15:05:42
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblFlowTaskRoleMappingAction")
public class TblFlowTaskRoleMappingAction extends BaseValidAction{
	
	@Autowired
	private TblFlowTaskRoleMappingService tblFlowTaskRoleMappingService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblFlowTaskRoleMappingEntity e){
		PaginationEntity<TblFlowTaskRoleMappingEntity> o = new PaginationEntity<TblFlowTaskRoleMappingEntity>();
		o.setRows(tblFlowTaskRoleMappingService.selectPagination(e, rows, page));
		o.setTotal(tblFlowTaskRoleMappingService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblFlowTaskRoleMappingEntity e){
		try {
			int rows = tblFlowTaskRoleMappingService.insert(e);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblFlowTaskRoleMappingEntity e){
		try {
			int rows = tblFlowTaskRoleMappingService.update(e);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 批量删除实体信息
	 * @author cyf
	 * @date 2017-08-21 15:05:42
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblFlowTaskRoleMappingService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/selectByProcessidAndTaskid", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByProcessidAndTaskid(TblFlowTaskRoleMappingEntity e) {
		return tblFlowTaskRoleMappingService.selectByProcessidAndTaskid(e.getProcessid(), e.getTaskid());
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(TblFlowTaskRoleMappingEntity e) {
		try {
			int rows = tblFlowTaskRoleMappingService.deleteByRoleidAndProcessidAndTaskid(e.getRoleid(), e.getProcessid(), e.getTaskid());
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
}