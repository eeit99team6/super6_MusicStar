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
<div class="wrapper scrollbar-dynamic">
	<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild wrapper scrollbar-dynamic">
		<div class="slideshow full">
			<c:forEach var="slide" items="${applicationScope.slides}">
				<section data-slide-image="${slide.slide_photo}">
					<a href="${slide.slide_link}">
						<article class="inner">
							<h2>${slide.slide_name}</h2>
							<P>${slide.slide_description}</P>
						</article>
					</a>
				</section>
			</c:forEach>
		</div>
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</div>
</body>
</html>