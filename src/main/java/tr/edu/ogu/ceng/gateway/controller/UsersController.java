package tr.edu.ogu.ceng.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.service.UsersService;


@RestController
@RequestMapping
@RequiredArgsConstructor

public class UsersController {

	private UsersService usersService;
	@GetMapping("/{id}")
	public Users getUser(@PathVariable Long id) {
		
		return usersService.getUser(id);
	}

}
