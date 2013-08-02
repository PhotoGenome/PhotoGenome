<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send Email Result</title>
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
		<h2>The email was sent successfully!</h2>
	</center>
</body>
</html>