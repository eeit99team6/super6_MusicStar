<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	var $pwd_reset_form = $("#pwd_reset_form"),
	$ajax_mask = $("#ajax_mask"),
	$new_pwd = $("#new_pwd"),
	$check_new_pwd = $("#check_new_pwd"),
	$new_pwd_error = $("#new_pwd_error"),
	$check_new_pwd_error = $("#check_new_pwd_error");
	
	function pwdCheck(){
		if($new_pwd.val() !== $check_new_pwd.val() && $check_new_pwd.val().trim().length > 0){
			$check_new_pwd_error.html("密碼不相符");
			return false;
		}else{
			$check_new_pwd_error.html("");
			return true;
		}	
	}
	
	$pwd_reset_form.submit(function(e) {
		e.preventDefault();
		var formData = new FormData($pwd_reset_form[0])
		, action = $pwd_reset_form.attr("action");
	
		if(pwdCheck()){		
		$.ajax({
			type : 'POST',
			url : action,
			data : formData,
			contentType : false,
			cache : false,
			processData : false,
			success : function(data) {
				$ajax_mask.addClass("ajax_hide");
				setTimeout(function(){
				if (data.success) {
					alert(data.success);
					location.href = ctx + "/index.jsp";
				} else {
					$new_pwd_error.html(data.newPwdError || "");
					$check_new_pwd_error.html(data.checkNewPwdError || "");
					if(data.errMsg){
						alert(data.errMsg);
					}
				}},100);			
			},
			beforeSend : function(){$ajax_mask.removeClass("ajax_hide");}
		});		
		}
	});
		
	$new_pwd.blur(function(){	
		pwdCheck();
	});
	$check_new_pwd.blur(function(){	
		pwdCheck();		
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
				<form id="pwd_reset_form"
					action="<c:url value="/security/resetPwdAjax"/>" method="post">
					<div class="card-header text-center">
						<h1>重置密碼</h1>
					</div>
					<div class="card-body">
						<div class="list-group">
							<div class="list-group-item">
								<label for="new_pwd">*新密碼：</label> 
								<span id="new_pwd_error" class="text-danger"></span> 
								<input type="password"	id="new_pwd" name="newPwd" placeholder="請輸入新密碼" class="form-control" required="required" />
							</div>
							<div class="list-group-item">
								<label for="check_new_pwd">*確認新密碼：</label> 
								<span id="check_new_pwd_error" class="text-danger"></span>
								<input type="password" id="check_new_pwd" name="checkNewPwd" placeholder="請確認新密碼" class="form-control" required="required" />
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