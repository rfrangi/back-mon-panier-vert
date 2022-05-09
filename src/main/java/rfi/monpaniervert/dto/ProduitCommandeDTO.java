package rfi.monpaniervert.dto;

import java.util.Date;

import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ESSCategorie;

public class ProduitCommandeDTO {

	private Long id;
	private String name;
	private ECategorie categorie;
    private Double tarif;
    private Long quantiteCommande;
    private Long idCompagnie;
    private Double poidsMin;
    private Double poidsMax;
    private int nbPieceLot;
	private ESSCategorie ssCategorie;
    private String reference;
    private Boolean promo;
	private Boolean bio;
	private Date creationDate;
	private Date modificationDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ECategorie getCategorie() {
		return categorie;
	}
	public void setCategorie(ECategorie categorie) {
		this.categorie = categorie;
	}
	public Double getTarif() {
		return tarif;
	}
	public void setTarif(Double tarif) {
		this.tarif = tarif;
	}
	public Long getQuantiteCommande() {
		return quantiteCommande;
	}
	public void setQuantiteCommande(Long quantiteCommande) {
		this.quantiteCommande = quantiteCommande;
	}
	public Long getIdCompagnie() {
		return idCompagnie;
	}
	public void setIdCompagnie(Long idCompagnie) {
		this.idCompagnie = idCompagnie;
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
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Boolean getPromo() {
		return promo;
	}
	public void setPromo(Boolean promo) {
		this.promo = promo;
	}
	public Boolean getBio() {
		return bio;
	}
	public void setBio(Boolean bio) {
		this.bio = bio;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
}
