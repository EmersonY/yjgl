package com.kingtopinfo.base.action;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseRoleService;
import com.kingtopinfo.base.util.WfConstants;

/**
 * @ClassName action.TblBaseRoleAction
 * @Description TBL_BASE_ROLE表Action类
 * @author cyf
 * @date 2017-06-05 14:21:42
 * @version 1.0
 * @remark create by generator
 */

@Controller
@RequestMapping("/TblBaseRoleAction")
public class TblBaseRoleAction extends BaseValidAction {
	
	@Autowired
	private TblBaseRoleService tblBaseRoleService;
	@Autowired
	private TblBaseLogService	tblBaseLogService;
	
	/**
	 * @Description 查询角色树信息
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	@RequestMapping(value = "/selectRoleTree", method = RequestMethod.POST)
	@ResponseBody
	public Object selectRoleTree() {
		List<TblBaseRoleEntity> list = tblBaseRoleService.selectRoleTree();
		return list;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		o.setRows(tblBaseRoleService.selectPaginationByRoleId(e, rows, page));
		o.setTotal(tblBaseRoleService.selectCountByRoleId(e));
		return o;
	}
	
	@RequestMapping(value = "/unList", method = RequestMethod.POST)
	@ResponseBody
	public Object unList(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e) {
		PaginationEntity<TblBaseUserEntity> o = new PaginationEntity<TblBaseUserEntity>();
		o.setRows(tblBaseRoleService.selectUnPaginationByRoleId(e, rows, page));
		o.setTotal(tblBaseRoleService.selectUnCountByRoleId(e));
		return o;
	}
	
	/**
	 * @Description 新增角色
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(@Valid TblBaseRoleEntity e, BindingResult result) {
		String content = "账号：" + UserInfoUtil.getAccount() + "新增一条角色信息,新增角色名为：" + e.getRolename();
		if (result.hasErrors()) {
			resultMap.put("valid", false);
			return valid(resultMap, result);
		}
		try {
			resultMap.put("valid", true);
			int rows = tblBaseRoleService.insert(e);
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
			return resultMap;
		} catch (Exception e2) {
			e2.printStackTrace();
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		
		return resultMap;
	}
	
	/**
	 * @Description 修改角色
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@Valid TblBaseRoleEntity e, BindingResult result) {
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleService.getByPkey(e.getBaseroleid());
		String content = "账号：" + UserInfoUtil.getAccount() + "编辑一条角色信息,编辑角色名为：" + tblBaseRoleEntity.getRolename();
		if (result.hasErrors()) {
			resultMap.put("valid", false);
			return valid(resultMap, result);
		}
		try {
			resultMap.put("valid", true);
			int rows = tblBaseRoleService.update(e, tblBaseRoleEntity);
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, content, WfConstants.SUCCESS);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		
		return resultMap;
	}
	
	/**
	 * @Description 删除角色
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(TblBaseRoleEntity e) {
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleService.getByPkey(e.getBaseroleid());
		String content = "账号：" + UserInfoUtil.getAccount() + "删除一条角色信息,删除角色名为：" + tblBaseRoleEntity.getRolename();
		try {
			tblBaseRoleService.delete(e.getBaseroleid());
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, content, WfConstants.SUCCESS);
			resultMap.put("sec", true);
		} catch (Exception e2) {
			tblBaseLogService.insertLog(WfConstants.ROLEMODULE, content, WfConstants.FAIL);
			resultMap.put("sec", false);
		}
		return resultMap;
	}

	/**
	 * @Description 改变父节点信息
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	@RequestMapping(value = "/move", method = RequestMethod.POST)
	@ResponseBody
	public Object move(TblBaseRoleEntity e) {
		return tblBaseRoleService.move(e);
	}
	
	/**
	 * @Description 改变父节点信息
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	@RequestMapping(value = "/selectAddedMenuRoles", method = RequestMethod.POST)
	@ResponseBody
	public Object selectAddedMenuRoles(String menuid) {
		return tblBaseRoleService.selectAddedMenuRoles(menuid);
	}
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-07-27 17:08:27
	 */
	@RequestMapping(value = "/selectPagination", method = RequestMethod.POST)
	@ResponseBody
	public Object selectPagination(@RequestParam("page") Integer page, Integer rows, TblBaseRoleEntity e) {
		PaginationEntity<TblBaseRoleEntity> o = new PaginationEntity<TblBaseRoleEntity>();
		o.setRows(tblBaseRoleService.selectPagination(e, rows, page));
		o.setTotal(tblBaseRoleService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 批量删除实体信息
	 * @author cyf
	 * @date 2017-09-18 09:25:29
	 */
	@RequestMapping(value = "/selectMKMenuRoles", method = RequestMethod.POST)
	@ResponseBody
	public Object selectMKMenuRoles(TblBaseRoleEntity e) {
		return tblBaseRoleService.selectMKMenuRoles(e.getBaseroleid());
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	@ResponseBody
	public Object select() {
		return tblBaseRoleService.select();
	}
	
	@RequestMapping(value = "/selectSecondRole", method = RequestMethod.POST)
	@ResponseBody
	public Object selectSecondRole(TblBaseRoleEntity e) {
		PaginationEntity<TblBaseRoleEntity> o = new PaginationEntity<TblBaseRoleEntity>();
		o.setRows(tblBaseRoleService.selectSecondRole(e));
		return o;
	}
}