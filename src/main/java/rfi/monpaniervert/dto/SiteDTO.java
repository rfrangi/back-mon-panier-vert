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
	private Long telephone;
	private String email;
	private String img;
	
	public SiteDTO(Long id, String name, EStatusSite status, Date creationDate, Date modificationDate) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}
	
	public SiteDTO(Long id, String name, EStatusSite status, Date creationDate, Date modificationDate, Long idAdresse, String adresse, String ville, String codePostal, String pays, String email, Long telephone) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.email = email;
		this.telephone = telephone;
		this.adresse = new AdresseDTO();
		this.adresse.setVille(ville);		
		this.adresse.setAdresse(adresse);
		this.adresse.setIdAdresse(idAdresse);
		this.adresse.setPays(pays);
		this.adresse.setCodePostal(codePostal);

	}
	
	public SiteDTO() {}
	
	
	
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

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
