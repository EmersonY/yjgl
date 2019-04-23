package com.kingtopinfo.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.activiti.service.ActTaskService;
import com.kingtopinfo.activiti.service.ActWorkFlowService;
import com.kingtopinfo.app.entity.YjgAppversionEntity;
import com.kingtopinfo.app.service.YjgAppSjdjService;
import com.kingtopinfo.base.entity.TblBaseFileEntity;
import com.kingtopinfo.base.entity.TblBaseImageEntity;
import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserExtEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.service.TblBaseRoleService;
import com.kingtopinfo.base.service.TblBaseUserService;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.mapUtil;
import com.kingtopinfo.sjwh.entity.YjgLsjgxxEntity;
import com.kingtopinfo.sjwh.service.YjgJgxxService;
import com.kingtopinfo.sjwh.service.YjgLsjgxxService;
import com.kingtopinfo.yjg.entity.YjgAppTypeEntity;
import com.kingtopinfo.yjg.entity.YjgGglEntity;
import com.kingtopinfo.yjg.entity.YjgGrxxEntity;
import com.kingtopinfo.yjg.entity.YjgSjczEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;
import com.kingtopinfo.yjg.entity.YjgSjqqEntity;
import com.kingtopinfo.yjg.entity.YjgTxlEntity;
import com.kingtopinfo.yjg.service.YjgGglService;
import com.kingtopinfo.yjg.service.YjgSjczService;
import com.kingtopinfo.yjg.service.YjgSjdjService;
import com.kingtopinfo.yjg.service.YjgSjdjUserMappingService;
import com.kingtopinfo.yjg.service.YjgSjqqService;


