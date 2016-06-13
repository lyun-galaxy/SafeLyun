<%@ page language="java" pageEncoding="UTF-8"%>
<body>
	<script type="text/javascript">
		$(function() {
			xjInit();

		});

		function xjInit() {
			$('#admin_xjgl_xjgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/sectionController/datagridAudit.action',
				queryParams : {
					zjid : getCookie("zjid")
				},
				fit : true,
				pagination : true,
				idField : 'id',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				frozenColumns : [ [ {
					field : 'id',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'uploader',
					title : '作者',
					width : 150,
					align : 'center',
				}, {
					field : 'name',
					title : '小节名称',
					width : 150,
					align : 'center',
				}, {
					field : 'code',
					title : '小节编号',
					width : 150,
					align : 'center',
				}, {
					field : 'context',
					title : '内容',
					width : 600,
					align : 'center',
				}, {
					field : 'minutes',
					title : '时长（分钟）',
					width : 80,
					align : 'center',
				}, {
					field : 'status',
					title : '状态',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == true) {
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
						xjRemove();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						xjEditFun();
					}
				}, '-', {
					text : '重置审核状态',
					iconCls : 'icon-edit',
					handler : function() {
						xjPass();
					}
				} ]
			});

		}

		function xjUnAudit() {
			$('#admin_xjgl_xjgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/sectionController/datagridUnaudit.action',
				queryParams : {
					zjid : getCookie("zjid")
				},
				fit : true,
				pagination : true,
				idField : 'id',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				frozenColumns : [ [ {
					field : 'id',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'uploader',
					title : '作者',
					width : 150,
					align : 'center',
				}, {
					field : 'name',
					title : '小节名称',
					width : 150,
					align : 'center',
				}, {
					field : 'code',
					title : '小节编号',
					width : 150,
					align : 'center',
				}, {
					field : 'context',
					title : '内容',
					width : 600,
					align : 'center',
				}, {
					field : 'minutes',
					title : '时长（分钟）',
					width : 80,
					align : 'center',
				}, {
					field : 'status',
					title : '状态',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == true) {
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
				}, '-', {
					text : '通过审核',
					iconCls : 'icon-edit',
					handler : function() {
						xjPass();
					}
				}, '-', {
					text : '返回',
					iconCls : 'icon-edit',
					handler : function() {
						xjInit();
					}
				} ]
			});
		}

		function xjPass() {
			var rows = $('#admin_xjgl_xjgl_datagrid').datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要授权当前选中的选项？', function(r) {
					if (r) {
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/sectionController/audit.action',
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
					msg : '请勾选要授权的选项！'
				});
			}

		}

		function xjEditFun() {
			var rows = $('#admin_xjgl_xjgl_datagrid').datagrid('getChecked');
			if (rows.length == 1) {
				var d = $('<div/>').dialog({
					width : 700,
					height : 550,
					href : '${pageContext.request.contextPath}/admin/xjgl/xjglEdit.jsp',
					align : 'center',
					title : '修改小节',
					buttons : [ {
						text : '修改',
						handler : function() {
							$('#admin_xjgl_xjglEdit_editForm').form('submit', {
								url : '${pageContext.request.contextPath}/sectionController/edit.action',
								success : function(data) {
									d.dialog('destroy');
									$('#admin_xjgl_xjgl_datagrid').datagrid('load');

									$.messager.show({
										title : '提示',
										msg : obj.msg,
									});
								}
							});
						}
					} ],
					onClose : function() {
						$(this).dialog('destroy');
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
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/sectionController/remove.action',
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

			$('#admin_xjgl_xjgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/sectionController/vaguedatagrid.action',
				queryParams : {
					zjid : getCookie("zjid")
				},
				fit : true,
				pagination : true,
				idField : 'id',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				frozenColumns : [ [ {
					field : 'id',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'uploader',
					title : '作者',
					width : 150,
					align : 'center',
				}, {
					field : 'name',
					title : '小节名称',
					width : 150,
					align : 'center',
				}, {
					field : 'code',
					title : '小节编号',
					width : 150,
					align : 'center',
				}, {
					field : 'context',
					title : '内容',
					width : 600,
					align : 'center',
				}, {
					field : 'minutes',
					title : '时长（分钟）',
					width : 80,
					align : 'center',
				}, {
					field : 'status',
					title : '状态',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == true) {
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
				检索小节名称(可模糊查询):<input name="subSectionName" /> </a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="xjSearchFun()">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="zjClearFun()">清空</a>
			</form>
			<br> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="xjUnAudit()">查看未审核</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_xjgl_xjgl_datagrid" data-options="border:false"></table>
		</div>

	</div>

	<div id="admin_xjgl_xjgl_addDialog" class="easyui-dialog" data-options="closed:true,title:'添加章节',buttons:[{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
                        document.getElementById('zjid').value = getCookie('zjid');
						$('#admin_xjgl_xjgl_addForm').form('submit',{
							url : '${pageContext.request.contextPath}/sectionController/add.action',					
							success : function(data) {
								$('#admin_xjgl_xjgl_datagrid').datagrid('load');
								$('#admin_xjgl_xjgl_addDialog').dialog('close');
								
								$.messager.show({
									title : '提示',
									msg : obj.msg+obj.success,
								});
							}
					});
					}}]" style="width: 55%; height: 80%;" align="center">
		<form id="admin_xjgl_xjgl_addForm" method="post">
			<table>
				<tr>
					<td><input name="zjid" id="zjid" type="hidden" value="" /></td>
				</tr>
				<tr>
					<th>小节名称</th>
					<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>内容</th>
					<td><textarea id="context" name="context" required="required" style="width: 800px; height: 400px; margin: 0 auto;">
    </textarea> <script type="text/javascript">
					var ue = UE.getEditor("context");
				</script></td>
				</tr>
				<tr>
					<th>编号</th>
					<td>第<input name="code" class="easyui-numberspinner" style="width: 60px;" data-options="min:1,max:120,editable:true">小节
					</td>
				</tr>
				<tr>
					<th>作者</th>
					<td><input name="uploader" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>时长</th>
					<td>共<input name="minutes" class="easyui-numberspinner" style="width: 60px;" required="required" data-options="min:1,max:120,editable:true">(分钟)
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
