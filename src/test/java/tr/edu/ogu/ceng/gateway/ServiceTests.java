package tr.edu.ogu.ceng.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.service.ApiKeyService;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class) // Mockito için uzantı
public class ServiceTests {
	
static	PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));
	
	
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
