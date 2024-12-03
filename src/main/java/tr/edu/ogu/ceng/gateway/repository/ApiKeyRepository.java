package tr.edu.ogu.ceng.gateway.repository;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.ApiKey;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey,Long>{

	// username ile apiKey değerini getirir
	@Query("SELECT u.apiKey FROM ApiKey u WHERE u.user.username = :username")
	String getApiKeyByUsername(String username);
	// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
			    
	// username ile createdBy değerini getirir
	@Query("SELECT u.createdBy FROM ApiKey u WHERE u.user.username = :username")
	String getCreatedByByUsername(String username);
				
	// username ile createdAt bilgisini getirir
	@Query("SELECT u.createdAt FROM ApiKey u WHERE u.user.username = :username")
	LocalDateTime getCreatedAtByUsername(String username);
				
	// username ile updatedBy bilgisini getirir
	@Query("SELECT u.updatedBy FROM ApiKey u WHERE u.user.username = :username")
	String getUpdatedByByUsername(String username);
				
	// username ile updatdeAt getirir
	@Query("SELECT u.updatedAt FROM ApiKey u WHERE u.user.username = :username")
	LocalDateTime getUpdatedAtByUsername(String username);
				
	// username ile deletedBy bilgisini getirir
	@Query("SELECT u.deletedBy FROM ApiKey u WHERE u.user.username = :username")
	String getDeletedByByUsername(String username);
				
	// username ile deletedAt bilgisini getirir
	@Query("SELECT u.deletedAt FROM ApiKey u WHERE u.user.username = :username")
	LocalDateTime getDeletedAtByUsername(String username);
			
	// username ile version bilgisini getirir
	@Query("SELECT u.version  FROM ApiKey u WHERE u.user.username = :username")
	Integer getVersionByUsername(String username);
			
	// username ile expiresAt getirir
	@Query("SELECT u.expiresAt FROM ApiKey u WHERE u.user.username = :username")
	LocalDateTime getExpiresAtByUsername(String username);
			
	// email ile apiKey değerini getirir
	@Query("SELECT u.apiKey FROM ApiKey u WHERE u.user.email = :email")
	String getApiKeyByEmail(String email);

	// email ile createdBy değerini getirir
	@Query("SELECT u.createdBy FROM ApiKey u WHERE u.user.email = :email")
	String getCreatedByByEmail(String email);

	// email ile createdAt bilgisini getirir
	@Query("SELECT u.createdAt FROM ApiKey u WHERE u.user.email = :email")
	LocalDateTime getCreatedAtByEmail(String email);

	// email ile updatedBy bilgisini getirir
	@Query("SELECT u.updatedBy FROM ApiKey u WHERE u.user.email = :email")
	String getUpdatedByByEmail(String email);

	// email ile updatedAt getirir
	@Query("SELECT u.updatedAt FROM ApiKey u WHERE u.user.email = :email")
	LocalDateTime getUpdatedAtByEmail(String email);

	// email ile deletedBy bilgisini getirir
	@Query("SELECT u.deletedBy FROM ApiKey u WHERE u.user.email = :email")
	String getDeletedByByEmail(String email);

	// email ile deletedAt bilgisini getirir
	@Query("SELECT u.deletedAt FROM ApiKey u WHERE u.user.email = :email")
	LocalDateTime getDeletedAtByEmail(String email);

	// email ile version bilgisini getirir
	@Query("SELECT u.version FROM ApiKey u WHERE u.user.email = :email")
	Integer getVersionByEmail(String email);

	// email ile expiresAt getirir
	@Query("SELECT u.expiresAt FROM ApiKey u WHERE u.user.email = :email")
	LocalDateTime getExpiresAtByEmail(String email);

				
		
	
}