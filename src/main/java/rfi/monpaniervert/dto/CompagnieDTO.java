package rfi.monpaniervert.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rfi.monpaniervert.enums.ECategorie;
import rfi.monpaniervert.enums.EStatusCompagnie;
import rfi.monpaniervert.enums.ETypeCompagnie;

public class CompagnieDTO {
	
	private Long id;
    private ETypeCompagnie type;
    private EStatusCompagnie status;
	private String name;
	private Long siret;
	private Long telephone;
	private String email;
	private AdresseDTO adresse;
	private Date creationDate;
	private Date modificationDate;
	private String img;
	private List<ECategorie> categories = new ArrayList<ECategorie>();

	
	public CompagnieDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CompagnieDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}	
	
	public CompagnieDTO(Long id, String name, EStatusCompagnie status, Date creationDate, Date modificationDate, Long idAdresse, String ville, String codePostal, String pays) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.adresse = new AdresseDTO();
		this.adresse.setVille(ville);		
		this.adresse.setIdAdresse(idAdresse);
		this.adresse.setPays(pays);
		this.adresse.setCodePostal(codePostal);

	}
	
	public CompagnieDTO(Long id, String name, EStatusCompagnie status, Date creationDate, Date modificationDate, AdresseDTO adresse) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.adresse = adresse;
	}
	
	public CompagnieDTO(Long id, EStatusCompagnie status, String name, Long siret, Long telephone, String email,
			Date creationDate, String img) {
		super();
		this.id = id;
		this.status = status;
		this.name = name;
		this.siret = siret;
		this.telephone = telephone;
		this.email = email;
		this.creationDate = creationDate;
		this.img = img;
	}
	
	public List<ECategorie> getCategories() {
		return categories;
	}

	public void setCategories(List<ECategorie> categories) {
		this.categories = categories;
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
	 * @return the type
	 */
	public ETypeCompagnie getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ETypeCompagnie type) {
		this.type = type;
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
	 * @return the siret
	 */
	public Long getSiret() {
		return siret;
	}
	/**
	 * @param siret the siret to set
	 */
	public void setSiret(Long siret) {
		this.siret = siret;
	}
	/**
	 * @return the telephone
	 */
	public Long getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the adresse
	 */
	public AdresseDTO getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(AdresseDTO adresse) {
		this.adresse = adresse;
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

	
	
}
