<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SelectAllMusic</title>
<link href="${ctx}/assets/css/common_style/allPages.css" rel="stylesheet"/>

<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">

.containerMain{
margin-top:50px;
margin-bottom:50px;
}

.jpPlayerContainer a{
text-decoration:none;
}

.jpPlayerContainer{
border:2px solid gray;
margin:10px 20px 5px 0px;
padding:20;
border-radius: 10px; 
wisth:10px;
background-color:lightcyan;
}

.jpPlayerContainer:hover{
box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.jcontainer{
text-align:left;
position: relative;
margin:0px 0px 0px 5px;
}

.imgDiv{
text-align:center;
display:block;
margin:5px;
border:2px solid #66B2FF;
border-radius:8px;
}

.imgDiv img{
border:1.5px solid #000080;
border-radius:3px;
margin:8px 20px 8px 8px;
}

.imgDiv in:hover{
box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

#jqueryMain{
text-align:center;
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
		 <div class="title-title"> 我的音樂 </div>
		 <!-- =========================================   -->
<div class="container containerMain">
  <div id="jqueryMain" class="row mx-auto text-center"> 
  </div>
</div>

	 	 <!-- =========================================   -->
	 </div>
	
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
		
	<script>
	$(document).ready(function(){
		
	
		
		$.getJSON( ctx +"/pages/musicSelectByIdAjax",{},function(jason_data){
			var docFrag = $(document.createDocumentFragment());
			var count = 1;
			$.each(jason_data,function(index,item){
				  var outSideDiv=$("<div></div>").addClass(" col-md-4 ");
				  var jpPlayerContainer = $("<div></div>").addClass("jpPlayerContainer");
				  var jpDivPlayer=$("<div></div>").attr({id:"jquery_jplayer_" + count})
				                                  
                  var jpDivContainer =$("<div></div>").attr({id:"jp_container_" + count}).addClass("jcontainer");
                  var linkPlay = $("<a></a>").addClass("jp-play fas fa-play-circle")
                                             .attr({ href:"#"})
                                             .text("Play");
                  var linkPause = $("<a></a>").addClass("jp-pause fas fa-pause-circle")
                                              .attr({ href:"#"})
                                              .text("Pause");
                  
                  var divImg = $("<div></div>").addClass("imgDiv")
                  var imgTag = $("<img></img>").attr({ 
                      src: item.music_photo,
                      width:80,
                      heigh:80,
                      })
                  divImg.append(imgTag).append(item.music_name)
                  
			      jpDivContainer.append(linkPlay);
			      jpDivContainer.append(linkPause);
			      jpPlayerContainer.append(jpDivPlayer);
			      jpPlayerContainer.append(jpDivContainer);
			      jpPlayerContainer.append(divImg);
			      outSideDiv.append(jpPlayerContainer);
			      docFrag.append(outSideDiv);
			      
                  $("#jqueryMain").append(docFrag);
                  jplayerGood(count,item.music_link);
	              count++;
			})
			
			function jplayerGood(fileId,source){
	  			jQuery("#jquery_jplayer_" + fileId).jPlayer( {
	  			    ready: function () {
	  			      jQuery(this).jPlayer("setMedia", {
	  				  mp3: source
	  			      });
	  			    },
	  			    cssSelectorAncestor: "#jp_container_" + fileId,
	  			    supplied: "mp3",
	  			    swfPath: ctx+"/assets/swf"
	  			  });
	  		return;
	  		}
			
//  			$(".jcontainer").on("click",function(){
// 	            if(($(this).next(".imgDiv").attr("data-click-state"))==1){
// 	            	$(this).next(".imgDiv").attr("data-click-state",0);
// 	                $(this).next(".imgDiv").css("display","none");
// 	            }else{
// 	               $(this).attr("data-click-state",1);	   
// 	               $(this).next(".imgDiv").css("display","block");
// 	            }
//  			})
 			
 			
 			$(".jcontainer").on("click",function(){
	          
	               if(($(this).next(".imgDiv").css("display"))=="none"){
	            	   $(this).next(".imgDiv").css("display","block");
	               }else{
	            	   $(this).next(".imgDiv").css("display","none");
	               };
 			})
		
			
		})

		});

	
	</script>
</body>
</html>