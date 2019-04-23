package com.kingtopinfo.yjg.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.service.TblBaseRoleService;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.yjg.entity.YjgFyjsjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.service.YjgFyjsjdjService;

/**
 * @ClassName action.YjgFyjsjdjAction
 * @Description YJG_FYJSJDJ表Action类
 * @author cyf
 * @date 2017-08-25 15:02:06
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgFyjsjdjAction")
public class YjgFyjsjdjAction extends BaseValidAction {
	
	@Autowired
	private YjgFyjsjdjService	yjgFyjsjdjService;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	@Autowired
	private TblBaseRoleService	tblBaseRoleService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-08-25 15:02:06
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgFyjsjdjEntity e) {
		PaginationEntity<YjgFyjsjdjEntity> o = new PaginationEntity<YjgFyjsjdjEntity>();
		TblBaseRoleEntity tlBaseRoleEntity = tblBaseRoleService.selectRoleByUserid(UserInfoUtil.getBaseuserid());
		if (tlBaseRoleEntity.getRolename().equals("现场施工巡查员") || tlBaseRoleEntity.getRolename().equals("维护单位管理员")) {
			e.setCkqx(tlBaseRoleEntity.getBaseroletype());
		}
		o.setRows(yjgFyjsjdjService.selectPagination(e, rows, page));
		o.setTotal(yjgFyjsjdjService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-08-25 15:02:06
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgFyjsjdjEntity e, String imageIds, HttpServletRequest request, String vedio) {
		try {
			int rows = yjgFyjsjdjService.insert(e, imageIds, request, vedio);
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
	 * @date 2017-08-25 15:02:06
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgFyjsjdjEntity e) {
		try {
			int rows = yjgFyjsjdjService.update(e);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 合并事件
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/merge", method = RequestMethod.POST)
	@ResponseBody
	public Object merge(@RequestBody List<YjgSjdjEntity> list, String fyjsjdjid) {
		try {
			int rows = yjgFyjsjdjService.merge(list, fyjsjdjid);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 查询从属子事件
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/listChildSjdj", method = RequestMethod.POST)
	@ResponseBody
	public Object listChildSjdj(YjgSjdjEntity e) {
		PaginationEntity<YjgSjdjEntity> o = new PaginationEntity<YjgSjdjEntity>();
		o.setRows(yjgFyjsjdjService.listChildSjdj(e));
		return o;
	}
	
	/**
	 * @Description 批量脱离
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/separate", method = RequestMethod.POST)
	@ResponseBody
	public Object separate(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = yjgFyjsjdjService.separate(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/editView", method = RequestMethod.GET)
	@ResponseBody
	public Object editView(String fyjsjdjid, HttpServletRequest request) {
		String yjxt = FilePathUtil.getFilePath("xmlj");
		YjgFyjsjdjEntity yjgFyjsjdjEntity = yjgFyjsjdjService.getByPkey(fyjsjdjid);
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setOperatype("YJG_IMG_FILE");
		tblBaseFileEntity.setOperaid(fyjsjdjid);
		List<TblBaseFileEntity> imgList = tblBaseFileService.getByOperaid(tblBaseFileEntity);
		for (TblBaseFileEntity tblBaseFileEntity2 : imgList) {
			tblBaseFileEntity2.setFilepath(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + yjxt + tblBaseFileEntity2.getFilepath());
		}
		tblBaseFileEntity.setOperatype("YJG_VEDIO_FILE");
		List<TblBaseFileEntity> VedioList = tblBaseFileService.getByOperaid(tblBaseFileEntity);
		ModelAndView modelAndView = new ModelAndView();
		if (VedioList.size() > 0) {
			TblBaseFileEntity videoEntity = VedioList.get(0);
			videoEntity.setRemark(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + yjxt + videoEntity.getRemark());
			modelAndView.addObject("videoEntity", videoEntity);
		}
		modelAndView.setViewName("/yjg/yjg_sjgl_fyj_edit");
		modelAndView.addObject(yjgFyjsjdjEntity);
		modelAndView.addObject("imgList", imgList);
		modelAndView.addObject("ckqx", yjgFyjsjdjEntity.getCkqx());
		return modelAndView;
	}
	
}