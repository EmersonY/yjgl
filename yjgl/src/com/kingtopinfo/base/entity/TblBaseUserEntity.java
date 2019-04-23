package com.kingtopinfo.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingtopinfo.activiti.entity.ActTaskEntity;

/**
 * @ClassName com.kingtopinfo.base.entity.TBaseUserEntity
 * @Description TBL_BASE_USER表映射实体类
 * @author dzb@kingtopinfo.com
 * @date 2017-5-16 11:11:11
 * @version 1.0
 * @remark
 */

public class TblBaseUserEntity extends ActTaskEntity implements Serializable {
	
	private static final long	serialVersionUID	= -1164611427458256536L;
	private String				baseuserid;									// 用户编号
	@NotBlank(message = "账户不能为空")
	@Size(min = 0, max = 32, message = "账户长度必须小于32")
	private String				account;									// 账号
	@NotBlank(message = "用户名不能为空")
	@Size(min = 0, max = 100, message = "用户名长度必须小于100")
	private String				username;									// 用户名
	@NotBlank(message = "密码不能为空")
	@JsonIgnore
	private String				password;									// 密码
	private String				newpassword;									// 密码
	private int					state;										// 有效状态
	private String				updateuserid;								// 更新人
	private java.util.Date		updatetime;									// 更新时间
	private String				pbaseuserid;								// 父用户信息编号
	private String				islogin;									// 是否登陆过 1：是 0：否
	
	private String				rolename;
	/**
	 * VO
	 */
	private String				baseroleid;									// 角色ID
	private String				sjdjid;										// 窨井登记事件ID
	private String				sfjd;										// 是否接件
	private String				phone;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date				czsj;										// 操作时间
	private String				jjly;										// 拒绝理由
	private String				pbaseuserName;								// 父用户名
	private String				type;										// 类型
	private int					seq;
	private String				filepath;	
	
	
	 public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	private List<TblBaseRoleEntity> rolelist;
	
	 
	public List<TblBaseRoleEntity> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<TblBaseRoleEntity> rolelist) {
		this.rolelist = rolelist;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getCzsj() {
		return czsj;
	}
	
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	
	public String getJjly() {
		return jjly;
	}
	
	public void setJjly(String jjly) {
		this.jjly = jjly;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getBaseroleid() {
		return baseroleid;
	}
	
	public void setBaseroleid(String baseroleid) {
		this.baseroleid = baseroleid;
	}
	
	public String getBaseuserid() {
		return baseuserid;
	}
	
	public void setBaseuserid(String baseuserid) {
		this.baseuserid = baseuserid;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
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
	
	public String getSjdjid() {
		return sjdjid;
	}
	
	public void setSjdjid(String sjdjid) {
		this.sjdjid = sjdjid;
	}
	
	public String getSfjd() {
		return sfjd;
	}
	
	public void setSfjd(String sfjd) {
		this.sfjd = sfjd;
	}
	
	public String getPbaseuserid() {
		return pbaseuserid;
	}
	
	public void setPbaseuserid(String pbaseuserid) {
		this.pbaseuserid = pbaseuserid;
	}
	
	public String getPbaseuserName() {
		return pbaseuserName;
	}
	
	public void setPbaseuserName(String pbaseuserName) {
		this.pbaseuserName = pbaseuserName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((baseroleid == null) ? 0 : baseroleid.hashCode());
		result = prime * result + ((baseuserid == null) ? 0 : baseuserid.hashCode());
		result = prime * result + ((czsj == null) ? 0 : czsj.hashCode());
		result = prime * result + ((jjly == null) ? 0 : jjly.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pbaseuserName == null) ? 0 : pbaseuserName.hashCode());
		result = prime * result + ((pbaseuserid == null) ? 0 : pbaseuserid.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
		result = prime * result + ((sfjd == null) ? 0 : sfjd.hashCode());
		result = prime * result + ((sjdjid == null) ? 0 : sjdjid.hashCode());
		result = prime * result + state;
		result = prime * result + ((updatetime == null) ? 0 : updatetime.hashCode());
		result = prime * result + ((updateuserid == null) ? 0 : updateuserid.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblBaseUserEntity other = (TblBaseUserEntity) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (baseroleid == null) {
			if (other.baseroleid != null)
				return false;
		} else if (!baseroleid.equals(other.baseroleid))
			return false;
		if (baseuserid == null) {
			if (other.baseuserid != null)
				return false;
		} else if (!baseuserid.equals(other.baseuserid))
			return false;
		if (czsj == null) {
			if (other.czsj != null)
				return false;
		} else if (!czsj.equals(other.czsj))
			return false;
		if (jjly == null) {
			if (other.jjly != null)
				return false;
		} else if (!jjly.equals(other.jjly))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pbaseuserName == null) {
			if (other.pbaseuserName != null)
				return false;
		} else if (!pbaseuserName.equals(other.pbaseuserName))
			return false;
		if (pbaseuserid == null) {
			if (other.pbaseuserid != null)
				return false;
		} else if (!pbaseuserid.equals(other.pbaseuserid))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		if (sfjd == null) {
			if (other.sfjd != null)
				return false;
		} else if (!sfjd.equals(other.sfjd))
			return false;
		if (sjdjid == null) {
			if (other.sjdjid != null)
				return false;
		} else if (!sjdjid.equals(other.sjdjid))
			return false;
		if (state != other.state)
			return false;
		if (updatetime == null) {
			if (other.updatetime != null)
				return false;
		} else if (!updatetime.equals(other.updatetime))
			return false;
		if (updateuserid == null) {
			if (other.updateuserid != null)
				return false;
		} else if (!updateuserid.equals(other.updateuserid))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public String getIslogin() {
		return islogin;
	}
	
	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}
	
}