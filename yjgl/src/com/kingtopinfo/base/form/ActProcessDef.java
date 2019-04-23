package com.kingtopinfo.base.form;

public class ActProcessDef {

	private String	id;					// ID
	private String	name;				// 名称
	private String	key;				// 流程定义的KEY
	private int		version;			// 流程定义的版本
	private String	resourceName;		// 流程定义的规则文件名称
	private String	diagramResourceName;// 流程定义的规则图片名称
	private String	deploymentId;		// 部署ID
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
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
	
	public String getDeploymentId() {
		return deploymentId;
	}
	
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
}
