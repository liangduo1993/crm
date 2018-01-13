$(function() {

	$("#systemTree").tree({
		url : '/js/data/menu.json',
		onClick : function(node) {
			if (!$("#myTabs").tabs('exists', node.text) && node.attributes.url) {
				$("#myTabs").tabs("add", {
					title : node.text,
					closable:true,
					href:node.attributes.url
				});
			}else{
				$("#myTabs").tabs('select',node.text);
			}
			
		}

	});

});
