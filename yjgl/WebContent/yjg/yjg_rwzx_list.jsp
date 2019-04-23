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
		<script type="text/javascript" src="../yjg/yjg_rwzx_list.js" charset="UTF-8"></script>
		<div id='loading' class="_loading"></div>
			<div class="easyui-tabs" id="_tasktab" data-options="fit:true,border:false">
				<div title="待办业务" data-options="fit:true">
					<div class="easyui-layout" data-options="fit:true">
						<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
							<div style="padding: 5px;">
								<a id="_yjg_rwzx_sjdj_list_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看详情</a>
							</div>
						</div>
						<div region="center">
							<table id="_yjg_rwzx_list_table" data-options="fit:true,border:false"></table>
						</div>
				</div>
			</div>
			<div title="已办业务" data-options="fit:true">
				<div class="easyui-layout" data-options="fit:true">
					<div region="center">
						<table id="_yjg_ybrwzx_list_table" data-options="fit:true,border:false"></table>
					</div>
				</div>
			</div>
<!-- 		<div title="办结任务" data-options="fit:true">
				<div class="easyui-layout" data-options="fit:true">
					<div region="center">
						<table id="_yjg_bjrwzx_list_table" data-options="fit:true,border:false"></table>
					</div>
				</div>
			</div>-->
		</div>
		<div id="_rwzx_window" class="easyui-window" title="提交下一个节点" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;height: 350px">
	        <div style="padding: 2px;text-align: center;">
	        	<form id="_rwzx_form" >
		        	<input type="hidden" id="_first_deny">
		       		<div style="padding: 2px;">
		       			<div style="padding: 2px;display: none;" id="_rwzx_dbzt_div">
				        	<input name="sfdb" id="_rwzx_dbzt" label="是否达标：" data-options="labelWidth:80,labelAlign:'right'" style="width:350px;height: 40px;" class="easyui-combobox" >
				        </div>
			        	<div style="padding: 2px;" id="_rwzx_assignee_div">
		       				<select name="taskEntitys[0].assignee" id="_rwzx_assignee" class="easyui-combogrid" label="接件人：" prompt="请选择接件人", data-options="labelAlign:'right',labelWidth:80,multiple:true,multiline:true" style="width:350px;height: 100px;"></select>
		       			</div>
		       			<div style="padding: 2px;display: none" id="_rwzx_bz_div">
		       				<input name="option"  id="_rwzx_bz" label="备注：" prompt="请输入备注", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:350px;height: 130px;" class="easyui-textbox" >
		        		</div>
		        		<input type="hidden" id="_rwzx_taskkey">
		        		<input type="hidden" id="_rwzx_qsrl" value="0">
		        		<input type="hidden" id="_rwzx_assigneeids" name="taskEntitys[0].assignee">
		        	</div>
		        	<div style="padding: 2px;">
			        	<a id="_rwzx_save_button" href="javascript:void(0);" class="easyui-linkbutton">保存</a>
			        	<a id="_rwzx_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm">取消</a>
		        	</div>
	        	</form>
	        </div>
		</div>
		
		<div id="_yjg_rwzx_list_location_window" class="easyui-window" title="位置" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:100%;padding:10px;height: 100%">
			<iframe style="width:98%; height:98%;" id="_yjg_rwzx_list_location_iframe"></iframe>
		</div>
		
		<div class="_yjg_rwzx_assignTools" id="_yjg_rwzx_assignTools" style="width:100%;height:20px;">  
           	接件人：<input type="text"  id ="_yjg_rwzx_assign_sear" style="width: 120px"/>
            <a href="javascript:void(0)" onclick="BaseType.searAssign('#_rwzx_assignee', '#_yjg_rwzx_assign_sear')">查询</a> 
            <a href="javascript:void(0)" onclick="$('#_yjg_rwzx_assign_sear').val('')">重置</a> 
    	</div> 
		
 		<jsp:include page="yjg_rwzx_ckxq.jsp"></jsp:include>
	</body>
</html>