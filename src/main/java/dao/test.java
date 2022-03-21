
package dao;

import dao.entities.Produit;

public class test {



	public static void main(String[] args) {
		IGestionmpl gestion = new IGestionmpl() ;
		
		Produit p1 = new Produit("pc",1500,4);
		
		Produit p2 = new Produit("clavier",20,10);
		
		Produit p3 = new Produit("telephone",850,15);
		
		//gestion.ajouterProduit(p1);
		//gestion.ajouterProduit(p2);
		//gestion.ajouterProduit(p3);
		
		gestion.getAllProducts();
		
		System.out.println(gestion.getProduct(1));
		Produit p = gestion.getProduct(3);
		p.setNomP("tel");
		gestion.mettreAjourProduit(p);
		//gestion.supprimerProduit(5);
		System.out.println(gestion.getAllProductsBMC("clavier"));
		System.out.println(gestion.getAllProducts());		
		
		

	}

}