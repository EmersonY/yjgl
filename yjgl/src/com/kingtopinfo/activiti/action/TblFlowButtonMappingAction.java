package com.kingtopinfo.activiti.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.TblFlowButtonMappingEntity;
import com.kingtopinfo.activiti.service.TblFlowButtonMappingService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;

/**
 * @ClassName action.TblFlowButtonMappingAction
 * @Description TBL_FLOW_BUTTON_MAPPING表Action类
 * @author cyf
 * @date 2017-09-19 15:07:18
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblFlowButtonMappingAction")
public class TblFlowButtonMappingAction extends BaseValidAction{
	
	@Autowired
	private TblFlowButtonMappingService tblFlowButtonMappingService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblFlowButtonMappingEntity e){
		PaginationEntity<TblFlowButtonMappingEntity> o = new PaginationEntity<TblFlowButtonMappingEntity>();
		o.setRows(tblFlowButtonMappingService.selectPagination(e, rows, page));
		o.setTotal(tblFlowButtonMappingService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-19 15:07:18
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblFlowButtonMappingEntity e){
		try {
			int rows = tblFlowButtonMappingService.insert(e);
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
	 * @date 2017-09-19 15:07:18
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblFlowButtonMappingEntity e){
		try {
			int rows = tblFlowButtonMappingService.update(e);
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
	 * @date 2017-09-19 15:07:18
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblFlowButtonMappingService.deleteBatch(idArray);
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
	public Object selectByProcessidAndTaskid(TblFlowButtonMappingEntity e) {
		return tblFlowButtonMappingService.selectByProcessidAndTaskid(e);
	}
	
}