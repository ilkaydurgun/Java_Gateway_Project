package tr.edu.ogu.ceng.gateway.servicetests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.github.dockerjava.api.model.ContainerSpecConfig;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.service.ApiKeyService;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class) // Mockito için uzantı
@Testcontainers
public class ServiceTests {
	
	@org.testcontainers.junit.jupiter.Container
	@ServiceConnection
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
