package com.kaankaplan.emailService.business.concretes;

import com.kaankaplan.emailService.business.abstracts.SmsSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsServiceImpl {

    private final SmsSender smsSender;
    private boolean enabled = true;

    public void sendSms(String recipient, String message) {
        if (!enabled) {
            log.debug("SMS sending is disabled.");
            return;
        }

        if (recipient == null || recipient.isBlank() || !isValidRecipient(recipient)) {
            log.warn("Skipping SMS because recipient is invalid: {}", recipient);
            return;
        }

        try {
            smsSender.send(recipient, message);
        } catch (RuntimeException ex) {
            log.error("Failed to send SMS to {}", recipient, ex);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private boolean isValidRecipient(String recipient) {
        return recipient.matches("^[0-9+\\-() ]{7,15}$");
    }
}
