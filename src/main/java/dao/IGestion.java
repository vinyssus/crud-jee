package dao;

import java.util.List;

import dao.entities.Produit;

public interface IGestion {


	public void ajouterProduit (Produit p);
	public void supprimerProduit (int id);
	public void mettreAjourProduit (Produit p);
	public List<Produit> getAllProducts ();
	public List<Produit> getAllProductsBMC (String motcle);
	public Produit getProduct (int id);
	
}
