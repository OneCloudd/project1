package com.github.project1.web.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class PostsResponse {
    private List<PostDTO> posts;
}