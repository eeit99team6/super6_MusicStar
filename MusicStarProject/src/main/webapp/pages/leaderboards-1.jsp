<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>賽事排行TOP10</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/leaderboards-1.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" >
<style type="text/css">
</style>
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/leaderboards-1.js"></script>
<script>
</script>
</head>
<body>
<div class="wrapper scrollbar-dynamic">
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">

		<div id="leaderboard-1-left">
			<div class="leaderboard-1-left-title">經典賽事</div>
		<!-- 	<div class="left-div">連結</div> -->
		</div>
			
		<div id="leaderboard-1-right">
			<h2 class="leaderboard-title"></h2>	
			<div class="leaderboard-1-table1">
			<table>		
				<thead>
					<tr>
						<th>排名</th>		
					</tr>	
				</thead>
				<tbody class="leaderboards-1-tbody-1">						
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_1</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_2</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_3</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_4</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_5</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_6</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_7</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_8</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_9</i></td></tr>
					<tr><td class="ch"><i class="material-icons" style="font-size:28px;color:red">filter_9_plus</i></td></tr>	
				</tbody>	
			</table>
			</div>
			<div class="leaderboard-1-table2">
			<table>		
				<thead>
					<tr>	
						<th>圖</th>
						<th>歌曲</th>
						<th>歌曲名稱</th>
						<th>作者</th>
						<th>敘述</th>
						<th>得票數</th>
					</tr>	
				</thead>
				<tbody class="leaderboards-1-tbody-2">
					<tr>
		<!-- 				<td>放圖</td> -->
		<!-- 				<td>放歌曲</td> -->
		<!-- 				<td>放歌曲名稱</td> -->
		<!-- 				<td>放作者</td> -->
		<!-- 				<td>放敘述</td> -->
		<!-- 				<td>放得票數</td> -->
					</tr>	
			</table>
			</div>
			<div class="leaderboard-1-back">	
				<a href="${ctx}/pages/leaderboards.jsp" class="btn btn-outline-danger" role="button" aria-pressed="true">回排行榜</a>
			</div>
		</div><!-- leaderboard-1-right -->

		<p>${errors.action}</p>	
	<input type="hidden" id="xxxId" value="${param.music_contest_id}">
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</div>
</body>
</html>