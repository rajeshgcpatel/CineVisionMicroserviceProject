package com.kaankaplan.emailService.business.concretes;

import com.kaankaplan.emailService.business.abstracts.SmsSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Slf4j
public class StubSmsSender implements SmsSender {
    @Override
    public void send(String recipient, String message) {
        log.info("Stub SMS to {}: {}", recipient, message);
    }
}
