package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.service.TransactionService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TransactionController {
	private TransactionService transactionService;
	
	public Transaction getTransaction(@PathVariable Long id) {
		return transactionService.getTransaction(id);
	}

}
