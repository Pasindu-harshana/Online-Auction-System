<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>SellersItems</title>
</head>
<body>
	<% 
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires", 0);
		if(session.getAttribute("seller")==null){
			response.sendRedirect("SellerLogin.jsp");
		}
	%>
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
	<h1 align="center">Sellers Items</h1>
	<div>
		<c:if test="${not empty massage}">
			<div align = "center" style ="color:green;"><h2><c:out value="${massage}"></c:out></h2></div>
		</c:if>
		<c:if test="${not empty massage2}">
			<div align = "center" style = "color:red;"><h5><c:out value="${massage2}"></c:out></h5></div>
		</c:if>
	</div>
	<table align = "center"cellpadding="10%" class="table table-bordered">
		<tr>
			<th>Item No</th>
			<th>Item Name</th>
			<th>item Description</th>
			<th>Item Price</th>
			<th>Item Image</th>
		</tr>
		<c:forEach var="items" items="${itemList}">
			<tr>
				<td>${items.itemNo}</td>
				<td>${items.itemName}</td>
				<td>${items.itemDiscription}</td>
				<td>${items.itemPrice}</td>
				<td>
					<img src="data:image/jpg;base64,${items.base64Image}"width = "250" height = "150" align = "left"/>
					<br>
					
						<table class="table-borderless">
							<tr>
								<td>
									<form action = "delete" method = "post">
										<input type="submit" class="btn btn-sm btn-primary" value="DeletetheItem">
										<input type="hidden" value="${items.itemNo}" name="itemID">
									</form>
								</td>
							</tr>
							<tr>
								<td>
									<form action="getItemSession.jsp" method="post">
										<input type="hidden" value="${items.itemNo}" name="itemID">
										<input type = "submit" class="btn btn-sm btn-primary" value = "UpdateDetails">
									</form>
								</td>
							</tr>
							<tr>
								<td>
									<form action="viewWinner" method = "post">
										<input type = hidden value = "${items.itemNo}" name="itemID">
										<input type = "submit" class = "btn btn-sm btn-primary" value = "View Current Winner">
									</form>
								</td>
							</tr>
						</table>
				</td>
			</tr>
		</c:forEach>
	</table>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>