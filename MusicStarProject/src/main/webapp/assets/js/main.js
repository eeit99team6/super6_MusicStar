<<<<<<< HEAD
var ctx = "/" + window.location.pathname.split("/")[1];

$(function() {

	var $window = $(window),
		$body = $('body');

	// Disable animations/transitions until the page has loaded.
	$body.addClass('is-loading');

	$window.on('load', function() {
		window.setTimeout(function() {
			$body.removeClass('is-loading');
		}, 100);
	});

=======
$(function() { 
	
    // Disable animations/transitions until the page has loaded.
    $body.addClass('is-loading');

    $window.on('load', function () {
        window.setTimeout(function () {
            $body.removeClass('is-loading');
        }, 100);
    });
	
>>>>>>> branch 'master' of https://github.com/eeit99team6/super6_MusicStar.git
	// Scrolly.
	if ($(".scrolly").length) {

		$('.scrolly').scrolly();
	}
});