package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.services.SettingService;

@RestController
@RequestMapping("/api/v1/setting")
@RequiredArgsConstructor
public class SettingController {
	
	private final SettingService ss;
	
	@GetMapping
	public String getSetting() {
		return "*";
	}
	
}
