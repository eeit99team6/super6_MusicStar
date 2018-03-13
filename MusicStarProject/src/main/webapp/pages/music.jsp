<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Music_Select</title>
  <jsp:include page="/includes/main_css.jsp" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style type="text/css">
  		/* Style the buttons */
		.btn {
		  border: none;
		  outline: none;
		  padding: 10px 16px;
		  background-color: #f1f1f1;
 		  cursor: pointer;
 		} 
		
		/* Style the active class (and buttons on mouse-over) */
 		.active, .btn:hover {
 		  background-color: #666;
 		  color: white;
		}
		
		.btn-color {
		  background-color: #3C9DF2;
		}
		.btn-hover:hover {
		  background-color: #0475d8;
 		  color: white;
		}
		
		.btn-primary.raised {
			box-shadow: 0 3px 0 0 #007299;
		}
		.btn-primary.raised:active, .btn-primary.raised.active {
			background: #33a6cc;
			box-shadow: none;
			margin-bottom: -3px;
			margin-top: 3px;
		}
		.nav-tabs{
  			background-color:white;
		}
		.tab-content{
		    background-color:#666;
		    color:#fff;
		    padding:5px;
		    border-top-left-radius:0px;
			border-top-right-radius:10px;
			border-bottom-right-radius:10px;
			border-bottom-left-radius:10px;
		}
		.nav-tabs > li > a{
		  	border: medium none;
		}
		.nav-tabs > li > a:hover{
		  	background-color: #666;
		    border: medium none;
		    border-radius: 0;
		    color:#fff;
		    border-top-left-radius:10px;
		    border-top-right-radius:10px;
		}
		
		#musicDiv:hover {
			box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		}
		#litab {
			background-color: transparent;
		}
		label {
			color: black;
			margin: 5px;
		}
  </style>
  <jsp:include page="/includes/main_js.jsp" />
</head>
<body>
<jsp:include page="/includes/main_header.jsp" />
	<!-- main_container start -->
	<div id="main_container" class="container-fuild">
<div class="container" style="margin-top:50px;margin-bottom:25px">
  <h2>MusicStar Selector</h2>
  <p>You can click one tab to change selector =)</p>

  <ul class="nav nav-tabs">
    <li class="active" id="litab"><a data-toggle="tab" href="#home">Button</a></li>
    <li id="litab"><a data-toggle="tab" href="#menu1">Input</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">    
	<form action="<c:url value="/music/radioSelect" />" method="get" class="card border-primary mb-3">
	<div class="card-header" style="margin:5px">
		<div>
			<h4 style="padding-top:3.5px;color:black">Style:</h4>
		</div>
		<div id="styleDiv" style="background-color:lightblue;display:inline-block;padding:8px 15px 0 16px;margin:0 0 5px 5px;padding:0 0 5px 0;border-radius:7px">
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
			<h4 style="padding-top:3.5px;color:black">Singer:</h4>
		</div>
		<div id="singerDiv" style="background-color:lightblue;display:inline-block;padding:8px 15px 0 0;margin:0 0 5px 5px;padding:0 0 5px 0;border-radius:7px">
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
		</div>
	</div>
		<div class="card-body" style="margin:0 0 0 10px">
			<button type="submit" class="btn btn-primary btn-lg raised btn-color btn-hover">submit</button>
		</div>
	</form>
    </div>
    
    
    <div id="menu1" class="tab-pane fade">
      <div class="card border-primary mb-3">
			<div class="card-header" style="margin:10px;color:black">Select Music Name Here</div>
				<form name="myData" action="<c:url value="/music/autoComplete" />" method="get">
					<div class="card-body" style="margin:10px">
						<input type="text" class="form-control" id="txtSearch" name="keyword" placeholder="Write here">
						<div id="div1"></div>
					</div>
					<div class="card-footer" style="margin:10px">
						<button type="submit" class="btn btn-primary btn-lg raised btn-color btn-hover">submit</button>
						<c:if test="${empty bean}">
							<div style="display:inline-block;margin:0 0 0 10px">
								<h5 style="color:red" id="errors">${errors.error}</h5>
							</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
    </div>
  </div>
  
<c:if test="${not empty bean}">
	<div class="container">
        <div class="row">
			<c:forEach var="beanVar" items="${bean}">
				<div style="height:auto;width:200px;display:inline-block;margin:1em;border-color:chocolate;border-style:none solid solid none;border-width:1px;border-radius:7px;" class="col-xl-2" id="musicDiv">

					<div style="margin:15px" class="view overlay">
						<a href="${beanVar.music_link}">
							<img style="max-width:100%;height:auto;height:100px;width:100px" class="img-fluid img-responsive center-block" src="${beanVar.music_photo}" title="${beanVar.music_name}">
						</a>
						<div class="mask rgba-orange-light"></div>
					</div>
					
					<div style="margin:5px">Singer: ${beanVar.music_member_id}</div>
				
					<div style="margin:5px">Style: ${beanVar.music_style_id}</div>
					
					<div style="margin:5px" id="like">Like: <img style="cursor: pointer" name="like" src="/FileSource/images/heart.gif"></div>
				
					<div style="margin:5px">Name: <a href="${beanVar.music_link}">${beanVar.music_name}</a></div>
				
					<div style="margin:5px">Description: ${beanVar.music_description}</div>
				
				</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>

	<script>
		//When first loaded nothing radio
		$(document).ready(function(){
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

			if($("#errors").text() != ""){
				$("ul li:eq(0)").attr("class","");
				$("ul li:eq(1)").attr("class","active");
				$("#home").attr("class","tab-pane fade");
				$("#menu1").attr("class","tab-pane fade in active");
			}
		})
		
		// Like
		var like = $("img[name*='like']");
		for(var i = 0;i<$("img[name*='like']").length;i++){
			like[i].addEventListener("click",function(){
				var sure = confirm("您確定要按讚嗎?");
				if(sure == true){
					alert("您已按下讚");
				}else{
					alert("您取消按讚");
				}
			})
		}
		
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
		
	</script>
	</div>
	<!-- main_container end -->
	<jsp:include page="/includes/main_aside.jsp" />
	<jsp:include page="/includes/main_footer.jsp" />
</body>
</html>