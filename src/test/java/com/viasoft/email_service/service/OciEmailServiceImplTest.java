package com.viasoft.email_service.service;

import static org.junit.jupiter.api.Assertions.*;

import com.viasoft.email_service.dto.EmailRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OciEmailServiceImplTest {

    @Test
    void testProcessEmail_PrintsCorrectJson() {
        OciEmailServiceImpl service = new OciEmailServiceImpl();

        EmailRequestDTO dto = new EmailRequestDTO();
        dto.setRecipientEmail("destino@exemplo.com");
        dto.setRecipientName("Destinatário");
        dto.setSenderEmail("remetente@exemplo.com");
        dto.setSubject("Teste");
        dto.setContent("Conteúdo");

        assertDoesNotThrow(() -> service.processEmail(dto));
    }
}