$(document).ready(function(){
//right	
	 var xxxId = $("#xxxId").val();
	
	$.getJSON(ctx+'/leaderboards-1.controller',{'music_contest_id':xxxId},function(data){
 		$('.leaderboard-title').html(data[0][2].music_contest_name)
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			console.log(index);
			var cc0=$('<td class="td1"></td>').html(index+1+'.')
			var cc1=$('<td></td>').html('<img class="card-img-top img-fluid w-60" src='+mu[1].music_photo+'>')
			var cc2=$('<td></td>').html('<a href="#" data-music-link='+mu[1].music_link+' class="play_music"><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cc3=$('<td></td>').html(mu[1].music_name)
			var cc6=$('<td class="td6"></td>').html(mu[0].music_contest_players_votes).counterUp({delay:10,time:1000,});
			var cc4=$('<td></td>').html(mu[0].music_contest_player_id)
			var cc5=$('<td></td>').html(mu[1].music_description)
    		var row =$('<tr></tr>').append([cc0,cc1,cc2,cc3,cc6,cc4,cc5])
    		docFrag.append(row);			
		});
		$('.leaderboards-1-tbody-2').append(docFrag);		
	});
	
	$('.leaderboards-1-tbody-2').on('click','.play_music',function(){
		var $this = $(this),
		musicName = $this.parents('tr').find('td:nth-child(4)').text(),
		musicCtstPlayerId = $this.parents('tr').find('td:nth-child(6)').text(),
		musicLink = $this.data('music-link'),
		musicPhoto = $this.parents('tr').find('img').attr('src')
	addAndPlayMusic(musicName,musicCtstPlayerId,musicLink,musicPhoto);
	})
	

//left
	$.getJSON(ctx+'/leaderboards.count.controller',function(datacount){
		var docFrag =$(document.createDocumentFragment());
		$.ajaxSettings.async = false;
		$.each(datacount,function(index,i){
			$.getJSON(ctx+'/leaderboards-1.controller',{'music_contest_id':i},function(data){
				console.log(data[1][2].music_contest_id);
				var cc=$('<div class="left-div"><i class="material-icons" style="font-size:24px;color:red">skip_next</i><a href="'+ctx+'/pages/leaderboards-1.jsp?music_contest_id='+data[1][2].music_contest_id+'">'+data[1][2].music_contest_name+'</a></div>')
				docFrag.append(cc)
			$('#leaderboard-1-left-body').append(docFrag);
			})
		})
	})
		$.ajaxSettings.async = true;
	

});
