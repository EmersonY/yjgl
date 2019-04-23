package com.kingtopinfo.activiti.entity;

import com.kingtopinfo.base.entity.TblBaseEntity;

/**
 * com.kingtopinfo.activiti.entity.DefinitionEntity
 * Description :流程部署定义实体
 * @author lxc 
 * Create at 2016年10月27日 下午2:08:54
 */
public class ActDefinitionEntity extends TblBaseEntity {
	
	private String id;//流程定义ID
	
	private String key;//流程定义KEY
	
	private String name;//流程定义名称
	
	private int version;//版本
	
	private String resourceName;//资源名称
	
	private String diagramResourceName;//图片资源名称
	
	private String description;//描述
	
	private String category;//类别
	
	private String deploymentId;//部署ID
	
	private String tenantId;//承租人ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDiagramResourceName() {
		return diagramResourceName;
	}

	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}
