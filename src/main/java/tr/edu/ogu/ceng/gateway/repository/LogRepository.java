package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.Log;
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

	 // username ile endpoint değerini getirir
	@Query("SELECT u.endpoint FROM Log u WHERE u.apiKey.user.username = :username")
	String getEndpointByUsername(String username);
	// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
		    
	// username ile requestTime değerini getirir
	@Query("SELECT u.requestTime FROM Log u WHERE u.apiKey.user.username = :username")
	LocalDateTime getRequestTimeByUsername(String username);
			
	// username ile responseTime bilgisini getirir
	@Query("SELECT u.responseTime FROM Log u WHERE u.apiKey.user.username = :username")
	LocalDateTime getResponseTimeByUsername(String username);
			
	// username ile statusCode bilgisini getirir
	@Query("SELECT u.statusCode FROM Log u WHERE u.apiKey.user.username = :username")
	Integer getStatusCodeByUsername(String username);
			
	// username ile createdBy kim tarafından yaratıldı getirir
	@Query("SELECT u.createdBy FROM Log u WHERE u.apiKey.user.username = :username")
	String getCreatedByByUsername(String username);
			
	// username ile createdAt bilgisini getirir
	@Query("SELECT u.createdAt FROM Log u WHERE u.apiKey.user.username = :username")
	LocalDateTime getCreatedAtByUsername(String username);
			
	// username ile kim tarafından güncellendi zamanını getirir
	@Query("SELECT u.updatedBy FROM Log u WHERE u.apiKey.user.username = :username")
	String getUpdatedByByUsername(String username);
		
	// username ile ne zaman güncellendi bilgisini getirir
	@Query("SELECT u.updatedAt FROM Log u WHERE u.apiKey.user.username = :username")
	LocalDateTime getUpdatedAtByUsername(String username);
		
	// username ile kim tarafından silindi zamanını getirir
	@Query("SELECT u.deletedBy FROM Log u WHERE u.apiKey.user.username = :username")
	String getDeletedByByUsername(String username);
				
	// username ile ne zaman silindi bilgisini getirir
	@Query("SELECT u.deletedAt FROM Log u WHERE u.apiKey.user.username = :username")
	LocalDateTime getDeletedAtByUsername(String username);
		
	// username ile log versiyonu bilgisini getirir
	@Query("SELECT u.version FROM Log u WHERE u.apiKey.user.username = :username")
	Integer getVersionByUsername(String username);
	
	// email ile endpoint değerini getirir
	@Query("SELECT u.endpoint FROM Log u WHERE u.apiKey.user.email = :email")
	String getEndpointByEmail(String email);

	// email ile requestTime değerini getirir
	@Query("SELECT u.requestTime FROM Log u WHERE u.apiKey.user.email = :email")
	LocalDateTime getRequestTimeByEmail(String email);

	// email ile responseTime bilgisini getirir
	@Query("SELECT u.responseTime FROM Log u WHERE u.apiKey.user.email = :email")
	LocalDateTime getResponseTimeByEmail(String email);

	// email ile statusCode bilgisini getirir
	@Query("SELECT u.statusCode FROM Log u WHERE u.apiKey.user.email = :email")
	Integer getStatusCodeByEmail(String email);

	// email ile createdBy kim tarafından yaratıldı getirir
	@Query("SELECT u.createdBy FROM Log u WHERE u.apiKey.user.email = :email")
	String getCreatedByByEmail(String email);

	// email ile createdAt bilgisini getirir
	@Query("SELECT u.createdAt FROM Log u WHERE u.apiKey.user.email = :email")
	LocalDateTime getCreatedAtByEmail(String email);

	// email ile kim tarafından güncellendi zamanını getirir
	@Query("SELECT u.updatedBy FROM Log u WHERE u.apiKey.user.email = :email")
	String getUpdatedByByEmail(String email);

	// email ile ne zaman güncellendi bilgisini getirir
	@Query("SELECT u.updatedAt FROM Log u WHERE u.apiKey.user.email = :email")
	LocalDateTime getUpdatedAtByEmail(String email);

	// email ile kim tarafından silindi zamanını getirir
	@Query("SELECT u.deletedBy FROM Log u WHERE u.apiKey.user.email = :email")
	String getDeletedByByEmail(String email);

	// email ile ne zaman silindi bilgisini getirir
	@Query("SELECT u.deletedAt FROM Log u WHERE u.apiKey.user.email = :email")
	LocalDateTime getDeletedAtByEmail(String email);

	// email ile log versiyonu bilgisini getirir
	@Query("SELECT u.version FROM Log u WHERE u.apiKey.user.email = :email")
	Integer getVersionByEmail(String email);

}
