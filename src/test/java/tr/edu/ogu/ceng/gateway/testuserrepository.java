package tr.edu.ogu.ceng.gateway;


import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
public class TestUserRepository{
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");
	
	static {
		postgres.start();
	}
	
	@Autowired		//bu test ayaga kalktiginda repo null yemesin diye enject ediyoruz
	UsersRepository repository; 
	
	@Test
	public void test() {
		Users user = new Users();
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("medusa");
		user.setPassword("admin");
		user.setRoles("admin");
		user.setEmail("bkorlaelci@gmail.com");
		repository.save(user);
	}
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
		
	}
}