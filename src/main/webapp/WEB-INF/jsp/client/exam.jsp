<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen"
	type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/exam.css" media="screen"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/exam.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({   
		url:'${pageContext.request.contextPath}/client_exam/getEpaper.action',   
		type:'get',   
		async : true, //默认为true 异步   
		dataType:'json',
	     success:function(data){  
	    	 var html = '';
	    	 var check = data.check;
	    	 console.info(data.status);
	    	 if(data.status == 1){
		    	 $.each(data.choiceList,function(i,item){
		    		 //choiceList.push(i+"");
		    		  html +='<strong>[&nbsp;'+(i+1)+'&nbsp;]&nbsp;'+item.itempoolQuestion+'</strong><br/><br/>';
		    		 if(item.a.length > 0){ 
		    		 	html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect"  value="A"/>'+item.a+'</label>';
		    		 }
		    		 if(item.b.length > 0){
		    		 	html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect" value="B"/>'+item.b+'</label>';
		    		 }
		    		 if(item.c.length > 0){
		    		 	html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect" value="C"/>'+item.c+'</label>';
		    		 }
		    		 if(item.d.length > 0){
		    		 	html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect" value="D"/>'+item.d+'</label>';
		    		 }
		    	});
		    	 $("#exam").append(html);
	    	 }else if(data.status == 0){
	    		 html +="<div class='bg'><div class='cont'><div class='c1'><img src='/SafeLyun/images/01.png' class='img1' /></div><h2>哎呀…现在不是考试时间</h2></div></div>";
	    		 $(".data_list").empty().append(html);
	    	 }else if(data.status == 2){
	    		 html +="<div class='bg'><div class='cont'><div class='c1'><img src='/SafeLyun/images/01.png' class='img1' /></div><h2>已通过，不能再进行考</h2></div></div>";
	    		 $(".data_list").empty().append(html);
	    	 }else if(data.status == 3){
	    		 html +="<div class='bg'><div class='cont'><div class='c1'><img src='/SafeLyun/images/01.png' class='img1' /></div><h2>哎呀…不好意思！没有补考次数了！！</h2></div></div>";
	    		 $(".data_list").empty().append(html);
	    	 }
	    	 
	     }
	});
});
</script>
<title>Insert title here</title>

</head>
<body onkeydown="keydown()">
	<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }/client_home/toHomePage.action">平安龙院</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath }/client_home/toHomePage.action">主页</a></li>
				<li><a href="${pageContext.request.contextPath }/client_study/studyUI.action">在线学习</a></li>
				<li class="active"><a href="#about">在线考试</a></li>
				<li><a href="${pageContext.request.contextPath }/client_score/toScoreUI.action">查看成绩</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->
	<div class="data_list">
		<div class="data_info">
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;考试时间：<strong>20分钟</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				计时：&nbsp;&nbsp;&nbsp;&nbsp;<font id="useTime"
					style="font-weight: bold;"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				剩余时间：&nbsp;&nbsp;&nbsp;&nbsp;<font id="remainTime"
					style="font-weight: bold;"></font>
			</p>
			<hr />
			<p class="examTitle">Java试卷一&nbsp;&nbsp;考试卷</p>

		</div>
		<div class="container">
			<div class="data_exam_content col-md-8 col-md-offset-2">
				<form id="myForm"
					action="${pageContext.request.contextPath}/client_exam/submitTestPaper.action"
					method="post">
					<input type="hidden" name="exam.student.id" value="" /> <input
						type="hidden" name="exam.paper.id" value="1" /> <strong><big>一，单选题</big></strong>(每题20分，答错不得分)<br />
					<br />
					<div id="exam"></div>
					<button class="btn btn-primary" type="submit">交卷</button>
				</form>
				<br>
				<hr>
				<footer>
				<p>&copy; 龙岩学院保卫处 & GALAXY团队</p>
				</footer>
			</div>

		</div>
		<script type="text/javascript">
			
		</script>
</body>
</html>