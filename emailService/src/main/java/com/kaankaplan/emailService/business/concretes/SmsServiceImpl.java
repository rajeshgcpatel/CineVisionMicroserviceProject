package com.kaankaplan.emailService.business.concretes;

import com.kaankaplan.emailService.business.abstracts.SmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl {

    private final SmsSender smsSender;
    private boolean enabled = true;

    public void sendSms(String recipient, String message) {
        if (!enabled || recipient == null || recipient.isBlank()) {
            return;
        }
        smsSender.send(recipient, message);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
