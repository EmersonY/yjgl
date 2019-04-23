package com.kingtopinfo.yjg.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.activiti.service.ActWorkFlowService;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.mapper.TblBaseUserMapper;
import com.kingtopinfo.base.mapper.TblBaseUserRoleMappingMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.util.AppPush;
import com.kingtopinfo.base.util.IDUtil;
import com.kingtopinfo.base.util.MyBatisUtil;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjUserMappingEntity;
import com.kingtopinfo.yjg.entity.YjgSjqqEntity;
import com.kingtopinfo.yjg.mapper.YjgSjdjMapper;
import com.kingtopinfo.yjg.mapper.YjgSjdjUserMappingMapper;
import com.kingtopinfo.yjg.mapper.YjgSjqqMapper;

/**
 * @ClassName service.YjgSjqqService
 * @Description YJG_SJQQ表服务类
 * @author cyf
 * @date 2017-08-24 11:25:35
 * @version 1.0
 * @remark create by generator
 */
@Service
@Transactional
public class YjgSjqqService {
	
	@Autowired
	private YjgSjqqMapper					yjgSjqqMapper;
	@Autowired
	private YjgSjdjMapper					yjgSjdjMapper;
	@Autowired
	private YjgSjdjUserMappingMapper		yjgSjdjUserMappingMapper;
	@Autowired
	private ActWorkFlowService				actWorkFlowService;
	@Autowired
	private TblBaseUserMapper				yblBaseUserMapper;
	@Autowired
	private TblBaseUserRoleMappingMapper	tblBaseUserRoleMappingMapper;
	@Autowired
	private TblBaseUserMapper				tblBaseUserMapper;
	@Autowired
	YjgSjdjService yjgSjdjService;
	/**
	 * @Description 按条件查询总条数
	 * @param e
	 *            YjgSjqqEntity实体
	 * @return 条数
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	public int getCount(YjgSjqqEntity e) {
		return yjgSjqqMapper.getCount(e);
	}
	
	/**
	 * @Description 按条件分页查询
	 * @param e
	 *            YjgSjqqEntity实体
	 * @param rowBounds
	 *            分页实体
	 * @return YjgSjqqEntity集合
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	public List<YjgSjqqEntity> selectPagination(YjgSjqqEntity e, Integer rows, Integer page) {
		RowBounds rowBounds = MyBatisUtil.rowBounds(rows, page);
		return yjgSjqqMapper.selectPagination(e, rowBounds);
	}
	
	/**
	 * @Description 按sjqsid查询YjgSjqqEntity信息
	 * @param sjqsid
	 *            主键sjqsid
	 * @return YjgSjqqEntity实体
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	public YjgSjqqEntity getByPkey(String sjqsid) {
		return yjgSjqqMapper.getByPkey(sjqsid);
	}
	
	/**
	 * @Description 添加YjgSjqqEntity信息
	 * @param e
	 *            YjgSjqqEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	public int insert(YjgSjqqEntity e) {
		return yjgSjqqMapper.insert(e);
	}
	
	/**
	 * @Description 修改YjgSjqqEntity信息
	 * @param e
	 *            YjgSjqqEntity实体
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	public int update(YjgSjqqEntity e) {
		return yjgSjqqMapper.update(e);
	}
	
	/**
	 * @Description 按sjqsid删除YjgSjqqEntity信息
	 * @param sjqsid
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 11:25:35
	 */
	public int delete(String sjqsid) {
		return yjgSjqqMapper.delete(sjqsid);
	}
	
