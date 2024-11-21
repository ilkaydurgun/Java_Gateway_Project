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
public class testuserrepository {
	@org.testcontainers.junit.jupiter.Container
	static PostgreSQLContainer<?> postgres=
			new PostgreSQLContainer<>("postgres:15-alpine");
	static {
		postgres.start();
	}
	@Autowired
	UsersRepository repository;
	
	
	@Test
	public void test() {
		
		Users user=new Users();
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("baris");
		user.setPassword("12345");
		user.setRoles("root");
		user.setEmail("cihanbaristurgut@gmail.com");
		repository.save(user);
		
		
		
	}
	@DynamicPropertySource
	static void configureProporties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url",postgres::getJdbcUrl );

		registry.add("spring.datasource.username", postgres::getUsername);

		registry.add("spring.datasource.password", postgres::getPassword);


	}
	
}
