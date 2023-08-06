package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDTO;
import kr.megaptera.assignment.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @DisplayName("")
    @Test
    void test() {
        // given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        postRepository.save(post);

        // when
        postService.update(PostDTO.of("title2", "content2"), 1L);

        // then
        PostDTO actual = postService.get(1L);
        Assertions.assertThat(actual)
                .hasFieldOrPropertyWithValue("title", "title2");

    }

}