package com.viasoft.email_service.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.viasoft.email_service.dto.EmailRequestDTO;
import com.viasoft.email_service.factory.EmailServiceFactory;
import com.viasoft.email_service.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailControllerTest {

    @InjectMocks
    private EmailController controller;

    @Mock
    private EmailServiceFactory factory;

    @Mock
    private EmailService emailService;

    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new EmailController(factory, messageSource);
        ReflectionTestUtils.setField(controller, "messageSource", messageSource);
    }

    @Test
    void testSendEmail_Returns204() {
        EmailRequestDTO dto = new EmailRequestDTO();
        dto.setRecipientEmail("teste@exemplo.com");
        dto.setRecipientName("Teste");
        dto.setSenderEmail("remetente@exemplo.com");
        dto.setSubject("Assunto");
        dto.setContent("Conteúdo");

        when(factory.getInstance()).thenReturn(emailService);
        doNothing().when(emailService).processEmail(dto);

        ResponseEntity<Void> response = controller.sendEmail(dto);

        verify(factory, times(1)).getInstance();
        verify(emailService, times(1)).processEmail(dto);
        assertEquals(204, response.getStatusCodeValue());
    }
}