$(function(){
			var contestId = $("#contestId").val();
			$.getJSON(ctx+"/contests/voting/Players?contestId="+contestId,function(data){		
			var $docFrag = $(document.createDocumentFragment());
			if(data.errMsg == null && data.length > 0){
				$.each(data,function(index,value){
					$docFrag.append(
						"<div class='display_show card'>"+
							"<div class='view view-eighth'>"+
								"<img src='"+value.musicPhoto+"'/>"+
								"<div class='mask'>"+
									"<h2>"+value.musicName+"</h2>"+
									"<p>"+value.musicDescription+"</p>"+
									"<a data-music-link='"+value.musicLink+"' class='info play_music'>播放歌曲</a>"+
									"<a data-player-id='"+value.musicCtstPlayerId+"' class='info voting'>投他一票</a>"+
								"</div>"+
							"</div>"+
							"<h5 class='view-description'>"+value.musicCtstPlayerId+" - "+value.musicName+"</h5>"+
						"</div>");
			});}else{
				$("#display_area").after("<h2 class='text-center' style='margin-bottom:200px;'>很抱歉此賽事目前沒有任何參賽者</h2>");
			}		
			for(let i = 0; i < 3 ;i++){
				$docFrag.append("<div class='display_show'></div>");
			}	
			$("#display_area").append($docFrag)
			.on("click",".play_music",function(e){
				var $this = $(this),
					musicName = $this.parent().find("h2").eq(0).text(),
					musicCtstPlayerId = $this.next("a").data("player-id"),
					musicLink = $this.data("music-link"),
					musicPhoto = $this.parents(".view").find("img").eq(0).attr("src");
				addAndPlayMusic(musicName,musicCtstPlayerId,musicLink,musicPhoto);
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