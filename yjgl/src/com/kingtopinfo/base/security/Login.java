package com.kingtopinfo.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.service.TblBaseUserService;

/**
 * @ClassName com.kingtopinfo.base.security.Login.java
 * @Description: 用户登录验证
 * @author dzb@kingtopinfo.com
 * @date 2016年3月28日 上午9:46:38
 * @version 1.0
 */
public class Login implements UserDetailsService {

	@Autowired
	private TblBaseUserService tblBaseUserService;

	@Override
	public UserDetails loadUserByUsername(String account) {
		
		TblBaseUserEntity tlBaseUserEntity = tblBaseUserService.selectByAccount(account);
		if (tlBaseUserEntity != null) {

			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority("base");	//默认加载base权限
			auths.add(auth);
			List<String> roleid = tblBaseUserService.selectRoleIdByAccount(account);
			for(int i=0;i<roleid.size();i++){
				auth = new SimpleGrantedAuthority(roleid.get(i));
				auths.add(auth);
			}
			UserInfoEntity user = new UserInfoEntity(account, tlBaseUserEntity.getPassword(), true, true, true, true, auths);
			user.setUsername(tlBaseUserEntity.getUsername()); // 添加附加属性
			user.setBaseuserid(tlBaseUserEntity.getBaseuserid());
			user.setAccount(tlBaseUserEntity.getAccount());
			return user;
		} else {
			return null;
		}
	}

}
