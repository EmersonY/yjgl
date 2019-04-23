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
		<script type="text/javascript" src="../yjg/yjg_yjsj_edit.js" charset="UTF-8"></script>
		<form action="" method="post" id="_yjg_sjcz_list_edit_form" >
       		<sec:csrfInput/>
       		<input name="sjczid"  value="${yjgSjczEntity.sjczid }" type="hidden">
			<div style="padding: 2px;">
        		<input name="czrxm" value="${yjgSjczEntity.czrxm }" id="_yjg_sjcz_list_edit_czrxm" label="处置人姓名：" prompt="请输入处置人姓名", data-options="labelWidth:90,labelAlign:'right',validType:'length[1,32]',required:true" style="width:400px;height: 34px;" class="easyui-textbox" >
        	</div>
	        <div style="padding: 2px;">
        		<input name="czsjStr"  value='${yjgSjczEntity.czsjStr }' id="_yjg_sjcz_list_edit_czsj" label="处置时间：" prompt="请输入处置时间", data-options="labelWidth:90,labelAlign:'right',editable:false,required:true" style="width:400px;height: 34px;" class="easyui-datetimebox" >
        	</div>
        	<div style="padding: 2px;">
        		<span style="width:85px;display: inline-block;float: left;padding-right: 5px;text-align:right">处置进度：</span>
        		<select id="_yjg_sjcz_list_edit_czzt" disabled="disabled" value="${yjgSjczEntity.czzt }" name="czzt" class = "easyui-combobox" data-options="editable:false,panelHeight:80" style="width: 100px">
					<option <c:if test="${yjgSjczEntity.czzt=='0'}">selected="selected"</c:if> value ="0">处置前</option>
					<option <c:if test="${yjgSjczEntity.czzt=='1'}">selected="selected"</c:if> value ="1">处置中</option>
					<option <c:if test="${yjgSjczEntity.czzt=='2'}">selected="selected"</c:if> value ="2">已完成</option>
					<option <c:if test="${yjgSjczEntity.czzt=='3'}">selected="selected"</c:if> value ="3">未达标</option>
				</select>
				<input type="hidden" value="${yjgSjczEntity.czzt}" id="_czztVal">
        	</div>
	        <div style="padding: 2px;">
        		<input name="czgcms" value="${yjgSjczEntity.czgcms }" id="_yjg_sjcz_list_edit_czgcms" label="过程描述：" prompt="请输入过程描述", data-options="multiline:true,labelWidth:90,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
        	</div>
	        <div style="padding: 2px;">
        		<input name="bz" value="${yjgSjczEntity.bz }" label="处置备注：" prompt="请输入处置备注", data-options="multiline:true,labelWidth:90,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
        	</div>
        </form>
       	<div class="apply_footer_img">
		<span class="apply_footer_select">
		<span style="width:85px; display: inline-block; float: left;padding-right: 5px;text-align:right">上报图片：</span>
			<form id="_yjg_sjcz_edit_img_form" enctype="multipart/form-data" method="POST" action="../TblBaseFileAction/addImg?${_csrf.parameterName}=${_csrf.token}">
				<span class="apply_footer_upload flex">
		    		<span class="bg flex_item">
                    	<img id="preview" width="150" height="180" style="display: block; width: 100%; height: 100%;">
						<input name="operatype" type="hidden" value="YJG_IMG_FILE">
						<span class="bg" onclick="$(this).next().click()"></span>
						<input type="file" id="_yjg_sjcz_edit_imageFile" name="imageFile" class="file" onchange="YjgYjsjEdit.insertImg(this)">
               			<input type="hidden" name="sjczid" value="${yjgSjczEntity.sjczid}">
               		</span>
           		</span>
           		<c:forEach var="item" items="${imgList}">
       				<div class="apply_footer_upload_sjcz_edit_new flex" flex" id="_yjg_edit_sjcz">
				    	<div class="bg flex_item">
	               			<img onclick="YjgYjsjEdit.deleteImg(this,'${item.fileid}')" src=${item.filepath} width="70" height="70" style="display: block;background-size:cover;"/>
						</div>
					</div>
       			</c:forEach>
          	</form>
			    	
         	<script type="text/x-jsrender" id="_yjg_edit_sjcz_render">
				<span class="apply_footer_upload_sjcz_edit_new flex">
					<span class="bg flex_item">
	           			<img onclick="YjgYjsjEdit.deleteImg(this,'{{:fileid}}')" src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
	         		</span>
				</span>
			</script>
		</span>
		</div>
		<div class="apply_footer_img" style="height: 70px">
			<form id="_sjcz_choose_file_edit_form" enctype="multipart/form-data" method="POST">
				<span style="width:85px;display: inline-block;float: left;text-align:right;padding-right: 5px">上报视频：</span>
				<input id="_sjcz_choose_edit_file" name="vediofile" style="width:195px;height: 34px;margin-right:5px;float: left;" value="">
      			<input name="operatype" type="hidden" value="YJG_VEDIO_FILE">
       			<c:if test="${not empty videoEntity}">
					<img id="_sjcz_choose_edit_img" src="${videoEntity.remark }" width="70" height="70" style="display: block;background-size:cover;float: left;padding-right: 5px;">
       				<input name="operaid" id="_sjcz_choose_edit_operaid" type="hidden" value="${videoEntity.fileid }">
				</c:if>
       			<input name="sjczid" type="hidden" value="${yjgSjczEntity.sjczid }">
			</form>
		</div>
        <div style="padding: 2px;text-align: center;">
        	<a id="_yjg_sjcz_list_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
        	<a id="_yjg_sjcz_list_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
        </div>
	</body>
</html>