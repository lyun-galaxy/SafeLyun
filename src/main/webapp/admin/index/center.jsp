<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	function addTabs(optis) {
		var t = $('#lyout_center_tabs');
		if (t.tabs('exists', optis.title)) {
			t.tabs('select', optis.title);
		} else {
			t.tabs('add', optis);
		}
	}
</script>

<div id="lyout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false">
	<div title="首页">Tab11231231</div>
</div>

