package com.kingtopinfo.activiti.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.activiti.entity.TblMkMenuEntity;
import com.kingtopinfo.activiti.service.TblMkMenuService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;

/**
 * @ClassName action.TblMkMenuAction
 * @Description TBL_MK_MENU表Action类
 * @author cyf
 * @date 2017-09-18 09:25:29
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblMkMenuAction")
public class TblMkMenuAction extends BaseValidAction{
	
	@Autowired
	private TblMkMenuService tblMkMenuService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblMkMenuEntity e){
		PaginationEntity<TblMkMenuEntity> o = new PaginationEntity<TblMkMenuEntity>();
		o.setRows(tblMkMenuService.selectPagination(e, rows, page));
		o.setTotal(tblMkMenuService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblMkMenuEntity e){
		try {
			int rows = tblMkMenuService.insert(e);
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
	 * @date 2017-09-18 09:25:29
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblMkMenuEntity e){
		try {
			int rows = tblMkMenuService.update(e);
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
	 * @date 2017-09-18 09:25:29
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblMkMenuService.deleteBatch(idArray);
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
	 * @date 2017-09-18 09:25:29
	 */
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	@ResponseBody
	public Object select() {
		List<TblMkMenuEntity> list = tblMkMenuService.select();
		return list;
	}
	
	/**
	 * @Description 删除模块菜单
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(String id) {
		return tblMkMenuService.delete(id);
	}
}