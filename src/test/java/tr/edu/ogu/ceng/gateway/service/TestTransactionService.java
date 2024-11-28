package tr.edu.ogu.ceng.gateway.service;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;

public class TestTransactionService {

	@Autowired
	TransactionService transactionService;
	
	@Mock
	TransactionRepository transactionRepository;
	
	
	
}
