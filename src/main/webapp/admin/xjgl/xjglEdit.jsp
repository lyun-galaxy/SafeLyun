<%@ page language="java" pageEncoding="UTF-8"%>

<form id="admin_xjgl_xjglEdit_editForm" method="post">
	<table>
		<input name="id" hidden="true" />
		<tr>
			<th>小节名称</th>
			<td><input name="name" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>内容</th>
			<td><input type="text" name="context" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>

	</table>
</form>
