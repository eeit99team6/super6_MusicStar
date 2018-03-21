<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘記密碼</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
.box {
	margin: 80px auto;
	width: 500px;
}
</style>
<jsp:include page="/includes/main_js.jsp" />
<script>
$(function() {
	// Register Ajax
	var $member_forget_form = $("#member_forget_form"),
	$ajax_mask = $("#ajax_mask"),
	$pwd_forget_id = $("#pwd_forget_id"),
	$pwd_forget_email = $("#pwd_forget_email"),
	$id_error = $("#id_error"),
	$email_error = $("#email_error");
	
	$member_forget_form.submit(function(e) {
		e.preventDefault();
		var formData = new FormData($member_forget_form[0])
		, action = $member_forget_form.attr("action");

		$.ajax({
			type : 'POST',
			url : action,
			data : formData,
			contentType : false,
			cache : false,
			processData : false,
			success : function(data) {
				$ajax_mask.addClass("ajax_hide");
				setTimeout(function(){if (data.success) {
					alert(data.success);
					location.href = ctx + "/index.jsp";
				} else {
					$id_error.html(data.idError || "");
					$email_error.html(data.emailError || "");				
				}},100);			
			},
			beforeSend : function(){$ajax_mask.removeClass("ajax_hide");}
		});		
	});
	
});
</script>
</head>
<body>
	<div class="wrapper scrollbar-dynamic">
		<jsp:include page="/includes/main_header.jsp" />
		<!-- main_container start -->
		<div id="main_container" class="container-fuild">
			<div class="box card">
				<form id="member_forget_form"
					action="<c:url value="/members/forgetPwdAjax"/>" method="post">
					<div class="card-header text-center">
						<h1>忘記密碼</h1>
						<h3>請提供帳號與電子郵件進行重置密碼</h3>
					</div>
					<div class="card-body">
						<div class="list-group">
							<div class="list-group-item">
								<label for="pwd_forget_id">*會員帳號：</label> 
								<span id="id_error" class="text-danger"></span> 
								<input type="text"	id="pwd_forget_id" name="pwdForgetId" placeholder="請輸入帳號" class="form-control" required="required" />
							</div>
							<div class="list-group-item">
								<label for="pwd_forget_email">*會員信箱：</label> 
								<span id="email_error" class="text-danger"></span>
								<input type="email" id="pwd_forget_email" name="pwdForgetEmail" placeholder="請輸入電子郵箱" class="form-control" required="required" />
							</div>
						</div>
					</div>
					<div class="card-footer text-center">
						<input type="submit" value="送出" class="col-md-6 mx-auto btn btn-info" />
					</div>
				</form>
			</div>
		</div>
		<!-- main_container end -->
		<jsp:include page="/includes/main_aside.jsp" />
		<jsp:include page="/includes/main_footer.jsp" />
	</div>
</body>
</html>