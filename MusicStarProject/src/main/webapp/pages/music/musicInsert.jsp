<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Default Page</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/music-upload-muisc/site.css" rel="stylesheet" />
<link href="${ctx}/assets/css/common_style/allPages.css" rel="stylesheet"/>
<link href="${ctx}/assets/css/musicInsert.css" rel="stylesheet"/>


<script src="${ctx}/assets/js/music-upload-muisc/site.js"> </script>

<style type="text/css">
</style>
<jsp:include page="/includes/main_js.jsp" />
<script>
	
</script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
	
	 
	<div class="title-title">上傳音樂</div>	 
	<div id="formContainer">
	<form method="POST" action="<c:url value='/musicInsert'/>" enctype="multipart/form-data">
	<label for="music_member_id">會員ID:</label><input name="music_member_id" type="text" value="${loginOK.mbrId}" class="col-xl-4" required readonly>
    <label for="music_name">音樂名稱:</label><input name="music_name" type="text" class="col-xl-4" required>
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
    
<!--<input id="music_photo" name="music_photo" type="file"> -->
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
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	
	<script>
	
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