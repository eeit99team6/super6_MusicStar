<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MusicStar 首頁</title>
<jsp:include page="/includes/main_css.jsp" />
<link href="${ctx}/assets/css/slideshow.css" rel="stylesheet" />
<jsp:include page="/includes/main_js.jsp" />
<script src="${ctx}/assets/js/jquery.scrollex.min.js"></script>
<script src="${ctx}/assets/js/skel.min.js"></script>
<script src="${ctx}/assets/js/util.js"></script>
<script src="${ctx}/assets/js/slideshow.js"></script>
</head>
<body>
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
	<div id="main_container" class="container-fuild">
		<div class="slideshow full">
			<section data-slide-image="${ctx}/assets/img/slideshow/night.jpg">
				<a href="#">
					<article class="inner">
						<h2>音樂比賽1</h2>
						<P>點擊進入音樂比賽1</P>
					</article>
				</a>
			</section>
			<section data-slide-image="${ctx}/assets/img/slideshow/sky.jpg">
				<a href="#">
					<article class="inner">
						<h2>音樂比賽2</h2>
						<P>點擊進入音樂比賽2</P>
					</article>
				</a>
			</section>
			<section data-slide-image="${ctx}/assets/img/slideshow/sea.jpg">
				<a href="#">
					<article class="inner">
						<h2>音樂比賽3</h2>
						<P>點擊進入音樂比賽3</P>
					</article>
				</a>
			</section>
			<section data-slide-image="${ctx}/assets/img/slideshow/baston.jpg">
				<a href="#">
					<article class="inner">
						<h2>音樂比賽4</h2>
						<P>點擊進入音樂比賽4</P>
					</article>
				</a>
			</section>

			<section data-slide-image="${ctx}/assets/img/slideshow/beach.jpg">
				<a href="#">
					<article class="inner">
						<h2>音樂比賽5</h2>
						<P>點擊進入音樂比賽5</P>
					</article>
				</a>
			</section>
		</div>
	</div>
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>