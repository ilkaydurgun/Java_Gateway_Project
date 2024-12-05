package tr.edu.ogu.ceng.gateway.common;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class Container {
	
	
	@org.testcontainers.junit.jupiter.Container
	static PostgreSQLContainer<?> postgres=
			new PostgreSQLContainer<>("postgres:15-alpine");
	static {
		postgres.start();
	}
	
	@DynamicPropertySource
	static void configureProporties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url",postgres::getJdbcUrl );

		registry.add("spring.datasource.username", postgres::getUsername);

		registry.add("spring.datasource.password", postgres::getPassword);

	}
}
