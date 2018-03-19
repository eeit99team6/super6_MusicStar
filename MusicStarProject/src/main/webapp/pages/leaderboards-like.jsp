<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>創作排行</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/leaderboards-like.css" rel="stylesheet" />
<link href="${ctx}/assets/css/common_style/allPages.css" rel="stylesheet"/>
<style type="text/css">
.title-title{
color:orange;
}
</style>
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/leaderboards-like.js"></script>
<script>
</script>
</head>
<body>
<div class="wrapper scrollbar-dynamic">
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
	
	<div class="title-title">創作者排行榜</div>

		<div id="leaderboards-like-table" class="container">
			<table class="table">
			  <thead class="text-light bg-dark">
			    <tr>
			      <th scope="col">按讚數</th>
			      <th scope="col">作者</th>
			      <th scope="col">Cover</th>
			      <th scope="col">歌曲</th>
			      <th scope="col">歌名</th>
			      <th scope="col">喜歡請點</th>
			      <th scope="col">加入我的歌單</th>
			    </tr>
			  </thead>
			  <tbody class="likemusic-tbody">
			<!--     <tr> -->
			<!--       <td>放排行</td> -->
			<!--       <td>放作者</td> -->
			<!--       <td>放圖</td> -->
			<!--       <td>放歌曲</td> -->
			<!--       <td>放歌名</td> -->
			<!--       <td>放歡請點</td> -->
			<!--     </tr> -->
			  </tbody>
			</table>
			
			<div class="leaderboard-like-back">	
				<a href="${ctx}/index.jsp" class="btn btn-outline-danger" role="button" aria-pressed="true">回首頁</a>
			</div>
			
		</div><!-- leaderboards-like-table -->
		
		<!-- Modal -->
		<div class="modal fade" id="ModalCenter" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="ModalLongTitle">加入歌單</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
			      <table >
				      	<tr>
				      		<td></td>
				      	</tr>
				      	<tr>
				      		<td></td>
				      	</tr>
				      	<tr style="visibility:hidden">
				      		<td></td>
				      	</tr>
						<tr>
							<td>歌單名稱&nbsp：&nbsp<select id="listselect"><option value="0">請選擇</option></select></td>
							<td>&nbsp&nbsp<a href="${ctx}/pages/mymusiclist.jsp">還沒有歌單請點</a></td>
						</tr>
			      </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" id="musicListSubmit">送出</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal-Msg -->
		<div class="modal fade" id="Modal-Msg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">提示訊息</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <div class="modal-msg"></div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal">確認</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</div>
</body>
</html>