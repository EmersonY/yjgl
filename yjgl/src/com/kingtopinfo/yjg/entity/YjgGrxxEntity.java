package com.kingtopinfo.yjg.entity;
	
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.base.entity.TblBaseRoleEntity;
	
/**
 * @ClassName entity.YjgSjdjEntity
 * @Description YJG_SJDJ表映射实体类
 * @author cyf
 * @date 2017-08-24 10:56:58
 * @version 1.0
 * @remark create by generator
 */
public class YjgGrxxEntity  implements Serializable {
	private static final long	serialVersionUID	= 3542267052335795357L;
	private String	 baseuserrolemid;
    private String			baseuserid;		// 用户信息编号
    private String			tel;			// 固定电话
    private String			phone;			// 移动电话
    private String			account;
    private int			state;
    private String			username;
    private String			filepath;
    
    
    public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	private List<TblBaseRoleEntity>  rolelist;
    
    
    
	public String getBaseuserrolemid() {
		return baseuserrolemid;
	}
	public void setBaseuserrolemid(String baseuserrolemid) {
		this.baseuserrolemid = baseuserrolemid;
	}
	public List<TblBaseRoleEntity> getRolelist() {
		return rolelist;
	}
	public void setRolelist(List<TblBaseRoleEntity> rolelist) {
		this.rolelist = rolelist;
	}

	public String getBaseuserid() {
		return baseuserid;
	}
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    

}