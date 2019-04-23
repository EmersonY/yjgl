<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>数据字典（列表）管理</title>
	</head>
	<body>
		<script type="text/javascript" src="../console/base_type_tree.js" charset="UTF-8"></script>
		
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="west" data-options="border:false" style="width:500px;" >
				<div class="easyui-layout" data-options="fit:true,border:false">
					<div region="north" data-options="border:false" style="border-right: 1px solid #ddd;border-bottom: 1px solid #ddd;background-color: #FAFAFA;padding: 5px;overflow:hidden;">
						<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
							<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">字典信息</legend>
						</fieldset>
						<div style="margin-top: 2px;margin-left: 10px;">
							<a id="_base_type_west_add_button" href="javascript:;" class="easyui-linkbutton" >新增</a>
							<a id="_base_type_west_edit_button" href="javascript:;" class="easyui-linkbutton" >编辑</a>
							<a id="_base_type_west_refresh_button" href="javascript:;" class="easyui-linkbutton" >刷新版本</a>
							<a id="_base_type_west_del_button" href="javascript:;" class="easyui-linkbutton l-btn-danger" >移除</a>
						</div>
					</div>
					<div region="center" data-options="border:false" style="border-right: 1px solid #ddd;">
						<table id="_base_type_west_table" data-options="fit:true,border:false"></table>
					</div>
				</div>
			</div>
			<div region="center" data-options="border:false">
				<div class="easyui-layout" data-options="fit:true,border:false" >
					<div region="north" data-options="border:false" style="border-bottom: 1px solid #ddd;background-color: #FAFAFA;padding: 5px;overflow:hidden;">
						<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
							<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">字典项</legend>
						</fieldset>
						<div style="margin-top: 2px;margin-left: 10px;">
							<a id="_base_type_tree_add_button" href="#" class="easyui-linkbutton">新增</a>
							<a id="_base_type_tree_edit_button" href="#" class="easyui-linkbutton">编辑</a>
							<a id="_base_type_tree_up_button" href="#" class="easyui-linkbutton">上移</a>
							<a id="_base_type_tree_down_button" href="#" class="easyui-linkbutton">下移</a>
							<a id="_base_type_tree_del_button" href="#" class="easyui-linkbutton l-btn-danger">删除</a>
						</div>
					</div>
					<div region="center" data-options="border:false">
						<table id="_base_type_center_treegrid" data-options="fit:true,border:false"></table>
					</div>
				</div>
			</div>
		</div>
		
		<div id="_base_type_west_add_window" class="easyui-window" title="增加数据字典信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseTypeAction/insert" method="post" id="_base_type_west_add_form">
				<input type="hidden" name="type" value="1"/>
				<sec:csrfInput/>
				<div style="padding: 2px;">
		        	<input name="name" class="easyui-textbox" label="名称：" prompt="请输入名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
		        <div style="padding: 2px;">
		        	<input name="code" class="easyui-textbox" label="代码：" prompt="请输入代码" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,50]'" style="width:400px;height: 34px;">
		        </div>
	       	 	<div style="padding: 2px;">
	       	 		<select name="state" class="easyui-combobox" label="状态：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;">   
					    <option value="1">启用</option>
					    <option value="0">禁用</option>
					</select>
	       	 	</div>
	       	 	<div style="padding: 2px;text-align: center;">
		        	<a id="_base_type_west_add_save_button" href="javascript:;" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_base_type_west_add_cancel_button" href="javascript:;" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
			</form>
		</div>
		
		<div id="_base_type_west_edit_window" class="easyui-window" title="编辑数据字典信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseTypeAction/update" method="post" id="_base_type_west_edit_form">
				<sec:csrfInput/>
				<input type="hidden" id="_base_type_west_edit_basetypeid" name="basetypeid" />
				<div style="padding: 2px;">
		        	<input id="_base_type_west_edit_name" type="text" name="name" class="easyui-textbox" label="名称：" prompt="请输入名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
	       	 	<div style="padding: 2px;">
	       	 		<select id="_base_type_west_edit_state" name="state" class="easyui-combobox" label="状态：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;">   
					    <option value="1">启用</option>
					    <option value="0">禁用</option>
					</select>
	       	 	</div>
	       	 	<div style="padding: 2px;text-align: center;">
					<a id="_base_type_west_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton">保存</a> 
					<a id="_base_type_west_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton">取消</a> 
				</div>
			</form>
		</div>
		
		<div id="_base_type_tree_add_window" class="easyui-window" title="新增数据字典项" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseTypeAction/insert" method="post" id="_base_type_tree_add_form">
				<sec:csrfInput/>
				<input type="hidden" id="_base_type_tree_add_pid" name="basetypepid"/>
				<input type="hidden" id="_base_type_tree_add_code" name="code"/>
				<div style="padding: 2px;">
		        	<input type="text" name="name" class="easyui-textbox" label="名称：" prompt="请输入名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
				<div style="padding: 2px;">
		        	<input type="text" name="value" class="easyui-textbox" label="值：" prompt="请输入值" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
		        </div>
				<div style="padding: 2px;">
	       	 		<select id="_base_type_tree_code_add_status" name="state" class="easyui-combobox" label="状态：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;">   
					    <option value="1">启用</option>
					    <option value="0">禁用</option>
					</select>
	       	 	</div>
	       	 	<div style="padding: 2px;text-align: center;">
					<a id="_base_type_tree_add_save" href="javascript:void(0);" class="easyui-linkbutton">保存</a> 
					<a id="_base_type_tree_add_cancel" href="javascript:void(0);" class="easyui-linkbutton">取消</a> 
				</div>
			</form>
		</div>
		
		<div id="_base_type_tree_edit_window" class="easyui-window" title="编辑数据字典项" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../TblBaseTypeAction/update" method="post" id="_base_type_tree_edit_form">
				<sec:csrfInput/>
				<input type="hidden" id="_base_type_tree_edit_basetypeid" name="basetypeid"/>
				<div style="padding: 2px;">
					<input type="text" name="name" id="_base_type_tree_edit_name" class="easyui-textbox" label="名称：" prompt="请输入名称" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
				</div>
				<div style="padding: 2px;">
					<input type="text" name="value" id="_base_type_tree_edit_value" class="easyui-textbox" label="值：" prompt="请输入值" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
				</div>
				<div style="padding: 2px;">
					<select id="_base_type_tree_code_edit_status" name="state"class="easyui-combobox" label="状态：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;">
						<option value="1">启用</option>
					    <option value="0">禁用</option>
					</select>
				</div>
	       	 	<div style="padding: 2px;text-align: center;">
					<a id="_base_type_tree_edit_save" href="javascript:void(0);" class="easyui-linkbutton">保存</a> 
					<a id="_base_type_tree_edit_cancel" href="javascript:void(0);" class="easyui-linkbutton">取消</a> 
				</div>
			</form>
		</div>
		
	</body>
</html>