package rfi.monpaniervert.managers.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;/*
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;*/
import org.springframework.stereotype.Component;

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.enums.EStatusEmail;
import rfi.monpaniervert.managers.MailManager;
import rfi.monpaniervert.models.Email;
import rfi.monpaniervert.repository.EmailRepository;

@Component
public class MailManagerImpl  implements MailManager {

//	@Autowired private JavaMailSender emailSender;
	@Autowired private EmailRepository emailRepository;

    public void sendSimpleMessage(String to, String subject, String text) {
      //  SimpleMailMessage message = new SimpleMailMessage(); 
      /*  message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        
        try {
        this.emailSender.send(message);
		this.save(to, to, subject, text, EStatusEmail.ENVOYER, null);
        }catch(MailException e) {
        	this.save(to, to, subject, text, EStatusEmail.ERREUR, e.getMessage());
        }*/
   }

	@Override
	public Page<Email> find(TdbMailDTO tdbMailDTO, Pageable pagination) {
		final String searchTerm = tdbMailDTO.getSearchTerm() == "" ? null : tdbMailDTO.getSearchTerm().equals(null) ? null : tdbMailDTO.getSearchTerm().trim();
		return this.emailRepository.find(searchTerm, pagination);
	}
	
	private Email save(String to, String from, String subject, String text, EStatusEmail status, String error) {
		final Email email = new Email();
		email.setCreationDate(new Date());
		email.setModificationDate(new Date());
		email.setStatus(status);
		email.setObjet(subject);
		email.setDest(to);
		email.setErrorMessage(error);
		return this.emailRepository.save(email);
	}

	@Override
	public void delete(Long id) {
		this.emailRepository.deleteById(id);
	}
}
