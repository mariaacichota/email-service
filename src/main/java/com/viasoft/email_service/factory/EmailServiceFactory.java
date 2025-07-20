package com.viasoft.email_service.factory;

import com.viasoft.email_service.config.AppProperties;
import com.viasoft.email_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceFactory {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AppProperties properties;

    public EmailService getInstance() {
        String integracao = properties.getIntegracao().toUpperCase();

        if (!integracao.equals("AWS") && !integracao.equals("OCI")) {
            throw new IllegalArgumentException("Valor inválido para mail.integracao: " + integracao);
        }

        return (EmailService) context.getBean(integracao);
    }

    @Autowired
    public EmailServiceFactory(ApplicationContext context, AppProperties properties) {
        this.context = context;
        this.properties = properties;
    }
}