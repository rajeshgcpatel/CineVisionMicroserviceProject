package com.kaankaplan.emailService.business.concretes;

import com.kaankaplan.emailService.business.abstracts.SmsSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class SmsServiceImplTest {

    @Test
    void shouldDelegateToSenderWhenEnabledAndRecipientIsPresent() {
        SmsSender smsSender = Mockito.mock(SmsSender.class);
        SmsServiceImpl smsService = new SmsServiceImpl(smsSender);
        smsService.setEnabled(true);

        smsService.sendSms("05000000000", "Ticket confirmed");

        verify(smsSender).send("05000000000", "Ticket confirmed");
    }

    @Test
    void shouldSkipSendingWhenDisabled() {
        SmsSender smsSender = Mockito.mock(SmsSender.class);
        SmsServiceImpl smsService = new SmsServiceImpl(smsSender);
        smsService.setEnabled(false);

        smsService.sendSms("05000000000", "Ticket confirmed");

        verifyNoInteractions(smsSender);
    }

    @Test
    void shouldSkipSendingWhenRecipientIsInvalid() {
        SmsSender smsSender = Mockito.mock(SmsSender.class);
        SmsServiceImpl smsService = new SmsServiceImpl(smsSender);
        smsService.setEnabled(true);

        smsService.sendSms("invalid", "Ticket confirmed");

        verifyNoInteractions(smsSender);
    }

    @Test
    void shouldNotThrowWhenSenderThrowsRuntimeException() {
        SmsSender smsSender = Mockito.mock(SmsSender.class);
        SmsServiceImpl smsService = new SmsServiceImpl(smsSender);
        smsService.setEnabled(true);
        Mockito.doThrow(new RuntimeException("provider down")).when(smsSender).send("05000000000", "Ticket confirmed");

        try {
            smsService.sendSms("05000000000", "Ticket confirmed");
        } catch (RuntimeException ex) {
            org.junit.jupiter.api.Assertions.fail("Expected no exception but got: " + ex.getMessage());
        }
    }
}
