$(function() {
		var ctx = "/" + window.location.pathname.split("/")[1], 
		$header = $("#header"), 
		$system_buttom = $("ul.menu li:last-child>i"),
		$sub_menu = $("#sub_menu"), 
		$sub_menu_close = $("#sub_menu a"),
		$loginForm = $("#login_form");

		// Main Header
		$system_buttom.on("click", function() {
			$sub_menu.addClass("visible");
		});

		$sub_menu_close.on("click", function() {
			$sub_menu.removeClass("visible");
		});

		// Login Ajax
		$loginForm.submit(function(e) {
			e.preventDefault();
			var formData = $loginForm.serialize(), action = $loginForm
					.attr("action"), $loginErr = $("#login_err");
			$loginErr.empty();
			$.post(action, formData, function(data) {
				if (data.success) {
					location.href = data.success;
				} else {
					$loginErr.html(data.errMsg);
				}
			});
		});

	});