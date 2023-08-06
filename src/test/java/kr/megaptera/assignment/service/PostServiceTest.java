package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDTO;
import kr.megaptera.assignment.exception.NoSuchPostIdException;
import kr.megaptera.assignment.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostServiceTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @DisplayName("게시글을 수정한다")
    @Test
    void update() {
        // given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        Post savedPost = postRepository.save(post);

        // when
        postService.update(PostDTO.of("title2", "content2"), savedPost.getId());

        // then
        PostDTO actual = postService.get(savedPost.getId());
        assertThat(actual)
                .hasFieldOrPropertyWithValue("title", "title2")
                .hasFieldOrPropertyWithValue("content", "content2");

    }

    @DisplayName("게시글을 삭제한다")
    @Test
    void delete() throws Exception {
        // Given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        Post savedPost = postRepository.save(post);

        // When
        postService.delete(savedPost.getId());

        // Then
        assertThrows(NoSuchPostIdException.class, () -> postService.get(savedPost.getId()));
    }
}