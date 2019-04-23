package com.kingtopinfo.base.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;

public class AppPush {

	
	//定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
//	public static final String appId = "7KeUBGLIjsAZIAolwzu1Y4";
//	public static final String appKey = "dKjD2CiMJf9KiJiPOwP9K2";
//	public static final String masterSecret = "BdJ3MIqnmsAXhLLNWejgE6";
	public static final String Path ="E:\\tp\\";
	public static final String appId = "qAD7vaOO8g8xPpHYtP0854";
	public static final String appKey = "RnKAxIK8jt7Rt4eEoMnIF9";
	public static final String masterSecret = "3TysUyYEgv6KWNWclHU709";
	public static final String	url				= "http://sdk.open.api.igexin.com/apiex.htm";

    public static void main(String[] args) throws IOException {
   
    	
//    	 String appId = "EHFx0RmBuL9iuvh05WxJq9";  
//         String appKey = "T8X5ijnhO8ACHWCPRphrO8";  
//         String masterSecret = "hvzdfG6ZSh8uo2N7eTNqq8";  
           
         Map<String, String> msg = new HashMap<>();  
         msg.put("title", "收到一条新消息!");  
         msg.put("titleText", "公告：哦我我我的");  
         
         
      	Map<String, String> m = new HashMap<String,String>();
    	m.put("pushl", "1");
//    	m.put("gglid", "1cb475fcd8c44f0a99c2650fdfb9eafa");
//    	m.put("222", "42222");
    	

//    	System.out.println("-----------"+JsonUtil.toJson(m).toString());
         msg.put("transText", JsonUtil.toJson(m).toString());  
           
//         String []cids = {};  
         IGtPush push = new IGtPush(appKey, masterSecret);  
 		IAliasResult s = push.queryClientId(appId, "101e844452ca465e8f79e601e0fca397");
//     	IAliasResult s = push.queryClientId(AppPush.appId,"7886086a3d534d3e9e41be718b05092c");
     	
         AppPushUtils pushUtils = new AppPushUtils(appId, appKey, masterSecret);  
//         IPushResult ret =  pushUtils.pushMsgToSingle(s.getClientIdList().get(0), msg);  
//         System.out.println("正在发送消息..."+s.getClientIdList().get(0));  
         IPushResult ret =  pushUtils.pushMsg1(msg);  
         System.out.println(ret.getResponse().get("result")); 
//         for(String cid : cids) {  
//             System.out.println("正在发送消息...");  
//             IPushResult ret =  pushUtils.pushMsgToSingle(cid, msg);  
//             System.out.println(ret.getResponse().toString());  
//         }  

//        IGtPush push = new IGtPush(url, appKey, masterSecret);
//
//        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
//        LinkTemplate template = new LinkTemplate();
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//        template.setTitle("欢迎使用个推!");
//        template.setText("这是一条推送消息~");
//        template.setUrl("http://getui.com");
//
//        List<String> appIds = new ArrayList<String>();
//        appIds.add(appId);
//
//        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
//        AppMessage message = new AppMessage();
//        message.setData(template);
//        message.setAppIdList(appIds);
//        message.setOffline(true);
//        message.setOfflineExpireTime(1000 * 600);
//
//        IPushResult ret = push.pushMessageToApp(message);
//        System.out.println(ret.getResponse().toString());
    }
  	public static void pushInfo(String cid) {
          AppPushUtils pushUtils = new AppPushUtils(AppPush.appId, AppPush.appKey, AppPush.masterSecret);  
          IGtPush push = new IGtPush(AppPush.appKey, AppPush.masterSecret);  
  		Map<String, String> msg = new HashMap<>();  
          msg.put("title", "收到一条新提醒!");  
          msg.put("titleText", "该账号已经在别处登录！");  
       	Map<String, String> m = new HashMap<String,String>();
      	m.put("pushl", "3");
      	msg.put("transText", JsonUtil.toJson(m).toString());
        System.out.println("正在发送消息..."+cid);  
  	    IPushResult ret =  pushUtils.pushMsgToSingle(cid, msg);  
  	    System.out.println(ret.getResponse().toString()); 
      
  	}
    @SuppressWarnings("unchecked")
	public static void pushInfo(YjgSjdjEntity en,Object idArray,int type) {
        AppPushUtils pushUtils = new AppPushUtils(AppPush.appId, AppPush.appKey, AppPush.masterSecret);  
        IGtPush push = new IGtPush(AppPush.appKey, AppPush.masterSecret);  
		Map<String, String> msg = new HashMap<>();
     	Map<String, String> m = new HashMap<String,String>();
		msg.put("title", "收到一条新事件!");  
		 m.put("pushl", "2");
		if(type == 4){
		    msg.put("titleText", "事件："+"新事件上报，需要派单！");  

		}else{
		    msg.put("titleText", "事件："+en.getTaskname()); 
		}
		m.put("sjdjid",en.getSjdjid());	
    	m.put("taskid", en.getTaskid());
    	m.put("instanceid", en.getInstanceid());
    	m.put("type", en.getTaskname());
        if(type == 1 || type == 4){
			msg.put("transText", JsonUtil.toJson(m).toString());
			for (TblBaseUserEntity Ten : (List<TblBaseUserEntity>) idArray) {
				if (Ten != null) {
					IAliasResult s = push.queryClientId(AppPush.appId, Ten.getBaseuserid());
					if (s.getClientIdList() != null) {
						System.out.println("正在发送消息..." + s.getClientIdList().get(0));
						IPushResult ret = pushUtils.pushMsgToSingle1(s.getClientIdList().get(0), msg);
						System.out.println(ret.getResponse().toString());
					}
				}
        	}
        }else if(type == 3){
        	m.put("dbzt", en.getDbzt());
        	m.put("wdbbz", en.getWdbbz());
        	msg.put("transText", JsonUtil.toJson(m).toString());
        	for(String Ten: (String[])idArray) {  
        		IAliasResult s = push.queryClientId(AppPush.appId, Ten);
            	if(s.getClientIdList() !=null){
            		 System.out.println("正在发送消息..."+s.getClientIdList().get(0));  
    	             IPushResult ret =  pushUtils.pushMsgToSingle1(s.getClientIdList().get(0), msg);  
    	             System.out.println(ret.getResponse().toString()); 
            	}
        	}
        }
        else{
        	  msg.put("transText", JsonUtil.toJson(m).toString());
        	for(String bid : (String[])idArray) {  
            	IAliasResult s = push.queryClientId(AppPush.appId, bid);
            	if(s.getClientIdList() !=null){
            		 System.out.println("正在发送消息..."+s.getClientIdList().get(0));  
    	             IPushResult ret =  pushUtils.pushMsgToSingle1(s.getClientIdList().get(0), msg);  
    	             System.out.println(ret.getResponse().toString()); 
            	}
            }
        }
        
	}
}
