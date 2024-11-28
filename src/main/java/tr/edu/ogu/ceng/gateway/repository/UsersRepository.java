package tr.edu.ogu.ceng.gateway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUsername(String username);
    
    
    Optional<Users> getByUsername(String username);
    
    @Query("SELECT u FROM users")
    Optional <List<Users>> getAllUsers();
    

}