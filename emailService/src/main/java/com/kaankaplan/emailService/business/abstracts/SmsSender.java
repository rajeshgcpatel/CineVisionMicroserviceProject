package com.kaankaplan.emailService.business.abstracts;

public interface SmsSender {
    void send(String recipient, String message);
}
