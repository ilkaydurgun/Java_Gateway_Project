package tr.edu.ogu.ceng.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Getter;
import lombok.Setter;

@SpringBootApplication
public class GatewayApplication {
		
	
	@Getter @Setter
	private String isim;

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		System.out.println("görev başarılı");
	}

}

