<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EEIT - jQuery</title>
<jsp:include page="/includes/main_css.jsp" />
<jsp:include page="/includes/main_js.jsp" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/jumbotron.css">
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<main role="main" class="container mt-2">
    <div class="row">
       <div class="col-lg-3">
       <div class="col-lg-9">
     
		<div class="card">
			<div class="card-header">jQuery 練習</div>
			<div class="card-body">
				<!-- 每頁不同的內容從這裡開始 -->
				 <table id="productTable" class="table table-bordered">
                       <thead>
                          <tr>
                             <th>id</th>
                             <th>音樂風格</th>
                             <th>音樂名稱</th>
                             <th>音樂上傳者</th>
                             <th>按讚</th>
                          </tr>
                       </thead>
                       <tbody>
                    
<!--                        	<tr><td>1</td><td>Chai</td><td>18.0000</td><td>39</td><td><button class="btn btn-info"><i class="fas fa-edit"></i></button></td></tr> -->
<!--                        		<tr><td>2</td><td>Chang</td><td>19.0000</td><td>17</td><td><button name=like value="insert" class="btn btn-info"><i class="fas fa-edit"></i></button></td></tr> -->
<!--                        		<tr><td>24</td><td>Guaraná Fantástica</td><td>4.5000</td><td>20</td><td><button class="btn btn-info"><i class="fas fa-edit"></i></button></td></tr> -->
            
                       </tbody>
                      
                   </table>
				<!-- 每頁不同的內容到這裡結束 -->
			</div>
		</div>


       </div>
    </div>
	</main>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {		
//讀取產品
			  $.getJSON('MusicController.controller',{music_id:1},function(data){
			    		//datas = [{},{}];
			    		console.log(data);
			    		var docFrag = $(document.createDocumentFragment());
			    		$.each(datas,function(idx,mu){
			    			//product = {}
			    			var cell1 = $("<td></td>").text(mu.music_id);
			    			var cell2 = $("<td></td>").text(mu.music_style_id);
			    			var cell3 = $("<td></td>").text(mu.music_name);
			    			var cell4 = $("<td></td>").text(mu.music_member_id);
			    			var cell5 = $("<td></td>").html('<button class="btn btn-info"><i class="fas fa-edit"></i></button>');
			    			var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
			    			
			    			docFrag.append(row);
			    		});
			    		$('#productTable>tbody').html(docFrag);
			    
			  });
		})
	</script>
</body>
</html>