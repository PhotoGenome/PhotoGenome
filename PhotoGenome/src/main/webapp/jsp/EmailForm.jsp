<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send Email</title>
     <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css" type="text/css" />
        <link rel="stylesheet" href="PG-Style.css" type="text/css" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
 
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

<form>
<a href="../jsp/Home.jsp">Home</a>
<a href="../jsp/Logout.jsp" id="logout" >Logout</a><br>
<!--  input type="button" id="submitLogout" value="Logout"/><br>-->
</form>
  
  
<script>
$(window).load(function () {
	if(sessionStorage.length > 0){
	
}
	else {
		window.location = "Login.jsp";
			
	}
});
</script>




	<center>
		<h1>Send Email</h1>
		<s:form action="doSendEmail" enctype="multipart/form-data" method="post">

			<table border="0" width="80%" align="center">
				<tr>
					<td>
						<s:textfield name="recipient" size="65" label="Recipient Address" />
					</td>
				</tr> 
				<tr>
					<td>
						<s:textfield name="subject" size="65" label="Subject" value="Join PhotoGenome!"/>
					</td>
				</tr> 
				<tr>
					<td>
						<s:textarea cols="50" rows="10" name="message" label="Message" 
						value="PhotoGenome is a great place to share memories and experiences with others.">						
						</s:textarea>
					</td>
				</tr> 
				<tr>
					<td>
						<s:file name="fileUpload" size="60" label="Attach file" />
					</td>
				</tr>				
				<tr>
					<td>
						<s:submit value="Send E-mail" align="center" />
					</td>
				</tr>
			</table>
		</s:form>
	</center>
</body>
</html>