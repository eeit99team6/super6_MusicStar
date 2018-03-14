<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>歌單總攬</title>
<jsp:include page="/includes/main_css.jsp" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/jumbotron.css">
<jsp:include page="/includes/main_js.jsp" />
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<main role="main" class="container mt-2">
    <div class="row">
       <div class="col-lg-3">
       <div class="col-lg-9">
     <br>
      <br>
       <br>
		<div class="card">
			<div class="card-body">
				<!-- 每頁不同的內容從這裡開始 -->
				 <table id="productTable" class="table table-bordered">
                       <thead>
                          <tr>
                            <th>歌單ID</th>
							<th>歌單名稱</th>
							<th>歌單描述</th>
							<th>歌單會員</th>
							<th>歌曲數量</th>
                          </tr>
                       </thead>
                       <tbody>
                       </tbody>
                      
                       <tfoot>
                       <tr>

                       </tfoot>
                   </table>
				<!-- 每頁不同的內容到這裡結束 -->
			</div>
		</div>


       </div>
    </div>
	</main>

	<script src="assets/js/jquery-3.3.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {		
			    //讀取歌單
			    	$.getJSON(ctx+'/pages/musiclists',function(datas){
			    		//datas = [{},{}];
			    		console.log(datas)
// 			    		console.log(datas[1])
// 			    		console.log(datas[1].member_music_list_description)
			    		var docFrag = $(document.createDocumentFragment());
			    		$.each(datas,function(idx,mu){
			    			console.log(mu.member_music_list_description)
			    			console.log(mu)
			    			//product = {}           '<a href=/pages/musiclistcontent> + 'mu.member_music_list_name'+ </a>'
//			    			                           .text('<a href='/pages/musiclistcontent+'>mu.member_music_list_name</a>
	// 		    			var cc2=$('<td></td>')     .html('<a href='+mu[1].music_link+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			    			var cell1 = $("<td></td>").html(mu.member_music_list_id);
			    			var cell2 = $("<td></td>").html('<a href="'+ctx+'/pages/musiclistcontent.jsp?member_music_list_content_id='+mu.member_music_list_id+'">' + mu.member_music_list_name + '</a>');
			    			var cell3 = $("<td></td>").html(mu.member_music_list_description);
			    			var cell4 = $("<td></td>").html(mu.member_music_list_member_id);
			    			var cell5 = $("<td></td>").html(mu.member_music_list_quantity);
			    			var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
			    			
			    			docFrag.append(row);
			    	})
			    	   		$('#productTable>tbody').html(docFrag);
			    	})
			})	    	
	</script>
    <jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>