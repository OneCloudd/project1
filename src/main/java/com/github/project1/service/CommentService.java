package com.github.project1.service;

import com.github.project1.repository.comment.CommentJpaRepository;
import com.github.project1.service.mapper.CommentMapper;
import com.github.project1.web.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentJpaRepository commentJpaRepository;

	public List<CommentDTO> getAllComments() {
		return commentJpaRepository.findAll().stream()
				.map(CommentMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	public List<CommentDTO> getCommentsByPostId(Integer postId) {
		return commentJpaRepository.findByPostId(postId).stream()
				.map(CommentMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
}