<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Update Seller Details</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="Index.html">Online Bidding</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="Index.html">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="InsertItem.jsp">Insert Items</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="UpdateSellerDetails.jsp">Update Info</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="./LogoutServlet2">Logout</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	<form action="DeleteSellerServlet" method="post">
				<input type="submit" value="Delete Seller Account">
	</form>
	<center>
		<br>
		<h2>Update Seller Details</h2>
		<br>
		<form action="UpdateSellerDetails"  method="post">
			<table>
				<tr>
					<td>Seller Name:</td>
					<td>
						<input type = "text" name="sellerName">
						<c:if test="${not empty massage1}">
							<c:out value="${massage1}"></c:out>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>Seller Pass:</td>
					<td>
						<input type="password" name="sellerPass">
					</td>
				</tr>
				<tr>
					<td>Re-Enter Pass:</td>
					<td>
						<input type="password" name="reEnter">
						<c:if test="${not empty massage2}">
							<c:out value="${massage2}"></c:out>
						</c:if>
						<c:if test="${not empty massage3}">
							<c:out value="${massage3}"></c:out>
						</c:if>
					</td>	
				</tr>
				<tr>
					<td>Mobile No:</td>
					<td>
						<input type="text" name="mobileNo" value="0">
						<c:if test="${not empty massage4}">
							<c:out value="${massage4}"></c:out>
						</c:if>
						<c:if test="${not empty massage5}">
							<c:out value="${massage5}"></c:out>
						</c:if>
						<c:if test="${not empty massage6}">
							<c:out value="${massage6}"></c:out>
						</c:if>
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" value="Update" class="btn btn-primary">
		</form>
			
	</center>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>