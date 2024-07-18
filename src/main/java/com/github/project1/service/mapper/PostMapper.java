package com.github.project1.service.mapper;

import com.github.project1.repository.post.PostEntity;
import com.github.project1.web.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

	@Mapping(target = "id", source = "postId")
	@Mapping(target = "title", source = "title")
	@Mapping(target = "content", source = "content")
	@Mapping(target = "author", source = "author")
	@Mapping(target = "createdAt", source = "createdAt")
	PostDTO toDto(PostEntity postEntity);

	@Mapping(target = "postId", source = "id")
	@Mapping(target = "title", source = "title")
	@Mapping(target = "content", source = "content")
	@Mapping(target = "author", source = "author")
	@Mapping(target = "createdAt", source = "createdAt")
	PostEntity toEntity(PostDTO postDTO);
}
