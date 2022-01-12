package rfi.monpaniervert.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.TdbMailDTO;
import rfi.monpaniervert.managers.MailManager;
import rfi.monpaniervert.models.Email;
import rfi.monpaniervert.services.MailService;

@Service
public class MailServiceImpl implements MailService {
    
	@Autowired private MailManager mailManager;

    public void sendSimpleMessage(String to, String subject, String text) {
        this.mailManager.sendSimpleMessage(to, subject, text);
    }

	@Override
	public Page<Email> find(TdbMailDTO tdbMailDTO, Pageable pagination) {
		return this.mailManager.find(tdbMailDTO, pagination);
	}

	@Override
	public void delete(Long id) {
		this.mailManager.delete(id);
	}
}
