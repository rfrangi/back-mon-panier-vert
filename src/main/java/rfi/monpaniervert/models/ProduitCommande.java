package rfi.monpaniervert.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ESSCategorie;

@Entity
@Table(	name = "produitCommande")
@EntityListeners(AuditingEntityListener.class)
public class ProduitCommande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long idProduit;
	
	@Column(nullable = false)
	private String name;
	
    @Enumerated(EnumType.STRING)
	private ECategorie categorie;
    
	@Column(nullable = false)
    private Double tarif;
    
	@Column(nullable = true)
    private Long quantiteCommande;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="commande_compagnie_id", nullable = false)
    @JsonBackReference
    private CommandeCompagnie commandeCompagnie;
    
	@Column(nullable = true)
    private Double poidsMin;
	
	@Column(nullable = true)
    private Double poidsMax;
	
	@Column(nullable = true)
    private int nbPieceLot;
    
    @Enumerated(EnumType.STRING)
	private ESSCategorie ssCategorie;

	@Column(nullable = true)
    private String reference;
   
	@Column(nullable = true)
    private Boolean promo;
	
	@Column(nullable = true)
	private Boolean bio;
	
	@CreatedDate
	private Date creationDate;

	@LastModifiedDate
	private Date modificationDate;
	
	private String img;
	private String compagnieName;
	
	public String getCompagnieName() {
		return compagnieName;
	}


	public void setCompagnieName(String compagnieName) {
		this.compagnieName = compagnieName;
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


	public Long getQuantiteCommande() {
		return quantiteCommande;
	}


	public void setQuantiteCommande(Long quantiteCommande) {
		this.quantiteCommande = quantiteCommande;
	}


	public Long getIdProduit() {
		return idProduit;
	}


	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}


	public CommandeCompagnie getCommandeCompagnie() {
		return commandeCompagnie;
	}


	public void setCommandeCompagnie(CommandeCompagnie commandeCompagnie) {
		this.commandeCompagnie = commandeCompagnie;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}
}
