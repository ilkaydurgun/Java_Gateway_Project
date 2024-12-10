package tr.edu.ogu.ceng.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.AuthenticationTokenRepository;

@Service
public class AuthenticationTokenService {

    private final AuthenticationTokenRepository authenticationTokenRepository;
    private final String secretKey = "secret"; // Güvenli bir anahtar kullanın (gerçek bir projede farklı olmalı)

    @Autowired
    public AuthenticationTokenService(AuthenticationTokenRepository authenticationTokenRepository) {
        this.authenticationTokenRepository = authenticationTokenRepository;
    }

    public String getTokenByUsername(String username) {
    	return authenticationTokenRepository.getTokenByUsername(username);
    }
    
    // JWT Token oluşturma
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);  // JWT subject olarak kullanıcı adı set edilir

        // Token geçerlilik süresi (örneğin 10 saat)
        Date now = new Date();
        Date validity = new Date(now.getTime() + 36000000);  // 10 saat geçerlilik

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Secret key ile imzalanır
                .compact();
    }

    // Token doğrulama
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;  // Token geçersizse false döndürülür
        }
    }

    // Token'dan kullanıcı adı almak
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Yeni bir AuthenticationToken oluşturma ve veritabanına kaydetme
    public AuthenticationToken saveAuthenticationToken(String username, String token,String password,String email) {
        AuthenticationToken authenticationToken = new AuthenticationToken();
        Users users=new Users();
        users.setUsername(username);
        users.setEmail(email);
        users.setRoles("user");
        users.setPassword(password);
        authenticationToken.setUser(users);
        authenticationToken.setToken(token);
        return authenticationTokenRepository.save(authenticationToken);  // Token veritabanına kaydedilir
    }

    // AuthenticationToken ID'ye göre almak
    public AuthenticationToken getAuthenticationToken(Long id) {
        return authenticationTokenRepository.getReferenceById(id);
    }
}
