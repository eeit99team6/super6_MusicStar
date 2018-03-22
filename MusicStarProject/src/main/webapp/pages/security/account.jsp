<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員資料</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
.scrollspy_container::-webkit-scrollbar {display:none}
.contorl_box::-webkit-scrollbar {display:none}

.title-title {
	margin-bottom: 20px;
}

.scrollspy_container {
	height: 62vh; 
 	overflow-y: scroll; 
	position: relative;
	user-select:none;
}

.contorl_box{
	height: calc(62vh - 36px);
	overflow-y: scroll; 
}

.contorl_box table,
.contorl_box tbody{
width:100%;
}
.contorl_box tr{
width:100%;
line-height: 40px;
}

.contorl_box th{
width:50%;
}

.editable{
cursor: pointer;
}
.editable:active{
background-color: rgba(0,0,0,0.1)
}

.image_box{
width:60px;
height:60px;
background-size:contain;
overflow: hidden;
}

.file_hide{
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    outline: none;
    opacity: 0;
    cursor: pointer;
}


</style>
<jsp:include page="/includes/main_js.jsp" />
<script>
	$(function(){
		var $ajax_mask = $("#ajax_mask"),
			$change_pwd_form = $("#change_pwd_form"),
			$mbr_old_pwd = $("#mbr_old_pwd"),
			$mbr_new_pwd = $("#mbr_new_pwd"),
			$check_new_pwd = $("#check_new_pwd"),
			$mbr_old_pwd_err = $("#mbr_old_pwd_err"),
			$mbr_new_pwd_err = $("#mbr_new_pwd_err"),
			$check_new_pwd_err = $("#check_new_pwd_err"),
			$change_name_modal = $("#change_name_modal"),
			$change_photo_modal = $("#change_photo_modal"),
			$change_gender_modal = $("#change_gender_modal"),
			$new_name = $("#new_name"),
			$new_photo = $("#new_photo"),
			$new_photo_form = $("#new_photo_form"),
			$new_gender = $("#new_gender"),
			$change_name_btn = $("#change_name_btn");
			$change_photo_btn = $("#change_photo_btn");
			$change_gender_btn = $("#change_gender_btn");
			
		// change name
		$change_name_btn.click(function(e){
			var newName = $new_name.val();
			if(newName && newName !== ""){
				$.post(ctx+"/members/update/name",{"newName":newName},function(data){
					if(data.success){
						alert(data.success);			
						$change_name_modal.modal("hide");
						location.reload();
					}else{
						alert(data.errMsg);
					}
				});
			}else{
				alert("名稱不可為空白");
			}
			});
		// change photo
		$change_photo_btn.click(function(e){
			var photoData = new FormData($new_photo_form[0]);
			if(photoData){			
				$.ajax({
					type : 'POST',
					url : ctx+"/members/update/photo",
					data : photoData,
					contentType : false,
					cache : false,
					processData : false,
					success : function(data) {
						$("#ajax_mask").addClass("ajax_hide");				
							if(data.success){							
							alert(data.success);			
							$change_photo_modal.modal("hide");
							location.reload();
							}else{
							alert(data.errMsg);
							}					
					}		
				});
			}else{
				alert("必須上傳圖片!");
			}
		});
		// change gender
		$change_gender_btn.click(function(e){
			var newGender = $new_gender.val();
			if(newGender && newGender !== ""){
				$.post(ctx+"/members/update/gender",{"newGender":newGender},function(data){
					if(data.success){
						alert(data.success);			
						$change_gender_modal.modal("hide");
						location.reload();
					}else{
						alert(data.errMsg);
					}
				});
			}else{
				alert("必須選擇性別");
			}
			$change_gender_modal.modal("hide");
			});
		
		/* change password */
		$mbr_new_pwd.blur(function(e){	
			if($check_new_pwd.val() !== $mbr_new_pwd.val() && $check_new_pwd.val() !== ""){
				$check_new_pwd_err.html("密碼不相符");
			}else{
				$check_new_pwd_err.html("");
			}		
		});
		$check_new_pwd.blur(function(e){	
			if($check_new_pwd.val() !== $mbr_new_pwd.val() && $check_new_pwd.val() != null){
				$check_new_pwd_err.html("密碼不相符");
			}else{
				$check_new_pwd_err.html("");
			}		
		});
		
		$change_pwd_form.submit(function(e) {
			e.preventDefault();
			var formData = new FormData($change_pwd_form[0])
			, action = $change_pwd_form.attr("action");

			if($check_new_pwd.val() !== $mbr_new_pwd.val()){
				$check_new_pwd_err.html("密碼不相符");
				return false;
			}else{
				$check_new_pwd_err.html("");
			}	
			
			$.ajax({
				type : 'POST',
				url : action,
				data : formData,
				contentType : false,
				cache : false,
				processData : false,
				success : function(data) {
					$ajax_mask.addClass("ajax_hide");
					setTimeout(function(){
					if (data.success) {
						$change_pwd_form[0].reset();
						$mbr_old_pwd_err.html("");
						$mbr_new_pwd_err.html("");
						$check_new_pwd_err.html("");
						alert(data.success);
					} else {
						$mbr_old_pwd_err.html(data.oldPwdError || "");
						$mbr_new_pwd_err.html(data.newPwdError || "");
						if(data.errMsg){
							alert(data.errMsg);
						}
					}},100);			
				},
				beforeSend : function(){$ajax_mask.removeClass("ajax_hide");}
			});		
		});
		/* change password end*/
	});
