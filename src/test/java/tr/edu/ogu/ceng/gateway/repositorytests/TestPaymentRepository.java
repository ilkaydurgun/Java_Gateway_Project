package tr.edu.ogu.ceng.gateway.repositorytests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestPaymentRepository extends DPS{

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    PaymentLogRepository paymentLogRepository;
    
    Users user=new Users();
    Payment payment= new Payment();
    Transaction transaction=new Transaction();
    PaymentLog paymentLog=new PaymentLog();
    
    Users savedUser;
    Payment savedPayment;
    Transaction savedTransaction;
    PaymentLog savedPaymentLog;
    
    @BeforeEach
    void setup() {
    	// Kullanıcı oluşturma
        user.setUsername("bariscbt");
        user.setPassword("12345");
        user.setEmail("cihanbaristurgutcbt@gmail.com");
        user.setRoles("root");
        user.setCreatedAt(LocalDateTime.now());
        savedUser = usersRepository.save(user);
        // Payment oluşturma
        payment.setUser(savedUser);
        payment.setOrderId(12345L);
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setCurrency("USD");
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setStatus("COMPLETED");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        // Payment kaydı
        savedPayment = paymentRepository.save(payment);
        // Transaction oluşturma
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
    public void verify_savedAllObjectSuccesfully() {
        // Kullanıcının başarıyla kaydedildiğini doğrulama
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("bariscbt");
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
    public void getByUsername_returnAmount() {
		System.out.println("Get payment amount by username: "+paymentRepository.getAmountByUsername("bariscbt"));
    }

    @Test
    @Transactional
    public void getByUsername_returnCurrency() {
		System.out.println("Get payment currency by username: "+paymentRepository.getCurrencyByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnPaymentMethod() {
		System.out.println("Get payment method by username: "+paymentRepository.getPaymentMethodByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnStatus() {
		System.out.println("Get payment status by username: "+paymentRepository.getStatusByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnCreatedBy() {
		System.out.println("Get payment created by by username: "+paymentRepository.getCreatedByByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnCreatedAt() {
		System.out.println("Get payment created at by username: "+paymentRepository.getCreatedAtByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnUpdatedBy() {
		System.out.println("Get payment updated by by username: "+paymentRepository.getUpdatedByByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnUpdatedAt() {
		System.out.println("Get payment updated at by username: "+paymentRepository.getUpdatedAtByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnDeletedBy() {
		System.out.println("Get payment deleted by username: "+paymentRepository.getDeletedByByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnDeletedAt() {
		System.out.println("Get payment deleted at by username: "+paymentRepository.getDeletedAtByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByUsername_returnVersion() {
		System.out.println("Get payment version by username: "+paymentRepository.getVersionByUsername("bariscbt"));
    }
    
    @Test
    @Transactional
    public void getByEmail_returnAmount() {
    	System.out.println("Get payment amount by email: " + paymentRepository.getAmountByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnCurrency() {
    	System.out.println("Get payment currency by email: " + paymentRepository.getCurrencyByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnPaymentMethod() {
    	System.out.println("Get payment method by email: " + paymentRepository.getPaymentMethodByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnStatus() {
    	System.out.println("Get payment status by email: " + paymentRepository.getStatusByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnCreatedBy() {
    	System.out.println("Get payment created by by email: " + paymentRepository.getCreatedByByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnCreatedAt() {
    	System.out.println("Get payment created at by email: " + paymentRepository.getCreatedAtByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnUpdatedBy() {
    	System.out.println("Get payment updated by by email: " + paymentRepository.getUpdatedByByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnUpdatedAt() {
    	System.out.println("Get payment updated at by email: " + paymentRepository.getUpdatedAtByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnDeletedBy() {
    	System.out.println("Get payment deleted by email: " + paymentRepository.getDeletedByByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnDeletedAt() {
    	System.out.println("Get payment deleted at by email: " + paymentRepository.getDeletedAtByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    @Test
    @Transactional
    public void getByEmail_returnVersion() {
    	System.out.println("Get payment version by email: " + paymentRepository.getVersionByEmail("cihanbaristurgutcbt@gmail.com"));
    }

    
   
}
