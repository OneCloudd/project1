package com.github.project1.web.dto.singupandlogin;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	private String email;
	private String password;

}
