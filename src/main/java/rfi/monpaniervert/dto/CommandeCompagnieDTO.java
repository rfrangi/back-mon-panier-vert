package rfi.monpaniervert.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import rfi.monpaniervert.enums.EStatusCommande;

public class CommandeCompagnieDTO {
	 
	private Long id;
    private EStatusCommande status;
	private Long compagnieId; 
    private Long commandeClientId;
	private Set<ProduitCommandeDTO> produitsCommande = new HashSet<>();
	private Date creationDate;
	private Date modificationDate;

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

	public Long getCompagnieId() {
		return compagnieId;
	}

	public void setCompagnieId(Long compagnieId) {
		this.compagnieId = compagnieId;
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

	public Long getCommandeClientId() {
		return commandeClientId;
	}

	public void setCommandeClientId(Long commandeClientId) {
		this.commandeClientId = commandeClientId;
	}

	public Set<ProduitCommandeDTO> getProduitsCommande() {
		return produitsCommande;
	}

	public void setProduitsCommande(Set<ProduitCommandeDTO> produitsCommande) {
		this.produitsCommande = produitsCommande;
	}
}
