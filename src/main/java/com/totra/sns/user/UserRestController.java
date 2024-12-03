package com.totra.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.totra.sns.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	private UserService userSerivce;
	
	public UserRestController(UserService userService) {
		this.userSerivce = userService;
	}
	
	@PostMapping("/join")
	public Map<String, Boolean> joinCreate(
								@RequestParam("loginId") String loginId
								, @RequestParam("password") String password
								, @RequestParam("name") String name
								, @RequestParam("email") String email
								, @RequestParam("nickname") String nickname){
		Map<String, Boolean> resultMap = new HashMap<>();
		
		boolean result = userSerivce.addUser(loginId, password, nickname, email, nickname);
		
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	@GetMapping("/join/duplicate-id")
	public boolean joinIdCheck(@RequestParam("loginId") String loginId) {
		return userSerivce.idCheck(loginId);		
	}
}
