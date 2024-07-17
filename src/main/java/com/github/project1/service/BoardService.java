package com.github.project1.service;

import com.github.project1.repository.user.UserEntity;
import com.github.project1.repository.user.UserJpaRepository;
import com.github.project1.web.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final UserJpaRepository userJpaRepository;

    @Transactional(transactionManager = "tmJpa1")
    public String signUpPost(String email, String password) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            userEntity.setUserName("작성자"); // 기본값 설정
            userEntity.setLoginToken(null);
            userEntity.setLoggedIn(false);

            userJpaRepository.save(userEntity);
            System.out.println(userEntity.getEmail());
            // return userEntity;
            return "{\"message:\" \"회원가입이 완료되었습니다.\"}";

        } catch (Exception e){
            log.error("Error saving user: 에러", e);
            throw new RuntimeException("회원가입에 실패했습니다.");
        }
    }
    @Transactional(transactionManager = "tmJpa1")
    public String loginPost(String email, String password) {
        try {
            UserEntity userEntity = userJpaRepository.findByEmailAndPassword(email, password);
            if (userEntity != null){
                userEntity.setLoggedIn(true);
                userEntity.setLoginToken("1");
                userJpaRepository.save(userEntity);
                return "{\"message:\" \"로그인이 성공적으로 완료되었습니다.\"}";
            } else {
                throw new RuntimeException("존재하지 않는 사용자입니다.");
            }
        }catch (Exception e){
            throw new RuntimeException("로그인에 실패하였습니다.");
        }
    }
    @Transactional(transactionManager = "tmJpa1")
    public String logoutPost(String email) {
        try {
            UserEntity userEntity = userJpaRepository.findByEmail(email);
            if (userEntity != null) {
                userEntity.setLoggedIn(false);
                userEntity.setLoginToken("0");
                userJpaRepository.save(userEntity);
                return "{\"message:\" \"로그아웃되었습니다.\"}";
            } else {
                throw new RuntimeException("사용자를 찾을수 없습니다.");
            }
        } catch (Exception e){
            throw new RuntimeException("로그아웃에 실패했습니다.");
        }

    }

}
