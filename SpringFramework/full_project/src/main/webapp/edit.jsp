<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1 style="text-align: center;">UPDATE EMPLOYEE</h1>
<form:form action="update" modelAttribute="emp" method="get" style="text-align: center;">
   ID: <form:input path="id"/><br><br>
   NAME: <form:input path="name"/><br><br>
   PHONE: <form:input path="phone"/><br><br>
   SALARY <form:input path="salary"/><br> <br><br>
    <form:button style="color: white; background-color: green;">update</form:button>
</form:form>


</body>
</html>