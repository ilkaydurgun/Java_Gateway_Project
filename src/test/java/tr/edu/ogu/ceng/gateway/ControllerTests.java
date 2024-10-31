package tr.edu.ogu.ceng.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import tr.edu.ogu.ceng.gateway.controller.ApiKeyController;
import tr.edu.ogu.ceng.gateway.controller.AuthenticationTokenController;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.service.ApiKeyService;
import tr.edu.ogu.ceng.gateway.service.AuthenticationTokenService;

@RunWith(SpringRunner.class)
@WebMvcTest({ApiKeyController.class, AuthenticationTokenController.class})
@AutoConfigureMockMvc
public class ControllerTests {
	
    @InjectMocks
    private ApiKeyController apiKeyController;

    @Mock
    private ApiKeyService apiKeyService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apiKeyController).build();
    }

    @Test
    public void testApiKeyController() throws Exception {
        // Given (Verilen)
        ApiKey apiKey = new ApiKey(); // Burada ApiKey nesnesini doldurmanız gerekebilir
        Long apiKeyId = 1L; // Long türünde bir ID kullanın
        apiKey.setId(apiKeyId);
        apiKey.setApiKey("gatewayapi");
        apiKey.setCreatedBy("gateway");
        apiKey.setUpdatedBy("me");
        apiKey.setDeletedBy("me"); 
        apiKey.setVersion(1); 
        
        // Mock API Key servisi
        when(apiKeyService.getApiKey(apiKeyId)).thenReturn(apiKey);

        // When (İşlem)
        MvcResult result = mockMvc.perform(get("/api-key/{id}", apiKeyId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn(); // Sonucu al

        // Dönüş değerini al
        String jsonResponse = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson nesnesi
        ApiKey returnedApiKey = objectMapper.readValue(jsonResponse, ApiKey.class); // JSON'dan ApiKey nesnesine çevir

     // Assertions (Doğrulamalar)
        assertNotNull(returnedApiKey);
        assertEquals(apiKeyId, returnedApiKey.getId());
        assertEquals("gateway", returnedApiKey.getCreatedBy());
        assertEquals("me", returnedApiKey.getUpdatedBy());
        assertEquals("me", returnedApiKey.getDeletedBy());
        assertEquals(1, returnedApiKey.getVersion());



        // Verify that the service method was called
        verify(apiKeyService, times(1)).getApiKey(apiKeyId);
    }
    
 // AuthenticationTokenController için test metodu ekliyoruz
    @InjectMocks
    private AuthenticationTokenController authenticationTokenController;

    @Mock
    private AuthenticationTokenService authenticationTokenService;

    @BeforeEach
    public void setUp1() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apiKeyController, authenticationTokenController).build();
    }
    @Test
    public void testAuthenticationTokenController() throws Exception {
        // Given (Verilen)
        AuthenticationToken token = new AuthenticationToken(); 
        Long tokenId = 1L; 
        token.setId(tokenId);
        token.setToken("test-token");
        token.setId(1L); 

        // Mock AuthenticationToken servisi
        when(authenticationTokenService.getAuthenticationToken(tokenId)).thenReturn(token);


        // When
        MvcResult result = mockMvc.perform(get("/auth-token/{id}", tokenId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // 200 bekleniyor
                .andReturn();

        // Assertions
        assertNotNull(result.getResponse().getContentAsString());

        // Verify service call
        verify(authenticationTokenService, times(1)).getAuthenticationToken(tokenId);
       
    }
}
