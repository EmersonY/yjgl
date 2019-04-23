package com.kingtopinfo.base.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserRoleMappingEntity;
import com.kingtopinfo.base.mapper.TblBaseRoleMapper;
import com.kingtopinfo.base.mapper.TblBaseUserMapper;
import com.kingtopinfo.base.mapper.TblBaseUserRoleMappingMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.base.util.WfConstants;

/**
 * @ClassName com.kingtopinfo.base.service.TBaseUserRoleMappingService
 * @Description T_BASE_USER_ROLE_MAPPING表服务类
 * @author dzb@kingtopinfo.com
 * @date 2014-02-18 09:21:47
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseUserRoleMappingService {
	
	@Autowired
	private TblBaseUserRoleMappingMapper tblBaseUserRoleMappingMapper;
	@Autowired
	private TblBaseLogService				tblBaseLogService;
	@Autowired
	private TblBaseUserMapper				tblBaseUserMapper;
	@Autowired
	private TblBaseRoleMapper				tblBaseRoleMapper;
	
	/**
	 * @Description:用户授权
	 * @author:cyf
	 * @param rows
	 * @time:2017年6月6日 下午3:48:10
	 */
	public int insert(TblBaseUserRoleMappingEntity e, int rows) {
		String[] baseuseridArray = e.getBaseuserid().split(",");
		String account = "";
		for (String baseuserid : baseuseridArray) {
			TblBaseUserRoleMappingEntity entity = new TblBaseUserRoleMappingEntity();
			entity.setBaseuserid(baseuserid);
			entity.setBaseroleid(e.getBaseroleid());
			entity.setBaseuserrolemid(IDUtil.getId());
			tblBaseUserRoleMappingMapper.deleteByUserid(baseuserid);
			rows += tblBaseUserRoleMappingMapper.insert(entity);
			
			TblBaseUserEntity tblBaseUserEntity = tblBaseUserMapper.getByPkey(baseuserid);
			account += tblBaseUserEntity.getAccount() + ",";
		}
		// 日志
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleMapper.getByPkey(e.getBaseroleid());
		String content = "账号：" + UserInfoUtil.getAccount() + "保存权限信息,将账号" + account.substring(0, account.length() - 1) + "添加进角色" + tblBaseRoleEntity.getRolename();
		tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.SUCCESS);
		return rows;
	}
	
	/**
	 * @Description:取消用户授权
	 * @author:cyf
	 * @param rows
	 * @time:2017年6月6日 下午3:48:10
	 */
	public int deleteByRoidAndUserid(TblBaseUserRoleMappingEntity e, int rows) {
		String[] useridArray = e.getBaseuserid().split(",");
		String account = "";
		for (String baseuserid : useridArray) {
			/**
			 * 去除父级关系
			 */
			TblBaseUserEntity tblBaseUserEntity = tblBaseUserMapper.getByPkey(baseuserid);
			TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleMapper.selectRoleidByUserid(baseuserid);
			if (tblBaseRoleEntity != null && tblBaseRoleEntity.getRolename().equals("现场施工巡查员")) {
				tblBaseUserEntity.setPbaseuserid("");
				tblBaseUserMapper.update(tblBaseUserEntity);
			}
			
			TblBaseUserRoleMappingEntity entity = new TblBaseUserRoleMappingEntity();
			entity.setBaseroleid(e.getBaseroleid());
			entity.setBaseuserid(baseuserid);
			rows += tblBaseUserRoleMappingMapper.deleteByRoidAndUserid(entity);
			
			account += tblBaseUserEntity.getAccount() + ",";
		}
		// 日志
		TblBaseRoleEntity tblBaseRoleEntity = tblBaseRoleMapper.getByPkey(e.getBaseroleid());
		String content = "账号：" + UserInfoUtil.getAccount() + "移除权限信息,将账号" + account.substring(0, account.length() - 1) + "从角色" + tblBaseRoleEntity.getRolename() + "移除";
		tblBaseLogService.insertLog(WfConstants.USERMODULE, content, WfConstants.SUCCESS);
		return rows;
	}
	
	public List<TblBaseUserEntity> selectUnAddUsersPagination(TblBaseUserEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseUserRoleMappingMapper.selectUnAddUsersPagination(e, rowBounds);
	}
	
	public List<TblBaseUserEntity> selectAddedUsersByRoleid(TblFlowTaskRoleMappingEntity taskRoleMappingEntity) {
		return tblBaseUserRoleMappingMapper.selectAddedUsersByRoleid(taskRoleMappingEntity);
	}
	
	public List<TblBaseUserEntity> selectAddedPusersByRoleid(TblFlowTaskRoleMappingEntity taskRoleMappingEntity) {
		return tblBaseUserRoleMappingMapper.selectAddedPusersByRoleid(taskRoleMappingEntity);
	}
	
	public int selectUnAddUsersCount(TblBaseUserEntity e) {
		return tblBaseUserRoleMappingMapper.selectUnAddUsersCount(e);
	}
	
}