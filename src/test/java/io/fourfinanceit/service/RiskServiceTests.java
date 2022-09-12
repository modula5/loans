package io.fourfinanceit.service;

import static java.time.Clock.systemUTC;
import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.fourfinanceit.ApiProperties;
import io.fourfinanceit.TestClockDelegate;
import io.fourfinanceit.entity.LoanApplication;
import io.fourfinanceit.exception.RiskException;
import io.fourfinanceit.repositories.LoanApplicationRepository;
import io.fourfinanceit.service.impl.RiskServiceImpl;

@ExtendWith(MockitoExtension.class)
class RiskServiceTests {

    ApiProperties apiProperties = new ApiProperties();

    @Mock
    LoanApplicationRepository loanApplicationRegistryRepository;

    @Mock
    RiskService riskService;

    TestClockDelegate clock = new TestClockDelegate(systemUTC());

    @BeforeEach
    void setUp() {
        apiProperties.setMinAmount(20);
        apiProperties.setMaxAmount(2000);
        apiProperties.setMinPeriod(7);
        apiProperties.setMaxPeriod(1800);
        apiProperties.setFirstForbiddenHour(0);
        apiProperties.setLastForbiddenHour(5);
        apiProperties.setMaxApplicationPerDay(3);

        riskService = new RiskServiceImpl(apiProperties, loanApplicationRegistryRepository, clock);
    }

    @Test
    @DisplayName("Analise risk verify save test")
    void analiseRiskVerifySaveTest() {
        clock.changeDelegate(Clock.fixed(LocalDateTime.parse("2017-10-25T08:59:59").toInstant(UTC), UTC));
        String ip = "0.0.0.0";
        LoanApplication applicationRegistry = new LoanApplication();
        applicationRegistry.setIp(ip);
        applicationRegistry.setApplyDate(LocalDate.now(clock));
        when(loanApplicationRegistryRepository.countByIpAndApplyDate(ip, LocalDate.now(clock))).thenReturn(0L);

        assertDoesNotThrow(() -> riskService.analizeRisk(applicationRegistry));
    }

    @Test
    void analizeRiskForbidenHoursJustStartedTest() {
        clock.changeDelegate(Clock.fixed(LocalDateTime.parse("2017-10-25T00:00:00").toInstant(UTC), UTC));
        String ip = "0.0.0.0";
        LoanApplication applicationRegistry = new LoanApplication();
        applicationRegistry.setIp(ip);

        assertThrows(RiskException.class, () -> riskService.analizeRisk(applicationRegistry));
    }

    @Test
    void analizeRiskForbidenHoursCloseToEndTest() {
        clock.changeDelegate(Clock.fixed(LocalDateTime.parse("2017-10-25T05:59:59").toInstant(UTC), UTC));
        String ip = "0.0.0.0";
        LoanApplication applicationRegistry = new LoanApplication();
        applicationRegistry.setIp(ip);

        assertThrows(RiskException.class, () -> riskService.analizeRisk(applicationRegistry));
    }

    @Test
    void analizeRiskToManyApplicationsTest() {
        clock.changeDelegate(Clock.fixed(LocalDateTime.parse("2017-10-25T08:59:59").toInstant(UTC), UTC));
        String ip = "0.0.0.0";
        LoanApplication applicationRegistry = new LoanApplication();
        applicationRegistry.setIp(ip);

        when(loanApplicationRegistryRepository.countByIpAndApplyDate(ip, LocalDate.now(clock))).thenReturn(4L);

        assertThrows(RiskException.class, () -> riskService.analizeRisk(applicationRegistry));
    }

}
