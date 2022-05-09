package rfi.monpaniervert.dto;

import java.util.Map;

import org.springframework.data.domain.Page;

import rfi.monpaniervert.enums.ESSCategorie;

public class ProduitByCatDTO {
	
	private Map<ESSCategorie, Long> mapNbSSCategorie;
	private Page<ProduitDTO> result;
	
	public Map<ESSCategorie, Long> getMapNbSSCategorie() {
		return mapNbSSCategorie;
	}
	public void setMapNbSSCategorie(Map<ESSCategorie, Long> mapNbSSCategorie) {
		this.mapNbSSCategorie = mapNbSSCategorie;
	}
	public Page<ProduitDTO> getResult() {
		return result;
	}
	public void setResult(Page<ProduitDTO> result) {
		this.result = result;
	}
	
	
    
}
