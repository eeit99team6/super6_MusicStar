<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>歌單內容</title>
<jsp:include page="/includes/main_css.jsp" />
<style>
#productTable img{
width:60px;
	height:60px;
}
.play_music{
cursor: pointer;
}
.centerContainer{
margin:0 auto;

}
th{
min-width:110px
}
td{
margin:0 auto;
}
</style>
<jsp:include page="/includes/main_js.jsp" />
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
	    <div class="row">
	    <div class="centerContainer">
       <div class="col-lg-3">
       <div class="col-lg-9">
     <br>
      <br>
       <br>
		
				<!-- 每頁不同的內容從這裡開始 -->
				 <table id="productTable" class="table table-bordered">
                       <thead>
                          <tr>
                            <th></th>
                            <th>歌單ID</th>
							<th>歌曲id</th>
							<th>歌曲名稱</th>
							<th>歌曲連結</th>
							<th>歌曲作者ID</th>
							<th><button type=button class=btn id="all" btn-primary>全部播放</button></th>
                          </tr>
                       </thead>
                       <tbody>
                       </tbody>
                       <tfoot>
                       <tr>
                       </tfoot>
                   </table>
				<!-- 每頁不同的內容到這裡結束 -->
			</div>
		</div>
       </div>
    </div>
      </div>

	<script>
		$(document).ready(function() {			
			    //讀取歌單
			    	$.getJSON(ctx+'/musiclistiditem.controller',{'member_music_list_content_id':'${param.member_music_list_content_id}'},function(datas){
			    		//datas = [{},{}];
			    		console.log(datas)
// 			    		console.log(datas[1].member_music_list_description)
			    		var docFrag = $(document.createDocumentFragment()),
			    			playlist = [];
			    		$.each(datas,function(idx,mu){
			    			//product = {}
			    			
			    			var cell1 = $("<td></td>").html('<img src='+mu[1].music_photo+'>')
			    			var cell2 = $("<td></td>").html(mu[0].member_music_list_content_id);
			    			var cell3 = $("<td></td>").html(mu[0].member_music_list_content_music_id);
			    			var cell4 = $("<td></td>").html(mu[1].music_name);
			    			var cell5 = $("<td></td>").html('<span data-music-link='+mu[1].music_link+' class="play_music"><i class="fa fa-play-circle" style="font-size:36px;color:green"></i></span>')
			    			var cell6 = $("<td></td>").html(mu[1].music_member_id);
			    			var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6]);
			    			
			    			docFrag.append(row);
			    			
			    			playlist.push(music(mu[1].music_name,mu[1].music_member_id,mu[1].music_link,mu[1].music_photo));
			    	})
							$("#all").click(function(){
								setNewPlaylistAndPlayMusic(playlist);
							});	    	
		    	
			    	   		$('#productTable>tbody').html(docFrag);
			    	});
		
		
			    	
			 $('#productTable>tbody').on('click','.play_music',function(){
				var $this = $(this),
				musicName = $this.parents('tr').find('td:nth-child(4)').text(),
				musicCtstPlayerId = $this.parents('tr').find('td:nth-child(6)').text(),
				musicLink = $this.data('music-link'),
				musicPhoto = $this.parents('tr').find('img').attr('src')
			addAndPlayMusic(musicName,musicCtstPlayerId,musicLink,musicPhoto);
			})
		})	
			
	</script>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>