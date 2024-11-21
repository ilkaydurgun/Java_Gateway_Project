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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import tr.edu.ogu.ceng.gateway.controller.ApiKeyController;
import tr.edu.ogu.ceng.gateway.controller.AuthenticationTokenController;
import tr.edu.ogu.ceng.gateway.controller.PaymentController;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.entity.Payment;
import tr.edu.ogu.ceng.gateway.service.ApiKeyService;
import tr.edu.ogu.ceng.gateway.service.AuthenticationTokenService;
import tr.edu.ogu.ceng.gateway.service.PaymentService;

@SpringBootTest
@WebMvcTest({ApiKeyController.class, AuthenticationTokenController.class})
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

 // AuthenticationTokenController için test metodu ekliyoruz
    @InjectMocks
    private AuthenticationTokenController authenticationTokenController;

    @Mock
    private AuthenticationTokenService authenticationTokenService;
    
    @Autowired
	private MockMvc mockMvc1;
    
    @BeforeEach
    public void setUp1() {
    	
        MockitoAnnotations.openMocks(this);
        mockMvc1 = MockMvcBuilders.standaloneSetup(apiKeyController, authenticationTokenController).build();
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
        MvcResult result = mockMvc1.perform(get("/auth-token/{id}", tokenId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assertions
        assertNotNull(result.getResponse().getContentAsString());

        // Verify service method call
        verify(authenticationTokenService, times(1)).getAuthenticationToken(tokenId);
    }
    
    @InjectMocks
    private PaymentController paymentController;
    
    @Mock
    private PaymentService paymentService;
    
    @Autowired
    private MockMvc mockMvc2;
    
    @BeforeEach
    public void setup2() {
    	MockitoAnnotations.openMocks(this);
    	mockMvc2=MockMvcBuilders.standaloneSetup(apiKeyController,paymentController).build();
    }
    @Test
    public void testPaymentController() throws Exception{
    	
    	// Given
    	Payment payment=new Payment();
        Long paymentId = 1L; 

    	payment.setId(paymentId);
    	payment.setOrderId(1L);
    	payment.setAmount(new BigDecimal("1231312312"));
    	payment.setCurrency("dolar");
    	payment.setPaymentMethod("credit");
    	payment.setStatus("OK");   	
    	payment.setCreatedAt(LocalDateTime.now());
    	payment.setUpdatedAt(LocalDateTime.now());

        when(paymentService.getPayment(paymentId)).thenReturn(payment);

     // When
        MvcResult result = mockMvc2.perform(get("/payment/{id}", paymentId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // 200 bekleniyor
                .andReturn();

        // Assertions
        assertNotNull(result.getResponse().getContentAsString());

        // Verify service call
        verify(paymentService, times(1)).getPayment(paymentId);
    	
    	
    	
    }
    
    
    
    
    
}
