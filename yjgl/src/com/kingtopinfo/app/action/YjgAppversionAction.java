package com.kingtopinfo.app.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.app.entity.YjgAppversionEntity;
import com.kingtopinfo.app.service.YjgAppSjdjService;
import com.kingtopinfo.app.service.YjgAppversionService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.service.TblBaseFileService;

/**
 * @ClassName action.YjgAppversionAction
 * @Description YJG_APPVERSION表Action类
 * @author cyf
 * @date 2018-01-02 09:51:19
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgAppversionAction")
public class YjgAppversionAction extends BaseValidAction{
	
	@Autowired
	private YjgAppversionService yjgAppversionService;
	@Autowired
	private TblBaseFileService		tblBaseFileService;
	@Autowired
	YjgAppSjdjService			yjgAppSjdjService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgAppversionEntity e){
		PaginationEntity<YjgAppversionEntity> o = new PaginationEntity<YjgAppversionEntity>();
		o.setRows(yjgAppversionService.selectPagination(e, rows, page));
		o.setTotal(yjgAppversionService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2018-01-02 09:51:19
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request, YjgAppversionEntity e) {
		try {
			int rows = yjgAppversionService.insert(request, e);
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
	 * @date 2018-01-02 09:51:19
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgAppversionEntity e){
		try {
			int rows = yjgAppversionService.update(e);
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
	 * @date 2018-01-02 09:51:19
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = yjgAppversionService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Package com.kingtopinfo.app.action
	 * @Description: 下载APP
	 * @author cyf
	 * @date 2017年12月8日 下午4:58:26
	 */
	@RequestMapping(value = "/downloadApp", method = RequestMethod.GET)
	@ResponseBody
	public Object downloadApp(HttpServletRequest request, HttpServletResponse response, YjgAppversionEntity e) {
		try {
			tblBaseFileService.downloadApp(request, response, e,2);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/downloadNewestApp", method = RequestMethod.GET)
	@ResponseBody
	public Object downloadNewestApp(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<YjgAppversionEntity> list = yjgAppversionService.select();
			tblBaseFileService.downloadApp(request, response, list.get(0),2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/dp", method = RequestMethod.GET)
	@ResponseBody
	public Object dp(HttpServletRequest request, HttpServletResponse response) {
		try {
			YjgAppversionEntity en = yjgAppSjdjService.getversion();
			tblBaseFileService.downloadApp(request, response, en,1);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
}