package web;

public class CreditModel {
	
	//les attributs sont les données saisies et les resultats à afficher
	private Long id;
	private Long montant;
	private int duree;
	private Long tauxEffectifGlobal;
    private float mensualite;
	
	
	public float getMensualite() {
		return mensualite;
	}



	public void setMensualite(float mensualite) {
		this.mensualite = mensualite;
	}



	public CreditModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getMontant() {
		return montant;
	}



	public void setMontant(Long montant) {
		this.montant = montant;
	}



	public int getDuree() {
		return duree;
	}



	public void setDuree(int duree) {
		this.duree = duree;
	}



	public Long getTauxEffectifGlobal() {
		return tauxEffectifGlobal;
	}



	public void setTauxEffectifGlobal(Long tauxEffectifGlobal) {
		this.tauxEffectifGlobal = tauxEffectifGlobal;
	}



	public CreditModel(Long montant, int duree, Long tauxEffectifGlobal,float mensualite) {
		super();
		this.montant = montant;
		this.duree = duree;
		this.tauxEffectifGlobal = tauxEffectifGlobal;
		this.mensualite=mensualite;
	}



	public CreditModel(Long id, Long montant, int duree, Long tauxEffectifGlobal,float mensualite) {
		super();
		this.id = id;
		this.montant = montant;
		this.duree = duree;
		this.tauxEffectifGlobal = tauxEffectifGlobal;
		this.mensualite=mensualite;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
 

}
