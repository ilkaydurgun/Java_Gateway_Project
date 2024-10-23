package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.PaymentMethod;
import tr.edu.ogu.ceng.gateway.service.PaymentMethodService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PaymentMethodController {

	
	private PaymentMethodService paymentMethodService;
	
	public PaymentMethod getPaymentMethod(@PathVariable Long id) {
		return paymentMethodService.getPaymentMethod(id);
	}
}
