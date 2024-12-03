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
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.PaymentLog;
import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.PaymentLogRepository;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
public class TestPaymentLogRepository extends DPS {

    
    @Autowired
    PaymentLogRepository paymentLogRepository;
    
    @Autowired
    PaymentRepository paymentRepository;
    
    @Autowired
    TransactionRepository transactionRepository;
    
    @Autowired
    UsersRepository usersRepository;
    
    Users user=new Users();
    Transaction transaction =new Transaction();
    Payment payment=new Payment();
    PaymentLog paymentLog=new PaymentLog();
    
    Users savedUser;
    Transaction savedTransaction;
    Payment savedPayment;
    PaymentLog savedPaymentLog;
    
    @BeforeEach
    void setup() {
    	// Kullanıcı oluşturma
    	user.setCreatedAt(LocalDateTime.now());
    	user.setUsername("barisss123");
    	user.setPassword("12345");
    	user.setRoles("root");
    	user.setEmail("cihanbaristurguttt123@gmail.com");
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
    	transaction.setCreatedAt(LocalDateTime.now());
    	transaction.setUpdatedAt(LocalDateTime.now());
    	// Transaction kaydı
    	savedTransaction = transactionRepository.save(transaction);

    	// PaymentLog oluşturma
    	paymentLog.setPayment(savedPayment);
    	paymentLog.setAction("Payment completed");
    	paymentLog.setCreatedAt(LocalDateTime.now());
    	paymentLog.setUpdatedAt(LocalDateTime.now());
    	// PaymentLog kaydı
    	savedPaymentLog = paymentLogRepository.save(paymentLog);

	
    }
    
    @Test
    @Transactional
    public void verify_savedAllObjectSuccessfully() {
        // Kullanıcının başarıyla kaydedildiğini doğrulama
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("barisss123");
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
    }
    
    @Test
    @Transactional
    public void getByUsername_returnAction() {
		System.out.println("Get action by username: "+paymentLogRepository.getActionByUsername("barisss123"));

    }

    @Test
    @Transactional
    public void getByUsername_returnCreatedBy() {
		System.out.println("Get created by by username: "+paymentLogRepository.getCreatedByByUsername("barisss123"));

    }
    
    @Test
    @Transactional
    public void getByUsername_returnCreatedAt() {
		System.out.println("Get created at by username: "+paymentLogRepository.getCreatedAtByUsername("barisss123"));

    } 
    
    @Test
    @Transactional
    public void getByUsername_returnUpdatedBy() {
		System.out.println("Get updated by by username: "+paymentLogRepository.getUpdatedByByUsername("barisss123"));

    }
    
    @Test
    @Transactional
    public void getByUsername_returnUpdatedAt() {
		System.out.println("Get updated at by username: "+paymentLogRepository.getUpdatedAtByUsername("barisss123"));

    }
    
    @Test
    @Transactional
    public void getByUsername_returnDeletedAt() {
		System.out.println("Get deleted at by username: "+paymentLogRepository.getDeletedAtByUsername("barisss123"));

    }
    
    @Test
    @Transactional
    public void getByUsername_returnDeletedBy() {
		System.out.println("Get deleted by by username: "+paymentLogRepository.getDeletedByByUsername("barisss123"));

    }

    @Test
    @Transactional
    public void getByUsername_returnVersion() {
		System.out.println("Get deleted by by username: "+paymentLogRepository.getVersionByUsername("barisss123"));

    }
    
    @Test
    @Transactional
    public void getByEmail_returnAction() {
        System.out.println("Get action by email: " + paymentLogRepository.getActionByEmail("cihanbaristurguttt123@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnCreatedBy() {
        System.out.println("Get created by by email: " + paymentLogRepository.getCreatedByByEmail("cihanbaristurguttt123@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnCreatedAt() {
        System.out.println("Get created at by email: " + paymentLogRepository.getCreatedAtByEmail("cihanbaristurguttt123@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnUpdatedBy() {
        System.out.println("Get updated by by email: " + paymentLogRepository.getUpdatedByByEmail("cihanbaristurguttt123@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnUpdatedAt() {
        System.out.println("Get updated at by email: " + paymentLogRepository.getUpdatedAtByEmail("cihanbaristurguttt123@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnDeletedAt() {
        System.out.println("Get deleted at by email: " + paymentLogRepository.getDeletedAtByEmail("cihanbaristurguttt123@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnDeletedBy() {
        System.out.println("Get deleted by by email: " + paymentLogRepository.getDeletedByByEmail("cihanbaristurguttt123@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnVersion() {
        System.out.println("Get version by email: " + paymentLogRepository.getVersionByEmail("cihanbaristurguttt123@gmail.com"));
    }

}
