<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Music Star Index</title>
<jsp:include page="/backend/include/css_js_include.jsp" />


<link href="${ctx}/assets/css/buttons.css" rel="stylesheet" />
<link href="${ctx}/assets/css/slideshowctrl.css" rel="stylesheet" />
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/slideshowctrl.js"></script>

<link type="text/css" rel="stylesheet" href="${ctx}/backend/css/jquery-ui.structure.css">
<link type="text/css" rel="stylesheet" href="${ctx}/backend/css/jquery-ui.theme.css">
<link type="text/css" rel="stylesheet" href="${ctx}/backend/css/jquery-ui.css">
<style>
</style>
</head>
<body>
	<!-- Navigation-- jsp include -->
	<jsp:include page="/backend/include/nav.jsp" />
	
      
 <div class="content-wrapper">
    <div class="container-fluid">   
   <ol class="breadcrumb" style="margin-top:60px;">
        <li class="breadcrumb-item">
          <a href="#">Music Star</a>
        </li>
        <li class="breadcrumb-item active">輪播牆後台</li>
      </ol>
<div class="row">
 	<div id="main_container" class="container card">
		<div class="row text-center slideshow_control_container">
			<div class="col-md-6">
				<div id="slideshow_control_insert" class="slideshow_control_btn button button-glow button-rounded button-highlight">新增輪播圖</div>
			</div>
			<div class="col-md-6">
				<div id="slideshow_control_update" class="slideshow_control_btn button button-glow button-rounded button-highlight">修改輪播圖</div>
			</div>
			<div class="col-md-6">
				<div id="slideshow_control_changeorder" class="slideshow_control_btn button button-glow button-rounded button-highlight">調整順序</div>
			</div>
			<div class="col-md-6">
				<div id="slideshow_control_delete" class="slideshow_control_btn button button-glow button-rounded button-highlight">刪除輪播圖</div>
			</div>
		</div>	
		<!-- Modal -->
		<div class="modal fade" id="slideshow_modify_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true" data-backdrop="static">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="slideshow_modify_modal_title">輪播圖修改</h5>
		      </div>
		      <div class="modal-body">
				<fieldset>
					<form id="slideshow_modify_form" enctype="multipart/form-data">
						<div class="form-group">
							<label for="slide_photo">輪播圖:</label><span id="slidePhotoError" class="text-danger"></span>
							<label for="slide_photo" id="slide_photo_box" data-instruction="" class="form-control slide_photo_box">
								<input type="file" id="slide_photo" name="slidePhoto" accept="image/*"/>
								<span id="slide_photo_description" class="">目前沒有圖片</span>
							</label>
						</div>
						<div class="form-group">
							<label for="slide_name">輪播圖名稱:</label><span id="slideNameError" class="text-danger"></span>
							<input type="text" id="slide_name" name="slideName" class="form-control">
						</div>
						<div class="form-group">
							<label for="slide_link">輪播圖連結:</label><span id="slideLinkError" class="text-danger"></span>
							<input type="text" id="slide_link" name="slideLink" class="form-control">
						</div>
						<div class="form-group">
							<label for="slide_description">輪播圖敘述:</label><span id="slideDescriptionError" class="text-danger"></span>
							<textarea rows="5" id="slide_description" name="slideDescription" class="form-control"></textarea>
						</div>
						<input type="hidden" id="silde_id" name="slideId"/>
					</form>
				</fieldset>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
		        <button type="button" id="slide_modify_confirm" class="btn btn-primary">保存更變</button>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="modal fade" id="slideshow_select_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		  <div class="modal-dialog modal-sm" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="slideshow_select_modal_title">選擇輪播圖</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
				<fieldset>
					<ul id="slide_list" class="list-group list-group-flush"></ul>
				</fieldset>	
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
		        <button type="button" id="slide_select_confirm" class="btn btn-primary">選擇</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
  </div>
 </div>  
</div>

</body>
</html>