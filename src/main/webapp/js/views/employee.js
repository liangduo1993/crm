$(function() {
	var employeeDatagrid = $("#employee_datagrid");
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
		pageList : [ 1, 5, 10, 20 ],
		onSelect:function(rowIndex, rowData){
			$(".state_check").linkbutton('enable');
			if(!rowData.state){
				$(".state_check").linkbutton('disable');
			}
		}
	});

	employeeDialog.dialog({
		width : 300,
		height : 320,
		buttons : "#bb",
		iconCls : "icon-add",
		resizeable : true,
		modal : true,
		closeable : true,
		closed:true
	});
	
	
	var cmdObj = {
			add:function () {
				employeeForm.form('clear');
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
				
				//console.debug($("#employee_form :input[name=admin]").val());
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


