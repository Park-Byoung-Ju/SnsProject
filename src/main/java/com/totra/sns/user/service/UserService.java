package com.totra.sns.user.service;

import org.springframework.stereotype.Service;

import com.totra.sns.common.SHA256HashingEncoder;
import com.totra.sns.user.domain.User;
import com.totra.sns.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(String loginId
						, String password
						, String name
						, String email
						, String nickname) {
		String encodingPassword = SHA256HashingEncoder.encode(password);
	
		int count = userRepository.insertUser(loginId, encodingPassword, name, email, nickname);
		
		if(count == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean idCheck(String loginId) {
		int count = userRepository.idCheck(loginId);
		
		if(count == 1) {
			return false;
		}else {
			return true;
		}
	}
	
	public User login(String loginId, String password) {
		String encodingPassword = SHA256HashingEncoder.encode(password);
		return userRepository.login(loginId, encodingPassword);
	}
}
