package com.kingtopinfo.yjg.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.yjg.entity.YjgTxlEntity;
import com.kingtopinfo.yjg.service.YjgTxlService;

/**
 * @ClassName action.YjgTxlAction
 * @Description YJG_TXL表Action类
 * @author cyf
 * @date 2017-12-04 09:16:38
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgTxlAction")
public class YjgTxlAction extends BaseValidAction{
	
	@Autowired
	private YjgTxlService yjgTxlService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgTxlEntity e){
		PaginationEntity<YjgTxlEntity> o = new PaginationEntity<YjgTxlEntity>();
		e.setBaseuserid(UserInfoUtil.getBaseuserid());
		o.setRows(yjgTxlService.selectPagination(e, rows, page));
		o.setTotal(yjgTxlService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-12-04 09:16:38
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgTxlEntity e){
		try {
			int rows = yjgTxlService.insert(e);
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
	 * @date 2017-12-04 09:16:38
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgTxlEntity e){
		try {
			int rows = yjgTxlService.update(e);
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
	 * @date 2017-12-04 09:16:38
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = yjgTxlService.deleteBatch(idArray);
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
	@RequestMapping(value = "/importTxlExcelData", method = RequestMethod.POST)
	@ResponseBody
	public Object importTxlExcelData(@RequestParam("fileList") List<MultipartFile> fileList) {
		try {
			resultMap.put("rows", yjgTxlService.importTxlExcelData(fileList));
			resultMap.put("sec", true);
		} catch (Exception e) {
			resultMap.put("sec", false);
			e.printStackTrace();
		}
		return resultMap;
	}
	
}