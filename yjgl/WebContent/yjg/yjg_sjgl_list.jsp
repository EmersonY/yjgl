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
		<script type="text/javascript" src="../yjg/yjg_sjgl_list.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="../css/sjgl.css">
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:over;height: 300px">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">事件管理</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_yjg_sjdj_list_search_form">
		        		<select id="_yjg_sjdj_list_sqzt" name="sqzt" class="easyui-combobox" label="事件状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
		        		<select id="_yjg_sjdj_list_sjlx" name="sjlx" class="easyui-combobox" label="事件性质：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:200px;height: 34px;"></select>
				    	<input class="easyui-textbox" id="_yjg_sjdj_list_sbrxm" label="上报人姓名：" prompt="请输入上报人姓名" data-options="labelWidth:90,labelAlign:'right'" style="width:200px;height: 34px;">	
		        		<select id="_yjg_sjdj_list_xxly" name="xxly" class="easyui-combobox" label="信息来源：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:200px;height: 34px;"></select>
						<input class="easyui-textbox" id="_yjg_sjdj_list_sjdjdh" label="事件单号：" prompt="请输入登记单号" data-options="labelWidth:90,labelAlign:'right'" style="width:200px;height: 34px;">	
		        		<br><br>
		        		<input class="easyui-textbox" id="_yjg_sjdj_list_jgbh" label="井盖编号：" prompt="请输入井盖编号" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
		        		<select id="_yjg_sjdj_list_jglx" name="jglx" class="easyui-combobox" label="井盖类型：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:200px;height: 34px;"></select>
		        		<input class="easyui-combobox" id="_yjg_sjdj_list_jgxz" label="井盖形状：" prompt="请输入井盖形状" data-options="labelWidth:90,labelAlign:'right'" style="width:200px;height: 34px;">	
		        		<input class="easyui-combobox" id="_yjg_sjdj_list_sfzw" label="防坠网状态：" prompt="请输入防坠网状态" data-options="labelWidth:90,labelAlign:'right'" style="width:200px;height: 34px;">	
		        		<input class="easyui-combobox" id="_yjg_sjdj_list_jgzt" label="井盖状态：" prompt="请输入井盖状态" data-options="labelWidth:90,labelAlign:'right'" style="width:200px;height: 34px;">	
		        		<br><br>
		        		<input class="easyui-textbox" id="_yjg_sjdj_list_jngj" label="井内管径：" prompt="请输入井内管径" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
		        		<input class="easyui-textbox" id="_yjg_sjdj_list_jggg" label="井盖规格：" prompt="请输入井盖规格" data-options="labelWidth:90,labelAlign:'right'" style="width:200px;height: 34px;">	
		        		<input class="easyui-datebox" id="_yjg_sjdj_list_ksTimeStart" label="生成时间：" data-options="labelWidth:90,labelAlign:'right',editable:false" style="width:190px;height: 34px;">
					       	 &nbsp;至&nbsp;
				        <input class="easyui-datebox" id="_yjg_sjdj_list_ksTimeEnd"data-options="labelAlign:'right',editable:false" style="width:100px;height: 34px;">
				        <input class="easyui-datebox" id="_yjg_sjdj_list_jsTimeStart" label="结束时间：" data-options="labelWidth:90,labelAlign:'right',editable:false" style="width:190px;height: 34px;">
					       	 &nbsp;至&nbsp;
				        <input class="easyui-datebox" id="_yjg_sjdj_list_jsTimeEnd" data-options="labelAlign:'right',editable:false" style="width:100px;height: 34px;">
				        	&nbsp;
				        <br><br>
		        		<select id="_yjg_sjdj_list_yzjb" name="yzjb" class="easyui-combobox" label="严重级别：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
		        		<input class="easyui-combobox" id="_yjg_sjdj_list_jgcz" label="井盖材质：" prompt="请输入井盖材质" data-options="labelWidth:90,labelAlign:'right'" style="width:200px;height: 34px;">	
		        		<input class="easyui-combobox" id="_yjg_sjdj_list_ssdl" label="所属道路：" prompt="请输入所属道路" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
				        &nbsp;
				        <a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
					<a id="_yjg_sjdj_list_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">添加事件</a>
					<a id="_yjg_sjdj_list_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑事件</a>
					<a id="_yjg_sjdj_list_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看详情</a>
					<c:if test="${fns:getAdminPath('sjgl_hbsj')}">
						<a id="_yjg_sjdj_list_merge_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">合并事件</a>
					</c:if>
 					<c:if test="${fns:getAdminPath('sjgl_dcdy')}">
 						<a id="_yjg_sjdj_list_export" href="javascript:void(0);" class="easyui-linkbutton" data-options="">台账导出打印</a>
					</c:if>
					<c:if test="${fns:getAdminPath('sjgl_zzddc')}">
						<a id="_yjg_sjdj_list_export_word_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">转办单导出</a>
					</c:if>
					<c:if test="${fns:getAdminPath('sjgl_scsj')}">
						<a id="_yjg_sjdj_list_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除事件</a>
					</c:if>
				</div>
			</div>
			<div region="center">
				<table id="_yjg_sjdj_list_datagrid" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_yjg_sjdj_list_export_window" class="easyui-window" title="导出打印" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form method="post" id="_yjg_sjdj_list_export_form">
				<input name="export_year" id="_yjg_sjdj_list_export_year" label="年份：" prompt="请选择导出年份", data-options="labelWidth:80,labelAlign:'right'" style="width:400px;height: 34px;" class="easyui-combobox" >
				<input name="export_month" id="_yjg_sjdj_list_export_month" label="月份：" prompt="请选择导出事件月份", data-options="labelWidth:80,labelAlign:'right',required:true,editable:false" style="width:400px;height: 34px;" class="easyui-combobox" >
			</form>
			<div style="padding: 2px;text-align: center;">
		        <a id="_yjg_sjdj_list_export_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">导出</a>
		        <a id="_yjg_sjdj_list_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
			 </div>
		</div>
		
		<div id="_yjg_sjdj_list_add_window" class="easyui-window" title="新增事件" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="height:500px;width:500px;padding:10px;">
			<div class="easyui-tabs">  
		    	<div title="非窨井事件">
					<form method="post" id="_yjg_fyjSjdj_list_add_form" >
		    		<input type="hidden" id="_yjg_fyj_add_imageTime" value=0>
		    		<input type="hidden" name="imageIds" id ="_yjg_fyj_add_imageIds"/> 
		    		<input type="hidden" name="vedio" id ="_yjg_fyj_add_vedio"/> 
		        		<sec:csrfInput/>
						<div style="padding: 2px;">
			        		<input name="sjlx" id="_yjg_fyjSjdj_list_add_sjlx" label="事件性质：" prompt="请输入事件类型", data-options="labelWidth:80,labelAlign:'right'" style="width:400px;height: 34px;" class="easyui-combobox" >
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="xxly" id="_yjg_fyjSjdj_list_add_xxly" label="信息来源：" prompt="请输入信息来源", data-options="labelWidth:80,labelAlign:'right'" style="width:400px;height: 34px;" class="easyui-combobox" >
			        	</div>
			        	<div style="padding: 2px;">
			        		<select name="ckqx" id="_yjg_fyjSjdj_list_add_ckqx" class="easyui-combogrid" label="查看权限：" prompt="非私密件此项不填", data-options="labelAlign:'right',labelWidth:80,multiple:true,multiline:true" style="width:400px;height: 50px;"></select>
			        	</div>
			        	<div style="padding: 2px;">
			        	    <a style="width:75px;float: left;padding-right: 5px;text-align:right">所在位置：</a>
			        		<a id="_yjg_fyjsjdj_list_add_wz_button"   href="javascript:void(0);" data-options=""  class="easyui-linkbutton" >选择</a>
			        		<input type="hidden" name="xzb" id ="_yjg_fyjSjdj_list_add_xzb"/> 
			        		<input type="hidden" name="yzb" id ="_yjg_fyjSjdj_list_add_yzb"/> 
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="" id="_yjg_fyjSjdj_list_add_input_xzqh" label="行政区划：" prompt="请输入行政区划", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
			        		<input type="hidden" name="xzqh" id ="_yjg_fyjSjdj_list_add_xzqh"/>
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="wzms" id="_yjg_fyjSjdj_list_add_wzms" label="位置描述：" prompt="请输入位置描述", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
			        	</div>
						<div style="padding: 2px;">
			        		<input name="bz"  id="_yjg_fyjSjdj_list_add_bz" label="基本情况：" prompt="请输入基本情况", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
			        	</div>
					</form>
	        		<div class="apply_footer_img">
						<span class="apply_footer_select">
						<span style="width:75px;display: inline-block;float: left;padding-right: 5px;text-align:right">上报图片：</span>
							<form id="_yjg_fyjSjdj_add_img_form" enctype="multipart/form-data" method="POST">
								<span class="apply_footer_upload flex">
						    		<span class="bg flex_item">
						    			<input name="operatype" type="hidden" value="YJG_IMG_FILE">
				                    	<img width="150" height="180" style="display: block; width: 100%; height: 100%;">
										<span class="bg" onclick="$(this).next().click()"></span>
										<input type="file" id="_yjg_fyjSjdj_add_imageFile" name="imageFile" class="file" onchange="YjgSjdjList.insertImg(this)">
			                		</span>
			            		</span>
		            		</form>
		            		<script type="text/x-jsrender" id="fyjSjdj_img">
								<span class="apply_footer_upload_new flex">
				    				<span class="bg flex_item">
	                   	 				<img onclick="YjgSjdjList.deleteImg(this,'{{:fileid}}')"  src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
									</span>								
								</span>
							</script>
						</span>
					</div>
					<div class="apply_footer_img">
						<form id="_fyj_choose_file_form" enctype="multipart/form-data" method="POST">
							<input name="operatype" type="hidden" value="YJG_VEDIO_FILE">
							<span style="width:75px;display: inline-block;float: left;padding-right: 5px;text-align:right">上报视频：</span>
							<input id="_fyj_choose_file" name="vediofile" style="width:265px;height: 34px;">
						</form>
					</div>
			        <div style="padding: 2px;text-align: center;">
			        	<a id="_yjg_fyjSjdj_list_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
			        	<a id="_yjg_fyjSjdj_list_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
			        </div>
			    </div>
		    	<div title="窨井事件"> 
					<form action="" method="post" id="_yjg_sjdj_list_add_form" >
		        		<sec:csrfInput/>
			        	<input type="hidden" id="_yjg_yj_add_imageTime" value=0>
		    			<input type="hidden" name="imageIds" id ="_yjg_yj_add_imageIds"/> 
		    			<input type="hidden" name="vedio" id ="_yjg_yj_add_vedio"/> 
			        	<div style="padding: 2px;">
			        		<input name="sjlx" id="_yjg_sjdj_list_add_sjlx" value="1" type="hidden">
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="xxly" id="_yjg_sjdj_list_add_xxly" label="信息来源：" prompt="请输入信息来源", data-options="labelWidth:80,labelAlign:'right'" style="width:400px;height: 34px;" class="easyui-combobox" >
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="yzjb" id="_yjg_sjdj_list_add_yzjb" label="严重级别：" prompt="请输入严重级别", data-options="labelWidth:80,labelAlign:'right'" style="width:400px;height: 34px;" class="easyui-combobox" >
			        	</div>
			        	<div style="padding: 2px;">
			        		<select class="easyui-combogrid" id="_yjg_sjdj_list_add_ckqx" name="ckqx" label="查看权限：" prompt="非私密件此项不填" data-options="labelAlign:'right',labelWidth:80,multiple:true,multiline:true" style="width:400px;height:50px"></select>
			        	</div>
			        	<div style="padding: 2px;">
			        	    <a style="width:75px;float: left;padding-right: 5px;text-align:right" >所在位置：</a>
			        		<a id="_yjg_sjdj_list_add_wz_button"   href="javascript:void(0);" data-options=""  class="easyui-linkbutton" >选择</a>
			        		<input type="hidden" name="xzb" id ="_yjg_sjdj_list_add_xzb"/> 
			        		<input type="hidden" name="yzb" id ="_yjg_sjdj_list_add_yzb"/> 
			        	</div>
						<div style="padding: 2px;">
			        		<input name="jglx" id="_yjg_sjdj_list_add_input_jglx" label="井盖类型：" prompt="请输入井盖类型", data-options="labelWidth:80,labelAlign:'right'" style="width:400px;height: 34px;" class="easyui-combobox" >
			        		<input type="hidden" id ="_yjg_sjdj_list_add_jglx"/>
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="ssdl" id="_yjg_sjdj_list_add_input_ssdl" label="所属道路：" prompt="请输入所属道路", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
			        		<input type="hidden" id ="_yjg_sjdj_list_add_ssdl"/>
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="xzqh" id="_yjg_sjdj_list_add_input_xzqh" label="行政区划：" prompt="请输入行政区划", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
			        		<input type="hidden" id ="_yjg_sjdj_list_add_xzqh"/>
			        	</div>
 	        			<div style="padding: 2px;">
			        		<input name="jgid" readonly="readonly" id="_yjg_sjdj_list_add_input_jgid" label="井盖编号：" prompt="请选择所在位置", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
			        		<input type="hidden" id ="_yjg_sjdj_list_add_jgid"/>
			        	</div>
			        	<div style="padding: 2px;">
			        		<input name="wzms" id="_yjg_sjdj_list_add_wzms" label="位置描述：" prompt="请输入位置描述", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
			        	</div>
						<div style="padding: 2px;">
			        		<input name="bz" id="_yjg_sjdj_list_add_bz" label="基本情况：" prompt="请输入基本情况", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
			        	</div>
			         </form>	
				     <div class="apply_footer_img">
						 <span class="apply_footer_select">
						 <span style="width:75px;display: inline-block;float: left;padding-right: 5px;text-align:right">上报图片：</span>
							 <form id="_yjg_sjdj_add_img_form" enctype="multipart/form-data" method="POST">
								 <span class="apply_footer_upload flex">
						    		 <span class="bg flex_item">
				                    	 <input name="operatype" type="hidden" value="YJG_IMG_FILE">
				                    	 <img width="150" height="180" style="display: block; width: 100%; height: 100%;">
										 <span class="bg" onclick="$(this).next().click()"></span>
										 <input type="file" id="_yjg_yjSjdj_add_imageFile" name="yjImageFile" class="file" onchange="YjgSjdjList.yjInsertImg(this)">
			                		 </span>
			            		 </span>
		            		 </form>
			            	 <script type="text/x-jsrender" id="sjdj_img">
									 <span class="apply_footer_upload_new flex">
				    					 <span class="bg flex_item">
	                   	 					 <img onclick="YjgSjdjList.yjDeleteImg(this,'{{:fileid}}')"  src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
										 </span>
									 </span>
							 </script>
						 </span>
					 </div>
			         <div class="apply_footer_img">
						<form id="_yj_choose_file_form" enctype="multipart/form-data" method="POST">
							<input name="operatype" type="hidden" value="YJG_VEDIO_FILE">
							<span style="width:75px;display: inline-block;float: left;padding-right: 5px;text-align:right">上报视频：</span>
							<input id="_yj_choose_file" name="yjVediofile" style="width:265px;height: 34px;">
						</form>
					 </div>
				     <div style="padding: 2px;text-align: center;">
				     	<a id="_yjg_sjdj_list_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
				     	<a id="_yjg_sjdj_list_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
				     </div>
			    </div>
	    	</div>
	    </div>
		
	   <div id="_yjg_sjdj_list_view_window" class="easyui-window" title="查看事件" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true">
			<div class="easyui-layout" data-options="fit:true">
				<div region="north" data-options="collapsible:false" id="_view_north" style="padding: 5px;overflow:auto" title="从属主事件详情">
					<input type="hidden" id="_sjlx">
					<div style="padding: 5px;">
			        	<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_view_wz_button">查看位置信息</a>
			        	<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_view_center_czsb_button">查看上报附件</a>
  						<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_view_center_cz_before_button">查看处置前附件</a>
  						<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_view_center_cz_after_button">查看处置后附件</a>
  						<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_designate_button">认领详情</a>
  					</div>
					<form action="" method="post" id="_yjg_sjdj_list_view_form" >
						<input name="xzb" id="_yjg_sjdj_list_view_xzb"  type="hidden">
	        			<input name="yzb" id="_yjg_sjdj_list_view_yzb"  type="hidden">
						<table width="100%" class="table-view">
							<tr>
								<th>登记单号：</th>
								<td>
									<input name="sjdjdh" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_sjdjdh"/>
								</td>
								<th>信息来源：</th>
								<td>
									<input name="xxly" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_xxly"/>
								</td>
								<th>上报人姓名：</th>
								<td>
									<input name="sbrxm" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_sbrxm"/>
								</td>
								<th>上报人电话：</th>
								<td>
									<input name="sbrdh" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_sbrdh"/>
								</td>
							</tr>
							<tr>
								<th>井盖编号：</th>
								<td>
									<input name="jgid" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_jgid"/>
								</td>
								<th>井盖类型：</th>
								<td>
									<input name="jglx" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_jglx"/>
								</td>
								<th>处理状态 ：</th>
								<td>
									<input name="sqzt" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_sqzt"/>
								</td>
								<th>严重级别：</th>
								<td>
									<input name="yzjb" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_yzjb"/>
								</td>
							</tr>
							<tr>
								<th>事件性质：</th>
								<td>
									<input name="sjlx" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_sjlx"/>
								</td>
								<th>行政区划：</th>
								<td>
									<input name="xzqh" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_xzqh"/>
								</td>
								<th>生成时间：</th>
								<td>
									<input name="scsj" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_scsj"/>
								</td>
								<th id="_th_jssj">结束时间：</th>
								<td id="_td_jssj">
									<input name="jssj" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_jssj"/>
								</td>
							</tr>
							<tr>
								<th>接件人：</th>
								<td>
									<input type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_jjr"/>
								</td>
								<th>所属道路：</th>
								<td>
									<input name="ssdl" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_ssdl"/>
								</td>
								<th>更新人姓名：</th>
								<td>
									<input name="updator" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_updator"/>
								</td>
								<th>更新时间：</th>
								<td>
									<input name="updatetime" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_updatetime"/>
								</td>
							</tr>
							<tr>
								<th>基本情况：</th>
								<td colspan="3">
									<input name="bz" type="text" class="easyui-textbox" readonly="readonly" style="width:80%;height: 100px;" data-options="multiline:true,required:false" id="_yjg_sjdj_list_view_bz"/>
								</td>
							</tr>
							<tr>
								<th>位置描述：</th>
								<td colspan="3">
									<input type="text" class="easyui-textbox" readonly="readonly" name="wzms" style="width:80%;height: 100px;" data-options="multiline:true,required:false" id="_yjg_sjdj_list_view_wzms"/>
								</td>
							</tr>
							<tr>
								<th>接件备注：</th>
								<td colspan="3">
									<input type="text" class="easyui-textbox" readonly="readonly" style="width:80%;height: 100px;" data-options="multiline:true,required:false" id="_yjg_sjdj_list_view_jjbz"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div region="center" style="padding: 5px;overflow:auto" title="从属子事件">
					<div class="easyui-layout" data-options="fit:true">
						<div data-options="region:'north',border:false" style="height: 45px;overflow:auto">
							<div style="padding: 5px;">
						        <a href="#" class="easyui-linkbutton l-btn-danger" id="_yjg_sjdj_list_view_center_separate_button">脱离事件</a>
			  				</div>
						</div>
						<div data-options="region:'center',fit:true,border:false" style="padding: 5px;overflow:auto">
							<table id="_yjg_sjdj_list_view_center_table" data-options="fit:true,border:false"></table>
						</div>
					</div>
				</div>
				<div region="south" id="_view_south" data-options="collapsible:false" style="padding: 5px;overflow:auto" title="事件处置进度">
					<div class="easyui-layout" data-options="fit:true,border:false">
						<div data-options="region:'north',border:false" style="height: 45px;overflow:auto">
							<div style="padding: 5px;">
								<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_view_east_view_button">查看详情</a>
