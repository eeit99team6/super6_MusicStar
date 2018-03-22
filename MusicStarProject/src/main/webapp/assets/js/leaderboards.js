$(document).ready(function(){
	$.getJSON(ctx + '/leaderboards.count.controller',function(datacount){			
		var docFrag =$(document.createDocumentFragment());
		var docFragIn =$(document.createDocumentFragment());
		$.ajaxSettings.async = false;
		$.each(datacount,function(index,i){
			$.getJSON(ctx + '/leaderboards.controller',{'music_contest_id':i},function(data){
	 	    	console.log(data);
				$.each(data,function(index,mu){				
					var cc1=$('<div></div>').html('<img class="img-fluid w-60 mb-1" src='+mu[1].music_photo+'>')
					var cc2=$('<div></div>').html('<a href="#" data-music-link='+mu[1].music_link+' class="play_music"><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
					var cc3=$('<p class="ph4 card-text small"></p>').html(mu[1].music_name)
					var cc4=$('<p class="ph5 card-text small"></p>').html(mu[0].music_contest_player_id)
		    		var rr=$('<li></li>').append([cc1,cc2,cc3,cc4])
		    		docFragIn.append(rr)
			});
	 	    		var rr1=$('<p class="leaderboard-title1 card-title mb-1"><i class="fa fa-play" style="font-size:32px;color:red"></i></p>').append(data[0][2].music_contest_name)
		    		var rr2=$('<ol class="leaderboard-ol card-body"></ol>').append(docFragIn)
		    		var rr3=$('<div class="card-footer small text-muted"></div>').html('<a href="' + ctx + '/pages/leaderboards-1.jsp?music_contest_id='+data[0][2].music_contest_id+'" class="btn btn-outline-danger" role="button" aria-pressed="true">看本榜Top10</a>')
		    		var ww=$('<div id="leaderboard-max1" class="container border border-danger card mb-3"></div>').append([rr1,rr2,rr3])
		    		docFrag.append(ww);					    								
				$("#leaderboard-outer").append(docFrag);
			})
		})			
	})	
	$.ajaxSettings.async = true;						

	$('#leaderboard-outer').on('click','.play_music',function(){
		var $this = $(this),
		musicName = $this.parents('li').find('.ph4').text(),
		musicCtstPlayerId = $this.parents('li').find('.ph5').text(),
		musicLink = $this.data('music-link'),
		musicPhoto = $this.parents('li').find('img').attr('src')
	addAndPlayMusic(musicName,musicCtstPlayerId,musicLink,musicPhoto);
	})
	
	
});

