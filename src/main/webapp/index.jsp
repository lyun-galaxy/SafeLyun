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
<script type="text/javascript" src="jslib/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jslib/syUtils.js"></script>
<script type="text/javascript" src="jslib/ajaxfileupload.js"></script>
<link rel="stylesheet" href="jslib/jquery-easyui-1.4.4/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.4.4/themes/icon.css" type="text/css"></link>

</head>

<body class="easyui-layout">
	<div data-options="region:'north'" style="height:100px;"></div>
	<div data-options="region:'south'" style="height:30px;"></div>
	<div data-options="region:'east',title:'日历'" style="width:180px;">
		<jsp:include page="admin/index/east.jsp"></jsp:include>
	</div>
	<div data-options="region:'west',title:'菜单'" style="width:200px;">
		<jsp:include page="admin/index/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'center'" style="background:#eee;">
		<jsp:include page="admin/index/center.jsp"></jsp:include>
	</div>
</body>
</html>
