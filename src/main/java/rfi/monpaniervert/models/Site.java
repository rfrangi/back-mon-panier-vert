package rfi.monpaniervert.models;

import java.util.HashSet;
import java.util.Objects;
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
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import rfi.monpaniervert.enums.EStatusSite;
@Entity
@Table(name = "site")
@EntityListeners(AuditingEntityListener.class)
public class Site {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idAdresse")
	private Adresse adresse;
	
    @Enumerated(EnumType.STRING)
	private EStatusSite status;
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "site_compagnie", 
				joinColumns = @JoinColumn(name = "site_id"), 
				inverseJoinColumns = @JoinColumn(name = "compagnie_id"))
	private Set<Compagnie> compagnies = new HashSet<>();


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
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}


	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	/**
	 * @return the compagnies
	 */
	public Set<Compagnie> getCompagnies() {
		return compagnies;
	}


	/**
	 * @param compagnies the compagnies to set
	 */
	public void setCompagnies(Set<Compagnie> compagnies) {
		this.compagnies = compagnies;
	}

	/**
	 * @return the status
	 */
	public EStatusSite getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(EStatusSite status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		return Objects.hash(adresse, compagnies, id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Site other = (Site) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(compagnies, other.compagnies)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
}