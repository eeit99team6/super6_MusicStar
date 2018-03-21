<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Music Star Index</title>
<jsp:include page="/backend/include/css_js_include.jsp" />

<link type="text/css" rel="stylesheet" href="${ctx}/backend/css/jquery-ui.structure.css">
<link type="text/css" rel="stylesheet" href="${ctx}/backend/css/jquery-ui.theme.css">
<link type="text/css" rel="stylesheet" href="${ctx}/backend/css/jquery-ui.css">

</head>
<body>
	<!-- Navigation-- jsp include -->
	<jsp:include page="/backend/include/nav.jsp" />

 	      <!-- Breadcrumbs-->

 <div class="content-wrapper">
    <div class="container-fluid">
      <!-- 創建比賽 查詢比賽 -->
            <ol class="breadcrumb"  style="margin-top:60px;">
        <li class="breadcrumb-item">
          <a href="#">Music Star</a>
        </li>
          <li class="breadcrumb-item active">比賽後台</li>
      </ol>
      
                                                                                
     <a style="padding-left:0; display:inline" class="nav-link" data-toggle="modal" data-target="#creatContest"><button class="btn btn-success"><i class="fa fa-fw fa-table"></i>建構比賽</button></a>
     <button class="btn btn-success" id="tableCheck"><i class="fa fa-fw fa-table"></i>查看比賽</button>
   
<!--===================== creatContest===========================-->
<div id="creatContest" class="modal fade" >
<div class="modal-dialog" role="document">               
  <form class="modal-content" method="POST" enctype="multipart/form-data" action="<c:url value='/backend/pages/musicContext'/>">
      <div class="modal-header" >
          <img src="../images/music-star.png" width="50"><h3 class="modal-title" id="exampleModalLabel">Music Star</h3>
         <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
      </div>

    <div class="modal-body">
      <label for="music_contest_name">比賽名稱</label><span id="userAjax2"></span>
      <input type="text" placeholder="請輸入比賽名稱" name="music_contest_name" class="col-xl-12">
    </div>  
    
    <div class="modal-body">
      <label for="music_contest_description">比賽簡介</label>
      <textarea id="confirmationText" class="text col-xl-12" name="music_contest_description" placeholder="請輸入比賽簡介"  ></textarea>
     </div> 
     
     <div class="modal-body" > 
      <label for="music_contest_photo">比賽照片</label>
      <input type="file" name="music_contest_photo" class="filestyle col-xl-12" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="lolo">
     </div> 
     
     <div class="modal-body">
      <label for="music_contest_style_id">音樂類型</label>
      <select name="music_contest_style_id" class="col-xl-12">
        <option value="1">Rock</option>
        <option value="2">Jazz</option>
        <option value="3">Blue</option>
        <option value="4">Country</option>
        <option value="5">Pop</option>
        <option value="6">Electronic</option>
        <option value="7">Flok</option>
        <option value="8">Alternative</option>
        <option value="9">Punk</option>
        <option value="10">Classical</option>
        <option value="11">Religion</option>
        <option value="12">Independent</option>
      </select>
      </div> 
      
     <div class="modal-body">
      <label for="music_contest_status">賽事狀況</label>
      <input type="text" placeholder="請輸入比賽狀況" name="music_contest_status" value="報名中" class="col-xl-12" readonly>
      </div> 
      
      
      <div class="modal-body">
      <label for="music_contest_apply_start_date">報名開始日期</label>
      <input type="text" placeholder="請選擇報名開始日期" name="music_contest_apply_start_date" id="music_contest_apply_start_date" class="col-xl-12">
      </div> 
      
     
     <div class="modal-body">
      <label for="music_contest_validate_date">審核開始日期</label>
      <input type="text" placeholder="請選擇報名截止/審核開始日期" name="music_contest_validate_date" id="music_contest_validate_date" class="col-xl-12">
     </div> 
     
     
     <div class="modal-body">
      <label for="music_contest_vote_start_date">投票開始日期</label>
      <input type="text" placeholder="請選擇投票開始日期" name="music_contest_vote_start_date" id="music_contest_vote_start_date" class="col-xl-12">
     </div> 
      
     <div class="modal-body">
      <label for="music_contest_end_date">比賽結束日期</label>
      <input type="text" placeholder="請選擇比賽結束日期" name="music_contest_end_date" id="music_contest_end_date" class="col-xl-12">
    </div> 
       <div class="modal-footer col-xl-12">
      <button type="submit" class="col-xl-12">送出</button>
     </div> 
      
       </div>
    </form>
    </div>     

      <!--=================================================================-->
