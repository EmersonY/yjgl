package com.kingtopinfo.activiti.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.activiti.entity.TblFlowTaskRoleMappingEntity;
import com.kingtopinfo.activiti.mapper.ActProcessMapper;
import com.kingtopinfo.base.entity.TblBaseUserEntity;
import com.kingtopinfo.base.mapper.TblBaseUserMapper;
import com.kingtopinfo.base.mapper.TblBaseUserRoleMappingMapper;
import com.kingtopinfo.base.security.UserInfoUtil;
import com.kingtopinfo.base.service.TblBaseUserRoleMappingService;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.mapper.YjgSjdjMapper;

/**
 * com.kingtopinfo.base.service.TBaseFlowRoleService Description :T_BASE_ROLE表服务类
 * 
 * @author lxc Create at 2016年11月23日 上午11:33:26
 */
@Service()
@Transactional
public class ActTaskService {
	
	@Autowired
	private ActWorkFlowService				workFlowService;
	@Autowired
	private TblFlowTaskRoleMappingService	tblFlowTaskRoleMappingService;
	@Autowired
	private TblBaseUserRoleMappingService	tblBaseUserRoleMappingService;
	@Autowired
	private ActProcessMapper				processMapper;
	@Autowired
	private TblBaseUserMapper				tblBaseUserMapper;
	@Autowired
	private YjgSjdjMapper					yjgSjdjMapper;
	@Autowired
	private TblBaseUserRoleMappingMapper	tblBaseUserRoleMappingMapper;
	
	// 查找第一个任务
	public List<ActTaskEntity> selectFirstTasks(String definitionkey, String starteventkey) {
		List<ActTaskEntity> result = new ArrayList<ActTaskEntity>();
		List<ActTaskEntity> list = workFlowService.selectFirstTasks(definitionkey, starteventkey);
		if (list != null && !list.isEmpty()) {
			for (ActTaskEntity each : list) {
				List<TblBaseUserEntity> candidates = new ArrayList<TblBaseUserEntity>();
				List<TblFlowTaskRoleMappingEntity> list2 = tblFlowTaskRoleMappingService
						.selectByProcessidAndTaskid(workFlowService.getDefinitionIdByDefinitionkeylast(definitionkey), each.getTaskkey());
				if (list2 != null && !list2.isEmpty()) {
					for (TblFlowTaskRoleMappingEntity taskRoleMappingEntity : list2) {
						candidates.addAll(tblBaseUserRoleMappingService.selectAddedUsersByRoleid(taskRoleMappingEntity));
					}
				}
				each.setCandidates(candidates);
				result.add(each);
			}
		}
		return result;
	}
	
	// 查找下一个任务
	public List<ActTaskEntity> selectNextTasks(String taskid, String baseuserid, String username, int type, String sjdjid) {
		YjgSjdjEntity yjgSjdjEntity = yjgSjdjMapper.getByPkey(sjdjid);
		List<ActTaskEntity> result = new ArrayList<ActTaskEntity>();
		List<ActTaskEntity> list = workFlowService.selectNextTasks(taskid);
		TblFlowTaskRoleMappingEntity tblFlowTaskRoleMappingEntity = new TblFlowTaskRoleMappingEntity();
		if (list != null && !list.isEmpty()) {
			for (ActTaskEntity each : list) {
				tblFlowTaskRoleMappingEntity.setProcessid(workFlowService.getTaskByTaskId(taskid).getDefinitionid());
				tblFlowTaskRoleMappingEntity.setTaskid(each.getTaskkey());
				if (type == 1) {
					each.setUserid(baseuserid);
					each.setUsername(username);
				} else {
					each.setUserid(UserInfoUtil.getBaseuserid());
					each.setUsername(UserInfoUtil.getUserName());
					tblFlowTaskRoleMappingEntity.setUsername(username);
				}
				List<TblBaseUserEntity> candidates = new ArrayList<TblBaseUserEntity>();
				// 确认权责派单给施工队和权属
				if (each.getTaskkey().equals("qzqr")) {
					if (type == 1) {
						tblFlowTaskRoleMappingEntity.setPbaseuserid(baseuserid);
						candidates.addAll(tblBaseUserRoleMappingService.selectAddedPusersByRoleid(tblFlowTaskRoleMappingEntity));
						candidates.add(tblBaseUserMapper.getByPkey(baseuserid));
					} else {
						tblFlowTaskRoleMappingEntity.setPbaseuserid(UserInfoUtil.getBaseuserid());
						candidates.addAll(tblBaseUserRoleMappingService.selectAddedPusersByRoleid(tblFlowTaskRoleMappingEntity));
						candidates.add(tblBaseUserMapper.getByPkey(UserInfoUtil.getBaseuserid()));
						}
				} else {
					candidates.addAll(tblBaseUserRoleMappingService.selectAddedUsersByRoleid(tblFlowTaskRoleMappingEntity));
				}
				// 线下派单给井盖办人员
				if (yjgSjdjEntity.getIsline().equals("1") && username != null && ("系统管理员".contains(username) || "井盖办".contains(username))) {
					candidates.addAll(tblBaseUserRoleMappingMapper.selectSuperUser(username));
				}
				// 去重
				Set<TblBaseUserEntity> set = new HashSet<TblBaseUserEntity>();
				set.addAll(candidates);
				candidates = new ArrayList<TblBaseUserEntity>(set);
				// 放值
				each.setCandidates(candidates);
				result.add(each);
			}
		}
			return result;
	}
	
