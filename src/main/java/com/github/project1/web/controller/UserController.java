package com.github.project1.web.controller;


import com.github.project1.service.UserService;
import com.github.project1.web.dto.singupandlogin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public SignupResponse signup(@RequestBody SignupRequest request) {
		userService.signup(request.getEmail(), request.getPassword());
		return new SignupResponse("회원가입이 완료되었습니다.");
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		String token = userService.login(request.getEmail(), request.getPassword());
		return new LoginResponse("로그인이 성공적으로 완료되었습니다.", token);
	}

	@PostMapping("/logout")
	public LogoutResponse logout(@RequestBody LogoutRequest request) {
		userService.logout(request.getEmail());
		return new LogoutResponse("로그아웃 되었습니다.");
	}

}
