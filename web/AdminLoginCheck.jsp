<%
String loginId = request.getParameter("loginid");
String pswd = request.getParameter("pswd");

if(loginId.equalsIgnoreCase("Admin")&& pswd.equalsIgnoreCase("Admin@18")){
response.sendRedirect("AdminHome.jsp");
}else{
response.sendRedirect("Admin.jsp?msg=faild");
}



%>