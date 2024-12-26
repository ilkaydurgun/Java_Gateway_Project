package tr.edu.ogu.ceng.gateway.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import tr.edu.ogu.ceng.gateway.dto.UsersDto;
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
    
    // Kullanıcıdan gelen getuser isteğini user mikroservisine yönlendirir ve oradan gelen isteği kullanıcıya döndürür
    @GetMapping("/{username}")
    public ResponseEntity<UsersDto> getUser(@PathVariable String username) {
        try {
            UsersDto user = usersService.getUser(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }
    
    // Kullanıcı oluşturma isteği
    @PostMapping("/create")
    public ResponseEntity<Users> createNewUser(@RequestBody Users user) {
    	
    	try {
        // API Gateway, gelen veriyi User mikroservisine iletmek için service metodunu çağırır
        Users createdUser = usersService.createNewUser(user);
        
        return ResponseEntity.ok(createdUser); // Kullanıcı başarıyla oluşturulursa 200 döner
    	}
    	catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }
    
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<UsersDto> users = usersService.getAllUsers();
        List<UsersDto> usersDTO = users.stream()
                .map(user -> modelMapper.map(users, UsersDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usersDTO);
    }
    

    //Bu kısım karşı mikroservisten veri alınmayıp gateway mikroservisinin
    //kendinden yanıt döndüren metodları barındırıp proje amacı dışında olduğu için yorum satırına alınmıştır. 
    /* 
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Users user = usersService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Kullanıcı bulunamazsa 404 döner
        }
        return ResponseEntity.ok(user); // Kullanıcı bulunduysa 200 OK döner
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        Users user = usersService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Kullanıcı bulunamazsa 404 döner
        }
        return ResponseEntity.ok(user); // Kullanıcı bulunduysa 200 OK döner
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        Users user = usersService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Kullanıcı bulunamazsa 404 döner
        }
        return ResponseEntity.ok(user); // Kullanıcı bulunduysa 200 OK döner
    }
    
    
    
    @GetMapping("/users/{username}")
    public ResponseEntity<String> loginUser(@PathVariable String username) {
        // 1. Username ile kullanıcıyı kendi veritabanımızdan buluyoruz
        // Users user = usersService.getUserByUsername(username);  
    	Users user =usersService.getUserByUsernameFromUserMicroservice(username);
        if (user != null) {
            //  Kullanıcı bulunduysa, token'ını alıyoruz. Bu tokeni kullanıcıyı yaratırken kendi veritabanımıza kaydetmiştik.
            String authenticationToken = authenticationTokenService.getTokenByUsername(username);

            if (authenticationToken != null && authenticationTokenService.validateToken(authenticationToken)) {
                // Token geçerli ise giriş başarılı
                return ResponseEntity.ok("Giriş başarılı!");
            } else {
                // Token geçersiz veya yoksa hata döndürüyoruz
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz veya eksik token!");
            }
        } else {
            // Kullanıcı bulunamadıysa hata döndürüyoruz
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Geçersiz kullanıcı!");
        }
    }
    */
}
