<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<aside id="main_aside">
	<div id="ajax_mask" class="ajax_hide">
		<div class="ajax_loding">
			<img src="${ctx}/assets/img/ajax/ajax-loading.gif" />
		</div>
	</div>
	<div id="jplayer_container row"
		class="jp-video jp-video-270p jp-container " role="application"
		aria-label="media player">
		<div>
			<i id="jp_toggle" class="fas fa-chevron-up"></i>
			<h6>Audio Player</h6>
		</div>
		<div id="jp_player_list" class="jp-type-playlist jp-hide">
			<div id="jplayer_main" class="jp-jplayer"></div>
			<div class="jp-gui">
				<div class="jp-interface">
					<div class="jp-progress">
						<div class="jp-seek-bar">
							<div class="jp-play-bar"></div>
						</div>
					</div>
					<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
					<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
					<div class="jp-details">
						<div class="jp-title" aria-label="title">&nbsp;</div>
					</div>
					<div class="jp-controls-holder">
						<div class="jp-volume-controls">
							<button class="jp-mute" role="button" tabindex="0">mute</button>
							<button class="jp-volume-max" role="button" tabindex="0">max
								volume</button>
							<div class="jp-volume-bar">
								<div class="jp-volume-bar-value"></div>
							</div>
						</div>
						<div class="jp-controls">
							<button class="jp-previous" role="button" tabindex="0">previous</button>
							<button class="jp-play" role="button" tabindex="0">play</button>
							<button class="jp-next" role="button" tabindex="0">next</button>
						</div>
						<div class="jp-toggles">
							<button class="jp-repeat" role="button" tabindex="0">repeat</button>
							<button class="jp-shuffle" role="button" tabindex="0">shuffle</button>
						</div>
					</div>
				</div>
			</div>
			<div class="jp-playlist scrollbar-dynamic">
				<ul>
					<!-- The method Playlist.displayPlaylist() uses this unordered list -->
					<li></li>
				</ul>
			</div>
			<div class="jp-no-solution">
				<span>Update Required</span> To play the media you will need to
				either update your browser to a recent version or update your <a
					href="http://get.adobe.com/flashplayer/" target="_blank">Flash
					plugin</a>.
			</div>
		</div>
	</div>
</aside>