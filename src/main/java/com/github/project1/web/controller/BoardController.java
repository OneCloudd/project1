    package com.github.project1.web.controller;

    import com.github.project1.repository.post.PostEntity;
    import com.github.project1.repository.post.PostRepository;
    import com.github.project1.repository.user.UserEntity;
    import com.github.project1.service.BoardService;
    import com.github.project1.web.dto.Post;
    import com.github.project1.web.dto.PostBody;
    import com.github.project1.web.dto.User;
    import lombok.AllArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.web.bind.annotation.*;

    import javax.swing.text.html.parser.Entity;
    import java.util.List;

    @RestController
    @RequestMapping("/api")
    @AllArgsConstructor
    public class BoardController {

        private final PostRepository postRepository;
        private final BoardService boardService;
        // 회원가입
        @PostMapping("/signup")
        public String singUpPost(@RequestBody User user){
            String entity = boardService.signUpPost(user.getEmail(), user.getPassword());
            return entity;
        }
        // 로그인
        @PostMapping("/login")
        public String loginPost(@RequestBody User user){
            String response = boardService.loginPost(user.getEmail(), user.getPassword());
            return response;
        }
        // 로그아웃
        @PostMapping("/logout")
        public ResponseEntity<String> logoutPost(@RequestBody User user){
            String response = boardService.logoutPost(user.getEmail());
            return ResponseEntity.ok(response);
        }

        // 조회
        @GetMapping("/posts")
        public List<Post> findAllPost(){
            return postRepository.findAllPost();
        }

        // 작성자로 게시물 검색
        @GetMapping("/posts/search")
        public List<Post> findBoardByEmail(@RequestParam("email") String email){
            return postRepository.findPostByEmail(email);
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


            return "title이 " + title + "인 게시물이 삭제되었습니다.";
        }

    //    // 게시물 수정
        @PutMapping("/posts/{id}")
        public PostEntity updatePost(@RequestBody PostBody postBody, @PathVariable Integer id){
            return postRepository.updatePost(postBody, id);
        }
    }
