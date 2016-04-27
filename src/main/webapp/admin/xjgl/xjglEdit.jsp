<%@ page language="java" pageEncoding="UTF-8"%>

<form id="admin_xjgl_xjglEdit_editForm" method="post">
	<table>
		<input name="id" hidden="true" />
		<input name="status" hidden="true" />
		<tr>
			<th>小节名称</th>
			<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>内容</th>
			<td><textarea id="admin_xjgl_xjglEdit_context" name="context" required="required" style="width: 800px; height: 400px; margin: 0 auto;">
    </textarea> <script type="text/javascript">
					var ue = UE.getEditor("admin_xjgl_xjglEdit_context");
				</script></td>

		</tr>
		<tr>
			<th>时长</th>
			<td>共<input name="minutes" class="easyui-numberspinner" style="width: 60px;" required="required" data-options="min:1,max:120,editable:true">(分钟)
			</td>
		</tr>
		<tr>
			<th>编号</th>
			<td>第<input name="code" class="easyui-numberspinner" style="width: 60px;" required="required" data-options="min:1,max:120,editable:true">(小节)
			</td>
		<tr>
	</table>
</form>
