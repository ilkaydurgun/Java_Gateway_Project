package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.Setting;
import tr.edu.ogu.ceng.gateway.entity.Users;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
	boolean existsBySettingKey(String settingKey);

	// settingkey ile createdAt getirilir
	@Query("SELECT u.createdAt FROM Setting u WHERE u.settingKey = :settingKey")
	LocalDateTime getCreatedAtBySettingKey(String settingKey);
	
	// settingkey ile createdBy getirilir
	@Query("SELECT u.createdBy FROM Setting u WHERE u.settingKey = :settingKey")
	String getCreatedByBySettingKey(String settingKey);
	
	// settingkey ile settingValue getirilir
	@Query("SELECT u.settingValue FROM Setting u WHERE u.settingKey = :settingKey")
	String getSettingValueBySettingKey(String settingKey);
	
	// settingkey ile deletedBy getirilir
	@Query("SELECT u.deletedBy FROM Setting u WHERE u.settingKey = :settingKey")
	String getDeletedByBySettingKey(String settingKey);
	
	// settingkey ile deletedAt getirilir
	@Query("SELECT u.deletedAt FROM Setting u WHERE u.settingKey = :settingKey")
	LocalDateTime getDeletedAtBySettingKey(String settingKey);
	
	// settingkey ile version getirilir
	@Query("SELECT u.version FROM Setting u WHERE u.settingKey = :settingKey")
	Integer getVersionBySettingKey(String settingKey);
	
	// settingkey ile updatedAt getirilir
	@Query("SELECT u.updatedAt FROM Setting u WHERE u.settingKey = :settingKey")
	LocalDateTime getUpdatedAtBySettingKey(String settingKey);
	
	// settingkey ile updatedBy getirilir
	@Query("SELECT u.updatedBy FROM Setting u WHERE u.settingKey = :settingKey")
	String getUpdatedByBySettingKey(String settingKey);
	
	
	
	


	
}