$(document).ready(function(){
	function createLikeTable(){
	$.getJSON(ctx + '/likeleaderboards.controller',function(data){
		console.log(data);
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			var cc1= $('<th class="text-danger"></th>').html(mu[5])
			var cc2= $('<td></td>').html(mu[3])
			var cc3= $('<td></td>').html('<img src='+mu[0]+'>')
			var cc4= $('<td></td>').html('<a href='+mu[1]+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
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

});
