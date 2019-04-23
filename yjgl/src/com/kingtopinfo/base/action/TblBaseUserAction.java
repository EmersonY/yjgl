package com.kingtopinfo.base.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserExtEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.RefreshResourceService;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseRoleService;
import com.kingtopinfo.base.service.TblBaseUserRoleMappingService;
import com.kingtopinfo.base.service.TblBaseUserService;
import com.kingtopinfo.base.util.WfConstants;

@Controller
@RequestMapping("/TblBaseUserAction")
public class TblBaseUserAction extends BaseValidAction {
	
	@SuppressWarnings("unused")
	private static Logger		l	= Logger.getLogger(TblBaseUserAction.class);
	
	@Autowired
	private TblBaseUserService	tblBaseUserService;
	@Autowired
	private TblBaseLogService	tblBaseLogService;
	@Autowired
	private TblBaseUserRoleMappingService	tblBaseUserRoleMappingService;
	@Autowired
	private RefreshResourceService			refreshResourceService;
	@Autowired
	private TblBaseRoleService				tblBaseRoleService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		if (!tblBaseRoleService.checkSuperUser()) {
			e.setPbaseuserid(UserInfoUtil.getBaseuserid());
		}
		o.setRows(tblBaseUserService.selectPagination(e, rows, page));
		o.setTotal(tblBaseUserService.count(e));
		return o;
	}
	
	@RequestMapping(value = "/listSecondUser", method = RequestMethod.POST)
	@ResponseBody
	public Object listSecondUser(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e, @RequestParam("type") Integer type) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		if (type == 1) {
			o.setRows(new ArrayList<TblBaseUserEntity>());
			o.setTotal(0);
		}
		if (type == 2) {
			o.setRows(tblBaseUserService.selectPagination(e, rows, page));
			o.setTotal(tblBaseUserService.count(e));
		}
		return o;
	}
	
	@RequestMapping(value = "/unsecondlist", method = RequestMethod.POST)
	@ResponseBody
	public Object unsecondlist(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		o.setRows(tblBaseUserService.selectUnsecondPagination(e, rows, page));
		o.setTotal(tblBaseUserService.unsecondCount(e));
		return o;
	}
	
	/**
	 * @Description:新增用户
	 * @author:cyf
	 * @time:2017年6月2日 上午10:12:32
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Valid TblBaseUserEntity e, BindingResult result) {
		String content = "账号：" + UserInfoUtil.getAccount() + "保存一条用户信息,新增账号为：" + e.getAccount();
		try {
			TblBaseUserEntity tBaseUserEntity = tblBaseUserService.selectByAccount(e.getAccount());
			if (tBaseUserEntity != null) {
				resultMap.put("unique", false);
				return resultMap;
			} else {
				if (result.hasErrors()) {
					resultMap.put("valid", false);
					return valid(resultMap, result);
				}
				tblBaseUserService.insert(e);
				tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.SUCCESS);
				resultMap.put("valid", true);
				resultMap.put("unique", true);
				resultMap.put("sec", true);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	/**
	 * @Description:修改用户
	 * @author:cyf
	 * @time:2017年6月2日 上午10:12:32
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object edit(TblBaseUserEntity e) {
		Map<String, Object> model = new HashMap<String, Object>();
		TblBaseUserEntity tBaseUserEntity = tblBaseUserService.getByPkey(e.getBaseuserid());
		String content = "账号：" + UserInfoUtil.getAccount() + "编辑一条用户信息,编辑账号为：" + tBaseUserEntity.getAccount();
		try {
			tblBaseUserService.update(e, tBaseUserEntity);
			tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.SUCCESS);
			model.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.FAIL);
			model.put("sec", false);
		}
		
		return model;
	}
	
	/**
	 * @Description:批量删除实体信息
	 * @author:cyf
	 * @throws UnsupportedEncodingException 
	 * @time:2017年6月1日 下午2:35:26
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray, @RequestParam("accountArray") String[] accountArray) throws UnsupportedEncodingException {
		String account = "";
		for (String string : accountArray) {
			account += new String(string.getBytes("ISO-8859-1"), "UTF-8");
		}
		String content = "账号：" + UserInfoUtil.getAccount() + "删除用户信息,删除账号为：" + account;
		try {
			int i = tblBaseUserService.deleteBatch(idArray);
			tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", i);
			resultMap.put("sec", true);
		} catch (Exception e) {
			tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @Description:通过主键查询用户
	 * @author:cyf
	 * @time:2017年6月1日 下午2:35:26
	 */
	@RequestMapping(value = "/getByPkey", method = RequestMethod.GET)
	@ResponseBody
	public Object getByPkey(@RequestParam("baseuserid") String baseuserid, HttpServletRequest request) {
		TblBaseUserEntity tblBaseUserEntity = tblBaseUserService.getByPkey(baseuserid);
		return tblBaseUserEntity;
	}
	
	/**
	 * @Description:重置密码
	 * @author:cyf
	 * @time:2017年6月1日 下午2:35:26
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePassword(TblBaseUserEntity e) {
		TblBaseUserEntity tBaseUserEntity = tblBaseUserService.getByPkey(e.getBaseuserid());
		String content = "账号：" + UserInfoUtil.getAccount() + "重置密码,重置密码账号为：" + tBaseUserEntity.getAccount();
		try {
			int i = tblBaseUserService.updatePassword(e);
			tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", i);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		
		return resultMap;
	}
	
	/**
	 * @Description:查询账户信息
	 * @author:cyf
	 * @time:2017年6月14日 上午11:24:44
	 */
	@RequestMapping(value = "/selectUserMsgById", method = RequestMethod.GET)
	@ResponseBody
	public Object selectUserMsgById(TblBaseUserEntity e) {
		List<Map<String, Object>> list = tblBaseUserService.selectUserMsgById(UserInfoUtil.getBaseuserid());
		return list;
	}
	
	/**
	 * @Description:更新账户信息
	 * @author:cyf
	 * @time:2017年6月14日 上午11:24:44
	 */
	@RequestMapping(value = "/updateMsgById", method = RequestMethod.POST)
	@ResponseBody
	public Object updateMsgById(TblBaseUserEntity tblBaseUserEntity, TblBaseUserExtEntity tblBaseUserExtEntity) {
		int rows = tblBaseUserService.updateMsgById(UserInfoUtil.getBaseuserid(), tblBaseUserEntity, tblBaseUserExtEntity);
		return rows;
	}
	
	/**
	 * @Description 查询未添加用户
	 * @author cyf
	 * @date 2017-08-21 14:59:04
	 */
	@RequestMapping(value = "/selectUnAddUsersPagination", method = RequestMethod.POST)
	@ResponseBody
	public Object selectUnAddUsersPagination(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity tblBaseUserEntity) {
		try {
			PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
			o.setRows(tblBaseUserRoleMappingService.selectUnAddUsersPagination(tblBaseUserEntity, rows, page));
			o.setTotal(tblBaseUserRoleMappingService.selectUnAddUsersCount(tblBaseUserEntity));
			return o;
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Package com.kingtopinfo.base.action
	 * @Description: 刷新数据
	 * @author cyf
	 * @date 2017年9月15日 下午5:20:56
	 */
	@RequestMapping(value = "/refreshCache", method = RequestMethod.POST)
	@ResponseBody
	public Object refreshCache() {
		return refreshResourceService.refreshCache();
	}
	
	/**
	 * @Package com.kingtopinfo.base.action
	 * @Description: 查询当前用户下的子用户
	 * @author cyf
	 * @date 2017年10月13日 上午9:35:02
	 */
	@RequestMapping(value = "/listByPuserId", method = RequestMethod.POST)
	@ResponseBody
	public Object listByPuserId(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		e.setPbaseuserid(UserInfoUtil.getBaseuserid());
		o.setRows(tblBaseUserService.selectByuserIdPagination(e, rows, page));
		o.setTotal(tblBaseUserService.getCountByuserId(e));
		return o;
	}
	
	/**
	 * @Package com.kingtopinfo.base.action
	 * @Description: 查询当前用户下的非子用户
	 * @author cyf
	 * @date 2017年10月13日 上午9:35:02
	 */
	@RequestMapping(value = "/listByUnPuserId", method = RequestMethod.POST)
	@ResponseBody
	public Object listByUnPuserId(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		e.setPbaseuserid(UserInfoUtil.getBaseuserid());
		o.setRows(tblBaseUserService.selectByUnUserIdPagination(e, rows, page));
		o.setTotal(tblBaseUserService.getCountByUnUserId(e));
		return o;
	}
	
	/**
	 * @Package com.kingtopinfo.base.action
	 * @Description: 为当前用户添加子用户
	 * @author cyf
	 * @date 2017年10月13日 上午9:35:02
	 */
	@RequestMapping(value = "/addPuser", method = RequestMethod.POST)
	@ResponseBody
	public Object addPuser(TblBaseUserEntity e) {
		try {
			resultMap.put("rows", tblBaseUserService.addPuser(e));
			resultMap.put("sec", true);
		} catch (Exception e2) {
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Package com.kingtopinfo.base.action
	 * @Description: 为当前用户删除子用户
	 * @author cyf
	 * @date 2017年10月13日 上午9:35:02
	 */
	@RequestMapping(value = "/deletePuser", method = RequestMethod.POST)
	@ResponseBody
	public Object deletePuser(TblBaseUserEntity e) {
		try {
			resultMap.put("rows", tblBaseUserService.deletePuser(e));
			resultMap.put("sec", true);
		} catch (Exception e2) {
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/updatePuserid", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePuserid(@RequestParam("idArray") String[] idArray, @RequestParam("pbaseuserid") String pbaseuserid) {
		try {
			resultMap.put("rows", tblBaseUserService.updatePuserid(idArray, pbaseuserid));
			resultMap.put("sec", true);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
		
	}
	
	@RequestMapping(value = "/importUserExcelData", method = RequestMethod.POST)
	@ResponseBody
	public Object importUserExcelData(@RequestParam("fileList") List<MultipartFile> fileList) throws Exception {
		try {
			tblBaseUserService.importUserExcelData(fileList);
			resultMap.put("rows", 1);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
		
	}
	
	@RequestMapping(value = "/checkUserAcitve", method = RequestMethod.POST)
	@ResponseBody
	public Object checkUserAcitve(String account) throws Exception {
		return tblBaseUserService.selectUserByAccount(account);
	}
}
