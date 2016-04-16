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
<link rel="stylesheet" href="css/exam.css" media="screen"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/exam.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
//禁止页面回退
javascript: window.history.forward(1);
var UnloadConfirm = {};
UnloadConfirm.MSG_UNLOAD = "数据尚未保存，离开后可能会导致试卷自动提交\n\n您确定要离开吗？";
UnloadConfirm.set = function(a) {
    window.onbeforeunload = function(b) {
        b = b || window.event;
        b.returnValue = a;
        return a
    }
};
UnloadConfirm.clear = function() {
    fckDraft.delDraftById();
    window.onbeforeunload = function() {}
};
UnloadConfirm.set(UnloadConfirm.MSG_UNLOAD);
	function keydown() {
		if (event.keyCode == 8) {
			event.keyCode = 0;
			event.returnValue = false;
			alert("当前设置不允许使用退格键");
		}
		if (event.keyCode == 13) {
			event.keyCode = 0;
			event.returnValue = false;
			alert("当前设置不允许使用回车键");
		}
		if (event.keyCode == 116) {
			event.keyCode = 0;
			event.returnValue = false;
			alert("当前设置不允许使用F5刷新键");
		}
		if ((event.altKey)
				&& ((window.event.keyCode == 37) || (window.event.keyCode == 39))) {
			event.returnValue = false;
			alert("当前设置不允许使用Alt+方向键←或方向键→");
		}
		if ((event.ctrlKey) && (event.keyCode == 78)) {
			event.returnValue = false;
			alert("当前设置不允许使用Ctrl+n新建IE窗口");
		}
		if ((event.shiftKey) && (event.keyCode == 121)) {
			event.returnValue = false;
			alert("当前设置不允许使用shift+F10");
		}
	}
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
			<a class="navbar-brand" href="main.jsp">平安龙院</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath }/main.jsp">主页</a></li>
				<li><a href="${pageContext.request.contextPath }/client_study/studyUI.action">在线学习</a></li>
				<li class="active"><a href="#about">在线考试</a></li>
				<li><a href="${pageContext.request.contextPath }/score.jsp">查看成绩</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->
	<div class="data_list">
		<div class="data_info">
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;考试时间：<strong>20分钟</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				计时：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="useTime"
					style="font-weight: bold;"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				剩余时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="remainTime"
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