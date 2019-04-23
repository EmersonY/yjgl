package com.kingtopinfo.activiti.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtopinfo.activiti.entity.ActDefinitionEntity;
import com.kingtopinfo.activiti.entity.ActTaskEntity;
import com.kingtopinfo.yjg.entity.YjgSjdjEntity;
import com.kingtopinfo.yjg.service.YjgSjdjService;

@Service
@Transactional
public class ActWorkFlowService {
	
	@Autowired
	private ProcessEngine processEngine;// 流程引擎bean
	@Autowired
	private RepositoryService repositoryService;//工作流仓储服务
	@Autowired
	private RuntimeService runtimeService;//工作流运行服务 
	@Autowired
	private TaskService taskService;//工作流任务服务
	@Autowired
	private HistoryService historyService;//工作流历史数据服务
	@Autowired
	private ManagementService managementService;//工作流唯一服务
	@Autowired
	private YjgSjdjService		yjgSjdjService;
	/**
	 * Description :页面传入zip压缩包(bpmn和png)部署流程
	 * @param deloymentName 部署的名称
	 * @param zip zip文件
	 * @author lxc
	 * Create at @2016年10月26日.下午2:16:00
	 */
	public Deployment deploymentZIP(String deloymentName,File zip){
		try{
			InputStream in = new FileInputStream(zip);
			ZipInputStream zipInputStream = new ZipInputStream(in);
			Deployment deployment = repositoryService.createDeployment().name(deloymentName).addZipInputStream(zipInputStream).deploy();
			if(deployment != null){
				System.out.println("部署流程\""+deloymentName+"\"成功。");
				return deployment;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Description :查询流程定义的总条数
	 * @author lxc
	 * Create at @2016年10月27日.下午2:22:07
	 */
	public int getProcessDefinitionCount() {
		return (int) repositoryService.createProcessDefinitionQuery().count();
	}
	
	/**
	 * Description :查询流程定义的信息List
	 * @author lxc
	 * Create at @2016年10月27日.下午2:27:13
	 */
	public List<ActDefinitionEntity> selectProcessDefinition(ActDefinitionEntity e,
			int rows, int page) {
		int start = (page-1) * rows;
		RowBounds rowBounds = new RowBounds(start,rows);
		return copyProcessDefinition(repositoryService.createProcessDefinitionQuery()
				.orderByProcessDefinitionCategory().asc()
				.orderByProcessDefinitionName().asc()
				.orderByProcessDefinitionVersion().desc()
				.listPage(rowBounds.getOffset(), rowBounds.getLimit()));
	}
	
	/**
	 * Description :复制流程定义实体为DefinitionEntity实体
	 * @author lxc
	 * Create at @2016年10月27日.下午2:26:15
	 */
	private List<ActDefinitionEntity> copyProcessDefinition(List<ProcessDefinition> list){
		List<ActDefinitionEntity> result = new ArrayList<ActDefinitionEntity>();
		if(list != null && !list.isEmpty()){
			for(ProcessDefinition each : list){
				ActDefinitionEntity d = new ActDefinitionEntity();
				d.setId(each.getId());
				d.setKey(each.getKey());
				d.setName(each.getName());
				d.setVersion(each.getVersion());
				d.setResourceName(each.getResourceName());
				d.setDiagramResourceName(each.getDiagramResourceName());
				d.setDescription(each.getDescription());
				d.setCategory(each.getCategory());
				d.setDeploymentId(each.getDeploymentId());
				d.setTenantId(each.getTenantId());
				result.add(d);
			}
		}	
		return result;
	}
	
	/**
	 * Description :判断流程是否处于运行状态
	 * 				根据实例id获得正在运行的获得的节点的个数
	 * @author lxc
	 * Create at @2016年10月28日.上午9:59:13
	 */
	public long getExecutionCountByDefinitionId(String definitionId){
		return runtimeService.createExecutionQuery().processDefinitionId(definitionId).count();
	}

	/**
	 * 删除流程部署
	 * @param deploymentId 流程部署id
	 * @param cascade 是否级联删除
	 * @return
	 */
	public void deleteProcessByDeploymentId(String deploymentId,boolean cascade){
		 repositoryService.deleteDeployment(deploymentId,cascade);
	}

	/**
	 * Description :流程启动 第一次
	 * @author lxc
	 * Create at @2016年5月11日.下午5:18:36
	 */
	public String getDefinitionIdByDefinitionkeylast(String definitionkey){
		return repositoryService.createProcessDefinitionQuery().processDefinitionKey(definitionkey).latestVersion().singleResult().getId();
	}
	
	/**
	 * 获得该任务的下一节点的taskKey和taskName(返回值集合中只有taskKey和taskName两属性有值，若需要更多信息则可以改写此方法进行获取)
	 * @param processInstanceId 流程实例id
	 * @param taskKey 当前任务key
	 */
	public List<ActTaskEntity> selectFirstTasks(String definitionkey, String starteventkey) {
		List<ActTaskEntity> result = new ArrayList<ActTaskEntity>();
		ProcessDefinition p = repositoryService.createProcessDefinitionQuery().processDefinitionKey(definitionkey).latestVersion().singleResult();
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(p.getId());
		ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(starteventkey);
		ExpressionFactory factory = new ExpressionFactoryImpl();
		return selectCurrentTasks(definition, currActivity, factory, result);
	}
	
	
	
	/**
	 * //获取下一任务节点
	 * 获得该任务的下一节点的taskKey和taskName(返回值集合中只有taskKey和taskName两属性有值，若需要更多信息则可以改写此方法进行获取)
	 * @param processInstanceId 流程实例id
	 * @param taskKey 当前任务key
	 * @param variables 参数
	 */
	public List<ActTaskEntity> selectNextTasks(String processInstanceId,String taskkey,Map<String,Object >variables){	
		List<ActTaskEntity> result = new ArrayList<ActTaskEntity>();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(taskkey);	
		SimpleContext context = new SimpleContext();
		ExpressionFactory factory = new ExpressionFactoryImpl();
		if(variables != null && !variables.isEmpty()){
			Set<String> keys = variables.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				context.setVariable(key, factory.createValueExpression(variables.get(key), Object.class));
			}
		}
		return selectNextTasks(definition, currActivity, factory, result);
	}
	
	/**
	 * 获得该任务的下一节点的taskKey和taskName(返回值集合中只有taskKey和taskName两属性有值，若需要更多信息则可以改写此方法进行获取)
	 * @param taskid 任务id
	 * @param variables 参数
	 */
	public List<ActTaskEntity> selectNextTasks(String taskid) {
		List<ActTaskEntity> result = new ArrayList<ActTaskEntity>();
		HistoricTaskInstance currentTask = historyService.createHistoricTaskInstanceQuery().taskId(taskid).singleResult();
		
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(currentTask.getProcessDefinitionId());
		ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(currentTask.getTaskDefinitionKey());
		ExpressionFactory factory = new ExpressionFactoryImpl();
		return selectNextTasks(definition, currActivity, factory, result);
	}
	
	//递归获取下一节点任务名称集合
	private List<ActTaskEntity> selectNextTasks(ProcessDefinitionImpl definition, PvmActivity pvmActivity, ExpressionFactory factory, List<ActTaskEntity> result) {
		List<PvmTransition> nextTransitionList = pvmActivity.getOutgoingTransitions();
		if(nextTransitionList != null && !nextTransitionList.isEmpty()){
			for(PvmTransition each:nextTransitionList){
				PvmActivity nextActivity = each.getDestination();
				if("userTask".equals(nextActivity.getProperty("type")) || "endEvent".equals(nextActivity.getProperty("type")) ){
					ActTaskEntity task = new ActTaskEntity();
					task.setTaskkey(nextActivity.getId());
					task.setTaskname((String) nextActivity.getProperty("name"));
					result.add(task);
				}else if("exclusiveGateway".equals(nextActivity.getProperty("type")) || "parallelGateway".equals(nextActivity.getProperty("type")) ){
					result = selectNextTasks(definition, nextActivity, factory, result);
				}else if("callActivity".equals(nextActivity.getProperty("type"))){
				}
			}
		}
        return result;  
	}
	
	// 递归获取当前节点任务名称集合
	private List<ActTaskEntity> selectCurrentTasks(ProcessDefinitionImpl definition, PvmActivity pvmActivity, ExpressionFactory factory, List<ActTaskEntity> result) {
		List<PvmTransition> nextTransitionList = pvmActivity.getOutgoingTransitions();
		if (nextTransitionList != null && !nextTransitionList.isEmpty()) {
			for (PvmTransition each : nextTransitionList) {
				PvmActivity currentActivity = each.getSource();
				if ("userTask".equals(currentActivity.getProperty("type")) || "endEvent".equals(currentActivity.getProperty("type"))) {
					ActTaskEntity task = new ActTaskEntity();
					task.setTaskkey(currentActivity.getId());
					task.setTaskname((String) currentActivity.getProperty("name"));
					result.add(task);
				} else if ("exclusiveGateway".equals(currentActivity.getProperty("type")) || "parallelGateway".equals(currentActivity.getProperty("type"))) {
					result = selectNextTasks(definition, currentActivity, factory, result);
				} else if ("callActivity".equals(currentActivity.getProperty("type"))) {
				}
			}
		}
		return result;
	}
	
	public ActTaskEntity getTaskByTaskId(String taskId){
		return copyHistoricTask(historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult());
	}
	
	/**
	 * 复制任务实体为WorkFlowEntity实体
	 * @param task 
	 * @return
	 */
	private ActTaskEntity copyHistoricTask(HistoricTaskInstance task){
		ActTaskEntity t = new ActTaskEntity();
		t.setTaskid(task.getId());
		t.setTaskkey(task.getTaskDefinitionKey());
		t.setTaskname(task.getName());
		t.setAssignee(task.getAssignee());
		t.setInstanceid(task.getProcessInstanceId());
		t.setDefinitionid(task.getProcessDefinitionId());
		t.setTaskstarttime(task.getCreateTime());
		t.setTaskendtime(task.getEndTime());
		t.setDuration(task.getDurationInMillis());
		t.setExecutionid(task.getExecutionId());
		t.setParenttaskid(task.getParentTaskId());
		t.setTaskdescription(task.getDescription());
//		t.setOwner(task.getOwner());
//		t.setPriority(task.getPriority());
//		t.setFormKey(task.getFormKey());
//		t.setCategory(task.getCategory());
//		t.setTenantId(task.getTenantId());
//		t.setDueDate(task.getDueDate());
		return t;
	}
	
	/**
	 * 获得该任务的上一节点的taskKey和taskName(返回值集合中只有taskKey和taskName两属性有值，若需要更多信息则可以改写此方法进行获取)
	 * @param taskid 任务id
	 * @param taskKey 当前任务key
	 */
	public List<ActTaskEntity> selectPreTasks(String taskid){	
		List<ActTaskEntity> result = new ArrayList<ActTaskEntity>();
		if(taskid == null || "".equals(taskid)) return result;
		HistoricTaskInstance currentTask = historyService.createHistoricTaskInstanceQuery().taskId(taskid).singleResult();
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(currentTask.getProcessDefinitionId());
		ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(currentTask.getTaskDefinitionKey());
		//设置断言条件
		SimpleContext context = new SimpleContext();
		ExpressionFactory factory = new ExpressionFactoryImpl();
		Map<String, Object> variables = taskService.getVariables(taskid);
		if(variables != null && !variables.isEmpty()){
			Set<String> keys = variables.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				context.setVariable(key, factory.createValueExpression(variables.get(key), Object.class));
			}
		}
		return selectPrevTasks(definition,currActivity,context,factory,currentTask.getProcessInstanceId(),result);
	}
	
	//递归获取上一节点任务名称集合
	private List<ActTaskEntity> selectPrevTasks(ProcessDefinitionImpl definition,PvmActivity pvmActivity,SimpleContext context,ExpressionFactory factory,String instanceid,List<ActTaskEntity> result){
		List<PvmTransition> preTransitionList = pvmActivity.getIncomingTransitions();
		if(preTransitionList != null && !preTransitionList.isEmpty()){
			for(PvmTransition each:preTransitionList){	
				String condition =(String)each.getProperty("conditionText");
				if(condition != null && !"".equals(condition) && !(boolean)(factory.createValueExpression(context,condition, boolean.class)).getValue(context)){
						continue;
				}
				PvmActivity preActivity = each.getSource();
				if("userTask".equals(preActivity.getProperty("type"))){
					if(!historyService.createHistoricTaskInstanceQuery().taskDefinitionKey(preActivity.getId()).processInstanceId(instanceid).list().isEmpty()){
						ActTaskEntity task = new ActTaskEntity();
						task.setTaskkey(preActivity.getId());
						task.setTaskname((String) preActivity.getProperty("name"));
						result.add(task);
					}
				}else if("exclusiveGateway".equals(preActivity.getProperty("type")) || "parallelGateway".equals(preActivity.getProperty("type")) ){
					result = selectPrevTasks(definition,preActivity,context,factory,instanceid,result);
				}		
			}
		}
		return result;
	}
		
	public Map<String,Object> selectPreVariables(String taskid){
		if(taskid == null || "".equals(taskid)) return null;
		return taskService.getVariables(taskid);
	}
	
	//启动流程
	public ProcessInstance startProcess(String definitionkey, Map<String, Object> variables, List<ActTaskEntity> taskEntitys, String assignees) {
		if (variables == null) {
			variables = new HashMap<String, Object>();
		}
		Map<String, Object> variablesa = variables;
		variablesa.put("assignees", assignees);
		ProcessInstance instance  = runtimeService.startProcessInstanceByKey(definitionkey, variablesa);
		return instance;		
	}
	
	  /*
     * 查询流程定义
     */
	public String findProcessDefinition() {
		List<ProcessDefinition> list = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
				.createProcessDefinitionQuery()// 创建一个流程定义查询
				.orderByProcessDefinitionVersion().desc()// 按照版本的升序排列
				.list();// 返回一个集合列表，封装流程定义
		
		return list.get(0).getId();
		
	}
	
	//根据流程实例id查询运行时任务
	public List<ActTaskEntity> selectTodotaskByInstanceid(String instanceid){
		return copyTasks(taskService.createTaskQuery().processInstanceId(instanceid).orderByTaskCreateTime().desc().list());
	}
	
	/**
	 * 复制任务实体为WorkFlowEntity实体
	 * @param list 代办任务集合列表
	 * @return
	 */
	private List<ActTaskEntity> copyTasks(List<Task> list){
		List<ActTaskEntity> result = new ArrayList<ActTaskEntity>();
		if(list != null && !list.isEmpty()){
			for(Task each : list){
				ActTaskEntity t = new ActTaskEntity();
				t.setTaskid(each.getId());
				t.setTaskkey(each.getTaskDefinitionKey());
				t.setTaskname(each.getName());
				t.setAssignee(each.getAssignee());
				t.setInstanceid(each.getProcessInstanceId());
				t.setDefinitionid(each.getProcessDefinitionId());
				t.setTaskstarttime(each.getCreateTime());
				t.setTaskendtime(null);
				t.setDuration(null);
				t.setExecutionid(each.getExecutionId());
				t.setParenttaskid(each.getParentTaskId());
				t.setTaskdescription(each.getDescription());
//				t.setOwner(each.getOwner());
//				t.setPriority(each.getPriority());
//				t.setFormKey(each.getFormKey());
//				t.setCategory(each.getCategory());
//				t.setTenantId(each.getTenantId());
//				t.setDueDate(each.getDueDate());
				result.add(t);
			}
		}
		return result;
	}
	//根据流程实例id  获取  流程开始节点
	public ActTaskEntity getStartEventByInstanceid(String instanceid){
		HistoricActivityInstance activity = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceid).activityName("接件").singleResult();
		ActTaskEntity e = new ActTaskEntity();
		e.setTaskkey(activity.getActivityId());
		e.setTaskname(activity.getActivityName());
		e.setTaskid(activity.getId());
		e.setAssignee(activity.getAssignee());
		e.setInstanceid(activity.getProcessInstanceId());
		e.setDefinitionid(activity.getProcessDefinitionId());
		return e;
	}
	
