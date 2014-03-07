<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Blank Page</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<c:set var="ctx" value="<%=request.getContextPath()%>" scope="request" />
	<c:set var="rs" value="${ctx}/static" scope="request" />
	<link href="${rs}/css/bootstrap.css" rel="stylesheet">
	<link href="${rs}/css/social.css" rel="stylesheet">
	<link href="${rs}/css/font-awesome.css" rel="stylesheet">
	<link href="${rs}/css/social.theme-blue.css" rel="stylesheet">
	<link href="${rs}/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
	<tiles:insertAttribute name="global" />
	<script src="${rs}/js/jquery.js"></script>
	<script src="${rs}/js/bootstrap.js"></script>
	<c:if test="${not empty js}">
		<c:forEach items="${fn:split(js,'|')}" var="_js">
			<script src="${rs}/js/${_js}.js"></script>
		</c:forEach>
	</c:if>
	<c:if test="${not empty css}">
		<c:forEach items="${fn:split(css,'|')}" var="_css">
			<link href="${rs}/css/${_css}.css" rel="stylesheet">
		</c:forEach>
	</c:if>
</body>
</html>