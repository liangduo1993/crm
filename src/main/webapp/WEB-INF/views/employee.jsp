<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<%@include file="/WEB-INF/views/common.jsp" %>
	<script type="text/javascript" src="/js/views/employee.js"></script>
	
	
</head>
<body>

	<table id="employee_datagrid" class="easyui-datagrid">
		<thead>
			<tr>
				<th field="id" halign="center" width="1">ID</th>
				<th field="username" halign="center" width="2">姓名</th>
				<th field="realname" halign="center" width="2">真实姓名</th>
				<th field="tel" halign="center" width="2">电话</th>
				<th field="email" halign="center" width="2">EMAIL</th>
				<th field="dept" halign="center" width="2" formatter="formatterDept">部门名称</th>
				<th field="inputtime" halign="center" width="2">入职时间</th>
				<th field="state" halign="center" width="2"
					formatter="formatterState">离职状态</th>
				<th field="admin" halign="center" width="2"
					formatter="formatterAdmin">管理员</th>
			</tr>
		</thead>
	</table>



	<div id="employee_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add" >新增</a> 
			<a class="easyui-linkbutton state_check" iconCls="icon-edit" plain="true"  data-cmd="edit">编辑</a> 
			<a class="easyui-linkbutton state_check" iconCls="icon-remove" plain="true" data-cmd="del" >离职</a> 
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true"  data-cmd="refresh">刷新</a>
		</div>
		<div>
			<form action="" id="query_form">
				关键字：<input type="text" name="keyword" style="width:80px"/>		
				入职时间：<input type="text" class="easyui-datebox"  name="beginDate"/>
				-<input type="text" class="easyui-datebox" name="endDate"/>
				状态<select name="state">
					<option value="">请选择</option>
					<option value="1">在职</option>
					<option value="0">离职</option>
					</select>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="cmdObj.queryByCondition();">查询</a>
			</form>
		</div>
	</div>
	
	
	
	<div id="employee_dialog" class="easyui-dialog" >
		<form id="employee_form" action="" method="post">
				<input type="hidden" name="id" />
				<table style="margin-top: 10px">
					<tr>
						<td>
						<table style="width:250px;height:400px;">
							<tr>
								<td>用户名：</td>
								<td><input type="text" name="username"></td>
							</tr>
							<tr>
								<td>真实姓名：</td>
								<td><input type="text" name="realname"></td>
							</tr>
							<tr>
								<td>密码：</td>
								<td><input type="password" name="password"></td>
							</tr>
							<tr>
								<td>电话：</td>
								<td><input type="text" name="tel"></td>
							</tr>
							<tr>
								<td>Email：</td>
								<td><input type="text" name="email" class="easyui-validatebox"
									data-options="validType:'email'"></td>
							</tr>
							<tr>
								<td>入职时间：</td>
								<td><input type="date" name="inputtime"></td>
							</tr>
							<tr>
								<td>部门：</td>
								<td><select id="dept_id" class="easyui-combobox"
									name="dept.id" style="width: 150px;">
								</select></td>
							</tr>
							<tr>
								<td>是否管理员：</td>
								<td><input type="checkbox" name="admin" value="0"></td>
							</tr>
						</table>
				</td>
				<td>
						<table id="self_role_datagrid" class="easyui-datagrid" style="width:220px;height:400px;">
							<thead>
								<tr>
									<th field="name" halign="center" width="1">权限名称</th>
								</tr>
							</thead>
						</table>
				</td>
				<td style="width: 30px"></td>
				<td >
						<table id="all_role_datagrid" class="easyui-datagrid" style="width:220px;height:400px;">
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