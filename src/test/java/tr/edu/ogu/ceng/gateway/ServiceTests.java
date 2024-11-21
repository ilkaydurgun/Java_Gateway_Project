package tr.edu.ogu.ceng.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.service.ApiKeyService;

import java.util.Optional;

@SpringBootTest
public class ServiceTests {
	
	static PostgreSQLContainer<?> postgres = 
	      	new PostgreSQLContainer<>("postgres:15-alpine");	
	
    @Mock
    private ApiKeyRepository apiKeyRepository; // Mock nesnesi

    @InjectMocks
    private ApiKeyService apiKeyService; // Test edilen sınıf

    private ApiKey testApiKey; // Test için kullanılacak nesne

    @BeforeEach
    public void setUp() {
        testApiKey = new ApiKey();
        testApiKey.setId(1L); // Örnek ID
        testApiKey.setApiKey("test-api-key"); // Örnek API anahtarı
        testApiKey.setCreatedBy("testUser"); // Örnek oluşturucu
    }

    @Test
    public void testFindApiKeyByIdService() {
        // API anahtarını bulma işlemi
        when(apiKeyRepository.findById(testApiKey.getId())).thenReturn(Optional.of(testApiKey));
        ApiKey foundApiKey = apiKeyService.getApiKey(testApiKey.getId());

        assertNotNull(foundApiKey);
        assertEquals("test-api-key", foundApiKey.getApiKey());
        
    }
}
