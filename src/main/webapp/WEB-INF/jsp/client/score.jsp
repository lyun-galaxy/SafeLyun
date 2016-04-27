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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/score.css" media="screen"
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
			<a class="navbar-brand" href="${pageContext.request.contextPath }/client_home/toHomePage.action">平安龙院</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath }/client_home/toHomePage.action">主页</a></li>
				<li><a href="${pageContext.request.contextPath }/client_study/studyUI.action">在线学习</a></li>
				<li><a href="${pageContext.request.contextPath}/client_exam/toExamPage.action">在线考试</a></li>
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
					<td>${student.studentNumber }</td>
					<td>姓名: </td>
					<td>${student.studentName }</td>
				</tr>
				<tr>
					<td>院系：</td>
					<td>${student.classes.specialty.department.departmentName }</td>
					<td>专业: </td>
					<td>${student.classes.specialty.specialtyName }</td>
				</tr>
				<tr>
					<td>班级：</td>
					<td>${student.classes.classesName }</td>
				</tr>
				<tr>
					<td>成绩：</td>
					<td>${student.score.scoreMark}</td>
				</tr>
		</table>
		
		<button class="b  " onclick="getClassesScore()">查看班级排名</button>
			<button class="b  " onclick="make_up_test()">补考</button>
	</div>
	</div>
	<br>
	<hr>
		<footer>
		<p>&copy; 龙岩学院保卫处 & GALAXY团队</p>
		</footer>	
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
<script type="text/javascript">
	function getClassesScore() {
		
		$.ajax({
			type : 'get',
			url : '${pageContext.request.contextPath}/client_score/getClassesStudentScore.action',
			dataType : 'json',
			cache : false,
			success : function(data) {
				var html = "<tr><th>编号</th><th>学号</th><th>姓名</th><th>院系</th><th>专业</th><th>班级</th><th>成绩</th></tr>";
				console.info(data);
				$(data.studentlist).each(function(i,d){
					
					html += "<tr><td>"+(i+1)+"</td><td>"+d.studentNumber+"</td><td>"+d.studentName+"</td><td>"+d.department+"</td><td>"+d.specialties+"</td><td>"+d.classes+"</td><td>"+d.score+"</td></tr>";
				
				});
				
				$(".gray").removeClass("gray").addClass("table").addClass("table-bordered").addClass("table-hover").empty().append(html);
			},
			error : function() {
				$(".modal-body").empty().append("请求超时");
				$("#mymodal").modal("show");
			}
		});
	}
	function make_up_test(){
		$.ajax({
			type : 'get',
			url : '',
			dataType : 'json',
			cache : false,
			success : function(data) {
				if(data.status == 1) {
					window.location = data.url;
				}
				else if(data.status == 2) {
					$(".modal-body").empty().append("已通过，不能再进行补考");
					$("#mymodal").modal("show");
				} else if(data.status == 3 ) {
					$(".modal-body").empty().append("不好意思！没有补考次数了！！");
					$("#mymodal").modal("show");
				}
			},
			error : function() {
				$(".modal-body").empty().append("请求超时");
				$("#mymodal").modal("show");
			}
		});
	}
</script>
</html>