package com.kingtopinfo.yjg.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.service.ActWorkFlowService;
import com.kingtopinfo.app.mapper.YjgAppSjdjMapper;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.mapper.TblBaseUserMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.AppPush;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;
import com.kingtopinfo.yjg.mapper.YjgSjdjUserMappingMapper;

/**
 * @ClassName service.YjgSjdjUserMappingService
 * @Description YJG_SJDJ_USER_MAPPING表服务类
 * @author cyf
 * @date 2017-09-21 17:44:54
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgSjdjUserMappingService {
	
	@Autowired
	private YjgSjdjUserMappingMapper	yjgSjdjUserMappingMapper;
	@Autowired
	private ActWorkFlowService			actWorkFlowService;
	@Autowired
	private YjgSjdjService				yjgSjdjService;
	@Autowired
	private TblBaseUserMapper			tblBaseUserMapper;
	@Autowired
	private YjgAppSjdjMapper yjgAppSjdjMapper;
	
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            YjgSjdjUserMappingEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	public int getCount(YjgSjdjUserMappingEntity e) {
		return yjgSjdjUserMappingMapper.getCount(e);
	}
	public YjgSjdjUserMappingEntity getByUserIdAndSjdjId(YjgSjdjEntity yjgSjdjEntity,int firstDeny) {
		String firstDenyStr = String.valueOf(firstDeny);
		return yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), yjgSjdjEntity.getBaseuserid(), firstDenyStr);

	}
	

	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgSjdjUserMappingEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgSjdjUserMappingEntity集合
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	public List<YjgSjdjUserMappingEntity> selectPagination(YjgSjdjUserMappingEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjUserMappingMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 查询全部信息
	 * @return YjgSjdjUserMappingEntity集合
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	public List<YjgSjdjUserMappingEntity> select() {
		return yjgSjdjUserMappingMapper.select();
	}
	
	
	/**
	 * @Description 按yjgsjdjusermappingid查询YjgSjdjUserMappingEntity信息
	 * @param yjgsjdjusermappingid
	 *            主键yjgsjdjusermappingid
	 * @return YjgSjdjUserMappingEntity实体
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	public YjgSjdjUserMappingEntity getByPkey(String yjgsjdjusermappingid) {
		return yjgSjdjUserMappingMapper.getByPkey(yjgsjdjusermappingid);
	}
	
	/**
	 * @Description 添加YjgSjdjUserMappingEntity信息
	 * @param e
	 *            YjgSjdjUserMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @param idArray
	 * @date 2017-09-21 17:44:54
	 */
	public int firstYesproperty(YjgSjdjUserMappingEntity e, String[] idArray) {
		int rows = 0;
		String assignees = "";
		for (String userid : idArray) {
			e.setBaseuserid(userid);
			e.setSfjd(0);
			e.setSjdjusermappingid(IDUtil.getId());
			e.setType("1");
			rows += yjgSjdjUserMappingMapper.insert(e);
			assignees += userid + ",";
		}
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjService.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSqzt(1);
		yjgSjdjEntity.setUpdatetime(new Date());
		rows = yjgSjdjService.update(yjgSjdjEntity);
		
		actWorkFlowService.claim(e.getTaskid(), UserInfoUtil.getBaseuserid());
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("assignees", assignees.substring(0, assignees.length() - 1));
		boolean flag = actWorkFlowService.completeTask(e.getTaskid(), variables);
		if (flag) {
			return rows;
		} else {
			return 0;
		}
		
	}
	
	public int firstYesproperty(YjgSjdjUserMappingEntity e, String[] idArray, String baseuserid) {
		int rows = 0;
		String assignees = "";
		for (String userid : idArray) {
			e.setBaseuserid(userid);
			e.setSfjd(0);
			e.setSjdjusermappingid(IDUtil.getId());
			e.setType("1");
			rows += yjgSjdjUserMappingMapper.insert(e);
			assignees += userid + ",";
		}
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjService.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSqzt(1);
		rows = yjgSjdjService.updateforapp(yjgSjdjEntity);
		
		actWorkFlowService.claim(e.getTaskid(), baseuserid);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("assignees", assignees.substring(0, assignees.length() - 1));
		variables.put("option", e.getOpinion());
		boolean flag = actWorkFlowService.completeTask(e.getTaskid(), variables);
		if (flag) {
			final String sjid =  e.getSjdjid();
			final int _rows = rows;
			final String[]  _idArray = idArray;
			new Thread(new Runnable() {
		        @Override
		        public void run() {
		            // TODO Auto-generated method stub
		        	if(_rows > 0){
						YjgSjdjEntity  yen = yjgSjdjService.selecttaskid(sjid);
						AppPush.pushInfo(yen,_idArray,2);
					}
		        }
		    }).start();
			
			return rows;
		} else {
			return 0;
		}
		
	}
	
	/**
	 * @Description 修改YjgSjdjUserMappingEntity信息
	 * @param e
	 *            YjgSjdjUserMappingEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	public int update(YjgSjdjUserMappingEntity e) {
		return yjgSjdjUserMappingMapper.update(e);
	}
	
	/**
	 * @Description 按yjgsjdjusermappingid删除YjgSjdjUserMappingEntity信息
	 * @param yjgsjdjusermappingid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	public int delete(String yjgsjdjusermappingid) {
		return yjgSjdjUserMappingMapper.delete(yjgsjdjusermappingid);
	}
	
	/**
	 * @Description 按yjgsjdjusermappingid集合批量删除YjgSjdjUserMappingEntity信息
	 * @param idArray
	 *            yjgsjdjusermappingid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-09-21 17:44:54
	 */
	public int deleteBatch(String[] idArray) {
		int row = 0;
		for (String id : idArray) {
			row += delete(id);
		}
		return row;
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: TODO
	 * @author cyf
	 * @date 2017年9月21日 下午5:49:45
	 */
	public int getUnUserCount(TblBaseUserEntity e) {
		return yjgSjdjUserMappingMapper.getUnUserCount(e);
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: TODO
	 * @author cyf
	 * @date 2017年9月21日 下午5:49:37
	 */
	public List<YjgSjdjUserMappingEntity> selectUnUserPagination(TblBaseUserEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjdjUserMappingMapper.selectUnUserPagination(e, rowBounds);
	}
	
	public List<TblBaseUserEntity> selectUsered(TblBaseUserEntity e) {
		return yjgSjdjUserMappingMapper.selectUsered(e);
	}
	
	public List<TblBaseUserEntity> selectRefuseUsered(TblBaseUserEntity e) {
		return yjgSjdjUserMappingMapper.selectRefuseUsered(e);
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 确认权属重新派单
	 * @author cyf
	 * @date 2017年9月30日 上午9:06:34
	 */
	public int yesproperty(YjgSjdjUserMappingEntity e, String[] idArray, int type) {
		
		int rows = 0;
		List<YjgSjdjUserMappingEntity> list = yjgSjdjUserMappingMapper.getBySjdjId(e.getSjdjid());
		for (YjgSjdjUserMappingEntity e1 : list) {
			yjgSjdjUserMappingMapper.delete(e1.getSjdjusermappingid());
		}
		
		// 完成任务
		String assignees = "";
		for (String userid : idArray) {
			e.setBaseuserid(userid);
			e.setSfjd(0);
			e.setSjdjusermappingid(IDUtil.getId());
			e.setType("1");
			rows += yjgSjdjUserMappingMapper.insert(e);
			assignees += userid + ",";
		}
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjService.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSqzt(1);
		yjgSjdjEntity.setUpdatetime(new Date());
		if (type == 1) {
			rows += yjgSjdjService.updateforapp(yjgSjdjEntity);
			actWorkFlowService.claim(e.getTaskid(), yjgSjdjEntity.getBaseuserid());
		} else {
			rows += yjgSjdjService.update(yjgSjdjEntity);
			actWorkFlowService.claim(e.getTaskid(), UserInfoUtil.getBaseuserid());
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("assignees", assignees.substring(0, assignees.length() - 1));
		variables.put("is_dd", 0);
		boolean flag = actWorkFlowService.completeTask(e.getTaskid(), variables);
		if (flag) {
			final String sjid =  e.getSjdjid();
			final int _rows = rows;
			final String[] _idArray = idArray;
			new Thread(new Runnable() {
		        @Override
		        public void run() {
		            // TODO Auto-generated method stub
		        	if(_rows > 0){
						YjgSjdjEntity  yen = yjgSjdjService.selecttaskid(sjid);
						AppPush.pushInfo(yen,_idArray,2);
					}
		        }
		    }).start();
			
			return rows;
		} else {
			return 0;
		}
	}
	
}