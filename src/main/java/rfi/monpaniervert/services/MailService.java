package rfi.monpaniervert.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.models.Email;

public interface MailService {

	void sendSimpleMessage(String to, String subject, String text);
	
	void sendHtmlEmail(String to, String subject, String text);
	
	Page<Email> find(TdbMailDTO tdbMailDTO, Pageable pagination);
	
	void delete(Long id);
	
	void resetPassword(@Valid String email);
}
