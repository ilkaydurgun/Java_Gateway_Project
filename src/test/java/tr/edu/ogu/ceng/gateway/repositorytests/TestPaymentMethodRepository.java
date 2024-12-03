package tr.edu.ogu.ceng.gateway.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.Session;

import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import jakarta.persistence.EntityManager;
import tr.edu.ogu.ceng.gateway.common.DPS;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.LogRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentMethodRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;
import tr.edu.ogu.ceng.gateway.entity.Log;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.PaymentMethod;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.entity.Transaction;


@SpringBootTest
public class TestPaymentMethodRepository extends DPS {

	
	@Autowired
	UsersRepository usersRepository;
	
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
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	Users user=new Users();
	RateLimit rateLimit=new RateLimit();
	Transaction transaction = new Transaction();
	ApiKey apiKey = new ApiKey();
	Payment payment=new Payment();
	Log log=new Log();
	PaymentMethod paymentMethod=new PaymentMethod();
	
	Users savedUser;
	ApiKey savedApiKey;
	Log savedLog;
	RateLimit savedRateLimit;
	Payment savedPayment;
	Transaction savedTransaction;
	PaymentMethod savedPaymentMethod;
	
	@BeforeEach
	void setup() {
		// Kullanıcı oluşturma
        user.setUsername("baris");
        user.setPassword("12345");
        user.setEmail("cihanbaristurgut@gmail.com");
        user.setRoles("root");
        user.setCreatedAt(LocalDateTime.now());
        savedUser=usersRepository.save(user);
        
        // Ödeme yöntemi oluşturma
        paymentMethod.setUser(savedUser);
        paymentMethod.setCreatedAt(LocalDateTime.now());
        paymentMethod.setCreatedBy(savedUser.getUsername());
        paymentMethod.setDefault(true);
        paymentMethod.setDeletedAt(LocalDateTime.now());
        paymentMethod.setDeletedBy(user.getUsername());
        paymentMethod.setDetails("currency dolar");
        paymentMethod.setType("$");
        paymentMethod.setUpdatedAt(LocalDateTime.now());
        paymentMethod.setUpdatedBy("baris");
        paymentMethod.setVersion(1);
        savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
	
	}
	
	
	@Test
	@Transactional
	public void verify_savedAllObjectSuccessfully() {
        // Kullanıcının başarıyla kaydedildiğini doğrulama
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("baris");
		// Ödeme şeklinin başarıyla kaydedildiğini doğrulama
        assertThat(savedPaymentMethod).isNotNull();
        assertThat(savedPaymentMethod.getUser().getUsername()).isEqualTo(user.getUsername());
        assertThat(savedPaymentMethod.getCreatedAt()).isNotNull();
        assertThat(savedPaymentMethod.getCreatedBy()).isNotNull();
        assertThat(savedPaymentMethod.getDeletedAt()).isNotNull();
        assertThat(savedPaymentMethod.getDeletedBy()).isNotNull();
        assertThat(savedPaymentMethod.getDetails()).isNotNull();
        assertThat(savedPaymentMethod.getType()).isNotNull();
        assertThat(savedPaymentMethod.getUpdatedAt()).isNotNull();
        assertThat(savedPaymentMethod.getUpdatedBy()).isNotNull();
        assertThat(savedPaymentMethod.getVersion()).isNotNull();
	}
	
	@Test
	@Transactional
	public void getByUsername_returnType() {
	    System.out.println("Get type by username: " + paymentMethodRepository.getTypeByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnDetails() {
	    System.out.println("Get details by username: " + paymentMethodRepository.getDetailsByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnIsDefault() {
	    System.out.println("Get default by username: " + paymentMethodRepository.getIsDefaultByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedBy() {
	    System.out.println("Get created by by username: " + paymentMethodRepository.getCreatedByByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedAt() {
	    System.out.println("Get created at by username: " + paymentMethodRepository.getcreatedAtByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnUpdatedBy() {
	    System.out.println("Get updated by by username: " + paymentMethodRepository.getUpdatedByByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnUpdatedAt() {
	    System.out.println("Get updated at by username: " + paymentMethodRepository.getUpdatedAtByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedBy() {
	    System.out.println("Get deleted by by username: " + paymentMethodRepository.getDeletedByByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedAt() {
	    System.out.println("Get deleted at by username: " + paymentMethodRepository.getDeletedAtByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByUsername_returnVersion() {
	    System.out.println("Get version by username: " + paymentMethodRepository.getVersionByUsername("baris"));

	}
	
	@Test
	@Transactional
	public void getByEmail_returnType() {
	    System.out.println("Get type by email: " + paymentMethodRepository.getTypeByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDetails() {
	    System.out.println("Get details by email: " + paymentMethodRepository.getDetailsByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnIsDefault() {
	    System.out.println("Get default by email: " + paymentMethodRepository.getIsDefaultByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedBy() {
	    System.out.println("Get created by by email: " + paymentMethodRepository.getCreatedByByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnCreatedAt() {
	    System.out.println("Get created at by email: " + paymentMethodRepository.getCreatedAtByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedBy() {
	    System.out.println("Get updated by by email: " + paymentMethodRepository.getUpdatedByByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnUpdatedAt() {
	    System.out.println("Get updated at by email: " + paymentMethodRepository.getUpdatedAtByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedBy() {
	    System.out.println("Get deleted by by email: " + paymentMethodRepository.getDeletedByByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnDeletedAt() {
	    System.out.println("Get deleted at by email: " + paymentMethodRepository.getDeletedAtByEmail("cihanbaristurgut@gmail.com"));
	}

	@Test
	@Transactional
	public void getByEmail_returnVersion() {
	    System.out.println("Get version by email: " + paymentMethodRepository.getVersionByEmail("cihanbaristurgut@gmail.com"));
	}

}
