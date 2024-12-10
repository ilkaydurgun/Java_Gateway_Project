package tr.edu.ogu.ceng.gateway.service;

import java.time.LocalDateTime;
import java.util.Optional;

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
	        throw new IllegalArgumentException("Username cannot be null or empty");
	    }
	    return usersRepository.getByUsername(username);
	                          
	}
	
	public LocalDateTime getUserCreatedTimeByUsername(String username) {
		if (username == null || username.trim().isEmpty()) {
	        throw new IllegalArgumentException("Username cannot be null or empty");
	    }
		return usersRepository.getCreatedAtByUsername(username);	
	}
	
	public Users getUserByEmail(String email){
		if (email == null || email.trim().isEmpty()) {
	        throw new IllegalArgumentException("Email cannot be null or empty");
	    }
		return usersRepository.getByEmail(email);
	}
	

	public Users createNewUser(Users user) {
	    // WebClient kullanarak User mikroservisine POST isteği gönderilir
	    Users savedUser = restClient.post()
	            .uri("http://192.168.137.195:8007/api/users/create")  // User mikroservisindeki "/create" endpoint'i
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
	            .retrieve()
	            .body(Users.class);  // Gelen veriyi Users sınıfına dönüştürür
	    // Kullanıcıyı veritabanına kaydediyoruz
	    usersRepository.save(savedUser);

	    System.out.println(savedUser);  // Kaydedilen kullanıcıyı konsola yazdırıyoruz
	    return savedUser;  // Kaydedilen kullanıcıyı geri döndürüyoruz
	}

	
}