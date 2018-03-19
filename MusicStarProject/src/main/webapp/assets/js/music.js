$(document).ready(function(){
	var needDeleteId;
	
	// Music Player
	$(".musicDiv").on('click','.play_button',function(){
		var $this = $(this),
		musicName = $this.parents('#musicDiv').find('#music_name').text(),
		musicCtstPlayerId = $this.parents('#musicDiv').find('#music_member_id').text(),
		musicLink = $this.parents("#musicDiv").find("input[name='music_link']").val(),
		musicPhoto = $this.parents("#musicDiv").find("#play_music").attr('src')
		addAndPlayMusic(musicName,musicCtstPlayerId,musicLink,musicPhoto);
	})
	
	
	// Get like counts function
	function getLikeCounts(){
		$.getJSON(ctx + '/music/musicSelectCount',
			function(data){ 
			$(":input[name='music_id']").each(function(){
				var key = $(this).val();		// $(this) -> $(":input[name='music_id']")
				var count = (key+1)*2-7 + data[key] || (key+1)*2-7 +"0";	// if data[key] == true -> save data[key] to count
												// if data[key] == false -> save "0" to count
									
				$(this).parents("#musicDiv").find(".count:eq(0)").text(count);
												// find(".count:eq(0)") -> find array[0] by eq(0)
			});
		});
	}
	
	// Delete Like function
	function deleteLike(needDeleteId){
		$.getJSON(ctx + '/likeleaderboards.likedelete.controller',{'likes_music_id':needDeleteId},function(data){
			getLikeCounts();
		})
	}
	
	$("#checkLikeOKButton").on("click",function(){
		deleteLike(needDeleteId);
	})
	
	$("#checkClickLike").on("click",function(){
		$("#checkLike").modal("show");
	})
	
	// When loading this page, call getLikeCounts() function to give like counts
	getLikeCounts();
	
	// Count up
	$('.count').counterUp({
        delay: 10,
        time: 1000
    });
	
	// When first loading this page nothing radio, give default button
	if($(":radio")[0].checked == false && $(":radio")[1].checked == false
			 && $(":radio")[2].checked == false && $(":radio")[3].checked == false
			 && $(":radio")[4].checked == false && $(":radio")[5].checked == false
			 && $(":radio")[6].checked == false && $(":radio")[7].checked == false
			 && $(":radio")[8].checked == false && $(":radio")[9].checked == false
			 && $(":radio")[10].checked == false && $(":radio")[11].checked == false
			 && $(":radio")[12].checked == false && $(":radio")[13].checked == false
			 && $(":radio")[14].checked == false && $(":radio")[15].checked == false
			 && $(":radio")[16].checked == false && $(":radio")[17].checked == false){
		document.getElementById("AllStyle").setAttribute("checked","checked");
		document.getElementById("AllSinger").setAttribute("checked","checked");
		$("#styleDiv label")[$("#styleDiv input[checked*=checked]").val()].className += " active";
		$("#singerDiv label")[$("#singerDiv input[checked*=checked]").index()].className += " active";
	}
	
	// When loaded page, give default tag
	$(".music_selector .nav-tabs > li > a:eq(0)").attr("class","btn active show");
	
	// When input has error massages, change default tag to input tag
	if($("#errors").text() != ""){
		$("ul li:eq(0)").attr("class","");
		$("ul li:eq(1)").attr("class","active");
		$("#home").attr("class","tab-pane fade");
		$("#menu1").attr("class","tab-pane fade in active");				
		$(".music_selector .nav-tabs > li > a:eq(0)").attr("class","btn");
		$(".music_selector .nav-tabs > li > a:eq(1)").attr("class","btn active show");
	}
	
	// Like
	$(".musicDiv").on('click','.like',function(){
		var id = $(this).parents(".musicDiv").find("input[name=music_id]").val();
		$.getJSON(ctx + '/music/musicSelectLike',{'likes_music_id':id},
			function(data){
				if(data.success){
					$(".modal-msg").eq(0).html("<span>"+ data.success +"</span>");
					$("#checkList").modal("show");
					
				// Renew page to display update count
					getLikeCounts();

				}else if(data.error){
					$("#modal-msg").eq(0).html("<span style='color:red'>"+ data.error +"</span>");
					$("#checkClick").modal("show");
					
					needDeleteId = id;
					
//					if (confirm("確定要取消按讚?")){				
//						$.getJSON(ctx + '/likeleaderboards.likedelete.controller',{'likes_music_id':id},function(data){
//							alert(data.deleteok);
//							getLikeCounts();
//						})
//					}
				}else if(data.mustlogin){
					$(".modal-msg").eq(0).html("<span style='color:red'>"+ data.mustlogin +"</span>");
					$("#checkList").modal("show");
					
					$("#checkList").on("click","#mustLoginButton",function(){
						// show login page
						$("#login_box").modal("show");
					})
				}
		})
	})
	
	// Like End
				
	// Click List
	$(".musicDiv").on("click",".list",function(e){
		$(".musicDiv").find("a[class='list']").removeAttr('data-target');
		$(".musicDiv").find("a[class='list']").removeAttr('data-toggle');
		var music_name = $(this).parents(".musicDiv").find("a[id='music_name']").text();
		var music_id = $(this).parents(".musicDiv").find("input[name='music_id']").val();
		$.getJSON(ctx + '/music/musicSelectList',
			function(data){
				if(data.mustlogin==null){
					$(".modal-content").find("td[id='music_list_music_name']").text("歌曲名稱: " + music_name);
					$(".modal-content").find("h5[class='modal-title']").after("<input type='hidden' name='music_id' value='" + music_id + "'></input>");
					
					if($('#listselect > option').length == 1){
						var docFrag =$(document.createDocumentFragment());
						$.each(data,function(index,mu){		
							var cc1= $("<option value='" +index + "'" + " " + "name='option'"+ "></option>").html(mu);
							docFrag.append(cc1);
						})
						$('#listselect').append(docFrag);								
					}
					
					// show add list page
					$("#exampleModal").modal("show");
				}else if(data.mustlogin!=null){
					$(".modal-msg").eq(0).html("<span style='color:red'>"+ data.mustlogin +"</span>");
					$("#checkList").modal("show");
					
					$("#checkList").on("click","#mustLoginButton",function(){
						// show login page
						$("#login_box").modal("show");
					})
				}
		})
	})
	
	// Submit List
	$(".modal-content").on("click","#musicListSubmit",function(){
		$(".modal-content").find("button[id='musicListSubmit']").removeAttr('data-target');
		$(".modal-content").find("button[id='musicListSubmit']").removeAttr('data-toggle');
		
		var member_music_list_content_music_id = $(this).parents(".modal-content").find("input[name='music_id']").val();
		var member_music_list_content_id = $(this).parents(".modal-content").find("select[id='listselect']").val();
		
		$.getJSON(ctx + '/music/musicInsertList',{'member_music_list_content_id':member_music_list_content_id,'member_music_list_content_music_id':member_music_list_content_music_id},
			function(data){
				if(data.insertListSuccess != null){
					$(".modal-msg").eq(0).html("<span>"+ data.insertListSuccess +"</span>");
					$("#checkList").modal("show");
					$("#exampleModal").modal("hide");
				}else{
					$(".modal-msg").eq(0).html("<span style='color:red'>"+ data.insertListFailed +"</span>");
					$("#checkList").modal("show");
				}
			})
		})
	})

	// Get the container element
	var btnContainer = document.getElementById("styleDiv");
	
	// Get all buttons with class="btn" inside the container
	var btns = btnContainer.getElementsByClassName("btn");
	
	// Loop through the buttons and add the active class to the current/clicked button
	for (var i = 0; i < btns.length; i++) {
	  	btns[i].addEventListener("click", function() {
	  		$("#styleDiv label[class='btn active']").attr("class","btn")
	    	this.className += " active";
	  });
	}
	
	// Get the container element
	var btnContainer2 = document.getElementById("singerDiv");
	
	// Get all buttons with class="btn" inside the container
	var btns2 = btnContainer2.getElementsByClassName("btn");
	
	// Loop through the buttons and add the active class to the current/clicked button
	for (var j = 0; j < btns2.length; j++){
		btns2[j].addEventListener("click", function(){
			var current2 = $("#singerDiv label[class*='btn active']");
			current2[0].className = current2[0].className.replace(" active", "");
			this.className += " active";
		});
	}
	
	// AutoComplete
	var show;
	window.addEventListener("load", init, false);
	function init() {
		var txt = document.getElementById("txtSearch");
		txt.addEventListener("keyup", getData);
		show = document.getElementById("div1");
	}
	function getData() {
		var xhr = new XMLHttpRequest();
		if(xhr != null){
			xhr.addEventListener("load",function(){
				if(xhr.status == 200){
					var temps = xhr.responseText;
					var datas = JSON.parse(temps);
					
					show.style.display = "block";
					if (show.childNodes.length > 0) {
						show.removeChild(show.childNodes[0]);
					}
					var eleDiv = document.createElement("div");
					eleDiv.className = "list-group";
					for (var j = 0, max = datas.length; j < max; j++) {
						var txtBtn = document.createTextNode(datas[j]);
						var eleBtn = document.createElement("button");
						eleBtn.className = "list-group-item list-group-item-action";
						eleBtn.setAttribute("type", "button");
						eleBtn.appendChild(txtBtn);
	
						eleBtn.addEventListener("click", function() {
							document.myData.keyword.value = this.firstChild.nodeValue;
							show.style.display = "none";
						})
						eleDiv.appendChild(eleBtn);
					}
					div1.appendChild(eleDiv);
				}
			})
			xhr.open("GET","jsonSimple?keyword="+txtSearch.value);
			xhr.send();
		}else{
			alert("您的瀏覽器不支援AJAX!!");
		}
	}