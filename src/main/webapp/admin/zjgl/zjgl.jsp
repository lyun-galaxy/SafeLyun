<%@ page language="java" pageEncoding="UTF-8"%>
<body>
	<script type="text/javascript">
		$(function() {
			zjInit();
		});

		function zjInit() {
			$('#admin_zjgl_zjgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/chapter/datagridAudit.action',
				fit : true,
				pagination : true,
				idField : 'sectionId',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				frozenColumns : [ [ {
					field : 'sectionId',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'sectionName',
					title : '章节名称',
					width : 150,
					align : 'center',
				}, {
					field : 'sectionChecked',
					title : '状态',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == 1) {
							return '已审核';
						} else {
							return '未审核';
						}
					},
				} ] ],
				toolbar : [ {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						zjRemove();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						zjEditFun();
					}
				}, '-', {
					text : '对该章节的小节管理',
					iconCls : 'icon-edit',
					handler : function() {
						zjManager();
					}
				} ]
			});

		}

		function zjUnAudit() {
			$('#admin_zjgl_zjgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/chapter/datagridUnaudit.action',
				fit : true,
				pagination : true,
				idField : 'sectionId',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				frozenColumns : [ [ {
					field : 'sectionId',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'sectionName',
					title : '章节名称',
					width : 150,
					align : 'center',
				}, {
					field : 'sectionChecked',
					title : '状态',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == 1) {
							return '已审核';
						} else {
							return '未审核';
						}
					},
				} ] ],
				toolbar : [ {
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						zjAppend();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						zjRemove();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						zjEditFun();
					}
				}, '-', {
					text : '通过审核',
					iconCls : 'icon-edit',
					handler : function() {
						zjPass();
					}
				}, '-', {
					text : '返回',
					iconCls : 'icon-back',
					handler : function() {
						zjInit();
					}
				} ]
			});

		}
		
		function zjPass(){
			var rows = $('#admin_zjgl_zjgl_datagrid').datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要授权当前选中的选项？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/chapter/audit.action',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(d) {
								var v = $('#admin_zjgl_zjgl_datagrid');
								v.datagrid('reload');
								v.datagrid('unselectAll');
								v.datagrid('clearChecked');
								$.messager.show({
									title : '提示',
									msg : d.msg
								});
							}
						});

					}
				});

			} else {
				$.messager.show({
					title : '提示',
					msg : '请勾选要授权的选项！'
				});
			}
		}

		function zjManager() {
			var rows = $('#admin_zjgl_zjgl_datagrid').datagrid('getChecked');
			if (rows.length == 1) {
				document.cookie = 'zjid=' + rows[0].id;
				var url = '${pageContext.request.contextPath}/admin/xjgl/xjgl.jsp';
				addTabs({
					title : '小节管理',
					href : url,
					closable : true
				});

			} else {
				$.messager.alert('提示', '请勾选一个要管理的章节！');
			}

		}

		function zjEditFun() {
			var rows = $('#admin_zjgl_zjgl_datagrid').datagrid('getChecked');
			if (rows.length == 1) {
				var d = $('<div/>').dialog({
					width : 270,
					height : 125,
					href : '${pageContext.request.contextPath}/admin/zjgl/zjglEdit.jsp',
					modal : true,
					align : 'center',
					title : '修改章节',
					buttons : [ {
						text : '修改',
						handler : function() {
							$('#admin_zjgl_zjglEdit_editForm').form('submit', {
								url : '${pageContext.request.contextPath}/chapter/edit.action',
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if (obj.success) {
										d.dialog('destroy');
										$('#admin_zjgl_zjgl_datagrid').datagrid('load');
										/*$('#admin_zjgl_zjgl_datagrid').datagrid('updateRow', {
											index : $('#admin_zjgl_zjgl_datagrid').datagrid('getRowIndex', rows[0]),
											row : obj.obj
										});*/
									}

									$.messager.show({
										title : '提示',
										msg : obj.msg,
									});
								}
							});
						}
					} ],
					onClose : function() {
						$(this).dailog('destroy');
					},
					onLoad : function() {
						$('#admin_zjgl_zjglEdit_editForm').form('load', rows[0]);
					}
				});
			} else {
				$.messager.alert('提示', '请勾选一个要编辑的选项！');
			}
		}

		function zjRemove() {
			var rows = $('#admin_zjgl_zjgl_datagrid').datagrid('getChecked');
			//	var rows=$('#admin_zjgl_zjgl_datagrid').datagrid('getSelected');
			//	var rows=$('#admin_zjgl_zjgl_datagrid').datagrid('getSelecteds');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中的选项？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].sectionId);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/chapter/remove.action',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(d) {
								var v = $('#admin_zjgl_zjgl_datagrid');
								v.datagrid('reload');
								v.datagrid('unselectAll');
								v.datagrid('clearChecked');
								$.messager.show({
									title : '提示',
									msg : d.msg
								});
							}
						});

					}
				});

			} else {
				$.messager.show({
					title : '提示',
					msg : '请勾选要删除的选项！'
				});
			}

		}

		function zjAppend() {

			$('#admin_zjgl_zjgl_addForm input').val('');
			$('#admin_zjgl_zjgl_addDialog').dialog('open');
		}

		function zjSearchFun() {
			//	$('#admin_zjgl_zjgl_datagrid').datagrid('load', {
			//		name : $('#admin_zjgl_zjgl_layout input[name=name]').val()
			//	});
			$('#admin_zjgl_zjgl_datagrid').datagrid('load', serializeObject($('#admin_zjgl_zjgl_searchForm')));
		}

		function zjClearFun() {
			$('#admin_zjgl_zjgl_layout input[name=name]').val('');
			$('#admin_zjgl_zjgl_datagrid').datagrid('load', {});
		}
	</script>

	<div id="admin_zjgl_zjgl_layout" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
			<form id="admin_zjgl_zjgl_searchForm">
				检索章节名称(可模糊查询):<input name="name" /> </a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="zjSearchFun()">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="zjClearFun()">清空</a>
			</form>
			<br>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="zjUnAudit()">查看未审核</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_zjgl_zjgl_datagrid" data-options="border:false"></table>
		</div>

	</div>

	<div id="admin_zjgl_zjgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加章节',buttons:[{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						$('#admin_zjgl_zjgl_addForm').form('submit',{
							url : '${pageContext.request.contextPath}/chapter/add.action',
							success : function(data) {
								var obj = jQuery.parseJSON(data);
								if (obj.success) {
									$('#admin_zjgl_zjgl_datagrid').datagrid('load');
									/*$('#admin_zjgl_zjgl_datagrid').datagrid('appendRow',obj.obj);*/
									/*$('#admin_zjgl_zjgl_datagrid').datagrid('insertRow',{
										index: 0,
										row:obj.obj
									});*/
									$('#admin_zjgl_zjgl_addDialog').dialog('close');
								}
								$.messager.show({
									title : '提示',
									msg : obj.msg,
								});
							}
					});
					}}]" style="width: 270px;height: 125px;" align="center">
		<form id="admin_zjgl_zjgl_addForm" method="post">
			<table>
				<tr>
					<th>章节名称</th>
					<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>

			</table>
		</form>
	</div>

</body>
</html>
