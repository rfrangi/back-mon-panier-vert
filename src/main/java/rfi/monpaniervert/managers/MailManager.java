package rfi.monpaniervert.managers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.models.Email;

public interface MailManager {
	
	 void sendSimpleMessage(String to, String subject, String text);

	Page<Email> find(TdbMailDTO tdbMailDTO, Pageable pagination);

	void delete(Long id);

}
