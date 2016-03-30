<%@ page language="java" pageEncoding="UTF-8"%>
<body>
	<script type="text/javascript">
		$(function() {
			$('#admin_xjgl_xjgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/sectionAction!datagrid.action',
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
					title : '小节名称',
					width : 150,
					align : 'center',
					sortable : true
				}, {
					field : 'context',
					title : '内容',
					width : 400,
					align : 'center',
					sortable : true
				} ] ],
				toolbar : [ {
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						xjAppend();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						xjRemove();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						xjEditFun();
					}
				} ]
			});
		});

		function xjEditFun() {
			var rows = $('#admin_xjgl_xjgl_datagrid').datagrid('getChecked');
			if (rows.length == 1) {
				var d = $('<div/>').dialog({
					width : 270,
					height : 170,
					href : '${pageContext.request.contextPath}/admin/xjgl/xjglEdit.jsp',
					modal : true,
					align : 'center',
					title : '修改小节',
					buttons : [ {
						text : '修改',
						handler : function() {
							$('#admin_xjgl_xjglEdit_editForm').form('submit', {
								url : '${pageContext.request.contextPath}/sectionAction!edit.action',
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if (obj.success) {
										d.dialog('destroy');
										//$('#admin_xjgl_xjgl_datagrid').datagrid('load');
										$('#admin_xjgl_xjgl_datagrid').datagrid('updateRow', {
											index : $('#admin_xjgl_xjgl_datagrid').datagrid('getRowIndex', rows[0]),
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
						$('#admin_xjgl_xjglEdit_editForm').form('load', rows[0]);
					}
				});
			} else {
				$.messager.alert('提示', '请勾选一个要编辑的选项！');
			}
		}

		function xjRemove() {
			var rows = $('#admin_xjgl_xjgl_datagrid').datagrid('getChecked');
			//	var rows=$('#admin_xjgl_xjgl_datagrid').datagrid('getSelected');
			//	var rows=$('#admin_xjgl_xjgl_datagrid').datagrid('getSelecteds');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中的选项？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/sectionAction!remove.action',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(d) {
								var v = $('#admin_xjgl_xjgl_datagrid');
								v.datagrid('load');
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

		function xjAppend() {

			$('#admin_xjgl_xjgl_addForm input').val('');
			$('#admin_xjgl_xjgl_addDialog').dialog('open');
		}

		function xjSearchFun() {
			//	$('#admin_xjgl_xjgl_datagrid').datagrid('load', {
			//		name : $('#admin_xjgl_xjgl_layout input[name=name]').val()
			//	});
			$('#admin_xjgl_xjgl_datagrid').datagrid('load', serializeObject($('#admin_xjgl_xjgl_searchForm')));
		}

		function zjClearFun() {
			$('#admin_xjgl_xjgl_layout input[name=name]').val('');
			$('#admin_xjgl_xjgl_datagrid').datagrid('load', {});
		}
	</script>

	<div id="admin_xjgl_xjgl_layout" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
			<form id="admin_xjgl_xjgl_searchForm">
				检索用户名(可模糊查询):<input name="name" /> </a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="xjSearchFun()">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="zjClearFun()">清空</a>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_xjgl_xjgl_datagrid" data-options="border:false"></table>
		</div>

	</div>

	<div id="admin_xjgl_xjgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加章节',buttons:[{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						$('#admin_xjgl_xjgl_addForm').form('submit',{
							url : '${pageContext.request.contextPath}/sectionAction!add.action',
							success : function(data) {
								var obj = jQuery.parseJSON(data);
								if (obj.success) {
									/*$('#admin_xjgl_xjgl_datagrid').datagrid('load');*/
									/*$('#admin_xjgl_xjgl_datagrid').datagrid('appendRow',obj.obj);*/
									$('#admin_xjgl_xjgl_datagrid').datagrid('insertRow',{
										index: 0,
										row:obj.obj
									});
									$('#admin_xjgl_xjgl_addDialog').dialog('close');
								}
								$.messager.show({
									title : '提示',
									msg : obj.msg,
								});
							}
					});
					}}]" style="width: 270px;height: 170px;" align="center">
		<form id="admin_xjgl_xjgl_addForm" method="post">
			<table>
				<tr>
					<th>小节名称</th>
					<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>内容</th>
					<td><input type="text" name="context" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>

			</table>
		</form>
	</div>

</body>
</html>
