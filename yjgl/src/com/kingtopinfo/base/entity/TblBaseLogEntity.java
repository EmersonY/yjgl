package com.kingtopinfo.base.entity;

import java.io.Serializable;

/**
 * @ClassName com.kingtopinfo.base.entity
 * @Description TBL_BASE_LOG表映射实体类
 * @author cyf
 * @date 2017-06-21 08:59:25
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseLogEntity implements Serializable {
	
	private static final long	serialVersionUID	= -6779618938296844495L;
	
	private String module;  //操作模块
	
	private String	logtime;		// 日志时间
	
	private String result;  //操作结果
	
	private String logid;  //主键ID
	
	private String userid;  //操作人ID
	
	private String content;  //操作内容
	
	private String username;  //操作人
	
	// 新增查询属性
	private String			logtimeStart;	// 查询开始时间
	private String			logtimeEnd;		// 查询结束时间
	
	public String getModule(){  
		return module;  
	}
	  
	public void setModule(String module){  
		this.module = module;  
	}  
	public String getResult(){  
		return result;  
	}
	  
	public void setResult(String result){  
		this.result = result;  
	}  
	public String getLogid(){  
		return logid;  
	}
	  
	public void setLogid(String logid){  
		this.logid = logid;  
	}  
	public String getUserid(){  
		return userid;  
	}
	  
	public void setUserid(String userid){  
		this.userid = userid;  
	}  
	public String getContent(){  
		return content;  
	}
	  
	public void setContent(String content){  
		this.content = content;  
	}  
	public String getUsername(){  
		return username;  
	}
	  
	public void setUsername(String username){  
		this.username = username;  
	}  
	
	public String getLogtimeStart() {
		return logtimeStart;
	}
	
	public void setLogtimeStart(String logtimeStart) {
		this.logtimeStart = logtimeStart;
	}
	
	public String getLogtimeEnd() {
		return logtimeEnd;
	}
	
	public void setLogtimeEnd(String logtimeEnd) {
		this.logtimeEnd = logtimeEnd;
	}
	
	public String getLogtime() {
		return logtime;
	}
	
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
}