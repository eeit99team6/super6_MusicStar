<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InsertMusicOK</title>
<link href="${ctx}/assets/css/common_style/allPages.css" rel="stylesheet"/>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">

#alerInfor{
text-align:center;
margin:200px;
}

#countdownDiv{
font-size:80%;
text-align:right;
margin-right:20%;
}

</style>
<jsp:include page="/includes/main_js.jsp" />
<script>
	
</script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
		 <div class="title-title"><div class="fab fa-angellist" > MusicStar </div> <div class="fab fa-angellist" ></div></div>

	       <div id="alerInfor"><h1>Yo~ ${insertMusicOk.music_member_id} ${message}${errorMessage}</h1>
	       </div></div> 
         <div id="countdownDiv"><h4><span id="nemberInterval">3</span><span>秒後幫你自動轉換頁面~</span><span class="fas fa-music"></span></h4></div>
	       
           
	
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
	<script>
	$(document).ready(function () {

		 setInterval(function(){
			    var count = $("#nemberInterval").text();
		        if(count!=0){
		         	count --; 
		         	$("#nemberInterval").text(count);	
		        }
		   
		    },1000);
		    
	    window.setTimeout(function () {
	        location.href = ctx + "/pages/music/selectAllMsuicById.jsp";
	    }, 3000);
	    
	   
	});
	</script>
	
</body>
</html>