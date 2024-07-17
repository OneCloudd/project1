package com.github.project1.web.controller;

import com.github.project1.repository.post.PostEntity;
import com.github.project1.repository.post.PostRepository;
import com.github.project1.service.exception.InvalidValueException;
import com.github.project1.service.exception.NotFoundException;
import com.github.project1.web.dto.Post;
import com.github.project1.web.dto.PostBody;
import com.github.project1.web.dto.ResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final PostRepository postRepository;

    // 조회
    @GetMapping("/posts")
    public ResponseWrapper findAllPost(){
        List<Post> posts = postRepository.findAllPost();
        ResponseWrapper responseWrappers = new ResponseWrapper(posts);
        log.info(posts.toString());
        return responseWrappers;
    }

    // 작성자로 게시물 검색
    @GetMapping("/posts/search")
    public ResponseEntity findBoardByEmail(@RequestParam("email") String email){
        try {
            List<Post> foundPostByEmail =  postRepository.findPostByEmail(email);
            return new ResponseEntity(foundPostByEmail, HttpStatus.OK);
        } catch(InvalidValueException ive){
            log.info("client 요청 error : " + ive.getMessage());
            return new ResponseEntity(ive.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 게시물 작성
    @PostMapping("/posts")
    public String createPost(@RequestBody PostBody postBody){
        postRepository.createPost(postBody);
        return "게시물이 성공적으로 작성되었습니다.";
    }

//    // 게시물 삭제
    @DeleteMapping("/posts/{title}")
    public String deletePost(@PathVariable String title){
        postRepository.deletePost(title);
        return "제목이 " + title + " 인 게시물이 삭제되었습니다.";
    }

//    // 게시물 수정
    @PutMapping("/posts/{id}")
    public PostEntity updatePost(@RequestBody PostBody postBody, @PathVariable Integer id){
        return postRepository.updatePost(postBody, id).orElseThrow(()-> new NotFoundException("ID가 " + id + "인 user는 없습니다."));
    }
}
