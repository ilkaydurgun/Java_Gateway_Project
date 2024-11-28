package tr.edu.ogu.ceng.gateway.repositorytests;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestRateLimitRepository {
	
	@org.testcontainers.junit.jupiter.Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");
	
	static {
		postgres.start();
	}
	
	@Autowired
	ApiKeyRepository apiKeyRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	RateLimitRepository rateLimitRepository;
	
	@Test
	public void test() {
		
		// Kullanıcı oluşturma
		Users user = new Users();
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("cihanbaris123456789123456789");
		user.setPassword("12345");
		user.setRoles("root");
		user.setEmail("cihanbaristurgut_1234567891234567891@gmail.com");
		Users savedUser = usersRepository.save(user);
		
		// API Key oluşturma
		ApiKey apiKey = new ApiKey();
		apiKey.setUser(savedUser);
		apiKey.setApiKey("19");
		apiKey.setCreatedAt(LocalDateTime.now());
		ApiKey savedApikey = apiKeyRepository.save(apiKey);
		
		// Rate Limit oluşturma
		RateLimit rateLimit = new RateLimit();
		rateLimit.setApiKey(savedApikey);
		rateLimit.setLimit(100);
		rateLimit.setWindow(Duration.ofMinutes(30));
		rateLimit.setCreatedBy("Test User");
		rateLimit.setCreatedAt(LocalDateTime.now());
		rateLimit.setUpdatedBy("Test User");
		rateLimit.setUpdatedAt(LocalDateTime.now());
		
		// RateLimit kaydını veritabanına kaydetme
		RateLimit savedRateLimit = rateLimitRepository.save(rateLimit);
		// Kaydedilen RateLimit verisinin doğruluğunu kontrol etme
		assertThat(savedRateLimit).isNotNull();
		assertThat(savedRateLimit.getLimit()).isEqualTo(100);
		assertThat(savedRateLimit.getWindow()).isEqualTo(Duration.ofMinutes(30));
		assertThat(savedRateLimit.getCreatedBy()).isEqualTo("Test User");
		assertThat(savedRateLimit.getApiKey()).isNotNull();
		assertThat(savedRateLimit.getApiKey().getApiKey()).isEqualTo("19");
		assertThat(savedRateLimit.getCreatedAt()).isNotNull();
		assertThat(savedRateLimit.getUpdatedAt()).isNotNull();
		
	}
	
	@DynamicPropertySource
	static void configureProporties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		
		registry.add("spring.datasource.username", postgres::getUsername);
		
		registry.add("spring.datasource.password", postgres::getPassword);
	}
}
