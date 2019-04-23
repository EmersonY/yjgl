package com.kingtopinfo.yjg.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseUserService;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjqqEntity;
import com.kingtopinfo.yjg.service.YjgSjqqService;

/**
 * @ClassName action.YjgSjqqAction
 * @Description YJG_SJQQ表Action类
 * @author cyf
 * @date 2017-08-24 11:25:35
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgSjqqAction")
public class YjgSjqqAction extends BaseValidAction {
	
	@Autowired
	private YjgSjqqService		yjgSjqqService;
	@Autowired
	private TblBaseUserService	tblBaseUserService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgSjqqEntity e) {
		e.setPbaseuserid(UserInfoUtil.getBaseuserid());
		PaginationEntity<YjgSjqqEntity> o = new PaginationEntity<YjgSjqqEntity>();
		o.setRows(yjgSjqqService.selectPagination(e, rows, page));
		o.setTotal(yjgSjqqService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgSjqqEntity e) {
		try {
			int rows = yjgSjqqService.update(e);
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
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = yjgSjqqService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description:确认权责
	 * @author cyf
	 * @date 2017年9月22日 上午10:36:13
	 */
	@RequestMapping(value = "/accesssj ", method = RequestMethod.POST)
	@ResponseBody
	public Object accesssj(YjgSjdjEntity e) {
		try {
			yjgSjqqService.accesssj(e, resultMap, 2);
		} catch (Exception e2) {
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 现成问题处理完成，提交
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/repair", method = RequestMethod.POST)
	@ResponseBody
	public Object repair(YjgSjqqEntity e) {
		try {
			resultMap.put("rows", yjgSjqqService.repair(e));
			resultMap.put("sec", true);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/findSjqqBySjdjid", method = RequestMethod.POST)
	@ResponseBody
	public Object findSjqqBySjdjid(YjgSjqqEntity yjgSjqqEntity) {
		return yjgSjqqService.findSjqqBySjdjid(yjgSjqqEntity);
	}
}