<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<head><meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/></head>  
		<title>厦门市政窨井管理信息系统</title>
		<%@ include file="/base/common.jspf"%>
		<link rel="shortcut icon" href="../images/logo/logo.png" type="image/x-icon" /> 
		<link rel="stylesheet" type="text/css" href="../easyui/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="../css/button.css">
		<script type="text/javascript" src="../js/jQuery.md5.js"  charset="UTF-8"></script>
		<script type="text/javascript" src="../js/util.common.js"  charset="UTF-8"></script>
		<script type="text/javascript" src="../js/easyui.common.js"  charset="UTF-8"></script>
		<script type="text/javascript" src="../js/banbk.js"  charset="UTF-8"></script>
		<script type="text/javascript" src="../base/login.js"  charset="UTF-8"></script>
		<style type="text/css">
			body,html { 
				margin:0px;
				padding:0px;
			}
			input{
				background-color:transparent;
			}
			
 			input:-webkit-autofill{
				-webkit-box-shadow: 0 0 0px 1000px rgb(255,255,255) inset;
			} 
			input::-webkit-input-placeholder {
	        	font-size: 15px;
	        	padding-left:14px;
			}
		</style>
	</head>
	<body>
		<div style="height: 597px;position:relative;" id="_width_div">
			<form id="_login" action="../_check" method="post" class="_login">
			<sec:csrfInput/>
				<img style="position:absolute; z-index:1;" height="597" src="../images/login/main.jpg">
				<img style="position:absolute; top:460px;  left:130px;  z-index:2;" height="56" width=653 src="../images/login/inputgroup.png">
				<input type="text" id="_username" placeholder="请输入账号：" data-options="required:true" name="un" style="border:1px solid #ccc; background-color:rgba(159, 84, 206, 0.01);height:40px;width:248px;left:153px;top:465px;position:absolute;z-index:2;"/>				
				<input type="password" id="_password" placeholder="请输入密码：" data-options="required:true" name="pw" style="border:1px solid #ccc; background-color:rgba(159, 84, 206, 0.01);height:40px;width:248px;left:422px;top:465px;position:absolute;z-index:2;"/>				
				<input id="_submit" value="" type="button" style="border-width:0px; background-image: url('../images/login/login-botton.png');height:56px;width: 131px;cursor:pointer;position:absolute;top:460px;left:795px;z-index:2;"/>
				<input id="_rememberpassword" type="checkbox" style="vertical-align:middle;font-size: 13px;position:absolute;z-index:2;top:478px;left:683px;"/>  
				<label for="_rememberpassword" class="_text" style="font-size: 15px;position:absolute;z-index:2;top:476px;left:704px;color:#7b7474;">记住密码</label>
				<div id="check_empty" style="display: none;">
					<span style="color:red;font-size: 15px;position:absolute;z-index:2;top:525px;left:153px;">账号或密码不能为空！</span>
				</div>
				<div id="check_zp" style="display: none;">
					<span style="color:red;font-size: 15px;position:absolute;z-index:2;top:525px;left:153px;">账号或密码错误！</span>
				</div>
				<div id="check_active" style="display: none;">
					<span style="color:red;font-size: 15px;position:absolute;z-index:2;top:525px;left:153px;" id="_active_tip"></span>
				</div>
 				<img style="position:absolute; top:300px; left:950px; z-index:1;" src="../images/login/code.png" />
				<img style="position:absolute; top:380px; left:950px; z-index:20;"  src="../images/login/word.png" /> 
			</form>
		</div>
	</body>
</html>