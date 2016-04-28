<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/exam.css" media="screen"
	type="text/css" />
</head>
<body>
	<div class='bg'>
		<div class='cont'>
			<div class='c1'>
				<img src='${pageContext.request.contextPath}/images/01.png' class='img1' />
			</div>
			<h2>对不起，您没有权限访问</h2>
			 <!-- 操作 -->
            <A HREF="javascript:history.go(-1);"><IMG SRC="${pageContext.request.contextPath}/images/goBack.png"/></A>
		</div>
	</div>
</body>
</html>