<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<body>
		<div id='loading' class="_loading"></div>
    	<div class="easyui-layout" data-options="fit:true">
   		<div region="center" data-options="border:true,collapsible:false">
   			<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
				<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">流程权限配置</legend>
			</fieldset>
			<div style="padding: 5px;">
				<a id="_flow_menu_tree_add_button" href="#" class="easyui-linkbutton">新增</a>
				<a id="_flow_menu_tree_edit_button" href="#" class="easyui-linkbutton">编辑</a>
				<a id="_flow_menu_tree_del_button" href="#" class="easyui-linkbutton l-btn-danger">删除</a>
			</div> 
			<ul id="_flow_menu_tree"></ul>
		</div>
		
		<div id="_flow_menu_menu_add" class="easyui-window" title="增加流程菜单信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_flow_menu_addForm">
				<sec:csrfInput/>
				<div style="padding: 2px;">
		        	<input id="_flow_menu_add_name" name="name" class="easyui-textbox" label="显示名称：" prompt="请输入显示名称" data-options="labelWidth:120,labelAlign:'right',required:true,validType:'length[1,50]'" style="width:400px;height: 34px;">
		        </div>
		        
		        <div style="padding: 2px;">
		        	<input id="_flow_menu_add_url" name="url" class="easyui-textbox" label="链接地址：" prompt="请输入链接地址" data-options="labelWidth:120,labelAlign:'right',required:true,validType:'length[1,150]'" style="width:400px;height: 34px;">
		        </div>
				
				<div style="padding: 2px;text-align: center;">
					<input type="hidden" id="_flow_menu_add_pid" name="flowmenupid" />
					<input type="hidden" id="_flow_menu_add_type" name="type" />
					<input type="hidden" id="_flow_menu_add_state" name="state" value="1"/>
		        	<a id="_flow_menu_add_save" href="javascript:;" class="easyui-linkbutton" >保存</a>
		        	<a id="_flow_menu_add_cancel" href="javascript:;" class="easyui-linkbutton l-btn-warm" >关闭</a>
		        </div>
			</form>
		</div>
		
		<div id="_flow_menu_menu_edit" class="easyui-window" title="修改流程菜单信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form method="post" id="_flow_menu_editForm" >
        		<sec:csrfInput/>
		        	<input id="_flow_menu_edit_id" name="id" type="hidden">
		        <div style="padding: 2px;">
		        	<input id="_flow_menu_edit_name" name="name" class="easyui-textbox" label="显示名称：" prompt="请输入显示名称" data-options="labelWidth:120,labelAlign:'right',required:true,validType:'length[1,50]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		        	<input id="_flow_menu_edit_url" name="url" class="easyui-textbox" label="链接地址：" prompt="请输入链接地址" data-options="labelWidth:120,labelAlign:'right',required:true,validType:'length[1,150]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_flow_menu_edit_save" href="javascript:;" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_flow_menu_edit_cancel" href="javascript:;" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>

		</div>
		
		<script type="text/javascript" src="../activiti/flow_menu.js" charset="UTF-8"></script>	
	</body>
</html>