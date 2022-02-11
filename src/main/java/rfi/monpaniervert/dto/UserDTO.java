package rfi.monpaniervert.dto;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import rfi.monpaniervert.enums.ECivilite;
import rfi.monpaniervert.enums.EStatusUser;

public class UserDTO {

	private Long id;
	private String email;
	private String password;
	private String lastname;
	private String firstname;
	private ECivilite civilite;
	private EStatusUser status;
	private Date creationDate;
	private Date modificationDate;
	private Boolean recevoirOffre;
	private Set<String> roles;
	private AdresseDTO Adresse;
	
	
	public UserDTO(Long id, String email,
			 String lastname, String firstname,
			 ECivilite civilite, Date creationDate, EStatusUser status) {
		this.id = id;
		this.email = email;
		this.lastname = lastname;
		this.firstname = firstname;
		this.civilite = civilite;
		this.creationDate = creationDate;
		this.status = status;
	}
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the status
	 */
	public EStatusUser getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EStatusUser status) {
		this.status = status;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the civilite
	 */
	public ECivilite getCivilite() {
		return civilite;
	}
	/**
	 * @param civilite the civilite to set
	 */
	public void setCivilite(ECivilite civilite) {
		this.civilite = civilite;
	}
	
	/**
	 * @return the adresse
	 */
	public AdresseDTO getAdresse() {
		return Adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(AdresseDTO adresse) {
		Adresse = adresse;
	}

	
	/**
	 * @return the roles
	 */
	public Set<String> getRoles() {
		return roles;
	}
	
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
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
	
	public Boolean getRecevoirOffre() {
		return recevoirOffre;
	}

	public void setRecevoirOffre(Boolean recevoirOffre) {
		this.recevoirOffre = recevoirOffre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Adresse, civilite, creationDate, email, firstname, id, lastname, modificationDate, password,
				recevoirOffre, roles, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(Adresse, other.Adresse) && civilite == other.civilite
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(id, other.id)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(modificationDate, other.modificationDate)
				&& Objects.equals(password, other.password) && Objects.equals(recevoirOffre, other.recevoirOffre)
				&& Objects.equals(roles, other.roles) && status == other.status;
	}
}
