package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.service.AuthenticationTokenService;
import tr.edu.ogu.ceng.gateway.service.UsersService;

@RestController
@RequestMapping("/users") // "/users" yoluyla isteklere cevap verir
@RequiredArgsConstructor
public class UsersController {
// restclient.create 
    private final UsersService usersService;
    private final AuthenticationTokenService authenticationTokenService;
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Users user = usersService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Kullanıcı bulunamazsa 404 döner
        }
        return ResponseEntity.ok(user); // Kullanıcı bulunduysa 200 OK döner
    }
    
    @GetMapping("{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        Users user = usersService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Kullanıcı bulunamazsa 404 döner
        }
        return ResponseEntity.ok(user); // Kullanıcı bulunduysa 200 OK döner
    }
    
    @GetMapping("{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        Users user = usersService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Kullanıcı bulunamazsa 404 döner
        }
        return ResponseEntity.ok(user); // Kullanıcı bulunduysa 200 OK döner
    }
    
 // Kullanıcı oluşturma isteği
    @PostMapping("/users/create")
    public ResponseEntity<Users> createNewUser(@RequestBody Users user) {
        // API Gateway, gelen veriyi User mikroservisine iletmek için service metodunu çağırır
        Users createdUser = usersService.createNewUser(user);
        // Kullanıcı oluşturulduktan sonra authentication token'ı oluşturuyoruz
        String token = authenticationTokenService.createToken(createdUser.getUsername());

        // Authentication token'ı veritabanına kaydediyoruz
        AuthenticationToken authenticationToken = authenticationTokenService.saveAuthenticationToken(createdUser.getUsername(), token, createdUser.getPassword(),createdUser.getEmail());

        
        return ResponseEntity.ok(createdUser); // Kullanıcı başarıyla oluşturulursa 200 döner
    }
    
    @PostMapping("/users/login")
    public ResponseEntity<String> loginUser(@RequestBody String username) {
        // 1. Username ile kullanıcıyı buluyoruz
        Users user = usersService.getUserByUsername(username);  // Kullanıcıyı veritabanından al

        if (user != null) {
            // 2. Kullanıcı bulunduysa, token'ını alıyoruz
            String authenticationToken = authenticationTokenService.getTokenByUsername(username);

            if (authenticationToken != null && authenticationTokenService.validateToken(authenticationToken)) {
                // 3. Token geçerli ise giriş başarılı
                return ResponseEntity.ok("Giriş başarılı!");
            } else {
                // 4. Token geçersiz veya yoksa hata döndürüyoruz
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz veya eksik token!");
            }
        } else {
            // 5. Kullanıcı bulunamadıysa hata döndürüyoruz
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz kullanıcı!");
        }
    }
    
}
