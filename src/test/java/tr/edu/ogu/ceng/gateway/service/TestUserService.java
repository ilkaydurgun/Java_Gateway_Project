package tr.edu.ogu.ceng.gateway.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tr.edu.ogu.ceng.gateway.AbstractTest;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;


@SpringBootTest
public class TestUserService extends AbstractTest {
	
	@Mock
	UsersRepository userRepository;
	
	@Autowired
	UsersService userService;
	
	@Test
	public void test() {

		Users user= this.createUser();
		
	}
	
	
}
