<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Welcome to Spring Security</h1>
	<hr>
	<p>
		User :
		<security:authentication property="principal.username" />
		<br> <br> Roles :
		<security:authentication property="principal.authorities" />
	</p>

	<hr>
	<security:authorize access="hasAnyRole('MANAGER','ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath }/leaders">Leadership
				Meeting</a> Only for Managers
		</p>
		<hr>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath }/systems"> IT
				Administration</a> Only for ADMIN
		</p>
		<hr>
	</security:authorize>

	<form:form action="${pageContext.request.contextPath }/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>