var ctx = "/" + window.location.pathname.split("/")[1];

$(function() {

	var $window = $(window),
		$body = $('body');

	// Disable animations/transitions until the page has loaded.
	$body.addClass('is-loading');

	window.setTimeout(function() {
		$body.removeClass('is-loading');
	}, 3000);

	$window.on('load', function() {
		$body.removeClass('is-loading');
	});
	
	// scroll bar
	$('.scrollbar-dynamic').scrollbar();

});