package com.kingtopinfo.sjwh.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.sjwh.entity.YjgJgxxEntity;
import com.kingtopinfo.sjwh.service.YjgJgxxService;
import com.kingtopinfo.sjwh.service.YjgLsjgxxService;

/**
 * @ClassName action.YjgJgxxAction
 * @Description YJG_JGXX表Action类
 * @author cyf
 * @date 2017-10-19 16:18:13
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgJgxxAction")
public class YjgJgxxAction extends BaseValidAction {
	
	@Autowired
	private YjgJgxxService yjgJgxxService;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	@Autowired
	private YjgLsjgxxService	yjgLsjgxxService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgJgxxEntity e) {
		PaginationEntity<YjgJgxxEntity> o = new PaginationEntity<YjgJgxxEntity>();
		if (e.getSsdl() != null && !e.getSsdl().equals("")) {
			String[] split = e.getSsdl().split(",");
			for (int i = 0; i < split.length; i++) {
				String arr = "'" + split[i] + "'";
				split[i] = arr;
			}
			String str1 = StringUtils.join(split, ",");
			e.setSsdl(str1);
		}
		o.setRows(yjgJgxxService.selectPagination(e, rows, page));
		o.setTotal(yjgJgxxService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 分页查询/入图状态
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/listByrtzt", method = RequestMethod.POST)
	@ResponseBody
	public Object listByrtzt(@RequestParam("page") Integer page, Integer rows, YjgJgxxEntity e) {
		PaginationEntity<YjgJgxxEntity> o = new PaginationEntity<YjgJgxxEntity>();
		o.setRows(yjgJgxxService.selectPaginationByrtzt(e, rows, page));
		o.setTotal(yjgJgxxService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgJgxxEntity e, HttpServletRequest request) {
		try {
			resultMap = yjgJgxxService.insert(e, request, resultMap);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object edit(YjgJgxxEntity e) {
		try {
			int rows = yjgJgxxService.update(e);
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
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = yjgJgxxService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 批量更新入库状态
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/updatertztBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object updaterkztBatch(@RequestBody List<YjgJgxxEntity> list) {
		try {
			int rows = yjgJgxxService.updatertztBatch(list);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 上传Excel导入数据
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/importExcelData", method = RequestMethod.POST)
	@ResponseBody
	public Object importExcelData(@RequestParam("fileList") List<MultipartFile> fileList) {
		try {
			resultMap.put("rows", yjgJgxxService.importExcelData(fileList));
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @Description 导出Excel井盖数据
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/exportExcelData", method = RequestMethod.GET)
	public void exportExcelData(@RequestParam("idArray") String[] idArray, HSSFWorkbook workbook, HttpServletResponse response, HttpServletRequest request) {
		try {
			yjgJgxxService.exportExcelData(idArray, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deleteImg", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteImg(String jgbh, HttpServletRequest request, int type) {
		try {
			YjgJgxxEntity yjgJgxxEntity = yjgJgxxService.getByJgbh(jgbh);
			resultMap.put("rows", tblBaseFileService.delegeJgImg(yjgJgxxEntity.getJgid(), request, type));
			resultMap.put("sec", true);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/addImg", method = RequestMethod.POST)
	@ResponseBody
	public Object addImg(YjgJgxxEntity e, String jgbh, HttpServletRequest request, int type) {
		try {
			resultMap = new HashMap<String, Object>();
			String jgid = yjgJgxxService.getByJgbh(jgbh).getJgid();
			String filePath = "";
			if (type == 1) {
				filePath = tblBaseFileService.saveNbImg(e, request, jgbh, jgid,resultMap);
			} else if (type == 2) {
				filePath = tblBaseFileService.saveJjImg(e, request, jgbh, jgid,resultMap);
			} else if (type == 3) {
				filePath = tblBaseFileService.saveYjImg(e, request, jgbh, jgid, resultMap);
			}
			filePath = FilePathUtil.getFileRealpath(filePath, request);
			resultMap.put("sec", true);
			resultMap.put("jgbh", jgbh);
			resultMap.put("filePath", filePath);
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
	@RequestMapping(value = "/findJgImg", method = RequestMethod.POST)
	@ResponseBody
	public Object findJgImg(HttpServletRequest request, YjgJgxxEntity e) {
		try {
			resultMap = new HashMap<String, Object>();
			resultMap = yjgLsjgxxService.findjgImg(e.getJgid(), resultMap, request);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}