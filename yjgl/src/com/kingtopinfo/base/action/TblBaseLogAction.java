package com.kingtopinfo.base.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseLogEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.service.TblBaseLogService;

/**
 * @ClassName com.kingtopinfo.base.action.TblBaseLogAction
 * @Description TBL_BASE_LOG表Action类
 * @author cyf
 * @date 2017-03-08 16:57:11
 * @version 1.0
 * @remark create by generator
 */

@Controller
@RequestMapping("/TblBaseLogAction")
public class TblBaseLogAction extends BaseValidAction {
	
	@Autowired
	private TblBaseLogService tblBaseLogService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblBaseLogEntity e) {
		PaginationEntity<TblBaseLogEntity> o = new PaginationEntity<TblBaseLogEntity>();
		o.setRows(tblBaseLogService.selectPagination(e, rows, page));
		o.setTotal(tblBaseLogService.selectCount(e));
		return o;
	}
	
}