<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib prefix="fns" uri="../tld/fns.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>${titleName}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sec:csrfMetaTags/>
		<script src="https://api.html5media.info/1.1.8/html5media.min.js"></script>
		<script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.min.js"></script>
		<script src="https://api.html5media.info/1.1.8/html5media.min.js"></script>
		<script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.min.js"></script>
	</head>
	<body>
		<div class="easyui-layout" style="overflow:auto" data-options="fit:true">
			<div region="north" style="padding: 5px;overflow:auto;height: 460px">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2" >
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300">图片附件</legend>
				</fieldset>
				<c:choose>
					<c:when  test="${not empty imgList}">
					    	<c:forEach var="item" items="${imgList}">
					       		<img src=${item.filepath} width="28%" height="400px" style="margin-right: 10px;float: left;display: block;background-size:cover"/>
					    	</c:forEach>
			    	</c:when >
			    	<c:otherwise>暂无图片</c:otherwise>
			    </c:choose>
	    	</div>
    		<div region="center">
    			<fieldset style="border: none;border-top: 1px solid #e2e2e2" >
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300">视频附件</legend>
				</fieldset>
				<c:choose>
		    		<c:when test="${not empty videoEntity}">
		    			<video id="cc-video-player" src="${videoEntity.filepath}" controls width="600px" heigt="400px"></video> 
					</c:when>
					<c:otherwise>暂无视频</c:otherwise>
				</c:choose>
			</div>
    	</div>
	</body>
</html>