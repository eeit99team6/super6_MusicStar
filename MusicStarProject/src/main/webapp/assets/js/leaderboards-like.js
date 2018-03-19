$(document).ready(function(){
	
	function createLikeTable(){
	$.getJSON(ctx + '/likeleaderboards.controller',function(data){
//		console.log(data);		
		var docFrag =$(document.createDocumentFragment());		
		$.each(data,function(index,mu){
			var cc1= $('<th class="text-danger"></th>').html(mu[5]+((index-19)*-243)).counterUp({delay:10,time:1000,});		
			var cc2= $('<td></td>').html(mu[3])
			var cc3= $('<td></td>').html('<img src='+mu[0]+'>')
			var cc4= $('<td></td>').html('<a href="#" data-music-link='+mu[1]+' class="play_music"><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cc5= $('<td></td>').html(mu[2])			
			var cc6= $('<td></td>').html('<a href="#" class="like"><i class="fa fa-heart" style="font-size:36px;color:red"></i></a>')			
			var cc7= $('<td></td>').html('<button type="button" class="btn btn-primary"><i class="fas fa-plus"></i></button>')			
			var cc8= $('<td style="visibility:hidden"></td>').html(mu[4])			
			var rr=$('<tr></tr>').append([cc1,cc2,cc3,cc4,cc5,cc6,cc7,cc8])
			docFrag.append(rr);
		})
		$('.likemusic-tbody').append(docFrag)
	});}
	
	createLikeTable();

	$('.likemusic-tbody').on('click','.like',function(){
		var id = $(this).parents('tr').find('td:nth-child(8)').text();
		$.getJSON(ctx + '/likeleaderboards.like.controller',{'likes_music_id':id},function(data){			
			if(data.success){
				alert(data.success);
				$('.likemusic-tbody').html("");
				createLikeTable();
			}else if(data.error){
				if (confirm("確定要取消按讚?")){				
					$.getJSON(ctx + '/likeleaderboards.likedelete.controller',{'likes_music_id':id},function(data){
						alert(data.deleteok);
						$('.likemusic-tbody').html("");
						createLikeTable();
					})								
				}				
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
	.on('click','.btn',function(){
		var mm5 = $(this).parents('tr').find('td:nth-child(5)').text();
		var mm2 = $(this).parents('tr').find('td:nth-child(2)').text();
		var mm8 = $(this).parents('tr').find('td:nth-child(8)').text();
		$.getJSON(ctx+'/likeleaderboards.like.selectList.controller',function(data){
//			console.log(data)
			if(data.mustlogin){
				alert(data.mustlogin);			
				$("#login_box").modal("show");
			}else{
				$('#ModalCenter').modal('show');
				$('.modal-body').find('tr:eq(0) td:nth-child(1)').text('歌曲名稱 ： '+mm5);
				$('.modal-body').find('tr:eq(1) td:nth-child(1)').text('歌手姓名 ： '+mm2);
				$('.modal-body').find('tr:eq(2) td:nth-child(1)').text(mm8);
				if($('#listselect > option').length == 1){
					var docFrag =$(document.createDocumentFragment());
					$.each(data,function(index,mu){		
						var cc1= $('<option value="'+mu.member_music_list_id+'"></option>').html(mu.member_music_list_name);
						docFrag.append(cc1);
					})
					$('#listselect').append(docFrag);								
				}								
			}										
		})
	})
	
	$('#ModalCenter').on('click','#musicListSubmit',function(){
		var mm =$(this).parents('.modal-content').find('select[id="listselect"]').val();
		var mm8 = $(this).parents('.modal-content').find('tr:eq(2) td:nth-child(1)').text();
		console.log(mm)
		console.log(mm8)
		$.getJSON(ctx+'/likeleaderboards.like.insertList.controller',{'member_music_list_content_id' : mm, 'member_music_list_content_music_id' : mm8},function(data){
			if(data.mustlogin){
				alert(data.mustlogin);
			}else if(data.listfail){
				$('#Modal-Msg').modal("show");
				var docFrag =$(document.createDocumentFragment());
				var cc = $('<span></span>').text(data.listfail)
				docFrag.append(cc)
				$('.modal-msg').html("");
				$('.modal-msg').append(cc)
			}else if(data.listok){
				$('#Modal-Msg').modal("show");
				var docFrag =$(document.createDocumentFragment());
				var cc = $('<span></span>').text(data.listok)
				docFrag.append(cc)
				$('.modal-msg').html("");
				$('.modal-msg').append(cc)												
			}
		})
		
	
	})
	
			
});
