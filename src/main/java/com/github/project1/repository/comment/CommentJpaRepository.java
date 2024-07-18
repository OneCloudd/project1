package com.github.project1.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {
	List<CommentEntity> findByPostId(Integer postId);
}