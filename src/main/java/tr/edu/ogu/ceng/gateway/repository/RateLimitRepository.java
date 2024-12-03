package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tr.edu.ogu.ceng.gateway.entity.RateLimit;

public interface RateLimitRepository extends JpaRepository<RateLimit, Long>{
	
	    // username ile limit değerini getirir
		@Query("SELECT u.limit FROM RateLimit u WHERE u.apiKey.user.username = :username")
		Integer getRateLimitByUsername(String username);
		// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
		    
		// username ile window değerini getirir
		@Query("SELECT u.window FROM RateLimit u WHERE u.apiKey.user.username = :username")
		Integer getWindowByUsername(String username);
			
		// username ile ratelimit kim tarafından yaratıldı bilgisini getirir
		@Query("SELECT u.createdBy FROM RateLimit u WHERE u.apiKey.user.username = :username")
		String getCreatedByByUsername(String username);
			
		// username ile ratelimit yaratılma zamanını getirir
		@Query("SELECT u.createdAt FROM RateLimit u WHERE u.apiKey.user.username = :username")
		LocalDateTime getCreatedAtByUsername(String username);
			
		// username ile ratelimit güncelleme zamanını getirir
		@Query("SELECT u.updatedAt FROM RateLimit u WHERE u.apiKey.user.username = :username")
		LocalDateTime getUpdatedAtByUsername(String username);
			
		// username ile ratelimit güncelleme kim tarafından bilgisini getirir
		@Query("SELECT u.updatedBy FROM RateLimit u WHERE u.apiKey.user.username = :username")
		String getUpdatedByByUsername(String username);
			
		// username ile refund silinme zamanını getirir
		@Query("SELECT u.deletedAt FROM RateLimit u WHERE u.apiKey.user.username = :username")
		LocalDateTime getDeletedAtByUsername(String username);
		
		// username ile refund kim tarafından silindi bilgisini getirir
		@Query("SELECT u.deletedBy FROM RateLimit u WHERE u.apiKey.user.username = :username")
		String getDeletedByByUsername(String username);
		
		// username ile refund kim tarafından silindi bilgisini getirir
		@Query("SELECT u.version FROM RateLimit u WHERE u.apiKey.user.username = :username")
		Integer getVersionByUsername(String username);
		
		 // email ile limit değerini getirir
		@Query("SELECT u.limit FROM RateLimit u WHERE u.apiKey.user.email = :email")
		Integer getRateLimitByEmail(String email);
		// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
		    
		// email ile window değerini getirir
		@Query("SELECT u.window FROM RateLimit u WHERE u.apiKey.user.email = :email")
		Integer getWindowByEmail(String email);
			
		// email ile ratelimit kim tarafından yaratıldı bilgisini getirir
		@Query("SELECT u.createdBy FROM RateLimit u WHERE u.apiKey.user.email = :email")
		String getCreatedByByEmail(String email);
			
		// email ile ratelimit yaratılma zamanını getirir
		@Query("SELECT u.createdAt FROM RateLimit u WHERE u.apiKey.user.email = :email")
		LocalDateTime getCreatedAtByEmail(String email);

		// email ile ratelimit güncelleme zamanını getirir
		@Query("SELECT u.updatedAt FROM RateLimit u WHERE u.apiKey.user.email = :email")
		LocalDateTime getUpdatedAtByEmail(String email);

		// email ile ratelimit güncelleme kim tarafından bilgisini getirir
		@Query("SELECT u.updatedBy FROM RateLimit u WHERE u.apiKey.user.email = :email")
		String getUpdatedByByEmail(String email);

		// email ile refund silinme zamanını getirir
		@Query("SELECT u.deletedAt FROM RateLimit u WHERE u.apiKey.user.email = :email")
		LocalDateTime getDeletedAtByEmail(String email);

		// email ile refund kim tarafından silindi bilgisini getirir
		@Query("SELECT u.deletedBy FROM RateLimit u WHERE u.apiKey.user.email = :email")
		String getDeletedByByEmail(String email);

		// email ile refund kim tarafından silindi bilgisini getirir
		@Query("SELECT u.version FROM RateLimit u WHERE u.apiKey.user.email = :email")
		Integer getVersionByEmail(String email);

	
		

}
