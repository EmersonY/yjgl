package com.kingtopinfo.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kingtopinfo.app.entity.YjgAppversionEntity;
import com.kingtopinfo.app.entity.YjgJgxxappEntity;
import com.kingtopinfo.base.entity.TblBaseImageEntity;
import com.kingtopinfo.base.entity.TblBaseTypeEntity;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.entity.TblBaseUserExtEntity;
import com.kingtopinfo.yjg.entity.YjgGrxxEntity;
import com.kingtopinfo.yjg.entity.YjgSjczEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;
import com.kingtopinfo.yjg.entity.YjgTxlEntity;

/**
 * @ClassName mapper.YjgSjdjMapper
 * @Description YJG_SJDJ表数据库操作接口
 * @author cyf
 * @date 2017-08-24 10:56:59
 * @version 1.0
 * @remark create by generator
 */
public interface YjgAppSjdjMapper {
	
	List<YjgTxlEntity>selectbwlforTimeanduser(YjgTxlEntity e);
	YjgSjdjEntity selecttaskid(YjgSjdjUserMappingEntity e);
	YjgAppversionEntity getversion();
	int SaveOrUpdateGrxx(TblBaseUserExtEntity e);
	int insertJgxx(YjgJgxxappEntity jgxx);
	int deleteJgxx(YjgJgxxappEntity jgxx);
	int updateps(TblBaseUserEntity e);
	int editJgxx(YjgJgxxappEntity jgxx);
	List<YjgJgxxappEntity> selectJgxx(YjgJgxxappEntity jgxx);
	int getCzCount(String sjdjid);
	int getCount(YjgSjdjEntity e);
	TblBaseUserEntity getUsername(String baseuserid);
	List<YjgSjdjEntity> selectPagination(YjgSjdjEntity e, RowBounds rowBounds);
	List<YjgSjdjEntity> selectTaskPagination(YjgSjdjEntity e, RowBounds rowBounds);
	YjgSjdjEntity getJbEntity(YjgSjdjEntity e);
	List<TblBaseImageEntity> getJbxxZp(YjgSjdjEntity e);
	List<YjgSjczEntity> getczlist(String sjdjid, RowBounds rowBounds);
	List<YjgSjczEntity> getczlist(String sjdjid);
	YjgSjdjEntity getxmlcID(String sjdjid);
	int delete(String sjdjid);
	TblBaseUserEntity selectuserbyid(String baseuserid);
	int deleteFile(String filepath);
	int getTaskCount(YjgSjdjEntity e);
	List<YjgTxlEntity> selectTxlPagination(YjgTxlEntity e,RowBounds rowBounds);
	int getTxlCount(YjgTxlEntity e);
	List<YjgTxlEntity> getTxlList(String baseuserid);
	List<YjgTxlEntity> getBwlList(String baseuserid);
	int updatetxl(YjgTxlEntity e);
	int inserttxl(YjgTxlEntity e);
	int deletetxl(YjgTxlEntity e);
	List<TblBaseTypeEntity> getTypes(String code);
	List<String> getTypename(String code);
	List<String> getTypeValue(String code);
	int updatebwl(YjgTxlEntity e);
	int insertbwl(YjgTxlEntity e);
	int deletebwl(YjgTxlEntity e);
	int updatebwlzt(YjgTxlEntity e);
	YjgGrxxEntity getGrxx(String baseuserid);
	
}