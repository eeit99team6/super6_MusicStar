<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>註冊會員</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
.box {
	margin: 80px auto;
	width: 500px;
}
</style>
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/register.js"></script>
<script>
	
</script>
</head>
<body>
	<div class="wrapper scrollbar-dynamic">
		<jsp:include page="/includes/main_header.jsp" />
		<!-- main_container start -->
		<div id="main_container" class="container">
			<div class="box card">
				<form id="register_form"
					action="<c:url value="/members/registerAjax"/>" method="post"
					enctype="multipart/form-data">
					<div class="card-header text-center">
						<h1>註冊會員</h1>
					</div>
					<div class="card-body">
						<div class="list-group">
							<div class="list-group-item">
								<label for="mbrRegisterId">*會員帳號：</label> <span id="idError"
									class="text-danger"></span> <input type="text"
									id="mbrRegisterId" name="mbrId" placeholder="請輸入帳號"
									class="form-control" required="required" />
							</div>
							<div class="list-group-item">
								<label for="mbrRegisterName">*會員姓名：</label> <span id="nameError"
									class="text-danger"></span> <input type="text"
									id="mbrRegisterName" name="mbrName" placeholder="請輸入姓名"
									class="form-control" required="required" />
							</div>
							<div class="list-group-item">
								<label for="mbrRegisterPwd">*會員密碼：</label> <span id="pwdError"
									class="text-danger"></span> <input type="password"
									id="mbrRegisterPwd" name="mbrPwd" placeholder="請輸入密碼"
									class="form-control" required="required" />
							</div>
							<div class="list-group-item">
								<label for="mbrRegisterPwdCheck">*確認密碼：</label> <span
									id="pwdCheckError" class="text-danger"></span> <input
									type="password" id="mbrRegisterPwdCheck" name="mbrPwdCheck"
									placeholder="請確認密碼" class="form-control" required="required" />
							</div>
							<div class="list-group-item">
								<label for="mbrRegisterEmail">*會員信箱：</label> <span
									id="emailError" class="text-danger"></span> <input type="email"
									id="mbrRegisterEmail" name="mbrEmail" placeholder="請輸入電子郵箱"
									class="form-control" required="required" />
							</div>
							<div class="list-group-item">
								<label>*會員性別：</label> <input type="radio" id="male"
									name="mbrGender" value="M" class="radio-inline"
									required="required" /><label for="male">男</label> <input
									type="radio" id="female" name="mbrGender" value="F"
									class="radio-inline" /><label for="female">女</label> <span
									id="genderError" class="text-danger"></span>
							</div>
						</div>
						<div class="list-group-item">
							<label>會員頭像：</label> <input type="file" id="profile"
								name="mbrProfile" accept="image/*" />
							<div>
								<span id="profileerror" class="text-danger"></span>
							</div>
						</div>
					</div>
					<div class="card-footer text-center">
						<input type="submit" value="確認註冊"
							class="col-md-6 mx-auto btn btn-info" />
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