<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>賽事報名</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/music-contest-signup/music-singup-bootstrap.min.css" rel="stylesheet" />
<link href="${ctx}/assets/css/music-contest-signup/music-singup-dataTables.bootstrap4.css" rel="stylesheet" />
<link href="${ctx}/assets/css/music-contest-signup/music-singup-font-awesome.min.css" rel="stylesheet" />
<link href="${ctx}/assets/css/music-contest-signup/music-singup-sb-admin.css" rel="stylesheet" />
<style type="text/css">

.title{
margin-top:5%;
text-align: center;
font-size:300%;
font-weight:bold;
}

.divImagContainer{
    position: relative;
    text-align: center;
    color: white;
    opacity:1;
    margin: 5% 20% 0% 20%;
    border-radius:30%;
}

#music-contest-sing-up-container{
margin:0 auto;
}

.divImagContainer:hover{
    opacity:0.7;
}

.text-block {
    position: absolute;
    bottom: 20px;
    right: 20px;
    background-color: black;
    color: white;
    padding-left: 20px;
    padding-right: 20px;
    border: solid 3px snow;
    cursor: pointer;
}

.text-block:hover{
  box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
}

.musicImgSan{
border:solid 1px black;
}

label{
font-weight: bold;
}


</style>
<jsp:include page="/includes/main_js.jsp" />
<script>
	
</script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="content">
	 
	<div id="main_container" class="container-fuid">
	 
	 <div class="title">報名中</div>
	 <div class="row">
	  <div id="music-contest-sing-up-container" class="card columns">
	  
     </div>
	  </div>
	</div>
<!--  =====================  沒有 login =============================== -->
<c:if test="${empty loginOK }">
<div id="creatForSure" class="modal fade" >
<div class="modal-dialog" role="document">
<div class="modal-content">
  <div class="modal-header">
   <p> Hi 訪客</p>
  </div> 
  
  <div class="modal-body">
     <P>請先登入會員</p>
     <a data-toggle="modal" data-target="#login_box"><button id="noLoginButton"  type="button"  data-dismiss="modal">確定</button></a>
 </div>
</div>
</div>
</div>

</c:if>

<!--  =====================  有 login  =============================== -->

<c:if test="${not empty loginOK}">


<div id="creatForSure" class="modal fade" >
<div class="modal-dialog" role="document">
<div class="modal-content">
  <div class="modal-header">
   <p>Hi ${loginOK.mbrId}</p>
  </div> 
  
  <div class="modal-body" id="borderIfo">
     <P>請先確認您已上傳此項賽事之音樂</p>
     <a data-toggle="modal" data-target="#creatContest"><button id="creatContestButton" type="button" data-dismiss="modal" >確定</button></a>
 </div>
</div>
</div>
</div>

</c:if>


<!--  =====================  modal fade for sign up music contest=============================== -->

