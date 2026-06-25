package com.kaankaplan.emailService.business.concretes;

import com.kaankaplan.emailService.business.abstracts.SmsSender;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StubSmsSender implements SmsSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("SMS to " + recipient + ": " + message);
    }
}