	// 查找下一个任务
	public List<ActTaskEntity> selectNextKey(String taskid) {
		List<ActTaskEntity> list = workFlowService.selectNextTasks(taskid);
		return list;
	}
	
	public List<ActTaskEntity> selectPreTasks(String taskid) {
		return workFlowService.selectPreTasks(taskid);
	}
	
	public boolean isCanRollback(String taskid) {
		List<ActTaskEntity> list = workFlowService.selectPreTasks(taskid);
		ActTaskEntity currentTask = workFlowService.getTaskByTaskId(taskid);
		Map<String, Object> variables = workFlowService.selectPreVariables(taskid);
		if (list != null && !list.isEmpty()) {
			for (ActTaskEntity each : list) {
				List<ActTaskEntity> list2 = workFlowService.selectNextTasks(currentTask.getInstanceid(), each.getTaskkey(), variables);
				if (list2 != null && !list2.isEmpty()) {
					if (list2.size() >= 2) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public List<ActTaskEntity> selectHistoryActByInstanceid(YjgSjdjEntity yjgSjdjEntity) {
		List<ActTaskEntity> list = new ArrayList<ActTaskEntity>();
		List<ActTaskEntity> tempList = processMapper.selectHistoryActByInstanceid(yjgSjdjEntity.getInstanceid());
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).getActtype().indexOf("Gateway") > 0 || "startEvent".equals(tempList.get(i).getActtype())) {
				continue;
			}
			if ("endEvent".equals(tempList.get(i).getActtype())) {
				if (yjgSjdjEntity.getSqzt() == 6) {
					tempList.get(i).setTaskname("已兜底");
				}
				if (yjgSjdjEntity.getSqzt() == 7) {
					tempList.get(i).setTaskname("已解决");
				}
			}
			list.add(tempList.get(i));
			}
		return list;
		}
	
	/**
	* @Package com.kingtopinfo.activiti.service  
	* @Description: 查询历史操作
	* @author cyf    
	* @date 2018年3月5日 上午9:59:29
	*/
	public String selectHistoryActBySjdjid(String sjdjid) {
		StringBuffer sb = new StringBuffer();
		List<ActTaskEntity> tempList = processMapper.selectHistoryActBySjdjid(sjdjid);
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).getActtype().indexOf("Gateway") > 0 || "startEvent".equals(tempList.get(i).getActtype()) || "endEvent".equals(tempList.get(i).getActtype())) {
				continue;
			}
			if(tempList.get(i).getUsername()!=null&&tempList.get(i).getTaskendtime()!=null&&tempList.get(i).getTaskname() != null){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sb.append(i + "." + tempList.get(i).getUsername());
				sb.append("在" + sf.format(tempList.get(i).getTaskendtime()) );
				sb.append("进行了" + tempList.get(i).getTaskname() + "操作" );
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
}