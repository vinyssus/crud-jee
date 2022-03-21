package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Produit;

public class GestionProduitImpJPA implements IGestion {
 
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionproduit");
	EntityManager em = emf.createEntityManager();	
	@Override
	public void ajouterProduit(Produit p) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.persist(p);
		et.commit();


	}

	@Override
	public void supprimerProduit(int id) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.remove(getProduct(id));
		et.commit();

	}

	@Override
	public void mettreAjourProduit(Produit p) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.persist(p);
		et.commit();
	}

	@Override
	public List<Produit> getAllProducts() {
		Query q=em.createQuery("select p from Produit p");
		return q.getResultList();
	}

	@Override
	public List<Produit> getAllProductsBMC(String motcle) {
		Query q=em.createQuery("select p from produit p where p.nom like :x");
		q.setParameter("x", "%"+motcle+"%");
		return q.getResultList();
	}

	@Override
	public Produit getProduct(int id) {
		
		return em.find(Produit.class, id);
	}

}
