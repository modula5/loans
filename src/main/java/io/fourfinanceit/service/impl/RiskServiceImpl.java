package io.fourfinanceit.service.impl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import io.fourfinanceit.ApiProperties;
import io.fourfinanceit.entity.LoanApplication;
import io.fourfinanceit.exception.RiskException;
import io.fourfinanceit.repositories.LoanApplicationRepository;
import io.fourfinanceit.service.RiskService;

@Service
public class RiskServiceImpl implements RiskService {

    private final ApiProperties apiProperties;

    private final LoanApplicationRepository loanApplicationRepository;

    private final Clock clock;

    public RiskServiceImpl(ApiProperties apiProperties, LoanApplicationRepository loanApplicationRepository, Clock clock) {
        this.apiProperties = apiProperties;
        this.loanApplicationRepository = loanApplicationRepository;
        this.clock = clock;
    }

    @Override
    public void analizeRisk(LoanApplication loanApplication) {
        if (isNowForbiddenHours() || isTooManyApplicationToday(loanApplication.getIp())) {
            throw new RiskException("loan risk is too high");
        }
    }

    private boolean isNowForbiddenHours() {
        int currHour = LocalTime.now(clock).getHour();
        return currHour >= apiProperties.getFirstForbiddenHour()
            && currHour <= apiProperties.getLastForbiddenHour();
    }

    private boolean isTooManyApplicationToday(String ip) {
        Long applicationsToday = loanApplicationRepository.countByIpAndApplyDate(ip, LocalDate.now(clock));
        return applicationsToday > apiProperties.getMaxApplicationPerDay();
    }
}
