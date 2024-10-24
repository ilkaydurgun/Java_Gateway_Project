package tr.edu.ogu.ceng.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Getter;
import lombok.Setter;
import tr.edu.ogu.ceng.gateway.entity.Log;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		System.out.println("görev başarılı");
		Log log=new Log();
		System.out.print(log.getId());
	}

}

