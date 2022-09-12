package io.fourfinanceit.service.impl;

import java.time.Clock;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fourfinanceit.dto.LoanApplicationDto;
import io.fourfinanceit.entity.Client;
import io.fourfinanceit.entity.LoanApplication;
import io.fourfinanceit.repositories.LoanApplicationRepository;
import io.fourfinanceit.service.ClientService;
import io.fourfinanceit.service.LoanApplicationService;
import io.fourfinanceit.service.RiskService;


@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private Clock clock;
    @Autowired
    private RiskService riskService;
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Autowired
    private ClientService clientService;

    @Override
    public void apply(LoanApplicationDto application, String remoteAddr) {

        LoanApplication applicationRegistry = new LoanApplication();
        applicationRegistry.setIp(remoteAddr);
        applicationRegistry.setApplyDate(LocalDate.now(clock));
        loanApplicationRepository.save(applicationRegistry);

        riskService.analizeRisk(applicationRegistry);

        clientService.findByCode(application.getCode())
            .orElseGet(() -> clientService.save(createClient(application)));
    }

    private Client createClient(LoanApplicationDto application) {
        return new Client(application.getCode(), application.getName(), application.getLastName());
    }

}
