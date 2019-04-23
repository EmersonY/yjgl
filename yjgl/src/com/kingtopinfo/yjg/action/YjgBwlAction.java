package com.kingtopinfo.yjg.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.TimeUtils;
import com.kingtopinfo.yjg.entity.YjgBwlEntity;
import com.kingtopinfo.yjg.service.YjgBwlService;

/**
 * @ClassName action.YjgBwlAction
 * @Description YJG_BWL表Action类
 * @author cyf
 * @date 2017-12-04 14:04:13
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgBwlAction")
public class YjgBwlAction extends BaseValidAction{
	
	@Autowired
	private YjgBwlService yjgBwlService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-12-04 14:04:13
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, Integer rows, YjgBwlEntity e) {
		PaginationEntity<YjgBwlEntity> o = new PaginationEntity<YjgBwlEntity>();
		e.setBaseuserid(UserInfoUtil.getBaseuserid());
		Date lastDay = TimeUtils.searchNextDay(startDate);
		o.setRows(yjgBwlService.selectPagination(e, rows, page, startDate, lastDay));
		o.setTotal(yjgBwlService.getCount(e, startDate, lastDay));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-12-04 14:04:13
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgBwlEntity e) {
		try {
			int rows = yjgBwlService.insert(e);
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
	 * @date 2017-12-04 14:04:13
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object edit(YjgBwlEntity e) {
		try {
			int rows = yjgBwlService.update(e);
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
	 * @date 2017-12-04 14:04:13
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = yjgBwlService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/selectByDate", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByDate(YjgBwlEntity e, @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
		try {
			List<Map<String, Object>> list = yjgBwlService.selectByDate(e, startDate);
			resultMap.put("list", list);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/remindBwl", method = RequestMethod.POST)
	@ResponseBody
	public Object remindBwl() {
		try {
			resultMap = yjgBwlService.remindBwl();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	@ResponseBody
	public Object read(YjgBwlEntity e) {
		try {
			resultMap.put("rows", yjgBwlService.read(e));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return resultMap;
	}
	
}