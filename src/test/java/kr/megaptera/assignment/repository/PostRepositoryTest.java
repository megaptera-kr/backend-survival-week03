package kr.megaptera.assignment.repository;

import kr.megaptera.assignment.domain.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @DisplayName("게시글을 저장하고 조회한다")
    @Test
    void save() {
        // given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();

        // when
        Post savedPost = postRepository.saveAndFlush(post);

        // then
        assertThat(savedPost)
                .hasFieldOrPropertyWithValue("title", post.getTitle())
                .hasFieldOrPropertyWithValue("content", post.getContent());
    }

}