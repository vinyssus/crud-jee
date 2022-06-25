package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestionCategorieJPA;
import dao.GestionProduitImpJPA;
import dao.IGestion;
import dao.IGestionCategorie;
import dao.IGestionmpl;
import dao.entities.Categorie;
import dao.entities.Produit;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/vinyssus")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    IGestion gestion;
    IGestionCategorie gestionC;
    @Override
    public void init() throws ServletException {
    	gestion = new GestionProduitImpJPA();
    	gestionC = new GestionCategorieJPA();
    	super.init();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Produit> liste = null;
		List<Categorie> listeC = null;
		/*PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<table border=1>");
		for(Produit p:liste)
			out.print("<tr><td>"+p.getNomP()+"</td><td>"+p.getPrix()+"</td></tr>");
			
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");     */   
		String action = request.getParameter("action"); 
		
		if (action==null) {
			
			liste=gestion.getAllProducts();
			listeC=gestionC.getAllCategories();
			
			if(liste.isEmpty()) {
				request.setAttribute("msg", "liste vide");
			}else 
				request.setAttribute("listeP", gestion.getAllProducts());
				request.getRequestDispatcher("listeP.jsp");	
			}
		else
		{
			if(action.equalsIgnoreCase("chercher"))
			{
				String mc = request.getParameter("motcle");
				request.setAttribute("listeP", gestion.getAllProductsBMC(mc));
				request.getRequestDispatcher("vue123.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("supprimer")){
				int id=Integer.parseInt(request.getParameter("id"));
				gestion.supprimerProduit(id);
				request.setAttribute("listeP",gestion.getAllProducts() );
				request.getRequestDispatcher("vue123.jsp").forward(request, response);
			}
				else if(action.equalsIgnoreCase("supprimerC"))
				{
					
					int id =  Integer.parseInt(request.getParameter("idc")) ; 
					Categorie cat = gestionC.getCategorie(id);
					 for (Produit p : gestion.getAllProducts()) {
						 if ((p.getCategorie()!=null)) {
						 if ((p.getCategorie().getNom()).equals(cat.getNom()) ) {
							 p.setCategorie(null);
							 gestion.mettreAjourProduit(p);
						 }
						 }
					 }
					 gestionC.deleteCategorie(id);
					 request.setAttribute("listeC",gestionC.getAllCategories() );
					request.getRequestDispatcher("listeCategorie.jsp").forward(request, response);	
					
				}
				
				
				
				if(liste.isEmpty()) {
					request.setAttribute("msg", "liste vide");
				}else 
					request.setAttribute("listeP", liste);
					request.getRequestDispatcher("vue123.jsp").forward(request, response);
			}
			
			else if(action.equalsIgnoreCase("save")) {
				String nom=request.getParameter("nom");
				Double prix=Double.parseDouble(request.getParameter("prix"));
				int quantite=Integer.parseInt(request.getParameter("quantite"));
				Produit p = new Produit(nom,prix,quantite);
				gestion.ajouterProduit(p);
				request.setAttribute("listeP", gestion.getAllProducts());
				request.setAttribute("categories", gestionC.getAllCategories());
				request.getRequestDispatcher("listeP.jsp");
			}
			else if (action.equalsIgnoreCase("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("produit", gestion.getProduct(id));
				request.getRequestDispatcher("AjoutProduit.jsp").forward(request, response);
				request.setAttribute("categories", gestionC.getAllCategories());
			}
			else if (action.equalsIgnoreCase("ajouter") && request.getMethod().equalsIgnoreCase("post")){
				
				String nom = request.getParameter("nom");
				double prix = Double.parseDouble(request.getParameter("prix"));
				int quantite = Integer.parseInt(request.getParameter("quantite"));
				int idp = Integer.parseInt(request.getParameter("idp"));
				int idcat = Integer.parseInt(request.getParameter("categorie"));
				Categorie c = new GestionCategorieJPA().getCategorie(idcat);
				
				if (idp!=0) {
					
					Produit p = gestion.getProduct(idp);
					        p.setCategorie(c);
							p.setNomP(nom);
					        p.setPrix(prix);
					        p.setQuantite(quantite);
					        gestion.mettreAjourProduit(p);
				}
				else {
					gestion.ajouterProduit(new Produit(nom,prix,quantite));
				}
				liste = gestion.getAllProducts();
						request.setAttribute("liste", liste);
				request.getRequestDispatcher("AjoutProduit.jsp").forward(request, response);
			}
			}
		request.setAttribute("liste", liste);
		request.getRequestDispatcher("vue123.jsp").forward(request, response);   
		
	}
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 

}
