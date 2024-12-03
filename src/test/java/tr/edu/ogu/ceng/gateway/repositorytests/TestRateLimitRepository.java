package tr.edu.ogu.ceng.gateway.repositorytests;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.common.DPS;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.Log;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.LogRepository;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestRateLimitRepository extends DPS {
	
	
	@Autowired
	ApiKeyRepository apiKeyRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	RateLimitRepository rateLimitRepository;
	
	@Autowired
	LogRepository logRepository;
	
	private Users user=new Users();
	private ApiKey apiKey=new ApiKey();
	private Log log=new Log();
	private RateLimit rateLimit=new RateLimit();	
	
	Users savedUser;
	ApiKey savedApiKey;
	Log savedLog;
	RateLimit savedRateLimit;
	
	@BeforeEach
	void setup() {
		// Kullanıcı oluşturma
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("cihanbaris123456789123456789");
		user.setPassword("12345");
		user.setRoles("root");
		user.setEmail("cihanbaristurgut_1234567891234567891@gmail.com");
		savedUser = usersRepository.save(user);
		// API Key oluşturma
		apiKey.setUser(savedUser);
		apiKey.setApiKey("19");
		apiKey.setCreatedAt(LocalDateTime.now());
		savedApiKey = apiKeyRepository.save(apiKey);
		// Log oluşturma
		log.setId(null);
		log.setApiKey(savedApiKey);
		log.setEndpoint("end");
		log.setRequestTime(LocalDateTime.now());
		log.setResponseTime(LocalDateTime.now());
		log.setStatusCode(1);
		log.setCreatedAt(LocalDateTime.now());
		log.setCreatedBy(savedUser.getUsername());
		log.setUpdatedAt(LocalDateTime.now());
		log.setUpdatedBy(user.getUsername());
		log.setDeletedAt(LocalDateTime.now());
		log.setDeletedBy("baris");
		log.setVersion(1);
		savedLog = logRepository.save(log);
		// Rate Limit oluşturma
		rateLimit.setApiKey(savedApiKey);
		rateLimit.setLimit(100);
		rateLimit.setWindow(30);
		rateLimit.setCreatedBy("Test User");
		rateLimit.setCreatedAt(LocalDateTime.now());
		rateLimit.setUpdatedBy("Test User");
		rateLimit.setUpdatedAt(LocalDateTime.now());
		savedRateLimit = rateLimitRepository.save(rateLimit);
	}
	
	
	@Test
	@Transactional
	public void saved_RateLimitAndVerifySuccesfully() {
		// Kaydedilen RateLimit verisinin doğruluğunu kontrol etme
		assertThat(savedRateLimit).isNotNull();
		assertThat(savedRateLimit.getLimit()).isEqualTo(100);
		assertThat(savedRateLimit.getWindow());
		assertThat(savedRateLimit.getCreatedBy()).isEqualTo("Test User");
		assertThat(savedRateLimit.getApiKey()).isNotNull();
		assertThat(savedRateLimit.getApiKey().getApiKey()).isEqualTo("19");
		assertThat(savedRateLimit.getCreatedAt()).isNotNull();
		assertThat(savedRateLimit.getUpdatedAt()).isNotNull();
	}
	
	@Test
	@Transactional
	public void getByUsername_returnRateLimit() {
		System.out.println("Get rate limit  by username: "+rateLimitRepository.getRateLimitByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnWindow() {
		System.out.println("Get window  by username: "+rateLimitRepository.getWindowByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedBy() {
		System.out.println("Get created by  by username: "+rateLimitRepository.getCreatedByByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedAt() {
		System.out.println("Get created at  by username: "+rateLimitRepository.getCreatedAtByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnUpdatedAt() {
		System.out.println("Get updated at  by username: "+rateLimitRepository.getUpdatedAtByUsername("cihanbaris123456789123456789"));

	}

	@Test
	@Transactional
	public void getByUsername_returnUpdatedBy() {
		System.out.println("Get updated by by username: "+rateLimitRepository.getUpdatedByByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedBy() {
		System.out.println("Get deleted by  by username: "+rateLimitRepository.getDeletedByByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedAt() {
		System.out.println("Get deleted at  by username: "+rateLimitRepository.getDeletedAtByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnVersion() {
		System.out.println("Get version  by username: "+rateLimitRepository.getVersionByUsername("cihanbaris123456789123456789"));

	}
	
	@Test
	@Transactional
	public void getByEmail_returnRateLimit() {
		System.out.println("Get rate limit by email: " + rateLimitRepository.getRateLimitByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnWindow() {
		System.out.println("Get window by email: " + rateLimitRepository.getWindowByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedBy() {
		System.out.println("Get created by by email: " + rateLimitRepository.getCreatedByByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedAt() {
		System.out.println("Get created at by email: " + rateLimitRepository.getCreatedAtByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedAt() {
		System.out.println("Get updated at by email: " + rateLimitRepository.getUpdatedAtByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedBy() {
		System.out.println("Get updated by by email: " + rateLimitRepository.getUpdatedByByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedBy() {
		System.out.println("Get deleted by by email: " + rateLimitRepository.getDeletedByByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedAt() {
		System.out.println("Get deleted at by email: " + rateLimitRepository.getDeletedAtByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnVersion() {
		System.out.println("Get version by email: " + rateLimitRepository.getVersionByEmail("cihanbaristurgut_1234567891234567891@gmail.com"));
	}

}
