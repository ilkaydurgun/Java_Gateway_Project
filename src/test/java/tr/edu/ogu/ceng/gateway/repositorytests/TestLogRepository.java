package tr.edu.ogu.ceng.gateway.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
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
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.LogRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
public class TestLogRepository extends DPS{

	
	
	@Autowired
	LogRepository logRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	RateLimitRepository rateLimitRepository;
	
	@Autowired
	ApiKeyRepository apiKeyRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	Log log=new Log();
	Users user=new Users();
	RateLimit rateLimit=new RateLimit();
	ApiKey apiKey =new ApiKey();
	Payment payment= new Payment();
	
	Log savedLog;
	Users savedUser;
	RateLimit savedRateLimit;
	ApiKey savedApiKey;
	Payment savedPayment;
	
	
	@BeforeEach
	void setup() {
		// Kullanıcı oluşturma
        user.setCreatedAt(LocalDateTime.now());
        user.setUsername("barisss123");
        user.setPassword("12345");
        user.setRoles("root");
        user.setEmail("cihanbaristurguttt123@gmail.com");
        savedUser = usersRepository.save(user);
        // ApiKey oluşturma
        apiKey.setUser(savedUser);
		apiKey.setApiKey("19");
		apiKey.setCreatedAt(LocalDateTime.now());
		savedApiKey = apiKeyRepository.save(apiKey);
		// Ödeme oluşturma
        payment.setUser(savedUser);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setOrderId(12345L);
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setCurrency("USD");
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setStatus("COMPLETED");
        savedPayment = paymentRepository.save(payment);
        //Log oluşturma
        log.setApiKey(savedApiKey);
        log.setEndpoint("19");
        log.setRequestTime(LocalDateTime.now());
        log.setStatusCode(0);
        log.setCreatedAt(LocalDateTime.now());
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
	public void verify_savedAllObjectSuccessfully() {
        // Kullanıcının başarıyla kaydedildiğini doğrulama
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("barisss123");
		// Apikeyin başarıyla kaydedildiğini doğrulama
        assertThat(savedApiKey).isNotNull();
        assertThat(savedApiKey.getApiKey()).isEqualTo("19");
        // Payment kaydının doğruluğunu kontrol etme
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedPayment.getCurrency()).isEqualTo("USD");
        assertThat(savedPayment.getStatus()).isEqualTo("COMPLETED");
        // Log başarıyla kaydedildiğini doğrulama
        assertThat(savedLog).isNotNull();
        assertThat(savedLog.getEndpoint()).isEqualTo("19");
 		// Kaydedilen RateLimit verisinin doğruluğunu kontrol etme
 		assertThat(savedRateLimit).isNotNull();
 		assertThat(savedRateLimit.getLimit()).isEqualTo(100);
 		assertThat(savedRateLimit.getWindow()).isEqualTo(30);
 		assertThat(savedRateLimit.getCreatedBy()).isEqualTo("Test User");
 		assertThat(savedRateLimit.getApiKey()).isNotNull();
 		assertThat(savedRateLimit.getApiKey().getApiKey()).isEqualTo("19");
 		assertThat(savedRateLimit.getCreatedAt()).isNotNull();
 		assertThat(savedRateLimit.getUpdatedAt()).isNotNull();
	}
	
	@Test
	@Transactional
	public void getByUsername_returnEndpoint() {
		System.out.println("Get endpoint  by username: "+logRepository.getEndpointByUsername("barisss123"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnRequestTime() {
		System.out.println("Get requestTime  by username: "+logRepository.getRequestTimeByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnResponseTime() {
		System.out.println("Get responseTime  by username: "+logRepository.getResponseTimeByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnStatusCode() {
		System.out.println("Get statusCode  by username: "+logRepository.getStatusCodeByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnCreatedBy() {
		System.out.println("Get created by  by username: "+logRepository.getCreatedByByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnCreatedAt() {
		System.out.println("Get created at  by username: "+logRepository.getCreatedAtByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnUpdatedBy() {
		System.out.println("Get updated by by username: "+logRepository.getUpdatedByByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnUpdatedAt() {
		System.out.println("Get updated at  by username: "+logRepository.getUpdatedAtByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnDeletedBy() {
		System.out.println("Get deleted by  by username: "+logRepository.getDeletedByByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnDeletedAt() {
		System.out.println("Get deleted at  by username: "+logRepository.getDeletedAtByUsername("barisss123"));

	}

	@Test
	@Transactional
	public void getByUsername_returnVersion() {
		System.out.println("Get version  by username: "+logRepository.getVersionByUsername("barisss123"));

	}
	
	@Test
	@Transactional
	public void getByEmail_returnEndpoint() {
	    System.out.println("Get endpoint by email: " + logRepository.getEndpointByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnRequestTime() {
	    System.out.println("Get requestTime by email: " + logRepository.getRequestTimeByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnResponseTime() {
	    System.out.println("Get responseTime by email: " + logRepository.getResponseTimeByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnStatusCode() {
	    System.out.println("Get statusCode by email: " + logRepository.getStatusCodeByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedBy() {
	    System.out.println("Get created by by email: " + logRepository.getCreatedByByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedAt() {
	    System.out.println("Get created at by email: " + logRepository.getCreatedAtByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedBy() {
	    System.out.println("Get updated by by email: " + logRepository.getUpdatedByByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedAt() {
	    System.out.println("Get updated at by email: " + logRepository.getUpdatedAtByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedBy() {
	    System.out.println("Get deleted by by email: " + logRepository.getDeletedByByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedAt() {
	    System.out.println("Get deleted at by email: " + logRepository.getDeletedAtByEmail("cihanbaristurguttt123@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnVersion() {
	    System.out.println("Get version by email: " + logRepository.getVersionByEmail("cihanbaristurguttt123@gmail.com"));
	}

}
