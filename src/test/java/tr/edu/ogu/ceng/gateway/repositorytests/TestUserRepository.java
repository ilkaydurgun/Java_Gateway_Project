package tr.edu.ogu.ceng.gateway.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.Session;

import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import jakarta.persistence.EntityManager;
import jakarta.websocket.Session;
import tr.edu.ogu.ceng.gateway.common.DPS;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
public class TestUserRepository extends DPS {

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	private Users user=new Users();
	private Payment payment=new Payment();

	
	@BeforeEach
	void setup() {
		
		if (!usersRepository.existsByUsername("baris") ) {
		
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("baris");
		user.setPassword("12345");
		user.setRoles("root");
		user.setEmail("cihanbaristurgut@gmail.com");
		
		payment.setUser(user);
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setPaymentMethod("credit-card");
        payment.setOrderId(1L);
        payment.setCurrency("dolar");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus("negative");
        usersRepository.save(user);
		paymentRepository.save(payment);
		}
		
	}
	
	@Test
	@Order(1)
	@Transactional
	public void getByUsername_IsEqualUsers() {
		Users user2=usersRepository.getByUsername("baris").get();
        System.out.println("Are users equal to each other?: "+user.equals(user2));
	}
	
	@Test
	@Order(2)
	@Transactional
	public void getByEmail_IsEqualUsers() {
		Users user3=usersRepository.getByEmail("cihanbaristurgut@gmail.com").orElseGet(null);
		System.out.println("Are users equal to each other?: "+user.equals(user3));
	}
	
	@Test
	@Order(3)
	@Transactional
	public void getByEmail_returnUsername() {
		System.out.println("Get user username by email: "+usersRepository.getUsernameByEmail("cihanbaristurgut@gmail.com"));
	}
	
	@Test
	@Order(4)
	@Transactional
	public void getByUsername_returnUserRoles() {
		System.out.println("Get user role by username: "+usersRepository.getRolesByUsername("baris"));
	}
	
	@Test
	@Order(5)
	@Transactional
	public void getByUsername_returnPaymentMethods() {
		System.out.println("Get user payment method by username: "+usersRepository.getPaymentMethodByUsername("baris"));
	}
	
	@Test
	@Order(6)
	@Transactional
	public void getByUsername_returnPaymentCurrency() {
		System.out.println("Get user payment currency by username: "+usersRepository.getCurrencyByUsername("baris"));
	}
	
	@Test
	@Order(7)
	@Transactional
	public void getByUsername_returnCreatedAt() {
		System.out.println("Get user created time by username: "+usersRepository.getCreatedAtByUsername("baris"));
	}

}
