<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html>
<html>
	<head>
		<title>厦门市政窨井管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sec:csrfMetaTags/>
	</head>
	<body>
		<script type="text/javascript" src="../activiti/flow_deploy_home.js" charset="UTF-8"></script>	
		
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div id="_deploy_north" region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">流程定制</legend>
				</fieldset>
				<form enctype="multipart/form-data" method="POST" action="../ActDeploymentAction/newdeploy?${_csrf.parameterName}=${_csrf.token}" id="_depolyment_form">
					<div style="padding: 5px;">
 						<input class="easyui-textbox" name="filename" label="流程名称：" prompt="请输入流程名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,32]'" style="width:265px;height: 34px;">
						&nbsp;&nbsp;&nbsp;
						<input class="easyui-filebox" id="_depolyment_data" name="file" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,32]'" style="width:265px;height: 34px;">
						<font color="red">选择要部署的zip文件 该zip压缩文件中需包含bpmn文件和对应的png文件。bpmn、png文件需要放在zip文件的根目录中</font>
					</div>
				</form>
				<div style="padding: 5px;">
					<a id="_depolymentSubmit" href="javascript:;" class="easyui-linkbutton" data-options="">上传流程</a>
				</div>
			</div>
			<div id="_deploy_center" data-options="region:'center'" style="background-color: #FAFAFA;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">流程定义信息列表</legend>
				</fieldset>
				<table id="_act_re_procdef_list" data-options="fit:true,border:false"></table>
			</div>
			<div id="_deploy_south" data-options="region:'south'" style="background-color: #FAFAFA;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">部署信息管理列表</legend>
				</fieldset>
				<table id="_act_re_deployment_list" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_set_window" class="easyui-window" data-options="fit:true,title:'流程配置',iconCls:'icon-set',width:$(window).width(),height:$(window).height(),draggable:true,shadow:true,modal:true,closed:true,closable:true,minimizable:false,maximizable:false,collapsible:false">
			<iframe id="_set_iframe" src="" width="100%" height="99%" marginheight="0" marginwidth="0" frameborder="0"></iframe>
		</div>
	</body>
</html>