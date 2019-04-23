<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sec:csrfMetaTags/>
	</head>
	<body>
		<script type="text/javascript" src="../xtgj/xtgj_txl.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">通讯录</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_xtgl_txl_search_form">
			       	    <input class="easyui-textbox" id="_xtgl_txl_txlxm" label="姓名：" prompt="请输入姓名" data-options="labelWidth:50,labelAlign:'right'" style="width:250px;height: 34px;"> 
			       	    <input class="easyui-textbox" id="_xtgl_txl_txlgs" label="单位名称：" prompt="请输入单位名称" data-options="labelWidth:100,labelAlign:'right'" style="width:250px;height: 34px;"> 
				        <input class="easyui-textbox" id="_xtgl_txl_txljob" label="单位职务：" prompt="请输入单位职务" data-options="labelWidth:100,labelAlign:'right'" style="width:250px;height: 34px;"> 
				        <input class="easyui-textbox" id="_xtgl_txl_txldh" label="电话：" prompt="请输入电话" data-options="labelWidth:100,labelAlign:'right'" style="width:250px;height: 34px;"> 
				       
				        <a href="#" class="easyui-linkbutton" id="_xtgl_txl_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_xtgl_txl_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
			        <a href="#" class="easyui-linkbutton" id="_xtgl_txl_list_import_button">导入通讯录Excel</a>
					<a id="_xtgl_txl_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">新增通讯录</a>
					<a id="_xtgl_txl_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑通讯录</a> 
					<a id="_xtgl_txl_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除通讯录</a>
				</div>
			</div>
			<div region="center">
				<table id="_xtgl_txl_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_xtgl_txl_add_window" class="easyui-window" title="新增通讯录" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_xtgl_txl_add_form" >
        		<sec:csrfInput/>
				<div style="padding: 2px;">
	        		<input name="txlxm" id="_xtgl_txl_add_txlxm" label="姓名：" prompt="请输入姓名", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="txlgs" id="_xtgl_txl_add_txlgs" label="单位名称：" prompt="请输入单位名称", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="txljob" id="_xtgl_txl_add_txljob" label="单位职务：" prompt="请输入单位职务", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="txlxzqh" id="_xtgl_txl_add_txlxzqh" label="邮编：" prompt="请输入邮编", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="txldh" id="_xtgl_txl_add_txldh" label="电话：" prompt="请输入电话", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="txlemail" id="_xtgl_txl_add_txlemail" label="邮箱：" prompt="请输入邮箱", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="txlbz" id="_xtgl_txl_add_txlbz" label="备注：" prompt="请输入备注", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_xtgl_txl_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存通讯录</a>
		        	<a id="_xtgl_txl_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
	    
	    <div id="_xtgl_txl_edit_window" class="easyui-window" title="编辑通讯录" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_xtgl_txl_edit_form" >
        		<sec:csrfInput/>
		        <div style="padding: 2px;">
	        		<input name="txlxm" id="_xtgl_txl_edit_txlxm" label="姓名：" prompt="请输入姓名", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="txlgs" id="_xtgl_txl_edit_txlgs" label="单位名称：" prompt="请输入单位名称", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="txljob" id="_xtgl_txl_edit_txljob" label="单位职务：" prompt="请输入单位职务", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="txlxzqh" id="_xtgl_txl_edit_txlxzqh" label="邮编：" prompt="请输入邮编", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="txldh" id="_xtgl_txl_edit_txldh" label="电话：" prompt="请输入电话", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="txlemail" id="_xtgl_txl_edit_txlemail" label="邮箱：" prompt="请输入邮箱", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="txlbz" id="_xtgl_txl_edit_txlbz" label="备注：" prompt="请输入备注", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;text-align: center;">
		            <input type="hidden" id="_edit_txlid" name="txlid"/>
		        	<a id="_xtgl_txl_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存通讯录</a>
		        	<a id="_xtgl_txl_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
		<div id="_xtgl_txl_list_import_window" class="easyui-window" title="导入Excel" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:400px;padding:10px;height: 150px">
			<form id="_xtgl_choose_file_form" enctype="multipart/form-data" method="POST">
				<input class="easyui-filebox" id="_xtgl_import_choose_file" name="fileList" data-options="labelWidth:80,labelAlign:'right',required:true" style="width:350px;height: 34px">
				<div style="padding: 2px;text-align: center;">
		        	<a id="_xtgl_txl_list_import_comfirm_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">导入</a>
		        	<a id="_xtgl_txl_list_import_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
				</div>
			</form>
		</div>
		
	</body>
</html>