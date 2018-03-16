	/*Google Login */
	// Initializes the Sign-In client.   		  
	function initClient() {
		gapi.load('auth2', function() {
			 var auth2 = gapi.auth2.init({
			 client_id: '49274278323-nm9ajs8osl7l4gv8vji8r3l8au6go1ra.apps.googleusercontent.com'
            });
        // Attach the click handler to the sign-in button
        auth2.attachClickHandler('google_login_btn', {}, onSuccess, onFailure);
         });
    }
        
    // Handle successful sign-in  
    var onSuccess = function(user) {
		$("#ajax_mask").removeClass("ajax_hide");
        console.log('Signed in as ' + user.getBasicProfile().getName());
        var id_token = user.getAuthResponse().id_token;
		$.post(ctx + "/members/googleLoginAjax", {
			"idToken" : id_token
		}, function(data) {
			$("#ajax_mask").addClass("ajax_hide");	
			if (data.success) {
				location.href = data.success;
			}else {
				alert(data.errMsg);
			}
		});
     }
    
    // Handle sign-in failures.     
    var onFailure = function(error) {
    }
    /*Google Login End*/
    
    /*FB Login*/
	window.fbAsyncInit = function() {
		FB.init({
			appId : '153075598715933',
			cookie : true,
			xfbml : true,
			version : 'v2.12'
		});

		FB.AppEvents.logPageView();
		FB.getLoginStatus(function(response) {
		});
	};
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {
			return;
		}
		js = d.createElement(s);
		js.id = id;
		js.src = "https://connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	
	function fbLoginAjax(accessToken){
		$.post(ctx + "/members/fbLoginAjax", {
			"accessToken" : accessToken
		}, function(data) {
			$("#ajax_mask").addClass("ajax_hide");	
			if (data.success) {
				FB.logout();
				location.href = data.success;
			} else {
				alert(data.errMsg);
			}
		});
	}
    /*FB Login End*/
	
$(function() {
		var $header = $("#header"), 
		$system_buttom = $("ul.menu li:last-child>i"),
		$sub_menu = $("#sub_menu"), 
		$sub_menu_close = $("#sub_menu a"),
		$loginForm = $("#login_form"),
		$rememberme = $("#rememberme");

		/*Sub Menu*/
		$system_buttom.on("click", function() {
			$sub_menu.addClass("visible");
		});

		$sub_menu_close.on("click", function() {
			$sub_menu.removeClass("visible");
		});
		/*Sub Menu End*/

		// Login Ajax
		$loginForm.submit(function(e) {
			e.preventDefault();
			$("#ajax_mask").removeClass("ajax_hide");
			var formData = $loginForm.serialize(), action = $loginForm
					.attr("action"), $loginErr = $("#login_err");
			$loginErr.empty();
			$.post(action, formData, function(data) {
				$("#ajax_mask").addClass("ajax_hide");	
				if (data.success) {
					location.href = data.success;
				} else {
					$loginErr.html(data.errMsg);
				}
			});
		});
		
		if($rememberme.prop("checked")){
			$("#mbrId").one("keyup",function(){
				$("#mbrPwd").val("");
				$rememberme.prop("checked",false);
			});
		}
		
		//FB Login
		$("#fb_login_btn").click(function() {
			FB.login(function(response) {
				if (response.status === 'connected') {
					$("#ajax_mask").removeClass("ajax_hide");
					fbLoginAjax(response.authResponse.accessToken);
				}
			}, {
				scope : 'public_profile,email'
			});
		});
		
	});