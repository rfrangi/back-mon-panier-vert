package rfi.monpaniervert.managers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.enums.ETypeEmail;
import rfi.monpaniervert.models.Email;
import rfi.monpaniervert.models.User;

public interface MailManager {
	
	 void sendSimpleMessage(String to, String subject, String text, ETypeEmail type);

	Page<Email> find(TdbMailDTO tdbMailDTO, Pageable pagination);

	void delete(Long id);

	void sendMailResetPassword(String url, User user);

	void sendHtmlEmail(String to, String subject, String text, ETypeEmail type);

}
