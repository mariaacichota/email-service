package com.viasoft.email_service.controller;

import com.viasoft.email_service.dto.EmailRequestDTO;
import com.viasoft.email_service.factory.EmailServiceFactory;
import com.viasoft.email_service.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailServiceFactory factory;

    public EmailController(EmailServiceFactory factory) {
        this.factory = factory;
    }

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailRequestDTO request) {
        EmailService service = factory.getInstance();
        service.processEmail(request);
        return ResponseEntity.noContent().build();
    }
}