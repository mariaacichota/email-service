package com.viasoft.email_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.email_service.dto.EmailAwsDTO;
import com.viasoft.email_service.dto.EmailRequestDTO;
import com.viasoft.email_service.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service("AWS")
public class AwsEmailServiceImpl implements EmailService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void processEmail(EmailRequestDTO request) {
        EmailAwsDTO dto = new EmailAwsDTO();
        dto.setRecipient(request.getRecipientEmail());
        dto.setRecipientName(request.getRecipientName());
        dto.setSender(request.getSenderEmail());
        dto.setSubject(request.getSubject());
        dto.setContent(request.getContent());

        JsonUtil.printJson("AWS Email Payload", dto);
    }
}