package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.PaymentMethod;
import tr.edu.ogu.ceng.gateway.repository.PaymentMethodRepository;

@RequiredArgsConstructor
@Service
public class PaymentMethodService {
	
	private PaymentMethodRepository paymentMethodRepository;
	
	public PaymentMethod getPaymentMethod(Long id) {
		
		return paymentMethodRepository.getReferenceById(id);
	}
}
