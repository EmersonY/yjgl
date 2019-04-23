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
		<script type="text/javascript" src="../sjwh/sjwh_jgsjsh_list.js" charset="UTF-8"></script>
		
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:over;height: 300px">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">数据审核</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_sjwh_jgsjsh_list_search_form">
						<select id="_sjwh_jgsjsh_list_search_jglx" class="easyui-combobox" label="井盖类型：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgsjsh_list_search_jgxz" class="easyui-combobox" label="井盖形状：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgsjsh_list_search_sfzw" class="easyui-combobox" label="防坠网状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgsjsh_list_search_jgcz" class="easyui-combobox" label="井盖材质：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<br><br>
				        <select id="_sjwh_jgsjsh_list_search_ssdl" class="easyui-combobox" label="所属道路：" prompt="请输入所属道路" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgsjsh_list_search_shzt" class="easyui-combobox" label="审核状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<input id="_sjwh_jgsjsh_list_search_jggg" class="easyui-textbox" label="井盖规格：" prompt="请输入井盖规格" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input id="_sjwh_jgsjsh_list_search_jngj" class="easyui-textbox" label="井内管径：" prompt="请输入井内管径" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<br><br>
						<input class="easyui-textbox" id="_sjwh_jgsjsh_list_search_lsjs" label="井盖深度：" prompt="请输入井盖深度" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input class="easyui-textbox" id="_sjwh_jgsjsh_list_search_sbr" label="上报人：" prompt="请输入上报人" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input class="easyui-datebox" id="_sjwh_search_sbsj_timeStart" label="上报时间：" prompt="请输入开始时间" data-options="labelWidth:85,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
						 &nbsp;至&nbsp;
				        <input class="easyui-datebox" id="_sjwh_search_sbsj_timeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:180px;height: 34px;">
				        <br><br>
				        <input class="easyui-textbox" id="_sjwh_jgsjsh_list_search_lsjgsl" label="井盖数量：" prompt="请输入井盖数量" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input class="easyui-textbox" id="_sjwh_jgsjsh_list_search_shr" label="审核人：" prompt="请输入审核人" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input class="easyui-datebox" id="_sjwh_search_shsj_timeStart" label="审核时间：" prompt="请输入开始时间" data-options="labelWidth:85,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
						 &nbsp;至&nbsp;
				        <input class="easyui-datebox" id="_sjwh_search_shsj_timeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:180px;height: 34px;">
				        &nbsp;
				        <a href="#" class="easyui-linkbutton" id="_sjwh_jgsjsh_list_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_sjwh_jgsjsh_list_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
			    	<a id="_sjwh_jgsjsh_list_pass_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">审核通过</a>
					<a id="_sjwh_jgsjsh_list_refuse_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">审核不通过</a>
					<a id="_sjwh_jgsjsh_list_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看</a>
<!-- 				<a id="_sjwh_jgsjsh_list_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除</a>-->				</div>
			</div>
			<div region="center">
				<table id="_sjwh_jgsjsh_list_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_sjwh_jgxlgl_list_location_window" class="easyui-window" title="位置" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:100%; height:100%;">
			<iframe style="width:98%; height:98%;" id="_sjwh_jgxlgl_list_location_iframe"></iframe>
		</div>
		
	    <div id="_sjwh_jgsjsh_list_view_window" class="easyui-window" title="查看" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:600px;padding:10px;">
			<form action="" method="post" id="_sjwh_jgsjsh_list_view_form" >
				<input type="hidden" name="lsxzb" id="_sjwh_jgsjsh_list_view_lsxzb">
				<input type="hidden" name="lsyzb" id="_sjwh_jgsjsh_list_view_lsyzb">
				<table width="100%" class="table-view">
					<tr>
						<th>井盖状态：</th>
						<td>
							<input name="lsjgzt" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsjgzt"/>
						</td>
						<th>井盖规格：</th>
						<td>
							<input name="lsjggg" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsjggg"/>
						</td>
					</tr>
					<tr>
						<th>井盖材质：</th>
						<td>
							<input name="lsjgcz" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsjgcz"/>
						</td>
						<th>是否有防坠网：</th>
						<td>
							<input name="lssfzw" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lssfzw"/>
						</td>
					</tr>
					<tr>
						<th>井盖形状：</th>
						<td>
							<input name="lsjgxz" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsjgxz"/>
						</td>
						<th>井盖类型：</th>
						<td>
							<input name="lsjglx" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsjglx"/>
						</td>
					</tr>
					<tr>
						<th>井盖编号：</th>
						<td>
							<input name="lsjgbh" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsjgbh"/>
						</td>
						<th>井内管径：</th>
						<td>
							<input name="lsjngj" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsjngj"/>
						</td>
					</tr>
					<tr>
						<th>上报人：</th>
						<td>
							<input name="sbr" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_sbr"/>
						</td>
						<th>上报时间：</th>
						<td>
							<input name="sbsj" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_sbsj"/>
						</td>
					</tr>
					<tr>
						<th>管理单位：</th>
						<td>
							<input name="lsgldw" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsgldw"/>
						</td>
						<th>权属单位：</th>
						<td>
							<input name="lsqsdw" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsqsdw"/>
						</td>
					</tr>
					<tr>
						<th>所属道路：</th>
						<td>
							<input name="lsssdl" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_lsssdl"/>
						</td>
						<th>审核状态</th>
						<td>
							<input readonly="readonly" name="shzt" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_shzt"/>
						</td>
					</tr>
					<tr>
						<th>审核人：</th>
						<td>
							<input name="shrxm" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_shr"/>
						</td>
						<th>审核时间：</th>
						<td>
							<input name="shsj" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgsjsh_list_view_shsj"/>
						</td>
					</tr>
					<tr>
						<th style="height:70px">井盖内部图片：</th>
						<td>
							<div id="_sjwh_jgsjsh_list_view_nbtp_no_div">暂未上传内部图片</div>
							<div id="_sjwh_jgsjsh_list_view_nbtp_div">
								<img id="_sjwh_jgsjsh_list_view_nbtp" width="80" height="70" style="display: block;background-size:cover;">
								<a href="#" onclick="SjwhJgsjshList.findHighDefinition(1)">高清</a>
							</div>
						</td>
						<th>井盖近景图片：</th>
						<td>
							<div id="_sjwh_jgsjsh_list_view_jjtp_no_div">暂未上传近景图片</div>
							<div id="_sjwh_jgsjsh_list_view_jjtp_div">
								<img id="_sjwh_jgsjsh_list_view_jjtp" width="80" height="70" style="display: block;background-size:cover;">
								<a href="#" onclick="SjwhJgsjshList.findHighDefinition(2)">高清</a>
							</div>
						</td>
					</tr>
					<tr>
						<th>井盖远景图片：</th>
						<td>
							<div id="_sjwh_jgsjsh_list_view_yjtp_no_div">暂未上传远景图片</div>
							<div id="_sjwh_jgsjsh_list_view_yjtp_div">
								<img id="_sjwh_jgsjsh_list_view_yjtp" width="80" height="70" style="display: block;background-size:cover;">
								<a href="#" onclick="SjwhJgsjshList.findHighDefinition(3)">高清</a>
							</div>
						</td>
					</tr>
				</table>
			</form>
			<div style="padding: 2px;text-align: center;">
	        	<a id="_sjwh_jgsjsh_list_view_wz_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看位置</a>
	        	<a id="_sjwh_jgsjsh_list_view_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
			</div>
		</div>
	</body>
</html>