$(function() {
	var roleDatagrid = $("#role_datagrid");
	var permissionDatagrid = $("#permission_datagrid");
	var allpermissionDatagrid = $("#all_permission_datagrid");
	var roleDialog = $("#role_dialog");
	var roleForm = $("#role_form");
	var input_id = $("#role_form input[name='id']");
	
	roleDatagrid.datagrid({
		fit : true,
		rownumbers : true,
		url : "/role_list",
		method : "get",
		singleSelect : true,
		fitColumns : true,
		pagination : true,
		toolbar : '#role_tb',
		pageList : [ 1, 5, 10, 20 ]
	});

	roleDialog.dialog({
		width : 540,
		height : 500,
		buttons : "#bb",
		iconCls : "icon-add",
		resizeable : true,
		modal : true,
		closeable : true,
		closed:true
	});
	
	permissionDatagrid.datagrid({
		title:'自身权限',
		rownumbers : true,
		method : "get",
		singleSelect : true,
		fitColumns : true,
		fit : true,
		onDblClickRow:function(rowIndex, rowData){
			permissionDatagrid.datagrid('deleteRow',rowIndex);
		},
	});
	
	allpermissionDatagrid.datagrid({
		title:'所有权限',	
		rownumbers : true,
		url : "/permission_list",
		method : "get",
		singleSelect : true,
		fitColumns : true,
		fit : true,
		pagination:true,
		pageSize:10,
		onDblClickRow:function(rowIndex, rowData){
			var rows = permissionDatagrid.datagrid('getRows');
			var flag = true;
			for(var i=0;i<rows.length;i++){
				if(rows[i].name == rowData.name){
					flag = false;
					break;
				}
			}
			if(flag){
				permissionDatagrid.datagrid('insertRow',{
					row:rowData
				});
			}
		},
	});
	
	var p = allpermissionDatagrid.datagrid('getPager');
	$(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        displayMsg:'',
	        showRefresh:false,
	        showPageList:false,
	    });  
	
	var cmdObj = {
			add:function () {
				roleForm.form('clear');
				permissionDatagrid.datagrid('loadData',{total:0,rows:[]}); 
				p.pagination('select',1);
				roleDialog.panel('open').panel('setTitle',
						'Add an role');
			},
			edit:function () {
				var rowData = roleDatagrid.datagrid('getSelected');
				if (!rowData) {
					$.messager.alert('温馨提示', '请选择需要编辑的角色!', 'info');
				} else {
					permissionDatagrid.datagrid('options').url = '/permission_queryByRID?id=' + rowData.id;
//					$.get('/permission_queryByRID', {id:rowData.id},function(data){
//						permissionDatagrid.datagrid('loadData',data);
//					} ,'json');
					permissionDatagrid.datagrid('reload');
					roleDialog.panel('open').panel('setTitle',
							'Edit an role');
					roleForm.form('clear').form('load', rowData);
				}
			},
			del:function () {
				var rowData = roleDatagrid.datagrid('getSelected');
				if (!rowData) {
					$.messager.alert('温馨提示', '请选择需要禁用的角色!', 'info');
				} else {
					$.messager.confirm('确认对话框', '您确定要禁用该角色么？', function(r) {
						if (r) {
							//jQuery.get(url, [data], [callback], [type])
							$.get('/role_changeState', {id : rowData.id}, function(data) {
								if (data.success) {
									$.messager.alert('温馨提示', data.msg, 'info',	function() {
														roleDatagrid.datagrid(
																'reload');
													});
								} else {
									$.messager.alert('温馨提示', data.msg, 'info');
								}
							}, 'json');
						}
					});
				}
			},
			refresh:function () {
				roleDatagrid.datagrid('reload');
				$(".state_check").linkbutton('enable');
			},
			
			save:function () {
				var id = input_id.val();
				var url;
				if (!id) {
					url = '/role_save';
				} else {
					url = '/role_update';
				}
				
				roleForm.form('submit', {
					url : url,
					success : function(data) {
						var data = $.parseJSON(data);
						if (data.success) {
							$.messager.alert('温馨提示', data.msg, 'info', function() {
								roleDialog.dialog('close');
								roleDatagrid.datagrid('load');
							});
						}else{
							$.messager.alert('温馨提示', data.msg, 'info');
						}
					},
					 onSubmit: function(param){ 
						 	var rows = permissionDatagrid.datagrid('getRows');
						 	for(var i=0;i<rows.length;i++){
						 		 param['permissions['+i+'].id']=rows[i].id;
						 	}
					    } 
					
					
				});
				
				
				
				
			},

			cancel:function () {
				roleDialog.panel('close');
			},
			loadPermission:function(){
				$.messager.confirm('确认对话框', '权限加载需要消耗一定的时间，您确定要加载么？', function(r) {
					if (r) {
						//jQuery.get(url, [data], [callback], [type])
						$.get('/permission_load',  function(data) {
							$.messager.alert('温馨提示', data.msg, 'info');
							if (data.success)
								allpermissionDatagrid.datagrid('reload');
						}, 'json');
					}
				});
			}
	
	};
	
	
	$("a[data-cmd]").on('click',function(){
		var cmd = $(this).data("cmd");
		cmdObj[cmd]();
	});
	
	
});







