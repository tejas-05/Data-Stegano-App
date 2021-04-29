<%@page import="java.io.File"%>
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
        img {
           -webkit-filter: contrast(200%) brightness(150%) grayscale(1);  /* Safari 6.0 - 9.0 */
          filter: contrast(200%) brightness(150%)grayscale(1);
       
 
   
}
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
       <%  
           String uniqueId = request.getParameter("id");
           String touseremail = request.getParameter("touseremail");
           String picid = request.getParameter("picid");
           File path = new File("F:/workspace/ReversibleDataHiding/build/web/listofpic/"+picid);
          
           //ImageConvertFilter img = new ImageConvertFilter();
          // img.grayImage(path);
           String imgpath = path.getAbsolutePath();
          // Grayscale1.grayImageFilter(imgpath);
           
           System.out.println("IMG PATh = "+path.getAbsolutePath());
           int id = Integer.parseInt(uniqueId); 
           
          %>    
         
          <div class="form_settings">
            <p><span>Unique ID</span><input class="contact" type="text" name="uniqueId" value="<%=uniqueId%>" readonly="" /></p>
            <p><span>Email Address</span><input class="contact" type="text" name="emailid" value="<%=touseremail%>" /></p>
            <p><span>Unique Pic ID</span><input class="contact" type="text" name="picUniqueID" value="<%=picid%>" /></p>
            <!--<p><span>Pic </span><td><img src="getSharedPic.jsp?id=<%=id%>" width="250" height="140"> </td></p>-->
            <p><span>Pic </span><td><img style="image-orientation: 2x"  src="listofpic/<%=picid%>" width="250" height="140"> </td></p>
            
          <p style="padding-top: 15px"><span>&nbsp;</span><a href="AdminSharedPic.jsp"><input class="submit" type="button" name="contact_submitted" value="Back" /></a></p>
            
          </div>
        </form>
          <%
String rspmsg = request.getParameter("msg");
if(rspmsg!=null && rspmsg.equalsIgnoreCase("faild")){
 out.println("<font color='RED'>Invalid password</font>");
}else if(rspmsg!=null && rspmsg.equalsIgnoreCase("success")){
String spDate = request.getParameter("data");
%>

            <%
}

%>
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
