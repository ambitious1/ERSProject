<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>My Reimbursements</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>My Reimbursements</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Amount</th>
          <th>Submitted</th>
          <th>Resolved</th>
          <th>Description</th>
          <th>Author</th>
          <th>Resolver</th>
          <th>Status</th>
          <th>Type</th>
          
       </tr>
       <c:forEach items="${reimbList}" var="reimbList" >
          <tr>
             <td>${reimbList.ID}</td>
             <td>${rembList.Amount}</td>
             <td>${reimbList.Submitted}</td>
             <td>${reimbList.Resolved}</td>
             <td>${reimbList.Description}</td>
             <td>${reimbList.Author}</td>
             <td>${reimbList.Resolver}</td>
             <td>${reimbList.Status}</td>
              </tr>
       </c:forEach>
    </table>
 
    <a href="requestReimb" >Request Reimbursement</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>