<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contest History Page</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
</style>
<jsp:include page="/includes/main_js.jsp" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-TW.min.js"></script>
<script>
	var musicStyleMap;
	$.getJSON(ctx + "/music/styleMap",function(data){
		musicStyleMap = data;
	})	
	function formatStyle(value, row, index){
		return "<h5>"+musicStyleMap[value]+"</h5>";
	}
	function formatImg(value, row, index){
		return "<img src='"+value+"' height='100px' width='180px'/>";
	}
	function formatLink(value, row, index){
		return "<a href='"+ctx+"/pages/contests/contest_voting_players.jsp?contestId="+value+"'>前往查看</a>";
	}
</script>
</head>
<body>
	<div class="wrapper scrollbar-dynamic">
		<jsp:include page="/includes/main_header.jsp" />
		<!-- main_container start -->
		<div id="main_container" class="container-fuild">
		 <div class="title-title"> 歷史賽事 </div>
		 		<table id="contests_table" data-smart-display="true" data-sortable="true" data-pagination="true" data-search="true" data-toggle="table" data-url="<c:url value="/contests/close"/>">		
			<thead>
				<tr>
					<th data-field="music_contest_style_id" data-formatter="formatStyle">賽事類型</th>
					<th data-field="music_contest_name">賽事名稱</th>
					<th data-field="music_contest_photo" data-formatter="formatImg" data-searchable="false">賽事圖片</th>
					<th data-field="music_contest_description">賽事簡介</th>
					<th data-formatter="比賽結束">賽事狀態</th>
					<th data-field="music_contest_id" data-formatter="formatLink" data-searchable="false">投票頁面</th>
				</tr>
			</thead>
		</table>
		   
		 
		 
		 </div>
		 
		<!-- main_container end -->
		<jsp:include page="/includes/main_aside.jsp" />
		<jsp:include page="/includes/main_footer.jsp" />
	</div>
</body>
</html>