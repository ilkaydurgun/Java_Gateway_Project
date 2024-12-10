package tr.edu.ogu.ceng.gateway.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUsername(String username);
    default Boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }
    Users getByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Users getByEmail(String email);
    
    // userid ile user getirilir
    @Query("SELECT u FROM Users u WHERE u.id = :id")
    Optional<Users> findById(@Param("id") Long id);

    // email ile username getirilir
    @Query("SELECT u.username FROM Users u WHERE u.email = :email")
    String getUsernameByEmail(String email);
    
    // username ile role getirilir
    @Query("SELECT u.roles FROM Users u WHERE u.username = :username")
    String getRolesByUsername(String username);
    
    // username ile paymentmethod getirilir
    @Query("SELECT u.paymentMethod FROM Payment u WHERE u.user.username = :username")
    String getPaymentMethodByUsername(String username);
    
    // username ile currency getirilir
    @Query("SELECT u.currency FROM Payment u WHERE u.user.username = :username")
    String getCurrencyByUsername(String username); 
    
    // username ile createdAt getirilir
    @Query("SELECT u.createdAt FROM Users u WHERE u.username = :username")
    LocalDateTime getCreatedAtByUsername(String username); 
  
}

