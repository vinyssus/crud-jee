package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.entities.Produit;


public class IGestionmpl implements IGestion {
	Connection cx = SingletonConnection.getInstance();
	@Override
	public void ajouterProduit(Produit p) {
		try {
			PreparedStatement st = cx.prepareStatement("insert into produit(nom,prix,quantite)values(?,?,?)");
			st.setString(1, p.getNomP());
			st.setDouble(2, p.getPrix());
			st.setInt(3, p.getQuantite());
			st.executeUpdate();

		}catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
	}

	@Override
	public void supprimerProduit(int id) {
		try {
			PreparedStatement st = cx.prepareStatement("delete from produit where id = ?");
			st.setInt(1, id);
			st.executeUpdate();

		}catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
	}

	@Override
	public void mettreAjourProduit(Produit p) {
		try {
			PreparedStatement st = cx.prepareStatement("update produit set nom=?,prix=?,quantite=? where id =?");
			st.setString(1, p.getNomP());
			st.setDouble(2, p.getPrix());
			st.setInt(3, p.getQuantite());
			st.setInt(4, p.getId());
			st.executeUpdate();

		}catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
	}

	@Override
	public List<Produit> getAllProducts() {
		List<Produit> liste = new ArrayList<>();
		try {
			PreparedStatement ps = cx.prepareStatement("select * from produit ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produit p = new Produit();
				p.setId(rs.getInt(1));
				p.setNomP(rs.getString(2));
				p.setPrix(rs.getDouble(3));
				p.setQuantite(rs.getInt(4));
				liste.add(p);

			    }
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public List<Produit> getAllProductsBMC(String motcle) {
		List<Produit> liste = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = cx.prepareStatement("select * from produit  where nom like ?");
			ps.setString(1, "%" + motcle + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produit p = new Produit();
				p.setId(rs.getInt(1));
				p.setNomP(rs.getString(2));
				p.setPrix(rs.getDouble(3));
				p.setQuantite(rs.getInt(4));
				liste.add(p);
		
			}
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public Produit getProduct(int id) {
		List<Produit> liste = new ArrayList<>();
		Produit p = new Produit();
		PreparedStatement ps;
		try {
			ps = cx.prepareStatement("select * from produit where id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				
				p.setId(rs.getInt(1));
				p.setNomP(rs.getString(2));
				p.setPrix(rs.getDouble(3));
				p.setQuantite(rs.getInt(4));
			}
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return p;
	}
	

}
