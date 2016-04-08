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
<link rel="stylesheet" href="css/style.css" media="screen"
	type="text/css" />
<link rel="stylesheet" href="css/score.css" media="screen"
	type="text/css" />
<link href="${pageContext.request.contextPath}/buttons/css/buttons.css" rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/buttons/js/buttons.js"></script>
<title>Insert title here</title>

</head>
<body>
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
			<a class="navbar-brand" href="main.jsp">平安龙院</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">主页</a></li>
				<li><a href="study.jsp">在线学习</a></li>
				<li><a href="exam.jsp">在线考试</a></li>
				<li class="active"><a href="#score">查看成绩</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container --> 
	</nav>
	<!-- /.navbar -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right" >
		
		<div class="col-md-6 col-md-offset-3 "  align="center">
			<table class="gray">
				<tr>
					<td>学号：</td>
					<td>2013034500</td>
					<td>姓名: </td>
					<td>张三</td>
				</tr>
				<tr>
					<td>院系：</td>
					<td>信息工程学院</td>
					<td>专业: </td>
					<td>软件工程</td>
				</tr>
				<tr>
					<td>班级：</td>
					<td>13级软工一班</td>
				</tr>
				<tr>
					<td>成绩：</td>
					<td>95分</td>
				</tr>
		</table>
		<button class="b  ">查看班级排名</button>
			<button class="b  ">补考</button>
	</div>
	</div>
	<br>
	<hr>
		<footer>
		<p>&copy; 龙岩学院保卫处 & GALAXY团队</p>
		</footer>	
	</div>
	
</body>
</html>