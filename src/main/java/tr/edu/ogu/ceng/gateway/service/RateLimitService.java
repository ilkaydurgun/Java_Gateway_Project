package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.repository.RateLimitRepository;

@RequiredArgsConstructor
@Service
public class RateLimitService {
	
	private RateLimitRepository rateLimitRepository;
	
	public RateLimit getRatelimit(Long id) {
		
		return rateLimitRepository.getReferenceById(id);
				}

}
