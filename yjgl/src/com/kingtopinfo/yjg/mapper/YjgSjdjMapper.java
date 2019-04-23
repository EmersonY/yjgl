package com.kingtopinfo.yjg.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.yjg.entity.YjgBaseSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgJgSjCxVo;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjTimeEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;
import com.kingtopinfo.yjg.entity.YjgYwtjEntity;

/**
 * @ClassName mapper.YjgSjdjMapper
 * @Description YJG_SJDJ表数据库操作接口
 * @author cyf
 * @date 2017-08-24 10:56:59
 * @version 1.0
 * @remark create by generator
 */
public interface YjgSjdjMapper {
	
	YjgSjdjEntity selecttaskid(String sjdjid);
	int getCount(YjgSjdjEntity e);
	List<YjgSjdjEntity> selectPagination(YjgSjdjEntity e, RowBounds rowBounds);
	List<YjgSjdjEntity> selectTaskPagination(YjgSjdjEntity e, RowBounds rowBounds);
	List<YjgSjdjEntity> select();
	YjgSjdjEntity getByPkey(String sjdjid);
	int insert(YjgSjdjEntity e);
	int update(YjgSjdjEntity e);
	int delete(String sjdjid);
	List<YjgSjdjEntity> listChildSjdj(String sjdjid);
	int separate(String id);
	int getTaskCount(YjgSjdjEntity e);
	List<YjgSjdjEntity> selectTaskedPagination(YjgSjdjEntity e, RowBounds rowBounds);
	List<YjgJgSjCxVo> selectByJgxxAndSjxx(YjgJgSjCxVo vo);
	int getTaskedCount(YjgSjdjEntity e);
	List<YjgSjdjEntity> selectBjtaskPagination(YjgSjdjEntity e, RowBounds rowBounds);
	int getBjtaskCount(YjgSjdjEntity e);
	int getCountByXxly(YjgSjdjEntity e);
	int getCountByExceptXxly(YjgSjdjEntity e);
	int getCountBySjlx(YjgSjdjEntity e);
	int getBhCountByGglx(YjgBaseSjdjEntity e);
	int getClCountByGglx(YjgBaseSjdjEntity e);
	int getJgtjDetailCount(YjgSjdjEntity e);
	int getJglxCount(YjgSjdjEntity e);
	int getTaskRecentCount(YjgSjdjEntity e);
	List<YjgBaseSjdjEntity> getCountGroupByJglx(YjgYwtjEntity e);
	List<YjgBaseSjdjEntity> getCountGroupByDlid(YjgYwtjEntity e);
	List<YjgBaseSjdjEntity> getCountGroupByJgid(YjgYwtjEntity e);
	List<YjgSjdjEntity> selectYjsjJlPagination(YjgSjdjEntity e, RowBounds rowBounds);
	List<YjgSjdjEntity> exportDataBytime(YjgSjdjTimeEntity e);
	List<YjgSjdjEntity> selectYwtjPagination(YjgSjdjEntity e, RowBounds rowBounds);
	List<YjgSjdjEntity> selectJglxPagination(YjgSjdjEntity e, RowBounds rowBounds);


}