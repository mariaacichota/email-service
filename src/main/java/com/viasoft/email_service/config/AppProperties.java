package com.viasoft.email_service.config;

import com.viasoft.email_service.enums.EmailProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mail")
public class AppProperties {

    private EmailProvider integracao;

    public EmailProvider getIntegracao() {
        return integracao;
    }

    public void setIntegracao(EmailProvider integracao) {
        this.integracao = integracao;
    }
}