package com.kingtopinfo.yjg.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.kingtopinfo.base.ext.PaginationEntity;
import com.kingtopinfo.yjg.entity.YjgGglEntity;
import com.kingtopinfo.yjg.service.YjgGglService;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.util.AppPush;
import com.kingtopinfo.base.util.AppPushUtils;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.JsonUtil;

/**
 * @ClassName action.YjgGglAction
 * @Description YJG_GGL表Action类
 * @author cyf
 * @date 2017-12-06 09:07:28
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjgGglAction")
public class YjgGglAction extends BaseValidAction{
	
	@Autowired
	private YjgGglService yjgGglService;
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgGglEntity e){
		PaginationEntity<YjgGglEntity> o = new PaginationEntity<YjgGglEntity>();
		o.setRows(yjgGglService.selectPagination(e, rows, page));
		o.setTotal(yjgGglService.getCount(e));
		return o;
	}
	
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(YjgGglEntity e){
		try {
			String gglid = IDUtil.getId();
			e.setGglid(gglid);
			int rows = yjgGglService.insert(e);
			if(rows >0){
		        Map<String, String> msg = new HashMap<>();  
		        msg.put("title", "收到一条新公告!");  
		        msg.put("titleText", "公告："+e.getGglbt());  
		        Map<String, String> m = new HashMap<String,String>();
			    m.put("pushl", "1");
			    m.put("gglid", gglid);		    			         
		        msg.put("transText", JsonUtil.toJson(m).toString());  
		        AppPushUtils pushUtils = new AppPushUtils(AppPush.appId, AppPush.appKey, AppPush.masterSecret);  
		        IPushResult ret =  pushUtils.pushMsg1(msg);  
		        System.out.println(ret.getResponse().toString()); 
			}
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
	/**
	 * @Description 显示实体修改信息
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */
	@RequestMapping(value = "/showedit", method = RequestMethod.GET)
	public Object showupdate(Model model,YjgGglEntity e){
		e = yjgGglService.getByPkey(e.getGglid());
		model.addAttribute("e", e);
		return "xtgj/xtgj_ggl_edit_textarea";
	}
	
	/**
	 * @Description 显示实体修改信息
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */
	@RequestMapping(value = "/showGglAppByGglid", method = RequestMethod.GET)
	public Object showGglAppByGglid(Model model,YjgGglEntity e){
		e = yjgGglService.getByPkey(e.getGglid());
		model.addAttribute("gl", e);
		return "xtgj/xtgj_ggl_app";
	}
	
	/**
	 * @Description 显示实体修改信息
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */
	@RequestMapping(value = "/showgglapp", method = RequestMethod.GET)
	public Object showgglapp(Model model){
		List<YjgGglEntity> glList = yjgGglService.select();
		YjgGglEntity gl = null;
		if(glList.size()>0){
			gl=glList.get(0);
		}
		model.addAttribute("gl", gl);
		return "xtgj/xtgj_ggl_app";
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-12-06 09:07:28
	 */	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object update(YjgGglEntity e){
		try {
			int rows = yjgGglService.update(e);
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
	 * @date 2017-12-06 09:07:28
	 */	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@RequestParam("idArray") String[] idArray){
		try {
			int rows = yjgGglService.deleteBatch(idArray);
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("sec", false);
		}
		return resultMap;
	}
	
}