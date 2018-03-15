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
font-weight:bold;
}

.jpPlayerContainer{
margin:10px 20px 5px 0px;
}

.outSide{
text-align:center;
position:relative;
overflow: hidden;
padding:1px;
}

.jcontainer{
text-align:center;
position: relative;
margin:0px 0px 0px 5px;
position:relative;
overflow: hidden;
}

.imgDiv{
text-align:center;
margin:2px; 
position:relative;
overflow: hidden;
}

.imgDiv img{
border:2px solid black;
border-radius:10px;
border-radius: 50%;
padding:10px;
width:300px;
}

.rotateClass{
-webkit-animation: rotation 2s infinite linear;
}


@-webkit-keyframes rotation {
		from {
				-webkit-transform: rotate(0deg);
		}
		to {
				-webkit-transform: rotate(359deg);
		}
}

.imgDiv img:hover{
/*  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);  */
}


#jqueryMain{
text-align:center;
}

.textDiv{
position:absolute;
background-color:black;
right:50%;
bottom:10%;
color:snow;
font-weight:bold;
padding:2px;
border:1px dashed snow;
}

.centerDiv{
position:absolute;
right:45%;
bottom:45%;
width:35px;
height:35px;
background-color:snow;
border-radius:50%;
border:2px solid black;
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
				  var outSideDiv=$("<div></div>").addClass(" col-md-4 outSide");
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
                                               }).addClass("imgClass")
                                               
                  var textDiv = $("<div></div>").text(item.music_name).addClass("textDiv");
                  var centerDiv = $("<div></div>").addClass("centerDiv")
                  
                  divImg.append(imgTag)
                  divImg.append(textDiv)
                  divImg.append(centerDiv)
               
                  
			      jpDivContainer.append(linkPlay);
			      jpDivContainer.append(linkPause);
			      
			      divImg.append(jpDivContainer)
			      
			      jpPlayerContainer.append(jpDivPlayer);
			      
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
			
 			
 			$(".jcontainer").on("click","a",function(){
	               if(($(this).parents(".imgDiv").find(".imgClass").is(".rotateClass"))){
	            	   $(this).parents(".imgDiv").find(".imgClass").removeClass("rotateClass");
	               }else{
	            	   $(this).parents(".imgDiv").find(".imgClass").addClass("rotateClass");
	               };
 			})
		
			
		})

		});

	
	</script>
</body>
</html>