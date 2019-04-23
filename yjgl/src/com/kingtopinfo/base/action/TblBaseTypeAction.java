package com.kingtopinfo.base.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtopinfo.base.entity.TblBaseTypeEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.service.TblBaseTypeService;

@Controller
@RequestMapping("/TblBaseTypeAction")
public class TblBaseTypeAction extends BaseValidAction {
	
	@Autowired
	private TblBaseTypeService		tblBaseTypeService;
	
	@RequestMapping(value = "/selectByCode", method = RequestMethod.POST)
	@ResponseBody
	public Object selectByCode(TblBaseTypeEntity tblBaseTypeEntity) {
		List<TblBaseTypeEntity> list = tblBaseTypeService.selectByCode(tblBaseTypeEntity.getCode());
		try {
			resultMap.put("list", list);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return list;
	}
	
	@RequestMapping(value = "/selectAllByCode", method = RequestMethod.POST)
	@ResponseBody
	public Object selectAllByCode(TblBaseTypeEntity tblBaseTypeEntity) {
		return tblBaseTypeService.selectAllByCode(tblBaseTypeEntity.getCode());
	}
	
	@RequestMapping(value = "/selectMenu", method = RequestMethod.POST)
	@ResponseBody
	public Object selectMenu(TblBaseTypeEntity tBaseTypeEntity) {
		return tblBaseTypeService.selectMenu(tBaseTypeEntity);
	}
	
	@RequestMapping(value = "/moveUpOrDown", method = RequestMethod.POST)
	@ResponseBody
	public Object moveUpOrDown(String type, String checkId, Integer checkSequ, String targetId, Integer targetSequ) {
		try {
			int rows = tblBaseTypeService.moveUpOrDown(type, checkId, checkSequ, targetId, targetSequ);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(TblBaseTypeEntity tblBaseTypeEntity) {
		try {
			int rows = tblBaseTypeService.insert(tblBaseTypeEntity);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(TblBaseTypeEntity tblBaseTypeEntity) {
		try {
			int rows = tblBaseTypeService.update(tblBaseTypeEntity);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = tblBaseTypeService.deleteBatch(idArray);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteBatchLoop", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatchLoop(@RequestParam("idArray") String[] idArray) {
		try {
			int rows = tblBaseTypeService.deleteLoop(idArray);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/deletesByPid", method = RequestMethod.POST)
	@ResponseBody
	public Object deletesByPid(TblBaseTypeEntity tblBaseTypeEntity) {
		try {
			int rows = tblBaseTypeService.selectCountByPid(tblBaseTypeEntity.getBasetypepid());
			if (rows == 0) {
				rows = tblBaseTypeService.delete(tblBaseTypeEntity.getBasetypepid());
				resultMap.put("rows", rows);
				resultMap.put("result", 1);
			} else {
				resultMap.put("result", 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", 3);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/updateVerByCode", method = RequestMethod.POST)
	@ResponseBody
	public Object updateVerByCode(TblBaseTypeEntity tblBaseTypeEntity) {
		try {
			int rows = tblBaseTypeService.updateVerByCode(tblBaseTypeEntity);
			resultMap.put("rows", rows);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
		
	}
	
	@RequestMapping(value = "/selectVerByCode", method = RequestMethod.POST)
	@ResponseBody
	public Object selectVerByCode(TblBaseTypeEntity tblBaseTypeEntity) {
		try {
			int ver = tblBaseTypeService.selectVerByCode(tblBaseTypeEntity);
			resultMap.put("ver", ver);
			resultMap.put("sec", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/selectValueByCodeAndName", method = RequestMethod.POST)
	@ResponseBody
	public Object selectValueByCodeAndName(TblBaseTypeEntity tblBaseTypeEntity) {
		try {
			tblBaseTypeEntity = tblBaseTypeService.selectValueByCodeAndName(tblBaseTypeEntity);
			if (tblBaseTypeEntity != null) {
				resultMap.put("value", tblBaseTypeEntity.getValue());
				resultMap.put("attribute", tblBaseTypeEntity.getAttribute());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}