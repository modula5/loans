package io.fourfinanceit.service;

import io.fourfinanceit.dto.LoanApplicationDto;

public interface LoanApplicationService {

    void apply(LoanApplicationDto aplication, String remoteAddr);
}
