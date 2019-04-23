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
		<script type="text/javascript" src="../yjg/yjg_yjsj_list.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="../css/sjgl.css">
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">已接事件</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_yjg_sjqq_list_search_form">
				        <input class="easyui-textbox" id="_yjg_sjqq_list_sjdjdh" label="事件单号：" prompt="请输入事件单号" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
				       	<input class="easyui-datebox" id="_yjg_sjqq_list_logtimeStart" label="接件时间：" prompt="请输入开始时间" data-options="labelWidth:80,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
					       	 &nbsp;至&nbsp;
				        <input class="easyui-datebox" id="_yjg_sjqq_list_logtimeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:150px;height: 34px;">
				        <select id="_yjg_yjsj_list_dbzt" name="dbzt" class="easyui-combobox" label="达标状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
				        <a href="#" class="easyui-linkbutton" id="_yjg_sjqq_list_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_yjg_sjqq_list_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
					<a id="_yjg_sjqq_list_sjcz_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">上报处置情况</a>
					<a id="_yjg_yjsj_list_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看详情</a>
				</div>
			</div>
			<div region="center">
				<table id="_yjg_sjqq_list_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_yjg_sjcz_list_window" class="easyui-window" title="查看处理进度" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height: 600px">
			<div region="north" data-options="border:false,fit:true" style="height: 45px">
				<div style="padding: 5px;">
					<a id="_yjg_sjcz_list_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">新增</a>
					<a id="_yjg_sjcz_list_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑</a>
					<a id="_yjg_sjcz_list_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看</a>
					<a id="_yjg_sjcz_list_repair_button" href="javascript:void(0);" class="easyui-linkbutton">处置完成上报</a>
					<a id="_yjg_sjcz_list_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除</a>
					<input type="hidden" id="_datagrid_total">
				</div>
			</div>
			<div region="center">
				<table id="_yjg_sjcz_list_table"></table>
			</div>
		</div>
		
		<div id="_yjg_sjcz_list_add_window" class="easyui-window" title="新增" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_yjg_sjcz_list_add_form" >
        		<sec:csrfInput/>
    			<input type="hidden" id="_yjg_sjcz_add_imageTime" value=0>
    			<input type="hidden" name="imageIds" id ="_yjg_sjcz_add_imageIds"/> 
    			<input type="hidden" name="vedio" id ="_yjg_sjcz_add_vedio"/> 
        		<input type="hidden" id="_yjg_sjcz_sjdjid" name="sjdjid">
	        	<div style="padding: 2px;">
	        		<input name="czrxm" id="_yjg_sjcz_list_add_czrxm" label="处置人姓名：" prompt="请输入处置人姓名", data-options="labelWidth:90,labelAlign:'right',validType:'length[1,32]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
 	        	<div style="padding: 2px;">
	        		<input name="czsjStr" id="_yjg_sjcz_list_add_czsj" label="处置时间：" prompt="请输入处置时间", data-options="labelWidth:90,labelAlign:'right',editable:false,required:true" style="width:400px;height: 34px;" class="easyui-datetimebox" >
	        	</div> 
	        	<div style="padding: 2px;">
	        		<span style="width:85px;display: inline-block;float: left;padding-right: 5px;text-align:right">处置进度：</span>
		        	<select id="_yjg_sjcz_list_add_czzt" name="czzt" class = "easyui-combobox" style="width: 100px"></select>
				</div>
				<div style="padding: 2px;">
	        		<input name="czgcms" id="_yjg_sjcz_list_add_czgcms" label="过程描述：" prompt="请输入过程描述", data-options="multiline:true,labelWidth:90,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
	        		<input name="bz" id="_yjg_sjcz_list_add_bz" label="处置备注：" prompt="请输入处置备注", data-options="multiline:true,labelWidth:90,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
	        </form>
        	<div class="apply_footer_img">
				 <span class="apply_footer_select">
				 <span style="width:85px;display: inline-block;float: left;padding-right: 5px;text-align:right">上报图片：</span>
					 <form id="_yjg_sjcz_add_img_form" enctype="multipart/form-data" method="POST">
						 <span class="apply_footer_upload flex">
				    		 <span class="bg flex_item">
		                    	 <img width="150" height="180" style="display: block; width: 100%; height: 100%;">
								 <span class="bg" onclick="$(this).next().click()"></span>
								 <input type="file" id="_yjg_sjcz_add_imageFile" name="imageFile" class="file" onchange="YjgYjsjList.insertImg(this)">
	                		 </span>
	            		 </span>
            		 </form>
	            	 <script type="text/x-jsrender" id="_sjcz_sjdj_img">
							<span class="apply_footer_upload_new flex">
				    			<span class="bg flex_item">
	                   	 			<img onclick="YjgYjsjList.deleteImg(this,'{{:fileid}}')"  src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
								</span>
							</span>
						</script>
				 </span>
			</div>
			<div class="apply_footer_img">
				<form id="_sjcz_choose_file_form" enctype="multipart/form-data" method="POST">
					<input name="operatype" type="hidden" value="YJG_VEDIO_FILE">
					<span style="width:85px;display: inline-block;float: left;padding-right: 5px;text-align:right">上报视频：</span>
					<input id="_sjcz_choose_file" name="vediofile" style="width:265px;height: 34px;">
				</form>
			</div>
	        <div style="padding: 2px;text-align: center;">
	        	<a id="_yjg_sjcz_list_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
	        	<a id="_yjg_sjcz_list_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
	        </div>
	    </div>
	    
	    <div id="_yjg_sjcz_list_view_window" class="easyui-window" title="查看" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<div style="padding: 2px;">
				<form action="" method="post" id="_yjg_sjcz_list_view_form" >
					<table width="100%" class="table-view">
						<tr>
							<th>处置人姓名：</th>
							<td>
								<input name="czrxm" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_yjg_sjcz_list_view_czrxm"/>
							</td>
						</tr>
						<tr>
							<th>处置时间：</th>
							<td colspan="3">
								<input name="czsj" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_yjg_sjcz_list_view_czsj"/>
							</td>
						</tr>
						<tr>
							<th>过程描述：</th>
							<td colspan="3">
								<input name="czgcms" type="text" class="easyui-textbox" style="width:80%;height: 100px" data-options="multiline:true,required:false" id="_yjg_sjcz_list_view_czgcms"/>
							</td>
						</tr>
						<tr>
							<th>处置备注：</th>
							<td colspan="3">
								<input name="bz" type="text" class="easyui-textbox" style="width:80%;height: 100px" data-options="multiline:true,required:false" id="_yjg_sjcz_list_view_bz"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="padding: 2px;text-align: center">
				<a href="javascript:void(0);" id="_yjg_sjcz_list_view_file_button" class="easyui-linkbutton" >查看附件</a>
				<a href="javascript:void(0);" id="_yjg_sjcz_list_view_cancle_button" class="easyui-linkbutton l-btn-warm">取消</a>
			</div>
		</div>
		
		<div id="_yjg_yjsj_list_view_window" class="easyui-window" title="查看" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_yjg_yjsj_list_view_form" >
				<table width="100%" class="table-view">
					<tr>
						<th>接件时间：</th>
						<td>
							<input name="czsj" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_yjg_yjsj_list_view_czsj"/>
						</td>
					</tr>
					<tr>
						<th>接件备注：</th>
						<td colspan="3">
							<input name="bz" type="text" class="easyui-textbox" style="width:80%;height: 100px" data-options="required:false" id="_yjg_yjsj_list_view_bz"/>
						</td>
					</tr>
					<tr>
						<th>未达标建议：</th>
						<td colspan="3">
							<input name="wdbbz" type="text" class="easyui-textbox" style="width:80%;height: 100px" data-options="required:false" id="_yjg_yjsj_list_view_wdbbz"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="_yjg_yjsj_list_designate_window" class="easyui-window" title="派单用户" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height: 500px">
			<div class="easyui-layout" data-options="fit:true">
				<div region="center">
					<table id="_list_designate_user" style="width:auto;"></table>
				</div>
			</div>
		</div>
	</body>
</html>