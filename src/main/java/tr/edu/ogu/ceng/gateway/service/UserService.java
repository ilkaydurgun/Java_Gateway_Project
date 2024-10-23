package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.User;
import tr.edu.ogu.ceng.gateway.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

	private UserRepository userRepository;
	
	public User getUser(Long id) {
		
		return userRepository.getReferenceById(id);
	}
}