<table id="table1" class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th>NO.</th>
								<th>比賽名稱</th>
								<th>音樂類型</th>
								<th>比賽簡介</th>
								<th>賽事狀況</th>
								<th>報名開始日期</th>
								<th>審核開始日期</th>
								<th>投票開始日期</th>
								<th>比賽結束日期</th>
								<th>比賽照片</th>
								<th>修改</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
 
  
      <!-- Area Chart Example-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-area-chart"></i> Area Chart Example</div>
        <div class="card-body">
          <canvas id="myAreaChart" width="100%" height="30"></canvas>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
      <div class="row">
        <div class="col-lg-8">
          <!-- Example Bar Chart Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-bar-chart"></i> Bar Chart Example</div>
            <div class="card-body">
              <canvas id="myBarChart" width="100" height="50"></canvas>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div>
        </div>
        
        
        <div class="col-lg-4">
          <!-- Example Pie Chart Card-->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-pie-chart"></i> Pie Chart Example</div>
            <div class="card-body">
              <canvas id="myPieChart" width="100%" height="100"></canvas>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Music Star 2018</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>

    
		<!-- Bootstrap core JavaScript-->
		<script src="${ctx}/backend/vendor/jquery/jquery.min.js"></script>
		<script src="${ctx}/backend/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<!-- Core plugin JavaScript-->
		<script src="${ctx}/backend/vendor/jquery-easing/jquery.easing.min.js"></script>
		<!-- Page level plugin JavaScript-->
		<script src="${ctx}/backend/vendor/chart.js/Chart.min.js"></script>
		<!-- Custom scripts for all pages-->
		<script src="${ctx}/backend/js/sb-admin.min.js"></script>
		<!-- Custom scripts for this page-->
		<script src="${ctx}/backend/js/sb-admin-charts.min.js"></script>
	</div>
</div>
	
<!--=========================updateContest=============================-->    	

<div id="updateContest" class="modal fade"> 
  <div class="modal-dialog" role="document">              
  
  <form class="modal-content" method="POST" enctype="multipart/form-data" action="<c:url value='/backend/pages/musicContext'/>">
      <div class="modal-header" >
          <img src="../images/music-star.png" width="50"><h3 class="modal-title" id="exampleModalLabel">Music Star</h3>
         <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
      </div>

    <div class="modal-body">
      <label for="music_contest_id">No.</label>
      <input type="text" id="update_id" name="music_contest_id" class="col-xl-12" readonly>
    </div>
    
    <div class="modal-body">
      <label for="music_contest_name">比賽名稱</label>
      <input id="update_name" type="text" placeholder="請輸入比賽名稱" name="music_contest_name" class="col-xl-12">
    </div>  
      
    <div class="modal-body">
      <label for="music_contest_description">比賽簡介</label>
      <textarea id="update_decription" class="text col-xl-12" name="music_contest_description" placeholder="請輸入比賽簡介" ></textarea>
    </div> 
      
    <div class="modal-body">  
      <label for="music_contest_photo">比賽照片</label>
      <input type="file" name="music_contest_photo" class="filestyle col-xl-12" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="lolo">
    </div>
   
   <div class="modal-body">   
      <label for="music_contest_style_id">音樂類型</label>
      <select id="update_style_id" name="music_contest_style_id" class="col-xl-12">
        <option value="1" selected>Rock</option>
        <option value="2">Jazz</option>
        <option value="3">Blue</option>
        <option value="4">Country</option>
        <option value="5">Pop</option>
        <option value="6">Electronic</option>
        <option value="7">Flok</option>
        <option value="8">Alternative</option>
        <option value="9">Punk</option>
        <option value="10">Classical</option>
        <option value="11">Religion</option>
        <option value="12">Independent</option>
      </select>
    </div>
    
    <div class="modal-body">  
      <label for="music_contest_apply_start_date">報名開始日期</label>
      <input type="text" placeholder="請選擇報名開始日期" name="music_contest_apply_start_date" id="update_start_date" class="col-xl-12">
    </div>
    
    <div class="modal-body">  
      <label for="music_contest_validate_date">審核開始日期</label>
      <input type="text" placeholder="請選擇報名截止/審核開始日期" name="music_contest_validate_date" id="update_validate_date" class="col-xl-12">
    </div>
    
    <div class="modal-body"> 
      <label for="music_contest_vote_start_date">投票開始日期</label>
      <input type="text" placeholder="請選擇投票開始日期" name="music_contest_vote_start_date" id="update_vote_date" class="col-xl-12">
    </div>
    
    <div class="modal-body">  
      <label for="music_contest_end_date">比賽結束日期</label>  
      <input type="text" placeholder="請選擇比賽結束日期" name="music_contest_end_date" id="update_end_date" class="col-xl-12">
    </div> 
    
       
    <div class="modal-body">
      <label for="music_contest_status">賽事狀況</label>
      <input id="update_status" type="text" placeholder="請輸入比賽狀況" name="music_contest_status" class="col-xl-12" readonly>
    </div>
     
      <div class="modal-footer ">
      <button type="submit" name="music_contest_update" class="col-xl-12">送出</button>
     </div> 
    </form>
