package rfi.monpaniervert.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.EStatusCompagnie;
import rfi.monpaniervert.enums.ETypeCompagnie;


@Entity
@Table(	name = "compagnie", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "siret") 
		})
@EntityListeners(AuditingEntityListener.class)
public class Compagnie {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Enumerated(EnumType.STRING)
	private ETypeCompagnie type;
    
    @Enumerated(EnumType.STRING)
	private EStatusCompagnie status;
    
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable = false)
	private Long siret;
	
	private Long telephone;
	private String email;
	private String img;
	

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idAdresse")
	private Adresse adresse;

	@CreatedDate
	private Date creationDate;

	@LastModifiedDate
	private Date modificationDate;
	
	@OneToMany(mappedBy="compagnie")
	@JsonManagedReference
	private Set<Produit> produits = new HashSet<>();
	
	
    @ElementCollection(targetClass=ECategorie.class)
    @CollectionTable(name="compagnie_categorie")
	@Column(name = "categorie", nullable = false)
	private List<ECategorie> categories = new ArrayList<ECategorie>();
	
	public Compagnie() {}
	
	public Compagnie(Long id, String name, EStatusCompagnie status, Date creationDate, Date modificationDate) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}
	
	public List<ECategorie> getCategories() {
		return categories;
	}

	public void setCategories(List<ECategorie> categories) {
		this.categories = categories;
	}

	public Set<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ETypeCompagnie getType() {
		return type;
	}


	public void setType(ETypeCompagnie type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getSiret() {
		return siret;
	}


	public void setSiret(Long siret) {
		this.siret = siret;
	}


	public Long getTelephone() {
		return telephone;
	}


	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	/**
	 * @return the status
	 */
	public EStatusCompagnie getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(EStatusCompagnie status) {
		this.status = status;
	}


	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}


	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	/**
	 * @return the modificationDate
	 */
	public Date getModificationDate() {
		return modificationDate;
	}


	/**
	 * @param modificationDate the modificationDate to set
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}


	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}


	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
