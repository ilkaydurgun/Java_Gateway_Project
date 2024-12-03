package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.query.NativeQuery;

import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.entity.Payment;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// username ile kur türünü getirir
	@Query("SELECT u.currency FROM Transaction u WHERE u.payment.user.username = :username")
	String getCurrencyByUsername(String username);
    // repository metodlarını yaz, metodlar service katmanında hazır testlerinin yazılmış olması gerek
    
	// username ile transaction yaratılma zamanı getirilir
	@Query("SELECT u.createdAt FROM Transaction u WHERE u.payment.user.username = :username")
	LocalDateTime getCreatedAtByUsername(String username);
	
	// username ile transaction kim tarafından yaratıldı bilgisini getirir
	@Query("SELECT u.createdBy FROM Transaction u WHERE u.payment.user.username = :username")
	String getCreatedByByUsername(String username);
	
	// username ile transaction durumunu getirir
	@Query("SELECT u.status FROM Transaction u WHERE u.payment.user.username = :username")
	String getStatusByUsername(String username);
	
	// username ile transactionda kullanılmış miktarı getirir
	@Query("SELECT u.amount FROM Transaction u WHERE u.payment.user.username = :username")
	String getAmountByUsername(String username);
	
	// username ile transaction silinme zamanını getirir
	@Query("SELECT u.deletedAt FROM Transaction u WHERE u.payment.user.username = :username")
	LocalDateTime getDeletedAtByUsername(String username);
	
	// username ile transaction idsini getirir
	@Query("SELECT u.id FROM Transaction u WHERE u.payment.user.username = :username")
	String getTransactionIdByUsername(String username);
	
}
