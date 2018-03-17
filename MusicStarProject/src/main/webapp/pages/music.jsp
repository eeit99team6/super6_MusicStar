<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>音樂搜尋</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/music.css" rel="stylesheet" />
<link href="${ctx}/assets/css/common_style/allPages.css" rel="stylesheet"/>
<jsp:include page="/includes/main_js.jsp" />
</head>
<body>
<div class="wrapper scrollbar-dynamic">
<jsp:include page="/includes/main_header.jsp" />

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">加入歌單</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span id="close" aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="">
        	<table>
        		<tbody>
        			<tr><td id="music_list_music_name">歌曲名稱: </td></tr>
        			<tr><td id="listname">歌單名稱: <select id="listselect"><option value="0">請選擇</option></select></td></tr>
        		</tbody>
        	</table>
	        <div class="modal-footer">
	           <button type="button" class="btn btn-primary" id="musicListSubmit">送出</button>
	           <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
	        </div>
        </form>
      </div>
    </div>
  </div>
</div>

	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
  <div class="title-title">音樂搜尋引擎 </div>
<div class="container">
<div class="music_selector">
  <ul class="nav nav-tabs">
    <li class="active" id="litab"><a data-toggle="tab" href="#home" class="btn">按鈕搜尋</a></li>
    <li id="litab"><a data-toggle="tab" href="#menu1" class="btn">文字搜尋</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">    
	<form action="<c:url value="/pages/radioSelect" />" method="get" class="card border-primary mb-3">
	<div class="card-header">
		<div>
			<h4>Style(曲風):</h4>
		</div>
		<div id="styleDiv">
			<input class="form-check-input" type="radio" name="Style" value="0" id="AllStyle" style="visibility:hidden" <c:if test="${Style.AllStyle=='0'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='0'}">			
				<label class="btn active" for="AllStyle">AllStyle</label>
			</c:if>
			<c:if test="${Style.AllStyle!='0'}">			
				<label class="btn" for="AllStyle">AllStyle</label>
			</c:if>
			<input type="radio" name="Style" value="1" id="Rock" style="visibility:hidden" <c:if test="${Style.AllStyle=='1'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='1'}">			
				<label class="btn active" for="Rock">Rock</label>
			</c:if>
			<c:if test="${Style.AllStyle!='1'}">			
				<label class="btn" for="Rock">Rock</label>
			</c:if>
			<input type="radio" name="Style" value="2" id="Jazz" style="visibility:hidden" <c:if test="${Style.AllStyle=='2'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='2'}">			
				<label class="btn active" for="Jazz">Jazz</label>
			</c:if>
			<c:if test="${Style.AllStyle!='2'}">			
				<label class="btn" for="Jazz">Jazz</label>
			</c:if>
			<input type="radio" name="Style" value="3" id="Blue" style="visibility:hidden" <c:if test="${Style.AllStyle=='3'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='3'}">			
				<label class="btn active" for="Blue">Blue</label>
			</c:if>
			<c:if test="${Style.AllStyle!='3'}">			
				<label class="btn" for="Blue">Blue</label>
			</c:if>
			<input type="radio" name="Style" value="4" id="Country" style="visibility:hidden" <c:if test="${Style.AllStyle=='4'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='4'}">			
				<label class="btn active" for="Country">Country</label>
			</c:if>
			<c:if test="${Style.AllStyle!='4'}">			
				<label class="btn" for="Country">Country</label>
			</c:if>
			<input type="radio" name="Style" value="5" id="Pop" style="visibility:hidden" <c:if test="${Style.AllStyle=='5'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='5'}">			
				<label class="btn active" for="Pop">Pop</label>
			</c:if>
			<c:if test="${Style.AllStyle!='5'}">			
				<label class="btn" for="Pop">Pop</label>
			</c:if>
			<input type="radio" name="Style" value="6" id="Electronic" style="visibility:hidden" <c:if test="${Style.AllStyle=='6'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='6'}">			
				<label class="btn active" for="Electronic">Electronic</label>
			</c:if>
			<c:if test="${Style.AllStyle!='6'}">			
				<label class="btn" for="Electronic">Electronic</label>
			</c:if>
			<input type="radio" name="Style" value="7" id="Flok" style="visibility:hidden" <c:if test="${Style.AllStyle=='7'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='7'}">			
				<label class="btn active" for="Flok">Flok</label>
			</c:if>
			<c:if test="${Style.AllStyle!='7'}">			
				<label class="btn" for="Flok">Flok</label>
			</c:if>
			<input type="radio" name="Style" value="8" id="Alternative" style="visibility:hidden" <c:if test="${Style.AllStyle=='8'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='8'}">			
				<label class="btn active" for="Alternative">Alternative</label>
			</c:if>
			<c:if test="${Style.AllStyle!='8'}">			
				<label class="btn" for="Alternative">Alternative</label>
			</c:if>
			<input type="radio" name="Style" value="9" id="Punk" style="visibility:hidden" <c:if test="${Style.AllStyle=='9'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='9'}">			
				<label class="btn active" for="Punk">Punk</label>
			</c:if>
			<c:if test="${Style.AllStyle!='9'}">			
				<label class="btn" for="Punk">Punk</label>
			</c:if>
			<input type="radio" name="Style" value="10" id="Classical" style="visibility:hidden" <c:if test="${Style.AllStyle=='10'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='10'}">			
				<label class="btn active" for="Classical">Classical</label>
			</c:if>
			<c:if test="${Style.AllStyle!='10'}">			
				<label class="btn" for="Classical">Classical</label>
			</c:if>
			<input type="radio" name="Style" value="11" id="Religion" style="visibility:hidden" <c:if test="${Style.AllStyle=='11'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='11'}">			
				<label class="btn active" for="Religion">Religion</label>
			</c:if>
			<c:if test="${Style.AllStyle!='11'}">			
				<label class="btn" for="Religion">Religion</label>
			</c:if>
			<input type="radio" name="Style" value="12" id="Independent" style="visibility:hidden" <c:if test="${Style.AllStyle=='12'}">checked="checked"</c:if>>
			<c:if test="${Style.AllStyle=='12'}">			
				<label class="btn active" for="Independent">Independent</label>
			</c:if>
			<c:if test="${Style.AllStyle!='12'}">			
				<label class="btn" for="Independent">Independent</label>
			</c:if>
		</div>
		<div>
			<h4>Singer(歌手):</h4>
		</div>
		<div id="singerDiv">
			<input type="radio" name="Singer" value="AllSinger" id="AllSinger" style="visibility:hidden" <c:if test="${Style.Singer=='AllSinger'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='AllSinger'}">			
				<label class="btn active" for="AllSinger">AllSinger</label>
			</c:if>
			<c:if test="${Style.Singer!='AllSinger'}">			
				<label class="btn" for="AllSinger">AllSinger</label>
			</c:if>
			<input type="radio" name="Singer" value="9527" id="9527" style="visibility:hidden" <c:if test="${Style.Singer=='9527'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='9527'}">			
				<label class="btn active" for="9527">9527</label>
			</c:if>
			<c:if test="${Style.Singer!='9527'}">			
				<label class="btn" for="9527">9527</label>
			</c:if>
			<input type="radio" name="Singer" value="Mike123" id="Mike123" style="visibility:hidden" <c:if test="${Style.Singer=='Mike123'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Mike123'}">			
				<label class="btn active" for="Mike123">Mike123</label>
			</c:if>
			<c:if test="${Style.Singer!='Mike123'}">			
				<label class="btn" for="Mike123">Mike123</label>
			</c:if>
			<input type="radio" name="Singer" value="Toney456" id="Toney456" style="visibility:hidden" <c:if test="${Style.Singer=='Toney456'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Toney456'}">			
				<label class="btn active" for="Toney456">Toney456</label>
			</c:if>
			<c:if test="${Style.Singer!='Toney456'}">			
				<label class="btn" for="Toney456">Toney456</label>
			</c:if>
			<input type="radio" name="Singer" value="Angel789" id="Angel789" style="visibility:hidden" <c:if test="${Style.Singer=='Angel789'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Angel789'}">			
				<label class="btn active" for="Angel789">Angel789</label>
			</c:if>
			<c:if test="${Style.Singer!='Angel789'}">			
				<label class="btn" for="Angel789">Angel789</label>
			</c:if>
			<input type="radio" name="Singer" value="Jran" id="Jran" style="visibility:hidden" <c:if test="${Style.Singer=='Jran'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Jran'}">			
				<label class="btn active" for="Jran">Jran</label>
			</c:if>
			<c:if test="${Style.Singer!='Jran'}">			
				<label class="btn" for="Jran">Jran</label>
			</c:if>
			<input type="radio" name="Singer" value="Samie" id="Samie" style="visibility:hidden" <c:if test="${Style.Singer=='Samie'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Samie'}">			
				<label class="btn active" for="Samie">Samie</label>
			</c:if>
			<c:if test="${Style.Singer!='Samie'}">			
				<label class="btn" for="Samie">Samie</label>
			</c:if>
			<input type="radio" name="Singer" value="Apple" id="Apple" style="visibility:hidden" <c:if test="${Style.Singer=='Apple'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Apple'}">
				<label class="btn active" for="Apple">Apple</label>
			</c:if>
			<c:if test="${Style.Singer!='Apple'}">			
				<label class="btn" for="Apple">Apple</label>
			</c:if>
			<input type="radio" name="Singer" value="Lee123" id="Lee123" style="visibility:hidden" <c:if test="${Style.Singer=='Lee123'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Lee123'}">			
				<label class="btn active" for="Lee123">Lee123</label>
			</c:if>
			<c:if test="${Style.Singer!='Lee123'}">			
				<label class="btn" for="Lee123">Lee123</label>
			</c:if>
			<input type="radio" name="Singer" value="Ray456" id="Ray456" style="visibility:hidden" <c:if test="${Style.Singer=='Ray456'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Ray456'}">			
				<label class="btn active" for="Ray456">Ray456</label>
			</c:if>
			<c:if test="${Style.Singer!='Ray456'}">			
				<label class="btn" for="Ray456">Ray456</label>
			</c:if>
			<input type="radio" name="Singer" value="NeihuWu" id="NeihuWu" style="visibility:hidden" <c:if test="${Style.Singer=='NeihuWu'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='NeihuWu'}">			
				<label class="btn active" for="NeihuWu">NeihuWu</label>
			</c:if>
			<c:if test="${Style.Singer!='NeihuWu'}">			
				<label class="btn" for="NeihuWu">NeihuWu</label>
			</c:if>
			<input type="radio" name="Singer" value="RayWang" id="RayWang" style="visibility:hidden" <c:if test="${Style.Singer=='RayWang'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='RayWang'}">			
				<label class="btn active" for="RayWang">RayWang</label>
			</c:if>
			<c:if test="${Style.Singer!='RayWang'}">			
				<label class="btn" for="RayWang">RayWang</label>
			</c:if>
			<input type="radio" name="Singer" value="Eve" id="Eve" style="visibility:hidden" <c:if test="${Style.Singer=='Eve'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Eve'}">			
				<label class="btn active" for="Eve">Eve</label>
			</c:if>
			<c:if test="${Style.Singer!='Eve'}">			
				<label class="btn" for="Eve">Eve</label>
			</c:if>
			<input type="radio" name="Singer" value="Gavin" id="Gavin" style="visibility:hidden" <c:if test="${Style.Singer=='Gavin'}">checked="checked"</c:if>>
			<c:if test="${Style.Singer=='Gavin'}">			
				<label class="btn active" for="Gavin">Gavin</label>
			</c:if>
			<c:if test="${Style.Singer!='Gavin'}">			
				<label class="btn" for="Gavin">Gavin</label>
			</c:if>
		</div>
	</div>
		<div class="card-body" id="button_submit">
			<button type="submit" class="btn btn-primary btn-lg raised btn-color btn-hover">submit</button>
		</div>
	</form>
    </div>
    
    
    <div id="menu1" class="tab-pane fade">
      <div class="card border-primary mb-3">
			<div class="card-header" id="input_title">請輸入歌名搜尋</div>
				<form name="myData" action="<c:url value="/pages/autoComplete" />" method="get">
					<div class="card-body" id="input_keyword">
						<input type="text" class="form-control" id="txtSearch" name="keyword" placeholder="ex:追光者">
						<div id="div1"></div>
					</div>
					<div class="card-footer" id="input_submit">
						<button type="submit" class="btn btn-primary btn-lg raised btn-color btn-hover">submit</button>
						<c:if test="${empty bean}">
							<div id="input_errors">
								<h5 id="errors">${errors.error}</h5>
							</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
    </div>
    
	<c:if test="${not empty bean}">
		<div id="music_container" class="container">
	        <div class="row">
				<c:forEach var="beanVar" items="${bean}">
					<div class="col-xl-2 musicDiv" id="musicDiv">
						
						<div class="view container" id="music_player">
							<input type="hidden" name="music_link" value="${beanVar.music_link}">
							<img id="play_music" class="img-fluid img-responsive center-block play_music" src="${beanVar.music_photo}">
							<div id="middle">
								<i id="play_button" class="fas fa-play play_button"></i>
							</div>
						</div>
						
						<div><input type="hidden" name="music_id" value="${beanVar.music_id}"/></div>
						
						<div id="singer">歌手: <div id="music_member_id">${beanVar.music_member_id}</div></div>
						
						<div id="song">歌名: <a href="" id="music_name">${beanVar.music_name}</a></div>
					
						<div><input type="hidden" name="music_style_id" value="${beanVar.music_style_id}"/></div>
						
						<div id="like_music" class="like_music">Like:&nbsp;&nbsp;
							<i class="fa fa-heart like" id="like_mark"></i>
						</div>
						
						<div id="like_count">按讚數:&nbsp;&nbsp;<span class="count"></span></div>
						
						<div id="add_list">加歌單:&nbsp;&nbsp;
							<a data-toggle="modal" data-target="#exampleModal" class="list">
          						<i class="fas fa-clipboard-list" id="list_mark"></i>
        					</a>
						</div>
					
						<div id="description">描述: ${beanVar.music_description}</div>
					
					</div>
				</c:forEach>
			</div>
		</div>
		</c:if>
	</div>  
	</div>
</div>
	<!-- Optional JavaScript -->
	<script src="${ctx}/assets/js/music.js"></script>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
	</div>
</body>
</html>