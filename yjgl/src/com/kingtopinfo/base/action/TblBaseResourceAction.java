package com.kingtopinfo.base.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseResourceEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseResourceService;
import com.kingtopinfo.base.util.WfConstants;

/**
 * @ClassName action.TblBaseResourceAction
 * @Description TBL_BASE_RESOURCE表Action类
 * @author cyf
 * @date 2017-07-27 14:45:09
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblBaseResourceAction")
public class TblBaseResourceAction extends BaseValidAction{
	
	@Autowired
	private TblBaseResourceService tblBaseResourceService;
	@Autowired
	private TblBaseLogService		tblBaseLogService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblBaseResourceEntity e){
		PaginationEntity<TblBaseResourceEntity> o = new PaginationEntity<TblBaseResourceEntity>();
		o.setRows(tblBaseResourceService.selectPagination(e, rows, page));
		o.setTotal(tblBaseResourceService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblBaseResourceEntity e){
		String content = "账号：" + UserInfoUtil.getAccount() + "保存一条资源信息,新增资源名称为：" + e.getName();
		try {
			int rows = tblBaseResourceService.insert(e);
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.SUCCESS);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblBaseResourceEntity e){
		String content = "账号：" + UserInfoUtil.getAccount() + "编辑一条资源信息,编辑资源名称为：" + e.getName();
		try {
			int rows = tblBaseResourceService.update(e);
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.SUCCESS);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	

	/**
	 * @Description 批量删除实体信息
	 * @author cyf
	 * @date 2017-07-27 14:45:09
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		String resourceName = "";
		for (String baseresourceid : idArray) {
			TblBaseResourceEntity tblBaseResourceEntity = tblBaseResourceService.getByPkey(baseresourceid);
			resourceName += tblBaseResourceEntity.getName() + ",";
		}
		String content = "账号：" + UserInfoUtil.getAccount() + "删除资源信息,删除资源名称为：" + resourceName.substring(0, resourceName.length() - 1);
		try {
			int rows = tblBaseResourceService.deleteBatch(idArray);
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.SUCCESS);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}