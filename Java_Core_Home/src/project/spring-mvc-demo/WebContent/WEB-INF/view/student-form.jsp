<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Registration Form</title>
</head>
<body>
	<form:form action="processForm" method="get" modelAttribute="student">
		First Name: <form:input path="firstName" />
		<br>
		<br>
		Last Name: <form:input path="lastName" />
		<br>
		<br>
		Country: 
		<form:select path="country">
			<form:options items="${student.countryOptions}" />
		</form:select>
		<br>
		<br>
		Favorite Language: 
		<form:radiobuttons path="favoriteLanguage"
			items="${student.favoriteLanguageOptions }" />
		<br>
		<br>
		Operating System: 
		Windows <form:checkbox path="operatingSystems" value="Windows" />
		Linux <form:checkbox path="operatingSystems" value="Linux" />
		MacOS <form:checkbox path="operatingSystems" value="MacOS" />
		Unix <form:checkbox path="operatingSystems" value="Unix" />
		<br>
		<br>
		<input type="submit" value="Click Me !">


	</form:form>

</body>
</html>