package com.viasoft.email_service.factory;

import com.viasoft.email_service.config.AppProperties;
import com.viasoft.email_service.enums.EmailProvider;
import com.viasoft.email_service.service.EmailService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceFactory {

    private final ApplicationContext context;
    private final AppProperties properties;

    public EmailServiceFactory(ApplicationContext context, AppProperties properties) {
        this.context = context;
        this.properties = properties;
    }

    public EmailService getInstance() {
        EmailProvider integracao = properties.getIntegracao();

        if (integracao == null) {
            throw new IllegalArgumentException("Valor inválido para mail.integracao");
        }

        return (EmailService) context.getBean(integracao.name());
    }
}
