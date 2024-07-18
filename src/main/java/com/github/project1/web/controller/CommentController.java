package com.github.project1.web.controller;

import com.github.project1.service.CommentService;
import com.github.project1.web.dto.CommentDTO;
import com.github.project1.web.dto.CommentsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {


	private final CommentService commentService;

	@GetMapping("/comments")
	public ResponseEntity<CommentsResponse> getAllComments() {
		List<CommentDTO> commentDTOs = commentService.getAllComments();
		return ResponseEntity.ok(new CommentsResponse(commentDTOs));
	}

	@GetMapping("/comments/search")
	public ResponseEntity<CommentsResponse> getCommentsByPostId(@RequestParam Integer postId) {
		List<CommentDTO> comments = commentService.getCommentsByPostId(postId);
		return ResponseEntity.ok(new CommentsResponse(comments));
	}
}
