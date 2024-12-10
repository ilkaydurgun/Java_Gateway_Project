package tr.edu.ogu.ceng.gateway.repository;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken ,Long> {
	
	
	@Query("SELECT u.token FROM AuthenticationToken u WHERE u.user.username = :username")
	String getTokenByUsername(@Param("username") String username);

	
	// token ile issuedAt getirilir
	@Query("SELECT u.issuedAt FROM AuthenticationToken  u WHERE u.token = :token")
	LocalDateTime getIssuedAtByToken(String token);
    // repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek*/
	
	// token ile expiresAt getirilir
	@Query("SELECT u.expiresAt FROM AuthenticationToken  u WHERE u.token = :token")
	LocalDateTime getExpiresAtByToken(String token);
	
	// token ile createdBy getirilir
	@Query("SELECT u.createdBy FROM AuthenticationToken  u WHERE u.token = :token")
	String getcreatedByByToken(String token);
	
	// token ile deletedBy getirilir
	@Query("SELECT u.deletedBy FROM AuthenticationToken  u WHERE u.token = :token")
	String getdeletedByByToken(String token);
	
	// token ile username getirilir
	@Query("SELECT u.user.username FROM AuthenticationToken  u WHERE u.token = :token")
	String getUsernameByToken(String token);
	
	
}
