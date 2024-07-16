package com.github.project1.service;


import com.github.project1.repository.user.UserEntity;
import com.github.project1.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserJpaRepository userJpaRepository;

	public void signup(String email, String password) {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		userJpaRepository.save(userEntity);
	}

	public String login(String email, String password) {
		UserEntity userEntity = userJpaRepository.findByEmailAndPassword(email, password);
		if (userEntity != null) {
			userEntity.setLoggedIn(true);
			String token = UUID.randomUUID().toString();
			userEntity.setLoginToken(token);
			userJpaRepository.save(userEntity);
			return token;
		}
		throw new RuntimeException("로그인 실패!");
	}

	public void logout(String email) {
		UserEntity userEntity = userJpaRepository.findByEmail(email);
		if (userEntity != null) {
			userEntity.setLoggedIn(false);
			userEntity.setLoginToken(null);
			userJpaRepository.save(userEntity);
		}else {
			throw new RuntimeException("로그아웃 실패!");
		}
	}
}
