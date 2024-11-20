package tr.edu.ogu.ceng.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.edu.ogu.ceng.gateway.controller.ApiKeyController;
import tr.edu.ogu.ceng.gateway.controller.AuthenticationTokenController;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.service.ApiKeyService;
import tr.edu.ogu.ceng.gateway.service.AuthenticationTokenService;

@SpringBootTest
@WebMvcTest({ApiKeyController.class, AuthenticationTokenController.class})
public class ControllerTests {

    @InjectMocks
    private ApiKeyController apiKeyController;

    @Mock
    private ApiKeyService apiKeyService;

    @InjectMocks
    private AuthenticationTokenController authenticationTokenController;

    @Mock
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApiKeyController() throws Exception {
        // Given
        ApiKey apiKey = new ApiKey();
        Long apiKeyId = 1L;
        apiKey.setId(apiKeyId);
        apiKey.setApiKey("gatewayapi");
        apiKey.setCreatedBy("gateway");
        apiKey.setUpdatedBy("me");
        apiKey.setDeletedBy("me");
        apiKey.setVersion(1);

        // Mock the ApiKey service
        when(apiKeyService.getApiKey(apiKeyId)).thenReturn(apiKey);

        // When
        MvcResult result = mockMvc.perform(get("/api-key/{id}", apiKeyId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Parse the response
        String jsonResponse = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ApiKey returnedApiKey = objectMapper.readValue(jsonResponse, ApiKey.class);

        // Assertions
        assertNotNull(returnedApiKey);
        assertEquals(apiKeyId, returnedApiKey.getId());
        assertEquals("gateway", returnedApiKey.getCreatedBy());
        assertEquals("me", returnedApiKey.getUpdatedBy());
        assertEquals("me", returnedApiKey.getDeletedBy());
        assertEquals(1, returnedApiKey.getVersion());

        // Verify service method call
        verify(apiKeyService, times(1)).getApiKey(apiKeyId);
    }

    @Test
    public void testAuthenticationTokenController() throws Exception {
        // Given
        AuthenticationToken token = new AuthenticationToken();
        Long tokenId = 1L;
        token.setId(tokenId);
        token.setToken("test-token");

        // Mock AuthenticationToken service
        when(authenticationTokenService.getAuthenticationToken(tokenId)).thenReturn(token);

        // When
        MvcResult result = mockMvc.perform(get("/auth-token/{id}", tokenId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assertions
        assertNotNull(result.getResponse().getContentAsString());

        // Verify service method call
        verify(authenticationTokenService, times(1)).getAuthenticationToken(tokenId);
    }
}
