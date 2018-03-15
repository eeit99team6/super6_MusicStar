<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>我的歌單</title>
<jsp:include page="/includes/main_css.jsp" />
<jsp:include page="/includes/main_js.jsp" />

</head>
<jsp:include page="/includes/main_header.jsp" />
	
	
	<!-- main_container start -->
	<div id="main_container" class="container-fuild mx-auto text-center">
    <div class="row">
       <div class="col-lg-3">
       <div class="col-lg-9">
     <br>
      <br>
       <br>
       <h3>我的歌單</h3>
		<div class="card">
			<div class="card-body">
				<!-- 每頁不同的內容從這裡開始 -->
				 <table id="like_table" class="table table-bordered">
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
    </div>
 </div>	

	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />


	<script src="assets/js/jquery-3.3.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script language="javascript">
	$(document).ready(function() {		
	    //讀取歌單
	    	$.getJSON(ctx+'/mymusiclistidid.controller',function(datas){
	    		//datas = [{},{}];
	    		console.log(datas)
//		    		console.log(datas[1].member_music_list_description)
	    		var docFrag = $(document.createDocumentFragment());
	    		$.each(datas,function(idx,mu){
	    		
	    			//product = {}
	    			var cell1 = $("<td></td>").html(mu.member_music_list_id);
	    			var cell2 = $("<td></td>").html(mu.member_music_list_name);
	    			var cell3 = $("<td></td>").html(mu.member_music_list_description);
	    			var cell4 = $("<td></td>").html(mu.member_music_list_member_id);
	    			var cell5 = $("<td></td>").html(mu.member_music_list_quantity);
	    		
	    			var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
	    			
	    			docFrag.append(row);
	    	})
	    	   		$('#like_table>tbody').html(docFrag);
	    	})
	})	    	
</script>
</body>
</html>