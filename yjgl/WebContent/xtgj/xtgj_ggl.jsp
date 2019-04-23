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
		<script type="text/javascript" src="../xtgj/xtgj_ggl.js" charset="UTF-8"></script>
 
			
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">公告栏</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_yjg_ggl_search_form">
				       	<input class="easyui-textbox" id="yjg_ggl_gglnr" label="公告内容：" prompt="请输入公告内容" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;"> 
				       	<input class="easyui-textbox" id="yjg_ggl_czrxm" label="操作人：" prompt="请输入操作人" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;"> 
				        <input class="easyui-datetimebox" id="yjg_ggltimeStart" label="操作时间：" prompt="请输入开始时间" data-options="labelWidth:100,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
					       	 &nbsp;至&nbsp;
				        <input class="easyui-datetimebox" id="yjg_ggltimeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:180px;height: 34px;">
				       
				        <a href="#" class="easyui-linkbutton" id="_yjg_ggl_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_yjg_ggl_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
					<a id="_yjg_ggl_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">新增</a>
					<a id="_yjg_ggl_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑</a> 
					<a id="_yjg_ggl_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除</a>
				</div>
			</div>
			<div region="center">
				<table id="_yjg_ggl_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_yjg_ggl_add_window" class="easyui-window" title="新增" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;">
			<form action="" method="post" id="_yjg_ggl_add_form" >
			
        		<sec:csrfInput/>
        		<div style="padding: 2px;">
	        		<input name="gglbt" id="_xtgj_ggl_add_gglbt" label="公告栏标题：" prompt="请输入公告栏标题", data-options="labelWidth:90,labelAlign:'right',validType:'length[1,200]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
				<div style="padding: 2px;">
				公告栏内容:</br>
					<iframe id="_yjg_ggl_add_iframe" style="width: 100%; height: 300px;" frameborder="0" marginheight="0" marginwidth="0"   src=""></iframe>
	 
	            </div>
		        <div style="padding: 2px;text-align: center;">
		            <input type="hidden" id="_yjg_ggl_add_gglnr" name="gglnr"/>
		        	<a id="_yjg_ggl_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_yjg_ggl_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
	    
	    <div id="_yjg_ggl_edit_window" class="easyui-window" title="编辑" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;">
			<form action="" method="post" id="_yjg_ggl_edit_form" >
        		<sec:csrfInput/>
        		<div style="padding: 2px;">
	        		<input name="gglbt" id="_xtgj_ggl_edit_gglbt" label="公告栏标题：" prompt="请输入公告栏标题", data-options="labelWidth:90,labelAlign:'right',validType:'length[1,200]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
	        	</div>
		        <div style="padding: 2px;">
		        <!-- 
	        		<input name="gglnr" id="_yjg_ggl_edit_gglnr" label="公告栏内容：" prompt="请输入公告栏内容", data-options="labelWidth:90,labelAlign:'right',validType:'length[1,200]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
		         -->
	        		公告栏内容:</br>
					<iframe id="_yjg_ggl_edit_iframe" style="width: 100%; height: 300px;" frameborder="0" marginheight="0" marginwidth="0"   src=""></iframe>
				
	        	</div>
		        <div style="padding: 2px;text-align: center;">
		            <input type="hidden" id="_edit_gglid" name="gglid"/>
		            <input type="hidden" id="_yjg_ggl_edit_gglnr" name="gglnr"/>
		        	<a id="_yjg_ggl_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_yjg_ggl_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
		
		
	</body>
</html>