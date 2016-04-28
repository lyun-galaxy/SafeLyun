<%@ page language="java" pageEncoding="UTF-8"%>

<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/print.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#admin_bbgl_bbgl_grade').combobox({
				valueField : 'gradeName',
				textField : 'gradeName',
				editable : false,
				url : '${pageContext.request.contextPath}/report/getGradeJson.action'
			});

			var depart03 = $('#admin_bbgl_bbgl_depart').combobox({
				valueField : 'departId',
				textField : 'departName',
				editable : false,
				url : '${pageContext.request.contextPath}/report/getDepartJson.action',
				onSelect : function(params) {
					$.get('${pageContext.request.contextPath}/report/getProJson.action', {
						parentid : params.departId
					}, function(data) {
						pro03.combobox("clear").combobox('loadData', data);
						class03.combobox("clear");
					}, 'json');
				}
			});

			var pro03 = $('#admin_bbgl_bbgl_pro').combobox({
				valueField : 'proId',
				textField : 'proName',
				editable : false,
				onSelect : function(params) {
					$.get('${pageContext.request.contextPath}/report/getClassJson.action', {
						parentid : params.proId
					}, function(data) {
						class03.combobox("clear").combobox('loadData', data);
					}, 'json');
				}

			});

			var class03 = $('#admin_bbgl_bbgl_class').combobox({
				valueField : 'classId',
				textField : 'className',
				editable : false
			});

		});

		function admin_bbgl_bbgl_searchGrade() {
			if ($('#admin_bbgl_bbgl_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '年级不能为空！',
				});
			} else if ($('#admin_bbgl_bbgl_depart').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '院系不能为空！',
				});
			} else {

				$('#admin_bbgl_bbgl_getClassForm').form('submit', {
					url : '${pageContext.request.contextPath}/report/allDepartDatagrid.action',
					success : function(datas) {
						var obj = jQuery.parseJSON(datas);
						$('#admin_bbgl_bbgl_Datagrid').datagrid({
							data : obj.rows,
							fit : true,
							checkOnSelect : false,
							selectOnCheck : false,
							fitColumns : true,
							rownumbers : true,
							columns : [ [ {
								field : 'grade',
								title : '年级',
								width : 150,
								align : 'center',
							}, {
								field : 'departName',
								title : '院系',
								width : 200,
								align : 'center',
							}, {
								field : 'completeRate',
								title : '完成率',
								width : 150,
								align : 'center',
							}, {
								field : 'passRate',
								title : '通过率',
								width : 150,
								align : 'center',
							} ] ],
							toolbar : [ {
								text : '打印',
								iconCls : 'icon-print',
								handler : function() {
									var str = $('#admin_bbgl_bbgl_grade').combobox('getValue') + '级各院系安全教育考试报表';
									CreateFormPage(str, $('#admin_bbgl_bbgl_Datagrid'));
								}
							} ]
						});
					}
				});

			}
		}

		function admin_bbgl_bbgl_searchDepart() {
			if ($('#admin_bbgl_bbgl_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '年级不能为空！',
				});
			} else if ($('#admin_bbgl_bbgl_depart').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '院系不能为空！',
				});
			} else if ($('#admin_bbgl_bbgl_pro').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个专业！',
				});
			} else {
				$('#admin_bbgl_bbgl_getClassForm').form('submit', {
					url : '${pageContext.request.contextPath}/report/proDatagrid.action',
					success : function(datas) {
						var obj = jQuery.parseJSON(datas);
						$('#admin_bbgl_bbgl_Datagrid').datagrid({
							data : obj.rows,
							fit : true,
							checkOnSelect : false,
							selectOnCheck : false,
							fitColumns : true,
							rownumbers : true,
							columns : [ [ {
								field : 'grade',
								title : '年级',
								width : 150,
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
								field : 'completeRate',
								title : '完成率',
								width : 150,
								align : 'center',
							}, {
								field : 'passRate',
								title : '通过率',
								width : 150,
								align : 'center',
							} ] ],
							toolbar : [ {
								text : '打印',
								iconCls : 'icon-print',
								handler : function() {
									var str = $('#admin_bbgl_bbgl_grade').combobox('getValue') + '级' + $('#admin_bbgl_bbgl_depart').combobox('getValue') + '安全教育考试报表';
									CreateFormPage(str, $('#admin_bbgl_bbgl_Datagrid'));
								}
							} ]
						});
					}
				});

			}

		}

		function admin_bbgl_bbgl_searchClass() {
			if ($('#admin_bbgl_bbgl_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个年级！',
				});
			} else if ($('#admin_bbgl_bbgl_depart').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个院系！',
				});
			} else if ($('#admin_bbgl_bbgl_pro').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个专业！',
				});
			} else if ($('#admin_bbgl_bbgl_class').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个班级！',
				});
			} else {
				$('#admin_bbgl_bbgl_getClassForm').form('submit', {
					url : '${pageContext.request.contextPath}/report/classGatagrid.action',
					success : function(datas) {
						var obj = jQuery.parseJSON(datas);
						$('#admin_bbgl_bbgl_Datagrid').datagrid({
							data : obj.rows,
							fit : true,
							checkOnSelect : false,
							selectOnCheck : false,
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
								text : '打印',
								iconCls : 'icon-print',
								handler : function() {
									var str = $('#admin_bbgl_bbgl_grade').combobox('getValue') + '级' + $('#admin_bbgl_bbgl_class').combobox('getValue') + '安全教育考试报表';
									CreateFormPage(str, $('#admin_bbgl_bbgl_Datagrid'));
								}
							} ]
						});
					}
				});
			}
		}

	</script>

	<div id="admin_bbgl_bbgl_layout" class="easyui-layout"
		data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false"
			style="height: 120px">
			<form id="admin_bbgl_bbgl_getClassForm" method="post">
				年级：<input id="admin_bbgl_bbgl_grade" name="gradeId" style="width: 10%">
				院系：<input id="admin_bbgl_bbgl_depart" name="departId"> 专业：<input
					id="admin_bbgl_bbgl_pro" name="proId" style="width: 12%"> 班级：<input
					id="admin_bbgl_bbgl_class" name="classId" style="width: 12%"> <br>
				<a id="" href="#" class="easyui-linkbutton" onclick="admin_bbgl_bbgl_searchGrade()">查询院系报表</a>
				<a id="" href="#" class="easyui-linkbutton" onclick="admin_bbgl_bbgl_searchDepart()">查询专业报表</a>
				<a id="" href="#" class="easyui-linkbutton" onclick="admin_bbgl_bbgl_searchClass()">查询班级报表</a>
			</form>

		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_bbgl_bbgl_Datagrid" data-options="border:false"
				style="width: 500px; height: 200px"></table>
		</div>
	</div>
</body>
</html>
