<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<header id="main_header">
	<nav class="main_nav">
		<ul class="menu">
			<li><a href="<c:url value="/index.jsp"/>">MusicStar</a></li>
			<li><a href="<c:url value="/pages/contest.jsp#title"/>">賽事專區</a>
				<ul>
					<li><a href="<c:url value="/pages/contest.jsp#rule"/>">賽事規則</a></li>
					<li><a href="<c:url value="/pages/contest.jsp#applying"/>">報名中</a></li>
					<li><a href="<c:url value="/pages/contest.jsp#voting"/>">投票中</a></li>
					<li><a href="<c:url value="/pages/contest.jsp#end"/>">歷史賽事</a></li>
				</ul></li>
			<li><a href="<c:url value="/pages/rank.jsp#title"/>">歌曲排行</a>
				<ul>
					<li><a href="<c:url value="/pages/rank.jsp#contests"/>">賽事排行</a></li>
					<li><a href="<c:url value="/pages/rank.jsp#likes"/>">創作排行</a></li>
				</ul></li>
			<li><a href="<c:url value="/pages/music.jsp"/>">音樂搜尋</a></li>
			<li><a href="#">測試區</a>
				<ul>
					<li><a href="<c:url value="/testpages/test_table.jsp"/>">測試表格</a></li>
					<li><a href="<c:url value="/testpages/test_fileupload.jsp"/>">上傳測試</a></li>
					<li><a href="<c:url value="/testpages/test_google_login.jsp"/>">Google登入</a></li>
				</ul></li>
			<c:choose>
				<c:when test="${not empty loginOK}">
					<li><a href="#l"> ${loginOK.mbrName} <c:choose>
								<c:when test="${not empty loginOK.mbrPhoto}">
									<img src="${loginOK.mbrPhoto}" class="rounded-circle" />
								</c:when>
								<c:otherwise>
									<img src="${ctx}/assets/img/profile/init_profile.png" class="rounded-circle" />
								</c:otherwise>
							</c:choose></a>
						<ul>
							<li><a href="#">資料設定</a></li>
							<li><a href="#">上傳音樂</a></li>
							<li><a href="#">我的音樂</a></li>
							<li><a href="<c:url value="/members/logout"/>">登出</a></li>
						</ul> <i class="fas fa-align-justify" title="系統"></i></li>
				</c:when>
				<c:otherwise>
					<li><a href="#login" data-toggle="modal" data-target="#login_box">登入會員</a><i class="fas fa-align-justify" title="系統"></i></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<nav id="sub_menu">
		<ul class="links">
			<li><a href="<c:url value="/pages/contest.jsp#title"/>">賽事專區</a>
				<ul>
					<li><a href="<c:url value="/pages/contest.jsp#rule"/>">賽事規則</a></li>
					<li><a href="<c:url value="/pages/contest.jsp#applying"/>">報名中</a></li>
					<li><a href="<c:url value="/pages/contest.jsp#voting"/>">投票中</a></li>
					<li><a href="<c:url value="/pages/contest.jsp#end"/>">歷史賽事</a></li>
				</ul></li>
			<li><a href="<c:url value="/pages/rank.jsp#title"/>">歌曲排行</a>
				<ul>
					<li><a href="<c:url value="/pages/rank.jsp#contests"/>">賽事排行</a></li>
					<li><a href="<c:url value="/pages/rank.jsp#likes"/>">創作排行</a></li>
				</ul></li>
			<li><a href="<c:url value="/pages/music.jsp"/>">音樂搜尋</a></li>
			<li><a href="#">測試區</a>
				<ul>
					<li><a href="<c:url value="/testpages/test_table.jsp"/>">測試表格</a></li>
					<li><a href="<c:url value="/testpages/test_fileupload.jsp"/>">上傳測試</a></li>
					<li><a href="<c:url value="/testpages/test_google_login.jsp"/>">google登入</a></li>
				</ul></li>
			<c:choose>
				<c:when test="${not empty loginOK}">
					<li><a href="#">個人檔案</a>
						<ul>
							<li><a href="#">帳戶設定</a></li>
							<li><a href="#">上傳音樂</a></li>
							<li><a href="#">我的音樂</a></li>
							<li><a href="<c:url value="/members/logout"/>">登出</a></li>
						</ul></li>
				</c:when>
				<c:otherwise>
					<li><a href="#login" data-toggle="modal" data-target="#login_box">登入會員</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<a class="close"></a>
	</nav>
	<c:if test="${empty loginOK}">
		<div class="container">
			<div class="modal fade" id="login_box">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<form action="<c:url value="/members/loginAjax"/>" method="post" id="login_form">
							<div class="modal-header">
								<h4 class="modal-title">登入帳號</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="mbrId">請輸入帳號:</label><span class="text-danger"></span>
									<input type="text" name="mbrId" id="mbrId" placeholder="請輸入帳號" value="${rmUser}" class="form-control" required="required" />
								</div>
								<div class="form-group">
									<label for="mbrPwd">請輸入密碼:</label><span class="text-danger"></span>
									<input type="password" name="mbrPwd" id="mbrPwd" placeholder="請輸入密碼" value="${rmPassword}" class="form-control" required="required" />
								</div>
								<div class="form-check">
									<input type="checkbox" name="rememberme" id="rememberme" class="form-check-input" <c:if test="${not empty rememberMe and rememberMe eq 'enabled'}">checked="checked"</c:if> />
									<label for="rememberme" class="form-check-label">保持登入狀態</label>
								</div>
							</div>
							<div class="modal-body">
								<h6>
									還不是會員? <a href="<c:url value="/pages/register.jsp"/>">註冊會員</a>
								</h6>
							</div>
							<div class="modal-footer">
								<span id="login_err" class="text-danger"></span>
								<input type="submit" value="會員登入" class="btn" />
							</div>
							<div class="modal-body">
								<h4>使用社群登入:</h4>
								<div class="g-signin2" data-onsuccess="googleLogInSuccess"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</header>
