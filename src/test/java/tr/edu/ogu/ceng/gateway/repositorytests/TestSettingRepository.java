package tr.edu.ogu.ceng.gateway.repositorytests;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.common.DPS;
import tr.edu.ogu.ceng.gateway.entity.Setting;
import tr.edu.ogu.ceng.gateway.repository.SettingRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestSettingRepository extends DPS{
	
	
	@Autowired
	SettingRepository settingRepository;
	
	Setting setting = new Setting();
    Setting savedSetting;
	
	@BeforeEach
	public void setup() {
		if(!settingRepository.existsBySettingKey("1")) {
			// Setting nesnesi oluşturma
			setting.setSettingKey("1");
			setting.setSettingValue("26");
			setting.setCreatedAt(LocalDateTime.now());
			setting.setDeletedAt(LocalDateTime.now());
			setting.setDeletedBy("root");
			setting.setCreatedBy("root");
			setting.setUpdatedAt(LocalDateTime.now());
			setting.setUpdatedBy("root");
			savedSetting=settingRepository.save(setting);

		}
	}
	
	@Test
	@Order(1)
	@Transactional
	public void saved_settingSuccessfullyAndKey() {
		// Kaydedilen verinin doğruluğunu kontrol etme
		assertThat(savedSetting).isNotNull();
		assertThat(savedSetting.getSettingKey()).isEqualTo("1");
		assertThat(savedSetting.getCreatedAt()).isNotNull();
	}
	
	@Test
	@Order(2)
	@Transactional
	public void verify_settingById() {
		// Veritabanından kaydedilen nesneyi tekrar alıp doğrulama
		Setting fetchedSetting = settingRepository.findById(savedSetting.getId()).orElse(null);
		// Nesnenin doğru şekilde alındığını ve tüm alanlarının doğru olduğunu kontrol etme
		assertThat(fetchedSetting).isNotNull();
		assertThat(fetchedSetting.getSettingKey()).isEqualTo(savedSetting.getSettingKey());
		assertThat(fetchedSetting.getCreatedAt()).isEqualTo(savedSetting.getCreatedAt());
	}
	
	@Test
	@Order(3)
	@Transactional
	public void getBySettingKey_returnCreatedAt() {
		System.out.println("Get setting created at by settingkey: "+settingRepository.getCreatedAtBySettingKey("1"));
	}
	
	@Test
	@Order(4)
	@Transactional
	public void getBySettingKey_returnCreatedBy() {
		System.out.println("Get setting createdby by settingkey: "+settingRepository.getCreatedByBySettingKey("1"));
	}
	
	@Test
	@Order(5)
	@Transactional
	public void getBySettingKey_returnSettingValue() {
		System.out.println("Get setting setting value by settingkey: "+settingRepository.getSettingValueBySettingKey("1"));
	}
	
	@Test
	@Order(6)
	@Transactional
	public void getBySettingKey_returnDeletedBy() {
		System.out.println("Get setting deletedby by settingkey: "+settingRepository.getDeletedByBySettingKey("1"));
	}
	
	@Test
	@Order(7)
	@Transactional
	public void getBySettingKey_returnDeletedAt() {
		System.out.println("Get setting deleted at by settingkey: "+settingRepository.getDeletedAtBySettingKey("1"));
	}
	
	@Test
	@Order(8)
	@Transactional
	public void getBySettingKey_returnVersion() {
		System.out.println("Get setting version by settingkey: "+settingRepository.getVersionBySettingKey("1"));
	}
	
	@Test
	@Order(9)
	@Transactional
	public void getBySettingKey_returnUpdatedAt() {
		System.out.println("Get setting updated at by settingkey: "+settingRepository.getUpdatedAtBySettingKey("1"));
	}
	
	@Test
	@Order(10)
	@Transactional
	public void getBySettingKey_returnUpdatedBy() {
		System.out.println("Get setting updated by settingkey: "+settingRepository.getUpdatedByBySettingKey("1"));
	}
	


}
