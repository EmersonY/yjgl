package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserRoleMappingEntity;
/**
 * @ClassName mapper.TblBaseUserRoleMappingMapper
 * @Description TBL_BASE_USER_ROLE_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-06-05 14:25:06
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseUserRoleMappingMapper {
	
	int insert(TblBaseUserRoleMappingEntity e);
	int deleteByRoidAndUserid(TblBaseUserRoleMappingEntity e);
	int deleteByUserid(String userid);
	int deleteByRoleid(String roleid);
	List<TblBaseUserEntity> selectAddedUsersByRoleid(TblFlowTaskRoleMappingEntity taskRoleMappingEntity);
	List<TblBaseUserEntity> selectAddedPusersByRoleid(TblFlowTaskRoleMappingEntity taskRoleMappingEntity);
	List<TblBaseUserEntity> selectByProcessidAndTaskid(TblFlowTaskRoleMappingEntity taskRoleMappingEntity);
	List<TblBaseUserEntity> selectUnAddUsersPagination(TblBaseUserEntity e, RowBounds rowBounds);
	int selectUnAddUsersCount(TblBaseUserEntity e);
	List<TblBaseUserEntity> selectSuperUser(@Param("username") String username);

}