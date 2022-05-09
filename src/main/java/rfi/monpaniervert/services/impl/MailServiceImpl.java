package rfi.monpaniervert.services.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.enums.ETypeEmail;
import rfi.monpaniervert.managers.MailManager;
import rfi.monpaniervert.managers.UserManager;
import rfi.monpaniervert.models.Email;
import rfi.monpaniervert.models.User;
import rfi.monpaniervert.services.MailService;

@Service
public class MailServiceImpl implements MailService {
    
	@Autowired private MailManager mailManager;
	@Autowired private UserManager userManager;
	
	@Value("${front.url}")
	private String urlFront;
	@Value("${front.url.resetpassword}")
	private String urlResetPassword;
	

    public void sendSimpleMessage(String to, String subject, String text) {
        this.mailManager.sendSimpleMessage(to, "Test send simple email", text, ETypeEmail.TEST);
    }
    
    public void sendHtmlEmail(String to, String subject, String text) {
        this.mailManager.sendHtmlEmail(to, subject, text, ETypeEmail.TEST);
    }

	@Override
	public Page<Email> find(TdbMailDTO tdbMailDTO, Pageable pagination) {
		return this.mailManager.find(tdbMailDTO, pagination);
	}

	@Override
	public void delete(Long id) {
		this.mailManager.delete(id);
	}

	@Override
	public void resetPassword(@Valid String email) {
		final User user = this.userManager.generatedTokenResetPassword(email);
		if(user != null) {
			final String token = user.getTokenResetPassword();
			final String url = this.urlFront + this.urlResetPassword + token;
			this.mailManager.sendMailResetPassword(url, user);
		}
	}
}
