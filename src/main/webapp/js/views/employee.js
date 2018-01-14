$(function() {
	var employeeDatagrid = $("#employee_datagrid");
	var selfRoleDatagrid = $("#self_role_datagrid");
	var allRoleDatagrid = $("#all_role_datagrid");
	var employeeDialog = $("#employee_dialog");
	var employeeForm = $("#employee_form");
	var input_admin = $("#employee_form :input[name=admin]");
	var input_inputtime =$("#employee_form :input[name=inputtime]");
	var input_id = $("#employee_form input[name='id']");
	
	employeeDatagrid.datagrid({
		fit : true,
		rownumbers : true,
		url : "/employee_list",
		method : "get",
		singleSelect : true,
		fitColumns : true,
		pagination : true,
		toolbar : '#employee_tb',
		pageList : [ 10,20,50 ],
		onSelect:function(rowIndex, rowData){
			$(".state_check").linkbutton('enable');
			if(!rowData.state){
				$(".state_check").linkbutton('disable');
			}
		},
		pageSize:50
	});

	employeeDialog.dialog({
		width : 800,
		height : 500,
		buttons : "#bb",
		iconCls : "icon-add",
		resizeable : true,
		modal : true,
		closeable : true,
		closed:true
	});
	
	
	selfRoleDatagrid.datagrid({
		title:'自身角色',
		rownumbers : true,
		method : "get",
		singleSelect : true,
		fitColumns : true,
		onDblClickRow:function(rowIndex, rowData){
			$(this).datagrid('deleteRow',rowIndex);
		},
	});
	
	allRoleDatagrid.datagrid({
		title:'所有角色',	
		rownumbers : true,
		url : "/role_list",
		method : "get",
		singleSelect : true,
		fitColumns : true,
		pagination:true,
		pageSize:10,
		onDblClickRow:function(rowIndex, rowData){
			var rows = selfRoleDatagrid.datagrid('getRows');
			var flag = true;
			for(var i=0;i<rows.length;i++){
				if(rows[i].name == rowData.name){
					flag = false;
					break;
				}
			}
			if(flag){
				selfRoleDatagrid.datagrid('insertRow',{
					row:rowData
				});
			}
		},
	});
	var p = allRoleDatagrid.datagrid('getPager');
	$(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        displayMsg:'',
	        showRefresh:false,
	        showPageList:false,
	    }); 
	
	
//		$.get('/department_list',function(data){
//			var rows = data.rows;
//			for(var i=0;i<rows.length;i++){
//				data.rows[i]['dept.name']=rows[i].name;
//			}
//			console.debug(rows);
			$("#dept_id").combobox({
				valueField: 'id',
				textField: 'name',
				url:'/department_list',
			});
//		});
	
	
	
	var cmdObj = {
			add:function () {
				employeeForm.form('clear');
				selfRoleDatagrid.datagrid('loadData',{total:0,rows:[]}); 
				p.pagination('select',1);
				input_inputtime.val(null);
				employeeDialog.panel('open').panel('setTitle',
						'Add an Employee');
			},
			save:function () {
				var id = input_id.val();
				var url;
				if (!id) {
					url = '/employee_save';
				} else {
					url = '/employee_update';
				}
				
				if(input_admin.prop("checked")){
					input_admin.prop("value",1);
				}
				
				//easyui的form表单提交方式
				employeeForm.form('submit', {
					url : url,
					success : function(data) {
						var data = $.parseJSON(data);
						if (data.success) {
							$.messager.alert('温馨提示', data.msg, 'info', function() {
								employeeDialog.dialog('close');
								employeeDatagrid.datagrid('load');
							});
						}else{
							$.messager.alert('温馨提示', data.msg, 'info');
						}
					},
					onSubmit: function(param){    
						var rows = selfRoleDatagrid.datagrid('getRows');
						console.debug(rows);
						for(var i=0;i<rows.length;i++){
							param['roles['+i+'].id'] = rows[i].id;    
						}
				    }
				});
			},
			del:function () {
				var rowData = employeeDatagrid.datagrid('getSelected');
				if (!rowData) {
					$.messager.alert('温馨提示', '请选择需要离职的员工!', 'info');
				} else {
					$.messager.confirm('确认对话框', '您确定要离职该员工么？', function(r) {
						if (r) {
							//jQuery.get(url, [data], [callback], [type])
							$.get('/employee_changeState', {id : rowData.id}, function(data) {
								if (data.success) {
									$.messager.alert('温馨提示', data.msg, 'info',	function() {
														employeeDatagrid.datagrid(
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
			edit:function () {
				var rowData = employeeDatagrid.datagrid('getSelected');
				if (!rowData) {
					$.messager.alert('温馨提示', '请选择需要编辑的员工!', 'info');
				} else {
					selfRoleDatagrid.datagrid('options').url = '/role_queryRoleByEID?id=' + rowData.id;
					selfRoleDatagrid.datagrid('load');
					employeeDialog.panel('open').panel('setTitle',
							'Edit an Employee');
					//easyUI的form表单数据回显遵从同名匹配的原则
					if(rowData.dept)
						rowData['dept.id'] = rowData.dept.id;
					employeeForm.form('clear').form('load', rowData);
					if(rowData.admin){
						input_admin.prop("checked",true);
					}
					
				}
			},
			refresh:function () {
				employeeDatagrid.datagrid('reload');
				$(".state_check").linkbutton('enable');
			},
			cancel:function () {
				employeeDialog.panel('close');
			},
		
			queryByCondition:function (){
					var params = {};
					var arr =$("#query_form").serializeArray();
					for(var i=0; i < arr.length; i++){
						params[arr[i].name]=arr[i].value;
					}
					employeeDatagrid.datagrid('load',params);
				}

	
	
	};
	
	
	$("a[data-cmd]").on('click',function(){
		var cmd = $(this).data("cmd");
		cmdObj[cmd]();
	});
	
	
});






function formatterDept(value, row, index) {
	if (value) {
		return value.name;
	} else {
		return "";
	}
}
function formatterState(value, row, index) {
	if (value == 1) {
		return "<font color='green'>在职</font>";
	} else {
		return "<font color='red'>离职</font>";
	}
}
function formatterAdmin(value, row, index) {
	if (value == 1) {
		return "<font color='red'>是</font>";
	} else {
		return "否";
	}
}


