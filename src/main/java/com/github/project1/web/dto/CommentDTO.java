package com.github.project1.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentDTO {
	private Integer id;
	private String content;
	private String author;
	private Integer postId;
	private LocalDateTime createdAt;
}