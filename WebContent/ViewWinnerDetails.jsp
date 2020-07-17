<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>Winner Details</h2>
		<table>
				<tr>
					<td>Item No:</td>
					<td>${maxbidInfo.itemID}</td>
				</tr>
				<tr>
					<td>Win Price:</td>
					<td>${maxbidInfo.maxBidPrice}</td>
				</tr>
				<tr>
					<td>Winner ID:</td>
					<td>${maxbidInfo.userID}</td>
				</tr>
				<tr>
					<td>Winner Name:</td>
					<td>${winnerInfo.name}</td>
				</tr>
				<tr>
					<td>Winner Mobile Number:</td>
					<td>${winnerInfo.mobileNo }</td>
				</tr>

		</table>
	</center>
</body>
</html>