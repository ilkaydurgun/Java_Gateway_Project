package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Refund;
import tr.edu.ogu.ceng.gateway.service.RefundService;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class RefundController {

	private RefundService refundService;
	
	public Refund getRefund(@PathVariable Long id) {
		return refundService.getRefund(id);
		
	}
}
