package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.PaymentMethod;
@Repository
public interface  PaymentMethodRepository  extends JpaRepository<PaymentMethod, Long>{

	// username ile type değerini getirir
	@Query("SELECT u.type FROM PaymentMethod u WHERE u.user.username = :username")
	String getTypeByUsername(String username);
		
	// username ile details değerini getirir
	@Query("SELECT u.details FROM PaymentMethod u WHERE u.user.username = :username")
	String getDetailsByUsername(String username);
		
	// username ile isDefault değerini getirir
	@Query("SELECT u.isDefault FROM PaymentMethod u WHERE u.user.username = :username")
	boolean getIsDefaultByUsername(String username);
		
	// username ile createdBy değerini getirir
	@Query("SELECT u.createdBy FROM PaymentMethod u WHERE u.user.username = :username")
	String getCreatedByByUsername(String username);
		
	// username ile createdAt değerini getirir
	@Query("SELECT u.createdAt FROM PaymentMethod u WHERE u.user.username = :username")
	LocalDateTime getcreatedAtByUsername(String username);
		
	// username ile updatedBy değerini getirir
	@Query("SELECT u.updatedBy FROM PaymentMethod u WHERE u.user.username = :username")
	String getUpdatedByByUsername(String username);
			
	// username ile updatedAt değerini getirir
	@Query("SELECT u.updatedAt FROM PaymentMethod u WHERE u.user.username = :username")
	LocalDateTime getUpdatedAtByUsername(String username);
			
	// username ile deletedBy değerini getirir
	@Query("SELECT u.deletedBy FROM PaymentMethod u WHERE u.user.username = :username")
	String getDeletedByByUsername(String username);
		
	// username ile deletedAt değerini getirir
	@Query("SELECT u.deletedAt FROM PaymentMethod u WHERE u.user.username = :username")
	LocalDateTime getDeletedAtByUsername(String username);
	
	// username ile version değerini getirir
	@Query("SELECT u.version FROM PaymentMethod u WHERE u.user.username = :username")
	Integer getVersionByUsername(String username);
	
	// email ile type değerini getirir
	@Query("SELECT u.type FROM PaymentMethod u WHERE u.user.email = :email")
	String getTypeByEmail(String email);

	// email ile details değerini getirir
	@Query("SELECT u.details FROM PaymentMethod u WHERE u.user.email = :email")
	String getDetailsByEmail(String email);

	// email ile isDefault değerini getirir
	@Query("SELECT u.isDefault FROM PaymentMethod u WHERE u.user.email = :email")
	boolean getIsDefaultByEmail(String email);

	// email ile createdBy değerini getirir
	@Query("SELECT u.createdBy FROM PaymentMethod u WHERE u.user.email = :email")
	String getCreatedByByEmail(String email);

	// email ile createdAt değerini getirir
	@Query("SELECT u.createdAt FROM PaymentMethod u WHERE u.user.email = :email")
	LocalDateTime getCreatedAtByEmail(String email);

	// email ile updatedBy değerini getirir
	@Query("SELECT u.updatedBy FROM PaymentMethod u WHERE u.user.email = :email")
	String getUpdatedByByEmail(String email);

	// email ile updatedAt değerini getirir
	@Query("SELECT u.updatedAt FROM PaymentMethod u WHERE u.user.email = :email")
	LocalDateTime getUpdatedAtByEmail(String email);

	// email ile deletedBy değerini getirir
	@Query("SELECT u.deletedBy FROM PaymentMethod u WHERE u.user.email = :email")
	String getDeletedByByEmail(String email);

	// email ile deletedAt değerini getirir
	@Query("SELECT u.deletedAt FROM PaymentMethod u WHERE u.user.email = :email")
	LocalDateTime getDeletedAtByEmail(String email);

	// email ile version değerini getirir
	@Query("SELECT u.version FROM PaymentMethod u WHERE u.user.email = :email")
	Integer getVersionByEmail(String email);

}
