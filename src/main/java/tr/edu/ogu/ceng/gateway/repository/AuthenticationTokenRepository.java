package tr.edu.ogu.ceng.gateway.repository;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken ,Long> {
	
	@Query("SELECT u.issuedAt FROM AuthenticationToken  u WHERE u.token = :token")
	LocalDateTime getByTokenIssuedAt(String token);
    	// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek*/
}
