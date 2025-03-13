<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<center>
<table border="1"  style="border-collapse: collapse; width: 80% ; text-align: center;">

<tr>

<td>ID</td>
<td>NAME</td>
<td>PHONE</td>
<td>SALARY</td>
<td>UPDATE</td>
<td>DELETE</td>
</tr>

<c:forEach var="emp" items="${employees}">

<tr>
<td>${emp.id}</td>
<td>${emp.name}</td>
<td>${emp.phone}</td>
<td>${emp.salary}</td>

<td><a href="edit?id=${emp.id}"><button style="background-color: green;color: white; ">update</button></a></td>

<td><a href="delete?id=${emp.id}"><button style="background-color: red;color: white; ">delete</button></a></td>

</tr>

</c:forEach>

</table>

</center>

</body>
</html>