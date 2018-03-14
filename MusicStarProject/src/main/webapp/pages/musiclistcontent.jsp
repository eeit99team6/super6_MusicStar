<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>歌單內容</title>
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
                            <th></th>
                            <th>歌單ID</th>
							<th>歌曲id</th>
							<th>歌曲名稱</th>
							<th>歌曲連結</th>
							<th>歌曲作者ID</th>
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
			    	$.getJSON(ctx+'/musiclistiditem.controller',{'member_music_list_content_id':'${param.member_music_list_content_id}'},function(datas){
			    		//datas = [{},{}];
			    		console.log(datas)
// 			    		console.log(datas[1].member_music_list_description)
			    		var docFrag = $(document.createDocumentFragment());
			    		$.each(datas,function(idx,mu){
			    		
			    			//product = {}
			    			var cell1 = $("<td></td>").text(mu[1].music_photo);
			    			var cell2 = $("<td></td>").text(mu[0].member_music_list_content_id);
			    			var cell3 = $("<td></td>").text(mu[0].member_music_list_content_music_id);
			    			var cell4 = $("<td></td>").text(mu[1].music_id);
			    			var cell5 = $("<td></td>").text(mu[1].music_link);
			    			var cell6 = $("<td></td>").text(mu[1].music_member_id);
			    			var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6]);
			    			
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