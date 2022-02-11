package rfi.monpaniervert.dto;

import java.util.List;

import rfi.monpaniervert.enums.EStatusCompagnie;

public class TdbCompagnieDTO {

	private String id;
	private String searchTerm;
	private List<EStatusCompagnie> status;
	
	public List<EStatusCompagnie> getStatus() {
		return status;
	}
	
	public void setStatus(List<EStatusCompagnie> status) {
		this.status = status;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the searchTerm
	 */
	public String getSearchTerm() {
		return searchTerm;
	}
	/**
	 * @param searchTerm the searchTerm to set
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
