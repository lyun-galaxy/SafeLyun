<%@ page language="java" pageEncoding="UTF-8"%>

<form id="admin_zjgl_zjglEdit_editForm" method="post">
	<table>
		<tr>
			<input name="sectionId" hidden="true" />
			<input name="sectionChecked" hidden="true" />
			<th>章节名称</th>
			<td><input name="sectionName" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>作者</th>
			<td><input name="sectionUploader" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<th>编号 </th>
		<td>第<input name="sectionCode" class="easyui-numberspinner" style="width:60px;"  data-options="min:1,max:120,editable:true">(小节)</td>
	</table>
</form>
