<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fns" uri="../tld/fns.tld" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>厦门市政窨井管理信息系统</title>
		<sec:csrfMetaTags/>
		<%@ include file="/base/common.jspf"%>
		<script type="text/javascript" src="../base/main.js" charset="UTF-8"></script>
		<link rel="stylesheet" href="../css/base.css">
		<script type="text/javascript" src="../easyui/util/baseUtil.js"></script>
		<link rel="stylesheet" href="../icons/font_qianniu/iconfont.css">
		<link rel="stylesheet" type="text/css" href="../css/waterfall.css">
		<link rel="stylesheet" type="text/css" href="../css/ywtj.css">
		<link rel="stylesheet" type="text/css" href="../easyui/themes/material/easyui.css">
		<script type="text/javascript" src="../js/easyui.common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../js/util.common.js" charset="UTF-8"></script>
  		<script type="text/javascript" src="../js/baseType.js"></script>
  		<script type="text/javascript" src="../js/store.js"></script>
  		<script type="text/javascript" src="../js/echarts/echarts-all.js"></script>
  		<script type="text/javascript" charset="UTF-8" src="../js/basepara.js"></script>
		<script type="text/javascript" charset="UTF-8" src="../js/baseType.js"></script>
 		<script type="text/javascript" charset="UTF-8" src="../webgis/dojoconfig.js"charset="utf-8"></script>
		<script type="text/javascript" charset="UTF-8" src="../js/util.common.js"></script>
		<script type="text/javascript" charset="UTF-8" src="../easyui/datagrid-detailview.js"></script>
		<script type="text/javascript" charset="UTF-8" src="http://syseng.kingtopinfo.com:9081/arcgis_js_3.14/init.js"></script>
		<link rel="stylesheet" type="text/css" href="http://syseng.kingtopinfo.com:9081/arcgis_js_3.14/esri/css/esri.css">
	</head>
	
	<body class="easyui-layout" >
  		<div id='loading' class="_main_loading"></div>
		<div data-options="region:'north'" style="height:80px;background-color: #2D3E50;">
			<div style="height:100%;width:100%;background-color:#2D3E50;overflow: hidden;">
				<img style="float:left;width="30%" height="100%" src="../images/main/title_left.png">
				<div style="float:right;width: 400px;height: 70px;background-size:100%;background-image:url(../images/main/title_right.jpg) ">
					<div style="float:right;width: 200px;height: 70px;">
						<div style="float:right;margin-top: 30px;margin-right: 10px;">
 							<a href="javascript:;" onclick="location.reload()"><img alt="主页" width="25px" height="25px" src="../images/main/home.png" title="主页"></a>				
							<a href="javascript:;" id="_usermsg"><img alt="当前用户信息" width="25px" height="25px" src="../images/main/user.png" title="查看用户信息"></a>
							<c:if test="${fns:getAdminPath('main_dt')}">
								<a target="_Blank" href="../webgis/index.jsp" id="_webgis"><img alt="地图" width="25px" height="25px" src="../images/main/map.png" title="地图"></a>						
							</c:if>
							<a href="javascript:;" id="_logon_button"><img alt="注销" width="25px" height="25px" src="../images/main/exit.png" title="注销"></a>
							<form id="_logon_form" action="../logout" method="post">
								<sec:csrfInput/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'south'" style="height:30px;background-color: #2D3E50;"></div>
		<div data-options="region:'west',split:true" title="<i style='font-size: 20px;' class='icon iconfont icon-other'></i>" style="width:150px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<c:forEach items="${list}" var="l">
					<div title="${l.e.menuname}" style="font-size: 20px;font-weight: 300;">
						<ul class="easyui-datalist nemu" data-options="fit:true,border:false">
							<c:forEach items="${l.list}" var="cl">
								<!-- <i style="font-size: 24px;" class="nemu icon iconfont ${cl.icon}"></i> -->
				        		<li value="${cl.src}">${cl.menuname}</li>
				        	</c:forEach>
				        </ul>
					</div>
				</c:forEach>
		     </div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-panel" data-options="fit:true,border:false" id="_main" >
			   <div class="masonry">
			     <c:forEach items="${gglList}" var="gl">
				     <!-- begin -->
				        <div class="item">
				        <div class="item__content">
				        
				           <table>
				              <tr>
				                <td style="text-align: right;width: 90px;"> 公告标题：</td>
				                <td>${gl.gglbt }</td>
				              </tr>
				              <tr>
				                <td style="text-align: right"> 公告时间：</td>
				                <td> <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${gl.czsj }" /> </td>
				              </tr>
				               <tr>
				                <td style="text-align: right"> 公告内容：</td>
				                <td> ${gl.gglnr }</td>
				              </tr>
				           </table>
				        		   
				         </div>
				        <!-- more items -->
		   			   </div>
		   			  <!-- end -->
			     </c:forEach>
	   			</div>
			</div>
		</div>
		<div id="_user_ext_edit" class="easyui-window" class="easyui-window" title="用户信息" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
		    <div title="基本资料">  
		    	<form action="" method="post" id="_ext_editForm">
		    		<sec:csrfInput/>
					<div style="padding: 2px;">
	        			<input id="_edit_account" name="account" class="easyui-textbox" readonly="readonly" label="账号：" prompt="请输入账号" data-options="labelWidth:80,labelAlign:'right',required:true" style="width:400px;height: 34px;">
	       	 		</div>
	       	 		<div style="padding: 2px;">
	        			<input id="_edit_username" name="username" class="easyui-textbox" label="用户名：" prompt="请输入用户名" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,100]'" style="width:400px;height: 34px;">
	       	 		</div>
	       	 		<div style="padding: 2px;">
	        			<input id="_edit_tel" name="tel" class="easyui-textbox" label="固定电话：" prompt="请输入固定电话" data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]'" style="width:400px;height: 34px;">
	       	 		</div>
					<div style="padding: 2px;">
	        			<input id="_edit_phone" name="phone" class="easyui-textbox" label="移动电话：" prompt="请输入移动电话" data-options="labelWidth:80,labelAlign:'right',validType:'length[1,11]'" style="width:400px;height: 34px;">
	       	 		</div>
	       	 		<div style="padding: 2px;">
	       	 			<select id="_edit_gender" name="gender" class="easyui-combobox" label="性别：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;">   
						    <option value="1">男</option>
						    <option value="0">女</option>
						</select>
	       	 		</div>
	       	 					        	
	       	 		<div style="padding: 2px;text-align: center;">
			        	<a id="_edit_ext_save" href="#" class="easyui-linkbutton">保存</a> 
	       	 			<a id="_edit_pwd_button" href="#" class="easyui-linkbutton l-btn-danger">修改密码</a>
	        			<a id="_edit_cancel" href="#" onclick="$('#_user_ext_edit').window('close')" class="easyui-linkbutton l-btn-warm">取消</a> 
	        		</div>
				</form>
			</div>
		</div>
 		<div id="_user_pwd_edit" class="easyui-window" class="easyui-window" title="修改密码" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form id="_password_upate" action="" method="post">
				<sec:csrfInput/>
				<div style="padding: 2px;">
       				<input type="password" id="_new_password" name="password" class="easyui-textbox" label="新密码：" prompt="请输入新密码" data-options="labelWidth:80,labelAlign:'right',required:true,validType:'length[1,32]'" style="width:400px;height: 34px;">
      	 		</div>
      	 		<div style="padding: 2px;">
       				<input type="password" id="_sure_password" class="easyui-textbox" label="确认密码：" prompt="请确认密码" data-options="labelWidth:80,labelAlign:'right',required:true" style="width:400px;height: 34px;">
      	 		</div>
      	 		<div style="padding: 2px;text-align: center;">
       	 			<input type="hidden" id="_user_id" name="baseuserid">
       	 			<a id="_edit_password_save" href="#" class="easyui-linkbutton">保存</a>
		        	<a id="_edit_password_cancel" href="#" onclick="$('#_user_pwd_edit').window('close')" class="easyui-linkbutton l-btn-warm">取消</a> 
       			</div>
			</form>
		</div>
		<div id="_sjwh_list_look_img_window" class="easyui-window" data-options="width:900,height:900,draggable:true,shadow:true,modal:true,closed:true,closable:true,minimizable:false,maximizable:false,collapsible:false">
	    	<img id="_sjwh_list_look_img" width="880px" height="880px"/>
	    </div>
	</body>
</html>