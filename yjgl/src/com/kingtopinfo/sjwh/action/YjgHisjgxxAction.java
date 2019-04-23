package com.kingtopinfo.sjwh.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.sjwh.entity.YjgHisjgxxEntity;
import com.kingtopinfo.sjwh.service.YjgHisjgxxService;

/**
 * @ClassName action.YjgHisjgxxAction
 * @Description YJG_HISJGXX表Action类
 * @author cyf
 * @date 2017-10-19 16:19:36
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgHisjgxxAction")
public class YjgHisjgxxAction extends BaseValidAction {
	
	@Autowired
	private YjgHisjgxxService yjgHisjgxxService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgHisjgxxEntity e) {
		PaginationEntity<YjgHisjgxxEntity> o = new PaginationEntity<YjgHisjgxxEntity>();
		if (e.getHisssdl() != null && !e.getHisssdl().equals("")) {
			String[] split = e.getHisssdl().split(",");
			for (int i = 0; i < split.length; i++) {
				String arr = "'" + split[i] + "'";
				split[i] = arr;
			}
			String str1 = StringUtils.join(split, ",");
			e.setHisssdl(str1);
		}
		o.setRows(yjgHisjgxxService.selectPagination(e, rows, page));
		o.setTotal(yjgHisjgxxService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-10-19 16:19:36
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgHisjgxxEntity e) {
		try {
			int rows = yjgHisjgxxService.update(e);
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
	 * @date 2017-10-19 16:19:36
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = yjgHisjgxxService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}