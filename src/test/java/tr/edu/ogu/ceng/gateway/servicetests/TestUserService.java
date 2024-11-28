package tr.edu.ogu.ceng.gateway.servicetests;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;
import tr.edu.ogu.ceng.gateway.service.UsersService;

@SpringBootTest

public class TestUserService {
	
	
	@Mock
	UsersRepository usersRepository;
	
	@Autowired
	UsersService usersService;
	
	
	@Test
	public void test() {
		Users user=new Users();
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("baris12");
		user.setPassword("1234567");
		user.setRoles("root");
		user.setEmail("cihanbaristurgut12@gmail.com");
		
		usersService.save(user);
	}
}