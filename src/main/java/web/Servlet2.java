package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GestionCategorieJPA;
import dao.GestionProduitImpJPA;
import dao.IGestion;
import dao.IGestionCategorie;
import dao.entities.Categorie;
import dao.entities.Produit;


/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/viny")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IGestion gestion ;
	IGestionCategorie gestionC ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
    public void init() throws ServletException {
    	gestion = new GestionProduitImpJPA();
    	gestionC = new GestionCategorieJPA();
super.init();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action==null) {
			request.setAttribute("listeP", gestion.getAllProducts());
			request.getRequestDispatcher("vue123.jsp").forward(request, response);
		}
		else {
			if(action.equalsIgnoreCase("chercher"))
			{
				String mc=request.getParameter("motcle");
				request.setAttribute("listeP", gestion.getAllProductsBMC(mc));
				request.getRequestDispatcher("vue123.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("supprimer"))
				{
					int id =  Integer.parseInt(request.getParameter("id")) ; 
					gestion.supprimerProduit(id);		
					request.setAttribute("listeP",gestion.getAllProducts() );
					request.getRequestDispatcher("vue234.jsp").forward(request, response);
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
				request.getRequestDispatcher("AjoutCat .jsp").forward(request, response);	
				
			}
			
			else if(action.equalsIgnoreCase("ajoutP"))
				{
					request.setAttribute("listeC", gestionC.getAllCategories());
					request.getRequestDispatcher("vue234.jsp").forward(request, response);
				}	
			else if(action.equalsIgnoreCase("saveP")){
						int idcat = Integer.parseInt(request.getParameter("categorie"));
						Categorie c = gestionC.getCategorie(idcat);
						String idp = request.getParameter("idp");
						if(idp!="")
						{
							Produit p = gestion.getProduct(Integer.parseInt(request.getParameter("idp")));
							p.setNomP(request.getParameter("nom"));
							p.setPrix(Double.parseDouble(request.getParameter("prix")));
							p.setQuantite(Integer.parseInt(request.getParameter("qte")));
							gestion.mettreAjourProduit(p);
						}
						else {
							Produit p =new Produit(request.getParameter("nom"),
									Double.parseDouble(request.getParameter("prix")),
									Integer.parseInt(request.getParameter("qte"))
									);
							p.setCategorie(c);
							gestion.ajouterProduit(p);
							
							
						}
						
						request.setAttribute("listeP", gestion.getAllProducts());
						request.setAttribute("listeC", gestionC.getAllCategories());

						request.getRequestDispatcher("vuejspl.jsp").forward(request, response);
					}
			else if(action.equalsIgnoreCase("ajoutC"))
			{
				request.setAttribute("listeC", gestionC.getAllCategories());
				request.getRequestDispatcher("AjoutCat.jsp").forward(request, response);
			}	
			else if(action.equalsIgnoreCase("saveC")){
				int idcat = Integer.parseInt(request.getParameter("categorie"));
				Categorie c = gestionC.getCategorie(idcat);
				String idc = request.getParameter("idc");
				if(idc!="")
				{
					Categorie cat = gestionC.getCategorie(Integer.parseInt(request.getParameter("idc")));
					cat.setNom(request.getParameter("nom"));
					gestionC.mettreAjourCategorie(cat);
				}
				else {
					Categorie c1 =new Categorie (request.getParameter("nom"));
					gestionC.addCategorie(c1);;
					
				}
				request.setAttribute("listeC", gestionC.getAllCategories());
				request.getRequestDispatcher("AjoutCat.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("update")){
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("produit", gestion.getProduct(id));
				request.setAttribute("categorie", gestionC.getCategorie(id));
				request.getRequestDispatcher("vue234.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("categorie")){
				System.out.println(gestionC.getAllCategories());
				request.setAttribute("listeC", gestionC.getAllCategories());
				request.getRequestDispatcher("AjoutCat.jsp").forward(request, response);
			}
					
				
			
		}
		
		
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
