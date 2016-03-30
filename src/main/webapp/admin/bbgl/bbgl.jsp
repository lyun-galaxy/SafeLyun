<%@ page language="java" pageEncoding="UTF-8"%>

<body>
	<script type="text/javascript" src="jslib/print.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#getClass_grade').combobox({
				valueField : 'gradeId',
				textField : 'gradeName',
				editable : false,
				url : '${pageContext.request.contextPath}/reportAction!getGradeJson.action'
			});

			var depart03 = $('#getClass_depart').combobox({
				valueField : 'departId',
				textField : 'departName',
				editable : false,
				url : '${pageContext.request.contextPath}/reportAction!getDepartJson.action',
				onSelect : function(params) {
					$.get('${pageContext.request.contextPath}/reportAction!getProJson.action', {
						parentid : params.departId
					}, function(data) {
						pro03.combobox("clear").combobox('loadData', data);
						class03.combobox("clear");
					}, 'json');
				}
			});

			var pro03 = $('#getClass_pro').combobox({
				valueField : 'proId',
				textField : 'proName',
				editable : false,
				onSelect : function(params) {
					$.get('${pageContext.request.contextPath}/reportAction!getClassJson.action', {
						parentid : params.proId
					}, function(data) {
						class03.combobox("clear").combobox('loadData', data);
					}, 'json');
				}

			});

			var class03 = $('#getClass_class').combobox({
				valueField : 'classId',
				textField : 'className',
				editable : false
			});

		});

		function searchGrade() {
			if ($('#getClass_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '年级不能为空！',
				});
			} else {

				$('#admin_bbgl_bbgl_getClassForm').form('submit', {
					url : '${pageContext.request.contextPath}/reportAction!allDepartDatagrid.action',
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
									var str=$('#getClass_grade').combobox('getValue')+'级各院系安全教育考试报表';
									CreateFormPage(str,$('#admin_bbgl_bbgl_Datagrid'));
								}
							} ]
						});
					}
				});

			}
		}

		function searchDepart() {
			if ($('#getClass_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '年级不能为空！',
				});
			} else if ($('#getClass_depart').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '院系不能为空！',
				});
			} else {
				$('#admin_bbgl_bbgl_getClassForm').form('submit', {
					url : '${pageContext.request.contextPath}/reportAction!proDatagrid.action',
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
									var str=$('#getClass_grade').combobox('getValue')+'级'+$('#getClass_depart').combobox('getValue')+'安全教育考试报表';
									CreateFormPage(str,$('#admin_bbgl_bbgl_Datagrid'));
								}
							} ]
						});
					}
				});

			}

		}

		function searchClass() {
			if ($('#getClass_grade').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个年级！',
				});
			} else if ($('#getClass_depart').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个院系！',
				});
			} else if ($('#getClass_pro').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个专业！',
				});
			} else if ($('#getClass_class').combobox('getValue') == '') {
				$.messager.show({
					title : '提示',
					msg : '请选择一个班级！',
				});
			} else {
				$('#admin_bbgl_bbgl_getClassForm').form('submit', {
					url : '${pageContext.request.contextPath}/reportAction!classGatagrid.action',
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
									var str=$('#getClass_grade').combobox('getValue')+'级'+$('#getClass_class').combobox('getValue')+'安全教育考试报表';
									CreateFormPage(str,$('#admin_bbgl_bbgl_Datagrid'));
								}
							} ]
						});
					}
				});
			}
		}

	</script>

	<div id="admin_bbgl_bbgl_layout" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 90px;">
			<form id="admin_bbgl_bbgl_getClassForm" method="post">
				年级：<input id="getClass_grade" name="gradeId" style="width: 10%"> 院系：<input id="getClass_depart" name="departId"> 专业：<input id="getClass_pro" name="proId" style="width: 12%"> 班级：<input id="getClass_class" name="classId" style="width: 12%"> <br> <a id="" href="#" class="easyui-linkbutton" onclick="searchGrade()">查看年级下的院系报表</a> <a id="" href="#" class="easyui-linkbutton" onclick="searchDepart()">查看院系下的专业报表</a>
				<!-- 				 <a id="" href="#" class="easyui-linkbutton" onclick="searchPro()">查询专业报表</a> -->
				<a id="" href="#" class="easyui-linkbutton" onclick="searchClass()">查看班级报表</a>
			</form>

		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_bbgl_bbgl_Datagrid" data-options="border:false" style="width:500px;height:200px"></table>
		</div>
	</div>
</body>
</html>
