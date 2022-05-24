package rfi.monpaniervert.dto;

import java.util.List;

import rfi.monpaniervert.enums.EStatusCommande;

public class TdbCommandeDTO {

	private Long userId;
	private Long compagnieId;
	private Long siteId;
	private List<EStatusCommande> status;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCompagnieId() {
		return compagnieId;
	}
	public void setCompagnieId(Long compagnieId) {
		this.compagnieId = compagnieId;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public List<EStatusCommande> getStatus() {
		return status;
	}
	public void setStatus(List<EStatusCommande> status) {
		this.status = status;
	} 
}
