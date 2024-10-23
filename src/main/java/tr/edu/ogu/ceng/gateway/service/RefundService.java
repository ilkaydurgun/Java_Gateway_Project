package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Refund;
import tr.edu.ogu.ceng.gateway.repository.RefundRepository;

@RequiredArgsConstructor
@Service
public class RefundService {
	
	private RefundRepository refundRepository;
	
	public Refund getRefund(Long id) {
		
		return refundRepository.getReferenceById(id);
	}

}
