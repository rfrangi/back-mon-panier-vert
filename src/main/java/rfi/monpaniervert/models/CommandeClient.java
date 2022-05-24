package rfi.monpaniervert.models;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import rfi.monpaniervert.enums.EModeRetrait;
import rfi.monpaniervert.enums.EStatusCommande;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class CommandeClient {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EStatusCommande status;
    
	private String ref;
    
	@Column(nullable = false)
	private Long userId; 
	
	@Column(nullable = false)
	private Long siteId; 
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idAdresseLivraison")
	private Adresse adresseLivraison;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idAdresseFacturation")
	private Adresse adresseFacturation;
	
	@CreatedDate
	private Date creationDate;

	@LastModifiedDate
	private Date modificationDate;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EModeRetrait modeRetrait;
	
	@Column(nullable = false)
	private double montant;
	
	@Column(nullable = false)
	private Date dateRetrait;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "cmd_client_compagnie", 
				joinColumns = @JoinColumn(name = "cmd_client_id"), 
				inverseJoinColumns = @JoinColumn(name = "cmd_compagnie_id"))
	private Set<CommandeCompagnie> commandesCompagnie = new HashSet<>();

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Set<CommandeCompagnie> getCommandesCompagnie() {
		return commandesCompagnie;
	}

	public void setCommandesCompagnie(Set<CommandeCompagnie> commandesCompagnie) {
		this.commandesCompagnie = commandesCompagnie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EStatusCommande getStatus() {
		return status;
	}

	public void setStatus(EStatusCommande status) {
		this.status = status;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Adresse getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(Adresse adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public Adresse getAdresseFacturation() {
		return adresseFacturation;
	}

	public void setAdresseFacturation(Adresse adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
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

	public EModeRetrait getModeRetrait() {
		return modeRetrait;
	}

	public void setModeRetrait(EModeRetrait modeRetrait) {
		this.modeRetrait = modeRetrait;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDateRetrait() {
		return dateRetrait;
	}

	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}
}
