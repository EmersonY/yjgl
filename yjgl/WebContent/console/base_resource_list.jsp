<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib prefix="fns" uri="../tld/fns.tld" %>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sec:csrfMetaTags/>
	</head>
	<body>
		<script type="text/javascript" src="../console/base_resource_list.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">资源管理</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_base_resource_list_search_form">
				       	<input class="easyui-textbox" id="_resource_search_resourceno" label="资源编号：" prompt="请输入资源编号" data-options="labelWidth:85,labelAlign:'right'" style="width:265px;height: 34px;">
				       	<input class="easyui-textbox" id="_resource_search_name" label="资源名称：" prompt="请输入资源名称" data-options="labelWidth:105,labelAlign:'right'" style="width:265px;height: 34px;">
				        <a href="#" class="easyui-linkbutton" id="_base_resource_list_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_base_resource_list_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
					<a id="_base_resource_list_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">新增</a>
					<a id="_base_resource_list_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑</a>
					<a id="_base_resource_list_linkRole_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">授权</a>
					<a id="_base_resource_list_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除</a>
				</div>
			</div>
			<div region="center">
				<table id="_base_resource_list_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_base_resource_list_add_window" class="easyui-window" title="新增" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_base_resource_list_add_form" >
        		<sec:csrfInput/>
        		<input type="hidden" name="menuname" id="_add_menuname">
				<div style="padding: 2px;">
	        		<input name="resourceno" id="_base_resource_list_add_resourceno" label="资源编号：" prompt="请输入资源编号" data-options="labelWidth:80,labelAlign:'right',validType:'length[1,200]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="padding: 2px;">
	        		<input name="name" id="_base_resource_list_add_name" label="资源名称：" prompt="请输入资源名称", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="padding: 2px;">
	        		<select id="_base_resource_list_add_menuname" name="basemenuid" class="easyui-combobox" label="对应模块：" prompt="请添加对应模块" data-options="labelWidth:80,labelAlign:'right',panelHeight:300,required:true" style="width:400px;height: 34px;" ></select>  
	        	</div>
	        	<div style="padding: 2px;">
		        	<select id="_base_resource_list_add_status" name="status" class="easyui-combobox" label="是否锁定：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		        </div>
		        <div style="padding: 2px;">
	        		<input name="remark" id="_base_resource_list_add_remark" label="资源描述：" prompt="请输入描述", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_base_resource_list_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_base_resource_list_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
	    
	    <div id="_base_resource_list_edit_window" class="easyui-window" title="编辑" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_base_resource_list_edit_form" >
        		<sec:csrfInput/>
        		<input type="hidden" name="menuname" id="_edit_menuname">
        		<input type="hidden" name="basemenuid" id="_edit_basemenuid">
        		<input type="hidden" class="easyui-textbox" name="baseresourceid" id="_base_resource_list_edit_baseresourceid" >
		        <div style="padding: 2px;">
	        		<input name="resourceno" readonly="readonly" id="_base_resource_list_edit_resourceno" label="资源编号：" prompt="请输入资源编号", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,200]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="padding: 2px;">
	        		<input name="name" id="_base_resource_list_edit_name" label="资源名称：" prompt="请输入资源名称", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input id="_base_resource_list_edit_menuname" label="对应模块：" prompt="请输入对应模块", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="padding: 2px;">
		        	<select id="_base_resource_list_edit_status" name="status" class="easyui-combobox" label="是否锁定：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;"></select>
		        </div>
	        	<div style="padding: 2px;">
	        		<input name="remark" id="_base_resource_list_edit_remark" label="描述：" prompt="请输入描述", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_base_resource_list_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_base_resource_list_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
		
		<div id="_base_resource_list_linkRole_window" class="easyui-window" title="资源授权" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height:600px;">
			<div class="easyui-layout" data-options="border:false,fit:true">
				<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				    <div style="padding: 5px;">
						<a id="_base_resource_list_linkRole_comfirm" href="javascript:void(0);" class="easyui-linkbutton" data-options="">确定</a>
						<a id="_base_resource_list_linkRole_cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="">取消</a>
					</div>
				</div>
				<div region="center">
					<table id="_base_resource_list_linkRole_table" data-options="fit:true,border:false"></table>
				</div>
			</div>
		</div>
	</body>
</html>