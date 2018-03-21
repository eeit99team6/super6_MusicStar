<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Music Star Index</title>
<jsp:include page="/backend/include/css_js_include.jsp"/>
<style type="text/css">
.row .mr-5{
text-align:cneter;
font-size:40px;
}


</style>
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">

  <!-- Navigation-- 用jsp:include進來-->
  <jsp:include page="/backend/include/nav.jsp"/>
  
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb"  style="margin-top:4px;">
        <li class="breadcrumb-item" >
          <a href="#">Music Star</a>
        </li>
        <li class="breadcrumb-item active">主控台</li>
      </ol>
<div class="row">
        <div class="col-xl-4 col-sm-6 mb-4">
          <div class="card text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-comments"></i>
              </div>
              <div class="mr-5">報名中賽事</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="pages/charts.jsp">
              <span class="float-left" id="span_applying" style="font-size:40px;">努力加載中...</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-4 col-sm-6 mb-4">
          <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-list"></i>
              </div>
              <div class="mr-5">投票中賽事</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="pages/charts.jsp">
              <span class="float-left" id="span_voting" style="font-size:40px;">努力加載中...</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-4 col-sm-6 mb-4">
          <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-shopping-cart"></i>
              </div>
              <div class="mr-5">歷史賽事</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="pages/charts.jsp">
              <span class="float-left" id="span_close" style="font-size:40px;text-align:center;">努力加載中...</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
      </div>


      <!-- Area Chart Example-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-area-chart"></i>Music 總覽</div>
        <div class="card-body">
          <canvas id="myAreaChart" width="100%" height="30"></canvas>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>

    </div>

   
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
    <script src="js/sb-admin-charts.js"></script>
  </div>
<script>

$(function(){
	
	// start contest 
	$.getJSON("/MusicStarProject/contests/applying",{},function(jason_data_applying){
			$("#span_applying").text(jason_data_applying.length)
	});
	
	// voting contest 
	$.getJSON("/MusicStarProject/contests/voting",{},function(jason_data_voting){
			$("#span_voting").text(jason_data_voting.length)
	});
	//close contest
	$.getJSON("/MusicStarProject/contests/close",{},function(jason_data_close){
			$("#span_close").text(jason_data_close.length)
	});
	
	
	
	
})

</script>    
</body>

</html>
