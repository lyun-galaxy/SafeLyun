<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() {

		$('#admin_index_west_tree').tree({
			url : '${pageContext.request.contextPath}/admin/index/tree_data.json',
			loadFilter : function(data) {
				if (data.d) {
					return data.d;
				} else {
					return data;
				}
			}
		});

	});
</script>

<ul id="admin_index_west_tree" data-options="lines:true,
					onLoadSuccess:function(){$(this).tree('collapseAll')},
					onClick:function(node){
						if(node.attributes.url){
							var url='${pageContext.request.contextPath}/'+node.attributes.url;
							addTabs({title:node.text,href:url,closable:true});
						}
					}"></ul>

<%-- 
<ul id="admin_index_west_tree" class="easyui-tree" data-options="url:'admin/index/tree_data.json',lines:true,
					onLoadSuccess:function(){$(this).tree('collapseAll')},
					onClick:function(node){
						if(node.attributes.url){
							var url='${pageContext.request.contextPath}/'+node.attributes.url;
							addTabs({title:node.text,href:url,closable:true});
						}
					}">

</ul> --%>
