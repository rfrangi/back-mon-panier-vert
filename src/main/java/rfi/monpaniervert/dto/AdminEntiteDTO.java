package rfi.monpaniervert.dto;

import rfi.monpaniervert.enums.ETypeEntite;

public class AdminEntiteDTO {
	
	private UserDTO user;
	private CompagnieDTO compagnie;
	private SiteDTO site;
	private ETypeEntite type;

	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public CompagnieDTO getCompagnie() {
		return compagnie;
	}
	public void setCompagnie(CompagnieDTO compagnie) {
		this.compagnie = compagnie;
	}
	public SiteDTO getSite() {
		return site;
	}
	public void setSite(SiteDTO site) {
		this.site = site;
	}
	public ETypeEntite getType() {
		return type;
	}
	public void setType(ETypeEntite type) {
		this.type = type;
	}
}
