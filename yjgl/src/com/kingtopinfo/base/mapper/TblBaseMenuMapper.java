package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.base.entity.TblBaseMenuEntity;
import com.kingtopinfo.base.entity.TblBaseMenuListEntity;

/**
 * @ClassName com.kingtopinfo.base.mapper.TBaseMenuMapper
 * @Description T_BASE_MENU表数据库操作接口
 * @author dzb@kingtopinfo.com
 * @date 2014-02-20 09:12:37
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseMenuMapper {
	
	List<TblBaseMenuListEntity> select();
	
	List<TblBaseMenuListEntity> selectMenuTreeByRoleId();
	TblBaseMenuEntity getByKey(String id);
	List<TblBaseMenuEntity> selectMenuByRoids(@Param("list") List<String> list);
	List<TblBaseMenuEntity> selectMenuByPid(String pid);
	int selectMaxSequ();
	int selectMinSequ();
	TblBaseMenuEntity secTopMaxMsg(String basemenuid);
	TblBaseMenuEntity secTopMinMsg(String basemenuid);
	int insert(TblBaseMenuEntity e);
	int update(TblBaseMenuEntity e);
	List<TblBaseMenuEntity> getAll();
	List<TblBaseMenuEntity> selectByPid(String pid);
	int delete(String basemenuid);
	int deleteByMenuid(String menuid);
	int move(TblBaseMenuEntity e);
	int updateSequ(TblBaseMenuEntity e);
	List<TblBaseMenuEntity> selectByRole(@Param("list") List<String> list, @Param("basemenupid") String basemenupid);
	List<String> selectRoleByUserId(String baseuserid);
	List<String> selectUrlByRoleId(String roleid);
	List<TblBaseMenuEntity> selectAllUrl();
	List<TblBaseMenuEntity> selectByUrl(String url);
	TblBaseMenuEntity selectByKey(String basemenuid);
	List<String> selectRoleByUrl(String src);
	int getCount(TblBaseMenuEntity e);
	List<TblBaseMenuEntity> selectPagination(TblBaseMenuEntity e, RowBounds rowBounds);
	List<TblBaseMenuEntity> selectroleOfMenu(@Param("roleid") String roleid);

}