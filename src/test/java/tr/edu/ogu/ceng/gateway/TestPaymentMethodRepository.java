package tr.edu.ogu.ceng.gateway;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.Session;

import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import jakarta.persistence.EntityManager;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.LogRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;
import tr.edu.ogu.ceng.gateway.entity.Log;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.entity.Transaction;


@SpringBootTest
public class TestPaymentMethodRepository {
	
	@org.testcontainers.junit.jupiter.Container
	static PostgreSQLContainer<?> postgres=
			new PostgreSQLContainer<>("postgres:15-alpine");
	static {
		postgres.start();
	}
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	ApiKeyRepository apiKeyRepository;
	
	@Autowired
	LogRepository logRepository;
	
	@Autowired 
	RateLimitRepository rateLimitRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	@Test
	public void test() {
		
		// Kullanıcı oluşturma
        Users user = new Users();
        user.setUsername("baris");
        user.setPassword("12345");
        user.setEmail("cihanbaristurgut@gmail.com");
        user.setRoles("root");
        user.setCreatedAt(LocalDateTime.now());
        Users savedUser = usersRepository.save(user);

        // Kullanıcının başarıyla kaydedildiğini doğrulama
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("baris");
		
		ApiKey apiKey=new ApiKey();
		apiKey.setUser(savedUser);
		apiKey.setApiKey("1");
		apiKey.setCreatedAt(LocalDateTime.now());
		ApiKey savedApiKey = apiKeyRepository.save(apiKey);
		
		 // Apikeyin başarıyla kaydedildiğini doğrulama
        assertThat(savedApiKey).isNotNull();
        assertThat(savedApiKey.getApiKey()).isEqualTo("1");
        
        //Log oluşturma
        Log log=new Log();
        log.setApiKey(savedApiKey);
        log.setEndpoint("1");
        log.setRequestTime(LocalDateTime.now());
        log.setStatusCode(0);
        log.setCreatedAt(LocalDateTime.now());
        Log savedLog = logRepository.save(log);

     // Log başarıyla kaydedildiğini doğrulama
        assertThat(savedLog).isNotNull();
        assertThat(savedLog.getEndpoint()).isEqualTo("1");
        
     /*// Rate Limit oluşturma
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
     		assertThat(savedRateLimit.getUpdatedAt()).isNotNull();*/

		
     // Ödeme oluşturma
        Payment payment = new Payment();
        payment.setUser(savedUser);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setOrderId(12345L);
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setCurrency("USD");
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setStatus("COMPLETED");

        // Payment kaydı
        Payment savedPayment = paymentRepository.save(payment);

        // Payment kaydının doğruluğunu kontrol etme
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedPayment.getCurrency()).isEqualTo("USD");
        assertThat(savedPayment.getStatus()).isEqualTo("COMPLETED");

        // İşlem oluşturma
        Transaction transaction = new Transaction();
        transaction.setPayment(savedPayment);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setStatus("SUCCESS");
        transaction.setAmount(BigDecimal.valueOf(100.00));
        transaction.setCurrency("USD");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());

        // Transaction kaydı
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Transaction kaydının doğruluğunu kontrol etme
        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedTransaction.getCurrency()).isEqualTo("USD");
        assertThat(savedTransaction.getStatus()).isEqualTo("SUCCESS");
        
        // Payment ve Transaction ilişkisinin doğruluğunu kontrol etme
        assertThat(savedTransaction.getPayment()).isEqualTo(savedPayment);
        assertThat(savedPayment.getUser()).isEqualTo(savedUser);


		
		
		
	}
	@DynamicPropertySource
	static void configureProporties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url",postgres::getJdbcUrl );

		registry.add("spring.datasource.username", postgres::getUsername);

		registry.add("spring.datasource.password", postgres::getPassword);


	}
	
}
