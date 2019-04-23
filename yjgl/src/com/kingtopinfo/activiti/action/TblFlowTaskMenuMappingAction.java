package com.kingtopinfo.activiti.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.TblFlowTaskMenuMappingEntity;
import com.kingtopinfo.activiti.service.TblFlowTaskMenuMappingService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;

/**
 * @ClassName action.TblFlowTaskMenuMappingAction
 * @Description TBL_FLOW_TASK_MENU_MAPPING表Action类
 * @author cyf
 * @date 2017-09-20 09:39:46
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblFlowTaskMenuMappingAction")
public class TblFlowTaskMenuMappingAction extends BaseValidAction{
	
	@Autowired
	private TblFlowTaskMenuMappingService tblFlowTaskMenuMappingService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblFlowTaskMenuMappingEntity e){
		PaginationEntity<TblFlowTaskMenuMappingEntity> o = new PaginationEntity<TblFlowTaskMenuMappingEntity>();
		o.setRows(tblFlowTaskMenuMappingService.selectPagination(e, rows, page));
		o.setTotal(tblFlowTaskMenuMappingService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-20 09:39:46
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblFlowTaskMenuMappingEntity e){
		try {
			int rows = tblFlowTaskMenuMappingService.insert(e);
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
	 * @date 2017-09-20 09:39:46
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblFlowTaskMenuMappingEntity e){
		try {
			int rows = tblFlowTaskMenuMappingService.update(e);
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
	 * @date 2017-09-20 09:39:46
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblFlowTaskMenuMappingService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * Description :根据流程id和任务id查询所关联的菜单
	 * 
	 * @author lxc Create at @2016年11月2日.下午2:28:34
	 */
	@RequestMapping(value = "/selectByProcessidAndTaskid", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByProcessidAndTaskid(TblFlowTaskMenuMappingEntity e) {
		return tblFlowTaskMenuMappingService.selectByProcessidAndTaskid(e.getProcessid(), e.getTaskid());
	}
	
	/**
	 * @Description 删除实体信息
	 * @author cyf
	 * @date 2017-09-15 17:04:28
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(TblFlowTaskMenuMappingEntity e) {
		try {
			int rows = tblFlowTaskMenuMappingService.delete(e);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}