<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PhotoGenome - Upload Photos</title>
</head>
<body>
	<s:form action="resultAction" namespace="/" method="POST"
		enctype="multipart/form-data">

		<table>
			<tr>
				<td><s:file name="fileList" label="Select photo(s) to upload"
						multiple="multiple" /> <s:hidden name="userId" value="1000"></s:hidden>
				</td>
				<td><s:submit value="Upload" name="submit" /></td>
			</tr>
		</table>
	</s:form>

	<a href="http://localhost:8080/PhotoGenome/jsp/ViewPhotos.jsp">View
		Uploaded Photos</a>
</body>
</html>