package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.Refund;
@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
	
	// username ile refund miktarını getirir
	@Query("SELECT u.amount FROM Refund u WHERE u.transaction.payment.user.username = :username")
	String getAmountByUsername(String username);
	// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
	    
	// username ile refund durumunu getirir
	@Query("SELECT u.status FROM Refund u WHERE u.transaction.payment.user.username = :username")
	String getStatusByUsername(String username);
		
	// username ile refund kim tarafından yaratıldı bilgisini getirir
	@Query("SELECT u.createdBy FROM Refund u WHERE u.transaction.payment.user.username = :username")
	String getCreatedByByUsername(String username);
		
	// username ile refund yaratılma zamanını getirir
	@Query("SELECT u.createdAt FROM Refund u WHERE u.transaction.payment.user.username = :username")
	LocalDateTime getCreatedAtByUsername(String username);
		
	// username ile refund güncelleme zamanını getirir
	@Query("SELECT u.updatedAt FROM Refund u WHERE u.transaction.payment.user.username = :username")
	LocalDateTime getUpdatedAtByUsername(String username);
		
	// username ile refund güncelleme kim tarafından bilgisini getirir
	@Query("SELECT u.updatedBy FROM Refund u WHERE u.transaction.payment.user.username = :username")
	String getUpdatedByByUsername(String username);
		
	// username ile refund silinme zamanını getirir
	@Query("SELECT u.deletedAt FROM Refund u WHERE u.transaction.payment.user.username = :username")
	LocalDateTime getDeletedAtByUsername(String username);
	
	// username ile refund kim tarafından silindi bilgisini getirir
	@Query("SELECT u.deletedBy FROM Refund u WHERE u.transaction.payment.user.username = :username")
	String getDeletedByByUsername(String username);
	
	// username ile refund kim tarafından silindi bilgisini getirir
	@Query("SELECT u.id FROM Refund u WHERE u.transaction.payment.user.username = :username")
	Long getRefundIdByUsername(String username);
	
	// username ile refund kur türünü getirir
	@Query("SELECT u.transaction.currency FROM Refund  u WHERE u.transaction.payment.user.username = :username")
	String getRefundCurrencyByUsername(String username);
	
	// email ile refund miktarını getirir
	@Query("SELECT u.amount FROM Refund u WHERE u.transaction.payment.user.email = :email")
	String getAmountByEmail(String email);
	// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
		    
	// email ile refund durumunu getirir
	@Query("SELECT u.status FROM Refund u WHERE u.transaction.payment.user.email = :email")
	String getStatusByEmail(String email);
			
	// email ile refund kim tarafından yaratıldı bilgisini getirir
	@Query("SELECT u.createdBy FROM Refund u WHERE u.transaction.payment.user.email = :email")
	String getCreatedByByEmail(String email);
			
	// email ile refund yaratılma zamanını getirir
	@Query("SELECT u.createdAt FROM Refund u WHERE u.transaction.payment.user.email = :email")
	LocalDateTime getCreatedAtByEmail(String email);
			
	// email ile refund güncelleme zamanını getirir
	@Query("SELECT u.updatedAt FROM Refund u WHERE u.transaction.payment.user.email = :email")
	LocalDateTime getUpdatedAtByEmail(String email);
			
	// email ile refund güncelleme kim tarafından bilgisini getirir
	@Query("SELECT u.updatedBy FROM Refund u WHERE u.transaction.payment.user.email = :email")
	String getUpdatedByByEmail(String email);
			
	// email ile refund silinme zamanını getirir
	@Query("SELECT u.deletedAt FROM Refund u WHERE u.transaction.payment.user.email = :email")
	LocalDateTime getDeletedAtByEmail(String email);
		
	// email ile refund kim tarafından silindi bilgisini getirir
	@Query("SELECT u.deletedBy FROM Refund u WHERE u.transaction.payment.user.email = :email")
	String getDeletedByByEmail(String email);
		
	// email ile refund kim tarafından silindi bilgisini getirir
	@Query("SELECT u.id FROM Refund u WHERE u.transaction.payment.user.email = :email")
	Long getRefundIdByEmail(String email);
	
}
