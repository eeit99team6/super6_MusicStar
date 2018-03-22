<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="../css/table.css" /> -->
<title>我的歌單</title>
<jsp:include page="/includes/main_css.jsp" />
<jsp:include page="/includes/main_js.jsp" />
<style type="text/css">

.centerContainer{
margin:0 auto;
}

.idTd{
color:snow;
visibility:hidden;
} 
th{
min-width:110px
}
td{
margin:0 auto;
}
</style>
</head>
<body>
<jsp:include page="/includes/main_header.jsp" />
<!-- main_container start -->
	<div id="main_container" class="container-fuild">
			 <div class="title-title"> 我的歌單 </div>
	
<!-- 	<main role="main" class="container mt-2"> -->
    <div class="row">
    <div class="centerContainer">
       <div class="col-lg-3">
       <div class="col-lg-9">
     <br>
      <br>
       <br>
		
				<!-- 每頁不同的內容從這裡開始 -->
				 <table id="like_table" class="table table-bordered">
                       <thead>
                          <tr>
                            <th>歌單編號</th>
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
                       <form name="myForm">
                        <td><input type="hidden" id="ProductID" name="ProductID"><span></span></td>
                        <td><input type="text" style="width:100px" class="form-control" id="member_music_list_name" name="member_music_list_name" placeholder="歌單名稱"></td>
                        <td><input type="text" style="width:100px" class="form-control" id="member_music_list_description" name="member_music_list_description" placeholder="歌單描述"></td>
                        <td><button id="buttonAdd" type="button" class="btn btn-primary"><i class="fas fa-plus"></i></button></td>
                       </tr>
                       </form>
                       </tfoot>
                   </table>
				<!-- 每頁不同的內容到這裡結束 -->
			</div>
		</div>


       </div>
    </div>
    </div>
    
<!-- 	</main> -->

<!-- 	<script src="assets/js/jquery-3.3.1.min.js"></script> -->
<!-- 	<script src="assets/js/bootstrap.min.js"></script> -->
	<script language="javascript">
	$(document).ready(function() {
		function createListTable(){
	    //讀取歌單
	    	$.getJSON(ctx+'/mymusiclistidid.controller',function(datas){
	    		//datas = [{},{}];
	    		console.log(datas)
//		    		console.log(datas[1].member_music_list_description)
				if(datas.fucknull){
					alert(datas.fucknull);
				}else{
				var i=1;
	    		var docFrag = $(document.createDocumentFragment());
	    		$.each(datas,function(idx,mu){			  	    		
	    			//product = {}
	    			var cell1 = $("<td></td>").html(i);
	    			var cell2 = $("<td></td>").html('<a href="'+ctx+'/pages/mymusiclistcontent.jsp?member_music_list_content_id='+mu.member_music_list_id+'">' + mu.member_music_list_name + '</a>');
	    			var cell3 = $("<td></td>").html(mu.member_music_list_description);
	    			var cell4 = $("<td></td>").html(mu.member_music_list_member_id);
	    			var cell5 = $("<td></td>").html(mu.member_music_list_quantity);
	    			var cell6 = $("<td></td>").html('<button class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>');
	    			var cell7 = $("<td style='visibility:hidden'></td>").html(mu.member_music_list_id);//.addClass("idTd");

	    			var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6,cell7]);
	    			i++;
	    			docFrag.append(row);
	    		});

				    //刪除歌單			    
			$('#like_table>tbody').on('click','tr button:nth-child(1)',function(){
	 			   var id = $(this).parents('tr').find('td:nth-child(7)').text();
	 			   $.get(ctx+'/deletemymusiclist',{'member_music_list_id':id},function(data){
	 				   if(data.ok){		 					   
	 				   alert(data.ok);
	 				  	createListTable();
	 				   }

	 			   })
			  });
	    	   		$('#like_table>tbody').html(docFrag);
	    	}});}
	    	 		
		$('#buttonAdd').click(function(){
	    	var datas = $('form[name="myForm"]').serialize();
	    	//var datas = $('form[name="myForm"]').serializeArray();
	    	//console.log(datas);
	    	$.post(ctx+'/insertmymusiclist',datas,function(data){
	    		 		    		
	    		 		    		
	    		$('#member_music_list_name').val('');
				$('#member_music_list_description').val('');
				alert(data.insertok);
				createListTable();
	    	});	
	 	});
		createListTable();
		});	  
</script>
</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>