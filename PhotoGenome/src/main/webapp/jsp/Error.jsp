<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send Email Error</title>
</head>
<body>
	<center>
		<h2>There were a problem while sending an e-mail to: <s:property value="recipient"/></h2>
		<h3>Error details:</h3>
		<h4><s:property value="exception" /></h4>
	</center>
</body>
</html>