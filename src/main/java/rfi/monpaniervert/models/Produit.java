package rfi.monpaniervert.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.ETypeTarif;

@Entity
@Table(	name = "produit")
@EntityListeners(AuditingEntityListener.class)
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
    @Enumerated(EnumType.STRING)
	private ECategorie categorie;
    
	@Column(nullable = false)
    private Double tarif;
    
    @Enumerated(EnumType.STRING)
    private ETypeTarif typeTarif;
    
	@Column(nullable = false)
    private Long quantite;

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
