package metier;

public class CreditMetier {

	
	public CreditMetier() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public float calculMensualite(Long c, int n, Long t) {
		
		float t1=c*t/12000 ;
		float t2=1-(((1+t/12000))^(-1*n));
		
		float mensualiteM = t1/t2;
		return mensualiteM ;
	}
	
	 

}
