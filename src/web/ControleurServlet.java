package web;

import metier.CreditMetier;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;   // importer pour utiliser les annotations pour deployer la servlet
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





// 2 eme methode pour deployer la servlet dans le serveur web : les annotations

@WebServlet( name="servletCredit" ,  urlPatterns = {"/servletAppelation", "*.php"}  )    //urlPatterns est un tableau
public class ControleurServlet extends HttpServlet {
	private CreditMetier creditMetier;  //le metier est un attribut du servlet qui est le controleur
   
	@Override
	public void init() throws ServletException {
		creditMetier= new CreditMetier(); //dans cette methode , on instance le metier
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  // 1 ere etape : on recupere les donnees saisies
		long c=Long.parseLong(req.getParameter("montant"));
		int n = Integer.parseInt(req.getParameter("duree"));
		long t= Long.parseLong(req.getParameter("tauxEffectifGlobal"));
		  
		//2 eme etape : on doit instancier le model et stocker les donnees saisies dans le model 
		CreditModel creditModel=new CreditModel(); //on instance le constructeur par defaut PUIS on donne les valeurs saisie dans le formulaire  aux attributs de ce modele --> "credit" 
		
		creditModel.setMontant(c);
		creditModel.setDuree(n);
		creditModel.setTauxEffectifGlobal(t);
		 
		//3 eme etape: appeler le metier
		float   mensualiteM =  creditMetier.calculMensualite(c,n,t);
		 //4 eme etape : stoker le resultat dans le model  (le modele  contient comme attributs --> les donnees saisies dans la vue et le resultat a afficher )
		creditModel.setMensualite(mensualiteM);
		
		
		//connection et enregistrement dans la base suite au calul de credit 
		try{
			//1 : charger le pilote jdbc de mysql qui est "com.mysql.jdbc.Driver"
			Class.forName("com.mysql.jdbc.Driver"); 
			//2
			Connection  cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simulateurcreditweb","root","");   // "simulateurcreditweb"   est le nom de la base de donnee
		    //3
		   java.sql.PreparedStatement pr= cn.prepareStatement("INSERT INTO   mensualite VALUES (NULL,?,?,?,?)"); 
		   
		    pr.setLong(1,creditModel.getMontant());
		    pr.setLong(2,creditModel.getDuree());
		    pr.setLong(3,creditModel.getTauxEffectifGlobal());
		    pr.setDouble(4,mensualiteM);
		     
		    pr.execute(); 
		 
		}catch(Exception e) { 
			e.printStackTrace(); 
		}
		
		 //affichage sans  forward a la page JSP
		//  PrintWriter out=resp.getWriter(); 
		 // out.println("Mensualite :"+mensualiteM );
		  
		//1 ère methode avec sendRedirect  --> obligatoire le passage par la session 
		HttpSession session =req.getSession(true);
		session.setAttribute("leModel", creditModel);
		resp.sendRedirect("VueIndexSimulateur.jsp");
		
		
		
		//2 ème methode avec getRequestDispatcher  -->pas de session 
		  //affichage et forward a la page JSP
		
	  req.setAttribute("leModel", creditModel); // cad ajouter a l objet request un attribut qui s appelle leModel dont la valeur est credit pour pouvoir recuperer le resultat "mensualiteM" par la suite 
	  req.getRequestDispatcher("VueIndexSimulateur.jsp").forward(req, resp);  // le forward cad: envoyer request et response a la page JSP
		 
	}
	
	//la methode doGet est appeler la premiere --> donc  on instance le modele pour qu on aura pas d exception dans la vue qui est la page JSP
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  
		 req.setAttribute("leModel", new CreditModel() );    // instance du constructeur par defaut  du  " model "
		req.getRequestDispatcher("VueIndexSimulateur.jsp").forward(req, resp);
	}
}
