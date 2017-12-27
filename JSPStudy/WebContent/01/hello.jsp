<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
JSP表达式输出：<%="hello world" %>
<or/>
<%!
	String str="hello world";//均为jsp声明代码用于定义页面范围的变量函数和类  jsp声明为全局变量
%>

<% //jsp程序段
	//String str="hello world";
	out.println(str);//out对象用于向客户端输出数据
%>
</body>
</html>