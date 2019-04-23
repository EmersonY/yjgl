package com.kingtopinfo.base.mapper;

import com.kingtopinfo.base.entity.TblBaseUserExtEntity;

/**
 * @ClassName mapper.TblBaseUserExtMapper
 * @Description TBL_BASE_USER_EXT表数据库操作接口
 * @author cyf
 * @date 2017-06-14 15:36:47
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseUserExtMapper {
	
	TblBaseUserExtEntity getByUserId(String baseuserid);
	int update(TblBaseUserExtEntity e);
	int insert(TblBaseUserExtEntity e);
	
}