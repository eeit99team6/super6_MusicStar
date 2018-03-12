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

	// Scrolly.
	if ($(".scrolly").length) {

		$('.scrolly').scrolly();
	}
});