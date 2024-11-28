package tr.edu.ogu.ceng.gateway;

import java.time.LocalDateTime;


import tr.edu.ogu.ceng.gateway.entity.Users;

public class AbstractTest {
	
	
	protected Users createUser( ) {
		
		Users user= new Users();
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("medusa");
		user.setPassword("admin");
		user.setRoles("admin");
		user.setEmail("bkorlaelci@gmail.com");
		
		return user;
	}
}
