$(function(){
			var contestId = $("#contestId").val();
			$.getJSON(ctx+"/contests/voting/Players?contestId="+contestId,function(data){		
			var docFrag = $(document.createDocumentFragment());
			if(data.errMsg == null){
				$.each(data,function(index,value){
					docFrag.append(
						"<div class='display_show card'>"+
							"<div class='view view-eighth'>"+
								"<img src='"+value.musicPhoto+"'/>"+
								"<div class='mask'>"+
									"<h2>"+value.musicName+"</h2>"+
									"<p>"+value.musicDescription+"</p>"+
									"<a href='#' data-music-link='"+value.musicLink+"' class='info play_music'>播放歌曲</a>"+
									"<a href='#'data-player-id='"+value.musicCtstPlayerId+"' class='info voting'>投他一票</a>"+
								"</div>"+
							"</div>"+
							"<h5 class='view-description'>"+value.musicCtstPlayerId+" - "+value.musicName+"</h5>"+
						"</div>");
			});}		
			for(let i = 0; i < 5 ;i++){
				docFrag.append("<div class='display_show'></div>");
			}	
			$("#display_area").append(docFrag)
			.on("click",".play_music",function(e){
				var musicLink = $(this).data("music-link");
				alert("播放歌曲: "+ $(this).parent().find("h2").eq(0).text());
				if(musicLink){location.href=musicLink;}
				})			
	 		.on("click",".voting",function(e){
				var musicCtstPlayerId = $(this).data("player-id");
				if(confirm("確定要投票嗎?請注意:投票後就不能再更改囉!!")){
					$.getJSON(ctx + "/contests/voting/vote",{"musicCtstPlayerId":musicCtstPlayerId,"musicCtstId":contestId},
							function(data){
								if(data.mustLogin){
									alert(data.mustLogin);
									$("#login_box").modal("show");
								}else if(data.errMsg){
									alert(data.errMsg);
								}else if(data.success){							
									alert(data.success);
									alert("您投了 "+musicCtstPlayerId+" 一票~感謝您參與投票!!");}});}});
			});});