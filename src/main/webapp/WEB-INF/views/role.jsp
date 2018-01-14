<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<%@include file="/WEB-INF/views/common.jsp" %>
	<script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>

	<table id="role_datagrid" class="easyui-datagrid">
		<thead>
			<tr>
				<th field="sn" halign="center" width="1">角色编码</th>
				<th field="name" halign="center" width="1">角色名称</th>
			</tr>
		</thead>
	</table>



	<div id="role_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add" >新增</a> 
			<a class="easyui-linkbutton state_check" iconCls="icon-edit" plain="true"  data-cmd="edit">编辑</a> 
			<a class="easyui-linkbutton state_check" iconCls="icon-remove" plain="true" data-cmd="del" >禁用</a> 
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true"  data-cmd="refresh">刷新</a>
			<a class="easyui-linkbutton" iconCls="icon-tip" plain="true"  data-cmd="loadPermission">权限加载</a>
		</div>
	</div>
	
	
	
	<div id="role_dialog" class="easyui-dialog" >
		<form id="role_form" action="" method="post">
			<table align="center" style="margin-top: 10px;">
				<input type="hidden" name="id" />
				</br>
				<tr>
					<div style="padding-left: 30px">
						角色编码：<input type="text" name="sn">
						角色名称：<input type="text" name="name">
					</div>
				</tr>
				<tr>
				<td style="width:250px;height:350px;">
					<table id="permission_datagrid" class="easyui-datagrid">
						<thead>
							<tr>
								<th field="name" halign="center" width="1">权限名称</th>
							</tr>
						</thead>
					</table>
				</td>
				<td style="width:250px;height:350px;">
					<table id="all_permission_datagrid" class="easyui-datagrid">
						<thead>
							<tr>
								<th field="name" halign="center" width="1">权限名称</th>
							</tr>
						</thead>
  					</table>
				</td>
				</tr>
				
			</table>
		</form>
		
		
	</div> 
	
	<div id="bb">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save" >Save</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel" >Cancel</a>
	</div>
	


</body>
</html>