package tr.edu.ogu.ceng.gateway.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.jsonwebtoken.Jwts;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.AuthenticationTokenRepository;
import tr.edu.ogu.ceng.gateway.service.AuthenticationTokenService;

public class TestAuthenticationTokenService {

    @Mock
    private AuthenticationTokenRepository authenticationTokenRepository;

    @InjectMocks
    private AuthenticationTokenService authenticationTokenService;

    private AuthenticationToken authenticationToken;
    private Users user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // Setup Users ve AuthenticationToken nesnelerini
        user = new Users();
        user.setUsername("baristurgut");
        user.setEmail("cihanbaristr@gmail.com");
        user.setRoles("user");
        user.setPassword("12345");

        authenticationToken = new AuthenticationToken();
        authenticationToken.setUser(user);
        authenticationToken.setToken("testtesttesttest");
        authenticationToken.setIssuedAt(LocalDateTime.now());
        authenticationToken.setExpiresAt(LocalDateTime.now().plusHours(10));
        authenticationToken.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testGetTokenByUsername() {
        // Test için mock davranışı belirle
        when(authenticationTokenRepository.getTokenByUsername("baristurgut")).thenReturn("testtesttesttest");

        String token = authenticationTokenService.getTokenByUsername("baristurgut");

        // Token doğru döndü mü kontrol et
        assertEquals("testtesttesttest", token);
    }

    @Test
    void testCreateToken() {
        // Token oluşturma metodunu test et
        String token = authenticationTokenService.createToken("baristurgut");
        System.out.println(token);
        // Token oluşturulmuş mu diye kontrol et
        assertNotNull(token);
        assertThat(token.length()).isGreaterThan(0);   // Token boş olmamalı
    }

    @Test
    void testValidateToken_ValidToken() {
        String validToken = Jwts.builder()
                .setSubject("baristurgut")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 36000000)) // 10 saat geçerlilik
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secret")
                .compact();

        boolean isValid = authenticationTokenService.validateToken(validToken);

        assertTrue(isValid);  // Geçerli token doğru şekilde doğrulanmalı
    }

    @Test
    void testValidateToken_InvalidToken() {
        String invalidToken = "invalidtoken";

        boolean isValid = authenticationTokenService.validateToken(invalidToken);

        assertFalse(isValid);  // Geçersiz token yanlış şekilde doğrulanmalı
    }

    @Test
    void testGetUsernameFromToken() {
        String token = Jwts.builder()
                .setSubject("baristurgut")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 36000000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secret")
                .compact();

        String username = authenticationTokenService.getUsernameFromToken(token);
        System.out.println(username);
        assertEquals("baristurgut", username);  // Token'dan doğru kullanıcı adı alınmalı
    }

    @Test
    void testSaveAuthenticationToken() {
        // Save işlemini mock repository ile test et
        when(authenticationTokenRepository.save(any(AuthenticationToken.class))).thenReturn(authenticationToken);

        AuthenticationToken savedToken = authenticationTokenService.saveAuthenticationToken("baristurgut", "testtesttesttest", "12345", "cihanbaristr@gmail.com");

        // Token veritabanına kaydedildi mi kontrol et
        assertNotNull(savedToken);
        assertEquals("testtesttesttest", savedToken.getToken());
    }

    @Test
    void testGetAuthenticationToken() {
        when(authenticationTokenRepository.getReferenceById(1L)).thenReturn(authenticationToken);

        AuthenticationToken retrievedToken = authenticationTokenService.getAuthenticationToken(1L);

        assertNotNull(retrievedToken);  // Token alınmalı
        assertEquals("baristurgut", retrievedToken.getUser().getUsername());  // Kullanıcı adı doğru olmalı
    }
}
