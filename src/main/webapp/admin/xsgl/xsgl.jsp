<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/print.js"></script>
	<script type="text/javascript">
		$(function() {
			setSwitch();

			$('#admin_xsgl_xsgl_grade').combobox({
				valueField : 'gradeId',
				textField : 'gradeName',
				editable : false,
				url : '${pageContext.request.contextPath}/report/getGradeJson.action'
			});
		});

		function xsImport() {
			if ($('#uploadfile').val() == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个Excel文件',
				});
			} else {
				$('#admin_xsgl_xsgl_rollingDialog').dialog('open');
				$.ajaxFileUpload({
					url : '${pageContext.request.contextPath}/adminuser/importfile.action',//用于文件上传的服务器端请求地址
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
						$.messager.show({
							title : '提示',
							msg : '服务中断或连接超时导致通信失败！' ,
						});
					}
				});
			}
		}

		function xsExportAllStudentScore() {
		/*	$('#admin_xsgl_xsgl_rollingDialog').dialog('open');
			
			//要自己修改
			location.href = '${pageContext.request.contextPath}/fileAction!download.action?filePath=' + 'D:\\apache-tomcat-7.0.41\\webapps\\ajaxFile';
			$('#admin_xsgl_xsgl_rollingDialog').dialog('close');
			$.messager.show({
				title : '提示',
				msg : '导出成功！',
			});*/
		//	var garde = $('#admin_xsgl_xsgl_grade').combobox('getText');
		
		/*	$.ajax({
				type: "POST",
				url : "${pageContext.request.contextPath}/reportPrint/ClassStudent.action",
				
				success : function() {
					alert("导出成功")
				},
				error:function(){
					alert("导出失败");
				}
				});*/
				
				var form=$("<form>");//定义一个form表单
				form.attr("style","display:none");
				form.attr("target","_self");
				form.attr("method","post");
				form.attr("action","${pageContext.request.contextPath}/reportPrint/AllStudent.action");
				var input1=$("<input>");
				input1.attr("type","hidden");
				input1.attr("name","exportData");
				input1.attr("value",(new Date()).getMilliseconds());
				$("body").append(form);//将表单放置在web中
				form.append(input1);

				form.submit();//表单提交 
			
		}

		function setSwitch() {
			$.ajax({
				url : '${pageContext.request.contextPath}/switchController/getFlag.action',
				dataType : 'json',
				success : function(d) {
					if (d.flag == false) {
						$('#xsSwitch').switchbutton({
							checked : false
						});
					} else {
						$('#xsSwitch').switchbutton({
							checked : true
						});
					}

				}
			});
		}

		function admin_xsgl_xsgl_xsChange() {
			$.ajax({
				url : '${pageContext.request.contextPath}/switchController/changeFlag.action',
				dataType : 'json',
				success : function(d) {
					$.messager.show({
						title : '提示',
						msg : d.msg,
					});
				}
			});
		}

		function admin_xsgl_xsgl_searchAll() {
			if ($('#admin_xsgl_xsgl_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个年级！',
				});
			} else {
				document.cookie = 'gradeId=' + $('#admin_xsgl_xsgl_grade').combobox('getText');
				$('#admin_xsgl_xsgl_Datagrid').datagrid({
					url : '${pageContext.request.contextPath}/report/allStudentScore.action',
					fit : true,
					pagination : true,
					rownumbers : true,
					fitColumns : true,
					columns : [ [ {
						field : 'no',
						title : '学号',
						width : 200,
						align : 'center',
					}, {
						field : 'name',
						title : '姓名',
						width : 200,
						align : 'center',
					}, {
						field : 'departName',
						title : '院系',
						width : 200,
						align : 'center',
					}, {
						field : 'proName',
						title : '专业',
						width : 200,
						align : 'center',
					}, {
						field : 'clazzName',
						title : '班级',
						width : 200,
						align : 'center',
					},{
						field : 'score',
						title : '成绩',
						width : 150,
						align : 'center',
						formatter : function(value, row, index) {
							if (value < 60) {
								return '<span style="color:red;">未通过(' + value + ')</span>';
							} else {
								return value;
							}
						},
					} ] ],
					toolbar : [ {
						text : '导出所有学生的成绩',
						iconCls : 'icon-redo',
						handler : function() {
							xsExportAllStudentScore();
						}
					} ]
				});
			}
		}

		function admin_xsgl_xsgl_searchNoPass() {
			if ($('#admin_xsgl_xsgl_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个年级！',
				});
			} else {
				document.cookie = 'gradeId=' + $('#admin_xsgl_xsgl_grade').combobox('getText');
				$('#admin_xsgl_xsgl_Datagrid').datagrid({
					url : '${pageContext.request.contextPath}/report/allNoPass.action',
					fit : true,
					pagination : true,
					rownumbers : true,
					fitColumns : true,
					columns : [ [ {
						field : 'no',
						title : '学号',
						width : 200,
						align : 'center',
					}, {
						field : 'name',
						title : '姓名',
						width : 200,
						align : 'center',
					}, {
						field : 'departName',
						title : '院系',
						width : 200,
						align : 'center',
					}, {
						field : 'proName',
						title : '专业',
						width : 200,
						align : 'center',
					}, {
						field : 'score',
						title : '成绩',
						width : 150,
						align : 'center',
						formatter : function(value, row, index) {
							if (value < 60) {
								return '<span style="color:red;">未通过(' + value + ')</span>';
							} else {
								return value;
							}
						},
					} ] ],
					toolbar : [ {
						text : '导出所有未通过考试学生的成绩',
						iconCls : 'icon-redo',
						handler : function() {
							var form=$("<form>");//定义一个form表单
							form.attr("style","display:none");
							form.attr("target","_self");
							form.attr("method","post");
							form.attr("action","${pageContext.request.contextPath}/reportPrint/AllNoPassStudent.action");
							var input1=$("<input>");
							input1.attr("type","hidden");
							input1.attr("name","exportData");
							input1.attr("value",(new Date()).getMilliseconds());
							$("body").append(form);//将表单放置在web中
							form.append(input1);
							form.submit();//表单提交 
						}
					} ]
				});
			}
		}
	</script>

	<div id="admin_bbgl_bbgl_layout" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',border:false" style="height: 120px">
			<form id="admin_xsgl_xsgl_importForm" method="post">
				Excel文件：<input type="file" id="uploadfile" name="file" style="width: 300px" /><br /> <a id="btn" href="#" class="easyui-linkbutton" onclick="xsImport()" data-options="iconCls:'icon-add'">导入</a>
			</form>
			考试开关： <input id="xsSwitch" class="easyui-switchbutton" data-options="onText:'NO',offText:'OFF',onChange:function(){admin_xsgl_xsgl_xsChange();}"> <br> 年级： <input id="admin_xsgl_xsgl_grade" name="gradeId" style="width: 10%"> <a id="" href="#" class="easyui-linkbutton" onclick="admin_xsgl_xsgl_searchAll()">查看所有学生成绩</a> <a id="" href="#" class="easyui-linkbutton" onclick="admin_xsgl_xsgl_searchNoPass()">查看所有未通过考试的学生</a>

		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_xsgl_xsgl_Datagrid" data-options="border:false" style="width: 500px; height: 200px"></table>
		</div>
	</div>

	<div id="admin_xsgl_xsgl_rollingDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'提示'" style="width: 300px; height: 70px;" align="center">
		<img alt="上传中。。。" src="jslib/imgs/rolling.gif"><br> <a>上传中,请等候...</a>
	</div>
</body>
</html>
