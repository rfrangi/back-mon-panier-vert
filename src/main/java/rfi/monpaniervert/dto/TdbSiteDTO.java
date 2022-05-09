package rfi.monpaniervert.dto;

import java.util.List;

import rfi.monpaniervert.enums.EStatusSite;

public class TdbSiteDTO {

	private String id;
	private String searchTerm;
	private String searchByAdresse;
	private List<EStatusSite> status;
	
	public String getSearchByAdresse() {
		return searchByAdresse;
	}
	
	public void setSearchByAdresse(String searchByAdresse) {
		this.searchByAdresse = searchByAdresse;
	}
	
	public List<EStatusSite> getStatus() {
		return status;
	}
	public void setStatus(List<EStatusSite> status) {
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
