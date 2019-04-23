package com.kingtopinfo.yjg.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.kingtopinfo.yjg.entity.YjgJgSjCxVo;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.service.YjgSjdjService;

/**
 * @ClassName action.YjgSjdjAction
 * @Description YJG_SJDJ表Action类
 * @author cyf
 * @date 2017-08-24 10:56:59
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgSjdjAction")
public class YjgSjdjAction extends BaseValidAction {
	
	@Autowired
	private YjgSjdjService		yjgSjdjService;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	@Autowired
	private TblBaseRoleService	tblBaseRoleService;

	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity e) {
		String baseuserid = UserInfoUtil.getBaseuserid();
		PaginationEntity<YjgSjdjEntity> o = new PaginationEntity<YjgSjdjEntity>();
		if (e.getSsdl() != null && !e.getSsdl().equals("")) {
			String[] split = e.getSsdl().split(",");
			for (int i = 0; i < split.length; i++) {
				String arr = "'" + split[i] + "'";
				split[i] = arr;
			}
			String str1 = StringUtils.join(split, ",");
			e.setSsdl(str1);
		}
		TblBaseRoleEntity tlBaseRoleEntity = tblBaseRoleService.selectRoleByUserid(baseuserid);
		if (tlBaseRoleEntity.getRolename().equals("现场施工巡查员") || tlBaseRoleEntity.getRolename().equals("维护单位管理员")) {
			e.setCkqx(tlBaseRoleEntity.getBaseroletype());
		}
		o.setRows(yjgSjdjService.selectPagination(e, rows, page));
		o.setTotal(yjgSjdjService.getCount(e));
		return o;
	}
	
	@RequestMapping(value = "/listbyjgxx", method = RequestMethod.POST)
	@ResponseBody
	public Object listbyjgxx(YjgJgSjCxVo vo) {
		List<YjgJgSjCxVo> rows = yjgSjdjService.selectByJgxxAndSjxx(vo);
		return rows;
	}
	
	/**
	 * @Description 分页查询待办任务
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/listTask", method = RequestMethod.POST)
	@ResponseBody
	public Object listTask(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity e) {
		PaginationEntity<YjgSjdjEntity> o = new PaginationEntity<YjgSjdjEntity>();
		e.setAssignee(UserInfoUtil.getBaseuserid());
		o.setRows(yjgSjdjService.selectTaskPagination(e, rows, page));
		o.setTotal(yjgSjdjService.getTaskCount(e));
		return o;
	}
	
	/**
	 * @Description 分页查询已办任务
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/listTasked", method = RequestMethod.POST)
	@ResponseBody
	public Object listTasked(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity e) {
		PaginationEntity<YjgSjdjEntity> o = new PaginationEntity<YjgSjdjEntity>();
		e.setAssignee(UserInfoUtil.getBaseuserid());
		o.setRows(yjgSjdjService.selectTaskedPagination(e, rows, page));
		o.setTotal(yjgSjdjService.getTaskedCount(e));
		return o;
	}
	
	/**
	 * @Description 分页查询已办任务
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/listBjtask", method = RequestMethod.POST)
	@ResponseBody
	public Object listBjtask(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity e) {
		PaginationEntity<YjgSjdjEntity> o = new PaginationEntity<YjgSjdjEntity>();
		e.setAssignee(UserInfoUtil.getBaseuserid());
		o.setRows(yjgSjdjService.selectBjtaskPagination(e, rows, page));
		o.setTotal(yjgSjdjService.getBjtaskCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgSjdjEntity e, String imageIds, String vedio) {
		try {
			int rows = yjgSjdjService.insert(e, imageIds, vedio, 2);
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
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgSjdjEntity e) {
		try {
			int rows = yjgSjdjService.update(e);
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
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestBody List<YjgSjdjEntity> list) {
		try {
			int rows = yjgSjdjService.deleteBatch(list);
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
	public Object merge(@RequestBody List<YjgSjdjEntity> list, String yjgSjdjId) {
		try {
			int rows = yjgSjdjService.merge(list, yjgSjdjId);
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
		return yjgSjdjService.listChildSjdj(e);
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
			int rows = yjgSjdjService.separate(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 现场确认通过
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/comfirmPass", method = RequestMethod.POST)
	@ResponseBody
	public Object comfirmPass(YjgSjdjEntity e) {
		try {
			int rows = yjgSjdjService.comfirmPass(e, 2);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 现场确认不通过
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/comfirmRefuse", method = RequestMethod.POST)
	@ResponseBody
	public Object comfirmRefuse(YjgSjdjEntity e) {
		try {
			int rows = yjgSjdjService.comfirmRefuse(e, 2);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 兜底
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/fallback", method = RequestMethod.POST)
	@ResponseBody
	public Object fallback(YjgSjdjEntity e) {
		try {
			resultMap.put("rows", yjgSjdjService.fallback(e, 2));
			resultMap.put("sec", true);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 回退任务
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/rollbackTask", method = RequestMethod.POST)
	@ResponseBody
	public Object rollbackTask(YjgSjdjEntity e, String opinion) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("opinion", opinion == null ? "" : opinion);
			e.setVariables(map);
			e.setOpinion(opinion == null ? "" : opinion);
			resultMap.put("sec", yjgSjdjService.rollbackTask(e));
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteImg", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteImg(String sjdjid, HttpServletRequest request) {
		try {
			String dstPath = FilePathUtil.getFilePath("disk_Path") + FilePathUtil.getFilePath("yjg_Img_Path") + "/";
			int rows = tblBaseFileService.deleteFile(sjdjid, request, dstPath);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
		
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description: 任务指派
	 * @author cyf
	 * @date 2017年10月17日 下午3:19:59
	 */
	@RequestMapping(value = "/taskAssign", method = RequestMethod.POST)
	@ResponseBody
	public Object taskAssign(YjgSjdjEntity yjgSjdjEntity, @Param("idArray") String[] idArray) {
		try {
			int rows = yjgSjdjService.taskAssign(yjgSjdjEntity, idArray, 2);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description: 重新派单
	 * @author cyf
	 * @date 2017年10月17日 下午3:49:20
	 */
	@RequestMapping(value = "/sendAgain", method = RequestMethod.POST)
	@ResponseBody
	public Object sendAgain(YjgSjdjEntity yjgSjdjEntity, @Param("idArray") String[] idArray, @Param("firstDeny") int firstDeny) {
		try {
			int rows = yjgSjdjService.sendAgain(yjgSjdjEntity, 2, firstDeny);
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
	public Object editView(String sjdjid, HttpServletRequest request) {
		String yjxt = FilePathUtil.getFilePath("xmlj");
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjService.getByPkey(sjdjid);
		TblBaseFileEntity tblBaseFileEntity = new TblBaseFileEntity();
		tblBaseFileEntity.setOperatype("YJG_IMG_FILE");
		tblBaseFileEntity.setOperaid(sjdjid);
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
		modelAndView.setViewName("/yjg/yjg_sjgl_yj_edit");
		modelAndView.addObject(yjgSjdjEntity);
		modelAndView.addObject("imgList", imgList);
		modelAndView.addObject("ckqx", yjgSjdjEntity.getCkqx());
		return modelAndView;
	}
	
	@RequestMapping(value = "/listHisFlow", method = RequestMethod.POST)
	@ResponseBody
	public Object listHisFlow(YjgSjdjEntity yjgSjdjEntity) {
		try {
			return yjgSjdjService.listHisFlow(yjgSjdjEntity);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/remindTip", method = RequestMethod.POST)
	@ResponseBody
	public Object femindTip() {
		try {
			resultMap = new HashMap<String, Object>();
			return yjgSjdjService.remindTip(resultMap);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/remindRecentTip", method = RequestMethod.POST)
	@ResponseBody
	public Object remindRecentTip() {
		try {
			resultMap = new HashMap<String, Object>();
			return yjgSjdjService.remindRecentTip(resultMap);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/exportExcelData", method = RequestMethod.GET)
	public void exportExcelData(@RequestParam("idArray") String[] idArray, HSSFWorkbook workbook, HttpServletResponse response, HttpServletRequest request) {
		try {
			yjgSjdjService.exportExcelData(idArray, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/exportTransferOrder", method = RequestMethod.GET)
	public void exportTransferOrder(@RequestParam("idArray") String[] idArray, HttpServletRequest request, HttpServletResponse response) {
		try {
			yjgSjdjService.exportTransferOrder(idArray, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}