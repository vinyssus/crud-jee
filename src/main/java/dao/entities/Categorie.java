package dao.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private	int id;
   private String nom;
   
   @OneToMany(mappedBy = "categorie",cascade = CascadeType.ALL)
   private List<Produit>liste;
   


public Categorie(String nom) {
	super();
	this.nom = nom;
}

public Categorie() {
	super();
}

public Categorie(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

}