</script>
</head>
<body>
	<div class="wrapper scrollbar-dynamic">
		<jsp:include page="/includes/main_header.jsp" />
		<!-- main_container start -->
		<div id="main_container" class="container-fuild">
		<h2 class="title-title">會員資料</h2>
			<div class="container">
				<div class="row">
					<div class="col-md-4">
						<div id="control_list" class="list-group">
							<a class="list-group-item list-group-item-action" href="#control_1">個人資料查看與修改</a>
							<a class="list-group-item list-group-item-action" href="#control_2">重設個人密碼</a>														
						</div>
					</div>
					<div class="col-md-8">
						<div data-spy="scroll" data-target="#control_list" data-offset="0" class="scrollspy_container">
							<h3 id="control_1" class="contorl_title">個人資料查看與修改：</h3>
							<div class="contorl_box">
								<table>
									<tbody>						
										<tr title="此項目僅供查看" class="list-group-item"><th>會員帳號</th><th>${loginOK.mbrId}</th><td><i class="fas fa-eye"></i></td></tr>
										<tr title="修改名稱" class="list-group-item editable" data-toggle="modal" data-target="#change_name_modal"><th>名 稱</th><th>${loginOK.mbrName}</th><td><i class="far fa-edit"></i></td></tr>
										<tr title="修改頭像" class="list-group-item editable" data-toggle="modal" data-target="#change_photo_modal"><th>頭 像</th><th>
										<c:choose>
										<c:when test="${loginOK.mbrPhoto eq null}">
											<div class="image_box" style="background-image: url('${ctx}/assets/img/profile/init_profile.png')"></div>
										</c:when>
										<c:otherwise>
											<div class="image_box" style="background-image: url('${loginOK.mbrPhoto}')"></div>
										</c:otherwise>
										</c:choose>
										</th><td><i class="far fa-edit"></i></td></tr>
										<tr title="修改性別" class="list-group-item editable" data-toggle="modal" data-target="#change_gender_modal"><th>性 別</th><th>									
										<c:choose>
										<c:when test="${fn:containsIgnoreCase(loginOK.mbrGender,'m')}">男性</c:when>
										<c:when test="${fn:containsIgnoreCase(loginOK.mbrGender,'f')}">女性</c:when>
										<c:otherwise>其他</c:otherwise>
										</c:choose>
										</th><td><i class="far fa-edit"></i></td></tr>
										<tr title="此項目僅供查看" class="list-group-item"><th>電子郵件</th><th>${loginOK.mbrEmail}</th><td><i class="fas fa-eye"></i></td></tr>
										<tr title="此項目僅供查看" class="list-group-item"><th>註冊日期</th><th>${fn:substring(loginOK.mbrRegisterDate,0,10)}</th><td><i class="fas fa-eye"></i></td></tr>
									</tbody>
								</table>
							</div>
							<hr>
							<h3 id="control_2" class="contorl_title">重設個人密碼：</h3>
							<div class="contorl_box">
								<form id="change_pwd_form" action="<c:url value="/members/update/pwd"/>" method="post">
								<table>
								<tbody>
								<c:if test="${not empty loginOK.mbrPwd}">
									<tr title="請輸入原密碼" class="list-group-item"><th>請輸入原密碼</th><th><input type="password" class="form-control" id="mbr_old_pwd" name="mbrOldPwd" required="required"/><span id="mbr_old_pwd_err" class="text-danger"></span></th></tr>
								</c:if>
								<tr title="請輸入新密碼" class="list-group-item"><th>請輸入新密碼</th><th><input type="password" class="form-control" id="mbr_new_pwd" name="mbrNewPwd" required="required"/><span id="mbr_new_pwd_err" class="text-danger"></span></th></tr>
								<tr title="請確認新密碼" class="list-group-item"><th>請確認新密碼</th><th><input type="password" class="form-control" id="check_new_pwd" required="required"/><span id="check_new_pwd_err" class="text-danger"></span></th></tr>
								<tr title="重設密碼" class="list-group-item"><th></th><th></th><td><input type="submit" value="重設密碼" class="form-control"/></td></tr>
								</tbody>
								</table>
								</form>
							</div>							
						</div>
					</div>
				</div>
			</div>
			<!-- change name modal -->
			<div class="modal fade" id="change_name_modal" tabindex="-1" role="dialog" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">修改名稱</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <label for="new_name">請輸入您的新名稱：</label>
			        <input type="text" id="new_name" class="form-control" value="${loginOK.mbrName}">
			      </div>
			      <div class="modal-footer">			        
			        <button type="button" class="btn btn-primary" id="change_name_btn">修改名稱</button>
			      </div>
			    </div>
			  </div>
			</div>
			<!-- change photo modal -->
			<div class="modal fade" id="change_photo_modal" tabindex="-1" role="dialog" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">修改頭像</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      <form id="new_photo_form" enctype="multipart/form-data">
			        <label for="new_photo">請上傳您的新頭像：</label>
			        <input type="file" id="new_photo" name="newPhoto" class="form-control"/>
			      </form>
			      </div>
			      <div class="modal-footer">			       
			        <button type="button" class="btn btn-primary" id="change_photo_btn">修改頭像</button>
			      </div>
			    </div>
			  </div>
			</div>
			<!-- change gender modal -->
			<div class="modal fade" id="change_gender_modal" tabindex="-1" role="dialog" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">修改性別</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <label for="new_gender">請選擇您的性別：</label>
			        <select id="new_gender" class="form-control">
			        <option value="m">男</option>
			        <option value="f">女</option>
			        <option value="o">其他</option>
			        </select>
			      </div>
			      <div class="modal-footer">			       
			        <button type="button" class="btn btn-primary" id="change_gender_btn">確認選擇</button>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
		<!-- main_container end -->
		<jsp:include page="/includes/main_aside.jsp" />
		<jsp:include page="/includes/main_footer.jsp" />
	</div>
</body>
</html>