	/**
	 * @Description 按sjqsid集合批量删除YjgSjqqEntity信息
	 * @param idArray
	 *            sjqsid集合
	 * @return 影响条数
	 * @author cyf
	 * @date 2017-08-24 11:25:35
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
	 * @Description: 通过事件登记ID和权属ID查询事件权属
	 * @author cyf
	 * @date 2017年9月7日 上午11:33:22
	 */
	public YjgSjqqEntity findSjqqBySjdjid(YjgSjqqEntity yjgSjqqEntity) {
		return yjgSjqqMapper.findSjqqBySjdjid(yjgSjqqEntity.getSjdjid());
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: 接受事件
	 * @author cyf
	 * @param mappingid
	 * @date 2017年9月22日 上午10:47:39
	 */
	public void accesssj(YjgSjdjEntity yjgSjdjEntity, Map<String, Object> resultMap, int type) {
		YjgSjdjEntity e = yjgSjdjMapper.getByPkey(yjgSjdjEntity.getSjdjid());
		TblBaseUserEntity tblBaseUserEntity = new TblBaseUserEntity();
		TblBaseUserEntity pTblBaseUserEntity = null;
		e.setSqzt(4);
		e.setUpdatetime(new Date());
		String userid = "";
		if (type == 1) {
			userid = yjgSjdjEntity.getBaseuserid();
			yjgSjdjEntity.getTaskEntitys().get(0).setAssignee(userid);
			tblBaseUserEntity = yblBaseUserMapper.getByPkey(yjgSjdjEntity.getBaseuserid());
			actWorkFlowService.claim(yjgSjdjEntity.getTaskid(), userid);
			
		} else {
			userid = UserInfoUtil.getBaseuserid();
			yjgSjdjEntity.getTaskEntitys().get(0).setAssignee(userid);
			tblBaseUserEntity = yblBaseUserMapper.getByPkey(userid);
			actWorkFlowService.claim(yjgSjdjEntity.getTaskid(), UserInfoUtil.getBaseuserid());
		}
		yjgSjdjMapper.update(e);
		// 更新二级关系
		YjgSjdjUserMappingEntity yjgSjdjUserMappingEntity = yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), userid, "1");
		if (yjgSjdjUserMappingEntity != null) {
			yjgSjdjUserMappingEntity.setSfjd(1);
			yjgSjdjUserMappingEntity.setCzsj(new Date());
			yjgSjdjUserMappingMapper.update(yjgSjdjUserMappingEntity);
		}
		if (tblBaseUserEntity.getPbaseuserid() != null) {
			yjgSjdjUserMappingEntity = yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), tblBaseUserEntity.getPbaseuserid(), "1");
			if (yjgSjdjUserMappingEntity != null) {
				yjgSjdjUserMappingEntity.setSfjd(1);
				yjgSjdjUserMappingEntity.setCzsj(new Date());
				yjgSjdjUserMappingMapper.update(yjgSjdjUserMappingEntity);
				pTblBaseUserEntity = yblBaseUserMapper.getByPkey(tblBaseUserEntity.getPbaseuserid());
			}
		}
		// 更新三级关系
		yjgSjdjUserMappingEntity = yjgSjdjUserMappingMapper.getByUserIdAndSjdjIdAndType(yjgSjdjEntity.getSjdjid(), userid, "2");
		yjgSjdjUserMappingEntity.setSfjd(1);
		yjgSjdjUserMappingEntity.setCzsj(new Date());
		yjgSjdjUserMappingMapper.update(yjgSjdjUserMappingEntity);
		// 新增事件权属
		YjgSjqqEntity yjgSjqqEntity = new YjgSjqqEntity();
		if (type == 1) {
			yjgSjqqEntity.setBaseuserid(yjgSjdjEntity.getBaseuserid());
			yjgSjqqEntity.setQssgid(yjgSjdjEntity.getBaseuserid());
			tblBaseUserEntity = tblBaseUserMapper.getByPkey(yjgSjdjEntity.getBaseuserid());
			yjgSjqqEntity.setQssgmc(tblBaseUserEntity.getUsername());
			yjgSjqqEntity.setCzr(yjgSjdjEntity.getUserName());
		} else {
			yjgSjqqEntity.setBaseuserid(UserInfoUtil.getBaseuserid());
			yjgSjqqEntity.setQssgid(UserInfoUtil.getBaseuserid());
			yjgSjqqEntity.setQssgmc(UserInfoUtil.getUserName());
			yjgSjqqEntity.setCzr(UserInfoUtil.getUserName());
		}
		
		yjgSjqqEntity.setDbzt("0");
		yjgSjqqEntity.setSjqsid(IDUtil.getId());
		yjgSjqqEntity.setSjdjid(yjgSjdjEntity.getSjdjid());
		yjgSjqqEntity.setIsdel(1);
		yjgSjqqEntity.setCzsj(new Date());
		yjgSjqqEntity.setBz(yjgSjdjEntity.getBz());
		if (pTblBaseUserEntity != null) {
			yjgSjqqEntity.setQsid(pTblBaseUserEntity.getBaseuserid());
			yjgSjqqEntity.setQsmc(pTblBaseUserEntity.getUsername());
		}
		int rows = yjgSjqqMapper.insert(yjgSjqqEntity);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("is_qxfw", 1);
		variables.put("assignee", yjgSjqqEntity.getBaseuserid());
		boolean flag = actWorkFlowService.completeTask(yjgSjdjEntity.getTaskid(), variables, yjgSjdjEntity.getTaskEntitys());
		if (flag) {
			resultMap.put("sec", true);
			resultMap.put("rows", rows);
		} else {
			resultMap.put("sec", false);
			resultMap.put("rows", 0);
		}
	}
	
	/**
	 * @Package com.kingtopinfo.yjg.service
	 * @Description: TODO
	 * @author cyf
	 * @date 2017年9月26日 下午4:41:43
	 */
	public int repair(YjgSjqqEntity e) {
		int rows = 0;
		String processid = actWorkFlowService.findProcessDefinition();
		TblFlowTaskRoleMappingEntity taskRoleMappingEntity = new TblFlowTaskRoleMappingEntity();
		taskRoleMappingEntity.setProcessid(processid);
		taskRoleMappingEntity.setTaskid("czqkqr");
		String assignees = "";
		List<TblBaseUserEntity> list = tblBaseUserRoleMappingMapper.selectByProcessidAndTaskid(taskRoleMappingEntity);
		for (TblBaseUserEntity tblBaseUserEntity : list) {
			if (tblBaseUserEntity != null) {
				assignees += tblBaseUserEntity.getBaseuserid() + ",";
			}
		}
		
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(e.getSjdjid());
		yjgSjdjEntity.setSqzt(5);
		yjgSjdjEntity.setUpdatetime(new Date());
		rows = yjgSjdjMapper.update(yjgSjdjEntity);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("assignees", assignees.substring(0, assignees.length() - 1));
		boolean flag = actWorkFlowService.completeTask(e.getTaskid(), variables);
		if (flag ) {
			final String sjid =  e.getSjdjid();
			final int _rows = rows;
			final 	List<TblBaseUserEntity> _list = list;
			new Thread(new Runnable() {
		        @Override
		        public void run() {
		            // TODO Auto-generated method stub
		        	if(_rows > 0){
						YjgSjdjEntity  yen = yjgSjdjService.selecttaskid(sjid);
						AppPush.pushInfo(yen,_list,1);
					}
		        }
		    }).start();
			
			return rows;
		} else {
			return 0;
		}
	}
	
}