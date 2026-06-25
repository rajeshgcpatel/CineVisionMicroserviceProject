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
}
