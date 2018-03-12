<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>歌單總覽</title>
<jsp:include page="/includes/main_css.jsp" />
<jsp:include page="/includes/main_js.jsp" />

</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />

<br>
<br>
<c:if test="${not empty select}">
<table>
	<thead>
	<tr>
		<th>歌單ID</th>
		<th>歌單Name</th>
		<th>歌單描述</th>
		<th>歌單會員</th>
		<th>歌曲數量</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="row" items="${select}">
		<c:url value="/pages/product.jsp" var="path">
			<c:param name="member_music_list_id" value="${row.member_music_list_id}" />
			<c:param name="member_music_list_name" value="${row.member_music_list_name}" />
			<c:param name="member_music_list_description" value="${row.member_music_list_description}" />
			<c:param name="member_music_list_member_id" value="${row.member_music_list_member_id}" />
			<c:param name="member_music_list_quantity" value="${row.member_music_list_quantity}" />
		</c:url>
	<tr>
		<td>${row.member_music_list_id}</td>
		<td><a href="${path}">${row.member_music_list_name}</a></td>
		<td>${row.member_music_list_description}</td>
		<td>${row.member_music_list_member_id}</td>
		<td>${row.member_music_list_quantity}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>

<h3><a href="<c:url value="/pages/product.jsp" />">Product Table</a></h3>
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>