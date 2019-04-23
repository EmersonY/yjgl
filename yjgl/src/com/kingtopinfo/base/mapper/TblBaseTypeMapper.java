package com.kingtopinfo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kingtopinfo.base.entity.TblBaseTypeEntity;

/**
 * @ClassName com.kingtopinfo.mapper.TBaseTypeMapper
 * @Description T_BASE_TYPE表数据库操作接口
 * @author dzb@kingtopinfo.com
 * @date 2013-11-19 15:45:37
 * @version 1.0
 * @remark create by generator
 */
public interface TblBaseTypeMapper {
	
	String getssdlDate(String name);
	int insert(TblBaseTypeEntity e);
	int update(TblBaseTypeEntity e);
	int updateByCode(TblBaseTypeEntity e);
	int delete(String id);
	int move(TblBaseTypeEntity e);
	List<TblBaseTypeEntity> selectByCode(String code);
	List<TblBaseTypeEntity> selectChildByCode(String code);
	List<TblBaseTypeEntity> selectAllByCode(String code);
	List<TblBaseTypeEntity> selectMenu(TblBaseTypeEntity tblBaseTypeEntity);
	List<TblBaseTypeEntity> selectByCodeAndValue(@Param("code") String code, @Param("value") String value);
	int selectCountByPid(String pid);
	Integer selectMaxSequ();
	int updateSequ(TblBaseTypeEntity e);
	TblBaseTypeEntity getByKey(String id);
	List<TblBaseTypeEntity> selectPidByIdAndCode(TblBaseTypeEntity e);
	int selectVerByCode(TblBaseTypeEntity e);
	int updateVerByCode(TblBaseTypeEntity e);
	List<TblBaseTypeEntity> selectNameAndValueByCode(String code);
	TblBaseTypeEntity selectValueByCodeAndName(TblBaseTypeEntity e);
	
}