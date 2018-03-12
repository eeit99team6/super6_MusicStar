<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Default Page</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
.title{
margin-top:5%;
text-align: center;
font-size:300%;
font-weight:bold;
}

input{
display:block;
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
	
	 
	 <div class="title">上傳音樂</div>
	 
	<form method="POST" action="<c:url value='/musicInsert'/>" enctype="multipart/form-data">
    music_name:<input name="music_name" type="text">
    music_link:<input name="music_link" type="file">
    music_member_id<input name="music_member_id" type="text">
    music_photo<input name="music_photo" type="file">
    music_description<input name="music_description" type="text">
    music_lyrics<input name="music_lyrics" type="text">
    <select name="music_style_id" class="col-xl-12">
        <option value="1">Rock</option>
        <option value="2">Jazz</option>
        <option value="3">Blue</option>
        <option value="4">Country</option>
        <option value="5">Pop</option>
        <option value="6">Electronic</option>
        <option value="7">Flok</option>
        <option value="8">Alternative</option>
        <option value="9">Punk</option>
        <option value="10">Classical</option>
        <option value="11">Religion</option>
        <option value="12">Independent</option>
      </select>
	
	<button type="submit">Submit</button>
	
	</form>
	
	
	
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>