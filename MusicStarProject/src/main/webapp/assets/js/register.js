$(function() { 
	// Register Ajax
	var $registerForm = $("#register_form");
	$registerForm.submit(function(e) {
		e.preventDefault();
		var formData = $registerForm.serialize(), action = $registerForm
				.attr("action");

		$.post(action, formData, function(data) {
			if (data.success) {
				alert(data.success.message);
				location.href = ctx + "/index.jsp";
			} else {
				$("#idError").html(data.error.idError || "");
				$("#nameError").html(data.error.nameError || "");
				$("#pwdError").html(data.error.pwdError || "");
				$("#emailError").html(data.error.emailError || "");
				$("#genderError").html(data.error.genderError || "");
				$("#errorMsg").html(data.error.message || "");
			}
		});
	});
});