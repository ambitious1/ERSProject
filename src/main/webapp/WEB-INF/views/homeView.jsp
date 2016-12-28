<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width-device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">  
      <link rel="stylesheet" href="../js/animatedText.js">
     <title>Employee Reimbursement System</title>
  </head>
  <body>
 
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>
    
      <h3>ERS Home</h3>
      
        <ul id = "example">
         
         <li>Storing Users Information using Cookies and Sessions</li>
         <li>Reimbursement List</li>
         <li>Request Reimbursement</li>
         <li>Managers can approve and deny</li>
         
        </ul>

     <jsp:include page="_footer.jsp"></jsp:include>
 
  </body>
</html>