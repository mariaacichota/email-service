package com.viasoft.email_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailAwsDTO {

    @NotBlank
    @Email
    @Size(max = 45)
    private String recipient;

    @NotBlank
    @Size(max = 60)
    private String recipientName;

    @NotBlank
    @Email
    @Size(max = 45)
    private String sender;

    @NotBlank
    @Size(max = 120)
    private String subject;

    @NotBlank
    @Size(max = 256)
    private String content;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}