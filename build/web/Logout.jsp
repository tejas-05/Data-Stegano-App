<%-- 
    Document   : Logout
    Created on : Dec 13, 2018, 2:32:46 PM
    Author     : Ramu Maloth 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <% 
       session.invalidate();
       response.sendRedirect("index.jsp");
       
%>
    </body>
</html>
