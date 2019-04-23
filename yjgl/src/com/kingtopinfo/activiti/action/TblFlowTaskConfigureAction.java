package com.kingtopinfo.activiti.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.TblFlowTaskConfigureEntity;
import com.kingtopinfo.activiti.entity.TblFlowTaskTimeEntity;
import com.kingtopinfo.activiti.service.TblFlowTaskConfigureService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;

/**
 * @ClassName action.TblFlowTaskConfigureAction
 * @Description Tbl_FLOW_TASK_CONFIGURE表Action类
 * @author cyf
 * @date 2017-09-19 14:22:08
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblFlowTaskConfigureAction")
public class TblFlowTaskConfigureAction extends BaseValidAction{
	
	@Autowired
	private TblFlowTaskConfigureService tblFlowTaskConfigureService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblFlowTaskConfigureEntity e){
		PaginationEntity<TblFlowTaskConfigureEntity> o = new PaginationEntity<TblFlowTaskConfigureEntity>();
		o.setRows(tblFlowTaskConfigureService.selectPagination(e, rows, page));
		o.setTotal(tblFlowTaskConfigureService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-19 14:22:08
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblFlowTaskConfigureEntity e){
		try {
			int rows = tblFlowTaskConfigureService.insert(e);
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
	 * @date 2017-09-19 14:22:08
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblFlowTaskConfigureEntity e){
		try {
			int rows = tblFlowTaskConfigureService.update(e);
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
	 * @date 2017-09-19 14:22:08
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblFlowTaskConfigureService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 按主键查询实体信息
	 * @author lxc@kingtopinfo.com
	 */
	@RequestMapping(value = "/selectByPidAndTid", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByPidAndTid(TblFlowTaskConfigureEntity e) {
		return tblFlowTaskConfigureService.selectByProcessidAndTaskid(e.getProcessid(), e.getTaskid());
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(TblFlowTaskConfigureEntity e, TblFlowTaskTimeEntity flow) {
		Map<String, Object> map = new HashMap<String, Object>();
		int row = tblFlowTaskConfigureService.save(e, flow);
		map.put("sec", true);
		map.put("row", row);
		map.put("id", e.getFlowtaskconfigureid());
		map.put("flowid", flow.getId());
		return map;
	}
	
}