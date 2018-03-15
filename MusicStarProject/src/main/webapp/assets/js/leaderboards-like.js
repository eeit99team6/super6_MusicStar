$(document).ready(function(){
	function createLikeTable(){
	$.getJSON(ctx + '/likeleaderboards.controller',function(data){
		console.log(data);
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			var cc1= $('<th class="text-danger"></th>').html(mu[5]).counterUp({delay:10,time:1000,});
			var cc2= $('<td></td>').html(mu[3])
			var cc3= $('<td></td>').html('<img src='+mu[0]+'>')
			var cc4= $('<td></td>').html('<a href="#" data-music-link='+mu[1]+' class="play_music"><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cc5= $('<td></td>').html(mu[2])			
			var cc6= $('<td></td>').html('<a href="#" class="like"><i class="fa fa-heart" style="font-size:36px;color:red"></i></a>')			
			var cc7= $('<td style="visibility:hidden"></td>').html(mu[4])			
			var rr=$('<tr></tr>').append([cc1,cc2,cc3,cc4,cc5,cc6,cc7])
			docFrag.append(rr);
		})
		$('.likemusic-tbody').append(docFrag)
	});}
	
	createLikeTable();

	$('.likemusic-tbody').on('click','.like',function(){
		var id = $(this).parents('tr').find('td:nth-child(7)').text();
		$.getJSON(ctx + '/likeleaderboards.like.controller',{'likes_music_id':id},function(data){			
			if(data.success){
				alert(data.success);
				$('.likemusic-tbody').html("");
				createLikeTable();
			}else if(data.error){
				alert(data.error);
			}else if(data.mustlogin){
				alert(data.mustlogin);
				$("#login_box").modal("show");
			}			
		})		
	})
	.on('click','.play_music',function(){
		var $this = $(this),
		musicName = $this.parents('tr').find('td:nth-child(5)').text(),
		musicCtstPlayerId = $this.parents('tr').find('td:nth-child(2)').text(),
		musicLink = $this.data('music-link'),
		musicPhoto = $this.parents('tr').find('img').attr('src')
	addAndPlayMusic(musicName,musicCtstPlayerId,musicLink,musicPhoto);
	})
	
	
	
	

});
