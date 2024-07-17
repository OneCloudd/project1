package com.github.project1.repository.user;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "email",length = 255, nullable = false)
    private String email;
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;
    @Column(name = "login_token", length = 255)
    private String loginToken;
    @Column(name = "is_logged_in")
    private boolean isLoggedIn;
}

