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
<style>

</style>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	getSubsectionContent('${pageContext.request.contextPath}/client_study/getSubsectionByUser.action');	
});
</script>
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
				href="${pageContext.request.contextPath }/client_home/toHomePage.action">平安龙院</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a
					href="${pageContext.request.contextPath }/client_home/toHomePage.action">主页</a></li>
				<li class="active"><a href="#">在线学习</a></li>
				<li><a
					href="${pageContext.request.contextPath}/client_exam/toExamPage.action">在线考试</a></li>
				<li><a
					href="${pageContext.request.contextPath}/client_score/toScoreUI.action">查看成绩</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right" style="height:700px">

			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="row" >
					<div class="jumbotron">
						<h1>${subsection.subsectionName }</h1>
					</div>
					<div class="row" id="content" > ${subsection.subsectionContent }<br>
						
					</div>
					<!--/row-->
				</div>
			</div>
			<!--/.col-xs-12.col-sm-9-->

			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">

				<ul id="accordion" class="accordion">
					<c:forEach items="${sectionList }" var="section" varStatus="status"
						step="1" begin="0">
						<li>
							<div class="link">
								<i class="fa fa-paint-brush"></i>${section.sectionName }<i
									class="fa fa-chevron-down"></i>
							</div>

							<ul class="submenu">
								<c:forEach items="${section.subsections }" var="subsection">
									<c:if test="${subsection.subsectionChecked}">
									<li><a href="javascript:void(0)"
										onclick="getSubsectionContent('${pageContext.request.contextPath}/client_study/getSubsection/${subsection.subsectionId}.action')">${subsection.subsectionName}</a></li>
									</c:if>
								</c:forEach>
							</ul>

						</li>
					</c:forEach>
					<div class="hold hold1" >
						<div class="pie pie1"></div>
					</div>
					<div class="hold hold2">
						<div class="pie pie2"></div>
					</div>
					<div class="bg"></div>
					<div class="time">
						<span></span><em></em>
					</div>
				</ul>
				
			</div>
			

		</div>
		<!--/.sidebar-offcanvas-->
	</div>
	<!--/row-->


	<hr>
	<footer>
	<p>&copy; 龙岩学院安全工作处 & GALAXY团队</p>
	</footer>

	</div>
	<!--/.container-->
	<div class="modal fade bs-example-modal-sm" id="mymodal2" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
				</div>
				<div class="modal-body">....</div>
				<div class="modal-footer">
						
					<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="YesSkip()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" id="mymodal" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
				</div>
				<div class="modal-body">....</div>
				<div class="modal-footer">
						
					<button type="button" class="btn btn-primary" data-dismiss="modal" >确定</button>
					
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery.pin.js"></script>
<script src="${pageContext.request.contextPath}/js/offcanvas.js"></script>
<script
	src="${pageContext.request.contextPath}/js/ie10-viewport-bug-workaround.js"></script>
<script src="${pageContext.request.contextPath}/js/study.js"></script>

</html>