<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PhotoGenome - Upload Photos</title>
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
		$('#UserIdHidden').val(sessionStorage.getItem("userId"));
}
	else {
		window.location = "Login.jsp";
	}
});
</script>
	<s:form action="resultAction" namespace="/" method="POST"
		enctype="multipart/form-data">

		<table>
			<tr>
				<td><s:file name="fileList" label="Select photo(s) to upload"
						multiple="multiple" /> <s:hidden id="UserIdHidden" name="userId"></s:hidden>
				</td>
				<td><s:submit value="Upload" name="submit" /></td>
			</tr>
		</table>
	</s:form>

	
</body>
</html>