<div id="creatContest" class="modal fade" >
<div class="modal-dialog" role="document">               
  <form class="modal-content" method="POST" enctype="multipart/form-data" action="<c:url value='/insertPlayer'/>">
      <div class="modal-header">
          <img src="${ctx}/assets/img/pagesUsage/music-star.png" width="50"><h3 class="modal-title" id="exampleModalLabel">Music Star</h3>
         <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
      </div>

    <div class="modal-body">
         <label for="music_contest_id">音樂比賽名稱:</label>
         <select name="music_contest_id" id="music_contest_id" class="col-xl-12">
         </select>
    </div>  
    
    <div class="modal-body">
      <label for="music_contest_player_id">會員:</label>
      <input type="text" placeholder="music_contest_player_id" name="music_contest_player_id" id="music_contest_player_id" class="col-xl-12" value="${loginOK.mbrId}" readonly/>
    </div>  
     
      <div class="modal-body" id="music_id_div">
      <label for="music_id">您的音樂:</label>
      <input style="display:none" type="text" placeholder="music_id" name="music_id" id="music_id" class="col-xl-6" readonly>
      </div> 
      
       <div class="modal-footer col-xl-12">
      <button type="submit" class="col-xl-12">送出</button>
     </div> 
      
       </div>
    </form>
    </div>  	
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
	<script>
	$(document).ready(function(){
		
		$.getJSON("musicContesetSignUpAjax", {}, function(json_data){
			  
			$.each(json_data,function(index,item){
			 
			   var cellImg = $("<img/>").attr({
				                 src: item.music_contest_photo,
			                     title: item.music_contest_name,
                                 alt: item.music_contest_name,
                                 name: item.music_contest_id
			                     })
                                 .addClass("card-img img-fluid w-100"); 
		
			  
			  var div = $("<div></div>").addClass("card divImagContainer ")
			                            .attr({
			                            id:"div" + item.music_contest_id,
			                            });
			  
			  var divInside = $("<div></div>").addClass("text-block")
			                                  .attr({
			                                   id:item.music_contest_id,
			                                  });
			  var link = $("<a></a>").attr({
				   'data-toggle': "modal",
				   'data-target': "#creatForSure"
			   });
			  
			  var h4 = $("<h4></h4>").text(item.music_contest_name)
			  var p = $("<p></p>").text()
			  
			  divInside.append(h4)
			  link.append(divInside)
			  div.append(cellImg)
			  div.append(link)
		  	 $("#music-contest-sing-up-container").append(div)
		  	
			})

/* 抓取動態產生的 text-block 拿到 偷塞的 music_contest_id 和  會員 id 透過Ajax檢察 此會員是否已經報名過此項比賽  
 此方法必須在 上方的.getjson內才得以執行*/
		     
				$(".text-block").on("click",function(){
				   var musicContestId = $(this).attr("id");
				   var memeberTemp = $(".modal-header").children("p").text();
				   var memeberId = memeberTemp.substr(3);
				   
// Ajax 判斷是否已經報名過此比賽
					$.get("checkedPlayerAjax",{"music_contest_id":musicContestId,"music_contest_player_id":memeberId},function(countForSingup){
						
						if(countForSingup==0){
				            $("#borderIfo").children("p").text("請先確認您已上傳此項賽事之音樂");
							$("#creatContestButton").show();
						}else{
							$("#borderIfo").children("p").text("您已經報名過了");
						    $("#creatContestButton").hide();
						}
						
				   });
				});
		});
		
		$("#creatContestButton").on("click",function(){
			
			
			$.getJSON("musicContesetSignUpAjax", {}, function(json_data){
				  
				$.each(json_data,function(index,item){
					 var option = $("<option></option>").val(item.music_contest_id);
					 
					 option.text(item.music_contest_name).addClass("col-xl-12");
					
					 $("#music_contest_id").append(option);
				  	 });	
				});
		});
			
		// 送 Ajax 到   後端 撈取 該參賽者的 音樂資料  回送到 creatContest 的 form 中
		$.getJSON("musicSelectByIdAjax",{},function(jason_data){
			
			$(".spanBack").empty();
		   // music_id_div
			$.each(jason_data,function(index,item){
				var span = $("<sapn></span>").addClass("pl-2 spanBack");
				var musicImg = $("<img/>").attr({
					                       src: item.music_photo,
				                           title: item.music_name + ".mp3",
				                           width: "10%",
				                           height: "10%",
				                           value: item.music_id
			                       	       })
				                           .addClass("musicImgSan");
				span.append(musicImg)
				$("#music_id_div label").after(span);
				
				$("#music_id").val(item.music_id);
				
		     	});
		   
		   
			  $(".musicImgSan").on("click",function(){
				  var musicId= $(this).attr("value");
				  var musicName = $(this).attr("title");
				  $("#music_id").val(musicId);
				  alert("您即將選取 " + musicName +" 為比賽歌曲");
			      })
		        });
	
	    	});


		

	
	</script>
</body>
</html>