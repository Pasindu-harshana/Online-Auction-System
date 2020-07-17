<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>UserWins</title>
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
	        <a class="nav-link" href="./view">Place Bids</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="InsertItem.jsp">Insert Items</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	        Register
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="Registation.jsp">Register as a User</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="SellerRegistation.jsp">Register as a Seller</a>
	        </div>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="./LogoutServlet">Logout</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	<center>
	 	<h2>Current Winning Items</h2>
	 	<table class="table table-bordered">
	 		<tr>
	 			<th>Item ID</th>
	 			<th>Seller ID</th>
	 			<th>My ID</th>
	 			<th>Item Name</th>
	 			<th>Item Description</th>
	 			<th>My Bid</th>
	 			
	 		</tr>
	 		<tr>
		 		<c:forEach var = "item" items ="${list2}">
		 			<c:forEach var="maxbid" items="${list1}">
		 				 <td>${maxbid.itemID}</td>
		 				 <td>${item.sellerID}</td>
		 				 <td>${maxbid.userID}</td>
			 			 <td>${item.itemName}</td>
			 			 <td>${item.itemDiscription}</td>
			 			 <td>${maxbid.maxBidPrice}</td>	
		 			 </c:forEach>	 			 
		 		</c:forEach>
		 		
		 			
		 		
		 	</tr>
	 	</table>
	</center>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>