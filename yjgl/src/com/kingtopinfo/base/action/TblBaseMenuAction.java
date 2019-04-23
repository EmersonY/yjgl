package com.kingtopinfo.base.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kingtopinfo.base.entity.TblBaseMenuEntity;
import com.kingtopinfo.base.entity.TblBaseMenuListEntity;
import com.kingtopinfo.base.entity.TblBaseResourceEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseMenuService;
import com.kingtopinfo.base.service.TblBaseUserService;
import com.kingtopinfo.base.util.WfConstants;
import com.kingtopinfo.yjg.entity.YjgGglEntity;
import com.kingtopinfo.yjg.service.YjgGglService;

@Controller
@RequestMapping("/main")
public class TblBaseMenuAction extends BaseValidAction {
	@Autowired
	private TblBaseMenuService	tblBaseMenuService;
	@Autowired
	private TblBaseLogService	tblBaseLogService;
	@Autowired
	private YjgGglService yjgGglService;
	@Autowired
	private TblBaseUserService	tblBaseUserService;
	

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String list(Model model, HttpSession httpSession) {
		List<TblBaseMenuListEntity> list = tblBaseMenuService.menuList(UserInfoUtil.getBaseuserid(), httpSession);
		List<YjgGglEntity>  gglList = yjgGglService.select();
		tblBaseUserService.checkFirstLogin();
		model.addAttribute("list", list);
		model.addAttribute("gglList", gglList);
		return "base/main";
	}
	
	/**
	 * @Description:查询菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午9 :33:56
	 */
	@RequestMapping(value = "/selectMenuTree", method = RequestMethod.GET)
	@ResponseBody
	public Object selectMenuTree() {
		List<TblBaseMenuListEntity> list = tblBaseMenuService.select();
		return list;
	}
	
	/**
	 * @Description:新增菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午9:33:43
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(@Valid TblBaseMenuEntity e, BindingResult result) {
		String content = "账号：" + UserInfoUtil.getAccount() + "保存一条菜单信息,新增菜单名称为：" + e.getMenuname();
		try {
			if (result.hasErrors()) {
				resultMap.put("valid", false);
				return valid(resultMap, result);
			}
			resultMap.put("valid", true);
			int rows = tblBaseMenuService.insert(e);
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			e2.printStackTrace();
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
		
	}
	
	/**
	 * @Description:修改菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午9:33:34
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblBaseMenuEntity e) {
		String content = "账号：" + UserInfoUtil.getAccount() + "编辑一条菜单信息,编辑菜单名称为：" + e.getMenuname();
		try {
			int rows = tblBaseMenuService.update(e);
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description:修改菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午9:33:34
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(TblBaseMenuEntity e) {
		String content = "账号：" + UserInfoUtil.getAccount() + "删除一条菜单信息删除菜单名称为：" + e.getMenuname();
		try {
			int rows = tblBaseMenuService.delete(e.getBasemenuid());
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.MENUMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description:更改菜单节点
	 * @author:cyf
	 * @time:2017年6月8日 上午9:33:34
	 */
	@RequestMapping(value = "/move", method = RequestMethod.POST)
	@ResponseBody
	public Object move(TblBaseMenuEntity e) {
		int rows = tblBaseMenuService.move(e);
		return rows;
	}
	
	/**
	 * @Description:更改菜单子节点
	 * @author:cyf
	 * @time:2017年6月8日 上午9:33:34
	 */
	@RequestMapping(value = "/moveUpOrDown", method = RequestMethod.POST)
	@ResponseBody
	public Object moveUpOrDown(String currentBasemenuid, Integer currentSequ, String npBasemenuid, Integer npSequ) {
		int rows = tblBaseMenuService.moveUpOrDown(currentBasemenuid, currentSequ, npBasemenuid, npSequ);
		return rows;
	}
	
	/**
	 * @Description:更改菜单顶级节点 1:上移 0:下移
	 * @author:cyf
	 * @time:2017年6月8日 上午9:33:34
	 */
	@RequestMapping(value = "/moveTopUpOrDown", method = RequestMethod.POST)
	@ResponseBody
	public Object moveTopUpOrDown(String currentBasemenuid, Integer currentSequ, Integer state) {
		Map<String, Object> map = tblBaseMenuService.moveTopUpOrDown(currentBasemenuid, currentSequ, state);
		return map;
	}

	/**
	 * @Description:退出系统日志
	 * @author:cyf
	 * @time:2017年6月22日 下午4:33:22
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	@ResponseBody
	public Object loginOut() {
		tblBaseLogService.insertLog(WfConstants.LOGIN, "账号：" + UserInfoUtil.getAccount() + "成功退出系统", WfConstants.SUCCESS);
		return true;
	}
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-07-28 09:39:57
	 */
	@RequestMapping(value = "/selectPagination", method = RequestMethod.POST)
	@ResponseBody
	public Object selectPagination(@RequestParam("page") Integer page, Integer rows, TblBaseMenuEntity e) {
		PaginationEntity<TblBaseMenuEntity> o = new PaginationEntity<TblBaseMenuEntity>();
		o.setRows(tblBaseMenuService.selectPagination(e, rows, page));
		o.setTotal(tblBaseMenuService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-07-28 09:39:57
	 */
	@RequestMapping(value = "/selectRoleTree", method = RequestMethod.POST)
	@ResponseBody
	public Object selectRoleTree() {
		List<TblBaseMenuListEntity> list = tblBaseMenuService.select();
		return list;
	}
	
	/**
	 * @Description 判断是否含有资源权限
	 * @param no
	 * @return
	 */
	public static Boolean hasNo(String no) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Object resources = session.getAttribute("resourceNoMap");
		Map<String, TblBaseResourceEntity> resourceNoMap = (Map<String, TblBaseResourceEntity>) resources;
		if (resourceNoMap.containsKey(no)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Description:查询用户权限下菜单
	 * @author:cyf
	 * @time:2017年6月8日 上午9:33:56
	 */
	@RequestMapping(value = "/selectMenuTreeByRoleId", method = RequestMethod.GET)
	@ResponseBody
	public Object selectMenuTreeByRoleId() {
		List<TblBaseMenuEntity> list = tblBaseMenuService.selectMenuTreeByRoleId();
		return list;
	}
	
	@RequestMapping(value = "/selectroleOfMenu", method = RequestMethod.GET)
	@ResponseBody
	public Object selectroleOfMenu(String roleId) {
		return tblBaseMenuService.selectroleOfMenu(roleId);
	}
	
	/**
	 * @Package com.kingtopinfo.base.action
	 * @Description: TODO
	 * @author cyf
	 * @date 2017年11月21日 下午2:10:02
	 */
	@RequestMapping(value = "/timeOut", method = RequestMethod.POST)
	@ResponseBody
	public Object timeOut() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == null || principal instanceof  java.lang.String) {
			return true;
		} else {
			return false;
		}
	}
	
}
