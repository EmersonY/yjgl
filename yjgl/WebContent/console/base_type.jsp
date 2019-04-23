<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>厦门市政窨井管理信息系统</title>
		<sec:csrfMetaTags/>
	<body>
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="center" data-options="border:false">
				<div id="tt" class="easyui-tabs" data-options="fit:true">  
				    <div title="数据字典（列表）管理" data-options="href:'../console/base_type_list.jsp'">  
				    </div>
				    <div title="数据字典（树形）管理" data-options="href:'../console/base_type_tree.jsp'">
				    </div>
				</div>
			</div>
		</div>
	</body>
</html>