<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() {

	});
</script>

<ul id="admin_index_west_tree" class="easyui-tree" data-options="url:'admin/index/tree_data.json',lines:true,
					onLoadSuccess:function(){$(this).tree('collapseAll')},
					onClick:function(node){
						if(node.attributes.url){
							var url='${pageContext.request.contextPath}/'+node.attributes.url;
							addTabs({title:node.text,href:url,closable:true});
						}
					}">
	
</ul>
