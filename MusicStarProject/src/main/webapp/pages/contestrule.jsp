<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contest Rule Page</title>
<jsp:include page="/includes/main_css.jsp" />
<style type="text/css">
.marginTest {
	margin-top: 20px;
}

</style>
<link href="${ctx}/assets/css/common_style/allPages.css"
	rel="stylesheet" />

<jsp:include page="/includes/main_js.jsp" />
<script>
	
</script>
</head>
<body>
	<div class="wrapper scrollbar-dynamic">
		<jsp:include page="/includes/main_header.jsp" />
		<!-- main_container start -->
		<div id="main_container" class="container-fuild">
			<div class="title-title">賽事規則</div>

			<div class="container">
				<div class="p-top-4 p-bottom-6 marginTest">

					<div class="desktop-hide m-bottom-6"></div>
					<!-- /desktop-hide -->

					<div class="p-top-2">
						<div class="row">
							<div class="col-md-8 work-content">

								<h3>
									<strong>一、比賽辦法</strong>
								</h3>

								<ul>
									<li>參加資格：一半(含)以上的團員擁有在學證明之學生(含碩、博生)若過程中有團員更動，須通知臺大音樂節工作團隊</li>
									<li>徵選曲目須為原創歌曲。</li>
									<li>主辦單位（第五屆台大音樂節）保留更改徵選權力。</li>
								</ul>

								<p>&nbsp;</p>

								<h3>
									<strong>二、Demo 規定</strong>
								</h3>

								<ol>
									<li>將 Demo 以個人帳號上傳至
										StreetVoice「第五屆台大音樂節學生樂團野台徵選」徵選活動，並於報名系統中附上音檔連結。</li>
									<li>Demo 時間長度及錄製方法不限。</li>
									<li>若作品有歌詞，請於 StreetVoice 音檔之「歌詞」欄位附上完整歌詞。</li>
									<li>每團於報名系統中只得附上一份 Demo 之 StreetVoice 連結。</li>
									<li>Demo 如需重新錄製、上傳，須主動將新的 Demo 連結寄信至官方信箱，並不得更改曲目。</li>
									<li>徵選結果共錄取八組。</li>
								</ol>

								<p>&nbsp;</p>

								<h3>
									<strong>三、報名方式</strong>
								</h3>

								<p>
									<strong>Step 1：</strong>
								</p>

								<ol>
									<li>前往 StreetVoice 上傳 Demo請先註冊加入 StreetVoice
										會員，並依系統指示完成會員帳號啟用（已是會員者可直接進入下一步驟）</li>
									<li>點選頁面右方的「我要參加徵選」按鈕</li>
									<li>詳讀比賽辦法、同意相關規定後，點選「下一步」</li>
									<li>在列表中找到您欲參加徵選的作品，按下該作品右方的「參加」按鈕。選擇完畢後點選「下一步」即完成報名</li>
								</ol>

								<p>
									<strong>Step 2：</strong>前往台大音樂節報名表單（<a
										href="https://goo.gl/forms/bz1Oidhn3eqwSAoX2">https://goo.gl/forms/bz1Oidhn3eqwSAoX2</a>）
									填寫報名資料
								</p>

								<p>
									<strong>Step 3：</strong>於報名後 1-2 天後至報名表單填寫之信箱收取官方寄出信件
								</p>

								<p>
									<strong>Step 4：</strong>收取信件後，繳交報名費200元/團，匯款資訊：
								</p>

								<p style="margin-left: 40px;">
									銀行名稱：郵局<br /> 分行名稱：台大郵局<br /> 戶名：國立臺灣大學音樂產業與唱片製作實務社崔鑫<br />
									帳號：<a href="tel:00012360640257">00012360640257</a>
								</p>

								<p>&nbsp;</p>

								<h3>
									<strong>四、比賽時程</strong>
								</h3>

								<ul>
									<li>報名：03 / 10（六）～ 03 / 25（日）17:00</li>
									<li>繳交報名費用：03 / 10（六）～ 03 / 27（二）17:00</li>
									<li>人氣投票：03 / 26（四）～ 04 / 08（日）</li>
									<li>評審時間：03 / 26（四）～ 04 / 15（日）</li>
									<li>公布結果：04 / 16（一）</li>
								</ul>

								<p>&nbsp;</p>

								<h3>
									<strong>五、活動獎勵</strong>
								</h3>

								<p>入選樂團將於台大音樂節野台時間 5/19~5/20 表演。</p>

								<p>&nbsp;</p>

								<h3>
									<strong>六、評審辦法</strong>
								</h3>

								<p>由主辦單位聘請音樂人士擔任評審，並同步統計「喜歡」點擊次數作為評選指標。</p>

								<p style="margin-left: 40px;">&nbsp;</p>

								<p>&nbsp;</p>

							</div>
							<!-- /m-top-6 -->
						</div>
						<!-- /col-md-8 -->
					</div>
					<!-- /row -->
				</div>
				<!-- /m-top-2 -->
			</div>
			<!-- /p-top-4 p-bottom-6 -->
		</div>
		<!-- /container -->







	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
	</div>
</body>
</html>