<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/buttons/css/buttons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/buttons/js/buttons.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-2 " style="margin-top: 20px; margin-bottom: 20px">
				<img alt="" src="${pageContext.request.contextPath}/images/123.jpg" width="64px" height="64px">
			</div>
			<div class="col-md-2 col-md-offset-8"
				style="margin-top: 20px; margin-bottom: 20px">
				<p>欢迎，${student.studentName }同学</p>
				<a href="${pageContext.request.contextPath}/client_login/logout.action">注销</a>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row ">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img class="first-slide" src="${pageContext.request.contextPath}/images/1.jpg" alt="First slide">
						<div class="container">
							<div class="carousel-caption">
								<h1>
									<img alt="" src="${pageContext.request.contextPath}/images/title.png">
								</h1>
								<p>该系统专门为学生提供在线学习，在线考试等功能。</p>
								<p>
									<a class="btn btn-lg btn-primary" href="#" role="button">Sign
										up today</a>
								</p>
							</div>
						</div>
					</div>
					<div class="item">
						<img class="second-slide" src="${pageContext.request.contextPath}/images/2.jpg" alt="Second slide">
						<div class="container">
							<div class="carousel-caption">
								<h1>
									<img alt="" src="${pageContext.request.contextPath}/images/title.png">
								</h1>
								<p>该系统专门为学生提供在线学习，在线考试等功能。</p>
								<p>
									<a class="btn btn-lg btn-primary" href="#" role="button">Learn
										more</a>
								</p>
							</div>
						</div>
					</div>
					<div class="item">
						<img class="third-slide" src="${pageContext.request.contextPath}/images/3.jpg" alt="Third slide">
						<div class="container">
							<div class="carousel-caption">
								<h1>
									<img alt="" src="${pageContext.request.contextPath}/images/title.png">
								</h1>
								<p>该系统专门为学生提供在线学习，在线考试等功能。</p>
								<p>
									<a class="btn btn-lg btn-primary" href="#" role="button">Browse
										gallery</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-4" style="margin-top: 20px; margin-bottom: 20px">
				<div class="col-md-6 col-md-offset-3" align="center">

					<button
						class="big button button-primary button-circle button-longshadow "
						onclick="study()">
						<i class="icon-user-md icon-3x"></i>
					</button>
					<div class="caption">
						<h3>当前进度</h3>
						<p>${studyschedule.subsection.subsectionName }</p>
						<p>
							<button class="button button-3d button-primary button-pill"
								onclick="study()">开始学习</button>
						</p>
					</div>

				</div>
			</div>
			<div class="col-md-4" style="margin-top: 20px; margin-bottom: 20px">
				<div class="col-md-6 col-md-offset-3" align="center">

					<button
						class="big button button-royal button-circle button-longshadow "
						onclick="exam()">
						<i class="icon-file-alt icon-3x"></i>
					</button>
					<div class="caption">
						<h3>考试系统</h3>
						<p>
							<button class="button button-3d button-royal button-pill"
								onclick="exam()">进入考试</button>
						</p>
					</div>

				</div>
			</div>
			<div class="col-md-4" style="margin-top: 20px; margin-bottom: 20px">
				<div class="col-md-6 col-md-offset-3" align="center">

					<button
						class="big button button-action button-circle button-longshadow "
						onclick="score()">
						<i class="icon-list-alt icon-3x"></i>
					</button>
					<div class="caption">
						<h3>个人成绩</h3>
						<p>成绩详情</p>
						<p>
							<button class="button button-3d button-action button-pill"
								onclick="score()">查看成绩</button>
						</p>
					</div>

				</div>
			</div>
		</div>
		<hr>
		<footer>
		<p>&copy; 龙岩学院保卫处 & GALAXY团队</p>
		</footer>
	</div>

</body>
<script type="text/javascript">
	function study() {
		window.location = "${pageContext.request.contextPath }/client_study/studyUI.action";
	}
	function exam() {
		window.location = "${pageContext.request.contextPath}/client_exam/toExamPage.action";
	}
	function score() {
		window.location = "${pageContext.request.contextPath }/client_score/toScoreUI.action";
	}
</script>
</html>