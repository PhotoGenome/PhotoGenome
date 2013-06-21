<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>
   File Name : <s:property value="fileUploadFileName"/> 
</h4> 

<h4>
   Content Type : <s:property value="fileUploadContentType"/> 
</h4> 

<h4>
   File : <s:property value="fileUpload"/> 
</h4> 

Uploaded Image:
    <br/>
    <img src="<s:property value="fileUploadFileName"/>" photoId="<s:property value="photoId"/>" />

</body>
</html>