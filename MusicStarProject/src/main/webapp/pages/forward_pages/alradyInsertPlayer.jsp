<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AlreadyInsertedPlayer</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
.title{
margin-top:5%;
margin-bottom:30px;
text-align: center;
font-size:300%;
font-weight:bold;
}

#alerInfor{
text-align:center;
}

</style>
<jsp:include page="/includes/main_js.jsp" />
<script>
	
</script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
		 <div class="title"><div class="fab fa-angellist" > MusicStar 敬告</div></div>
	       <div id="alerInfor"><h1>${alertInsert}</h1></div></div>
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>