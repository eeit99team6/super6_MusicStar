<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票中的賽事</title>
<jsp:include page="/includes/main_css.jsp" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css"
	rel="stylesheet" />
<style type="text/css">
#contests_table * {
	text-align: center;
}
</style>
<jsp:include page="/includes/main_js.jsp" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-TW.min.js"></script>
<script>
	function formatImg(value, row, index){
		return "<img src='"+value+"' height='100px'/>";
	}
	function formatLink(value, row, index){
		return "<a href='"+ctx+"/pages/contests/contest_voting_players.jsp?contestId="+value+"'>前往投票</a>";
	}
</script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
		<h1 class="default_title display-3">投票中的賽事</h1>
		<table id="contests_table" data-smart-display="true" data-sortable="true" data-pagination="true" data-search="true" data-toggle="table" data-url="<c:url value="/contests/voting"/>">		
			<thead>
				<tr>
					<th data-field="music_contest_style_id">賽事類型</th>
					<th data-field="music_contest_name">賽事名稱</th>
					<th data-field="music_contest_photo" data-formatter="formatImg">賽事圖片</th>
					<th data-field="music_contest_description">賽事簡介</th>
					<th data-field="music_contest_status">賽事狀態</th>
					<th data-field="music_contest_id" data-formatter="formatLink">投票頁面</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<c:url value="" />
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>