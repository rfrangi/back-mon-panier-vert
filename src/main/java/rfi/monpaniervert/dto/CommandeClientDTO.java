package rfi.monpaniervert.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import rfi.monpaniervert.enums.EModeRetrait;
import rfi.monpaniervert.enums.EStatusCommande;


public class CommandeClientDTO {
	 

	private Long id;
	private EStatusCommande status;
	private String ref;
	private Long userId; 
	private Long siteId; 
	private AdresseDTO adresseLivraison;
	private AdresseDTO adresseFacturation;
	private Date creationDate;
	private Date modificationDate;
	private EModeRetrait modeRetrait;
	private double montant;
	private Date dateRetrait;
	private Set<CommandeCompagnieDTO> commandesCompagnie = new HashSet<>();

	
	public CommandeClientDTO() {}
	

	public CommandeClientDTO(Long id, Date creationDate) {
		this.id = id;
		this.creationDate = creationDate;
	}
	
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Set<CommandeCompagnieDTO> getCommandesCompagnie() {
		return commandesCompagnie;
	}

	public void setCommandesCompagnie(Set<CommandeCompagnieDTO> commandesCompagnie) {
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

	public AdresseDTO getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(AdresseDTO adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public AdresseDTO getAdresseFacturation() {
		return adresseFacturation;
	}

	public void setAdresseFacturation(AdresseDTO adresseFacturation) {
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
