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
			<td><input type="text" name="context" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<th>时长</th>
		<td><input name="minutes" class="easyui-numberspinner" style="width:60px;" required="required" data-options="min:1,max:120,editable:true">(分钟)</td>

	</table>
</form>
