package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.service.ApiKeyService;


@RestController
@RequestMapping("/api-key")
@RequiredArgsConstructor
public class ApiKeyController {
	
	private ApiKeyService apiKeyService;
	
	@GetMapping("/{id}")
	public ApiKey getApiKey(@PathVariable Long id) {
		return apiKeyService.getApiKey(id);
	}

}