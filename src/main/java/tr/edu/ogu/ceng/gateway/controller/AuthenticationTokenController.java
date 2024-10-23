package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.AuthenticationToken;
import tr.edu.ogu.ceng.gateway.service.AuthenticationTokenService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationTokenController {

	private AuthenticationTokenService authenticationTokenService;
	
	public AuthenticationToken getAuthenticationToken(@PathVariable Long id) {
		return authenticationTokenService.getAuthenticationToken(id);
	}
}
