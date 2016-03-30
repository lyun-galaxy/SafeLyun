<%@ page language="java" pageEncoding="UTF-8"%>
<body>
	<script type="text/javascript">
		$(function() {
			$('#admin_yhgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/userAction!datagrid.action',
				fit : true,
				pagination : true,
				idField : 'id',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				sortName : 'name',
				sortOrder : 'asc',
				frozenColumns : [ [ {
					field : 'id',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'name',
					title : '登录名称',
					width : 150,
					align : 'center',
					sortable : true
				} ] ],
				columns : [ [ {
					field : 'password',
					title : '密码',
					width : 150,
					align : 'center',
					sortable : true,
					formatter : function(value, row, index) {
						//	return '<span title="'+row.name+':'+value+'">' + value + '</span>';
						return '******';
					}
				}] ],
				toolbar : [ {
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						append();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						remove();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						editFun();
					}
				} ]
			});
		});

		function editFun() {
			var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
			if (rows.length == 1) {
				var d = $('<div/>').dialog({
					width : 500,
					height : 200,
					href : '${pageContext.request.contextPath}/admin/yhglEdit.jsp',
					modal : true,
					title : '编辑用户',
					buttons : [ {
						text : '编辑',
						handler : function() {
							$('#admin_yhglEdit_editForm').form('submit', {
								url : '${pageContext.request.contextPath}/userAction!edit.action',
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if (obj.success) {
										d.dialog('destroy');
										//$('#admin_yhgl_datagrid').datagrid('load');
										$('#admin_yhgl_datagrid').datagrid('updateRow', {
											index : $('#admin_yhgl_datagrid').datagrid('getRowIndex', rows[0]),
											row : obj.obj
										});
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
						$('#admin_yhglEdit_editForm').form('load', rows[0]);
					}
				});
			} else {
				$.messager.alert('提示', '请勾选一个要编辑的选项！');
			}
		}

		function remove() {
			var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
			//	var rows=$('#admin_yhgl_datagrid').datagrid('getSelected');
			//	var rows=$('#admin_yhgl_datagrid').datagrid('getSelecteds');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中的选项？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/userAction!remove.action',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(d) {
								var v = $('#admin_yhgl_datagrid');
								v.datagrid('load');
								v.datagrid('unselectAll');
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

		function append() {

			$('#admin_yhgl_addForm input').val('');
			$('#admin_yhgl_addDialog').dialog('open');
		}

		function searchFun() {
			//	$('#admin_yhgl_datagrid').datagrid('load', {
			//		name : $('#admin_yhgl_layout input[name=name]').val()
			//	});
			$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
		}

		function clearFun() {
			$('#admin_yhgl_layout input[name=name]').val('');
			$('#admin_yhgl_datagrid').datagrid('load', {});
		}
	</script>

	<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
			<form id="admin_yhgl_searchForm">
				检索用户名(可模糊查询):<input name="name" /> </a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun()">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="clearFun()">清空</a>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_yhgl_datagrid" data-options="border:false"></table>
		</div>

	</div>

	<div id="admin_yhgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						$('#admin_yhgl_addForm').form('submit',{
							url : '${pageContext.request.contextPath}/userAction!add.action',
							success : function(data) {
								var obj = jQuery.parseJSON(data);
								if (obj.success) {
									/*$('#admin_yhgl_datagrid').datagrid('load');*/
									/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
									$('#admin_yhgl_datagrid').datagrid('insertRow',{
										index: 0,
										row:obj.obj
									});
									$('#admin_yhgl_addDialog').dialog('close');
								}
								$.messager.show({
									title : '提示',
									msg : obj.msg,
								});
							}
					});
					}}]" style="width: 550px;height: 200px;" align="center">
		<form id="admin_yhgl_addForm" method="post">
			<table>
				<tr>
					<th>编号</th>
					<td><input name="id" readonly="readonly" /></td>
					<th>登录名称</th>
					<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="password" type="password" class="easyui-validatebox" data-options="required:true" /></td>
					
				</tr>
				
			</table>
		</form>
	</div>

</body>
</html>
