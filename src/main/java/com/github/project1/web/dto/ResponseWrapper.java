package com.github.project1.web.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper {
    private List<Post> posts;
}
