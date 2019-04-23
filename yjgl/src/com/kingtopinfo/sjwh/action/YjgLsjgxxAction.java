package com.kingtopinfo.sjwh.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.sjwh.entity.YjgLsjgxxEntity;
import com.kingtopinfo.sjwh.service.YjgLsjgxxService;

/**
 * @ClassName action.YjgLsjgxxAction
 * @Description YJG_LSJGXX表Action类
 * @author cyf
 * @date 2017-10-19 16:19:44
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgLsjgxxAction")
public class YjgLsjgxxAction extends BaseValidAction {
	
	@Autowired
	private YjgLsjgxxService yjgLsjgxxService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgLsjgxxEntity e) {
		PaginationEntity<YjgLsjgxxEntity> o = new PaginationEntity<YjgLsjgxxEntity>();
		if (e.getLsssdl() != null && !e.getLsssdl().equals("")) {
			String[] split = e.getLsssdl().split(",");
			for (int i = 0; i < split.length; i++) {
				String arr = "'" + split[i] + "'";
				split[i] = arr;
			}
			String str1 = StringUtils.join(split, ",");
			e.setLsssdl(str1);
		}
		o.setRows(yjgLsjgxxService.selectPagination(e, rows, page));
		o.setTotal(yjgLsjgxxService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgLsjgxxEntity e) {
		try {
			int rows = yjgLsjgxxService.insert(e);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgLsjgxxEntity e) {
		try {
			int rows = yjgLsjgxxService.update(e);
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
	 * @date 2017-10-19 16:19:44
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = yjgLsjgxxService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Package com.kingtopinfo.sjwh.action
	 * @Description: 审核不通过
	 * @author cyf
	 * @date 2017年11月9日 上午11:22:02
	 */
	@RequestMapping(value = "/refuse", method = RequestMethod.POST)
	@ResponseBody
	public Object refuse(YjgLsjgxxEntity e) {
		try {
			int rows = yjgLsjgxxService.refuse(e);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Package com.kingtopinfo.sjwh.action
	 * @Description: 审核通过
	 * @author cyf
	 * @date 2017年11月9日 上午11:22:02
	 */
	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	@ResponseBody
	public Object pass(YjgLsjgxxEntity e, HttpServletRequest request) {
		try {
			resultMap = new HashMap<String, Object>();
			yjgLsjgxxService.pass(e, resultMap, request);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Package com.kingtopinfo.sjwh.action
	 * @Description: 查找
	 * @author cyf
	 * @date 2017年11月9日 上午11:22:02
	 */
	@RequestMapping(value = "/findLsjgImg", method = RequestMethod.POST)
	@ResponseBody
	public Object findLsjgImg(HttpServletRequest request,YjgLsjgxxEntity e){
		try {
			resultMap = new HashMap<String, Object>();
			resultMap = yjgLsjgxxService.findjgImg(e.getLsjgid(), resultMap, request);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
}