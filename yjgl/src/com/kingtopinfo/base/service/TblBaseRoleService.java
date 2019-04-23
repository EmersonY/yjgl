package com.kingtopinfo.base.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseRoleEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.mapper.TblBaseRoleMapper;
import com.kingtopinfo.base.mapper.TblBaseRoleMenuMappingMapper;
import com.kingtopinfo.base.mapper.TblBaseUserMapper;
import com.kingtopinfo.base.mapper.TblBaseUserRoleMappingMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;

/**
 * @ClassName service.TblBaseRoleService
 * @Description TBL_BASE_ROLE表服务类
 * @author cyf
 * @date 2017-06-05 14:21:42
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseRoleService {
	
	@Autowired
	private TblBaseRoleMapper tblBaseRoleMapper;
	@Autowired
	private TblBaseUserMapper	tblBaseUserMapper;
	@Autowired
	private TblBaseUserRoleMappingMapper	tblBaseUserRoleMappingMapper;
	@Autowired
	private TblBaseRoleMenuMappingMapper	tblBaseRoleMenuMappingMapper;
	
	/**
	 * @Description 按baseroleid查询TblBaseRoleEntity信息
	 * @param baseroleid 主键baseroleid
	 * @return TblBaseRoleEntity实体
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */	
	public TblBaseRoleEntity getByPkey(String baseroleid){
		return tblBaseRoleMapper.getByPkey(baseroleid);
	}
	
	/**
	 * @Description 按baseroleid删除TblBaseRoleEntity信息
	 * @param baseroleid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */	
	public int delete(String baseroleid){
		int rows = 0;
		List<TblBaseRoleEntity> list = tblBaseRoleMapper.selectByPid(baseroleid);
		if (list != null && !list.isEmpty()) {
			for (TblBaseRoleEntity each : list) {
				delete(each.getBaseroleid());
			}
		}
		tblBaseUserRoleMappingMapper.deleteByRoleid(baseroleid);
		tblBaseRoleMenuMappingMapper.deleteByRoleid(baseroleid);
		rows += tblBaseRoleMapper.delete(baseroleid);
		return rows;
	}
	

	/**
	 * @Description 按baseroleid集合批量删除TblBaseRoleEntity信息
	 * @param idArray baseroleid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */	
	public int deleteBatch(String[] idArray){
		int row = 0;
		for(String id : idArray){
			row += delete(id);
		}
		return row;
	}
	
	/**
	 * @Description 查询角色信息
	 * @param
	 * @author cyf
	 * @param state
	 * @date 2017-06-05 14:21:42
	 */
	public List<TblBaseRoleEntity> selectRoleTree() {
		return tblBaseRoleMapper.selectRoleTree();
	}
	
	/**
	 * @Description 分页查询用户数量
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	public Integer selectCountByRoleId(TblBaseUserEntity tBaseUserEntity) {
		return tblBaseUserMapper.selectCountByRoleId(tBaseUserEntity);
	}
	
	/**
	 * @Description 分页查询用户信息
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	public List<TblBaseUserEntity> selectPaginationByRoleId(TblBaseUserEntity tBaseUserEntity, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseUserMapper.selectPaginationByRoleId(tBaseUserEntity, rowBounds);
	}
	
	/**
	 * @Description 分页查询其余权限下用户数量
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	public Integer selectUnCountByRoleId(TblBaseUserEntity tBaseUserEntity) {
		return tblBaseUserMapper.selectUnCountByRoleId(tBaseUserEntity);
	}
	
	/**
	 * @Description 分页查询其余权限下用户信息
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	public List<TblBaseUserEntity> selectUnPaginationByRoleId(TblBaseUserEntity tBaseUserEntity, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseUserMapper.selectUnPaginationByRoleId(tBaseUserEntity, rowBounds);
	}
	
	/**
	 * @Description 新增TblBaseRoleEntity信息
	 * @param e
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	public int insert(TblBaseRoleEntity tblBaseRoleEntity) {
		tblBaseRoleEntity.setBaseroleid(IDUtil.getId());
		tblBaseRoleEntity.setState(1);
		return tblBaseRoleMapper.insert(tblBaseRoleEntity);
	}
	
	/**
	 * @Description 修改TblBaseRoleEntity信息
	 * @param e
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	public int update(TblBaseRoleEntity e, TblBaseRoleEntity tblBaseRoleEntity) {
		tblBaseRoleEntity.setRolename(e.getRolename());
		tblBaseRoleEntity.setUpdatetime(new Date());
		tblBaseRoleEntity.setUpdateuserid(UserInfoUtil.getBaseuserid());
		tblBaseRoleEntity.setBaseroletype(e.getBaseroletype());
		return tblBaseRoleMapper.update(tblBaseRoleEntity);
	}
	
	/**
	 * @Description 修改节点信息
	 * @param e
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-06-05 14:21:42
	 */
	public int move(TblBaseRoleEntity e) {
		return tblBaseRoleMapper.move(e);
	}
	
	/**
	 * @Description:查询菜单已包含角色
	 * @author:cyf
	 * @time:2017年6月8日 下午4:41:30
	 */
	public List<TblBaseRoleEntity> selectAddedMenuRoles(String menuid) {
		return tblBaseRoleMapper.selectAddedMenuRoles(menuid);
	}
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            TblBaseRoleEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-07-28 09:48:37
	 */
	public int getCount(TblBaseRoleEntity e) {
		return tblBaseRoleMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            TblBaseRoleEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return TblBaseRoleEntity集合
	 * @author cyf
	 * @date 2017-07-28 09:48:37
	 */
	public List<TblBaseRoleEntity> selectPagination(TblBaseRoleEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return tblBaseRoleMapper.selectPagination(e, rowBounds);
	}

	/**
	 * @Description 查询全部信息
	 * @return TblBaseRoleEntity集合
	 * @author cyf
	 * @date 2017-07-28 09:48:37
	 */
	public List<TblBaseRoleEntity> select() {
		return tblBaseRoleMapper.select();
	}
	
	public List<TblBaseRoleEntity> selectMKMenuRoles(String menuid) {
		return tblBaseRoleMapper.selectMKMenuRoles(menuid);
	}
	
	public boolean checkSuperUser() {
		String selectSuperUserRoleid = tblBaseRoleMapper.selectSuperUserRoleid();
		Collection<GrantedAuthority> authorities = UserInfoUtil.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (selectSuperUserRoleid.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 利用用户ID查询角色ID
	* @author cyf    
	* @date 2018年1月17日 下午3:49:30
	 */
	public TblBaseRoleEntity selectRoleidByUserid(String baseuserid) {
		return tblBaseRoleMapper.selectRoleidByUserid(baseuserid);
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 查询权属信息
	* @author cyf    
	 * @param e 
	* @date 2018年1月18日 下午3:32:53
	 */
	public List<TblBaseRoleEntity> selectSecondRole(TblBaseRoleEntity e) {
		return tblBaseRoleMapper.selectSecondRole(e);
	}
	
	/**
	* @Package com.kingtopinfo.base.service  
	* @Description: 通过用户id查询角色名称
	* @author cyf    
	* @date 2018年1月18日 下午4:20:27
	 */
	public TblBaseRoleEntity selectRoleByUserid(String baseuserid) {
		return tblBaseRoleMapper.selectRoleByUserid(baseuserid);
	}
	
}