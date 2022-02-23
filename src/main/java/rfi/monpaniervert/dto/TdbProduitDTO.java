package rfi.monpaniervert.dto;

import java.util.List;

import rfi.monpaniervert.enums.ECategorie;

public class TdbProduitDTO {

	private Long id;
	private String searchTerm;
	private Long idCompagnie;
	private List<ECategorie> categories;
	private long minTarif;
	private long maxTarif;
	
	public TdbProduitDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Long getIdCompagnie() {
		return idCompagnie;
	}

	public void setIdCompagnie(Long idCompagnie) {
		this.idCompagnie = idCompagnie;
	}

	public List<ECategorie> getCategories() {
		return categories;
	}

	public void setCategories(List<ECategorie> categories) {
		this.categories = categories;
	}

	public long getMinTarif() {
		return minTarif;
	}

	public void setMinTarif(long minTarif) {
		this.minTarif = minTarif;
	}

	public long getMaxTarif() {
		return maxTarif;
	}

	public void setMaxTarif(long maxTarif) {
		this.maxTarif = maxTarif;
	}	
}
