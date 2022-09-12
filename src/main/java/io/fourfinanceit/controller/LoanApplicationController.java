package io.fourfinanceit.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.fourfinanceit.dto.LoanApplicationDto;
import io.fourfinanceit.service.LoanApplicationService;

@RestController
@RequestMapping("/api/applications")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> apply(@RequestBody LoanApplicationDto loanApplicationDto,
                                                    HttpServletRequest request) {

        loanApplicationService.apply(loanApplicationDto, request.getRemoteAddr());
        return ResponseEntity.ok().build();
    }

}
