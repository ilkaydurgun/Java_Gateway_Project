package tr.edu.ogu.ceng.gateway.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.ApiKey;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey,Long>{


	
}