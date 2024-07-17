package com.github.project1.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.project1.repository.post.PostEntity;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Post {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private String createdAt;

    public Post(PostBody postBody){
        this.title = postBody.getTitle();
        this.content = postBody.getContent();
        this.author = postBody.getAuthor();
    }

    public Post(PostEntity postEntity){
        this.id = postEntity.getId();
        this.title = postEntity.getTitle() != null ? postEntity.getTitle() : "";
        this.content = postEntity.getContent() !=null ? postEntity.getContent() : "";
        this.author = postEntity.getAuthor() != null ? postEntity.getAuthor() : "";
        this.createdAt = postEntity.getCreatedAt().toString() !=null ? postEntity.getCreatedAt().toString() : "";
    }
}
