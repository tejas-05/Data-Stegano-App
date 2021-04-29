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
          <h1><a href="UserHome.jsp">Implementation of Separable Reversible Data Hiding <span class="logo_colour">Scheme in Image Encryption Process</span></a></h1>
          
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li class="selected"><a href="UserHome.jsp">Home</a></li>
          <li><a href="UploadPic.jsp">Share Pic</a></li>
          <li><a href="SharedToMe.jsp">Shared to me</a></li>
          <li><a href="SharedToImages.jsp">Shared</a></li>
          <li><a href="Logout.jsp">Log Out</a></li>
        </ul>
      </div>
    </div>
    <div id="site_content">
        <% String userName = session.getAttribute("username").toString(); %>
        <!-- insert the page content here -->
        <h1>Welcome to <font style="color: red"><b><%=userName%></b></font></h1>
          
            
        
      
    </div>
    <div id="footer">
      Copyright &copy; Reverse Water marking | <a href="www.google.com">HTML5</a> | <a href="./www.google.com">CSS</a> | <a href="www.google.com">Java Templates</a>
    </div>
  </div>
</body>
</html>
