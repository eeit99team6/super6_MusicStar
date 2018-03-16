$(function(){
			var contestId = $("#contestId").val();
            var finalDate = null;
            $(".counter_container").hide();
			if(contestId){
				$("#ajax_mask").removeClass("ajax_hide");			
			$.getJSON(ctx+"/contests/voting/detail",{"contestId":contestId},function(data){
				var now = new Date(),
				$vote_description = $("#vote_description"),
				startDate = new Date(data.music_contest_vote_start_date);
				finalDate = new Date (data.music_contest_end_date);
				$("#main_container>.default_title").text(data.music_contest_name);			
				if(startDate > now){
					$vote_description.text("投票尚未開始");
					
				}else if(now > finalDate){					
					$vote_description.text("投票已經結束");			
				}else{
					$(".counter_container").show();
					$vote_description.text("投票截止日期為  : " + data.music_contest_end_date);			
				}
				$.getJSON(ctx+"/contests/voting/players",{"contestId":contestId},function(data){		
					$("#ajax_mask").addClass("ajax_hide");
					var $docFrag = $(document.createDocumentFragment());
					if(data.errMsg == null && data.length > 0){
						$.each(data,function(index,value){
							var votes = value.musicCtstPlayerVotes || 0;
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
									"<h5 class='view-description'>"+value.musicCtstPlayerId+"　-　"+value.musicName+"</h5>"+
									"<h5 class='view-votes'>目前票數：<span class='counter'>"+votes+"</span></h5>"+
								"</div>");});
						}else{
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
					var musicCtstPlayerId = $(this).data("player-id"),
						thisPlayerVotes = $(this).parents(".display_show").find(".counter:eq(0)");
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
										alert("您投了 "+musicCtstPlayerId+" 一票~感謝您參與投票!!");
										var votes = thisPlayerVotes.text();
										thisPlayerVotes.text(++votes);
										$(".counter").counterUp({beginAt: votes, time: 500 });}});}})
				;});
			});
			}else{
				$(".counter_container").hide();
			}
						
			$(window).on('load', function (e) {
                 var labels = ['weeks', 'days', 'hours', 'minutes', 'seconds'],
                   template = _.template($('#main-counter-template').html()),
                   currDate = '00:00:00:00:00',
                   nextDate = '00:00:00:00:00',
                   parser = /([0-9]{2})/gi,
                   $example = $('#main-counter');
                 
                 // num counterup
                 $(".counter").counterUp({ time: 1500 });
                 // Parse countdown string to an object
                 function strfobj(str) {
                   var parsed = str.match(parser),
                     obj = {};
                   labels.forEach(function (label, i) {
                     obj[label] = parsed[i]
                   });
                   return obj;
                 }
                 // Return the time components that diffs
                 function diff(obj1, obj2) {
                   var diff = [];
                   labels.forEach(function (key) {
                     if (obj1[key] !== obj2[key]) {
                       diff.push(key);
                     }
                   });
                   return diff;
                 }
                 // Build the layout
                 var initData = strfobj(currDate);
                 labels.forEach(function (label, i) {
                   $example.append(template({
                     curr: initData[label],
                     next: initData[label],
                     label: label
                   }));
                 });
                 // Starts the countdown
                 $example.countdown(finalDate, function (event) {
                   var newDate = event.strftime('%w:%d:%H:%M:%S'),
                     data;
                   if (newDate !== nextDate) {
                     currDate = nextDate;
                     nextDate = newDate;
                     // Setup the data
                     data = {
                       'curr': strfobj(currDate),
                       'next': strfobj(nextDate)
                     };
                     // Apply the new values to each node that changed
                     diff(data.curr, data.next).forEach(function (label) {
                       var selector = '.%s'.replace(/%s/, label),
                         $node = $example.find(selector);
                       // Update the node
                       $node.removeClass('flip');
                       $node.find('.curr').text(data.curr[label]);
                       $node.find('.next').text(data.next[label]);
                       // Wait for a repaint to then flip
                       _.delay(function ($node) {
                         $node.addClass('flip');
                       }, 50, $node);
                     });
                   }
                 });
               });	

});