package com.kingtopinfo.base.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseMenuEntity;
import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.entity.TblBaseRoleMenuMappingEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseMenuService;
import com.kingtopinfo.base.service.TblBaseRoleMenuMappingService;
import com.kingtopinfo.base.service.TblBaseRoleService;
import com.kingtopinfo.base.util.WfConstants;

/**
 * @ClassName action.TblBaseRoleMenuMappingAction
 * @Description TBL_BASE_ROLE_MENU_MAPPING表Action类
 * @author cyf
 * @date 2017-06-08 17:03:12
 * @version 1.0
 * @remark create by generator
 */

@Controller
@RequestMapping("/TblBaseRoleMenuMappingAction")
public class TblBaseRoleMenuMappingAction extends BaseValidAction {
	
	@Autowired
	private TblBaseRoleMenuMappingService tblBaseRoleMenuMappingService;
	@Autowired
	private TblBaseRoleService				tblBaseRoleService;
	@Autowired
	private TblBaseMenuService				tblBaseMenuService;
	@Autowired
	private TblBaseLogService				tblBaseLogService;
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(TblBaseRoleMenuMappingEntity e) {
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleService.getByPkey(e.getBaseroleid());
		TblBaseMenuEntity tblBaseMenuEntity = tblBaseMenuService.getByKey(e.getBasemenuid());
		String content = "账号：" + UserInfoUtil.getAccount() + "保存菜单权限信息,角色" + tblBaseRoleEntity.getRolename() + "拥有查看菜单" + tblBaseMenuEntity.getMenuname() + "权利";
		try {
			int rows = tblBaseRoleMenuMappingService.insert(e);
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteByRoleidAndMenuid", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteByRoleidAndMenuid(TblBaseRoleMenuMappingEntity e) {
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleService.getByPkey(e.getBaseroleid());
		TblBaseMenuEntity tblBaseMenuEntity = tblBaseMenuService.getByKey(e.getBasemenuid());
		String content = "账号：" + UserInfoUtil.getAccount() + "移除菜单权限信息,角色" + tblBaseRoleEntity.getRolename() + "失去查看菜单" + tblBaseMenuEntity.getMenuname() + "权利";
		try {
			int rows = tblBaseRoleMenuMappingService.deleteByRoleidAndMenuid(e);
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
		
	}
	
}