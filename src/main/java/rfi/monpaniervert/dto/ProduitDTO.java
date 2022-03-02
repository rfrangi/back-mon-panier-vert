package rfi.monpaniervert.dto;

import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ESSCategorie;
import rfi.monpaniervert.enums.ETypeTarif;

public class ProduitDTO {
	
	private Long id;
	private String name;
	private String reference;
	private ECategorie categorie;
    private Double tarif;
    private ETypeTarif typeTarif;
    private Long quantite;
    private String img;
    private Long idCompagnie;
    private String compagnieName;
    private String description;
    private Double poidsMin;
    private Double poidsMax;
    private int nbPieceLot;
	private ESSCategorie ssCategorie;
	
	public ProduitDTO() {}

	
    public ProduitDTO(Long id, String name, ECategorie categorie, Double tarif, Long quantite) {
		super();
		this.id = id;
		this.name = name;
		this.categorie = categorie;
		this.tarif = tarif;
		this.quantite = quantite;
	}

	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public Double getPoidsMin() {
		return poidsMin;
	}


	public void setPoidsMin(Double poidsMin) {
		this.poidsMin = poidsMin;
	}


	public Double getPoidsMax() {
		return poidsMax;
	}


	public void setPoidsMax(Double poidsMax) {
		this.poidsMax = poidsMax;
	}


	public int getNbPieceLot() {
		return nbPieceLot;
	}


	public void setNbPieceLot(int nbPieceLot) {
		this.nbPieceLot = nbPieceLot;
	}


	public ESSCategorie getSsCategorie() {
		return ssCategorie;
	}


	public void setSsCategorie(ESSCategorie ssCategorie) {
		this.ssCategorie = ssCategorie;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompagnieName() {
		return compagnieName;
	}

	public void setCompagnieName(String compagnieName) {
		this.compagnieName = compagnieName;
	}

	public Long getIdCompagnie() {
		return idCompagnie;
	}

	public void setIdCompagnie(Long idCompagnie) {
		this.idCompagnie = idCompagnie;
	}


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the categorie
	 */
	public ECategorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(ECategorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the tarif
	 */
	public Double getTarif() {
		return tarif;
	}

	/**
	 * @param tarif the tarif to set
	 */
	public void setTarif(Double tarif) {
		this.tarif = tarif;
	}

	/**
	 * @return the typeTarif
	 */
	public ETypeTarif getTypeTarif() {
		return typeTarif;
	}

	/**
	 * @param typeTarif the typeTarif to set
	 */
	public void setTypeTarif(ETypeTarif typeTarif) {
		this.typeTarif = typeTarif;
	}

	/**
	 * @return the quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
}
