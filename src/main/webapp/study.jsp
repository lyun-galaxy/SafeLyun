<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" media="screen"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/offcanvas.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/study.js"></script>
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
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/main.jsp">平安龙院</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/main.jsp">主页</a></li>
				<li class="active"><a href="#">在线学习</a></li>
				<li><a href="${pageContext.request.contextPath}/exam.jsp">在线考试</a></li>
				<li><a href="${pageContext.request.contextPath}/score.jsp">查看成绩</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="jumbotron">${subsection.subsectionName }</div>
				<div class="row" id="content">${subsection.subsectionContent }</div>
				<!--/row-->
			</div>
			<!--/.col-xs-12.col-sm-9-->

			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">

				<ul id="accordion" class="accordion">
					<c:forEach items="${sectionList }" var="section" varStatus="status" step="1" begin="0">
						<li>
							<div class="link">
								<i class="fa fa-paint-brush"></i>${section.sectionName }<i
									class="fa fa-chevron-down"></i>
							</div>

							<ul class="submenu">
								<c:forEach items="${section.subsections }" var="subsection">
									<li><a href="javascript:void(0)"
										onclick="getSubsectionContent('${pageContext.request.contextPath}/client_study/getSubsection/${subsection.subsectionId}.action')">${subsection.subsectionName}</a></li>
								</c:forEach>
							</ul>

						</li>
					</c:forEach>
					<!-- <li class="open">
						<div class="link">
							<i class="fa fa-paint-brush"></i>第一章 自然灾害<i
								class="fa fa-chevron-down"></i>
						</div>
						<ul class="submenu"  style="display:block;">
							<li><a href="#" style="background: #b63b4d;">第一课 可怕的龙卷风</a></li>
							<li><a href="#">第二课 突遇疯狂海啸</a></li>

						</ul>
					</li>
					<li>
						<div class="link">
							<i class="fa fa-code"></i>第二章 意外伤害<i class="fa fa-chevron-down"></i>
						</div>
						<ul class="submenu">
							<li><a href="#">第一课 突遇车祸</a></li>
							<li><a href="#">第二课 警惕宿舍起火</a></li>

						</ul>
					</li>
					<li>
						<div class="link">
							<i class="fa fa-mobile"></i>第三章 公共卫生<i class="fa fa-chevron-down"></i>
						</div>
						<ul class="submenu">
							<li><a href="#">第一课 安全用药</a></li>
							<li><a href="#">第二课 将毒食品“掷出窗外“</a></li>

						</ul>
					</li>
					<li><div class="link">
							<i class="fa fa-globe"></i>第四章 其他安全<i class="fa fa-chevron-down"></i>
						</div>
						<ul class="submenu">
							<li><a href="#">第一课 核辐射危害健康</a></li>
							<li><a href="#">第二课 在野外迷路</a></li>

						</ul></li> -->
				</ul>

			</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<!--/row-->


		<hr>
		<footer>
		<p>&copy; 龙岩学院保卫处 & GALAXY团队</p>
		</footer>

	</div>
	<!--/.container-->
</body>
<script src="${pageContext.request.contextPath}/js/jquery.pin.js"></script>
<script src="${pageContext.request.contextPath}/js/offcanvas.js"></script>
<script
	src="${pageContext.request.contextPath}/js/ie10-viewport-bug-workaround.js"></script>

<script type="text/javascript">
	$("#accordion").pin({
		padding : {
			top : 45,
			bottom : 10
		},
		minWidth : 940
	});
	function getSubsectionContent(url) {
		$.ajax({
			type : 'get',
			url : url,
			dataType : 'json',
			cache : false,
			success : function(data) {
				$(".jumbotron").empty().append(
						"<h1>" + data.subsectionName + "</h1>");
				$("#content").empty().append(data.subsectionContent);
			},
			error : function() {
				alert('error');
			}
		});
	}
	//getSubsectionContent('${pageContext.request.contextPath}/client_study/getSubsection/1.action');
</script>
</html>