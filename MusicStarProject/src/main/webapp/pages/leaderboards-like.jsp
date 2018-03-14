<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>創作排行</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/leaderboards-like.css" rel="stylesheet" />
<style type="text/css">
</style>
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/leaderboards-like.js"></script>
<script>
</script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
	
	<div class="leaderboards-like-title">創作者排行榜</div>

		<div id="leaderboards-like-table" class="container">
			<table class="table">
			  <thead class="text-light bg-dark">
			    <tr>
			      <th scope="col">排行</th>
			      <th scope="col">作者</th>
			      <th scope="col">Cover</th>
			      <th scope="col">歌曲</th>
			      <th scope="col">歌名</th>
			      <th scope="col">喜歡請點</th>
			    </tr>
			  </thead>
			  <tbody class="likemusic-tbody">
			<!--     <tr> -->
			<!--       <td>放排行</td> -->
			<!--       <td>放作者</td> -->
			<!--       <td>放圖</td> -->
			<!--       <td>放歌曲</td> -->
			<!--       <td>放歌名</td> -->
			<!--       <td>放歡請點</td> -->
			<!--     </tr> -->
			  </tbody>
			</table>
			
			<div class="leaderboard-like-back">	
				<a href="${ctx}/index.jsp" class="btn btn-outline-danger" role="button" aria-pressed="true">回首頁</a>
			</div>
			
		</div><!-- leaderboards-like-table -->
		
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>