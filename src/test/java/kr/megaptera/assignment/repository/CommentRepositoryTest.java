package kr.megaptera.assignment.repository;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @DisplayName("댓글을 생성하고 저장한다")
    @Test
    void save() {
        // given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();

        Comment comment = Comment.builder()
                .content("content1")
                .post(post)
                .build();

        // when
        postRepository.saveAndFlush(post);
        Comment savedComment = commentRepository.saveAndFlush(comment);

        // then
        Assertions.assertThat(savedComment)
                .hasFieldOrPropertyWithValue("content", "content1")
                .hasFieldOrPropertyWithValue("post", post);

    }

}