package rfi.monpaniervert.models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import rfi.monpaniervert.enums.EStatusEmail;
import rfi.monpaniervert.enums.ETypeEmail;

@Entity
@Table(name = "emails")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@Column(nullable = true)
	private Blob message;

	@Column(nullable = true)
	private String dest;
	
	@Column(nullable = true)
	private String objet;

	@Column(nullable = true)
	private String errorMessage;
	
	@Enumerated(EnumType.STRING)
	private EStatusEmail status;
	
	
	@Enumerated(EnumType.STRING)
	private ETypeEmail type;
	
	@CreatedDate
	private Date creationDate;

	@LastModifiedDate
	private Date modificationDate;
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
	 * @return the message
	 */
	public Blob getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Blob message) {
		this.message = message;
	}

	/**
	 * @return the dest
	 */
	public String getDest() {
		return dest;
	}

	/**
	 * @param dest the dest to set
	 */
	public void setDest(String dest) {
		this.dest = dest;
	}

	/**
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * @param objet the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}

	/**
	 * @return the status
	 */
	public EStatusEmail getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EStatusEmail status) {
		this.status = status;
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

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the type
	 */
	public ETypeEmail getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ETypeEmail type) {
		this.type = type;
	}
	
	
}
