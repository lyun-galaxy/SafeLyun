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
			<!--img alt="" src="${pageContext.request.contextPath}/images/admin_title.png"-->
			<h1 style="font-size:30px">龙岩学院安全在线教育</h1>
		</div>
		<div>
		   <a href="${pageContext.request.contextPath}/client_login/logout.action">退出</a>
		   <a onclick="show()">修改密码</a>
		</div>
	</div>
	<div data-options="region:'south'" style="height: 30px;">
		<div align="center" style="padding-right: 5px;padding-bottom: 5px">
			<p>&copy; 龙岩学院安全工作处 & GALAXY团队</p>
		</div>
		
	</div>
	<div data-options="region:'east',title:'日历'" style="width: 180px;">
		<jsp:include page="/admin/index/east.jsp"></jsp:include>
	</div>
	<div data-options="region:'west',title:'菜单'" style="width: 200px;">
		<jsp:include page="/admin/index/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'center'" style="background: #eee;">
		<jsp:include page="/admin/index/center.jsp"></jsp:include>
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
					<h4 class="modal-title" id="gridSystemModalLabel">修改密码</h4>
				</div>
				<div class="modal-body">
					<table>
						<tr>
							<td>原密码</td>
							<td><input id="oldPassword" type="password"
								class="form-control" placeholder="Password"></td>
						</tr>
						<tr>
							<td>新密码</td>
							<td><input id="newPassword1" type="password"
								class="form-control" placeholder="new Password"></td>
						</tr>
						<tr>
							<td>确认密码</td>
							<td><input id="newPassword2" type="password"
								class="form-control" placeholder="confirm Password"></td>
						</tr>
					</table>
					<br>
				</div>
				<div class="modal-footer">

					<button type="button" onclick="modifyPassword()" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">提示</h4>
						</div>
						<div class="modal-body">
							<p id="msg" style="color: red;"></p>
						</div>
						<div class="modal-footer">
							<button type="button" id="ret_hint" style="background-color: #edbc6c;" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
</body>
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
	src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
function show(){
	$("#mymodal").modal("show");
}
function modifyPassword() {
	$('#mymodal').modal('toggle');
	var oldPassword = $('#oldPassword').val().replace(/\s/g, "");
	var newPassword1 = $('#newPassword1').val().replace(/\s/g, "");
	var newPassword2 = $('#newPassword2').val().replace(/\s/g, "");
	var success = myAlerts(oldPassword, "请输入原始密码！");
	if(!success)return;
	success = myAlerts(newPassword1, "请输入新密码！");
	if(!success)return;
	success = myAlerts(newPassword2, "请输入确认密码！");
	if(!success)return;
	if(newPassword1!=newPassword2){
		myAlerts('', "确认密码不一致！");
		return;
	}
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/manager_home/modify_password.action",
		data : {
			oldPassword : oldPassword,
			newPassword : newPassword1,
			reNewPassword : newPassword2
		},
		dataType : "json",
		success : function(data) {
			myAlerts('',data.msg);
			$('#oldPassword').val('');
			$('#newPassword1').val('');
			$('#newPassword2').val('');
		},
		error : function(data) {
			console.info(data);
			myAlerts("", "请求超时!");
		}

	});
}
function myAlerts(val, msg) {
	if(val.length==0){
		$('#msg').text(msg);
		$('#myModal').modal({
			keyboard : true
		});
		return false;
	}
	return true;
}
</script>
</html>