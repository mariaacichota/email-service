package com.viasoft.email_service.service;

import com.viasoft.email_service.dto.EmailRequestDTO;

public interface EmailService {
    void processEmail(EmailRequestDTO request);
}
