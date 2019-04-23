package com.kingtopinfo.base.form;

public class ActRuTask {

	private String	id;			// ID
	private String	name;		// 名称
	private String	createTime;	// 发布时间
	private String	assignee;	// 办理人
	private String	logtimeStart;	// 开始时间
	private String	logtimeEnd;		// 结束时间
	
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
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getAssignee() {
		return assignee;
	}
	
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	public String getLogtimeStart() {
		return logtimeStart;
	}

	public void setLogtimeStart(String logtimeStart) {
		this.logtimeStart = logtimeStart;
	}

	public String getLogtimeEnd() {
		return logtimeEnd;
	}

	public void setLogtimeEnd(String logtimeEnd) {
		this.logtimeEnd = logtimeEnd;
	}

}
