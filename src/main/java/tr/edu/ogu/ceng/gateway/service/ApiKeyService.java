package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;

@RequiredArgsConstructor
@Service
public class ApiKeyService {
    
    private ApiKeyRepository apiKeyRepository; // final ekleyin

    public ApiKey getApiKey(Long id) {
        return apiKeyRepository.findById(id).orElse(null);
		    }

	
}
