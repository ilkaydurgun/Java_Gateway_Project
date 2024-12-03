package tr.edu.ogu.ceng.gateway.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
import tr.edu.ogu.ceng.gateway.entity.PaymentLog;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.LogRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentLogRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
public class TestApiKeyRepository extends DPS{
	
	
	
	@Autowired
	ApiKeyRepository apiKeyRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	LogRepository logRepository;
	
	@Autowired
	RateLimitRepository rateLimitRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PaymentLogRepository paymentLogRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	ApiKey apiKey=new ApiKey();
	Users user=new Users();
	Log log=new Log();
	RateLimit rateLimit=new RateLimit();
	Transaction transaction = new Transaction();
	PaymentLog paymentLog=new PaymentLog();
	Payment payment=new Payment();
	
	Users savedUser;
	ApiKey savedApiKey;
	Log savedLog;
	RateLimit savedRateLimit;
	Transaction savedTransaction;
	PaymentLog savedPaymentLog;
	Payment savedPayment;
	
	@BeforeEach
	void setup() {
		// Kullanıcı oluşturma
        user.setCreatedAt(LocalDateTime.now());
        user.setUsername("barisss12213");
        user.setPassword("12345");
        user.setRoles("root");
        user.setEmail("cihanbaristurguttt122313@gmail.com");
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
        // İşlem oluşturma
        transaction.setPayment(savedPayment);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setStatus("SUCCESS");
        transaction.setAmount(BigDecimal.valueOf(100.00));
        transaction.setCurrency("USD");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());
        savedTransaction = transactionRepository.save(transaction);
        // PaymentLog oluşturma
        paymentLog.setPayment(savedPayment);
        paymentLog.setAction("Payment completed");
        paymentLog.setCreatedAt(LocalDateTime.now());
        paymentLog.setUpdatedAt(LocalDateTime.now());
        savedPaymentLog = paymentLogRepository.save(paymentLog);
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
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
		// Apikeyin başarıyla kaydedildiğini doğrulama
        assertThat(savedApiKey).isNotNull();
        assertThat(savedApiKey.getApiKey()).isEqualTo("19");
        // Payment kaydının doğruluğunu kontrol etme
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedPayment.getCurrency()).isEqualTo("USD");
        assertThat(savedPayment.getStatus()).isEqualTo("COMPLETED");
        // Transaction kaydının doğruluğunu kontrol etme
        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedTransaction.getCurrency()).isEqualTo("USD");
        assertThat(savedTransaction.getStatus()).isEqualTo("SUCCESS");
        // Payment ve Transaction ilişkisinin doğruluğunu kontrol etme
        assertThat(savedTransaction.getPayment()).isEqualTo(savedPayment);
        assertThat(savedPayment.getUser()).isEqualTo(savedUser);
        // PaymentLog kaydının doğruluğunu kontrol etme
        assertThat(savedPaymentLog).isNotNull();
        assertThat(savedPaymentLog.getAction()).isEqualTo("Payment completed");
        assertThat(savedPaymentLog.getPayment()).isEqualTo(savedPayment);
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
	public void getByUsername_returnApiKey() {
		System.out.println("Get apikey by username: "+apiKeyRepository.getApiKeyByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedBy() {
		System.out.println("Get created by by username: "+apiKeyRepository.getCreatedByByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedAt() {
		System.out.println("Get created at by username: "+apiKeyRepository.getCreatedAtByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnUpdatedBy() {
		System.out.println("Get updated by by username: "+apiKeyRepository.getUpdatedByByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnUpdatedAt() {
		System.out.println("Get updated at username: "+apiKeyRepository.getUpdatedAtByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedBy() {
		System.out.println("Get deleted by by username: "+apiKeyRepository.getDeletedByByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedAt() {
		System.out.println("Get deleted at by username: "+apiKeyRepository.getDeletedAtByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnVersion() {
		System.out.println("Get version by username: "+apiKeyRepository.getVersionByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnExpiresAt() {
		System.out.println("Get expires at by username: "+apiKeyRepository.getExpiresAtByUsername("barisss12213"));

	}
	
	@Test
	@Transactional
	public void getByEmail_returnApiKey() {
	    System.out.println("Get apikey by email: " + apiKeyRepository.getApiKeyByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedBy() {
	    System.out.println("Get created by by email: " + apiKeyRepository.getCreatedByByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedAt() {
	    System.out.println("Get created at by email: " + apiKeyRepository.getCreatedAtByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedBy() {
	    System.out.println("Get updated by by email: " + apiKeyRepository.getUpdatedByByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedAt() {
	    System.out.println("Get updated at email: " + apiKeyRepository.getUpdatedAtByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedBy() {
	    System.out.println("Get deleted by by email: " + apiKeyRepository.getDeletedByByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedAt() {
	    System.out.println("Get deleted at by email: " + apiKeyRepository.getDeletedAtByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnVersion() {
	    System.out.println("Get version by email: " + apiKeyRepository.getVersionByEmail("cihanbaristurguttt122313@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnExpiresAt() {
	    System.out.println("Get expires at by email: " + apiKeyRepository.getExpiresAtByEmail("cihanbaristurguttt122313@gmail.com"));
	}

}
