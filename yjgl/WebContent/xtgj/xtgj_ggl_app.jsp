<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta content="telephone=no, address=no" name="format-detection">
		<meta
			content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
			name="viewport">  
		<title>厦门市政窨井管理信息系统</title>
		 </head>
	
	<body style="width: 95%;" >
	    <div style="text-align: center;font-size: 25px;font-weight:bold;">${gl.gglbt }</div>
	    <div style="text-align: right;"><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${gl.czsj }" /></div>
	    <div >${gl.gglnr }</div>
	</body>
</html>