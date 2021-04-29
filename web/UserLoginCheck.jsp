<%@page import="com.reversable.db.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
String userName = request.getParameter("loginid");
String pswd = request.getParameter("pswd");

Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;

try{
con = DBConnection.getDBConnection();
String sqlQuery = "select *from usrdata where loginid = ? and pswd = ?";
ps = con.prepareStatement(sqlQuery);
ps.setString(1,userName);
ps.setString(2, pswd);
rs = ps.executeQuery();
if(rs.next()){
session.setAttribute("useremail", rs.getString("email"));
session.setAttribute("username", rs.getString("username"));
session.setAttribute("loginuser", userName);
response.sendRedirect("UserHome.jsp");
}else{
response.sendRedirect("User.jsp?msg=faild");
}
}catch(Exception ex){
ex.printStackTrace();
}


%>