package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.repository.AuthenticationTokenRepository;

@RequiredArgsConstructor
@Service
public class AuthenticationTokenService {
	
	private AuthenticationTokenRepository authenticationTokenRepository;
	
	public AuthenticationToken getAuthenticationToken(Long id) {
		
		return authenticationTokenRepository.getReferenceById(id);
	}
	

}
