package tr.edu.ogu.ceng.gateway.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Setting;
import tr.edu.ogu.ceng.gateway.repository.SettingRepository;

@RequiredArgsConstructor
@Service
public class SettingService {
	private final SettingRepository settingRepository;
	
	
	public Setting getSetting(Long id) {
		
		return settingRepository.getReferenceById(id);
		
	}
	
}