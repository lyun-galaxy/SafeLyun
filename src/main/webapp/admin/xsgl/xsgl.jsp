<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<body>
	<script type="text/javascript">
		function xsImport() {

			$('#admin_xsgl_xsgl_rollingDialog').dialog('open');

			$.ajaxFileUpload({
				url : '${pageContext.request.contextPath}/userAction!importFile.action',//用于文件上传的服务器端请求地址
				secureuri : true,//是否启用安全提交，一般设置为false
				fileElementId : 'uploadfile',//文件上传控件的id
				dataType : 'text',//服务器返回的数据类型
				success : function(data) {
					$('#admin_xsgl_xsgl_rollingDialog').dialog('close');
					var obj = jQuery.parseJSON(data);
					if (obj.success) {
						$('#admin_xsgl_xsgl_importDialog').dialog('close');
					}
					$.messager.show({
						title : '提示',
						msg : obj.msg,
					});
				},
				error : function(data, status, e) {
					alert("服务中断或连接超时导致通信失败！");
					alert(e);
				}
			});
		}
	</script>


	<form id="admin_xsgl_xsgl_importForm" method="post">
		Excel文件：<input type="file" id="uploadfile" name="file" style="width:300px" /><br /> <a id="btn" href="#" class="easyui-linkbutton" onclick="xsImport()" data-options="iconCls:'icon-add'">导入</a>
	</form>

	<div id="admin_xsgl_xsgl_rollingDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'提示'" style="width: 300px;height: 70px;" align="center">
		<img alt="上传中。。。" src="jslib/imgs/rolling.gif"><br> <a>上传中,请等候...</a>
	</div>

</body>
</html>