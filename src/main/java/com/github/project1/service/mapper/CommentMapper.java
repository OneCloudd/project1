package com.github.project1.service.mapper;

import com.github.project1.repository.comment.CommentEntity;
import com.github.project1.web.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

	@Mapping(target = "id", source = "commentEntity.commentId")
	@Mapping(target = "content", source = "commentEntity.content")
	@Mapping(target = "author", source = "commentEntity.author")
	@Mapping(target = "postId", source = "commentEntity.postId")
	@Mapping(target = "createdAt", source = "commentEntity.createdAt")
	CommentDTO toDto(CommentEntity commentEntity);

	@Mapping(target = "commentId", source = "commentDTO.id")
	@Mapping(target = "content", source = "commentDTO.content")
	@Mapping(target = "author", source = "commentDTO.author")
	@Mapping(target = "postId", source = "commentDTO.postId")
	@Mapping(target = "createdAt", source = "commentDTO.createdAt")
	CommentEntity toEntity(CommentDTO commentDTO);
}