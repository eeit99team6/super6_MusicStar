$(function() {
	// audio player
	var cssSelector = {
		jPlayer : "#jplayer_main",
		cssSelectorAncestor : "#jplayer_container"
	};
	var playlist = [];
	var options = {
		playlistOptions : {
			autoPlay : false,
			enableRemoveControls : true,
			loopOnPrevious : true,
			shuffleOnLoop : true,
			displayTime : "slow",
			addTime : "fast",
			removeTime : "fast",
			shuffleTime : "slow"
		},
		swfPath : ctx + "/assets/swf",
		supplied : "mp3, m4a, m4v",
		wmode : "window",
		useStateClassSkin : true,
		autoBlur : true,
		smoothPlayBar : true,
		keyEnabled : false
	};

	var audioPlaylist = new jPlayerPlaylist(cssSelector, playlist, options);

	$("body").on("click", "#jp_toggle", function() {
		$("#jp_player_list").toggleClass("jp-hide");
		$(this).toggleClass("fa-chevron-down").toggleClass("fa-chevron-up");
	});
});