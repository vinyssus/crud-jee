<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dao.entities.Produit"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link rel="stylesheet" href="ajoutproduit.css" />


<meta charset="ISO-8859-1">
<title>Ajouter un produit</title>
</head>
<body>
<%@include file="Home.jsp" %>
<br><br>


<form>
<h2>Ajouter Produit</h2>
<div class="box">
  <div class="form-group ">
    <label for="exampleInputEmail1" >Nom:</label><br>
    <input type="text" class="form-control"  id="nom" name="nom" placeholder="Nom" value="${produit.nom}"><br>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Prix:</label><br>
    <input type="number" class="form-control"  id="prix" name="prix" placeholder="Prix" value="${produit.prix}" ><br>
  </div>
 <div class="form-group">
    <label for="exampleInputPassword1">Quantité:</label><br>
    <input type="number" class="form-control"  id="qte" name="qte" placeholder="Quantité" value="${produit.qte}"><br>
  </div>
  <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Catégorie
  </button>
  
  <select name="categorie">
  <option selected hidden>Choose here </option>
    <c:forEach items="${listeC}" var="c">
  	<option value=${c.id} <c:if test="${produit.categorie.id=c.id }">selected="true"</c:if>>${c.nom}</option>
  	  </c:forEach>
  </select>
</div>

  <input type=hidden name=idp value="${produit.id}">
  <button type="submit" class="btn btn-primary"  name="action" value="save">Save</button>
  </div>
</form>
</body>
</html>