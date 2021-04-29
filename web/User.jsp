<!DOCTYPE HTML>
<html>

<head>
  <title>Reversible Data Hiding </title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="style/style.css" title="style" />
</head>

<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.jsp">Implementation of Separable Reversible Data Hiding <span class="logo_colour">Scheme in Image Encryption Process</span></a></h1>
          
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li><a href="index.jsp">Home</a></li>
          <li class="selected"><a href="User.jsp">Login</a></li>
          <li><a href="Register.jsp">Register</a></li>
          <li><a href="Admin.jsp">Admin</a></li>
          <li><a href="contact.html">Contact Us</a></li>
        </ul>
      </div>
    </div>
    <div id="site_content">
     
        <!-- insert the page content here -->
        <h1>User Login  Form</h1>
          <form action="UserLoginCheck.jsp" method="post">
          <div class="form_settings">
            <table>
                    <tr>
                        <td>Login ID</td>
                        <td><input type="text" class="contact" name="loginid" required  pattern="[A-Za-z]+" title="Name should not contain integer value"></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="pswd" required pattern="(?=.*\d)(?=.*[a-z]).{8,}" title="Must contain at least one number and lowercase letter, and at least 8 or more characters"> </td>
                    </tr>
                       
                    <tr>
                        <td><input type="reset" name="Reset" class="submit"> </td>
                        <td><input type="submit" name="submit" value="Login" class="submit"> </td>
                       
                    </tr>
                    <tr>
            <%
                String msg = request.getParameter("msg");
                if(msg!=null && msg.equalsIgnoreCase("success")){
                out.println("<font color='green'>Registration Successfull </font>");
                }else if(msg!=null && msg.equalsIgnoreCase("faild")){
                out.println("<font color='RED'>Invalid user Name or Password</font>");
                }
          
%>
                </table>
          </div>
        </form>
            
        
      
    </div>
    <div id="footer">
      Copyright &copy; Reverse Water marking | <a href="www.google.com">HTML5</a> | <a href="./www.google.com">CSS</a> | <a href="www.google.com">Java Templates</a>
    </div>
  </div>
</body>
</html>
