package tr.edu.ogu.ceng.gateway.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.dto.UsersDto;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;
@RequiredArgsConstructor
@Service
public class UsersService {

	private final RestClient restClient;
	private final AuthenticationTokenService authenticationTokenService;
	
	

	
	public UsersDto getUser(String username) {
	    String userMicroServiceUrl = "http://192.168.137.195:8007/api/users/getUserByUsername/" + username; // User mikroservisinin getUser url'si
	    
	    // User mikroservisinden kullanıcı yanıtı dönmeli
	    return restClient.get()
	                     .uri(userMicroServiceUrl)
	                     .retrieve()
	                     .body(UsersDto.class);
	                     
	}
	
	public List<UsersDto> getAllUsers() {
	    String userMicroServiceUrl = "http://192.168.137.195:8007/api/users/getAllUsers/" ; // User mikroservisinin getAllUsers url'si
	    
	    // User mikroservisinden kullanıcı yanıtı dönmeli
	    return restClient.get()
                .uri(userMicroServiceUrl)
                .retrieve()
                .body(new ParameterizedTypeReference<List<UsersDto>>() {});
	                     
	}

	public Users createNewUser(Users user) {
	    try {
	        // RestClient kullanımı
		    String userMicroServiceUrl = "http://192.168.137.195:8007/api/users/createUser"; // User mikroservisinin createUser URL'si

	    	
	        Users savedUser = restClient.post()// RestClient'in post() metodu
	            .uri(userMicroServiceUrl)  // User mikroservisindeki "/create" endpoint'i
	            .accept(MediaType.APPLICATION_JSON)
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(user)  // Gönderilecek veri
	            .retrieve()  // Yanıtı al
	            .body(Users.class);  // Gelen yanıtı Users sınıfına dönüştür
	            
	        // Eğer kullanıcı başarıyla oluşturulduysa
	        if (savedUser != null) {
	        	// Kullanıcı oluşturulduktan sonra authentication token'ı oluşturuyoruz
	            String token = authenticationTokenService.createToken(savedUser.getUsername());

	            // Authentication token'ı veritabanına kaydediyoruz
	            AuthenticationToken authenticationToken = authenticationTokenService.saveAuthenticationToken(savedUser.getUsername(), token, savedUser.getPassword(),savedUser.getEmail());

	            return savedUser; // Oluşturulan kullanıcıyı döndür
	        }
	        return null;  // Kullanıcı oluşturulamadıysa null döndür

	    } catch (Exception e) {
	        // Hata durumunda loglama yapılabilir
	        System.err.println("Kullanıcı uzak bilgisayarda yaratılırken hata oluştu: " + e.getMessage());
	        e.printStackTrace();  // Hata hakkında daha fazla bilgi
	        return null;  // Hata durumunda null döndür
	    }
	}
	
	
	//Bu kısım karşı mikroservisten veri alınmayıp gateway mikroservisinin
    //kendinden yanıt döndüren metodları barındırıp proje amacı dışında olduğu için yorum satırına alınmıştır. 
	/*public Users getUser(Long id) {
	    return usersRepository.findById(id).orElse(null); // Kullanıcı bulunamazsa null döner
	}
	public Users save(Users user) {	
		return usersRepository.save(user);
	}
	
	public Users getUserByUsername(String username){	
		if (username == null || username.trim().isEmpty()) {
	        throw new IllegalArgumentException("Username boş olamaz");
	    }
	    return usersRepository.getByUsername(username);
	                          
	}
	
	public LocalDateTime getUserCreatedTimeByUsername(String username) {
		if (username == null || username.trim().isEmpty()) {
	        throw new IllegalArgumentException("Username boş olamaz");
	    }
		return usersRepository.getCreatedAtByUsername(username);	
	}
	
	public Users getUserByEmail(String email){
		if (email == null || email.trim().isEmpty()) {
	        throw new IllegalArgumentException("Email boş olamaz");
	    }
		return usersRepository.getByEmail(email);
	}
	

	

	public Users getUserByUsernameFromUserMicroservice(String username) {
		try {
		return restClient.get().uri("http://192.168.137.195:8007/api/users/{username}")
				.accept(org.springframework.http.MediaType.APPLICATION_JSON)
				.retrieve()
				.body(Users.class);
		
	} catch (Exception e) {
        // Hata durumunda loglama yapılabilir
        System.err.println("Kullanıcı uzak bilgisayardan getirilirken hata oluştu: " + e.getMessage());
        return null;
    }
		
	}*/
}