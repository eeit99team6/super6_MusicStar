<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>歌單</title>
<jsp:include page="/includes/main_css.jsp" />
<link rel="stylesheet" type="text/css" href="" />

<jsp:include page="/includes/main_js.jsp" />
<script type="text/javascript">
function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}
}
</script>
</head>
<body>
<jsp:include page="/includes/main_header.jsp" />
<br>
<br><br>
<br>
<h3>歌單編輯</h3>


<form action="<c:url value="/pages/product.controller" />" method="get">
<table>
	<tr>
		<td>歌單ID : </td>
		<td><input type="text" name="member_music_list_id" value="${param.member_music_list_id}"></td>
		<td>${errors.xxx1}</td>
	</tr>
	<tr>
		<td>歌單Name : </td>
		<td><input type="text" name="member_music_list_name" value="${param.member_music_list_name}"></td>
		<td></td>
	</tr>

	<tr>
		<td>歌單會員 : </td>
		<td><input type="text" name="member_music_list_member_id" value="${param.member_music_list_member_id}"></td>
		<td>${errors.xxx2}</td>
	</tr>
	<tr>
		<td>歌單描述: </td>
		<td><input type="text" name="member_music_list_description" value="${param.member_music_list_description}"></td>
		<td>${errors.xxx3}</td>
	</tr>
	<tr>
		<td>歌曲數量: </td>
		<td><input type="text" name="member_music_list_quantity" value="${param.member_music_list_quantity}"></td>
		<td>${errors.xxx4}</td>
	</tr>
	<tr>
	   <td><button id="buttonAdd" type="button" name="prodaction" class="btn btn-primary"><i class="fas fa-plus"></i></button></td>
	
		<td>
			<input type="submit" name="prodaction" value="Insert">
			<input type="submit" name="prodaction" value="Update">
		</td>
		<td>
			<input type="submit" name="prodaction" value="Delete">
			<input type="submit" name="prodaction" value="Select">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
</table>

</form>


<h3><span class="error">${errors.action}</span></h3>

<c:if test="${not empty delete}">
<h3>Delete Product Table Success : ${delete} row deleted</h3>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty insert}">
<h3>Insert Product Table Success</h3>
<table border="1">
	<tr><td>歌單Name</td><td>${insert.member_music_list_name}</td></tr>
	<tr><td>歌單會員</td><td>${insert.member_music_list_member_id}</td></tr>
	<tr><td>歌單描述</td><td>${insert.member_music_list_description}</td></tr>
	<tr><td>歌曲數量</td><td>${insert.member_music_list_quantity}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>

<c:if test="${not empty update}">
<h3>Update Product Table Success</h3>
<table border="1">
	<tr><td>歌單Name</td><td>${update.member_music_list_name}</td></tr>
	<tr><td>歌單會員</td><td>${update.member_music_list_member_id}</td></tr>
	<tr><td>歌單描述</td><td>${update.member_music_list_description}</td></tr>
	<tr><td>歌曲數量</td><td>${update.member_music_list_quantity}</td></tr>
</table>
<script type="text/javascript">clearForm();</script>
</c:if>
<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>