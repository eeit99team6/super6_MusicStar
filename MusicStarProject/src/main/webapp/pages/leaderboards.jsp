<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>賽事排行</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/leaderboards.css" rel="stylesheet" />
<style type="text/css">
</style>
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/leaderboards.js"></script>
<script>
</script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
	
		<div class="title-title">經典賽事排行榜</div>
			
		<div id="leaderboard-outer" class="container">
<!-- 			<div id='leaderboard-max1' class="container border border-danger"> -->
<!-- 				<h2 class="leaderboard-title1"><i class="fa fa-play" style="font-size:48px;color:red"></i></h2> -->
<!-- 				<ol class="leaderboard-ol"> -->
<!-- 					<li> -->
<!-- 						<div><img src="圖:"></div> -->
<!-- 						<div><a href="連結:"><i class="fa fa-play-circle" style="font-size:36px;color:red"></i></a></div> -->
<!-- 						<h4>歌名:</h4> -->
<!-- 						<h5>歌手:</h5> -->
<!-- 					</li>	 -->
<!-- 				</ol> -->
<%-- 				<a href="${ctx}/leaderboards-1.jsp?music_contest_id=1" class="btn btn-outline-danger" role="button" aria-pressed="true">看本榜Top10</a> --%>
<!-- 			</div> -->
<!-- 		</div>leaderboard-outer -->
		<p>${errors.action}</p>	
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>