/**
 * @ClassName action.YjgFyjsjdjAction
 * @Description YJG_FYJSJDJ表Action类
 * @author cyf
 * @date 2017-08-25 15:02:06
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjglApp_SjdjAction")
public class YjglAppSjdjAction extends BaseValidAction {
	
	@Autowired
	YjgAppSjdjService			yjgAppSjdjService;
	@Autowired
	TblBaseUserService tblBaseUserService;
	@Autowired
	YjgSjdjService				yjgSjdjService;
	@Autowired
	YjgSjdjUserMappingService	yjgSjdjUserMappingService;
	@Autowired
	ActTaskService				actTaskService;
	@Autowired
	YjgSjqqService				yjgSjqqService;
	@Autowired
	YjgSjczService				yjgSjczService;
	@Autowired
	ActWorkFlowService			actWorkFlowService;
	@Autowired
	YjgJgxxService				yjgJgxxService;
	@Autowired
	TblBaseFileService			tblBaseFileService;
	@Autowired
	YjgLsjgxxService			yjgLsjgxxService;
	@Autowired
	YjgGglService			yjgGglService;
	@Autowired
	private Memory memory;
	@Autowired
	private TblBaseRoleService tblBaseRoleService;
	

	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity e,HttpServletRequest request) {
		TblBaseRoleEntity tlBaseRoleEntity = tblBaseRoleService.selectRoleByUserid(e.getBaseuserid());
		if (tlBaseRoleEntity.getRolename().equals("现场施工巡查员") || tlBaseRoleEntity.getRolename().equals("维护单位管理员")) {
			e.setCkqx(tlBaseRoleEntity.getBaseroletype());
		}

		Integer total = yjgAppSjdjService.getCount(e);
		if (((page - 1) * rows) >= total) {
			if(total==0){
				return mapUtil.setMaputil200("请求成功", null, null);
			}else{
				return mapUtil.setMaputil402("没有更多数据了", null);
			}
			
		} else {
			List<YjgSjdjEntity> list = yjgAppSjdjService.selectPagination(e, rows, page);
			if (list.size() != 0) {
				return mapUtil.setMaputil200("请求成功", request.getSession().getId(), list);
			} else {
				return mapUtil.setMaputil200("请求成功，数据为空", null, null);
			}
		}
	}
	
	
	/**
	 * @Description 
	 * 临时井盖删除
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/SaveOrUpdateGrxx", method = RequestMethod.POST)
	@ResponseBody
	public Object SaveOrUpdateGrxx(TblBaseUserExtEntity e) {
		
		int rows= yjgAppSjdjService.SaveOrUpdateGrxx(e);
		if (rows> 0) {
			return mapUtil.setMaputil200("请求成功", null, null);
		} else {
			return mapUtil.setMaputil200("请求成功，数据为空", null, null);
		}
		
	}
	@RequestMapping(value = "/selectbwlforTimeanduser", method = RequestMethod.POST)
	@ResponseBody
	public Object selectbwlforTimeanduser(YjgTxlEntity e) {
		try {
			
//			e.setBwldate(TimeUtils.formatyyyyMMdd(bwldate1));
			List<YjgTxlEntity> list= yjgAppSjdjService.selectbwlforTimeanduser(e);
			if (list != null) {
				return mapUtil.setMaputil200("请求成功", null, list);
			} else {
				return mapUtil.setMaputil200("请求成功，数据为空", null, null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return  mapUtil.setMaputil400("数据异常", null);
		}
		
	}
	
	/**
	 * @Description 查询基本信息
	 * @author zqq
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/getTypelist", method = RequestMethod.POST)
	@ResponseBody
	public Object getTypelist(@RequestParam("code") String code) {
		YjgAppTypeEntity en = new YjgAppTypeEntity();
		en.setNamelist(yjgAppSjdjService.getTypename(code));
		en.setValuelist(yjgAppSjdjService.getTypeValue(code));
		return mapUtil.setMaputil200("请求成功", null, en);
		// if(en !=null){
		// return mapUtil.setMaputil200("请求成功", null,en);
		// }else{
		// return mapUtil.setMaputil200("请求成功，数据为空",null,null);
		// }
		
	}
	
	/**
	 * @Description 更改密码
	 * @author zqq
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/updateps", method = RequestMethod.POST)
	@ResponseBody
	public Object updateps(TblBaseUserEntity en) {
		try {
//			TblBaseUserEntity oTBaseUserEntity = tblBaseUserService.selectByAccount(en.getAccount());
			
			int row = yjgAppSjdjService.updateps(en);
			if(row ==1 ){
				return  mapUtil.setMaputil200("请求成功", null, null);
			}else{
				return  mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return  mapUtil.setMaputil400("数据异常", null);
		}
	}
	
	
	/**
	 * @Description 井盖分页查询
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/jgxxList", method = RequestMethod.POST)
	@ResponseBody
	public Object jgxxList(@RequestParam("page") Integer page, Integer rows, YjgLsjgxxEntity e) {

		Integer total = yjgLsjgxxService.getCount(e);
		if (((page - 1) * rows) >= total) {
			if(total==0){
				return mapUtil.setMaputil200("请求成功", null, null);
			}else{
				return mapUtil.setMaputil402("没有更多数据了", null);
			}
		} else {
			List<YjgLsjgxxEntity> list = yjgLsjgxxService.selectPagination(e, rows, page);
			if (list.size() != 0) {
				return mapUtil.setMaputil200("请求成功", null, list);
			} else {
				return mapUtil.setMaputil200("请求成功，数据为空", null, null);
			}
		}
	}
	/**
	 * @Description 
	 * 临时井盖删除
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/deletejgxx", method = RequestMethod.POST)
	@ResponseBody
	public Object deletejgxx(YjgLsjgxxEntity e) {
		
		int rows= yjgLsjgxxService.delete(e.getLsjgid());
		if (rows> 0) {
			return mapUtil.setMaputil200("请求成功", null, null);
		} else {
			return mapUtil.setMaputil200("请求成功，数据为空", null, null);
		}
		
	}
	@RequestMapping(value = "/listHisFlow", method = RequestMethod.POST)
	@ResponseBody
	public Object listHisFlow(YjgSjdjEntity yjgSjdjEntity) {
		try {
			List<ActTaskEntity> list = yjgSjdjService.listHisFlow(yjgSjdjEntity);
			 if (list!=null) {
					return mapUtil.setMaputil200("请求成功", null, list);
				} else {
					return mapUtil.setMaputil200("请求成功，数据为空", null, null);
				}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil200("请求数据异常", null, null);
		}
	}
	
	/**
	 * @Description 井盖分页查询id
	 * @author cyf
	 * @date 2017-10-19 16:18:13
	 */
	@RequestMapping(value = "/getOnejgxx", method = RequestMethod.POST)
	@ResponseBody
	public Object getOnejgxx(YjgLsjgxxEntity e,HttpServletRequest request) {
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
			
		YjgLsjgxxEntity jgxx= yjgLsjgxxService.getByPkey(e.getLsjgid());
		if (jgxx != null) {
			if(jgxx.getJnzp()!=null && jgxx.getJnzp().size() !=0){
				for (TblBaseImageEntity en : jgxx.getJnzp()) {
					en.setPath(url + en.getPath());
				}
			}
			if(jgxx.getJmzp()!=null && jgxx.getJnzp().size() !=0){
				for (TblBaseImageEntity en1 : jgxx.getJmzp()) {
					en1.setPath(url + en1.getPath());
				}
			}
			if(jgxx.getJgzbzp()!=null && jgxx.getJnzp().size() !=0){
				for (TblBaseImageEntity en2 : jgxx.getJgzbzp()) {
					en2.setPath(url + en2.getPath());
				}
			}

			return mapUtil.setMaputil200("请求成功", null, jgxx);
		} else {
			return mapUtil.setMaputil200("请求成功，数据为空", null, null);
		}
		
	}
	/**
	 * @Description 查询基本信息
	 * @author zqq
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/getJbEntity", method = RequestMethod.POST)
	@ResponseBody
	public Object getJbEntity(YjgSjdjEntity e, HttpServletRequest request) {
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
		YjgSjdjEntity en = yjgAppSjdjService.getJbEntity(e);
		
		if (en != null) {
			List<YjgSjdjEntity> list = new ArrayList<YjgSjdjEntity>();
			YjgSjdjEntity en1 = new YjgSjdjEntity();
			en1 = en;
			en1.setItemType(1);
			list.add(en1);
			YjgSjdjEntity en2 = new YjgSjdjEntity();
			en2.setXxlyname(en.getXxlyname());
			en2.setJglx(en.getJglx());
			en2.setYzjb(en.getYzjb());
			en2.setCkqx(en.getCkqx());
			en2.setSbrxm(en.getSbrxm());
			en2.setXxly(en.getXxly());
			en2.setBz(en.getBz());
			en2.setJgid(en.getJgid());
			en2.setItemType(2);
			list.add(en2);
			e.setOperatype("YJG_IMG_FILE");
			List<TblBaseImageEntity> ylist1 = yjgAppSjdjService.getJbxxZp(e);
			for (TblBaseImageEntity ien : ylist1) {
				if (ien.getName() != null && "".equals(ien.getName()) == false) {
					ien.setName(url + ien.getName());
				}
				ien.setPath(url + ien.getPath());
			}
			if (e.getItemType() == 1) {
				if (ylist1 != null && ylist1.size() != 0) {
					YjgSjdjEntity en3 = new YjgSjdjEntity();
					en3.setItemType(3);
					en3.setXckctp(ylist1);
					list.add(en3);
				}
			} else {
				if (ylist1 != null && ylist1.size() != 0) {
					YjgSjdjEntity en3 = new YjgSjdjEntity();
					en3.setItemType(3);
					en3.setXckctp(ylist1);
					list.add(en3);
				} else {
					YjgSjdjEntity en3 = new YjgSjdjEntity();
					en3.setItemType(3);
					list.add(en3);
				}
				
			}
			e.setOperatype("YJG_VEDIO_FILE");
			List<TblBaseImageEntity> ylist = yjgAppSjdjService.getJbxxZp(e);
			for (TblBaseImageEntity een : ylist) {
				if (een.getName() != null && "".equals(een.getName()) == false) {
					een.setName(url + een.getName());
				}
				een.setPath(url + een.getPath());
			}
			if (e.getItemType() == 1) {
				if (ylist != null && ylist1.size() != 0) {
					YjgSjdjEntity en4 = new YjgSjdjEntity();
					en4.setItemType(4);
					en4.setXckctp(ylist);
					list.add(en4);
				}
			} else {
				if (ylist != null && ylist1.size() != 0) {
					YjgSjdjEntity en4 = new YjgSjdjEntity();
					en4.setItemType(4);
					en4.setXckctp(ylist);
					list.add(en4);
				} else {
					YjgSjdjEntity en4 = new YjgSjdjEntity();
					en4.setItemType(4);
					list.add(en4);
				}
				
			}
			return mapUtil.setMaputil200("请求成功", null, list);
		} else {
			return mapUtil.setMaputil200("请求成功，数据为空", null, null);
		}
		
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description: 查询username
	 * @author cyf
	 * @date 2017年9月22日 上午10:36:13
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Object test(TblBaseUserEntity e) {
		try {
			String baseuserid = e.getBaseuserid();
			TblBaseUserEntity en = yjgAppSjdjService.getUsername(baseuserid);
			if (en != null) {
				return mapUtil.setMaputil200("请求成功", null, en.getUsername());
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			return mapUtil.setMaputil400("出现异常", null);
		}
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description: 删除图片
	 * @author cyf
	 * @date 2017年9月22日 上午10:36:13
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteFile(TblBaseFileEntity e, HttpServletRequest request) {
		try {
			String dstPath = FilePathUtil.getFilePath("disk_Path") + FilePathUtil.getFilePath("yjg_Img_Path") + "/";
			int row = tblBaseFileService.deleteFile(e.getFileid(), request, dstPath);
			if (row > 0) {
				return mapUtil.setMaputil200("删除成功", null, null);
			} else {
				return mapUtil.setMaputil400("删除失败", null);
			}
		} catch (Exception e2) {
			return mapUtil.setMaputil400("出现异常", null);
		}
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description: 接受案件
	 * @author cyf
	 * @date 2017年9月22日 上午10:36:13
	 */
	@RequestMapping(value = "/accesssj", method = RequestMethod.POST)
	@ResponseBody
	public Object accesssj(YjgSjdjEntity e) {
		yjgSjqqService.accesssj(e, resultMap, 1);
		if ((int) resultMap.get("rows") > 0) {
			return mapUtil.setMaputil200("请求成功", null, null);
		} else {
			return mapUtil.setMaputil400("请求失败", null);
		}
		
	}

	@RequestMapping(value = "/getTypelist1", method = RequestMethod.POST)
	@ResponseBody
	public Object getTypelist1(YjgSjdjEntity e) {
		YjgAppTypeEntity en = yjgAppSjdjService.getTypelist();
		if (en!=null) {
			return mapUtil.setMaputil200("请求成功", null, en);
		} else {
			return mapUtil.setMaputil400("请求失败", null);
		}
		
	}
	/**
	 * @Description 得到临时表实体信息列表
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	@RequestMapping(value = "/getSjcjList", method = RequestMethod.POST)
	@ResponseBody
	public Object getSjcjList(YjgLsjgxxEntity e) {
		try {
			List<YjgLsjgxxEntity> l = yjgLsjgxxService.getSjcjList(e);
			if (l != null) {
				return mapUtil.setMaputil200("请求成功", null, l);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
		}
	}
	/**
	 * @Description 添加临时表实体信息
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	@RequestMapping(value = "/addSjcj", method = RequestMethod.POST)
	@ResponseBody
	public Object addSjcj(@RequestBody YjgLsjgxxEntity e) {
		try {
			int rows = yjgLsjgxxService.insert(e);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
		}
	}
	
	/**
	 * @Description 添加临时表实体信息
	 * @author cyf
	 * @date 2017-10-19 16:19:44
	 */
	@RequestMapping(value = "/updateSjcj", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSjcj(@RequestBody YjgLsjgxxEntity e) {
		try {
			int rows = yjgLsjgxxService.update(e);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
		}
	}
	/**
	 * @Description 添加实体信息
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	@RequestMapping(value = "/addczqk", method = RequestMethod.POST)
	@ResponseBody
	public Object addczqk(YjgSjczEntity e, String sjdjid) {
		try {
			int rows = yjgSjczService.insert(e, null, null, null, sjdjid, 1);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("出现异常", null);
		}
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
			int rows = yjgSjdjService.comfirmRefuse(e, 1);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("出现异常", null);
			
		}
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
			int rows = yjgSjdjService.comfirmPass(e, 1);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("出现异常", null);
		}
	}
	
	/**
	 * @Description 分页查询待办任务
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/listTask", method = RequestMethod.POST)
	@ResponseBody
	public Object listTask(@RequestParam("page") Integer page, Integer rows, YjgSjdjEntity e) {
		Integer total = yjgAppSjdjService.getTaskCount(e);
		if (((page - 1) * rows) >= total) {
			if(total==0){
				return mapUtil.setMaputil200("请求成功", null, null);
			}else{
				return mapUtil.setMaputil402("没有更多数据了", null);
			}
			
		} else {
			List<YjgSjdjEntity> list = yjgAppSjdjService.selectTaskPagination(e, rows, page);
			if (list.size() != 0) {
				return mapUtil.setMaputil200("请求成功", null, list);
			} else {
				return mapUtil.setMaputil200("请求成功,数据为空", null, null);
			}
		}
	}
	
	@RequestMapping(value = "/selectGg", method = RequestMethod.POST)
	@ResponseBody
	public Object selectGg() {
		try {
			List<YjgGglEntity> list = yjgGglService.selectGg();
			if (list != null) {
				resultMap = mapUtil.setMaputil200("请求成功", null, list);
			} else {
				resultMap = mapUtil.setMaputil400("请求失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	@RequestMapping(value = "/getBwlList", method = RequestMethod.POST)
	@ResponseBody
	public Object getBwlList(YjgTxlEntity e) {
		try {
			List<YjgTxlEntity> list = yjgAppSjdjService.getBwlList(e.getBaseuserid());
			if (list != null) {
				resultMap = mapUtil.setMaputil200("请求成功", null, list);
			} else {
				resultMap = mapUtil.setMaputil400("请求失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/getTxlList", method = RequestMethod.POST)
	@ResponseBody
	public Object getTxlList(YjgTxlEntity e) {
		try {
			List<YjgTxlEntity> list = yjgAppSjdjService.getTxlList(e.getBaseuserid());
			if (list != null) {
				resultMap = mapUtil.setMaputil200("请求成功", null, list);
			} else {
				resultMap = mapUtil.setMaputil400("请求失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/updatebwl", method = RequestMethod.POST)
	@ResponseBody
	public Object updatebwl(@RequestBody YjgTxlEntity e) {
		try {
			int rows = yjgAppSjdjService.updatebwl(e);
			if (rows > 0) {
				resultMap = mapUtil.setMaputil200("更新成功", null, null);
			} else {
				resultMap = mapUtil.setMaputil400("更新失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/insertbwl", method = RequestMethod.POST)
	@ResponseBody
	public Object insertbwl(@RequestBody YjgTxlEntity e) {
		try {
			int rows = yjgAppSjdjService.insertbwl(e);
			if (rows > 0) {
//				YjgTxlEntity en = new YjgTxlEntity();
//				en.setBaseuserid(e.getBaseuserid());
				List<YjgTxlEntity> list  = yjgAppSjdjService.getBwlList(e.getBaseuserid());
				resultMap = mapUtil.setMaputil200("插入成功", null, list);
			} else {
				resultMap = mapUtil.setMaputil400("插入失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/getGrxx", method = RequestMethod.POST)
	@ResponseBody
	public Object getGrxx(YjgGrxxEntity e,HttpServletRequest request) {
		try {
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
			YjgGrxxEntity en = yjgAppSjdjService.getGrxx(e.getBaseuserid());
			if (en != null) {
				if(en.getFilepath() !=null){
					en.setFilepath(url+en.getFilepath());
				}
				resultMap = mapUtil.setMaputil200("请求成功", null, en);
			} else {
				resultMap = mapUtil.setMaputil400("请求失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/deletebwl", method = RequestMethod.POST)
	@ResponseBody
	public Object deletebwl(YjgTxlEntity e) {
		try {
			int rows = yjgAppSjdjService.deletebwl(e);
			if (rows > 0) {
				resultMap = mapUtil.setMaputil200("删除成功", null, null);
			} else {
				resultMap = mapUtil.setMaputil400("删除失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	@RequestMapping(value = "/updatebwlzt", method = RequestMethod.POST)
	@ResponseBody
	public Object updatebwlzt(YjgTxlEntity e) {
		try {
			int rows = yjgAppSjdjService.updatebwlzt(e);
			if (rows > 0) {
				resultMap = mapUtil.setMaputil200("更新状态成功", null, null);
			} else {
				resultMap = mapUtil.setMaputil400("更新状态失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/updatetxl", method = RequestMethod.POST)
	@ResponseBody
	public Object updatetxl(@RequestBody YjgTxlEntity e) {
		try {
			int rows = yjgAppSjdjService.updatetxl(e);
			if (rows > 0) {
				resultMap = mapUtil.setMaputil200("更新成功", null, null);
			} else {
				resultMap = mapUtil.setMaputil400("更新失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/inserttxl", method = RequestMethod.POST)
	@ResponseBody
	public Object inserttxl(@RequestBody YjgTxlEntity e) {
		try {
			int rows = yjgAppSjdjService.inserttxl(e);
			if (rows > 0) {
				resultMap = mapUtil.setMaputil200("插入成功", null, null);
			} else {
				resultMap = mapUtil.setMaputil400("插入失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/deletetxl", method = RequestMethod.POST)
	@ResponseBody
	public Object deletetxl(YjgTxlEntity e) {
		try {
			int rows = yjgAppSjdjService.deletetxl(e);
			if (rows > 0) {
				resultMap = mapUtil.setMaputil200("删除成功", null, null);
			} else {
				resultMap = mapUtil.setMaputil400("删除失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
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
			int rows = yjgSjdjUserMappingService.firstYesproperty(e, idArray, e.getBaseuserid());
			if (rows > 0) {
				resultMap = mapUtil.setMaputil200("请求成功", null, null);
			} else {
				resultMap = mapUtil.setMaputil400("请求失败", null);
			}
			
			// resultMap.put("sec", true);
			// resultMap.put("rows", rows);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap = mapUtil.setMaputil400("请求异常", null);
		}
		return resultMap;
	}
//	@SuppressWarnings("unchecked")
//	public void pushInfo(YjgSjdjEntity en,Object idArray,int type) {
//		Map<String, String> msg = new HashMap<>();  
//        msg.put("title", "收到一条新事件!");  
//        msg.put("titleText", "事件："+en.getTaskname());  
//     	Map<String, String> m = new HashMap<String,String>();
//    	m.put("pushl", "2");
//    	m.put("sjdjid",en.getSjdjid());	
//    	m.put("taskid", en.getTaskid());
//    	m.put("instanceid", en.getInstanceid());
//    	m.put("type", en.getTaskname());
//        msg.put("transText", JsonUtil.toJson(m).toString());
//        AppPushUtils pushUtils = new AppPushUtils(AppPush.appId, AppPush.appKey, AppPush.masterSecret);  
//        IGtPush push = new IGtPush(AppPush.appKey, AppPush.masterSecret);  
//        if(type == 1){
//        	for(TblBaseUserEntity Ten: (List<TblBaseUserEntity>)idArray) {  
//        		IAliasResult s = push.queryClientId(AppPush.appId, Ten.getBaseuserid());
//            	if(s.getClientIdList() !=null){
//            		 System.out.println("正在发送消息..."+s.getClientIdList().get(0));  
//    	             IPushResult ret =  pushUtils.pushMsgToSingle(s.getClientIdList().get(0), msg);  
//    	             System.out.println(ret.getResponse().toString()); 
//            	}
//        	}
//        }else{
//        	for(String bid : (String[])idArray) {  
//            	IAliasResult s = push.queryClientId(AppPush.appId, bid);
//            	if(s.getClientIdList() !=null){
//            		 System.out.println("正在发送消息..."+s.getClientIdList().get(0));  
//    	             IPushResult ret =  pushUtils.pushMsgToSingle(s.getClientIdList().get(0), msg);  
//    	             System.out.println(ret.getResponse().toString()); 
//            	}
//            }
//        }
//        
//	}
	// 查找下一个任务
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectNextTasks", method = RequestMethod.POST)
	@ResponseBody

	public Object selectNextTasks(String taskid, String baseuserid, String username, String sjdjid) {
		List<ActTaskEntity> result = null;

//		List<ActTaskEntity> result1  = (List<ActTaskEntity> )memory.getValue("actTaskEntity");
//		if(result1 != null && result1.size() != 0){
//			result =  result1;
//		}else{

			List<ActTaskEntity> actTaskEntity = actTaskService.selectNextTasks(taskid, baseuserid, username, 1, sjdjid);
			if(actTaskEntity != null){
				result =  actTaskEntity;
			}

//		}
		if (result != null) {
			return mapUtil.setMaputil200("请求成功", null, result);
		} else {
			return mapUtil.setMaputil400("请求失败", null);
		}
	
		
	}
	
	/**
	 * @Description 修改实体信息
	 * @author cyf
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/updateSjsbData", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSjsbData(@RequestBody YjgSjdjEntity e) {
		try {
			int rows = yjgSjdjService.updateSj(e);
			if (rows > 0) {
				return mapUtil.setMaputil200("修改成功", null, null);
			} else {
				return mapUtil.setMaputil400("修改失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
		}
	}
	
	@RequestMapping(value = "/inertSjsbData", method = RequestMethod.POST)
	@ResponseBody
	public Object inertSjsbData(@RequestParam Map<String, String> map) {
		return mapUtil.setMaputil200("请求成功", null, null);
		
	}
	
	/**
	 * @Description 删除
	 * @author zqq
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/inertSjsbData1", method = RequestMethod.POST)
	@ResponseBody
	public Object inertSjsbData1(@RequestBody YjgSjdjEntity e) {
		int rows = yjgSjdjService.insert(e, null, null, 1);
		if (rows > 0) {
			return mapUtil.setMaputil200("添加成功", null, null);
		} else {
			return mapUtil.setMaputil400("添加失败", null);
		}
		
	}
	
	/**
	 * @Description 删除
	 * @author zqq
	 * @date 2017-08-24 10:56:59
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(String sjdjid) {
		// int row =1;
		int row = yjgAppSjdjService.delete(sjdjid);
		if (row > 0) {
			return mapUtil.setMaputil200("删除成功", null, null);
		} else {
			return mapUtil.setMaputil400("删除失败", null);
		}
	}
	
	@RequestMapping(value = "/yesproperty", method = RequestMethod.POST)
	@ResponseBody
	public Object yesproperty(YjgSjdjUserMappingEntity e, @Param("idArray") String[] idArray) {
		try {
			int rows = yjgSjdjUserMappingService.yesproperty(e, idArray, 1);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
		}
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
			int rows = yjgSjdjService.fallback(e, 1);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
			
		}
	}
	
//	/**
//	 * @Description 分页查询
//	 * @author cyf
//	 * @date 2017-09-12 08:50:55
//	 */
//	@RequestMapping(value = "/sjczlist", method = RequestMethod.POST)
//	@ResponseBody
//	public Object sjczlist(@RequestParam("page") Integer page, Integer rows, YjgSjczEntity e, HttpServletRequest request) {
//		
//		Integer total = yjgAppSjdjService.getCzCount(e.getSjdjid());
//		if (((page - 1) * rows) > total) {
//			return mapUtil.setMaputil402("没有更多数据了", null);
//			
//		} else {
//			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
//			List<YjgSjczEntity> list = yjgAppSjdjService.getczlist(e.getSjdjid(), rows, page);
//			for (YjgSjczEntity en : list) {
//				for (TblBaseImageEntity ten : en.getImagelist()) {
//					if (ten.getName() != null && "".equals(ten.getName()) == false) {
//						ten.setName(url + ten.getName());
//					}
//					ten.setPath(url + ten.getPath());
//				}
//				for (TblBaseImageEntity ven : en.getVediolist()) {
//					if (ven.getName() != null && "".equals(ven.getName()) == false) {
//						ven.setName(url + ven.getName());
//					}
//					ven.setPath(url + ven.getPath());
//				}
//			}
//			if (list.size() != 0) {
//				return mapUtil.setMaputil200("请求成功", null, list);
//			} else {
//				return mapUtil.setMaputil200("请求成功，数据为空", null, null);
//			}
//		}
//	}
	
	/**
	 * @Description 分页查询
	 * @author cyf
	 * @date 2017-09-12 08:50:55
	 */
	@RequestMapping(value = "/sjczlist", method = RequestMethod.POST)
	@ResponseBody
	public Object sjczlist(@RequestParam("page") Integer page, Integer rows, YjgSjczEntity e, HttpServletRequest request) {
		

			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
			List<YjgSjczEntity> list = yjgAppSjdjService.getczlist(e.getSjdjid());
			for (YjgSjczEntity en : list) {
				for (TblBaseImageEntity ten : en.getImagelist()) {
					if (ten.getName() != null && "".equals(ten.getName()) == false) {
						ten.setName(url + ten.getName());
					}
					ten.setPath(url + ten.getPath());
				}
				for (TblBaseImageEntity ven : en.getVediolist()) {
					if (ven.getName() != null && "".equals(ven.getName()) == false) {
						ven.setName(url + ven.getName());
					}
					ven.setPath(url + ven.getPath());
				}
			}
			if (list.size() != 0) {
				return mapUtil.setMaputil200("请求成功", null, list);
			} else {
				return mapUtil.setMaputil200("请求成功，数据为空", null, null);
			}
		}
	
	
	/**
	 * @Description 现成问题处理完成，提交
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	@RequestMapping(value = "/repair", method = RequestMethod.POST)
	@ResponseBody
	public Object repair(YjgSjqqEntity e) {
		try {
			int rows = yjgSjqqService.repair(e);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			if (e2 instanceof ActivitiObjectNotFoundException) {
				return mapUtil.setMaputil400("该任务已被处理，请返回！", null);
			}
			else{
				return mapUtil.setMaputil400("请求数据异常", null);
			}
		}
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
			int rows = yjgSjdjService.taskAssign(yjgSjdjEntity, idArray, 1);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (ActivitiObjectNotFoundException e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil401("该事件已被指派，请返回", null);
		}catch(Exception e){
			return mapUtil.setMaputil400("请求数据异常", null);
		}
	}
	
	/**
	 * @Description 查询流程节点
	 * @author cyf
	 * @date 2017-09-07 15:25:43
	 */
	@RequestMapping(value = "/getxmlc", method = RequestMethod.POST)
	@ResponseBody
	public Object getxmlc(TblBaseUserEntity e) {
		List<HistoricTaskInstance> lists = new ArrayList<HistoricTaskInstance>();
		List<HistoricTaskInstance> list = actWorkFlowService.findHisTask(e.getInstanceid());
		
		if (list.size() != 0) {
			for (HistoricTaskInstance c : list) {
				if (c.getAssignee() != null) {
					TblBaseUserEntity en = yjgAppSjdjService.selectuserbyid(c.getAssignee());
					c.setLocalizedDescription(en.getAccount());
				} else {
					c.setLocalizedDescription("");
				}
				lists.add(c);
			}
			return mapUtil.setMaputil200("请求成功", null, lists);
		} else {
			return mapUtil.setMaputil200("请求成功，数据为空", null, null);
		}
	}
	
	/**
	 * @Description 分页查询已接件用户
	 * @author cyf
	 * @date 2017-09-07 15:25:43
	 */
	@RequestMapping(value = "/selectUsered", method = RequestMethod.POST)
	@ResponseBody
	public Object selectUsered(TblBaseUserEntity e) {
		List<TblBaseUserEntity> list = yjgSjdjUserMappingService.selectRefuseUsered(e);
		if (list.size() != 0) {
			return mapUtil.setMaputil200("请求成功", null, list);
		} else {
			return mapUtil.setMaputil200("请求成功，数据为空", null, null);
		}
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
			int rows = yjgSjdjService.sendAgain(yjgSjdjEntity, 1, firstDeny);
			if (rows > 0) {
				return mapUtil.setMaputil200("请求成功", null, null);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
		}
	}
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description: 重新派单
	 * @author cyf
	 * @date 2017年10月17日 下午3:49:20
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	@ResponseBody
	public Object loginout(TblBaseUserEntity e,HttpServletRequest request) {
			String seed1 = request.getHeader("seed");  
			if(seed1 != null){
				memory.clearLoginInfoBySeed(seed1);	
			}
			String tokenid = (String)memory.getValue(seed1);
			if(tokenid == null){
				return mapUtil.setMaputil200("请求成功", null, null);
			}else{
				return mapUtil.setMaputil400("请求失败", null);
			}
			
	}
	/**
	 * @Description 添加图片
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadFiles(@RequestParam("type") String type, @RequestParam("username") String username, @RequestParam("sjid") String sjid,
			@RequestParam("files") MultipartFile[] multipartFile, HttpServletRequest request, TblBaseFileEntity tblBaseFileEntity, YjgSjdjEntity yjgSjdjEntity) {
		List<TblBaseImageEntity> imagelList1 = new ArrayList<TblBaseImageEntity>();
		try {
			yjgAppSjdjService.addImg(type, username, sjid, multipartFile, request, imagelList1);
//			yjgAppSjdjService.addImg(type, username, sjid, multipartFile, request, imagelList);
			return mapUtil.setMaputil200("上传成功", null, imagelList1);
		} catch (Exception e) {
			e.printStackTrace();
			return mapUtil.setMaputil400("上传失败", null);
		}
	}
	
	/**
	 * @Description 添加图片
	 * @author cyf
	 * @date 2017-03-08 16:57:11
	 */
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadLogo(@RequestParam("baseuserid") String baseuserid,@RequestParam("username") String username, @RequestParam("files") MultipartFile multipartFile, HttpServletRequest request) {
		try {
			TblBaseImageEntity en =yjgAppSjdjService.saveLogo(baseuserid, username, multipartFile, request);
			return mapUtil.setMaputil200("上传成功", null, en);
		} catch (Exception e) {
			e.printStackTrace();
			return mapUtil.setMaputil400("上传失败", null);
		}
	}
	/**
	 * @Package com.kingtopinfo.yjg.action
	 * @Description:获取版本号
	 * @author cyf
	 * @date 2017年10月17日 下午3:49:20
	 */
	@RequestMapping(value = "/getversion", method = RequestMethod.POST)
	@ResponseBody
	public Object getversion(YjgAppversionEntity y) {
		try {
			YjgAppversionEntity en = yjgAppSjdjService.getversion();
			if (en != null) {
				return mapUtil.setMaputil200("请求成功", null, en);
			} else {
				return mapUtil.setMaputil400("请求失败", null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return mapUtil.setMaputil400("请求异常", null);
		}
	}
	

	@RequestMapping(value = "/downloadApp", method = RequestMethod.POST)
	@ResponseBody
	public Object downloadApp(HttpServletRequest request, HttpServletResponse response,YjgAppversionEntity y) {
		try {
			YjgAppversionEntity en = yjgAppSjdjService.getversion();
			tblBaseFileService.downloadApp(request, response, en,1);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return "seccess";
	}
	

	// /**
	// * @Description 添加图片
	// * @author cyf
	// * @date 2017-03-08 16:57:11
	// */
	// @RequestMapping(value = "/downloadByFilePath", method = RequestMethod.GET)
	// @ResponseBody
	// public void downloadByFilePath(String type, String path, HttpServletResponse resp,HttpServletRequest request) {
	// String ath1 = "";
	// try {
	//
	// if ("image".equals(type)) {
	// String yjg_img_relative_Path = FilePathUtil.getFilePath("yjg_Img_Path");
	// ath1 = request.getSession().getServletContext().getRealPath("") + yjg_img_relative_Path + "/";
	//// ath1 = "E:\\eclispse\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\xmsz1\\files\\img\\" + path;
	//
	// } else {
	// String yjg_vedio_relative_Path = FilePathUtil.getFilePath("yjg_Vedio_Path");
	// ath1 = request.getSession().getServletContext().getRealPath("") + yjg_vedio_relative_Path + "/";
	// // 创建文件存放路径
	//
	//// ath1 = "E:\\eclispse\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\xmsz1\\files\\vedio\\" + path;
	//
	// }
	// BufferedOutputStream out = null;
	// FileInputStream ips = null;
	// try {
	// File f = new File(ath1);
	// // 获取图片存放路径
	// ips = new FileInputStream(new File(ath1));
	// resp.setContentType("multipart/form-data");
	// resp.setContentLength((int) f.length());
	// out = new BufferedOutputStream(resp.getOutputStream());
	// // 读取文件流
	// int len = 0;
	// byte[] buffer = new byte[1024];
	// while ((len = ips.read(buffer)) != -1) {
	// out.write(buffer, 0, len);
	// }
	// out.flush();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// out.close();
	// ips.close();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	
}