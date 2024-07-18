package com.github.project1.service;

import com.github.project1.repository.post.PostEntity;
import com.github.project1.repository.post.PostJpaRepository;
import com.github.project1.repository.user.UserEntity;
import com.github.project1.repository.user.UserJpaRepository;
import com.github.project1.service.mapper.PostMapper;
import com.github.project1.web.dto.PostDTO;
import com.github.project1.web.dto.PostsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class PostService {


	private final PostJpaRepository postJpaRepository;
	private final UserJpaRepository userJpaRepository;

	public List<PostDTO> getAllPosts() {
		return postJpaRepository.findAll().stream()
				.map(PostMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}


	public List<PostDTO> getPostsByAuthorEmail(String authorEmail) {
		UserEntity user = userJpaRepository.findByEmail(authorEmail);
		List<PostEntity> posts = postJpaRepository.findByUserId(user.getId());

		return posts.stream()
				.map(PostMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
}
