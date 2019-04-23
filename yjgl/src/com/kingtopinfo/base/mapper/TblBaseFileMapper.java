package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseFileEntity;

/**
 * @ClassName mapper.TblBaseFileMapper
 * @Description TBL_BASE_FILE表数据库操作接口
 * @author cyf
 * @date 2017-08-18 14:04:49
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseFileMapper {
	
	int saveOrupdatelogo(TblBaseFileEntity e);
	int getCount(TblBaseFileEntity e);
	List<TblBaseFileEntity> selectPagination(TblBaseFileEntity e,RowBounds rowBounds);
	List<TblBaseFileEntity> select();
	TblBaseFileEntity getByPkey(String fileid);
	TblBaseFileEntity getByFilepath(String filepath);
	List<TblBaseFileEntity> getByOperaid(TblBaseFileEntity e);
	int insert(TblBaseFileEntity e);
	int update(TblBaseFileEntity e);
	int delete(String fileid);
	int deleteFilepath(String fileid);
	List<TblBaseFileEntity> selectByOperatypeAndOperaid(TblBaseFileEntity e);

}