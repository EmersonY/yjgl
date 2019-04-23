package com.kingtopinfo.base.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseHolidaysEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.service.TblBaseHolidaysService;

/**
 * @ClassName action.TblBaseHolidaysAction
 * @Description TBL_BASE_HOLIDAYS表Action类
 * @author cyf
 * @date 2018-01-12 09:14:28
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblBaseHolidaysAction")
public class TblBaseHolidaysAction extends BaseValidAction{
	
	@Autowired
	private TblBaseHolidaysService tblBaseHolidaysService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblBaseHolidaysEntity e){
		PaginationEntity<TblBaseHolidaysEntity> o = new PaginationEntity<TblBaseHolidaysEntity>();
		o.setRows(tblBaseHolidaysService.selectPagination(e, rows, page));
		o.setTotal(tblBaseHolidaysService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2018-01-12 09:14:28
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblBaseHolidaysEntity e){
		try {
			int rows = tblBaseHolidaysService.insert(e);
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
	 * @date 2018-01-12 09:14:28
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object edit(TblBaseHolidaysEntity e) {
		try {
			int rows = tblBaseHolidaysService.update(e);
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
	 * @date 2018-01-12 09:14:28
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblBaseHolidaysService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}