	//根据流程实例id  获取  流程开始节点
	public ActTaskEntity getStartEventJbcsByInstanceid(String instanceid){
		HistoricActivityInstance activity = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceid).activityName("经办初审").singleResult();
		ActTaskEntity e = new ActTaskEntity();
		e.setTaskkey(activity.getActivityId());
		e.setTaskname(activity.getActivityName());
		e.setTaskid(activity.getId());
		e.setAssignee(activity.getAssignee());
		e.setInstanceid(activity.getProcessInstanceId());
		e.setDefinitionid(activity.getProcessDefinitionId());
		return e;
	}
	
	/**
	 * 完成个人任务，并指定该流程流向下个任务的执行人
	 * @param taskid 任务id
	 * @param assignee 下一任务执行人
	 * @param variables 参数集合
	 */
	public boolean completeTask(String taskid, Map<String, Object> variables, List<ActTaskEntity> nextTasks) {
		Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
		if(variables == null)
			variables = new HashMap<String, Object>();
		try {
			taskService.complete(taskid, variables);
			List<Task> tasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
			if(nextTasks != null && !nextTasks.isEmpty()){
				if(tasks != null && !tasks.isEmpty()){
					for(ActTaskEntity each: nextTasks){
						String taskKey = each.getTaskkey();		
						String assignee = each.getAssignee();
						if(taskKey != null && !"".equals(taskKey) && assignee != null && !"".equals(assignee)){
							Task t = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).taskDefinitionKey(taskKey).singleResult();
							if(t != null){
								taskService.setAssignee(t.getId(), assignee);
							}
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 完成个人任务 完成任务
	 * @param taskid 任务id
	 */
	public boolean completeTask(String taskId,Map<String, Object> variables){
		try {
			taskService.complete(taskId,variables);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @Package com.kingtopinfo.activiti.service
	 * @Description: 删除组成员
	 * @author cyf
	 * @date 2017年9月29日 下午3:44:01
	 */
	public void deleteCandidateUser(String taskId, String userId) {
		processEngine.getTaskService().deleteCandidateUser(taskId, userId);
	}
	
	/**
	 * 回退任务，任务将回退到上一节点去，并回退给上一节点的提交者
	 * 
	 * @param taskId
	 *            任务Id
	 * @param sjdjid
	 * @param taskKeys
	 *            回退的目标节点的key集合
	 */
	public boolean rollbackTask(String taskId, Map<String, Object> newVariables, String sjdjid) {
		HistoricTaskInstance currentTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		//流程定义
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(currentTask.getProcessDefinitionId());
        if (definition == null)return false;
        //流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(currentTask.getProcessInstanceId()).singleResult();
		if (processInstance == null) return false;//流程实例为null，则未找到该流程或者该流程实例已完成	
		
		//查找上一任务节点
		SimpleContext context = new SimpleContext();
		ExpressionFactory factory = new ExpressionFactoryImpl();
		Map<String, Object> variables = taskService.getVariables(taskId);
		if(variables != null && !variables.isEmpty()){
			Set<String> keys = variables.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				context.setVariable(key, factory.createValueExpression(variables.get(key), Object.class));
			}
		}
		ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(currentTask.getTaskDefinitionKey());
		List<ActivityImpl> preActivityImpls = new ArrayList<ActivityImpl>();
		selectPreviousTasks(definition,currActivity,context,factory,currentTask.getProcessInstanceId(),preActivityImpls);	
		if(preActivityImpls == null || preActivityImpls.isEmpty())
			return false;
		//下一出口
        List<PvmTransition> nextTransitionList = currActivity.getOutgoingTransitions();
        //清除当前活动的默认出口（下一步任务的出口）
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        oriPvmTransitionList.addAll(nextTransitionList);
        nextTransitionList.clear();
        // 建立新出口
        List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();   
        if(preActivityImpls != null && !preActivityImpls.isEmpty()){
        	for(ActivityImpl activityImpl:preActivityImpls){
                TransitionImpl newTransition = currActivity.createOutgoingTransition();
                newTransition.setDestination(activityImpl);  
                newTransitions.add(newTransition);
            }
        }
        System.out.println("------------"+newVariables);
        taskService.complete(taskId,newVariables);  
        historyService.deleteHistoricTaskInstance(taskId); //是否删除任务的历史记录
       // 为新任务设置原来的执行人
        for(ActivityImpl each:preActivityImpls){
        	List<HistoricActivityInstance> hisactivitys = historyService.createHistoricActivityInstanceQuery().processInstanceId(currentTask.getProcessInstanceId()).activityType("userTask").activityId(each.getId()).finished().orderByHistoricActivityInstanceEndTime().desc().list();
        	if(hisactivitys != null && !hisactivitys.isEmpty()){
	        	Task task = taskService.createTaskQuery().processInstanceId(currentTask.getProcessInstanceId()).taskDefinitionKey(each.getId()).singleResult();
	        	taskService.setAssignee(task.getId(), hisactivitys.get(0).getAssignee());
        	}	
        }
        // 恢复方向
        for (TransitionImpl transitionImpl : newTransitions) {
            currActivity.getOutgoingTransitions().remove(transitionImpl);
        }
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
        	nextTransitionList.add(pvmTransition);
        }
        System.out.println("驳回任务成功！");
		
		if (currentTask.getTaskDefinitionKey().equals("dd")) {
			YjgSjdjEntity e = yjgSjdjService.getByPkey(sjdjid);
			e.setSqzt(5);
			yjgSjdjService.update(e);
		} else if (currentTask.getTaskDefinitionKey().equals("dd")) {
			
		}
        return true;
	}
	
	//递归获取上一节点集合
	private List<ActivityImpl> selectPreviousTasks(ProcessDefinitionImpl definition,PvmActivity pvmActivity,SimpleContext context,ExpressionFactory factory,String instanceid,List<ActivityImpl> result){
		List<PvmTransition> preTransitionList = pvmActivity.getIncomingTransitions();
		if(preTransitionList != null && !preTransitionList.isEmpty()){
			for(PvmTransition each:preTransitionList){
				String condition =(String)each.getProperty("conditionText");
				if(condition != null && !"".equals(condition) && !(boolean)(factory.createValueExpression(context,condition, boolean.class)).getValue(context)){
						continue;
				}
				PvmActivity preActivity = each.getSource();
				if("userTask".equals(preActivity.getProperty("type"))){
					if(!historyService.createHistoricTaskInstanceQuery().taskDefinitionKey(preActivity.getId()).processInstanceId(instanceid).list().isEmpty()){
						result.add(definition.findActivity(preActivity.getId()));
					}
				}else if("exclusiveGateway".equals(preActivity.getProperty("type")) || "parallelGateway".equals(preActivity.getProperty("type")) ){
					result =selectPreviousTasks(definition,preActivity,context,factory,instanceid,result);
				}		
			}
		}
        return result;  
	}
	
	/**
	 * 删除流程
	 * 
	 * @param instanceId
	 * @param reason
	 */
	public void deleteProcessInstance(String instanceId, String reason) throws Exception {
		runtimeService.deleteProcessInstance(instanceId, reason);
	}
	
	/**
	 * 认领任务
	 * 
	 * @param taskId
	 * @param userId
	 */
	public void claim(String taskId, String userId) {
		taskService.claim(taskId, userId);
	}
	
	/**
	 * @Package com.kingtopinfo.activiti.service
	 * @Description: 查询历史任务
	 * @author cyf
	 * @date 2017年10月25日 下午3:24:14
	 */
	public List<HistoricTaskInstance> findHisTask(String processInstanceId) {

		return processEngine.getHistoryService()// 与历史数据（历史表）相关的Service
				.createHistoricTaskInstanceQuery()// 创建历史任务实例查询
				.processInstanceId(processInstanceId)//
				.orderByHistoricTaskInstanceStartTime().desc().list();
	}
	
}
