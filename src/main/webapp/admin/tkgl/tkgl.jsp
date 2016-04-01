<%@ page language="java" pageEncoding="UTF-8"%>

<body>
	<script type="text/javascript">
		$(function() {
			tkInit();
		
		});

		function tkInit() {
			$('#admin_tkgl_tkgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/questionbank/auditdatagrid.action',
				fit : true,
				pagination : true,
				idField : 'id',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				sortName : 'createDateTime',
				sortOrder : 'asc',
				frozenColumns : [ [ {
					field : 'id',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'title',
					title : '题目',
					width : 200,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'a',
					title : 'A选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'b',
					title : 'B选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'c',
					title : 'C选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'd',
					title : 'D选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'answer',
					title : '正确选项',
					width : 70,
					align : 'center',
				}, {
					field : 'createDateTime',
					title : '创建时间',
					width : 150,
					align : 'center',
					sortable : true,
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'status',
					title : '状态',
					width : 50,
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
						tkRemove();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						tkEditFun();
					}
				} ]
			});

		}

		function tkUnAudit() {
			$('#admin_tkgl_tkgl_datagrid').datagrid('unselectAll');
			$('#admin_tkgl_tkgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/questionbank/unauditdatagrid.action',//查询未经认证数据
				fit : true,
				pagination : true,
				idField : 'id',
				checkOnSelect : false,
				selectOnCheck : false,
				fitColumns : true,
				rownumbers : true,
				sortName : 'createDateTime',
				sortOrder : 'asc',
				frozenColumns : [ [ {
					field : 'id',
					title : '编号',
					width : 150,
					align : 'center',
					//hidden : true,
					checkbox : true
				}, {
					field : 'title',
					title : '题目',
					width : 200,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'a',
					title : 'A选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'b',
					title : 'B选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'c',
					title : 'C选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'd',
					title : 'D选项',
					width : 150,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'answer',
					title : '正确选项',
					width : 70,
					align : 'center',
				}, {
					field : 'createDateTime',
					title : '创建时间',
					width : 150,
					align : 'center',
					sortable : true,
					formatter : function(value, row, index) {
						return '<span title="'+value+'">' + value + '</span>';
					}
				}, {
					field : 'status',
					title : '状态',
					width : 50,
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
						tkAppend();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						tkRemove();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						tkEditFun();
					}
				}, '-', {
					text : '通过审核',
					iconCls : 'icon-ok',
					handler : function() {
						tkPass();
					}
				}, '-', {
					text : '批量导入',
					iconCls : 'icon-edit',
					handler : function() {
						tkImport();
					}
				}, '-', {
					text : '返回',
					iconCls : 'icon-back',
					handler : function() {
						tkInit();
					}
				} ]
			});

		}

		function tkPass() {
			var rows = $('#admin_tkgl_tkgl_datagrid').datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要授权当前选中的选项？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/questionbank/audit.action',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(d) {
								var v = $('#admin_tkgl_tkgl_datagrid');
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

		function tkEditFun() {
			var rows = $('#admin_tkgl_tkgl_datagrid').datagrid('getChecked');
			if (rows.length == 1) {
				var d = $('<div/>').dialog({
					width : 400,
					height : 500,
					href : '${pageContext.request.contextPath}/admin/tkgl/tkglEdit.jsp',
					modal : true,
					align : 'center',
					title : '修改题目',
					buttons : [ {
						text : '修改',
						handler : function() {
							$('#admin_tkgl_tkglEdit_editForm').form('submit', {
								url : '${pageContext.request.contextPath}/questionbank/edit.action',
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if (obj.success) {
										d.dialog('destroy');
										//$('#admin_tkgl_tkgl_datagrid').datagrid('load');
										$('#admin_tkgl_tkgl_datagrid').datagrid('updateRow', {
											index : $('#admin_tkgl_tkgl_datagrid').datagrid('getRowIndex', rows[0]),
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
						$('#admin_tkgl_tkglEdit_editForm').form('load', rows[0]);
					}
				});
			} else {
				$.messager.alert('提示', '请勾选一个要编辑的选项！');
			}
		}

		function tkRemove() {
			var rows = $('#admin_tkgl_tkgl_datagrid').datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中的选项？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/questionbank/remove.action',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(d) {
								var v = $('#admin_tkgl_tkgl_datagrid');
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

		function tkAppend() {

			$('#admin_tkgl_tkgl_addForm input').val('');
			$('#admin_tkgl_tkgl_addDialog').dialog('open');
		}

		function tkImport() {
			$('#admin_tkgl_tkgl_importDialog input').val('');
			$('#admin_tkgl_tkgl_importDialog').dialog('open');
		}

		function tkSearchFun() {
			//	$('#admin_tkgl_tkgl_datagrid').datagrid('load', {
			//		name : $('#admin_tkgl_tkgl_layout input[name=name]').val()
			//	});
			$('#admin_tkgl_tkgl_datagrid').datagrid('load', serializeObject($('#admin_tkgl_tkgl_searchForm')));
		}

		function tkClearFun() {
			$('#admin_tkgl_tkgl_layout input[name=name]').val('');
			$('#admin_tkgl_tkgl_datagrid').datagrid('load', {});
		}

		function impInfo() {
			/* alert(getPaths(document.getElementById('uploadfile'))); */
			/*   alert("1===" + $('#uploadfile').filebox('getText'));
			  if($('#uploadfile').val() == "")
			  {
			      alert("请选择Excel文件!");
			      return;
			  } */
			/*   
			  $.ajaxComplete(function(){
			      $(this).hide();
			  });
			 */
			$('#admin_tkgl_tkgl_rollingDialog').dialog('open'); 
			
			$.ajaxFileUpload({
				url : '${pageContext.request.contextPath}/questionbank/importfile.action',//用于文件上传的服务器端请求地址
				secureuri : true,//是否启用安全提交，一般设置为false
				fileElementId : 'uploadfile',//文件上传控件的id
				dataType : 'text',//服务器返回的数据类型
				success : function(data) {
					$('#admin_tkgl_tkgl_rollingDialog').dialog('close'); 
					var obj = jQuery.parseJSON(data);
					if (obj.success) {
						$('#admin_tkgl_tkgl_datagrid').datagrid('load');
						$('#admin_tkgl_tkgl_importDialog').dialog('close');
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

	<div id="admin_tkgl_tkgl_layout" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
			<form id="admin_tkgl_tkgl_searchForm">
				检索题目(可模糊查询):<input name="name" /> </a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="tkSearchFun()">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="tkClearFun()">清空</a>

			</form>
			<br> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="tkUnAudit()">查看未审核</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="admin_tkgl_tkgl_datagrid" data-options="border:false"></table>
		</div>

	</div>

    <div id="admin_tkgl_tkgl_importDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加题目',buttons:[{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						impInfo();	
					}}]" style="width: 350px;height: 150px;">
		<form id="admin_tkgl_tkgl_importForm" method="post">
				Excel文件：<input type="file" id="uploadfile" name="file" style="width:300px" >
		</form>

	</div>

	<div id="admin_tkgl_tkgl_rollingDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'提示'" style="width: 300px;height: 70px;" align="center">
		<img alt="上传中。。。" src="jslib/imgs/rolling.gif"><br> <a>上传中,请等候...</a>
	</div>

	<div id="admin_tkgl_tkgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加题目',buttons:[{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						$('#admin_tkgl_tkgl_addForm').form('submit',{
							url : '${pageContext.request.contextPath}/questionbank/add.action',
							success : function(data) {
								var obj = jQuery.parseJSON(data);
								if (obj.success) {
									/*$('#admin_tkgl_tkgl_datagrid').datagrid('load');*/
									/*$('#admin_tkgl_tkgl_datagrid').datagrid('appendRow',obj.obj);*/
									$('#admin_tkgl_tkgl_datagrid').datagrid('insertRow',{
										index: 0,
										row:obj.obj
									});
									$('#admin_tkgl_tkgl_addDialog').dialog('close');
								}
								$.messager.show({
									title : '提示',
									msg : obj.msg,
								});
							}
					});
					}}]" style="width: 350px;height: 300px;" title="添加题目">
		<form id="admin_tkgl_tkgl_addForm" method="post">
			<table>
				<tr>
					<th>题目</th>
					<td><input name="title" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>A选项</th>
					<td><input name="a" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>B选项</th>
					<td><input name="b" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>C选项</th>
					<td><input name="c" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>D选项</th>
					<td><input name="d" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr align="left">
					<td>正确选项:</td>
					<td><select name="answer">
							<option value="A" selected="selected">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
					</select></td>
				</tr>
				<tr align="center">

				</tr>

			</table>
		</form>
	</div>

</body>
