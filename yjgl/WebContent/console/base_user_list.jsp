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
		<script type="text/javascript" src="../console/base_user_list.js" charset="UTF-8"></script>	
		
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">用户信息</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_user_search_form">
				       	<input class="easyui-textbox" id="_user_search_account" label="帐号：" prompt="请输入帐号" data-options="labelWidth:50,labelAlign:'right'" style="width:250px;height: 34px;">
				       	<input class="easyui-textbox" id="_user_search_username" label="用户名：" prompt="请输入用户名" data-options="labelWidth:65,labelAlign:'right'" style="width:265px;height: 34px;">
				        <a href="#" class="easyui-linkbutton" id="_user_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_user_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
					<a id="_add" href="javascript:;" class="easyui-linkbutton" data-options="">新增用户</a>
					<a id="_edit" href="javascript:;" class="easyui-linkbutton" data-options="">编辑用户</a>
					<a id="_resertPassword" href="javascript:;" class="easyui-linkbutton" data-options="">重置密码</a>
<!-- 				<a id="_baseuser_import" href="javascript:;" class="easyui-linkbutton" data-options="">导入维护单位管理员</a>-->				    <a id="_delete" href="javascript:;" class="easyui-linkbutton l-btn-danger" data-options="">删除用户</a>
				</div>
			</div>
			<div data-options="region:'center'">
				<table id="_base_user_list" data-options="fit:true,border:false"></table>
			</div>
		</div>
		<div id="_add_w" class="easyui-window" title="新增用户信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseUserAction/add" method="post" id="_add_form" >
        		<sec:csrfInput/>
				<div style="padding: 2px;">
		        	<input name="account" class="easyui-textbox" label="帐号：" prompt="请输入帐号" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,32]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		        	<input type="password" id="password" name="password" class="easyui-textbox" label="密码：" prompt="请输入密码" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,32]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		        	<input type="password" id="password_again" name="password_again" class="easyui-textbox" label="确认密码：" prompt="请输入确认密码" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,32]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		        	<input name="username" class="easyui-textbox" label="用户名：" prompt="请输入用户名" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		        	<select id="_user_add_state" name="state" class="easyui-combobox" label="是否锁定：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		        </div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_add_submit" href="javascript:;" class="easyui-linkbutton" data-options="">保存用户</a>
		        	<a id="_add_reset" href="javascript:;" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
	    
	    <div id="_edit_w" class="easyui-window" title="编辑用户信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseUserAction/edit" method="post" id="_edit_form" >
        		<sec:csrfInput/>
        		<div style="padding: 2px;">
		        	<input id="_user_edit_baseuserid" name="baseuserid" class="easyui-textbox" type="hidden">
		        </div>
		        <div style="padding: 2px;">
		        	<input id="_user_edit_username" name="username" class="easyui-textbox" label="用户名：" prompt="请输入用户名" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		        	<select id="_user_edit_state" name="state" class="easyui-combobox" label="是否锁定：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		        </div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_edit_submit" href="javascript:;" class="easyui-linkbutton" data-options="">保存用户</a>
		        	<a id="_edit_reset" href="javascript:;" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
	    
	    <div id="_baseuser_import_window" class="easyui-window" title="导入用户" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:400px;padding:10px;height: 150px">
			<div style="padding: 2px;text-align: center;">
				<form id="_baseuser_import_form" enctype="multipart/form-data" method="POST">
					<input class="easyui-filebox" id="_baseuser_import_choose_file" name="fileList" data-options="labelWidth:80,labelAlign:'right',required:true" style="width:350px;height: 34px">
					<div style="padding: 2px;text-align: center;">
	        			<a id="_baseuser_import_comfirm_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">导入</a>
			        	<a id="_baseuser_import_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>