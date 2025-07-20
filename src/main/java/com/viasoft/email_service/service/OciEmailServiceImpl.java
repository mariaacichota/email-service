package com.viasoft.email_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.email_service.dto.EmailOciDTO;
import com.viasoft.email_service.dto.EmailRequestDTO;
import com.viasoft.email_service.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service("OCI")
public class OciEmailServiceImpl implements EmailService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void processEmail(EmailRequestDTO request) {
        EmailOciDTO dto = new EmailOciDTO();
        dto.setRecipientEmail(request.getRecipientEmail());
        dto.setRecipientName(request.getRecipientName());
        dto.setSenderEmail(request.getSenderEmail());
        dto.setSubject(request.getSubject());
        dto.setBody(request.getContent());

        JsonUtil.printJson("OCI Email Payload", dto);
    }
}