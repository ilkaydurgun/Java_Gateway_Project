package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.PaymentLog;
import tr.edu.ogu.ceng.gateway.service.PaymentLogService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PaymentLogController {

	private PaymentLogService paymentLogService;
	
	public PaymentLog getPaymentLog(@PathVariable Long id) {
		return paymentLogService.getPaymentLog(id);
	}
}
