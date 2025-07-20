package com.viasoft.email_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailOciDTO {

    @NotBlank
    @Email
    @Size(max = 40)
    private String recipientEmail;

    @NotBlank
    @Size(max = 50)
    private String recipientName;

    @NotBlank
    @Email
    @Size(max = 40)
    private String senderEmail;

    @NotBlank
    @Size(max = 100)
    private String subject;

    @NotBlank
    @Size(max = 250)
    private String body;

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}