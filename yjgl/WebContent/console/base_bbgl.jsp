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
		<script type="text/javascript" src="../console/base_bbgl.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">模块名称</legend>
				</fieldset>
<%-- 				<div style="padding: 5px;">
					<form action="" id="_base_bbgl_search_form">
				       	<!-- <input class="easyui-textbox" id="XXX" label="XXX：" prompt="请输入XXX" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;"> -->
				        <a href="#" class="easyui-linkbutton" id="_base_bbgl_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_base_bbgl_reset_button">重置</a>
			        </form>
			    </div> --%>
			    <div style="padding: 5px;">
					<a id="_base_bbgl_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">上传</a>
					<a id="_base_bbgl_download_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">下载</a>
					<a id="_base_bbgl_delete_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">删除</a>
					
					<!-- 
					<a id="_base_bbgl_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑</a>
					<a id="_base_bbgl_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看</a>
					<a id="_base_bbgl_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除</a>
					-->
				</div>
			</div>
			<div region="center">
				<table id="_base_bbgl_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_base_bbgl_add_window" class="easyui-window" title="新增" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="../YjgAppversionAction/add?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post" id="_base_bbgl_add_form" >
        		<sec:csrfInput/>
				<div style="padding: 2px;">
	        		<input name="versionname" id="_base_bbgl_add_versionname" label="版本名称：" prompt="请输入版本名称", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="versioncontent" id="_base_bbgl_add_versioncontent" label="更新内容：" prompt="请输入版本更新内容", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,300]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
					<select id="_base_bbgl_add_sfqzgx" name="sfqzgx" class="easyui-combobox" label="强制更新：" data-options="labelWidth:80,labelAlign:'right',editable:false" style="width:200px;height: 34px;"></select>
	        	</div>
	        	<div style="padding: 2px;">
					<span style="width:75px;display: inline-block;float: left;padding-right: 5px;text-align:right">更新文件：</span>
					<input id="_base_bbgl_file" name="appfile" style="width:188px;height: 34px;">
		        </div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_base_bbgl_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_base_bbgl_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
	    
	    <div id="_base_bbgl_edit_window" class="easyui-window" title="编辑" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:600px;padding:10px;">
			<form action="" method="post" id="_base_bbgl_edit_form" >
        		<sec:csrfInput/>
		        <div style="padding: 2px;">
	        		<input name="sfqzgx" id="_base_bbgl_edit_sfqzgx" label="0不强制更新，1强制更新：" prompt="请输入0不强制更新，1强制更新", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,22]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="id" id="_base_bbgl_edit_id" label="编号：" prompt="请输入编号", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="versionname" id="_base_bbgl_edit_versionname" label="版本名称：" prompt="请输入版本名称", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="updateperson" id="_base_bbgl_edit_updateperson" label="更新人：" prompt="请输入更新人", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="versioncode" id="_base_bbgl_edit_versioncode" label="版本号：" prompt="请输入版本号", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,22]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="versioncontent" id="_base_bbgl_edit_versioncontent" label="版本更新内容：" prompt="请输入版本更新内容", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,300]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
	        		<input name="updatetime" id="_base_bbgl_edit_updatetime" label="更新时间：" prompt="请输入更新时间", data-options="labelWidth:80,labelAlign:'right',editable:false,required:false" style="width:400px;height: 34px;" class="easyui-datebox" >
	        	</div>
		        <div style="padding: 2px;text-align: center;">
		        	<a id="_base_bbgl_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_base_bbgl_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
		
	    <div id="_base_bbgl_view_window" class="easyui-window" title="查看" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_base_bbgl_view_form" >
				<table width="100%" class="table-view">
					<tr>
						<th>0不强制更新，1强制更新：</th>
						<td>
							<input name="sfqzgx" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_base_bbgl_view_sfqzgx"/>
						</td>
						<th>编号：</th>
						<td>
							<input name="id" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_base_bbgl_view_id"/>
						</td>
					</tr>
					<tr>
						<th>版本名称：</th>
						<td>
							<input name="versionname" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_base_bbgl_view_versionname"/>
						</td>
						<th>更新人：</th>
						<td>
							<input name="updateperson" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_base_bbgl_view_updateperson"/>
						</td>
					</tr>
					<tr>
						<th>版本号：</th>
						<td>
							<input name="versioncode" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_base_bbgl_view_versioncode"/>
						</td>
						<th>版本更新内容：</th>
						<td>
							<input name="versioncontent" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_base_bbgl_view_versioncontent"/>
						</td>
					</tr>
					<tr>
						<th>更新时间：</th>
						<td colspan="3">
							<input name="updatetime" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_base_bbgl_view_updatetime"/>
						</td>
					</tr>	
				</table>
			</form>
		</div>
		
	</body>
</html>