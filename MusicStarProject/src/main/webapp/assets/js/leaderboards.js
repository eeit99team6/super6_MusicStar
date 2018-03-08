$(document).ready(function(){
							
//類型1	
	$.getJSON(ctx + '/leaderboards.controller',{'music_contest_id':1},function(data){
// 		console.log(data);
 			$('.leaderboard-title1').append(data[0][2].music_contest_name)
		var docFrag =$(document.createDocumentFragment());				
		$.each(data,function(index,mu){
			var cell1=$('<div></div>').html('<img src='+mu[1].music_photo+'>')
			var cell2=$('<div></div>').html('<a href='+mu[1].music_link+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cell3=$('<h4></h4>').html(mu[1].music_name)
			var cell4=$('<h5></h5>').html(mu[0].music_contest_player_id)
    		var row =$('<li></li>').append([cell1,cell2,cell3,cell4])
    		docFrag.append(row);			
		});
		$('#leaderboard-max1>ol').append(docFrag);		
	});
	
//類型2
	$.getJSON(ctx + '/leaderboards.controller',{'music_contest_id':2},function(data){
			$('.leaderboard-title2').append(data[0][2].music_contest_name)
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			var cell1=$('<div></div>').html('<img src='+mu[1].music_photo+'>')
			var cell2=$('<div></div>').html('<a href='+mu[1].music_link+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cell3=$('<h4></h4>').html(mu[1].music_name)
			var cell4=$('<h5></h5>').html(mu[0].music_contest_player_id)
    		var row =$('<li></li>').append([cell1,cell2,cell3,cell4])
    		docFrag.append(row);				
		});
		$('#leaderboard-max2>ol').append(docFrag);		
	});
	
//類型3
	$.getJSON(ctx + '/leaderboards.controller',{'music_contest_id':3},function(data){
			$('.leaderboard-title3').append(data[0][2].music_contest_name)
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			var cell1=$('<div></div>').html('<img src='+mu[1].music_photo+'>')
			var cell2=$('<div></div>').html('<a href='+mu[1].music_link+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cell3=$('<h4></h4>').html(mu[1].music_name)
			var cell4=$('<h5></h5>').html(mu[0].music_contest_player_id)
    		var row =$('<li></li>').append([cell1,cell2,cell3,cell4])
    		docFrag.append(row);				
		});
		$('#leaderboard-max3>ol').append(docFrag);		
	});
	
//類型4
	$.getJSON(ctx + '/leaderboards.controller',{'music_contest_id':4},function(data){
			$('.leaderboard-title4').append(data[0][2].music_contest_name)
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			var cell1=$('<div></div>').html('<img src='+mu[1].music_photo+'>')
			var cell2=$('<div></div>').html('<a href='+mu[1].music_link+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cell3=$('<h4></h4>').html(mu[1].music_name)
			var cell4=$('<h5></h5>').html(mu[0].music_contest_player_id)
    		var row =$('<li></li>').append([cell1,cell2,cell3,cell4])
    		docFrag.append(row);				
		});
		$('#leaderboard-max4>ol').append(docFrag);		
	});
	
//類型5
	$.getJSON(ctx + '/leaderboards.controller',{'music_contest_id':5},function(data){
			$('.leaderboard-title5').append(data[0][2].music_contest_name)
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			var cell1=$('<div></div>').html('<img src='+mu[1].music_photo+'>')
			var cell2=$('<div></div>').html('<a href='+mu[1].music_link+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cell3=$('<h4></h4>').html(mu[1].music_name)
			var cell4=$('<h5></h5>').html(mu[0].music_contest_player_id)
    		var row =$('<li></li>').append([cell1,cell2,cell3,cell4])
    		docFrag.append(row);				
		});
		$('#leaderboard-max5>ol').append(docFrag);		
	});
	
//類型6
	$.getJSON(ctx + '/leaderboards.controller',{'music_contest_id':6},function(data){
			$('.leaderboard-title6').append(data[0][2].music_contest_name)
		var docFrag =$(document.createDocumentFragment());
		$.each(data,function(index,mu){
			var cell1=$('<div></div>').html('<img src='+mu[1].music_photo+'>')
			var cell2=$('<div></div>').html('<a href='+mu[1].music_link+'><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></a>')
			var cell3=$('<h4></h4>').html(mu[1].music_name)
			var cell4=$('<h5></h5>').html(mu[0].music_contest_player_id)
    		var row =$('<li></li>').append([cell1,cell2,cell3,cell4])
    		docFrag.append(row);				
		});
		$('#leaderboard-max6>ol').append(docFrag);		
	});


});
