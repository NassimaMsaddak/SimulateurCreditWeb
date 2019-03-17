<%@page import="web.CreditModel"%>
 
<%
CreditModel creditModel=new CreditModel();
if (request.getAttribute("leModel")!=null){

	// recuperer le modele "credit" de l objet request
	  creditModel=(CreditModel)request.getAttribute("leModel");  //recuperer le model (a partir du controleur servlet )et faire un mini casting	
	
} 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculer mensualite</title>
<link rel="stylesheet"  type="text/css" href="<%= request.getContextPath() %>/css/style.css"> <!-- request.getContextPath() = /SimulateurCreditWeb    -->
</head> 
<body>  

<div>
		<form action="servletAppelation" method="post">
		montant m : <input type ="text" name="montant" value="<%=creditModel.getMontant()%>" /> <br> <br>
		durée de l emprunt en mois: <input type ="text" name="duree"  value="<%=creditModel.getDuree()%>" /><br> <br>
		taux effectif globam annuel t: <input type ="text" name="tauxEffectifGlobal"  value="<%= creditModel.getTauxEffectifGlobal()%>"  /> <br> <br>
		
		<input type="submit" name="calculer" value="calculer"/><br> <br>
		</form> 
</div>	
		<h2> Mensualite :</h2> <%=  creditModel.getMensualite() %>
</body>
</html>