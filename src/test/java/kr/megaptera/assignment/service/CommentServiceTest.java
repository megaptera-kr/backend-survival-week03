package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.CommentDTO;
import kr.megaptera.assignment.repository.CommentRepository;
import kr.megaptera.assignment.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentService commentService;

    @DisplayName("댓글을 조회한다")
    @Test
    void list() {
        // Given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        Comment comment1 = Comment.builder()
                .content("content1")
                .post(post)
                .build();
        Comment comment2 = Comment.builder()
                .content("content2")
                .post(post)
                .build();
        Comment comment3 = Comment.builder()
                .content("content3")
                .post(post)
                .build();
        postRepository.saveAndFlush(post);
        commentRepository.saveAllAndFlush(List.of(comment1, comment2, comment3));

        // When
        List<CommentDTO> commentDTOs = commentService.list(post.getId());

        // Then
        Assertions.assertThat(commentDTOs)
                .hasSize(3)
                .containsExactly(
                        new CommentDTO(1L, 1L, "content1"),
                        new CommentDTO(2L, 1L, "content2"),
                        new CommentDTO(3L, 1L, "content3")
                );
    }

}