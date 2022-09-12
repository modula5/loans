package io.fourfinanceit;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.time.Clock;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import io.fourfinanceit.IntegrationTest.TestConfiguration;
import io.fourfinanceit.repositories.ClientRepository;
import io.fourfinanceit.repositories.LoanApplicationRepository;

@SpringBootTest(classes = {LoansApplication.class}, webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestConfiguration.class)
public abstract class IntegrationTest {

	@Autowired
	protected ClientRepository clientRepository;

	@Autowired
	protected LoanApplicationRepository loanApplicationRepository;

	@Autowired
	protected TestRestTemplate restTemplate;

	@Autowired
	protected TestClockDelegate testClockDelegate;

	@AfterEach
	void cleanup() {
		clientRepository.deleteAll();
		loanApplicationRepository.deleteAll();
		testClockDelegate.reset();
	}


	static class TestConfiguration {

		@Primary
		@Bean
		TestClockDelegate testClockDelegate(Clock clock) {
			return new TestClockDelegate(clock);
		}

	}

}
