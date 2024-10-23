package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.User;
import tr.edu.ogu.ceng.gateway.service.UserService;


@RestController
@RequestMapping
@RequiredArgsConstructor

public class UserController {

	private UserService userService;
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		
		return userService.getUser(id);
	}

}
