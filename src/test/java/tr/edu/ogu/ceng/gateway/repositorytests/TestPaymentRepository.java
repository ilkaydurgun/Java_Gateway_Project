package tr.edu.ogu.ceng.gateway.repositorytests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

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
public class TestPaymentRepository {

    @org.testcontainers.junit.jupiter.Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15-alpine");

    static {
        postgres.start();
    }

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    PaymentLogRepository paymentLogRepository;
    
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

        // Payment oluşturma
        Payment payment = new Payment();
        payment.setUser(savedUser);
        payment.setOrderId(12345L);
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setCurrency("USD");
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setStatus("COMPLETED");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        // Payment kaydı
        Payment savedPayment = paymentRepository.save(payment);

        // Payment kaydının doğruluğunu kontrol etme
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedPayment.getCurrency()).isEqualTo("USD");
        assertThat(savedPayment.getStatus()).isEqualTo("COMPLETED");

        // Transaction oluşturma
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

        // PaymentLog oluşturma
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setPayment(savedPayment);
        paymentLog.setAction("Payment completed");
        paymentLog.setCreatedAt(LocalDateTime.now());
        paymentLog.setUpdatedAt(LocalDateTime.now());

        // PaymentLog kaydı
        PaymentLog savedPaymentLog = paymentLogRepository.save(paymentLog);

        // PaymentLog kaydının doğruluğunu kontrol etme
        assertThat(savedPaymentLog).isNotNull();
        assertThat(savedPaymentLog.getAction()).isEqualTo("Payment completed");
        assertThat(savedPaymentLog.getPayment()).isEqualTo(savedPayment);
        
    }

    @DynamicPropertySource
    static void configureProporties(DynamicPropertyRegistry registry) {
    	
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        
        registry.add("spring.datasource.username", postgres::getUsername);
        
        registry.add("spring.datasource.password", postgres::getPassword);
        
    }
}