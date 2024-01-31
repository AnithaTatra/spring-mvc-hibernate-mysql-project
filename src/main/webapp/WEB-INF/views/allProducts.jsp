<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Products</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<style type="text/css">
tr:first-child {
	font-weignt: bold;
	background-color: #c6c9c4
}
</style>
</head>
<body>
	<div class="container">
		<h2>All Registered Products</h2>
		<c:if test="${not empty success}">
			<div class="alert alert-success" role="alert">${success}</div>
		</c:if>

		<hr />
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<td>Product ID</td>
					<td>Product Name</td>
					<td>Price</td>
					<td>Modify</td>
					<td>Delete</td>
				</tr>
				<c:forEach items="${allproducts}" var="product">
					<tr>
						<td>${product.id}</td>
						<td>${product.name}&nbsp;${student.description}</td>
						<td>${student.price}</td>
						<td><a href="<c:url value='/edit-${product.id}' />">Modify</a></td>
						<td><a href="<c:url value='/delete-${product.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
		</table>
		<hr />
		<div class="form-group">
			<a class="btn btn-secondary" href="<c:url value='/new' />">Add
				New Product</a>
		</div>
	</div>
</body>
</html>