<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SelectAllMusic</title>

<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">

body{
position:relative;
}

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
border:1px solid black;
border-radius:10px;
border-radius: 50%;
padding:5px;
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
right:45.5%;
bottom:46.5%;
width:35px;
height:35px;
background-color:snow;
border-radius:50%;
border:1px solid black;
}

.musicEditor{
position:fixed;
right:65px;
bottom:30%;
font-size:300%;
}

.musicEditoButton{
font-size:35px;
}

.musicEditoButton .hoverText{
   
    visibility: hidden;
    width:;
    background-color: black;
    color: #fff;
    text-align: center;
    padding: 5px;
    font-size:15px;
    font-weight:bold;

    /* Position  musicEditoButton*/
    position: absolute;
    Y-index: 5;
}

.musicEditoButton:hover .hoverText{
visibility:visible;
}


.file-upload {
  background-color: #ffffff;
  width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.file-upload-btn {
  width: 100%;
  margin: 0;
  color: #fff;
  background: #1FB264;
  border: none;
  padding: 10px;
  border-radius: 4px;
  border-bottom: 4px solid #15824B;
  transition: all .2s ease;
  outline: none;
  text-transform: uppercase;
  font-weight: 700;
}

.file-upload-btn:hover {
  background: #C0C0C0;
  color: #C0C0C0;
  transition: all .2s ease;
  cursor: pointer;
}

.file-upload-btn:active {
  border: 0;
  transition: all .2s ease;
}

.file-upload-content {
  display: none;
  text-align: center;
}

.file-upload-input {
  position: absolute;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  outline: none;
  opacity: 0;
  cursor: pointer;
}

.image-upload-wrap {
  margin-top: 5px;
  border: 4px dashed #E0E0E0;
  position: relative;
}

.image-dropping,
.image-upload-wrap:hover {
  background-color: #C0C0C0;
  border: 4px dashed #ffffff;
}

.image-title-wrap {
  padding: 0 15px 15px 15px;
  color: #222;
}

.drag-text {
  text-align: center;
}

.drag-text h3 {
  font-weight: 100;
  text-transform: uppercase;
  color: black;
  padding: 60px 0;
}

.file-upload-image {
  max-height: 200px;
  max-width: 200px;
  margin: auto;
  padding: 20px;
}

.remove-image {
  width: 200px;
  margin: 0;
  color: #fff;
  background: #C0C0C0;
  border: none;
  padding: 10px;
  border-radius: 4px;
  border-bottom: 4px solid #C0C0C0;
  transition: all .2s ease;
  outline: none;
  text-transform: uppercase;
  font-weight: 700;
}

.remove-image:hover {
  background: #E0E0E0;
  color: #ffffff;
  transition: all .2s ease;
  cursor: pointer;
}

.remove-image:active {
  border: 0;
  transition: all .2s ease;
}

h6{
font-size:65%;
}

form{
margin-bottom:10px;
border:.5px solid  #C0C0C0;
padding:8px;
}

form button{
margin-top:5px;
}

#buttonDiv{
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
<!-- ================================== 音樂CD 區===============================================   -->
<div class="container containerMain">
  <div id="jqueryMain" class="row mx-auto text-center"> 
  </div>
</div>

<!-- =====================================================================================================   -->
</div>
	 
	 
<!-- ======================================== Music 修改 button ==============================================   -->	
	
	 <div class="musicEditor"><button class="musicEditoButton btn btn-warning" data-toggle="modal" data-target="#creatUpdatemusic"><span class="hoverText">點我修改音樂</span><i class="fas fa-music"></i></button></div>
	
	
<!-- ======================== Music 修改 button <<modal fade>> =====================================================================================   -->
	
	<div id="creatUpdatemusic" class="modal fade" >
<div class="modal-dialog" role="document">  

<div class="modal-content">

 <form class="modal-content"  method="POST" action="<c:url value='/musicupdate'/>" enctype="multipart/form-data">
 
       <div class="modal-header">
          <img src="${ctx}/assets/img/pagesUsage/music-star.png" width="50"><h3 class="modal-title" id="exampleModalLabel">Music Star 音樂修改</h3>
         <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
      </div>
    <input name="music_member_id" type="text" value="${loginOK.mbrId}" class="col-xl-4" required hidden readonly>
	             
	<label for="music_id">修改音樂:</label>
    <select name="music_id" class="col-xl-5 musicNameSelect" required>
    </select>
    
    <label for="music_name">音樂名稱:</label>
    <input name="music_name" class="col-xl-4" type="text" required />
    
<!-- ============================================ music_link  ================================================= -->
          
          <div>
           <label for="music_link">音樂檔案:</label><input name="music_link" type="file" id="musicStar" class="col-xl-12" accept="audio/*" required>
          <audio id="musicStarAudio" controls>
             <source src="" id="musicStarSong" />
          </audio>
         <h6> &lt;&lt; 將歌曲發佈到  MusicStar 請保證為原汁原味個人產品，MusicStar有絕對權力使用您的創作。 &gt;&gt;</h6>
          </div>
          
<!-- ============================================ music_link  ================================================= -->
   
    
<!-- ============================================ music_photo  ================================================= -->
    
    <label for="music_photo">音樂封面:</label><div class="image-upload-wrap">
     <input id="music_photo"  class="file-upload-input" type='file' name="music_photo" onchange="readURL(this);" accept="image/*" required />
      <div class="drag-text">
        <h3>請選擇音樂封面</h3>
      </div>
    </div>
    <div class="file-upload-content">
      <img class="file-upload-image" src="#" alt="your image" />
       <div class="image-title-wrap">
        <button type="button" onclick="removeUpload()" class="remove-image">取消 <span class="image-title">Uploaded Image</span></button>
       </div>
    </div>
    
<!-- ============================================ music_photo  ================================================= -->
    
    
    <label for="music_description">歌曲簡介:</label> <input name="music_description" type="text" class="col-xl-4" >
    <label for="music_lyrics">歌詞:</label><input name="music_lyrics" type="text" class="col-xl-4" >
    <label for="music_style_id">樂風:</label>
    <select name="music_style_id" class="col-xl-4" style="display:block" required>
        <option value="1">Rock</option>
        <option value="2">Jazz</option>
        <option value="3">Blue</option>
        <option value="4">Country</option>
        <option value="5">Pop</option>
        <option value="6">Electronic</option>
        <option value="7">Flok</option>
        <option value="8">Alternative</option>
        <option value="9">Punk</option>
        <option value="10">Classical</option>
        <option value="11">Religion</option>
        <option value="12">Independent</option>
      </select>
	<div id="buttonDiv"><button type="submit">確認</button></div>
	</form>
</div>
</div>
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
			
			
/*======================================================  音樂更(update)JS  =============================================================*/		
		
		$(".musicEditoButton").click(function(){
			
			$(".musicNameSelect").empty();
			$.getJSON( ctx +"/pages/musicSelectByIdAjax",{},function(jason_data){
				var docFrag = $(document.createDocumentFragment());
				$.each(jason_data,function(index,item){
					var musicNameOption = $("<option></option>").text(item.music_name);
					var musicId = item.music_id;
					    musicNameOption.val(musicId);
					$(".musicNameSelect").append(musicNameOption);
				});
			});
		});
			
/*======================================================  照片上傳專用JS  =============================================================*/		
			
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

	
	function readURL(input) {
		  if (input.files && input.files[0]) {

		    var reader = new FileReader();

		    reader.onload = function(e) {
		      $('.image-upload-wrap').hide();

		      $('.file-upload-image').attr('src', e.target.result);
		      $('.file-upload-content').show();

		      $('.image-title').html(input.files[0].name);
		    };

		    reader.readAsDataURL(input.files[0]);

		  } else {
		    removeUpload();
		  }
		}

		function removeUpload() {
		  $('.file-upload-input').replaceWith($('.file-upload-input').clone());
		  $('.file-upload-content').hide();
		  $('.image-upload-wrap').show();
		}
		$('.image-upload-wrap').bind('dragover', function () {
		        $('.image-upload-wrap').addClass('image-dropping');
		    });
		    $('.image-upload-wrap').bind('dragleave', function () {
		        $('.image-upload-wrap').removeClass('image-dropping');
		});
		    


		document.getElementById("musicStar").addEventListener("change", handleFiles, false);

		function handleFiles(event) {
		    	var files = event.target.files;
		    	$("#musicStarSong").attr("src", URL.createObjectURL(files[0]));
		    	document.getElementById("musicStarAudio").load();
		    };
	</script>
</body>
</html>