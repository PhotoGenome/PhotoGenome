<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Struts 2 - Login Application</title>
</head>
<body>
<h2>Struts 2 - Login Application</h2>
<s:actionerror />
<s:form action="login.action" method="post">
	<s:textfield name="username" key="username" size="20" />
	<s:password name="password" key="password" size="20" />
	<s:submit method="execute" key="login" align="center" />
</s:form>
</body>
</html>