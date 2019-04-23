<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>操作日志</title>
		<sec:csrfMetaTags/>
	</head>
	<body>
 		<script type="text/javascript" src="../console/base_log_list.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">操作日志</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="log_search_form">
				       	<select label="模块名称：" id="log_search_module"  class = "easyui-combobox" data-options="labelWidth:85,labelAlign:'right',editable:false,panelHeight:100" style="width: 200px">
							<option value ="-1">--请选择--</option>
							<option value ="用户管理">用户管理</option>
							<option value ="角色管理">角色管理</option>
							<option value ="菜单管理">菜单管理</option>
							<option value ="资源管理">资源管理</option>
						</select>
				       	<input class="easyui-textbox" id="log_search_username" label="操作用户：" prompt="请输入操作用户" data-options="labelWidth:85,labelAlign:'right'" style="width:250px;height: 34px;">
 				        <input class="easyui-textbox" id="log_search_content" label="操作内容：" prompt="请输入操作内容" data-options="labelWidth:85,labelAlign:'right'" style="width:200px;height: 34px;">
   				        <br><br>
 <!--  					<input class="easyui-textbox" id="log_search_result" label="操作结果：" prompt="请输入操作结果" data-options="labelWidth:85,labelAlign:'right'" style="width:200px;height: 34px;">
 -->   				    <input class="easyui-datebox" id="log_search_logtimeStart" label="操作时间：" prompt="请输入开始时间" data-options="labelWidth:85,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
					       	 &nbsp;至&nbsp;
				        <input class="easyui-datebox" id="log_search_logtimeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:180px;height: 34px;">
   				        <a href="#" id="log_search_button" class="easyui-linkbutton">查询</a>
   				        <a href="#" id="log_reset_button"  class="easyui-linkbutton">重置</a>
						<a id="log_view_button" href="javascript:;" class="easyui-linkbutton" data-options="">查看</a>
			        </form>
			    </div>
			</div>
			<div region="center" data-options="border:false">
				<table id="_log_list" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_view_window" class="easyui-window" title="查看日志" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;">
			<table width="99.5%" class="table-view">
				<tr>
					<th width="100px">
						操作人：
					</th>
					<td id="_view_username" width="200px">
					</td>
					<th  width="100px">
						操作时间：
					</th>
					<td id="_view_logtime">
					</td>
				</tr>
				<tr>
					<th>
						操作模块：
					</th>
					<td id="_view_module">	
					</td>	
					<th>
						操作结果：
					</th>
					<td id="_view_result">	
					</td>
				</tr>
				<tr>
					<th>
						操作内容：
					</th>
					<td id="_view_content" colspan="3">	
					</td>
				</tr>
			</table>
<!-- 			<div align="center" style="height: 30px">
				<a id="_view_cancel_button" href="#" onclick="$('#_view_window').window('close')" class="easyui-linkbutton l-btn-warm">取消</a> 
			</div> -->
		</div>
		
	</body>
</html>