package com.kingtopinfo.activiti.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.activiti.entity.TblMkMenuMappingEntity;

/**
 * @ClassName mapper.TblMkMenuMappingMapper
 * @Description TBL_MK_MENU_MAPPING表数据库操作接口
 * @author cyf
 * @date 2017-09-18 10:07:05
 * @version 1.0
 * @remark create by generator
 */
public interface TblMkMenuMappingMapper {
	
	int getCount(TblMkMenuMappingEntity e);
	List<TblMkMenuMappingEntity> selectPagination(TblMkMenuMappingEntity e,RowBounds rowBounds);
	List<TblMkMenuMappingEntity> select();
	TblMkMenuMappingEntity getByPkey(String id);
	int insert(TblMkMenuMappingEntity e);
	int update(TblMkMenuMappingEntity e);
	int delete(String id);
	int deleteByMenuid(String menuid);

}