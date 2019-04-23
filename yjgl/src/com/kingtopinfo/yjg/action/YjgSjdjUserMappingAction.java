package com.kingtopinfo.yjg.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;
import com.kingtopinfo.yjg.service.YjgSjdjUserMappingService;

/**
 * @ClassName action.YjgSjdjUserMappingAction
 * @Description YJG_SJDJ_USER_MAPPING表Action类
 * @author cyf
 * @date 2017-09-21 17:44:54
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgSjdjUserMappingAction")
public class YjgSjdjUserMappingAction extends BaseValidAction {
	
	@Autowired
	private YjgSjdjUserMappingService yjgSjdjUserMappingService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgSjdjUserMappingEntity e) {
		PaginationEntity<YjgSjdjUserMappingEntity> o = new PaginationEntity<YjgSjdjUserMappingEntity>();
		o.setRows(yjgSjdjUserMappingService.selectPagination(e, rows, page));
		o.setTotal(yjgSjdjUserMappingService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 权属确认
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	@RequestMapping(value = "/firstYesproperty", method = RequestMethod.POST)
	@ResponseBody
	public Object firstYesproperty(YjgSjdjUserMappingEntity e, @Param("idArray") String[] idArray) {
		try {
			int rows = yjgSjdjUserMappingService.firstYesproperty(e, idArray);
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
	 * @date 2017-09-21 17:44:54
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgSjdjUserMappingEntity e) {
		try {
			int rows = yjgSjdjUserMappingService.update(e);
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
	 * @date 2017-09-21 17:44:54
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = yjgSjdjUserMappingService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 分页查询未接件用户
	 * @author cyf
	 * @date 2017-09-07 15:25:43
	 */
	@RequestMapping(value = "/selectUnUserPagination", method = RequestMethod.POST)
	@ResponseBody
	public Object selectUnUserPagination(@RequestParam("page") Integer page, Integer rows, TblBaseUserEntity e) {
		PaginationEntity<YjgSjdjUserMappingEntity> o = new PaginationEntity<YjgSjdjUserMappingEntity>();
		o.setRows(yjgSjdjUserMappingService.selectUnUserPagination(e, rows, page));
		o.setTotal(yjgSjdjUserMappingService.getUnUserCount(e));
		return o;
	}
	
	/**
	 * @Description 分页查询已接件用户
	 * @author cyf
	 * @date 2017-09-07 15:25:43
	 */
	@RequestMapping(value = "/selectUsered", method = RequestMethod.POST)
	@ResponseBody
	public Object selectUsered(TblBaseUserEntity e, @RequestParam("type") String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		e.setType(type);
		List<TblBaseUserEntity> list = yjgSjdjUserMappingService.selectUsered(e);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * @Description 分页查询已接件用户
	 * @author cyf
	 * @date 2017-09-07 15:25:43
	 */
	@RequestMapping(value = "/yesproperty", method = RequestMethod.POST)
	@ResponseBody
	public Object yesproperty(YjgSjdjUserMappingEntity e, @Param("idArray") String[] idArray) {
		try {
			int rows = yjgSjdjUserMappingService.yesproperty(e, idArray, 2);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}