<%@page import="com.reversable.db.DBConnection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
    String loginid = request.getParameter("loginid");
    String pswd = request.getParameter("pswd");
    String custname = request.getParameter("custname");
    String email = request.getParameter("email");
    String mobile = request.getParameter("mobile");
    String address = request.getParameter("address");
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = DBConnection.getDBConnection();
        String sqlQuery = "insert into usrdata(loginid,pswd,username,email,mobile,address,cdate) values(?,?,?,?,?,?,?)";
        ps = con.prepareStatement(sqlQuery);
        ps.setString(1, loginid);
        ps.setString(2, pswd);
        ps.setString(3, custname);
        ps.setString(4, email);
        ps.setString(5, mobile);
        ps.setString(6, address);
        ps.setDate(7, sqlDate);
        int no = ps.executeUpdate();
        if (no > 0) {
            response.sendRedirect("Register.jsp?msg=success");
        } else {
            response.sendRedirect("Register.jsp?msg=faild");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>