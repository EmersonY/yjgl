<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<head><meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/></head>  
		<title>流程管理</title>
		<%@ include file="/base/common.jspf"%> 
		<script type="text/javascript" src="../js/banbk.js"  charset="UTF-8"></script>
		<script type="text/javascript" src="../js/baseType.js"  charset="UTF-8"></script>
		<script type="text/javascript" src="../js/util.common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../js/easyui.common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="flow_set.js"  charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="../easyui/themes/metro/easyui.css">
		<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="../css/button.css">
		<link rel="stylesheet" type="text/css" href="../css/base.css"></head>
		<link rel="stylesheet" href="../icons/font_qianniu/iconfont.css">
		<link rel="stylesheet" type="text/css" href="../easyui/themes/material/easyui.css">
		<script type = "text/javascript" > 
		</script> 
<body class="easyui-layout" id="_layout" >
    <div id="_process" data-options="region:'center',border:false,height:$(window).height()">
		<img id="_image" alt="流程图" src="" usemap="#flowmap">
		<map name="flowmap" id="flowmap"></map>
	</div>
	<div data-options="region:'east',border:false,split:false" style="width:300px;overflow-x:hidden;">
		<input type="hidden" id="_buttonJson" value="
			buttons=
			[	
				{'id':'_save_button','name':'保存','function':'save()','type':1,'sequ':1},
				{'id':'_end_button','name':'终止','function':'openStop()','type':2,'sequ':7},
				{'id':'_call_back_button','name':'回退','function':'openRollback()','type':2,'sequ':8},
				{'id':'_dispatcher_button','name':'提交','function':'openForword()','type':2,'sequ':9},
				{'id':'_print_button','name':'打印','function':'printBd()','type':1,'sequ':10},
				{'id':'_over_button','name':'退件','function':'openOver()','type':1,'sequ':11}
			]
		">
		<table>
			<tr>
<!-- 				<td style="display: none;" id="_lccd" >
					<div class="easyui-panel" style="width:260px;" data-options="height:$(window).height(),title:'流程菜单',border:true,closable:false,collapsible:true,minimizable:false,maximizable:false">  
						<ul id="_base_process_menu"></ul>
					</div>
				</td> -->
				<td style="display: none;" id="_lcsq" >
					<div class="easyui-panel" style="width:300px;height:500px;" data-options="title:'流程授权',border:true,closable:false,collapsible:true,minimizable:false,maximizable:false">  
						<ul id="base_process_role"></ul>
					</div>
				
				    <div id = "_node_properties" class="easyui-panel" title="节点属性" style="width:300px;overflow-x:hidden;" data-options="border:true,height: $(window).height()-130,closable:false,collapsible:true,minimizable:false,maximizable:false">  
						<form id="_node_form" method="post">
							<sec:csrfInput/>
							<table width="300px" class="table-edit">
								<tr height="35px">
									<th align="right" >
										节点Id：
									</th>
									<td>
										<input type="text" id="_node_id" name="taskid" readonly="readonly" style="width:120px;"/>
										<input type="hidden" id="_process_id" name="processid"/>
									</td>
								</tr>
								<tr height="35px">
									<th align="right">
										节点Name：
									</th>
									<td >
										<input type="text" name="taskname" id = "_node_name" readonly="readonly" style="width:120px;"/>
									</td>
								</tr>
<!-- 							<tr height="35px">
									<th align="right">
										流程总时长：
									</th>
									<td >
										<input type="text" name="time" id = "_node_maxtime" style="width:120px;"/>
									</td>
								</tr>
								<tr height="35px">
									<th align="right">
										<i class="labelrequired">*</i>&nbsp;按钮：
									</th>
									<td>
										<select id="_buttonSelect" class="easyui-combobox" name="buttonid" style="width:120px;"></select>
									</td>
								</tr>
								<tr height="35px">
									<th align="right">
										持续时间：
									</th>
									<td>
										<input id="_node_max_time" name="maxtimes" class="easyui-numberspinner" value="1" style="width:120px;" data-options="min:0,editable:true">天  
									</td>
								</tr>  
								<tr height="35px">
									<td align="center" colspan="2" >
										<a href="#" class="easyui-linkbutton" onclick="FlowSet.doSave()">保存</a>
										<input type="hidden" name="flowtaskconfigureid" id="_configure_id">
										<input type="hidden" name="id" id="_flow_id">
									</td>
								</tr>
-->
							 </table>
						 </form>
					</div> 
				</td>
			</tr>
		</table>
	</div> 
	<div id="_user_tree" class="easyui-window" data-options="border:false,title:'相关用户',modal:false,closed:true,closable:true,collapsible:false,minimizable:false,maximizable:false" style="width:350px;height:300px;" >
		<table id="_user_table" data-options="border:false"></table>
	</div>
</body>
</html>