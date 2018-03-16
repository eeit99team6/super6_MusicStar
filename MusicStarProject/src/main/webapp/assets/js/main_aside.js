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

	audioPlaylist = new jPlayerPlaylist(cssSelector, playlist, options);

	$("body").on("click", "#jp_toggle", function() {
		$("#jp_player_list").toggleClass("jp-hide");
		$(this).toggleClass("fa-chevron-down").toggleClass("fa-chevron-up");
	});
	
	addAndPlayMusic = function (musicName,playerId,musicLink,musicPhoto){
		audioPlaylist.add({
			title:musicName,
			artist:playerId,
			mp3:musicLink,
			poster:musicPhoto
		});
		audioPlaylist.play(-1);
		if($("#jp_player_list").hasClass("jp-hide")){
			$("#jp_player_list").removeClass("jp-hide");
			$("#jp_toggle").addClass("fa-chevron-down").removeClass("fa-chevron-up");			
		}
	}
});