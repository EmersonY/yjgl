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
		<script type="text/javascript" src="../sjwh/sjwh_jgxlgl_list.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:over;height: 300px">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">数据更新管理</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_sjwh_jgxlgl_list_search_form">
						<select id="_sjwh_jgxlgl_list_search_jglx" class="easyui-combobox" label="井盖类型：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgxlgl_list_search_jgxz" class="easyui-combobox" label="井盖形状：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgxlgl_list_search_jgcz" class="easyui-combobox" label="井盖材质：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgxlgl_list_search_sfzw" class="easyui-combobox" label="防坠网状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<br><br>
						<select id="_sjwh_jgxlgl_list_search_jgzt" class="easyui-combobox" label="井盖状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<select id="_sjwh_jgxlgl_list_search_ssdl" class="easyui-combobox" label="所属道路：" prompt="请输入所属道路" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
						<input id="_sjwh_jgxlgl_list_search_jngj" class="easyui-textbox" label="井内管径：" prompt="请输入井内管径" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input id="_sjwh_jgxlgl_list_search_jggg" class="easyui-textbox" label="井盖规格：" prompt="请输入井盖规格" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<br><br>
		        		<input class="easyui-textbox" id="_sjwh_jgxlgl_list_search_jgsl" label="井盖数量：" prompt="请输入井盖数量" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
						<input class="easyui-textbox" id="_sjwh_jgxlgl_list_search_js" label="井盖深度：" prompt="请输入井盖深度" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
						<input class="easyui-textbox" id="_sjwh_jgxlgl_list_search_gldw" label="管理单位：" prompt="请输入管理单位" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<input class="easyui-textbox" id="_sjwh_jgxlgl_list_search_qsdw" label="权属单位：" prompt="请输入权属单位" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						<br><br>			        
				        <input class="easyui-textbox" id="_sjwh_jgxlgl_list_search_jgbh" label="井盖编号：" prompt="请输入井盖编号" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
						&nbsp;
				        <a href="#" class="easyui-linkbutton" id="_sjwh_jgxlgl_list_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_sjwh_jgxlgl_list_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
					<a href="#" class="easyui-linkbutton" id="_sjwh_jgxlgl_list_export_button">导出Excel</a>
					<a href="#" class="easyui-linkbutton" id="_sjwh_jgxlgl_list_view_button">查看详情</a>
<!--			    <a href="#" class="easyui-linkbutton" id="_sjwh_jgxlgl_list_import_button">导入Excel</a>
 					<a href="#" class="easyui-linkbutton l-btn-danger" id="_sjwh_jgxlgl_list_delete_button">删除</a>
-->
				</div>
			</div>
			<div region="center">
				<table id="_sjwh_jgxlgl_list_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_sjwh_jgxlgl_list_location_window" class="easyui-window" title="位置" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:100%; height:100%;">
			<iframe style="width:98%; height:98%;" id="_sjwh_jgxlgl_list_location_iframe"></iframe>
		</div>
		
	    <div id="_sjwh_jgxlgl_list_view_window" class="easyui-window" title="查看详情" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:600px;padding:10px;">
			<form action="" method="post" id="_sjwh_jgxlgl_list_view_form" >
			<input type="hidden" name="xzb" id="_sjwh_jgxlgl_list_view_xzb">
			<input type="hidden" name="yzb" id="_sjwh_jgxlgl_list_view_yzb">
				<table width="100%" class="table-view">
					<tr>
						<th>井盖编号：</th>
						<td>
							<input name="jgbh" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_jgbh"/>
						</td>
						<th>井内管径：</th>
						<td>
							<input name="jngj" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_jngj"/>
						</td>
					</tr>
					<tr>
						<th>井盖状态：</th>
						<td>
							<input name="jgzt" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_jgzt"/>
						</td>
						<th>井盖规格：</th>
						<td>
							<input name="jggg" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_jggg"/>
						</td>
					</tr>
					<tr>
						<th>井盖材质：</th>
						<td>
							<input name="jgcz" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_jgcz"/>
						</td>
						<th>防坠网状态：</th>
						<td>
							<input name="sfzw" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_sfzw"/>
						</td>
					</tr>
					<tr>
						<th>井盖形状：</th>
						<td>
							<input name="jgxz" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_jgxz"/>
						</td>
						<th>井盖类型：</th>
						<td>
							<input name="jglx" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_jglx"/>
						</td>
					</tr>
					<tr>
						<th>操作时间：</th>
						<td>
							<input name="czrj" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_czrj"/>
						</td>
						<th>操作人：</th>
						<td>
							<input name="czr" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_czr"/>
						</td>
					</tr>
					<tr>
						<th>权属单位：</th>
						<td>
							<input name="qsdw" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_qsdw"/>
						</td>
						<th>管理单位：</th>
						<td>
							<input name="gldw" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_gldw"/>
						</td>
					</tr>
					<tr>
						<th>所属道路：</th>
						<td>
							<input name="ssdl" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_ssdl"/>
						</td>
						<th>道路建设时间：</th>
						<td>
							<input name="dljssj" type="text" class="easyui-textbox" style="width:85%" data-options="required:false" id="_sjwh_jgxlgl_list_view_dljssj"/>
						</td>
					</tr>
					<tr>
						<th style="height:70px">井盖内部图片：</th>
						<td>
							<div id="_sjwh_jgxlgl_list_view_nbtp_no_div">暂未上传内部图片</div>
							<div id="_sjwh_jgxlgl_list_view_nbtp_div">
								<img id="_sjwh_jgxlgl_list_view_nbtp" width="80" height="70" style="display: block;background-size:cover;">
								<a href="#" onclick="SjwhJgxlglList.findHighDefinition(1)">高清</a>
							</div>
						</td>
						<th>井盖近景图片：</th>
						<td>
							<div id="_sjwh_jgxlgl_list_view_jjtp_no_div">暂未上传近景图片</div>
							<div id="_sjwh_jgxlgl_list_view_jjtp_div">
								<img id="_sjwh_jgxlgl_list_view_jjtp" width="80" height="70" style="display: block;background-size:cover;">
								<a href="#" onclick="SjwhJgxlglList.findHighDefinition(2)">高清</a>
							</div>
						</td>
					</tr>
					<tr>
						<th>井盖远景图片：</th>
						<td>
							<div id="_sjwh_jgxlgl_list_view_yjtp_no_div">暂未上传远景图片</div>
							<div id="_sjwh_jgxlgl_list_view_yjtp_div">
								<img id="_sjwh_jgxlgl_list_view_yjtp" width="80" height="70" style="display: block;background-size:cover;">
								<a href="#" onclick="SjwhJgxlglList.findHighDefinition(3)">高清</a>
							</div>
						</td>
					</tr>	
				</table>
			</form>
			<div style="padding: 2px;text-align: center;">
	        	<a id="_sjwh_jgxlgl_list_view_wz_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看位置</a>
	        	<a id="_sjwh_jgxlgl_list_view_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
			</div>
		</div>
		<div id="_sjwh_jgxlgl_list_import_window" class="easyui-window" title="导入Excel" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:400px;padding:10px;height: 150px">
			<form id="_sjwh_choose_file_form" enctype="multipart/form-data" method="POST">
				<input class="easyui-filebox" id="_sjwh_import_choose_file" name="fileList" data-options="labelWidth:80,labelAlign:'right',required:true" style="width:350px;height: 34px">
				<div style="padding: 2px;text-align: center;">
		        	<a id="_sjwh_jgxlgl_list_import_comfirm_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">导入</a>
		        	<a id="_sjwh_jgxlgl_list_import_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
				</div>
			</form>
		</div>
	</body>
</html>