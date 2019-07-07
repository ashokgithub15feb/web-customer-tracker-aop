<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Save Customer</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/add-customer-style.css">

</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Management</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Save Customer</h3>
		<form:form action="saveCustomer" modelAttribute="customer" method="POST">
			
			<!-- need to associate this data with customer id -->
			<!-- add the customer id - at submit the form - data base saying set call -->
			<form:hidden path="id"/>
			
			<table>
				<tbody>
					<tr>
						<td>First Name:</td>
						<td><form:input path="firstName"/></td>
					</tr>
					
					<tr>
						<td>Last Name :</td>
						<td><form:input path="lastName"/></td>
					</tr>
					
					<tr>
						<td>Email     :</td>
						<td><form:input path="email"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form:form>
		
		<div style="clear; both;"></div>
		<p>
			<a href="${pageContext.request.contextPath }/customer/list">Back To List</a>
		</p>
		
	</div>
	
</body>
</html>