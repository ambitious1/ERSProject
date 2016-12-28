<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Manager's Info</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_adminMenu.jsp"></jsp:include>
 
    <h3>Hello: ${user.username}</h3>
 
    User Name: <b>${user.username}</b>
    <br />
    First Name: ${user.firstName } <br />
    Last Name: ${user.lastName } <br />
    Email: ${user.email } <br />
    Role: ${user.role } <br />
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>