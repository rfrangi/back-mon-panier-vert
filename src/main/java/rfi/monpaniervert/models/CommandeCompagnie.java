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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import rfi.monpaniervert.enums.EStatusCommande;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class CommandeCompagnie {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatusCommande status;
    
	@Column(nullable = false)
	private Long compagnieId; 

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="cmd_client_id", nullable = true)
    @JsonBackReference
    private CommandeClient commandeClient;
    
	@OneToMany(mappedBy="commandeCompagnie")
	@JsonManagedReference
	private Set<ProduitCommande> produitsCommande = new HashSet<>();
	
	@CreatedDate
	private Date creationDate;

	@LastModifiedDate
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

	public CommandeClient getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}

	public Set<ProduitCommande> getProduitsCommande() {
		return produitsCommande;
	}

	public void setProduitsCommande(Set<ProduitCommande> produitsCommande) {
		this.produitsCommande = produitsCommande;
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
