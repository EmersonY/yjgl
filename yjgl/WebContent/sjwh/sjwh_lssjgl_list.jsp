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
		<script type="text/javascript" src="../sjwh/sjwh_lssjgl_list.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">历史数据管理</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_sjwh_lssjgl_list_search_form">
				       	<input class="easyui-textbox" id="_sjwh_lssjgl_list_search_hisjgbh" label="井盖编号：" prompt="请输入井盖编号" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<select id="_sjwh_lssjgl_list_search_hisjglx" class="easyui-combobox" label="井盖类型：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
				        <select id="_sjwh_lssjgl_list_search_hisssdl" class="easyui-combobox" prompt="请输入所属道路" label="所属道路：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:300px;height: 34px;"></select>
				       	<br><br>
				       	<input class="easyui-textbox" id="_sjwh_lssjgl_list_search_hisczr" label="操作人：" prompt="请输入操作人" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input class="easyui-datebox" id="_sjwh_lssjgl_list_search_timeStart" label="操作时间：" prompt="请输入开始时间" data-options="labelWidth:85,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
						&nbsp;至&nbsp;
				        <input class="easyui-datebox" id="_sjwh_lssjgl_list_search_timeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:180px;height: 34px;">
				        &nbsp;
				        <a href="#" class="easyui-linkbutton" id="_sjwh_lssjgl_list_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_sjwh_lssjgl_list_reset_button">重置</a>
			        </form>
			    </div>
			</div>
			<div region="center">
				<table id="_sjwh_lssjgl_list_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
	</body>	
</html>