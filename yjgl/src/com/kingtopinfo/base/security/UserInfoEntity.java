package com.kingtopinfo.base.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserInfoEntity extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String baseuserid;	//用户编号
	private String account;		//账号
	private String username;	//用户名

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

	/**
	 * @param username	用户名
	 * @param password	密码
	 * @param enabled	账号是否存在
	 * @param accountNonExpired		账号是否过期
	 * @param credentialsNonExpired		账号凭证是否过期
	 * @param accountNonLocked	账号是否被锁定
	 * @param authorities	权限列表
	 */

	public UserInfoEntity(String username, String password, boolean enabled, boolean accountNonExpired,boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities){
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
