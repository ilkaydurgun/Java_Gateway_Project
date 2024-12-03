package tr.edu.ogu.ceng.gateway.repositorytests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestTransactionRepository extends DPS {


    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UsersRepository usersRepository;

    private Users user=new Users();
    private Payment payment=new Payment();
    private Transaction transaction=new Transaction();
    Users savedUser;
    Payment savedPayment;
    Transaction savedTransaction;
    
    @BeforeEach
    void setup() {
    	if (!usersRepository.existsByUsername("baris")) {
    	// Kullanıcı oluşturma
        user.setCreatedAt(LocalDateTime.now());
        user.setUsername("baris");
        user.setPassword("12345");
        user.setRoles("root");
        user.setDeletedBy("root");       
        user.setEmail("cihanbaristurgut@gmail.com");
        savedUser = usersRepository.save(user);
        // Ödeme oluşturma
        payment.setUser(savedUser);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setOrderId(12345L);
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setCurrency("USD");
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setStatus("COMPLETED");
        savedPayment = paymentRepository.save(payment);
        // Transaction oluşturma
        transaction.setPayment(savedPayment);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setStatus("SUCCESS");
        transaction.setAmount(BigDecimal.valueOf(100.00));
        transaction.setCurrency("USD");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());
        transaction.setCreatedBy(user.getUsername());
        transaction.setDeletedAt(LocalDateTime.now());
        savedTransaction = transactionRepository.save(transaction);
    	}
    }
    
    @Test
    @Transactional
    public void saved_userSuccessfully() {
    	assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("baris");
    }
    
    @Test
    @Transactional
    public void saved_paymentSuccessfully() {
    	assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedPayment.getCurrency()).isEqualTo("USD");
        assertThat(savedPayment.getStatus()).isEqualTo("COMPLETED");
    }
    
    @Test
    @Transactional
    public void saved_transactionSuccessfully() {
    	assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getAmount()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(savedTransaction.getCurrency()).isEqualTo("USD");
        assertThat(savedTransaction.getStatus()).isEqualTo("SUCCESS");
    }
    
    @Test
    @Transactional
    public void isTrue_paymentAndTransactionRelation() {
    	 assertThat(savedTransaction.getPayment()).isEqualTo(savedPayment);
         assertThat(savedPayment.getUser()).isEqualTo(savedUser);
    }
    
    @Test
    @Order(1)
    @Transactional
    public void getByUsername_returnCurrency() {
        System.out.println("Get transaction currency by username: "+transactionRepository.getCurrencyByUsername("baris"));
    }
    
    @Test
    @Order(2)
    @Transactional
    public void getByUsername_returnCreatedAt() {
        System.out.println("Get transaction created time by username: "+transactionRepository.getCreatedAtByUsername("baris"));
    }
    
    @Test
    @Order(3)
    @Transactional
    public void getByUsername_returnCreatedBy() {
        System.out.println("Get transaction's user created by username: "+transactionRepository.getCreatedByByUsername("baris"));
    }
    
    @Test
    @Order(4)
    @Transactional
    public void getByUsername_returnStatus() {
        System.out.println("Get transaction status by username: "+transactionRepository.getStatusByUsername("baris"));
    }
    
    @Test
    @Order(5)
    @Transactional
    public void getByUsername_returnAmount() {
        System.out.println("Get transaction amount by username: "+transactionRepository.getAmountByUsername("baris"));
    }
    
    @Test
    @Order(6)
    @Transactional
    public void getByUsername_returnDeletedAt() {
        System.out.println("Get transaction deleted time by username: "+transactionRepository.getDeletedAtByUsername("baris"));
    }

    @Test
    @Order(7)
    @Transactional
    public void getByUsername_returnTransactionId() {
        System.out.println("Get transaction id by username: "+transactionRepository.getTransactionIdByUsername("baris"));
    }
}
