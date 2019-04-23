<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib prefix="fns" uri="../tld/fns.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sec:csrfMetaTags/>
	</head>
	<body>
		<script type="text/javascript" src="../yjg/yjg_sjgl_fyj_edit.js" charset="UTF-8"></script>
		<form action="" method="post" id="_yjg_fyjSjdj_list_edit_form" >
       		<sec:csrfInput/>
	        <input name="fyjsjdjid" type="hidden" value="${yjgFyjsjdjEntity.fyjsjdjid }">
	        <input name="xzb" id="_yjg_fyjSjdj_list_edit_xzb" value="${yjgFyjsjdjEntity.xzb }" type="hidden">
	        <input name="yzb" id="_yjg_fyjSjdj_list_edit_yzb" value="${yjgFyjsjdjEntity.yzb }" type="hidden">
	        <div style="padding: 2px;">
        		<input name="sjdjdh" value="${yjgFyjsjdjEntity.sjdjdh }" id="_yjg_fyjSjdj_list_edit_sjdjdh" label="登记单号：" readonly="readonly" data-options="labelWidth:100,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
        	</div>
	        <div style="padding: 2px;">
        		<input name="sbrxm" value="${yjgFyjsjdjEntity.sbrxm }" id="_yjg_fyjSjdj_list_edit_sbrxm" label="上报人姓名：" prompt="请输入上报人姓名", data-options="labelWidth:100,labelAlign:'right',validType:'length[1,20]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
        	</div>
        	<div style="padding: 2px;">
        		<input name="sbrdh" value="${yjgFyjsjdjEntity.sbrdh }" id="_yjg_fyjSjdj_list_edit_sbrdh" label="上报人电话：" prompt="请输入上报电话", data-options="labelWidth:100,labelAlign:'right',validType:'length[1,20]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
        	</div>
        	<div style="padding: 2px;">
        		<input value="${yjgFyjsjdjEntity.sjlx }" id="_yjg_fyjSjdj_list_edit_sjlx" label="事件性质：" prompt="请输入事件类型", data-options="labelWidth:100,labelAlign:'right',validType:'length[1,22]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
        		<input name="sjlx" id="sjlxVal" type="hidden" value="${yjgFyjsjdjEntity.sjlx }">
        	</div>
	        <div style="padding: 2px;">
        		<input value="${yjgFyjsjdjEntity.xxly }" id="_yjg_fyjSjdj_list_edit_xxly" label="信息来源：" prompt="请输入信息来源", data-options="labelWidth:100,labelAlign:'right',validType:'length[1,22]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
       			<input name="xxly" id="xxlyVal" type="hidden" value="${yjgFyjsjdjEntity.xxly }">
        	</div>
        	<div style="padding: 2px;">
       			<select class="easyui-combogrid" id="_yjg_fyjSjdj_list_edit_ckqx" name="ckqx" label="查看权限：" prompt="非私密件此项不填" data-options="labelAlign:'right',labelWidth:100,multiple:true,multiline:true" style="width:400px;height:50px"></select>
				<input id="_yjg_fyjSjdj_list_edit_ckqx_res" type="hidden" value="${ckqx }">   
       		</div>
        	<div style="padding: 2px;">
        	    <a id="_yjg_fyjSjdj_list_edit_wz" style="width:95px;float: left;padding-right: 5px;text-align:right">所在位置：</a>
        		<a id="_yjg_fyjSjdj_list_edit_wz_button"  href="javascript:void(0);" data-options=""  class="easyui-linkbutton" >选择</a>
        	</div>
        	<div style="padding: 2px;">
        		<input readonly="readonly" value="${yjgFyjsjdjEntity.xzqh }" id="_yjg_fyjSjdj_list_edit_input_xzqh" label="行政区划：" prompt="请输入行政区划", data-options="labelWidth:100,labelAlign:'right',validType:'length[1,22]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
       			<input name="xzqh" id="_yjg_fyjSjdj_list_edit_xzqh" type="hidden" value="${yjgFyjsjdjEntity.xzqh }">
        	</div>
         	<div style="padding: 2px;">
        		<input name="wzms" value="${yjgFyjsjdjEntity.wzms }" id="_yjg_fyjSjdj_list_edit_wzms" label="位置描述：" prompt="请输入位置描述", data-options="multiline:true,labelWidth:100,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
        	</div> 
	        <div style="padding: 2px;">
        		<input name="bz" value="${yjgFyjsjdjEntity.bz }" id="_yjg_fyjSjdj_list_edit_bz" label="基本情况：" prompt="请输入基本情况", data-options="multiline:true,labelWidth:100,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
        	</div>
	    </form>
	    <div class="apply_footer_img">
			<span class="apply_footer_select">
			<span style="width:95px;display: inline-block;float: left;padding-right: 5px;text-align:right">上报图片：</span>
				<form id="_yjg_fyjSjdj_edit_img_form" enctype="multipart/form-data" method="POST" action="../TblBaseFileAction/addImg?${_csrf.parameterName}=${_csrf.token}">
					<span class="apply_footer_upload flex">
			    		<span class="bg flex_item">
	                    	<img id="preview" width="150" height="180" style="display: block; width: 100%; height: 100%;">
							<input name="operatype" type="hidden" value="YJG_IMG_FILE">
							<span class="bg" onclick="$(this).next().click()"></span>
							<input type="file" id="_yjg_fyjSjdj_edit_imageFile" name="imageFile" class="file" onchange="YjgFyjSjdjEdit.insertImg(this)">
                			<input type="text" name="fyjsjdjid" value="${yjgFyjsjdjEntity.fyjsjdjid}">
                		</span>
            		</span>
	           		<c:forEach var="item" items="${imgList}">
	       				<div class="apply_footer_upload_edit_new flex" id="_yjg_edit_fyjSjdj">
					    	<div class="bg flex_item">
		               			<img onclick="YjgFyjSjdjEdit.deleteImg(this,'${item.fileid}')" src=${item.filepath} width="70" height="70" style="display: block;background-size:cover;"/>
							</div>
						</div>
	       			</c:forEach>
           		</form>
				    	
           		<script type="text/x-jsrender" id="_yjg_edit_fyjSjdj_render">
					<span class="apply_footer_upload_edit_new flex">
						<span class="bg flex_item">
	           				<img onclick="YjgFyjSjdjEdit.deleteImg(this,'{{:fileid}}')" src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
	            		</span>
					</span>
				</script>
			</span>
		</div>
		<div class="apply_footer_img" style="height: 70px">
			<form id="_fyj_choose_file_edit_form" enctype="multipart/form-data" method="POST">
				<span style="width:95px;display: inline-block;float: left;text-align:right;padding-right: 5px">上报视频：</span>
				
				<input id="_fyj_choose_edit_file" name="vediofile" style="width:195px;height: 34px;margin-right:5px;float: left;" value="">
				<input name="operatype" type="hidden" value="YJG_VEDIO_FILE">
       			<c:if test="${not empty videoEntity}">
					<img id="_fyj_choose_edit_img" src="${videoEntity.remark }" width="70" height="70" style="display: block;background-size:cover;float: left;padding-right: 5px;">
       				<input name="operaid" id="_fyj_choose_edit_operaid" type="hidden" value="${videoEntity.fileid }">
				</c:if>
	       			<input name="fyjsjdjid" type="hidden" value="${yjgFyjsjdjEntity.fyjsjdjid }">
			</form>
		</div>
        <div style="padding: 2px;text-align: center;">
        	<a id="_yjg_fyjSjdj_list_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
        	<a id="_yjg_fyjSjdj_list_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
        </div>
        <div class="datagrid-toolbar" id="fyj_editUserTools" style="width:100%;height:20px;">  
           	权属单位：<input type="text"  id ="_fyj_baseroletype_sear" style="width: 150px"/>
            <a href="javascript:void(0)" onclick="BaseType.searQsRole('#_yjg_fyjSjdj_list_edit_ckqx', '#_fyj_baseroletype_sear', '#_yjg_fyjSjdj_list_edit_ckqx_res')">查询</a> 
            <a href="javascript:void(0)" onclick="$('#_fyj_baseroletype_sear').val('')">重置</a> 
    	</div> 
	</body>
</html>