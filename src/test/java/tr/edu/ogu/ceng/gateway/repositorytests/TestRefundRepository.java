package tr.edu.ogu.ceng.gateway.repositorytests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.common.DPS;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.Refund;
import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.RefundRepository;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class TestRefundRepository extends DPS {

	
	@Autowired
	RefundRepository refundRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	private Users user=new Users();
	private Payment payment=new Payment();
	private Transaction transaction=new Transaction();
	private Refund refund= new Refund();
	
	Users savedUser;
	Payment savedPayment;
	Transaction savedTransaction;
	Refund savedRefund;
	
	@BeforeEach
	void setup() {
		// Kullanıcı oluşturma
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("baris"); // Rastgele kullanıcı adı
		user.setPassword("randomPassword_" + UUID.randomUUID().toString()); // Rastgele şifre
		user.setRoles("user");
		user.setEmail("cihanbaristurgut@gmail.com"); // Rastgele e-posta
		savedUser = usersRepository.save(user);
		// Ödeme oluşturma
		payment.setUser(savedUser);
		payment.setCreatedAt(LocalDateTime.now());
		payment.setOrderId(12345L);
		payment.setAmount(BigDecimal.valueOf(100.00));
		payment.setCurrency("USD");
		payment.setPaymentMethod("CREDIT_CARD");
		payment.setStatus("COMPLETED");
		// Payment kaydı
		savedPayment = paymentRepository.save(payment);
		// İşlem oluşturma
		transaction.setPayment(savedPayment);
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setStatus("SUCCESS");
		transaction.setAmount(BigDecimal.valueOf(100.00));
		transaction.setCurrency("USD");
		transaction.setCreatedBy("baris");
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setUpdatedAt(LocalDateTime.now()); 
		// Transaction kaydı
		savedTransaction = transactionRepository.save(transaction);
		// Refund oluşturma
		refund.setTransaction(savedTransaction);
		refund.setId(null);
		refund.setDeletedBy("baris");
		refund.setDeletedAt(LocalDateTime.now());
		refund.setAmount(BigDecimal.valueOf(100.00));
		refund.setStatus("SUCCESS");
		refund.setCreatedBy("baris");
		refund.setCreatedAt(LocalDateTime.now());
		refund.setVersion(1);
		savedRefund = refundRepository.save(refund);
		System.out.println("Saved Refund ID: " + savedRefund.getId());
		
	}

	@Test
	@Transactional
	public void verify_savedAllObjectSuccessfully() {
		// Kaydedilen Refund verisinin doğruluğunu kontrol etme
		assertThat(savedRefund).isNotNull();
		assertThat(savedRefund.getAmount()).isEqualTo(BigDecimal.valueOf(100.00).setScale(1, RoundingMode.CEILING));
		assertThat(savedRefund.getStatus()).isEqualTo("SUCCESS");
		assertThat(savedRefund.getTransaction()).isNotNull();
		assertThat(savedRefund.getTransaction().getId()).isEqualTo(savedTransaction.getId());
		// Veritabanından kaydedilen Refund verisini tekrar alıp doğrulama
		Refund fetchedRefund = refundRepository.findById(savedRefund.getId()).orElse(null); 
		assertThat(fetchedRefund).isNotNull();
		assertThat(fetchedRefund.getAmount()).isEqualTo(savedRefund.getAmount().setScale(1, RoundingMode.CEILING));
		System.out.println("Fetched refund amount is : "+fetchedRefund.getAmount()+" need to be equal to saved refund amount is : "+ savedRefund.getAmount());
		assertThat(fetchedRefund.getStatus()).isEqualTo(savedRefund.getStatus());
		assertThat(fetchedRefund.getTransaction().getId()).isEqualTo(savedTransaction.getId());
	}
	
	@Test	
	@Transactional
	public void getByUsername_returnAmount() {
		System.out.println("Get refund amount by username: "+refundRepository.getAmountByUsername("baris"));
		System.out.println("User object is: "+ user);
	}

	@Test
	@Transactional
	public void getByUsername_returnStatus() {
		System.out.println("Get refund status by username: "+refundRepository.getStatusByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedBy() {
		System.out.println("Get refund createdby by username: "+refundRepository.getCreatedByByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByUsername_returnCreatedAt() {
		System.out.println("Get refund created at by username: "+refundRepository.getCreatedAtByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByUsername_returnUpdatedAt() {
		System.out.println("Get refund updated at by username: "+refundRepository.getUpdatedAtByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByUsername_returnUpdatedBy() {
		System.out.println("Get refund updatedby by username: "+refundRepository.getUpdatedByByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedAt() {
		System.out.println("Get refund deleted at by username: "+refundRepository.getDeletedAtByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByUsername_returnDeletedBy() {
		System.out.println("Get refund deletedby by username: "+refundRepository.getDeletedByByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByUsername_returnRefundId() {
		System.out.println("Get refund id  by username: "+refundRepository.getRefundIdByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnAmount() {
		System.out.println("Get refund amount by email: "+refundRepository.getAmountByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}

	@Test
	@Transactional
	public void getByEmail_returnStatus() {
		System.out.println("Get refund status by email: "+refundRepository.getStatusByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnCreatedBy() {
		System.out.println("Get refund createdby by email: "+refundRepository.getCreatedByByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnCreatedAt() {
		System.out.println("Get refund created at by email: "+refundRepository.getCreatedAtByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnUpdatedAt() {
		System.out.println("Get refund updated at by email: "+refundRepository.getUpdatedAtByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnUpdatedBy() {
		System.out.println("Get refund updatedby by email: "+refundRepository.getUpdatedByByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnDeletedAt() {
		System.out.println("Get refund deleted at by email: "+refundRepository.getDeletedAtByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnDeletedBy() {
		System.out.println("Get refund deletedby by email: "+refundRepository.getDeletedByByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getByEmail_returnRefundId() {
		System.out.println("Get refund id  by email: "+refundRepository.getRefundIdByEmail("cihanbaristurgut@gmail.com"));
		System.out.println("User object is: "+ user);
	}
	
	@Test
	@Transactional
	public void getUsername_returnRefundCurrency() {
		System.out.println("Get refund currency  by username: "+refundRepository.getRefundCurrencyByUsername("baris"));
		System.out.println("User object is: "+ user);
	}
	
}
