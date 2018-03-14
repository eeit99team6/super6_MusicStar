<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<br>
	<br>
	<h3>我的歌單</h3>
	<c:if test="${not empty select}">
		<table id="like_table">
			<thead>
				<tr>
					<th>歌單ID</th>
					<th>歌單Name</th>
					<th>歌單描述</th>
					<th>歌單會員</th>
					<th>歌曲數量</th>
					<th>按讚</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${select}">
					<c:url value="/pages/product.jsp" var="path">
						<c:param name="member_music_list_id"
							value="${row.member_music_list_id}" />
						<c:param name="member_music_list_name"
							value="${row.member_music_list_name}" />
						<c:param name="member_music_list_description"
							value="${row.member_music_list_description}" />
						<c:param name="member_music_list_member_id"
							value="${row.member_music_list_member_id}" />
						<c:param name="member_music_list_quantity"
							value="${row.member_music_list_quantity}" />
					</c:url>
					<tr>
						<td>${row.member_music_list_id}</td>
						<td><a href="${path}">${row.member_music_list_name}</a></td>
						<td>${row.member_music_list_description}</td>
						<td>${row.member_music_list_member_id}</td>
						<td>${row.member_music_list_quantity}</td>
						<td><button name="like" value="Insert" onclick="ShowValue()") class="btn btn-danger like">
							<i class="fas fa-trash-alt"></i>
							</button>
							<input type="hidden" name="member_music_list_quantity" value="${row.member_music_list_quantity}"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<h3>
		<a href="<c:url value="/pages/product.jsp" />">Product Table</a>
	</h3>
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
	<script language="javascript">
function ShowValue(){
// var v=document.getElementsByName("member_music_list_quantity").value;
// console.log(document.getElementsByName("member_music_list_quantity"));

// alert(v);
}

$("#like_table").on("click",".like",function(){
	alert($(this).parents("tr").find("td").eq(0).text());	
	});
</script>
</body>
</html>