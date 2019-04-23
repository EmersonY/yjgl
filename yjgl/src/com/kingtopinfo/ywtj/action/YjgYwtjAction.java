package com.kingtopinfo.ywtj.action;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.service.TblBaseTypeService;
import com.kingtopinfo.echarts.Option;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgYwtjEntity;
import com.kingtopinfo.yjg.service.YjgSjdjService;
import com.kingtopinfo.ywtj.service.YjgYwtjService;

/**
 * @Package com.kingtopinfo.ywtj.action
 * @Description: TODO
 * @author cyf
 * @date 2017年10月25日 上午10:50:51
 */
@Controller
@RequestMapping("/YjgYwtjAction")
public class YjgYwtjAction {
	
	@Autowired
	private YjgYwtjService	yjgYwtjService;
	@Autowired
	private YjgSjdjService	yjgSjdjService;
	@Autowired
	private TblBaseTypeService	tblBaseTypeService;
	
	private Option			option;
	
	public Option getOption() {
		return option;
	}
	
	public void setOption(Option option) {
		this.option = option;
	}
	
	/**
	 * 窨井业务来源统计
	 *
	 * @return
	 */
	@RequestMapping(value = "/loadYwlyBar", method = RequestMethod.POST)
	@ResponseBody
	public Object loadYwlyBar(YjgYwtjEntity ywtj) {
		YjgYwtjEntity yjgYwtjEntity = yjgYwtjService.loadYwlyBar(ywtj);
		return yjgYwtjEntity;
	}
	
	/**
	 * 窨井业务井盖统计
	 */
	@RequestMapping(value = "/loadJgtjGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object loadJgtjGrid(YjgYwtjEntity ywtj) {
		YjgYwtjEntity whjhGrid = yjgYwtjService.loadJgtjGrid(ywtj);
		return whjhGrid;
	}
	
	/**
	 * 按性质类型柱状图
	 */
	@RequestMapping(value = "/loadSjlxBar", method = RequestMethod.POST)
	@ResponseBody
	public Object loadSjlxBar(YjgYwtjEntity ywtj) {
		try {
			YjgYwtjEntity tjgYwtjEntity = yjgYwtjService.loadSjlxBar(ywtj);
			return tjgYwtjEntity;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 按性质类型柱状图110
	 */
	@RequestMapping(value ="/loadSjlxlyBar", method = RequestMethod.POST)
	@ResponseBody
	public Object loadSjlxlyBar(YjgYwtjEntity ywtj) {
		try {
			YjgYwtjEntity tjgYwtjEntity = yjgYwtjService.loadSjlxlyBar(ywtj);
			return tjgYwtjEntity;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 按窨井道路统计
	 */
	@RequestMapping(value = "/loadDltjGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object loadDltjGrid(YjgYwtjEntity ywtj) {
		try {
			YjgYwtjEntity tjgYwtjEntity = yjgYwtjService.loadDltjGrid(ywtj);
			return tjgYwtjEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按窨井类型统计
	 */
	@RequestMapping(value = "/loadJglxGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object loadJglxGrid(YjgYwtjEntity ywtj) {
		try {
			YjgYwtjEntity tjgYwtjEntity = yjgYwtjService.loadJglxGrid(ywtj);
			return tjgYwtjEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loadJgtjDetailGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object loadJgtjDetailGrid(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity e) {
		PaginationEntity<YjgSjdjEntity> o = new PaginationEntity<YjgSjdjEntity>();
		o.setRows(yjgYwtjService.selectYwtjPagination(e, rows, page));
		o.setTotal(yjgYwtjService.getJgtjDetailCount(e));
		return o;
	}

	@RequestMapping(value = "/loadsjczqkDetailGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object loadsjczqkDetailGrid(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity yjgSjdjEntity) {
		PaginationEntity<YjgSjdjEntity> o = new PaginationEntity<YjgSjdjEntity>();
		o.setRows(yjgSjdjService.selectJglxPagination(yjgSjdjEntity, rows, page));
		o.setTotal(yjgSjdjService.getJglxCount(yjgSjdjEntity));
		return o;
	}
}