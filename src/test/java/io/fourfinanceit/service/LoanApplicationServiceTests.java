package io.fourfinanceit.service;

import static java.time.ZoneOffset.UTC;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import io.fourfinanceit.ApiProperties;
import io.fourfinanceit.TestClockDelegate;
import io.fourfinanceit.dto.LoanApplicationDto;
import io.fourfinanceit.entity.Client;
import io.fourfinanceit.repositories.LoanApplicationRepository;
import io.fourfinanceit.service.impl.LoanApplicationServiceImpl;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceTests {

    @InjectMocks
    LoanApplicationService loanApplicationService = new LoanApplicationServiceImpl();

    ApiProperties apiProperties = new ApiProperties();

    @Mock
    LoanApplicationRepository loanApplicationRepository;

    @Mock
    ClientService clientService;

    @Mock
    RiskService riskService;

    @Spy
    TestClockDelegate clock = new TestClockDelegate(Clock.systemDefaultZone());

    @BeforeEach
    void setUp() {
        apiProperties.setMinAmount(20);
        apiProperties.setMaxAmount(2000);
        apiProperties.setMinPeriod(7);
        apiProperties.setMaxPeriod(1800);
        apiProperties.setFirstForbiddenHour(0);
        apiProperties.setLastForbiddenHour(5);
        apiProperties.setMaxApplicationPerDay(3);
    }

    @Test
    void applyVerifyCallsTest() {
        LoanApplicationDto loanApplicationDto = new LoanApplicationDto("code", "name", "lastName", 800D, 36);
        Client client = new Client("code", "name", "lastName");
        client.setId(1L);
        clock.changeDelegate((Clock.fixed(LocalDateTime.parse("2017-10-25T08:59:59").toInstant(UTC), UTC)));

        when(clientService.findByCode("code")).thenReturn(Optional.of(client));

        String ip = "0.0.0.0";
        loanApplicationService.apply(loanApplicationDto, ip);

        verify(clientService, times(1)).findByCode("code");
    }


}
