<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>

	<%@include file="/WEB-INF/views/common.jsp" %>
	<script type="text/javascript" src="/js/views/index.js"></script>
	<style type="text/css">
		.border_right_none{
			border-right: none
		}
	</style>


</head>
<body>




    <div class="easyui-layout" style="height:350px;" fit="true" width="800">
        <div data-options="region:'north'" style="height:80px;background:url('/img/banner-pic.gif') no-repeat bottom;background-size: cover;" border="border" >
        	<div align="center"> 
        		<h1>管理系统</h1>
        	</div>
        </div>
        <div data-options="region:'south'" style="height:50px;background:url('/img/banner-pic.gif') no-repeat bottom;background-size: cover;" border="border">
        	<div align="center">版权信息 </div>
        </div>
        <div data-options="region:'west',width:150"  headerCls="border_right_none" bodyCls="border_right_none">
        	<div class="easyui-accordion" fit="true" border="border">
				<div title="菜单" iconCls="icon-sum">
					<ul id="systemTree"></ul>
				</div>     
				   	
				<div title="帮助" iconCls="icon-tip">
					<h3 style="color:#0099FF;">Accordion for jQuery</h3>
					<p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
            	</div>        	
        	</div>
        </div>
        <div data-options="region:'center',width:650">
			
			<div id="myTabs" class="easyui-tabs" fit="true">	
				<div title="欢迎页" closable="true">
					<center> <h1 style="color:blue">欢迎登陆系统</h1> </center>
				</div>
			
			</div>
			            
            
            
            
        </div>
    </div>







</body>
</html>