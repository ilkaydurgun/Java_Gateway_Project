package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // username ile amount değerini getirir
	@Query("SELECT u.amount FROM Payment u WHERE u.user.username = :username")
	Integer getAmountByUsername(String username);
	// repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
	    
	// username ile currency değerini getirir
	@Query("SELECT u.currency FROM Payment u WHERE u.user.username = :username")
	String getCurrencyByUsername(String username);
		
	// username ile paymentMethod bilgisini getirir
	@Query("SELECT u.paymentMethod FROM Payment u WHERE u.user.username = :username")
	String getPaymentMethodByUsername(String username);
		
	// username ile status bilgisini getirir
	@Query("SELECT u.status FROM Payment u WHERE u.user.username = :username")
	String getStatusByUsername(String username);
		
	// username ile createdBy kim tarafından yaratıldı getirir
	@Query("SELECT u.createdBy FROM Payment u WHERE u.user.username = :username")
	String getCreatedByByUsername(String username);
		
	// username ile createdAt ne zaman yaratıldı bilgisini getirir
	@Query("SELECT u.createdAt FROM Payment u WHERE u.user.username = :username")
	LocalDateTime getCreatedAtByUsername(String username);
		
	// username ile kim tarafından güncellendi zamanını getirir
	@Query("SELECT u.updatedBy FROM Payment u WHERE u.user.username = :username")
	String getUpdatedByByUsername(String username);
	
	// username ile ne zaman güncellendi bilgisini getirir
	@Query("SELECT u.updatedAt FROM Payment u WHERE u.user.username = :username")
	LocalDateTime getUpdatedAtByUsername(String username);
	
	// username ile kim tarafından silindi zamanını getirir
	@Query("SELECT u.deletedBy FROM Payment u WHERE u.user.username = :username")
	String getDeletedByByUsername(String username);
			
	// username ile ne zaman silindi bilgisini getirir
	@Query("SELECT u.deletedAt FROM Payment u WHERE u.user.username = :username")
	LocalDateTime getDeletedAtByUsername(String username);
	
	// username ile payment versiyonu bilgisini getirir
	@Query("SELECT u.version FROM Payment u WHERE u.user.username = :username")
	Integer getVersionByUsername(String username);
	
	// email ile amount değerini getirir
	@Query("SELECT u.amount FROM Payment u WHERE u.user.email = :email")
	Integer getAmountByEmail(String email);

	// email ile currency değerini getirir
	@Query("SELECT u.currency FROM Payment u WHERE u.user.email = :email")
	String getCurrencyByEmail(String email);

	// email ile paymentMethod bilgisini getirir
	@Query("SELECT u.paymentMethod FROM Payment u WHERE u.user.email = :email")
	String getPaymentMethodByEmail(String email);

	// email ile status bilgisini getirir
	@Query("SELECT u.status FROM Payment u WHERE u.user.email = :email")
	String getStatusByEmail(String email);

	// email ile createdBy kim tarafından yaratıldı getirir
	@Query("SELECT u.createdBy FROM Payment u WHERE u.user.email = :email")
	String getCreatedByByEmail(String email);

	// email ile createdAt ne zaman yaratıldı bilgisini getirir
	@Query("SELECT u.createdAt FROM Payment u WHERE u.user.email = :email")
	LocalDateTime getCreatedAtByEmail(String email);

	// email ile kim tarafından güncellendi zamanını getirir
	@Query("SELECT u.updatedBy FROM Payment u WHERE u.user.email = :email")
	String getUpdatedByByEmail(String email);

	// email ile ne zaman güncellendi bilgisini getirir
	@Query("SELECT u.updatedAt FROM Payment u WHERE u.user.email = :email")
	LocalDateTime getUpdatedAtByEmail(String email);

	// email ile kim tarafından silindi zamanını getirir
	@Query("SELECT u.deletedBy FROM Payment u WHERE u.user.email = :email")
	String getDeletedByByEmail(String email);

	// email ile ne zaman silindi bilgisini getirir
	@Query("SELECT u.deletedAt FROM Payment u WHERE u.user.email = :email")
	LocalDateTime getDeletedAtByEmail(String email);

	// email ile payment versiyonu bilgisini getirir
	@Query("SELECT u.version FROM Payment u WHERE u.user.email = :email")
	Integer getVersionByEmail(String email);

}