</div>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.js"></script>
<script type="text/javascript" src="${ctx}/backend/js/jquery-datepicker-ui.js"></script>
	

	

<script type="text/javascript">
	var modal3 = document.getElementById("id03");
	var modal4 = document.getElementById("id04");
	
	window.onclick = function(event) {
	    if (event.target == modal3) {
	        modal3.style.display = "none";
	    }
	    if (event.target == modal4) {
	        modal4.style.display = "none";
	    }
	}
	
	jQuery(document).ready(function(){
		
		$("#creatContest label").css({"font-weight":"bold"})
		
 		$("#music_contest_apply_start_date, #music_contest_validate_date, #music_contest_vote_start_date, #music_contest_end_date").datepicker({dateFormat: "yy-mm-dd"});
		                                                                                   
 		$("#update_start_date, #update_validate_date, #update_vote_date, #update_end_date").datepicker({dateFormat: "yy-mm-dd"})
 		                                                                                  .on("change",function(){
                                                                                      // star 
                                                                                        var startTemp =  $("#update_start_date").val();
                                                                                           var startDate = new Date(startTemp).getTime();
                                                                                      // validate
                                                                                        var validateTemp = $("#update_validate_date").val();
                                                                                           var validateDate = new Date(validateTemp).getTime();
                                                                                      // voting
                                                                                        var voteTemp = $("#update_vote_date").val();
                                                                                           var voteDate = new Date(voteTemp).getTime();
                                                                                      // end
                                                                                        var endTemp =  $("#update_end_date").val();
                                                                                           var endDate = new Date(endTemp).getTime();
                                                                                      // compare            
                                                                                      var updateStatus = $("#update_status");
                                                                                            updateStatus.empty();
                                                                                      // current time
                                                                                         var currentTime = new Date().getTime();
                                                                                        
                                                                                      if(startDate < currentTime  &&  currentTime < validateDate){
                                                                                        updateStatus.val("報名中");
                                                                                      }else if( validateDate < currentTime &&  currentTime < voteDate ){
                                                                                        updateStatus.val("審核中");
                                                                                      }else if( voteDate < currentTime && currentTime < endDate ){
                                                                                        updateStatus.val("投票中");
                                                                                      }else if (endDate <= currentTime ){
                                                                                        updateStatus.val("比賽結束");
                                                                                      }
                                                                                      
                                                                                    });
		                                                                           
		                                        
 		
		$("#id03 input[name='music_contest_name']").keyup(function(){
	      var inputValue = $(this).val()
     	$.get("MusicContestNameCheck",{"userid":$(this).val()},function(data){
		
	 	if(inputValue !=''){
	    	$("#userAjax2").html("<span style='padding:0 0 0 20px'>"+data+"</span>")
		}else{
			$("#userAjax2").empty();
		}
     	    });
	    });
		
		

		
		//tableCheck
		$("#tableCheck").click(function(){
			$.getJSON("musicContextCheckAjax", {}, function(json_data){
	    $('#table1>tbody').empty();
	       $.each(json_data, function(index, item){
			    	 var cell1 = $("<td></td>").text(item.music_contest_id);
			    	 var cell2 = $("<td></td>").text(item.music_contest_name);
	                 var style=null;
			    	   if(item.music_contest_style_id=='1'){
			    		   style='Rock' 
	                   }else if(item.music_contest_style_id=='2'){
	                	   style='Jazz'
	                   }else if(item.music_contest_style_id=='3'){
	                	   style='Blue'
	                   }else if(item.music_contest_style_id=='4'){
	                	   style='Country'
	                   }else if(item.music_contest_style_id=='5'){
	                	   style='Pop'
	                   }else if(item.music_contest_style_id=='6'){
	                	   style='Electronic'
	                   }else if(item.music_contest_style_id=='7'){
	                	   style='Flok'
	                   }else if(item.music_contest_style_id=='8'){
	                	   style='Alternative'
	                   }else if(item.music_contest_style_id=='9'){
	                	   style='Punk'
	                   }else if(item.music_contest_style_id=='10'){
	                	   style='Classical'
	                   }else if(item.music_contest_style_id=='11'){
	                	   style='Religion'
	                   }else if(item.music_contest_style_id=='12'){
	                	   style='Independent'
	                   }    	   
			    	   var cell3 = $("<td></td>").text(style);  
	                   var cell4 = $("<td></td>").text(item.music_contest_description);
	                   var cell5 = $("<td></td>").text(item.music_contest_status);
	                   var cell6 = $("<td></td>").text(item.music_contest_apply_start_date);
	                   var cell7 = $("<td></td>").text(item.music_contest_validate_date);
	                   var cell8 = $("<td></td>").text(item.music_contest_vote_start_date);
	                   var cell9= $("<td></td>").text(item.music_contest_end_date);
	                   var cellImg = $("<img/>").attr({
	                	  src: item.music_contest_photo,
	                      title: item.music_contest_name,
	                      alt: item.music_contest_name,
	                      width:"50"
	                   });
	                   
	                   var cellLink =$("<a></a>")
	                                  .attr({href: item.music_contest_photo, title:item.music_contest_name })
	                                  .addClass("image-popup-no-margins");
	                   cellLink.append(cellImg)
	                   var cell10 = $("<td></td>").append(cellLink)
	                    cell10.append(cellLink)
	                   var cell11 = $("<td></td>").html('<a style="padding-left:0; display:inline" class="nav-link" data-toggle="modal" data-target="#updateContest"><button class="btn btn-info edit"><i class="fa fa-fw fa-wrench"></i></button></a>');
	                   var cell11_2 = $("<td></td>")
	                   
	                   var statusName = item.music_contest_status;
	                   if(statusName!='比賽結束'){
	                	  var row = $('<tr></tr>').append([cell1, cell2, cell3, cell4,cell5, cell6, cell7, cell8,cell9,cell10,cell11]);     
	                  }else{
	                	  var row = $('<tr></tr>').append([cell1, cell2, cell3, cell4,cell5, cell6, cell7, cell8,cell9,cell10,cell11_2]);     
	                  }
	                   
	                   $('#table1>tbody').append(row); 
	                    });
	       
	       $("#table1>tbody").on('click','tr button:nth-child(1)',function(){
           	$("#updateContest").show();
          	   var music_contest_id = $(this).parents('tr').find('td:nth-child(1)').text(); 
	    	   var music_contest_name = $(this).parents('tr').find('td:nth-child(2)').text();
               var music_contest_description = $(this).parents('tr').find('td:nth-child(4)').text();
               var music_contest_status = $(this).parents('tr').find('td:nth-child(5)').text();
               var music_contest_apply_start_date = $(this).parents('tr').find('td:nth-child(6)').text();
               var music_contest_validate_date = $(this).parents('tr').find('td:nth-child(7)').text();
               var music_contest_vote_start_date = $(this).parents('tr').find('td:nth-child(8)').text();
               var music_contest_end_date= $(this).parents('tr').find('td:nth-child(9)').text();
            	
               $("#update_id").val(music_contest_id).next("span").text(music_contest_id);
               $("#update_name").val(music_contest_name);
               $("#update_decription").val(music_contest_description);
               $("#update_start_date").val(music_contest_apply_start_date);
               $("#update_validate_date").val(music_contest_validate_date);	 	               
               $("#update_vote_date").val(music_contest_vote_start_date);
               $("#update_end_date").val(music_contest_end_date);
            
	           }) // 查看比賽賽事狀態按鈕結尾
	           
		    })
		 });
		

		
		
		
		
	});
	
	</script>
</body>
</html>