<!-- 					  		<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_view_east_viewAll_button">查看全部</a>-->			  				</div>
						</div>
						<div data-options="region:'center',fit:true,border:false" style="padding: 5px;overflow:auto">
							<table id="_yjg_sjcz_list_view_east_table" data-options="fit:true,border:false"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="_yjg_sjdj_czjd_list_view_window" class="easyui-window" title="查看处置进度" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true">
			<form action="" method="post" id="_yjg_sjdj_czjd_list_view_form" >
				<table width="100%" class="table-view">
					<tr>
						<th>操作人：</th>
						<td>
							<input name="czr" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_yjg_sjcz_list_view_czr"/>
						</td>
						<th>处置时间：</th>
						<td>
							<input name="czsj" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_yjg_sjcz_list_view_czsj"/>
						</td>
					</tr>
					<tr>
						<th>处置详情：</th>
						<td colspan="3">
							<input name="czgcms" type="text" class="easyui-textbox" style="width:80%;height:200px" data-options="required:false" id="_yjg_sjcz_list_view_czgcms"/>
						</td>
					</tr>
					<tr>
						<th>处置备注：</th>
						<td colspan="3">
							<input name="bz" type="text" class="easyui-textbox" style="width:80%;height:200px" data-options="required:false" id="_yjg_sjcz_list_view_bz"/>
						</td>
					</tr>
				</table>
			</form>
			<div style="padding: 5px;text-align: center"">
				<a href="#" class="easyui-linkbutton" id="_yjg_sjdj_list_view_enclosure_button">查看附件</a>
	  			<a href="#" class="easyui-linkbutton" class="easyui-linkbutton l-btn-warm" id="_yjg_sjdj_list_view_cancel_button">取消</a>
		  	</div>
		</div>
		
		<div id="_yjg_sjdj_list_merge_window" class="easyui-window" title="合并事件" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:1000px;padding:10px;height: 400px">
			<div class="easyui-layout" data-options="fit:true">
				<div region="center">
					<font color="red" size="3px">*双击选择合并主事件</font>
					<table id="_yjg_sjdj_list_merge_table" style="width:auto;"></table>
				</div>
			</div>
		</div>
		
		<div id="_yjg_sjdj_list_designate_window" class="easyui-window" title="派单详情" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height: 500px">
			<div class="easyui-tabs" id="_sjgl_tabs">  
		    	<div title="认领详情">
					<table id="_designate_user" style="width:auto;"></table>
				</div>
				<div title="确认权责详情">
					<table id="_assign_user" style="width:auto;"></table>
				</div>
			</div>
		</div>
		
		<div id="_yjg_sjdj_list_location_window" class="easyui-window" title="位置" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:100%;padding:10px;height: 100%">
			<iframe style="width:98%; height:98%;" id="_yjg_sjdj_list_location_iframe"></iframe>
		</div>
		
		<div class="yj_add_userTools" id="yj_add_userTools" style="width:100%;height:20px;">  
           	权属单位：<input type="text"  id ="_yj_add_baseroletype_sear" style="width: 150px"/>
            <a href="javascript:void(0)" onclick="BaseType.searQsRole('#_yjg_sjdj_list_add_ckqx', '#_yj_add_baseroletype_sear')">查询</a> 
            <a href="javascript:void(0)" onclick="$('#_yj_add_baseroletype_sear').val('')">重置</a> 
    	</div> 
		
 		<div class="fyj_add_userTools" id="fyj_add_userTools" style="width:100%;height:20px;">  
           	权属单位：<input type="text"  id ="_fyj_add_baseroletype_sear" style="width: 150px"/>
            <a href="javascript:void(0)" onclick="BaseType.searQsRole('#_yjg_fyjSjdj_list_add_ckqx', '#_fyj_add_baseroletype_sear')">查询</a> 
            <a href="javascript:void(0)" onclick="$('#_fyj_add_baseroletype_sear').val('')">重置</a> 
    	</div> 
	</body>
</html>