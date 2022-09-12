package io.fourfinanceit.repositories;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import io.fourfinanceit.entity.LoanApplication;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {

    Long countByIpAndApplyDate(String ip, LocalDate applyDate);
}
