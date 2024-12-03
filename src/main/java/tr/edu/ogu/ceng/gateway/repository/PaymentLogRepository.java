package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.PaymentLog;
@Repository
public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {
	
	// username ile action değerini getirir
	@Query("SELECT u.action FROM PaymentLog u WHERE u.payment.user.username = :username")
	String getActionByUsername(String username);
	
	// username ile createdBy değerini getirir
	@Query("SELECT u.createdBy FROM PaymentLog u WHERE u.payment.user.username = :username")
	String getCreatedByByUsername(String username);
	
	// username ile createdAt değerini getirir
	@Query("SELECT u.createdAt FROM PaymentLog u WHERE u.payment.user.username = :username")
	LocalDateTime getCreatedAtByUsername(String username);
	
	// username ile updatedBy değerini getirir
	@Query("SELECT u.updatedBy FROM PaymentLog u WHERE u.payment.user.username = :username")
	String getUpdatedByByUsername(String username);
	
	// username ile updatedAt değerini getirir
	@Query("SELECT u.updatedAt FROM PaymentLog u WHERE u.payment.user.username = :username")
	LocalDateTime getUpdatedAtByUsername(String username);
	
	// username ile deletedAt değerini getirir
	@Query("SELECT u.deletedAt FROM PaymentLog u WHERE u.payment.user.username = :username")
	LocalDateTime getDeletedAtByUsername(String username);
		
	// username ile deletedBy değerini getirir
	@Query("SELECT u.deletedBy FROM PaymentLog u WHERE u.payment.user.username = :username")
	String getDeletedByByUsername(String username);
		
	// username ile version değerini getirir
	@Query("SELECT u.version FROM PaymentLog u WHERE u.payment.user.username = :username")
	Integer getVersionByUsername(String username);
	
	// username ile details değerini getirir
	@Query("SELECT u.details FROM PaymentLog u WHERE u.payment.user.username = :username")
	String getDetailsByUsername(String username);
	
	// email ile action değerini getirir
	@Query("SELECT u.action FROM PaymentLog u WHERE u.payment.user.email = :email")
	String getActionByEmail(String email);

	// email ile createdBy değerini getirir
	@Query("SELECT u.createdBy FROM PaymentLog u WHERE u.payment.user.email = :email")
	String getCreatedByByEmail(String email);

	// email ile createdAt değerini getirir
	@Query("SELECT u.createdAt FROM PaymentLog u WHERE u.payment.user.email = :email")
	LocalDateTime getCreatedAtByEmail(String email);

	// email ile updatedBy değerini getirir
	@Query("SELECT u.updatedBy FROM PaymentLog u WHERE u.payment.user.email = :email")
	String getUpdatedByByEmail(String email);

	// email ile updatedAt değerini getirir
	@Query("SELECT u.updatedAt FROM PaymentLog u WHERE u.payment.user.email = :email")
	LocalDateTime getUpdatedAtByEmail(String email);

	// email ile deletedAt değerini getirir
	@Query("SELECT u.deletedAt FROM PaymentLog u WHERE u.payment.user.email = :email")
	LocalDateTime getDeletedAtByEmail(String email);

	// email ile deletedBy değerini getirir
	@Query("SELECT u.deletedBy FROM PaymentLog u WHERE u.payment.user.email = :email")
	String getDeletedByByEmail(String email);

	// email ile version değerini getirir
	@Query("SELECT u.version FROM PaymentLog u WHERE u.payment.user.email = :email")
	Integer getVersionByEmail(String email);

	// email ile details değerini getirir
	@Query("SELECT u.details FROM PaymentLog u WHERE u.payment.user.email = :email")
	String getDetailsByEmail(String email);

	

}
