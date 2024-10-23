package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Log;
import tr.edu.ogu.ceng.gateway.service.LogService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LogController {
	
	private LogService logService;
	
	
	public Log getLog(@PathVariable Long id) {
		return logService.getLog(id);
	}
}
