package tr.edu.ogu.ceng.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.edu.ogu.ceng.gateway.entity.PaymentLog;
@Repository
public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {

}
