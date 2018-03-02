<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test_fileupload</title>
<meta charset="utf-8" />
<jsp:include page="/includes/main_css.jsp" />
<jsp:include page="/includes/main_js.jsp" />
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
		<fieldset id="test_box">
		<form  action="<c:url value="/FileUpload"/>" method="post" enctype="multipart/form-data">
			<div class="row">
				<input id="test_text" name="audioFile" type="file" multiple="multiple">
			</div>
			<div class="row">
				<input type="submit" value="送出">
			</div>
		</form>
		</fieldset>
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>