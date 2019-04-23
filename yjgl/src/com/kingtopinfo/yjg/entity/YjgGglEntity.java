package com.kingtopinfo.yjg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName entity.YjgGglEntity
 * @Description YJG_GGL表映射实体类
 * @author cyf
 * @date 2017-12-07 14:01:27
 * @version 1.0
 * @remark create by generator
 */
public class YjgGglEntity {
	private static final long	serialVersionUID	= 3542267052335795357L;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date czsj;  //操作时间
	private String czrxm;  //操作人姓名
	private String gglid;  //公告栏编号
	private java.lang.Object gglnr;  //公告栏内容
	private String gglbt;  //公告栏标题
	
	
	//查询时间
	private String ggltimeStart;
	private String ggltimeEnd;
		
	
	
	
	public String getGgltimeStart() {
		return ggltimeStart;
	}

	public void setGgltimeStart(String ggltimeStart) {
		this.ggltimeStart = ggltimeStart;
	}

	public String getGgltimeEnd() {
		return ggltimeEnd;
	}

	public void setGgltimeEnd(String ggltimeEnd) {
		this.ggltimeEnd = ggltimeEnd;
	}

	public java.util.Date getCzsj(){  
		return czsj;  
	}
	  
	public void setCzsj(java.util.Date czsj){  
		this.czsj = czsj;  
	}  
	public String getCzrxm(){  
		return czrxm;  
	}
	  
	public void setCzrxm(String czrxm){  
		this.czrxm = czrxm;  
	}  
	public String getGglid(){  
		return gglid;  
	}
	  
	public void setGglid(String gglid){  
		this.gglid = gglid;  
	}  
	public java.lang.Object getGglnr(){  
		return gglnr;  
	}
	  
	public void setGglnr(java.lang.Object gglnr){  
		this.gglnr = gglnr;  
	}  
	public String getGglbt(){  
		return gglbt;  
	}
	  
	public void setGglbt(String gglbt){  
		this.gglbt = gglbt;  
	}  
	
}