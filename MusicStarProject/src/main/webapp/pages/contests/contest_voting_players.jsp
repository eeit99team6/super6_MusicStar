<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>進行投票</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
</style>
<link href="${ctx}/assets/css/voting.css" rel="stylesheet" />
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/voting.js"></script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container">
		<h1 class="default_title display-3">賽事投票中</h1>
		<div id="display_area" class="row"></div>
		<input type="hidden" id="contestId" value="${param.contestId}">
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>