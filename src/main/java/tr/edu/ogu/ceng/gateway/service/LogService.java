package tr.edu.ogu.ceng.gateway.service;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Log;
import tr.edu.ogu.ceng.gateway.repository.LogRepository;

@RequiredArgsConstructor
@Service
public class LogService {
	
	private LogRepository logRepository;
	
	public Log getLog(Long id) {
		
		return logRepository.getReferenceById(id);
	}

}
