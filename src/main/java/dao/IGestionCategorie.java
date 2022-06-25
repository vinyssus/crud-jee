package dao;

import java.util.List;

import dao.entities.Categorie;

public interface IGestionCategorie {

	public void addCategorie(Categorie c);
	public List<Categorie> getAllCategories();
	public Categorie getCategorie(int id);
	public void deleteCategorie(int id);
	public void mettreAjourCategorie(Categorie c);
}
