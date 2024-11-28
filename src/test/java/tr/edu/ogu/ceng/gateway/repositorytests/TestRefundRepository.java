package tr.edu.ogu.ceng.gateway.repositorytests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.Refund;
import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.RefundRepository;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestRefundRepository {
	
	@org.testcontainers.junit.jupiter.Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");
	
	static {
		postgres.start();
	}
	
	@Autowired
	RefundRepository refundRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	UsersRepository usersRepository;

	@Test
	public void test() {
		
		// Kullanıcı oluşturma
		Users user = new Users();
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("testuser_" + UUID.randomUUID().toString()); // Rastgele kullanıcı adı
		user.setPassword("randomPassword_" + UUID.randomUUID().toString()); // Rastgele şifre
		user.setRoles("user");
		user.setEmail("randomemail" + UUID.randomUUID().toString() + "@example.com"); // Rastgele e-posta
		Users savedUser = usersRepository.save(user);

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
		
		// Refund oluşturma
		Refund refund = new Refund();
		refund.setTransaction(savedTransaction);
		refund.setAmount(BigDecimal.valueOf(100.00));
		refund.setStatus("SUCCESS");
		refund.setCreatedAt(LocalDateTime.now());
		Refund savedRefund = refundRepository.save(refund);

		// Kaydedilen Refund verisinin doğruluğunu kontrol etme
		assertThat(savedRefund).isNotNull();
		assertThat(savedRefund.getAmount()).isEqualTo(BigDecimal.valueOf(100.00).setScale(1, RoundingMode.CEILING));
		assertThat(savedRefund.getStatus()).isEqualTo("SUCCESS");
		assertThat(savedRefund.getTransaction()).isNotNull();
		assertThat(savedRefund.getTransaction().getId()).isEqualTo(savedTransaction.getId());
		
		// Veritabanından kaydedilen Refund verisini tekrar alıp doğrulama
		Refund fetchedRefund = refundRepository.findById(savedRefund.getId()).orElse(null); // entity ile sql eşleşmediği için yeni bir migration dosyası oluşturduk ve testi geçti
		assertThat(fetchedRefund).isNotNull();
		assertThat(fetchedRefund.getAmount()).isEqualTo(savedRefund.getAmount().setScale(2, RoundingMode.CEILING));
		System.out.println(fetchedRefund.getAmount()+" "+ savedRefund.getAmount());
		assertThat(fetchedRefund.getStatus()).isEqualTo(savedRefund.getStatus());
		assertThat(fetchedRefund.getTransaction().getId()).isEqualTo(savedTransaction.getId());
		
	}
	
	@DynamicPropertySource
	static void configureProporties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		
		registry.add("spring.datasource.username", postgres::getUsername);
		
		registry.add("spring.datasource.password", postgres::getPassword);
	}

}
