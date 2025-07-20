package com.viasoft.email_service.controller;

import com.viasoft.email_service.dto.EmailRequestDTO;
import com.viasoft.email_service.factory.EmailServiceFactory;
import com.viasoft.email_service.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);
    private final EmailServiceFactory factory;
    private final MessageSource messageSource;

    public EmailController(EmailServiceFactory factory, MessageSource messageSource) {
        this.factory = factory;
        this.messageSource = messageSource;
    }

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailRequestDTO request) {
        log.info("Iniciando processamento de envio de e-mail. Dados recebidos: [Remetente: {}, Destinatário: {}, Assunto: {}]",
                request.getSenderEmail(), request.getRecipientEmail(), request.getSubject());

        EmailService service = factory.getInstance();
        service.processEmail(request);

        log.info(messageSource.getMessage("email.success", null, LocaleContextHolder.getLocale()));

        return ResponseEntity.noContent().build();
    }
}