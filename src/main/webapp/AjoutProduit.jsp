

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dao.entities.Produit"%>
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

<h2 class="text-center text-success">Ajouter Produit</h2>
<form class="container" action="viny">
  <div class="form-group">
      <label>nom</label>
    <input type="nom" class="form-control" id="nom" name="nom" placeholder="name" value="${produit.nom }">
  </div>
  <br>
  <div class="form-group">
    <label>prix</label>
    <input type="prix" class="form-control" id="prix" name="prix" placeholder="price" value="${produit.prix }">
  </div>
  <br>
  <div class="form-group">
      <label>quantité</label>
    <input type="quantite" class="form-control" id="quantite" name="quantite" placeholder="quantite" value="${produit.quantite }">
  </div>
  <br><br>
  <button type="submit" class="btn btn-primary" name="action" value="save">Submit</button>
</form>
</body>
</html>