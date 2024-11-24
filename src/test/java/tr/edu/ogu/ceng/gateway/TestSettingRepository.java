package tr.edu.ogu.ceng.gateway;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.entity.Setting;
import tr.edu.ogu.ceng.gateway.repository.SettingRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestSettingRepository {
	
	@org.testcontainers.junit.jupiter.Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");
	
	static {
		postgres.start();
	}
	
	@Autowired
	SettingRepository settingRepository;
	
	@Test
	public void test() {
		
		// Setting nesnesi oluşturma
		Setting setting = new Setting();
		setting.setSettingKey("1");
		setting.setCreatedAt(LocalDateTime.now());
		
		// Veritabanına kaydetme
		Setting savedSetting = settingRepository.save(setting);
		
		// Kaydedilen verinin doğruluğunu kontrol etme
		assertThat(savedSetting).isNotNull();
		assertThat(savedSetting.getSettingKey()).isEqualTo("1");
		assertThat(savedSetting.getCreatedAt()).isNotNull();
		
		// Veritabanından kaydedilen nesneyi tekrar alıp doğrulama
		Setting fetchedSetting = settingRepository.findById(savedSetting.getId()).orElse(null);
		
		// Nesnenin doğru şekilde alındığını ve tüm alanlarının doğru olduğunu kontrol etme
		assertThat(fetchedSetting).isNotNull();
		assertThat(fetchedSetting.getSettingKey()).isEqualTo(savedSetting.getSettingKey());
		assertThat(fetchedSetting.getCreatedAt()).isEqualTo(savedSetting.getCreatedAt());
		
	}

	@DynamicPropertySource
	static void configureProporties(DynamicPropertyRegistry registry) {
		
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		
		registry.add("spring.datasource.username", postgres::getUsername);
		
		registry.add("spring.datasource.password", postgres::getPassword);
	}
}
