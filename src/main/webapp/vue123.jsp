
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.entities.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ include file="Home.jsp" %>
<br>
<form class="d-flex col-md-4" action="viny">

		<p class="col-md-2">mot clé :</p>         
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="motcle">
        <button class="btn btn-outline-success" type="submit" name="action" value="chercher">Search</button>
</form>
<br>
<table class="table table-hover">
  <thead class="thead-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nom</th>
      <th scope="col">Prix</th>
      <th scope="col">Quantité</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${liste}" var="p">
    <tr>
		<td>${p.id} </td>
		<td>${p.nomP} </td>
		<td>${p.prix}</td>
		<td>${p.quantite} </td>
		<td><a href="viny?action=supprimer&id=${p.id}" class="btn btn-danger">Supprimer</a></td>
		<td><a href="viny?action=update&id=${p.id}" class="btn btn-success">editer</a></td>		
	</tr>
   </c:forEach>
  </tbody>
</table>


</body>
</html>