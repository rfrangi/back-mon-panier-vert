package rfi.monpaniervert.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import rfi.monpaniervert.enums.EStatusSite;

public class SiteDTO {
	
	private Long id;
	private String name;
	private AdresseDTO adresse;
	private EStatusSite status;
	private Set<CompagnieDTO> compagnies = new HashSet<>();
	private Date creationDate;
	private Date modificationDate;
	
	public SiteDTO() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AdresseDTO getAdresse() {
		return adresse;
	}
	public void setAdresse(AdresseDTO adresse) {
		this.adresse = adresse;
	}
	public EStatusSite getStatus() {
		return status;
	}
	public void setStatus(EStatusSite status) {
		this.status = status;
	}
	public Set<CompagnieDTO> getCompagnies() {
		return compagnies;
	}
	public void setCompagnies(Set<CompagnieDTO> compagnies) {
		this.compagnies = compagnies;
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
