<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PhotoGenome Home</title>
</head>
<body>
<script>
$(window).load(function () {
	if(sessionStorage.length > 0){
	
}
	else {
		window.location = "Login.jsp";
			
	}
});
</script>
<form>
<a href="../jsp/Logout.jsp" id="logout" >Logout</a><br>
<!--  input type="button" id="submitLogout" value="Logout"/><br>-->
</form>
<a href="../jsp/ViewAllPhotos.jsp">View All Photos</a><br>
<a href="../jsp/ViewPhotos.jsp">View Your Photos</a><br>
<a href="../jsp/PhotoLayout.jsp">Upload Photos</a><br>
<a href="../jsp/ImportMetadata.jsp">Import Metadata</a><br>
<a href="../jsp/ViewPhotosWSearch.jsp">Keyword based Search</a><br>
<a href="../jsp/EmailForm.jsp">Send email invitation</a><br>
</body>
</html>