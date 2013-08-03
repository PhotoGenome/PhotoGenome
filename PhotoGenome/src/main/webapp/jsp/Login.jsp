<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Struts 2 - Login Application</title>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css" type="text/css" />
        <link rel="stylesheet" href="PG-Style.css" type="text/css" />
  
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
  <style type="text/css" media="screen">
  body { background-color: white; font: 16px Helvetica, Arial; color: black; }
  #canvas { height: 500px; background: white; }
  </style>
  
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      
  <script>
       
        $(document).ready(function() {                        
            
      	  $('#submitL').click(function(event) {  
      		var username=$('#username').val();
      		var password=$('#password').val();
      	  login(username,password);  
      	  });
      	  
      	 function login(username,password){
     	        $.getJSON(
                 	  'login.action' , {username:username,password:password},
                 function(jsonLogin) {
                 		  if(jsonLogin.items==="NotAUser"){
                 			  $('#error').text('Invalid user and password');  
                 		  } else{
                 			window.location = "Home.jsp";
                 			sessionStorage.setItem("userId", jsonLogin.items.userId);
                 			sessionStorage.setItem("userFirstName", jsonLogin.items.userFirstName);
                     				  
                 		  }
                 		  
                 	  }
                 	);
                 return false;
            };
        });
           
        </script>
</head>
<body>
<h2>Struts 2 - Login Application</h2>
<form id="form1">
<table width="100%" bordercolor="black">
<tr><td>Login into PhotoGenome</td></tr>
<tr><td>
<div id="error" style="color:red;" ></div>
Username: <input type="text" id="username" />
Password: <input type="password" id="password" />
<input type="button" id="submitL" value="Submit"/>
</td></tr>
</table>
</form>
</body>
</html>