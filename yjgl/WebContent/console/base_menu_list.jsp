<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>菜单管理</title>
		<sec:csrfMetaTags/>
	</head>
	<body>
 		<script type="text/javascript" src="../console/base_menu_list.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
    	<div class="easyui-layout" data-options="fit:true">
    	
			<div region="west" data-options="border:false" style="width:250px;" >
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">菜单信息</legend>
				</fieldset>
				<div style="padding: 5px;">
					<a id="_west_menu_add" href="javascript:;" class="easyui-linkbutton" >添加</a>
					<a id="_west_menu_edit" href="javascript:;" class="easyui-linkbutton" >编辑</a>
					<a id="_west_menu_remove" href="javascript:;" class="easyui-linkbutton l-btn-danger" >移除</a>
				</div>
				<div id="_base_menu_west">
					<ul id="_base_menu_tree"></ul>
				</div>
			</div>
			
			<div region="center">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">角色信息</legend>
				</fieldset>
				<ul id="_base_menu_base_role"></ul>
			</div>
		
			<div id="_base_menu_menu_add" class="easyui-window" class="easyui-window" title="增加菜单信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
				<form action="" method="post" id="_base_menu_addForm">
					<sec:csrfInput/>
					<div style="padding: 2px;">
		        		<input id="_base_menu_add_name" name="menuname" class="easyui-textbox" label="显示名称：" prompt="请输入显示名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		       	 	</div>
		       	 	
		       	 	<div style="padding: 2px;">
		        		<input id="_base_menu_add_url" name="src" class="easyui-textbox" label="链接地址：" prompt="请输入链接地址" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,200]'" style="width:400px;height: 34px;">
		       	 	</div>
		       	 	
		       	 	<div style="padding: 2px;">
			        	<select id="_base_menu_add_state" name="state" class="easyui-combobox" label="是否锁定：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		       	 	</div>
		       	 	
		       	 	<div style="padding: 2px;text-align: center;">
		       	 		<input type="hidden" id="_base_menu_add_pid" name="basemenupid" />
			        	<a id="_base_menu_add_save" href="javascript:;" class="easyui-linkbutton" >保存</a>
			        	<a id="_base_menu_add_cancel" href="javascript:;" class="easyui-linkbutton l-btn-warm" >取消</a>
		        	</div>
				</form>
			</div>
	        
			<div id="_base_menu_menu_edit" class="easyui-window" class="easyui-window" title="编辑菜单信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
				<form action="" method="post" id="_base_menu_editForm">
					<sec:csrfInput/>
					<div style="padding: 2px;">
		        		<input id="_base_menu_edit_name" name="menuname" class="easyui-textbox" label="显示名称：" prompt="请输入显示名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		       	 	</div>
					
					<div style="padding: 2px;">
		        		<input id="_base_menu_edit_url" name="src" class="easyui-textbox" label="链接地址：" prompt="请输入链接地址" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,200]'" style="width:400px;height: 34px;">
		       	 	</div>
		       	 	
		       	 	<div style="padding: 2px;">
			        	<select id="_base_menu_edit_state" name="state" class="easyui-combobox" label="是否锁定：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		       	 	</div>
		       	 	
		       	 	<div style="padding: 2px;text-align: center;">
		       	 		<input type="hidden" name="basemenuid" id="_base_menu_edit_id"/>
			        	<a id="_base_menu_edit_save" href="javascript:;" class="easyui-linkbutton" >保存</a>
			        	<a id="_base_menu_edit_cancel" href="javascript:;" class="easyui-linkbutton l-btn-warm" >取消</a>
		        	</div>
					
				</form>
			</div>
	
			<div id="_base_menu_base_menu_menu" class="easyui-menu" style="width:120px;">
				<div onclick="BaseMenuList.baseMenuMoveUp()">上移</div>
				<div onclick="BaseMenuList.baseMenuMoveDown()">下移</div>
			</div>
		</div>
	</body>
</html>