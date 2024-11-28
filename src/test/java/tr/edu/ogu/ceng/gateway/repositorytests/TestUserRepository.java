package tr.edu.ogu.ceng.gateway.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.hibernate.Session;

import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import jakarta.persistence.EntityManager;
import jakarta.websocket.Session;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
public class TestUserRepository {
	
	@org.testcontainers.junit.jupiter.Container
	static PostgreSQLContainer<?> postgres=
			new PostgreSQLContainer<>("postgres:15-alpine");
	static {
		postgres.start();
	}
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void test() {
		
		Users user=new Users();
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("baris");
		user.setPassword("12345");
		user.setRoles("root");
		user.setEmail("cihanbaristurgut@gmail.com");
		usersRepository.save(user);
		
		Users user2=usersRepository.getByUsername("baris").get();
        System.out.println(user.equals(user2)); // Users entityde .equals methodunu override ederek iki nesnenin eşitliğini test ettik

		Users user3=usersRepository.getByEmail("cihanbaristurgut@gmail.com").get();
		System.out.println(user.equals(user3));
		
	}
	@DynamicPropertySource
	static void configureProporties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url",postgres::getJdbcUrl );

		registry.add("spring.datasource.username", postgres::getUsername);

		registry.add("spring.datasource.password", postgres::getPassword);

	}

}
