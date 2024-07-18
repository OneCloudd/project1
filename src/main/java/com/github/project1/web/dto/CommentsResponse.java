package com.github.project1.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommentsResponse {
	private List<CommentDTO> comments;
}
