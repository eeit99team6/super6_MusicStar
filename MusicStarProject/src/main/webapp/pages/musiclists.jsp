<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>歌單總覽</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
.centerContainer{
margin:0 auto;
padding-top:50px;
text-align: center;
}
#productTable th{
padding-left:60px;
padding-right:60px;
}
th{
min-width:110px
}
td{
margin:0 auto;
}
</style>

<link href="${ctx}/assets/css/common_style/allPages.css" rel="stylesheet"/>
<jsp:include page="/includes/main_js.jsp" />


</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	
	<!-- main_container start -->
	<div class="title-title">歌單總覽 </div> 
  <div class="row">
     <div class="centerContainer">				
				<!-- 每頁不同的內容從這裡開始 -->
				 <table id="productTable" class="table table-bordered">
                       <thead>
                          <tr>
                            <th>歌單ID</th>
							<th>歌單名稱</th>
							<th>歌單描述</th>
							<th>歌單會員</th>
<!-- 							<th>歌曲數量</th> -->
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
			    			var cell1 = $("<td ></td>").html(mu.member_music_list_id);
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
    

	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
	</body>
</html>