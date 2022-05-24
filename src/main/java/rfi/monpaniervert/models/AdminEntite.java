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

import rfi.monpaniervert.enums.ETypeEntite;

@Entity
@Table(name = "admin_compagnie")
@EntityListeners(AuditingEntityListener.class)
public class AdminEntite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Long entiteId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ETypeEntite type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEntiteId() {
		return entiteId;
	}

	public void setEntiteId(Long entiteId) {
		this.entiteId = entiteId;
	}

	public ETypeEntite getType() {
		return type;
	}

	public void setType(ETypeEntite type) {
		this.type = type;
	}
}
