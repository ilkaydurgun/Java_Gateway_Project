package tr.edu.ogu.ceng.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tr.edu.ogu.ceng.gateway.entity.ApiKey;

@ExtendWith(MockitoExtension.class) 
public class EntityTests {

	@Test
    public void testApiKeyEntity() {
        ApiKey apiKey = new ApiKey();
        
        // Set properties
        apiKey.setId(1L);
        apiKey.setApiKey("testApiKey");
        apiKey.setCreatedBy("admin");
        apiKey.setVersion(1);

        // Assertions
        assertNotNull(apiKey);
        assertEquals(1L, apiKey.getId());
        assertEquals("testApiKey", apiKey.getApiKey());
        assertEquals("admin", apiKey.getCreatedBy());
        assertEquals(1, apiKey.getVersion());
    }

}
