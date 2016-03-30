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
<script
	src="${pageContext.request.contextPath}/js/exam.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
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
				<li class="active"><a href="#about">在线考试</a></li>
				<li><a href="score.jsp">查看成绩</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->
	<div class="data_list">
	<div class="data_info">
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;考试时间：<strong>20分钟</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		计时：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="useTime" style="font-weight: bold;"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		剩余时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="remainTime" style="font-weight: bold;"></font></p>
		<hr/>
		<p class="examTitle">Java试卷一&nbsp;&nbsp;考试卷</p>
		
	</div>
	<div class="container">
	<div class="data_exam_content col-md-8 col-md-offset-2" >
		<form id="myForm" action="exam!add" method="post">
		<input type="hidden" name="exam.student.id" value=""/>
		<input type="hidden" name="exam.paper.id" value="1"/>
		<strong><big>一，单选题</big></strong>(每题20分，答错不得分)<br/><br/>
		
			<strong>[&nbsp;1&nbsp;]&nbsp;有一段java应用程序，它的主类名是a1，那么保存 它的源文件名可以是？</strong><br/><br/>
			<label class="radio">
				<input type="radio" name="id-r-2" value="A"/>
				A. a1.java
			</label>
			<label class="radio">
				<input type="radio" name="id-r-2" value="B"/>
				B. a.class
			</label>
			<label class="radio">
				<input type="radio" name="id-r-2" value="C"/>
				C. a1
			</label>
			<label class="radio">
				<input type="radio" name="id-r-2" value="D"/>
				D. 都可以
			</label>
			<br/>
		
			<strong>[&nbsp;2&nbsp;]&nbsp;在Java中？</strong><br/><br/>
			<label class="radio">
				<input type="radio" name="id-r-5" value="A"/>
				A. 一个子类可以有多个父类，一个父类也可以有多个子类
			</label>
			<label class="radio">
				<input type="radio" name="id-r-5" value="B"/>
				B. 一个子类可以有多个父类，但一个父类只可以有一个子类
			</label>
			<label class="radio">
				<input type="radio" name="id-r-5" value="C"/>
				C. 一个子类可以有一个父类，但一个父类可以有多个子类
			</label>
			<label class="radio">
				<input type="radio" name="id-r-5" value="D"/>
				D. 上述说法都不对
			</label>
			<br/>
		
			<strong>[&nbsp;3&nbsp;]&nbsp;下列关于Java语言的特点，描述错误的是？</strong><br/><br/>
			<label class="radio">
				<input type="radio" name="id-r-7" value="A"/>
				A. Java是跨平台的编程语言
			</label>
			<label class="radio">
				<input type="radio" name="id-r-7" value="B"/>
				B. Java支持分布式计算
			</label>
			<label class="radio">
				<input type="radio" name="id-r-7" value="C"/>
				C. Java是面向过程的编程语言
			</label>
			<label class="radio">
				<input type="radio" name="id-r-7" value="D"/>
				D. Java是面向对象的编程语言
			</label>
			<br/>
		
		<br/>
		<strong><big>一，多选题</big></strong>(每题30分，答错不得分)<br/><br/>
		
			<strong>[&nbsp;1&nbsp;]&nbsp;下面关于继承的叙述哪些是正确的？</strong><br/><br/>
			<label class="checkbox">
				<input type="checkbox" name="id-c-4" value="A"/>
				A. 在java中只允许单继承。
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-4" value="B"/>
				B. 在java中一个类只能实现一个接口。
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-4" value="C"/>
				C. 在java中一个类不能同时继承一个类和实现一个接口。
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-4" value="D"/>
				D. java的单一继承使代码更可靠。
			</label>
			<br/>
		
			<strong>[&nbsp;2&nbsp;]&nbsp;测试题目2</strong><br/><br/>
			<label class="checkbox">
				<input type="checkbox" name="id-c-20" value="A"/>
				测试选项一2
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-20" value="B"/>
				测试选项二2
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-20" value="C"/>
				测试选项三2
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-20" value="D"/>
				测试选项四2
			</label>
			<br/>
		
		<button class="btn btn-primary" type="submit">交卷</button>
		</form>
	</div>
</div>
	

</body>
</html>