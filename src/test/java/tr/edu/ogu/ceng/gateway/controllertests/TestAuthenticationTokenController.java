package tr.edu.ogu.ceng.gateway.controllertests;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tr.edu.ogu.ceng.gateway.controller.AuthenticationTokenController;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.service.AuthenticationTokenService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestAuthenticationTokenController {

    @Mock
    AuthenticationTokenService authenticationTokenService;
    
    @InjectMocks
    AuthenticationTokenController authenticationTokenController;

    AuthenticationToken authenticationToken = new AuthenticationToken();
    Users user = new Users();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        
        // Kullanıcı verilerini mock'la
        user.setId(1L);
        user.setCreatedAt(LocalDateTime.now());
        user.setUsername("baristurgut");
        user.setPassword("12345");
        user.setRoles("root");
        user.setEmail("cihanbaristr@gmail.com");

        // Token verisini mock'la
        authenticationToken.setUser(user);
        authenticationToken.setToken("testtesttesttest");
        authenticationToken.setIssuedAt(LocalDateTime.now());
        authenticationToken.setExpiresAt(LocalDateTime.now().plusDays(1)); // Tokenın 1 gün sonra sona ermesi
        authenticationToken.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testGetAuthenticationToken() {
        // Mock service çağrısını belirtin
        when(authenticationTokenService.getAuthenticationToken(anyLong())).thenReturn(authenticationToken);

        // Controller metodunu çağırın
        AuthenticationToken response = authenticationTokenController.getAuthenticationToken(1L);

        // Sonuçları doğrulayın
        assertNotNull(response);
        assertEquals("testtesttesttest", response.getToken());
        verify(authenticationTokenService, times(1)).getAuthenticationToken(1L);
    }

    @Test
    void testGetAuthenticationTokenByUsername() {
        // Mock service çağrısını belirtin
        when(authenticationTokenService.getTokenByUsername(anyString())).thenReturn(authenticationToken.getToken());

        // Controller metodunu çağırın
        String token = authenticationTokenController.getAuthenticationTokenByUsername("baristurgut");

        // Sonuçları doğrulayın
        assertNotNull(token);
        assertEquals("testtesttesttest", token);
        verify(authenticationTokenService, times(1)).getTokenByUsername("baristurgut");
    }

    @Test
    void testGetValidateAuthenticationTokenByUsername() {
        // Mock service çağrısını belirtin
        when(authenticationTokenService.validateToken(anyString())).thenReturn(true);

        // Controller metodunu çağırın
        boolean isValid = authenticationTokenController.getValidateAuthenticationTokenByUsername("testtesttesttest");

        // Sonuçları doğrulayın
        assertTrue(isValid);
        verify(authenticationTokenService, times(1)).validateToken("testtesttesttest");
    }
}
