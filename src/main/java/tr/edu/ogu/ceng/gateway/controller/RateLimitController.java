package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.RateLimit;
import tr.edu.ogu.ceng.gateway.service.RateLimitService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RateLimitController {

	private RateLimitService rateLimitService;
	
	public RateLimit geRateLimit(@PathVariable Long id) {
		return rateLimitService.getRatelimit(id);
	}
}
