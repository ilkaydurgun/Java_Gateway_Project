package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@RequiredArgsConstructor
@Service
public class UsersService {

	private UsersRepository userRepository;
	
	public Users getUser(Long id) {
		
		return userRepository.getReferenceById(id);
	}
}