package com.kingtopinfo.base.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseUserRoleMappingEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseUserRoleMappingService;
import com.kingtopinfo.base.util.WfConstants;

/**
 * @ClassName com.kingtopinfo.base.action.TBaseUserRoleMappingAction
 * @Description T_BASE_USER_ROLE_MAPPING表Action类
 * @author dzb@kingtopinfo.com
 * @date 2014-02-18 09:21:47
 * @version 1.0
 * @remark create by generator
 */

@Controller
@RequestMapping("/TblBaseUserRoleMappingAction")
public class TblBaseUserRoleMappingAction extends BaseValidAction {
	
	@Autowired
	private TblBaseUserRoleMappingService tblBaseUserRoleMappingService;
	@Autowired
	private TblBaseLogService				tblBaseLogService;

	/**
	 * @Description:用户授权
	 * @author:cyf
	 * @time:2017年6月6日 下午3:44:20
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(TblBaseUserRoleMappingEntity e) {
		try {
			int rows = 0;
			String baseuserid = e.getBaseuserid();
			if (baseuserid != null && !"".equals(baseuserid)) {
				rows = tblBaseUserRoleMappingService.insert(e, rows);
				resultMap.put("rows", rows);
				resultMap.put("sec", true);
			} else {
				resultMap.put("sec", false);
			}
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, "账号：" + UserInfoUtil.getAccount() + "保存角色权限信息", WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description:取消用户授权
	 * @author:cyf
	 * @time:2017年6月6日 下午3:44:20
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(TblBaseUserRoleMappingEntity e) {
		try {
			int rows = 0;
			String baseuserid = e.getBaseuserid();
			if (baseuserid != null && !"".equals(baseuserid)) {
				rows = tblBaseUserRoleMappingService.deleteByRoidAndUserid(e, rows);
				resultMap.put("rows", rows);
				resultMap.put("sec", true);
			} else {
				resultMap.put("sec", false);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, "账号：" + UserInfoUtil.getAccount() + "移除角色权限信息", WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
		
	}
	
}