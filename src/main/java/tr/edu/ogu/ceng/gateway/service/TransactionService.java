package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Transaction;
import tr.edu.ogu.ceng.gateway.repository.TransactionRepository;

@RequiredArgsConstructor
@Service
public class TransactionService {
	
	private TransactionRepository transactionRepository;
	
	public Transaction getTransaction(Long id) {
		
		return transactionRepository.getReferenceById(id);
	}

}
