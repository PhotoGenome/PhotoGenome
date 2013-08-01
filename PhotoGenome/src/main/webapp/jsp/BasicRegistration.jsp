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
            
      	  $('#submitR').click(function(event) {  
      		var firstname=$('#firstname').val();
      		var lastname=$('#lastname').val();
      		var email=$('#email').val();
      		var password=$('#password').val();
      	  register(firstname,lastname,email,password);  
      	  });
      	  
      	 function register(firstname,lastname,email,password){
     	        $.getJSON(
                 	  'register.action' , {firstname:firstname,lastname:lastname,email:email,password:password},
                 function(jsonLogin) {
                 		  if(jsonLogin.items==="NotRegistered"){
                 			  $('#error').text('Invalid details or server error.');  
                 		  } else{
                 			window.location = "Login.jsp";
                 			$('#error').text('You have successfully registered. Please login.');  
                           				  
                 		  }
                 		  
                 	  }
                 	);
                 return false;
            };
        });
           
        </script>
</head>
<body>
<h2>Photogenome Registration</h2>
<form id="form1">
<table width="100%" bordercolor="black">
<tr><td>Register for PhotoGenome</td></tr>
<tr><td>
<div id="error" style="color:red;" ></div>
First Name: <input type="text" id="firstname" /><br>
Last Name: <input type="text" id="lastname" /><br>
Email: <input type="text" id="email" /><br>
Password: <input type="password" id="password" /><br>
<input type="button" id="submitR" value="Submit"/><br>
</td></tr>
</table>
</form>
</body>
</html>