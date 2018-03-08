<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Music Login</title>
  <!-- Bootstrap core CSS-->
  <link href="${ctx}/assets/backend/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="${ctx}/assets/backend/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="${ctx}/assets/backend/css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form method="post" action="<c:url value='/backEndLogin'/>">
          <div class="form-group">
            <label for="employee_id">Id</label>
            <input class="form-control" id="exampleInputEmail1" name="employee_id"  placeholder="Enter Id">
          </div>
          <div class="form-group">
            <label for="employee_password">Password</label>
            <input class="form-control" id="exampleInputPassword1" name="employee_password" type="password" placeholder="Password">
          </div>
          <div class="form-group">
            <div class="form-check">
              <label class="form-check-label">
                 <span style="color:red">${errors.login_errors}</span>
            </div>
          </div>
           <button class="btn btn-primary btn-block" type="submit">Login</button>
        </form>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  <script src="${ctx}/assets/backend/vendor/jquery/jquery.min.js"></script>
  <script src="${ctx}/assets/backend/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="${ctx}/assets/backend/vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>