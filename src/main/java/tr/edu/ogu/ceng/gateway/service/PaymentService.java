package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.repository.PaymentRepository;

@RequiredArgsConstructor
@Service
public class PaymentService {
	
	private PaymentRepository paymentRepository;
	
	public Payment getPayment(Long id) {
		
		return paymentRepository.getReferenceById(id);
	}

}
