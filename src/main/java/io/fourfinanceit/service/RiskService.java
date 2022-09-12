package io.fourfinanceit.service;

import io.fourfinanceit.entity.LoanApplication;

public interface RiskService {

    void analizeRisk(LoanApplication loanApplication);
}
