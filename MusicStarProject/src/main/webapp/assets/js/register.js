$(function() {
	// Register Ajax
	var $registerForm = $("#register_form"),
	$ajax_mask = $("#ajax_mask"),
	$mbrRegisterId = $("#mbrRegisterId"),
	$mbrRegisterPwd = $("#mbrRegisterPwd"),
	$mbrRegisterPwdCheck = $("#mbrRegisterPwdCheck"),
	$idError = $("#idError"),
	$nameError = $("#nameError"),
	$pwdError = $("#pwdError"),
	$pwdCheckError = $("#pwdCheckError"),
	$emailError = $("#emailError"),
	$genderError = $("#genderError"),
	$profileerror = $("#profileerror");
	
	$registerForm.submit(function(e) {
		e.preventDefault();
		var formData = new FormData($registerForm[0])
		, action = $registerForm.attr("action");

		$.ajax({
			type : 'POST',
			url : action,
			data : formData,
			contentType : false,
			cache : false,
			processData : false,
			success : function(data) {
				$ajax_mask.addClass("ajax_hide");
				setTimeout(function(){if (data.success) {
					alert(data.success.message);
					location.href = ctx + "/index.jsp";
				} else {
					$idError.html(data.error.idError || "");
					$nameError.html(data.error.nameError || "");
					$pwdError.html(data.error.pwdError || "");
					$pwdCheckError.html(data.error.pwdError || "");
					$emailError.html(data.error.emailError || "");
					$genderError.html(data.error.genderError || "");
					$profileerror.html(data.error.profileError || "");				
				}},100);			
			},
			beforeSend : function(){$ajax_mask.removeClass("ajax_hide");}
		});		
	});
	
	$mbrRegisterPwd.blur(function(){	
		if($mbrRegisterPwdCheck.val() !== $mbrRegisterPwd.val() && $mbrRegisterPwdCheck.val().trim().length > 0){
			$pwdCheckError.html("密碼不相符");
		}else{
			$pwdCheckError.html("");
		}		
	});
	$mbrRegisterPwdCheck.blur(function(){	
		if($mbrRegisterPwdCheck.val() !== $mbrRegisterPwd.val() && $mbrRegisterPwdCheck.val().trim().length > 0){
			$pwdCheckError.html("密碼不相符");
		}else{
			$pwdCheckError.html("");
		}		
	});
	
});