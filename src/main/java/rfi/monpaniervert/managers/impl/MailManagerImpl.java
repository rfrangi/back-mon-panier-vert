package rfi.monpaniervert.managers.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;/*
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;*/
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.enums.ECivilite;
import rfi.monpaniervert.enums.EStatusEmail;
import rfi.monpaniervert.enums.ETypeEmail;
import rfi.monpaniervert.managers.MailManager;
import rfi.monpaniervert.models.Email;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.repository.EmailRepository;

@Component
public class MailManagerImpl implements MailManager {

	@Autowired private JavaMailSender emailSender;
	@Autowired private EmailRepository emailRepository;
	@Autowired private TemplateEngine templateEngine;

    public void sendSimpleMessage(String to, String subject, String text, ETypeEmail type) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        
        try {
        this.emailSender.send(message);
		this.save(to, to, subject, text, EStatusEmail.ENVOYER, null, type);
        }catch(MailException e) {
        	this.save(to, to, subject, text, EStatusEmail.ERREUR, e.getMessage().substring(250), type);
        }
   }
    
	@Override
	public void sendHtmlEmail(String to, String subject, String text, ETypeEmail type) {
		final MimeMessage message = emailSender.createMimeMessage();
        final MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "utf-8");
	        message.setContent(text, "text/html");
	        helper.setTo(to);
	        helper.setSubject(subject);
	        this.emailSender.send(message);
			this.save(to, to, subject, "TODO", EStatusEmail.ENVOYER, null, type);
		} catch (MessagingException e) {
        	this.save(to, to, subject, text, EStatusEmail.ERREUR, e.getMessage().substring(250), type);
		}
	}

	@Override
	public Page<Email> find(TdbMailDTO tdbMailDTO, Pageable pagination) {
		final String searchTerm = tdbMailDTO.getSearchTerm() == "" ? null : tdbMailDTO.getSearchTerm().equals(null) ? null : tdbMailDTO.getSearchTerm().trim();
		return this.emailRepository.find(searchTerm, pagination);
	}
	
	private Email save(String to, String from, String subject, String text, EStatusEmail status, String error, ETypeEmail type) {
		final Email email = new Email();
		email.setCreationDate(new Date());
		email.setModificationDate(new Date());
		email.setStatus(status);
		email.setObjet(subject);
		email.setDest(to);
		email.setType(type);
		email.setErrorMessage(error);
		return this.emailRepository.save(email);
	}

	@Override
	public void delete(Long id) {
		this.emailRepository.deleteById(id);
	}


	@Override
	public void sendMailResetPassword(String url, User user) {
		final Map<String, Object> variables = new HashMap<>();
	    variables.put("civilite", user.getCivilite().equals(ECivilite.MONSIEUR) ? "Mr" : "Mme");
	    variables.put("name", user.getLastname());
	    variables.put("url", url);
	    
	    final String output = this.templateEngine.process("reset-password", new Context(Locale.getDefault(), variables));
        this.sendHtmlEmail(user.getEmail(), "[MPV] Changement de mot de passe", output, ETypeEmail.RESET_PASSWORD);
	}

}
