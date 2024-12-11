package tr.edu.ogu.ceng.gateway.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;
@RequiredArgsConstructor
@Service
public class UsersService {

	private final RestClient restClient;
	private final UsersRepository usersRepository;
	
	public Users getUser(Long id) {
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
	

	public Users createNewUser(Users user) {
	    try {
	        // RestClient kullanımı
	        Users savedUser = restClient.post()// RestClient'in post() metodu
	            .uri("http://192.168.137.195:8007/api/users/create")  // User mikroservisindeki "/create" endpoint'i
	            .accept(MediaType.APPLICATION_JSON)
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(user)  // Gönderilecek veri
	            .retrieve()  // Yanıtı al
	            .body(Users.class);  // Gelen yanıtı Users sınıfına dönüştür
	            

	        // Eğer kullanıcı başarıyla oluşturulduysa
	        if (savedUser != null) {
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
		
	}
}