package com.kingtopinfo.app.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.kingtopinfo.app.action.Memory;
import com.kingtopinfo.app.entity.YjgAppversionEntity;
import com.kingtopinfo.app.service.YjgAppSjdjService;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.ext.BaseValidAction;
import com.kingtopinfo.base.service.TblBaseFileService;
import com.kingtopinfo.base.service.TblBaseLogService;
import com.kingtopinfo.base.service.TblBaseUserService;
import com.kingtopinfo.base.util.AppPush;
import com.kingtopinfo.base.util.FilePathUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.Md5;
import com.kingtopinfo.base.util.WfConstants;
import com.kingtopinfo.base.util.mapUtil;
import com.kingtopinfo.yjg.entity.YjgGrxxEntity;

/**
 * @ClassName action.YjgFyjsjdjAction
 * @Description YJG_FYJSJDJ表Action类
 * @author cyf
 * @date 2017-08-25 15:02:06
 * @version 1.0
 * @remark create by generator
 */
@Controller
@RequestMapping("/YjglApp_LoginAction")
public class YjglAppLoginAction extends BaseValidAction{
	@Autowired
	private Memory memory;
	@Autowired
	private YjgAppSjdjService yjgAppSjdjService;
	
	
	@Autowired
	private TblBaseLogService	tblBaseLogService;
	@Autowired
	private TblBaseUserService	tblBaseUserService;
	@Autowired
	private TblBaseFileService	tblBaseFileService;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(String un, String pw,String cid,HttpServletRequest request,HttpServletResponse response){
	
		TblBaseUserEntity oTBaseUserEntity = tblBaseUserService.selectByAccount(un);

		if(oTBaseUserEntity != null){
			if(un.equals(oTBaseUserEntity.getAccount()) && pw.equals(oTBaseUserEntity.getPassword())){
				String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + FilePathUtil.getFilePath("xmlj");
				if(oTBaseUserEntity.getFilepath() !=null){
					oTBaseUserEntity.setFilepath(url+oTBaseUserEntity.getFilepath());
				}
				YjgGrxxEntity y = yjgAppSjdjService.getGrxx(oTBaseUserEntity.getBaseuserid());
				if(y != null){
					oTBaseUserEntity.setRolelist(y.getRolelist());
				}
//				String oldCid = (String)memory.getValue(oTBaseUserEntity.getBaseuserid());
//				if(oldCid !=null && "".equals(oldCid) ==false){8
//				 IGtPush push = new IGtPush(AppPush.appKey, AppPush.masterSecret);  
//			 	IAliasResult s = push.queryClientId(AppPush.appId,oTBaseUserEntity.getBaseuserid());
			 	String seed = Md5.md5Digest(un);
			 	String tokenid = (String)memory.getValue(seed);
			 	String oldcid =(String)memory.getValue(oTBaseUserEntity.getBaseuserid());
			 	if(oldcid != null && oldcid.equals(cid)==false && tokenid != null){
			 		System.out.println("正在发送消息..."+oldcid);  
					AppPush.pushInfo(oldcid);
			 	}
				String token = Md5.getMd5Time(pw, un);
				memory.setValue(seed, token, 0, 0);
				memory.setValue(token, oTBaseUserEntity,0,0);
				memory.setValue(oTBaseUserEntity.getBaseuserid(), cid, 0, 0);
				System.out.println("----------------------"+memory.getValue(seed));
//				tblBaseLogService.insertLog(WfConstants.LOGIN, "账号：" + un + "成功登陆系统", WfConstants.SUCCESS);
				return mapUtil.setMaputil200("登录成功",(String)memory.getValue(seed),oTBaseUserEntity);
			}
			else if(pw.equals(oTBaseUserEntity.getPassword()) == false){
				return mapUtil.setMaputil401("密码错误", null);
			}
			else{
				return mapUtil.setMaputil400("登录失败",null);
			}
		}else{
			return mapUtil.setMaputil401("账号不存在", null);
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
			System.out.println("-------------------------------------");
			tblBaseFileService.downloadApp(request, response, en,1);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return "seccess";
	}
	
}