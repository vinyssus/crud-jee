package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Categorie;

public class GestionCategorieJPA implements IGestionCategorie{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionproduit");
	EntityManager em = emf.createEntityManager();	

	@Override
	public void addCategorie(Categorie c) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.persist(c);
		et.commit();
		
	}
		

	@Override
	public List<Categorie> getAllCategories() {
		Query q=em.createQuery("select c from Categorie c");
		return q.getResultList();
		
		
	}

	@Override
	public Categorie getCategorie(int id) {
		return em.find(Categorie.class, id);
		
	}

	@Override
	public void deleteCategorie(int id) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.remove(getCategorie(id));
		et.commit();
		
	
		
	}


	@Override
	public void mettreAjourCategorie(Categorie c) {
		// TODO Auto-generated method stub
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.persist(c
				);
		et.commit();
		
	}

	
}
