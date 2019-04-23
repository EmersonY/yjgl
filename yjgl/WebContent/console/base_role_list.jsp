<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色管理</title>
		<sec:csrfMetaTags/>
	</head>
	<body>
		
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true" id="_role_layout">
			
			<div region="west" data-options="border:false" id="_role_west">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">角色信息</legend>
				</fieldset>
				<div style="padding: 5px;">
					<a id="_west_role_add" href="javascript:;" class="easyui-linkbutton" >添加</a>
					<a id="_west_role_edit" href="javascript:;" class="easyui-linkbutton" >编辑</a>
					<a id="_west_role_configure_button" href="javascript:;" class="easyui-linkbutton" >按钮授权</a>
					<a id="_west_role_remove" href="javascript:;" class="easyui-linkbutton l-btn-danger" >移除</a>
				</div>
				<div id="_west">
					<ul id="base_role"></ul>
				</div>
			</div>	
			<div region="center" data-options="border:false" id="_role_center">
				<div class="easyui-layout" data-options="fit:true" >
					<div region="north" data-options="border:false" style="height: 130px;" >
						<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
							<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;" id="_legend"></legend>
						</fieldset>
						<div id="_user_search_div" style="padding: 5px;display:none" >
							<form action="" id="_role_user_form">
						       	<input class="easyui-textbox" id="_role_user_account" label="帐号：" prompt="请输入帐号" data-options="labelWidth:50,labelAlign:'right'" style="width:150px;height: 34px;">
						       	<input class="easyui-textbox" id="_role_user_username" label="用户名：" prompt="请输入用户名" data-options="labelWidth:65,labelAlign:'right'" style="width:180px;height: 34px;">
						        <a href="#" class="easyui-linkbutton" id="_role_user_search_button">查询</a>
						        <a href="#" class="easyui-linkbutton" id="_role_user_reset_button">重置</a>
					        </form>
						    <div style="padding: 5px;">
								<a id="_role_add" href="javascript:;" class="easyui-linkbutton" ></a>
								<a id="_role_remove" href="javascript:;" class="easyui-linkbutton l-btn-danger" ></a>
							</div>
						</div>
					</div>
					<div region="center" data-options="border:false">
						<table id="_role_user" data-options="fit:true,border:false"></table>
					</div>
				</div>
			</div>
			<div region="east" data-options="border:false" id="_role_east">
				<div class="easyui-layout" data-options="fit:true" >
					<div region="north" data-options="border:false" style="height: 130px;" >
						<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
							<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">现场施工巡查员</legend>
						</fieldset>
						<div id="_second_user_search_div" style="padding: 5px" >
							<form action="" id="_second_role_user_form">
						       	<input class="easyui-textbox" id="_second_role_user_account" label="帐号：" prompt="请输入帐号" data-options="labelWidth:50,labelAlign:'right'" style="width:150px;height: 34px;">
						       	<input class="easyui-textbox" id="_second_role_user_username" label="用户名：" prompt="请输入用户名" data-options="labelWidth:65,labelAlign:'right'" style="width:180px;height: 34px;">
						        <a href="#" class="easyui-linkbutton" id="_second_role_user_search_button">查询</a>
						        <a href="#" class="easyui-linkbutton" id="_second_role_user_reset_button">重置</a>
					        </form>
						    <div style="padding: 5px;">
								<a id="_second_role_add" href="javascript:;" class="easyui-linkbutton" >添加现场施工巡查员</a>
								<a id="_second_role_remove" href="javascript:;" class="easyui-linkbutton l-btn-danger" >移除现场施工巡查员</a>
							</div>
						</div>
					</div>
					<div region="center" data-options="border:false">
						<table id="_second_role_user" data-options="fit:true,border:false"></table>
					</div>
				</div>
			</div>
		</div>
			
		<div id="_role_add_window" class="easyui-window" title="增加角色组" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseRoleAction/insert" method="post" id="_role_add_form">
				<sec:csrfInput/>
				<div style="padding: 2px;">
		        	<input id="_add_rolename" name="rolename" class="easyui-textbox" label="角色名称：" prompt="请输入角色名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
		      	<div style="padding: 2px;">
		      		<select id="_add_roletype" name="baseroletype" class="easyui-combobox" label="角色类型：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		        </div>
				<div style="padding: 2px;text-align: center;">
		        	<input type="hidden" id="_add_pid" name="baserolepid"/>
		        	<a id="_add_save" href="javascript:;" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_add_close" href="javascript:;" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
			</form>
		</div>
		        
		<div id="_role_edit_window" class="easyui-window" title="编辑角色组" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseRoleAction/update" method="post" id="_role_edit_form">
				<sec:csrfInput/>
				<div style="padding: 2px;">
		        	<input id="_edit_rolename" name="rolename" class="easyui-textbox" label="角色名称：" prompt="请输入角色名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		      		<select id="_edit_roletype" name="baseroletype" class="easyui-combobox" label="角色类型：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		        </div>
				<div style="padding: 2px;text-align: center;">
		        	<input type="hidden" id="_edit_id" name="baseroleid"/>
		        	<a id="_edit_save" href="javascript:;" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_edit_close" href="javascript:;" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
			</form>
		</div>
	
		<div id="_role_user_add" class="easyui-window" title="添加用户" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height: 600px">
			<div region="north" data-options="border:false,fit:true" style="height: 80px">
				<div style="padding: 2px;">
					<form id="_role_user_add_form"> 
				       	<input class="easyui-textbox" id="_role_user_add_account" label="帐号：" prompt="请输入帐号" data-options="labelWidth:50,labelAlign:'right'" style="width:250px;height: 34px;">
				       	<input class="easyui-textbox" id="_role_user_add_username" label="用户名：" prompt="请输入用户名" data-options="labelWidth:65,labelAlign:'right'" style="width:265px;height: 34px;">
				        <a href="#" class="easyui-linkbutton" id="_role_user_add_search">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_role_user_add_reset">重置</a>
					</form>
				</div>
				<div style="padding: 2px;">
					<a href="#" class="easyui-linkbutton" id="_role_user_confirm">确定</a>
					<a href="#" class="easyui-linkbutton l-btn-warm" id="_role_user_cancel">取消</a>
				</div>
			</div>
			<div region="center">
				<table id="_unadd_user"></table>
			</div>
		</div>
		
		<div id="_second_role_user_add" class="easyui-window" title="添加现场施工巡查员" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height: 600px">
			<div region="north" data-options="border:false,fit:true" style="height: 80px">
				<div style="padding: 2px;">
					<form id="_second_role_user_add_form"> 
				       	<input class="easyui-textbox" id="_second_role_user_add_account" label="帐号：" prompt="请输入帐号" data-options="labelWidth:50,labelAlign:'right'" style="width:250px;height: 34px;">
				       	<input class="easyui-textbox" id="_second_role_user_add_username" label="用户名：" prompt="请输入用户名" data-options="labelWidth:65,labelAlign:'right'" style="width:265px;height: 34px;">
				        <a href="#" class="easyui-linkbutton" id="_second_role_user_add_search">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_second_role_user_add_reset">重置</a>
					</form>
				</div>
				<div style="padding: 2px;">
					<a href="#" class="easyui-linkbutton" id="_second_role_user_confirm">确定</a>
					<a href="#" class="easyui-linkbutton l-btn-warm" id="_second_role_user_cancel">取消</a>
				</div>
			</div>
			<div region="center">
				<table id="_second_unadd_user"></table>
			</div>
		</div>
		
		<div id="_base_role_list_linkRole_window" class="easyui-window" title="资源授权" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height:600px;">
			<div class="easyui-layout" data-options="border:false,fit:true">
				<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				    <div style="padding: 5px;">
						<a id="_base_role_list_linkRole_comfirm" href="javascript:void(0);" class="easyui-linkbutton" data-options="">确定</a>
						<a id="_base_role_list_linkRole_cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="">取消</a>
					</div>
				</div>
				<div region="center">
					<table id="_base_role_list_linkRole_table" data-options="fit:true,border:false"></table>
				</div>
			</div>
		</div>
	
		<script type="text/javascript" src="../console/base_role_list.js" charset="UTF-8"></script>
	</body>
</html>