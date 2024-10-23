package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.service.PaymentService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PaymentController {

	private PaymentService paymentService ;
	
	public Payment getPayment(@PathVariable Long id) {
		return paymentService.getPayment(id);
	}
}
