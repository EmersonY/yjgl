package com.kingtopinfo.base.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseResourceEntity;
import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.entity.TblBaseRoleResourceMappingEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseResourceService;
import com.kingtopinfo.base.service.TblBaseRoleResourceMappingService;
import com.kingtopinfo.base.service.TblBaseRoleService;
import com.kingtopinfo.base.util.WfConstants;

/**
 * @ClassName action.TblBaseRoleResourceMappingAction
 * @Description TBL_BASE_ROLE_RESOURCE_MAPPING表Action类
 * @author cyf
 * @date 2017-07-28 10:39:35
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/TblBaseRoleResourceMappingAction")
public class TblBaseRoleResourceMappingAction extends BaseValidAction{
	
	@Autowired
	private TblBaseRoleResourceMappingService tblBaseRoleResourceMappingService;
	@Autowired
	private TblBaseLogService					tblBaseLogService;
	@Autowired
	private TblBaseRoleService					tblBaseRoleService;
	@Autowired
	private TblBaseResourceService				tblBaseResourceService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblBaseRoleResourceMappingEntity e){
		PaginationEntity<TblBaseRoleResourceMappingEntity> o = new PaginationEntity<TblBaseRoleResourceMappingEntity>();
		o.setRows(tblBaseRoleResourceMappingService.selectPagination(e, rows, page));
		o.setTotal(tblBaseRoleResourceMappingService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@RequestParam("idArray") String[] idArray, @RequestParam("baseresourceid") String baseresourceid) {
		String resourceName = "";
		for (String baseRoleid : idArray) {
			TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleService.getByPkey(baseRoleid);
			resourceName += tblBaseRoleEntity.getRolename() + ",";
		}
		TblBaseResourceEntity tblBaseResourceEntity = tblBaseResourceService.getByPkey(baseresourceid);
		String content = "账号：" + UserInfoUtil.getAccount() + "授权给角色：" + resourceName.substring(0, resourceName.length() - 1) + " 查看资源：" + tblBaseResourceEntity.getName() + " 的权限";
		try {
			int rows = tblBaseRoleResourceMappingService.insert(idArray, baseresourceid);
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.SUCCESS);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e) {
			tblBaseLogService.insertLog(WfConstants.RESOURCEMODULE, content, WfConstants.FAIL);
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 利用角色添加实体信息
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */
	@RequestMapping(value = "/addByRole", method = RequestMethod.POST)
	@ResponseBody
	public Object addByRole(@RequestParam("idArray") String[] idArray, @RequestParam("baseroleid") String baseroleid) {
		try {
			int rows = tblBaseRoleResourceMappingService.insertByRole(idArray, baseroleid);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-07-28 10:39:35
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblBaseRoleResourceMappingEntity e){
		try {
			int rows = tblBaseRoleResourceMappingService.update(e);
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
	 * @date 2017-07-28 10:39:35
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = tblBaseRoleResourceMappingService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 通过ResourceId查询角色
	 * @param idArray
	 * @return
	 */
	@RequestMapping(value = "/selectByResourceId", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByResourceId(@RequestParam("baseresourceid") String baseresourceid) {
		List<TblBaseRoleResourceMappingEntity> list = new ArrayList<TblBaseRoleResourceMappingEntity>();
		try {
			list = tblBaseRoleResourceMappingService.selectByResourceId(baseresourceid);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @Description 通过ResourceId查询角色
	 * @param idArray
	 * @return
	 */
	@RequestMapping(value = "/selectByRoleId", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByRoleId(@RequestParam("baseroleid") String baseroleid) {
		List<TblBaseRoleResourceMappingEntity> list = new ArrayList<TblBaseRoleResourceMappingEntity>();
		try {
			list = tblBaseRoleResourceMappingService.selectByRoleId(baseroleid);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return list;
	}
	
}