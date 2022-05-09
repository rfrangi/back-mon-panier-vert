package rfi.monpaniervert.models;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import rfi.monpaniervert.enums.ECivilite;
import rfi.monpaniervert.enums.EStatusUser;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = true)
	private String password;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String firstname;

	@Enumerated(EnumType.STRING)
	private ECivilite civilite;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EStatusUser status = EStatusUser.ACTIF;
 
	@Column(nullable = false)
	private Boolean recevoirOffre;
	
	@Column(nullable = false)
	private Integer nbTentative;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idAdresse")
	private Adresse adresse;

	@CreatedDate
	private Date creationDate;

	@LastModifiedDate
	private Date modificationDate;
	
	private Date changeStatusDate;
	
	private Date lastConnexionDate;

	private String tokenResetPassword;

	public User() {}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getTokenResetPassword() {
		return tokenResetPassword;
	}

	public void setTokenResetPassword(String tokenResetPassword) {
		this.tokenResetPassword = tokenResetPassword;
	}

	public Date getChangeStatusDate() {
		return changeStatusDate;
	}


	public void setChangeStatusDate(Date changeStatusDate) {
		this.changeStatusDate = changeStatusDate;
	}


	public Integer getNbTentative() {
		return nbTentative;
	}


	public void setNbTentative(Integer nbTentative) {
		this.nbTentative = nbTentative;
	}
	

	public Date getLastConnexionDate() {
		return lastConnexionDate;
	}


	public void setLastConnexionDate(Date lastConnexionDate) {
		this.lastConnexionDate = lastConnexionDate;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public ECivilite getCivilite() {
		return civilite;
	}

	public void setCivilite(ECivilite civilite) {
		this.civilite = civilite;
	}

	public Boolean getRecevoirOffre() {
		return recevoirOffre;
	}

	public void setRecevoirOffre(Boolean recevoirOffre) {
		this.recevoirOffre = recevoirOffre;
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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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

}
