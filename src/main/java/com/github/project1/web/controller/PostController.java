package com.github.project1.web.controller;

import com.github.project1.service.PostService;
import com.github.project1.web.dto.PostDTO;
import com.github.project1.web.dto.PostsResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private final PostService postService;

	@GetMapping("/posts")
	public ResponseEntity<PostsResponse> getAllPosts() {
		List<PostDTO> postDTOs = postService.getAllPosts();
		logger.info("Returning posts: {}", postDTOs);
		return ResponseEntity.ok(new PostsResponse(postDTOs));
	}

	@GetMapping("/posts/search")
	public ResponseEntity<PostsResponse> getPostsByAuthorEmail(@RequestParam String authorEmail) {
		List<PostDTO> posts = postService.getPostsByAuthorEmail(authorEmail);
		return ResponseEntity.ok(new PostsResponse(posts));
	}
}