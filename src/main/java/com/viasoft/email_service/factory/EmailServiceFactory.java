package com.viasoft.email_service.factory;

import com.viasoft.email_service.config.AppProperties;
import com.viasoft.email_service.enums.EmailProvider;
import com.viasoft.email_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceFactory {

    private final ApplicationContext context;
    private final AppProperties properties;

    @Autowired
    private MessageSource messageSource;

    public EmailServiceFactory(ApplicationContext context, AppProperties properties) {
        this.context = context;
        this.properties = properties;
    }

    public EmailService getInstance() {
        EmailProvider integracao = properties.getIntegracao();

        if (integracao == null) {
            String mensagemErro;

            if (messageSource != null) {
                mensagemErro = messageSource.getMessage(
                        "email.invalid.integration",
                        new Object[]{null},
                        LocaleContextHolder.getLocale()
                );
            } else {
                mensagemErro = "Valor inválido para mail.integracao: null";
            }

            throw new IllegalArgumentException(mensagemErro);
        }
        return (EmailService) context.getBean(integracao.name());
    }
}
