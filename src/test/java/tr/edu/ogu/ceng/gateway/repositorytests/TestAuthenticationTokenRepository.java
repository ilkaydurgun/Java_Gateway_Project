package tr.edu.ogu.ceng.gateway.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Time;
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
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.entity.Log;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.AuthenticationTokenRepository;
import tr.edu.ogu.ceng.gateway.repository.LogRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
public class TestAuthenticationTokenRepository extends DPS{

	
	
	@Autowired
	AuthenticationTokenRepository authenticationTokenRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	ApiKeyRepository apiKeyRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	LogRepository logRepository;
	
	@Autowired
	RateLimitRepository rateLimitRepository;
	
	AuthenticationToken authenticationToken=new AuthenticationToken();
	Users user=new Users();
	ApiKey apiKey=new ApiKey();
	Payment payment=new Payment();	
	Log log=new Log();
	RateLimit rateLimit=new RateLimit();
	
	AuthenticationToken savedAuthenticationToken;
	Users savedUser;
	ApiKey savedApiKey;
	Payment savedPayment;
	Log savedLog;
	RateLimit savedRateLimit;
	
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
      	authenticationToken.setUser(savedUser);
        authenticationToken.setToken("testtesttesttest");
        authenticationToken.setIssuedAt(LocalDateTime.now());
        authenticationToken.setExpiresAt(LocalDateTime.now());
        authenticationToken.setCreatedAt(LocalDateTime.now());
        savedAuthenticationToken = authenticationTokenRepository.save(authenticationToken);
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
        // AuthenticationToken kaydının doğruluğunu kontrol etme
        assertThat(savedAuthenticationToken).isNotNull();
        assertThat(savedAuthenticationToken.getToken()).isEqualTo("testtesttesttest");
        LocalDateTime issuedAt = authenticationTokenRepository.getIssuedAtByToken("testtesttesttest"); //authenticationtoken repositoryde kendi yazdığımız getbytokenissuedat ile veri çektik
        LocalDateTime expiresAt=authenticationTokenRepository.getExpiresAtByToken("testsad");
        System.out.println(issuedAt); // Ekrana yazdırma (isteğe bağlı)
        System.out.println(authenticationTokenRepository.getUsernameByToken("testtesttesttest")); // Ekrana yazdırma (isteğe bağlı)
        assertThat(savedAuthenticationToken.getIssuedAt()).isEqualToIgnoringSeconds(issuedAt); //nanosaniye düzeyindeki hassasiyeti almaz
	}
	
	@Test
	@Transactional
	public void getByToken_returnIssuedAt() {
	    System.out.println("Get issued at by token : " + authenticationTokenRepository.getIssuedAtByToken("testtesttesttest"));

	}
	
	@Test
	@Transactional
	public void getByToken_returnExpiresAt() {
	    System.out.println("Get expires at by token : " + authenticationTokenRepository.getExpiresAtByToken("testtesttesttest"));

	}
	
	@Test
	@Transactional
	public void getByToken_returnCreatedBy() {
	    System.out.println("Get created by by token : " + authenticationTokenRepository.getcreatedByByToken("testtesttesttest"));

	}
	
	@Test
	@Transactional
	public void getByToken_returnDeletedBy() {
	    System.out.println("Get deleted by by token : " + authenticationTokenRepository.getdeletedByByToken("testtesttesttest"));

	}
	
	@Test
	@Transactional
	public void getByToken_returnUsername() {
	    System.out.println("Get username by by token : " + authenticationTokenRepository.getUsernameByToken("testtesttesttest"));

	}
}
