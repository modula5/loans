package io.fourfinanceit.Integration;

import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Clock;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.fourfinanceit.IntegrationTest;
import io.fourfinanceit.dto.LoanApplicationDto;

class LoanApplicationControllerTests extends IntegrationTest {

    @Test
    void createApplicationSuccessTest() {
        testClockDelegate.changeDelegate(Clock.fixed(LocalDateTime.parse("2017-10-25T08:59:59").toInstant(UTC), UTC));
        LoanApplicationDto applicationDto = new LoanApplicationDto();
        applicationDto.setCode("38956");
        applicationDto.setName("five");
        applicationDto.setLastName("fantastic");
        applicationDto.setAmount(20.5);
        applicationDto.setPeriod(36);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/api/applications", applicationDto, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void failedCreateApplicationDueToRiskCheckTest() {
        testClockDelegate.changeDelegate(Clock.fixed(LocalDateTime.parse("2017-10-25T04:59:59").toInstant(UTC), UTC));
        LoanApplicationDto applicationDto = new LoanApplicationDto();
        applicationDto.setCode("38956");
        applicationDto.setName("five");
        applicationDto.setLastName("fantastic");
        applicationDto.setAmount(20.5);
        applicationDto.setPeriod(36);


        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/api/applications", applicationDto, Void.class);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
    }

    @Test
    void failedCreateEmptyApplicationTest() {
        LoanApplicationDto applicationDto = new LoanApplicationDto();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/api/applications", applicationDto, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

}
