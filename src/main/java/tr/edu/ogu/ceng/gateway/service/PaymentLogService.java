package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.PaymentLog;
import tr.edu.ogu.ceng.gateway.repository.PaymentLogRepository;

@RequiredArgsConstructor
@Service
public class PaymentLogService {
	
	private PaymentLogRepository paymentLogRepository;
	
	public PaymentLog getPaymentLog(Long id) {
		
		return paymentLogRepository.getReferenceById(id);
	}
}
