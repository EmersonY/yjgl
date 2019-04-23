package com.kingtopinfo.base.action;

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
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.service.YjgSjczService;

/***
 * @ClassName action.TblBaseFileAction*@Description TBL_BASE_FILE表Action类*@author cyf*@date 2017-09-19 08:50:35*@version 1.0*@remark create by generator
 */
@Controller
@RequestMapping("/TblBaseFileAction")
public class TblBaseFileAction extends BaseValidAction {
	
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	@Autowired
	private YjgSjczService		yjgSjczService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-19 08:50:35
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, TblBaseFileEntity e) {
		PaginationEntity<TblBaseFileEntity> o = new PaginationEntity<TblBaseFileEntity>();
		o.setRows(tblBaseFileService.selectPagination(e, rows, page));
		o.setTotal(tblBaseFileService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-19 08:50:35
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(TblBaseFileEntity e) {
		try {
			int rows = tblBaseFileService.insert(e);
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
	 * @date 2017-09-19 08:50:35
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblBaseFileEntity e) {
		try {
			int rows = tblBaseFileService.update(e);
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
	 * @date 2017-09-19 08:50:35
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = tblBaseFileService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/selectByOperatypeAndOperaid", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByOperatypeAndOperaid(HttpServletRequest request, String operatype, String operaid) {
		try {
			String yjxt = FilePathUtil.getFilePath("xmlj");
			List<TblBaseFileEntity> list = tblBaseFileService.selectByOperatypeAndOperaid(operatype, operaid);
			list.get(0).setFilepath(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + yjxt + list.get(0).getFilepath());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description 上传图片
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/addImg", method = RequestMethod.POST)
	@ResponseBody
	public Object addImg(HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity, YjgSjdjEntity yjgSjdjEntity, String operatype) {
		try {
			tblBaseFileService.addImg(tblBaseFileEntity.getImageFile(), request, tblBaseFileEntity, yjgSjdjEntity, resultMap, operatype);
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 替换上传视频
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/updateVedio", method = RequestMethod.POST)
	@ResponseBody
	public Object updateVedio(HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity, YjgSjdjEntity yjgSjdjEntity, String operatype) {
		try {
			tblBaseFileService.updateVedio(tblBaseFileEntity, request, yjgSjdjEntity, resultMap, operatype);
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 上传视频
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/addVedio", method = RequestMethod.POST)
	@ResponseBody
	public Object addVedio(HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity, String operatype) {
		try {
			tblBaseFileService.addVedio(tblBaseFileEntity.getVediofile(), request, new YjgSjdjEntity(), resultMap, operatype);
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 上传窨井视频
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/addYjVedio", method = RequestMethod.POST)
	@ResponseBody
	public Object addYjVedio(HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity, YjgSjdjEntity yjgSjdjEntity, String operatype) {
		try {
			tblBaseFileService.addVedio(tblBaseFileEntity.getYjVediofile(), request, yjgSjdjEntity, resultMap, operatype);
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 上传窨井图片
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/yjAddImg", method = RequestMethod.POST)
	@ResponseBody
	public Object yjAddImg(HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity, YjgSjdjEntity yjgSjdjEntity) {
		try {
			tblBaseFileService.addImg(tblBaseFileEntity.getYjImageFile(), request, tblBaseFileEntity, yjgSjdjEntity, resultMap, "YJG_IMG_FILE");
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 上传窨井图片
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/czAddImg", method = RequestMethod.POST)
	@ResponseBody
	public Object czAddImg(HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity) {
		try {
			tblBaseFileService.addImg(tblBaseFileEntity.getImageFile(), request, tblBaseFileEntity, new YjgSjdjEntity(), resultMap, "YJG_IMG_FILE");
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 查看附件
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/enclosureView", method = RequestMethod.GET)
	@ResponseBody
	public Object enclosureView(HttpServletRequest request, YjgSjdjEntity yjgSjdjEntity, int type) {
		ModelAndView modelAndView = new ModelAndView();
		String operatype = "YJG_IMG_FILE";
		String yjxt = FilePathUtil.getFilePath("xmlj");
		String operaid = "";
		if (yjgSjdjEntity.getSjdjid() != null) {
			if (type == 1) {
				operaid = yjgSjdjEntity.getSjdjid();
				modelAndView.addObject("titleName", "上报附件");
			} else if (type == 2) {
				YjgSjczEntity yjgSjczEntity = new YjgSjczEntity();
				yjgSjczEntity.setSjdjid(yjgSjdjEntity.getSjdjid());
				yjgSjczEntity.setCzzt("0");
				List<YjgSjczEntity> list = yjgSjczService.selectByCzzt(yjgSjczEntity);
				for (YjgSjczEntity e : list) {
					operaid = e.getSjczid();
				}
				modelAndView.addObject("titleName", "处置前附件");
			} else if (type == 3) {
				YjgSjczEntity yjgSjczEntity = new YjgSjczEntity();
				yjgSjczEntity.setSjdjid(yjgSjdjEntity.getSjdjid());
				yjgSjczEntity.setCzzt("2");
				List<YjgSjczEntity> list = yjgSjczService.selectByCzzt(yjgSjczEntity);
				for (YjgSjczEntity e : list) {
					operaid = e.getSjczid();
				}
				modelAndView.addObject("titleName", "处置后附件");
			}
		} else if (yjgSjdjEntity.getFyjsjdjid() != null) {
			operaid = yjgSjdjEntity.getFyjsjdjid();
			modelAndView.addObject("titleName", "查看附件");
		} else if (yjgSjdjEntity.getSjczid() != null) {
			operaid = yjgSjdjEntity.getSjczid();
			modelAndView.addObject("titleName", "查看附件");
		}
		List<TblBaseFileEntity> imgList = tblBaseFileService.selectByOperatypeAndOperaid(operatype, operaid);
		for (TblBaseFileEntity tblBaseFileEntity2 : imgList) {
			tblBaseFileEntity2.setFilepath(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + yjxt + tblBaseFileEntity2.getFilepath());
		}
		operatype = "YJG_VEDIO_FILE";
		List<TblBaseFileEntity> videoList = tblBaseFileService.selectByOperatypeAndOperaid(operatype, operaid);
		modelAndView.setViewName("/yjg/yjg_sjgl_enclosure");
		modelAndView.addObject("imgList", imgList);
		if (videoList.size() > 0) {
			TblBaseFileEntity videoEntity = videoList.get(0);
			videoEntity.setFilepath(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + yjxt + videoEntity.getFilepath());
			videoEntity.setRemark(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + yjxt + videoEntity.getRemark());
			modelAndView.addObject("videoEntity", videoEntity);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteImg", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteImg(@RequestParam("fileid") String fileid, HttpServletRequest request) {
		try {
			String dstPath = FilePathUtil.getFilePath("disk_Path") + FilePathUtil.getFilePath("yjg_Img_Path") + "/";
			int rows = tblBaseFileService.deleteFile(fileid, request, dstPath);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}