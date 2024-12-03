package com.totra.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/login-view")
	public String userInput() {
		return "user/login";
	}
	
	@GetMapping("/join-view")
	public String userJoin() {
		return "user/join";
	}
}
