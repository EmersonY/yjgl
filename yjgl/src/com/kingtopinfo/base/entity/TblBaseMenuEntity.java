package com.kingtopinfo.base.entity;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName com.kingtopinfo.base.entity.TBaseMenuEntity
 * @Description T_BASE_MENU表映射实体类
 * @author dzb@kingtopinfo.com
 * @date 2014-02-20 09:12:37
 * @version 1.0
 * @remark create by generator
 */

public class TblBaseMenuEntity implements Serializable{
	
	private static final long	serialVersionUID	= 5188020843133710294L;
	
	private String			basemenuid;		// 菜单编号
	@NotBlank(message = "菜单名称不能为空")
	@Size(min = 0, max = 100, message = "菜单名称长度必须小于100")
	private String			menuname;		// 菜单名称
	@NotBlank(message = "链接地址不能为空")
	@Size(min = 0, max = 100, message = "链接地址长度必须小于200")
	private String			src;			// 链接地址
	private String			basemenupid;	// 菜单父编号
	private Integer			sequ;			// 排序序列
	private String			icon;			// 图标样式
	private Integer			state;			// 有效状态
	private String			updateuserid;	// 更新人
	private java.util.Date	updatetime;		// 更新时间
	
	/**
	 * VO
	 */
	private String			id;
	private String 			pid;
	private String			text;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	
	}	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
		
	public String getBasemenuid() {
		return basemenuid;
	}
	
	public void setBasemenuid(String basemenuid) {
		this.basemenuid = basemenuid;
	}
	
	public String getMenuname() {
		return menuname;
	}
	
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	
	public String getSrc() {
		return src;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getBasemenupid() {
		return basemenupid;
	}
	
	public void setBasemenupid(String basemenupid) {
		this.basemenupid = basemenupid;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getUpdateuserid() {
		return updateuserid;
	}
	
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public Integer getSequ() {
		return sequ;
	}
	
	public void setSequ(Integer sequ) {
		this.sequ = sequ;
	}
	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}