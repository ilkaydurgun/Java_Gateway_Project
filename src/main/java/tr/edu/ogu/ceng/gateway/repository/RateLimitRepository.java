package tr.edu.ogu.ceng.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.edu.ogu.ceng.gateway.entity.RateLimit;

public interface RateLimitRepository extends JpaRepository<RateLimit, Long>{

}
