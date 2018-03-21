<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>進行投票</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/voting.css" rel="stylesheet" />
<style type="text/css">
.title-title {
    margin-bottom: 20px;
}
</style>
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/lodash.min.js"></script>
<script src="${ctx}/assets/js/jquery.countdown.min.js"></script>
<script src="${ctx}/assets/js/voting.js"></script>
</head>
<body>
	<div class="wrapper scrollbar-dynamic">
		<jsp:include page="/includes/main_header.jsp" />
		<!-- main_container start -->
		<div id="main_container" class="container-fuild">
			<h1 id="contest_title" class="display-2 title-title">賽事投票中</h1>
			<div class="container">
				<h3 id="vote_description" class="text-center"></h3>
				<div class="counter_container text-center">
					<div class="counter_box">
						<div class="pure-g-r">
							<div class="pure-u-1-2">
								<div class="main_counter">
									<p>距離投票結束還有 :</p>
									<div class="countdown-container" id="main-counter">
										<div class="time weeks flip">
											<span class="count curr top">00</span> <span
												class="count next top">00</span> <span
												class="count next bottom">00</span> <span
												class="count curr bottom">00</span> <span class="label">週</span>
										</div>
										<div class="time days flip">
											<span class="count curr top">00</span> <span
												class="count next top">00</span> <span
												class="count next bottom">00</span> <span
												class="count curr bottom">00</span> <span class="label">天</span>
										</div>
										<div class="time hours flip">
											<span class="count curr top">00</span> <span
												class="count next top">00</span> <span
												class="count next bottom">00</span> <span
												class="count curr bottom">00</span> <span class="label">時</span>
										</div>
										<div class="time minutes flip">
											<span class="count curr top">00</span> <span
												class="count next top">00</span> <span
												class="count next bottom">00</span> <span
												class="count curr bottom">00</span> <span class="label">分</span>
										</div>
										<div class="time seconds flip">
											<span class="count curr top">00</span> <span
												class="count next top">00</span> <span
												class="count next bottom">00</span> <span
												class="count curr bottom">00</span> <span class="label">秒</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="display_area" class="row"></div>
				<input type="hidden" id="contestId" value="${param.contestId}">
			</div>
		</div>
		<!-- main_container end -->
		<jsp:include page="/includes/main_aside.jsp" />
		<jsp:include page="/includes/main_footer.jsp" />
	</div>
</body>
</html>