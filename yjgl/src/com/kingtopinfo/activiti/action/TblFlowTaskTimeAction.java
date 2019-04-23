package com.kingtopinfo.activiti.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.TblFlowTaskTimeEntity;
import com.kingtopinfo.activiti.service.TblFlowTaskTimeService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;

/**
 * @ClassName action.TblFlowTaskTimeAction
 * @Description TBL_FLOW_TASK_TIME表Action类
 * @author cyf
 * @date 2017-09-19 14:51:08
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblFlowTaskTimeAction")
public class TblFlowTaskTimeAction extends BaseValidAction{
	
	@Autowired
	private TblFlowTaskTimeService tblFlowTaskTimeService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblFlowTaskTimeEntity e){
		PaginationEntity<TblFlowTaskTimeEntity> o = new PaginationEntity<TblFlowTaskTimeEntity>();
		o.setRows(tblFlowTaskTimeService.selectPagination(e, rows, page));
		o.setTotal(tblFlowTaskTimeService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-19 14:51:08
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblFlowTaskTimeEntity e){
		try {
			int rows = tblFlowTaskTimeService.insert(e);
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
	 * @date 2017-09-19 14:51:08
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblFlowTaskTimeEntity e){
		try {
			int rows = tblFlowTaskTimeService.update(e);
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
	 * @date 2017-09-19 14:51:08
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblFlowTaskTimeService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/selectByProcessid", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByProcessid(TblFlowTaskTimeEntity e) {
		return tblFlowTaskTimeService.selectByProcessid(e);
	}
	
}