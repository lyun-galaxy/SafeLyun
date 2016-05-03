<%@ page language="java" pageEncoding="UTF-8"%>

<form id="admin_tkgl_tkglEdit_editForm" method="post">
	<table>
		<input name="id" hidden="true" />
		<input name="status" hidden="true" />
		<tr>
			<th>修改人</th>
			<td><input name="uploadName" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
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
		<tr>
			<td>正确选项:</td>
		</tr>
		<tr>
			<td><input type="radio" name="answer" id="answer" value="a" />A</td>
			<td><input type="radio" name="answer" id="answer" value="b" />B</td>
			<td><input type="radio" name="answer" id="answer" value="c" />C</td>
			<td><input type="radio" name="answer" id="answer" value="d" />D</td>
		</tr>
        
	</table>
</form>