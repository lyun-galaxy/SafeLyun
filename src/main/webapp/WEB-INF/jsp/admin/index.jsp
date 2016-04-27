<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Index</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/syUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/ajaxfileupload.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css">
</head>

<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 100px;">
		<div align="center">
			<img alt="" src="${pageContext.request.contextPath}/images/admin_title.png">
		</div>
	</div>
	<div data-options="region:'south'" style="height: 30px;">
		<div align="center">

			<p>&copy; 龙岩学院保卫处 & GALAXY团队</p>
		</div>
	</div>
	<div data-options="region:'east',title:'日历'" style="width: 180px;">
<<<<<<< HEAD
		<jsp:include page="${pageContext.request.contextPath}/admin/index/east.jsp"></jsp:include>
	</div>
	<div data-options="region:'west',title:'菜单'" style="width: 200px;">
		<jsp:include page="${pageContext.request.contextPath}/admin/index/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'center'" style="background: #eee;">
		<jsp:include page="${pageContext.request.contextPath}/admin/index/center.jsp"></jsp:include>
=======
		<jsp:include page="/admin/index/east.jsp"></jsp:include>
	</div>
	<div data-options="region:'west',title:'菜单'" style="width: 200px;">
		<jsp:include page="/admin/index/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'center'" style="background: #eee;">
		<jsp:include page="/admin/index/center.jsp"></jsp:include>
>>>>>>> branch 'master' of https://github.com/lyun-galaxy/SafeLyun.git
	</div>
</body>
</html>
