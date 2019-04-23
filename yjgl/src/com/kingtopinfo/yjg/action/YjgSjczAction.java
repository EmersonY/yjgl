package com.kingtopinfo.yjg.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.yjg.entity.YjgSjczEntity;
import com.kingtopinfo.yjg.service.YjgSjczService;
import com.kingtopinfo.yjg.service.YjgSjdjService;

/**
 * @ClassName action.YjgSjczAction
 * @Description YJG_SJCZ表Action类
 * @author cyf
 * @date 2017-09-12 08:50:55
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgSjczAction")
public class YjgSjczAction extends BaseValidAction {
	
	@Autowired
	private YjgSjczService		yjgSjczService;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	@Autowired
	private YjgSjdjService		yjgSjdjService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgSjczEntity e) {
		PaginationEntity<YjgSjczEntity> o = new PaginationEntity<YjgSjczEntity>();
		o.setRows(yjgSjczService.selectPagination(e, rows, page));
		o.setTotal(yjgSjczService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 通过事件ID查询处置信息
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	@RequestMapping(value = "/listBySjdjId", method = RequestMethod.POST)
	@ResponseBody
	public Object listBySjdjId(YjgSjczEntity e) {
		return yjgSjczService.selectBySjdjId(e);
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgSjczEntity e, String czsjStr, String imageIds, String vedio, String sjdjid) {
		try {
			int rows = yjgSjczService.insert(e, czsjStr, imageIds, vedio, sjdjid, 2);
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
	 * @date 2017-09-12 08:50:55
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgSjczEntity e, String czsjStr) {
		try {
			int rows = yjgSjczService.update(e, czsjStr);
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
	 * @date 2017-09-12 08:50:55
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray, HttpServletRequest request) {
		try {
			int rows = yjgSjczService.deleteBatch(idArray, request);
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
	public Object editView(String sjczid, HttpServletRequest request, String sjdjid) {
		String yjxt = FilePathUtil.getFilePath("xmlj");
		YjgSjczEntity yjgSjczEntity = yjgSjczService.getByPkey(sjczid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
		yjgSjczEntity.setCzsjStr(sdf.format(yjgSjczEntity.getCzsj()));
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setOperatype("YJG_IMG_FILE");
		tblBaseFileEntity.setOperaid(sjczid);
		List<TblBaseFileEntity> imgList = tblBaseFileService.getByOperaid(tblBaseFileEntity);
		for (TblBaseFileEntity tblBaseFileEntity2 : imgList) {
			tblBaseFileEntity2.setFilepath(FilePathUtil.getFileRealpath(tblBaseFileEntity2.getFilepath(), request));
		}
		tblBaseFileEntity.setOperatype("YJG_VEDIO_FILE");
		List<TblBaseFileEntity> VedioList = tblBaseFileService.getByOperaid(tblBaseFileEntity);
		ModelAndView modelAndView = new ModelAndView();
		if (VedioList.size() > 0) {
			TblBaseFileEntity videoEntity = VedioList.get(0);
			videoEntity.setRemark(FilePathUtil.getFileRealpath(videoEntity.getRemark(), request));
			modelAndView.addObject("videoEntity", videoEntity);
		}
		modelAndView.setViewName("/yjg/yjg_yjsj_edit");
		modelAndView.addObject(yjgSjczEntity);
		modelAndView.addObject("imgList", imgList);
		return modelAndView;
	}
	
	@RequestMapping(value = "/selectByCzzt", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByCzzt(YjgSjczEntity e) {
		e.setCzzt("2");
		List<YjgSjczEntity> list = yjgSjczService.selectByCzzt(e);
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}