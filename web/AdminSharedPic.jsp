<%@page import="com.reversable.db.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE HTML>
<html>

<head>
  <title>Reversible Data Hiding </title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="style/style.css" title="style" />
   <style>
th, td {
   border: 1px solid black;
 }
textarea {
   width: 100%;
}
        </style>
</head>

<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="AdminHome.jsp">Implementation of Separable Reversible Data Hiding <span class="logo_colour">Scheme in Image Encryption Process</span></a></h1>
          
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li><a href="AdminHome.jsp">Home</a></li>
          <li><a href="RegisteredUsers.jsp">Users</a></li>
          <li class="selected"><a href="AdminSharedPic.jsp">Shared Pics</a></li>
         
          <li><a href="Logout.jsp">Log Out</a></li>
        </ul>
      </div>
    </div>
    <div id="site_content">
      
        <!-- insert the page content here -->
        <h1>Welcome to <font style="color: red"><b>Admin</b></font></h1>
          
             <div class="form_settings">
             <table>
                <tr>
                    <th>S.No</th>
                    <th>User Name</th>
                  
                    <th>Emails</th>
                    <th>Encode Message</th>
                    <th>Pic Unique Name</th>
                    <th>Picture</th>
                    <th>Date</th>
                    <th>View</th>
                    
                </tr>
                <% 
                Connection con = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                int sno = 0;
                try{
                con = DBConnection.getDBConnection();
                String sqlQuery = "select *from sharedpics";
                ps = con.prepareStatement(sqlQuery);
               
                rs = ps.executeQuery();
                while(rs.next()){
                    sno++;
                int uniqueID = rs.getInt("id");
                String fromuser = rs.getString("fromuser");
                String touser = rs.getString("touser");
                String touseremail = rs.getString("touseremail");
                String encodemessage = rs.getString("encodemessage");
                String picid = rs.getString("picid");
                String pic = rs.getString("pic");
                String shareddate = rs.getString("shareddate");
                
                
                    %>
                <tr>
                    <td><%=sno%> </td>
                    <td><%=fromuser%> </td>
                    <!--<td><%=touser%> </td>-->
                    <td><%=touseremail%> </td>
                    <td><textarea id="t"><%=encodemessage%></textarea> </td>
                    <td><%=picid%> </td>
                    <td><img src="getSharedPic.jsp?id=<%=uniqueID%>" width="60" height="60"> </td>
                    <td><%=shareddate%> </td>
                    <td><a href="GetPictures.jsp?id=<%=uniqueID%>&touseremail=<%=touseremail%>&picid=<%=picid%>">View</a></td>
                </tr>
                <%
                }
                }catch(Exception ex){
                 ex.printStackTrace();
                }
                
                
%>
                
                    
            </table>
      
    </div>
    <div id="footer">
      
    </div> 
        
      
    </div>
    <div id="footer">
      Copyright &copy; Reverse Water marking | <a href="www.google.com">HTML5</a> | <a href="./www.google.com">CSS</a> | <a href="www.google.com">Java Templates</a>
    </div>
  </div>
</body>
</html>
