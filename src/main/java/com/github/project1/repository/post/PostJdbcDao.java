package com.github.project1.repository.post;

import com.github.project1.service.exception.InvalidValueException;
import com.github.project1.service.exception.NotFoundException;
import com.github.project1.web.dto.Post;
import com.github.project1.web.dto.PostBody;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PostJdbcDao implements PostRepository{

    private JdbcTemplate jdbcTemplate;

    static RowMapper<PostEntity> postEntityRowMapper = ((rs, rowNum) ->
            new PostEntity(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getNString("title"),
                    rs.getNString("author"),
                    rs.getDate("created_at"),
                    rs.getNString("content")
            ));

    @Override
    public List<Post> findAllPost() {
        List<PostEntity> postEntities = jdbcTemplate.query("SELECT * FROM post", postEntityRowMapper);
        return postEntities.stream().map(Post::new).collect(Collectors.toList());
    }

    @Override
    public List<Post> findPostByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new InvalidValueException("이메일을 입력해주세요");
        } else if (!email.contains("@")) {
            throw new InvalidValueException("이메일 형식이 올바르지 않습니다.");
        }

        List<PostEntity> postEntities = jdbcTemplate.query("SELECT post.id, post.user_id, post.title, post.author, post.created_at, post.content FROM post LEFT JOIN users ON users.id=post.user_id WHERE users.email =?", postEntityRowMapper, email);

        if (postEntities.isEmpty()) {
            throw new NotFoundException("이메일이 " + email + "인 user가 없습니다.");
        }

        return postEntities.stream().map(Post::new)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostBody postBody) {
        jdbcTemplate.update("INSERT INTO post(title, content, author) VALUES (?, ?, ?)",
        postBody.getTitle(), postBody.getContent(), postBody.getAuthor());
    }

    @Override
    public void deletePost(String title) {
        if (jdbcTemplate.update("DELETE FROM post WHERE title = ?", title) == 0){
            throw new NotFoundException("제목이" + title + "인 게시물은 존재하지 않습니다.");
        } else {
            jdbcTemplate.update("DELETE FROM post WHERE title = ?", title);
        }
    }

    @Override
    public Optional<PostEntity> updatePost(PostBody postBody, Integer id) {
        jdbcTemplate.update("UPDATE post SET title = ?, content = ? WHERE id = ?", postBody.getTitle(), postBody.getContent(), id);
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM post WHERE id = ?", postEntityRowMapper, id));
        } catch(Exception e){
            return Optional.empty();
        }
    }
}

