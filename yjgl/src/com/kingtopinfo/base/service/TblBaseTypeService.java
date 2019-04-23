package com.kingtopinfo.base.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.base.entity.TblBaseTypeEntity;
import com.kingtopinfo.base.mapper.TblBaseTypeMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.IDUtil;

/**
 * @ClassName com.kingtopinfo.service.TblBaseTypeService
 * @Description TBL_BASE_TYPE表服务类
 * @author cyf
 * @date 2017-6-28 15:45:37
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class TblBaseTypeService {
	
	@Autowired
	private TblBaseTypeMapper tblBaseTypeMapper;
	
	public List<TblBaseTypeEntity> selectByCode(String code){
		return tblBaseTypeMapper.selectByCode(code);
	}
	
	public List<TblBaseTypeEntity> selectAllByCode(String code){
		return tblBaseTypeMapper.selectAllByCode(code);
	}
	
	/**
	 * @Description:增加
	 * @author:cyf
	 * @time:2017年6月28日 下午2:43:42
	 */
	public int insert(TblBaseTypeEntity e){
		e.setBasetypeid(IDUtil.getId());
		int maxSequ = selectMaxSequ();
		e.setSequ(maxSequ+1);
		return tblBaseTypeMapper.insert(e);
	}
	public String getssdlDate(String name){
		return tblBaseTypeMapper.getssdlDate(name);
	}
	
	/**
	 * @Description:更新
	 * @author:cyf
	 * @time:2017年6月28日 下午2:42:56
	 */
	public int update(TblBaseTypeEntity e){
		TblBaseTypeEntity tblBaseTypeEntity = getByKey(e.getBasetypeid());
		tblBaseTypeEntity.setUpdatetime(new Date());
		tblBaseTypeEntity.setUpdateuserid(UserInfoUtil.getBaseuserid());
		tblBaseTypeEntity.setName(e.getName());
		tblBaseTypeEntity.setState(e.getState());
		tblBaseTypeEntity.setValue(e.getValue());
		if (e.getModule() != null) {
			tblBaseTypeEntity.setModule(e.getModule());
		}
		
		if (tblBaseTypeEntity.getBasetypepid() == null) {
			e = new TblBaseTypeEntity();
			e.setUpdatetime(new Date());
			e.setUpdateuserid(UserInfoUtil.getBaseuserid());
			e.setCode(tblBaseTypeEntity.getCode());
			e.setState(tblBaseTypeEntity.getState());
			tblBaseTypeMapper.updateByCode(e);
		}
		return tblBaseTypeMapper.update(tblBaseTypeEntity);
	}
	
	/**
	 * @Description:删除
	 * @author:cyf
	 * @time:2017年6月28日 下午2:43:03
	 */
	public int delete(String id) {
		return tblBaseTypeMapper.delete(id);
	}
	
	/**
	 * @Description:查询字典信息
	 * @author:cyf
	 * @time:2017年6月28日 下午2:43:12
	 */
	public List<TblBaseTypeEntity> selectMenu(TblBaseTypeEntity tblBaseTypeEntity) {
		return tblBaseTypeMapper.selectMenu(tblBaseTypeEntity);
	}
	
	/**
	 * @Description:通过父类型查询数量
	 * @author:cyf
	 * @time:2017年6月28日 下午2:43:24
	 */
	public int selectCountByPid(String pid) {
		return tblBaseTypeMapper.selectCountByPid(pid);
	}
	
	/**
	 * @Description:递归删除字典
	 * @author:cyf
	 * @time:2017年6月28日 下午2:42:33
	 */
	public int deleteLoop(String[] idArray) {
		int rows = 0;
		for (String id : idArray) {
			rows += deleteLeafLoop(id, rows);
		}
		return rows;
	}
	
	public int deleteLeafLoop(String id, int rows) {
		TblBaseTypeEntity tblBaseTypeEntity = tblBaseTypeMapper.getByKey(id);
		if (tblBaseTypeEntity != null) {
			List<TblBaseTypeEntity> list = tblBaseTypeMapper.selectPidByIdAndCode(tblBaseTypeEntity);
			if (list.size() > 0) {
				for (TblBaseTypeEntity e : list) {
					deleteLeafLoop(e.getBasetypeid(), rows);
				}
			}
			rows += delete(id);
		}
		return rows;
	}
	
	/**
	 * @Description:查询最大序列
	 * @author:cyf
	 * @time:2017年6月28日 下午2:42:13
	 */
	public int selectMaxSequ(){
		Integer i = tblBaseTypeMapper.selectMaxSequ();
		if(null == i){
			return 0;
		}else{
			return Integer.valueOf(i);
		}
	}
	
	/**
	 * @Description:交换seq序列
	 * @author:cyf
	 * @time:2017年6月28日 下午2:41:59
	 */
	public int moveUpOrDown(String type, String checkId, Integer checkSequ, String targetId, Integer targetSequ) {
		TblBaseTypeEntity checkEntity  = new TblBaseTypeEntity();
		checkEntity.setBasetypeid(checkId);
		checkEntity.setSequ(targetSequ);
		int row = tblBaseTypeMapper.updateSequ(checkEntity);
		
		TblBaseTypeEntity targetEntity = new TblBaseTypeEntity();
		targetEntity.setBasetypeid(targetId);
		targetEntity.setSequ(checkSequ);
		row += tblBaseTypeMapper.updateSequ(targetEntity);
		
		checkEntity = getByKey(checkId);
		checkEntity.setType(type);
		updateVerByCode(checkEntity);
		return row;
	}
	
	/**
	 * @Description:通过主键查询实体
	 * @author:cyf
	 * @time:2017年6月28日 下午2:41:46
	 */
	public TblBaseTypeEntity getByKey(String id) {
		return tblBaseTypeMapper.getByKey(id);
	}

	/**
	 * @Description://通过代码类型获取代码版本
	 * @author:cyf
	 * @time:2017年6月28日 下午2:40:33
	 */
	public int selectVerByCode(TblBaseTypeEntity e) {
		return tblBaseTypeMapper.selectVerByCode(e);
	}
	
	/**
	 * @Description:更新最近版本
	 * @author:cyf
	 * @time:2017年6月28日 下午2:41:05
	 */
	public int updateVerByCode(TblBaseTypeEntity e) {
		
		TblBaseTypeEntity TblBaseTypeEntity = new TblBaseTypeEntity();
		int ver = selectVerByCode(e);
		if(ver < 99999){
			ver = ver + 1;
		}else{
			ver = 0;
		}
		TblBaseTypeEntity.setCode(e.getCode());
		TblBaseTypeEntity.setVer(ver);
		TblBaseTypeEntity.setUpdatetime(new Date());
		TblBaseTypeEntity.setType(e.getType());
		return tblBaseTypeMapper.updateVerByCode(TblBaseTypeEntity);
	}
	
	/**
	 * @Description:批量删除
	 * @author:cyf
	 * @time:2017年6月28日 下午2:41:21
	 */
	public int deleteBatch(String[] idArray) {
		int row = 0;
		for (String baseuserid : idArray) {
			row += delete(baseuserid);
		}
		return row;
	}
	
	/**
	 * @Description:通过code和value查询字典信息
	 * @author:cyf
	 * @time:2017年6月28日 下午2:44:30
	 */
	public List<TblBaseTypeEntity> selectNameAndValueByCode(String code) {
		return tblBaseTypeMapper.selectNameAndValueByCode(code);
	}
	
	public TblBaseTypeEntity selectValueByCodeAndName(TblBaseTypeEntity e) {
		return tblBaseTypeMapper.selectValueByCodeAndName(e);
	}
	
}