package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.CommentDTO;
import kr.megaptera.assignment.repository.CommentRepository;
import kr.megaptera.assignment.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        commentRepository.deleteAllInBatch();
        postRepository.deleteAllInBatch();
    }

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
        List<Comment> comments = commentRepository.saveAllAndFlush(List.of(comment1, comment2, comment3));

        // When
        List<CommentDTO> commentDTOs = commentService.list(post.getId());

        // Then
        Assertions.assertThat(commentDTOs)
                .containsAll(
                        comments.stream()
                                .map(comment -> new CommentDTO(comment.getId(), comment.getPost().getId(), comment.getContent()))
                                .toList());
    }

    @DisplayName("댓글을 작성한다")
    @Test
    void create() {
        // Given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        postRepository.saveAndFlush(post);
        CommentDTO commentDTO = CommentDTO.of("content1");

        // When
        commentService.create(post.getId(), commentDTO);

        // Then
        List<Comment> byPostId = commentRepository.findByPostId(post.getId()).orElseThrow(RuntimeException::new);
        Assertions.assertThat(byPostId).hasSize(1);
    }

    @DisplayName("댓글을 수정한다")
    @Test
    void update() {
        // Given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        postRepository.saveAndFlush(post);
        Comment comment = Comment.builder()
                .content("content1")
                .post(post)
                .build();
        Comment savedComment = commentRepository.saveAndFlush(comment);
        CommentDTO commentDTO = CommentDTO.of(savedComment.getId(), "content2");

        // When
        commentService.update(savedComment.getId(), commentDTO);

        // Then
        Comment afterComment = commentRepository.findById(savedComment.getId())
                .orElseThrow(RuntimeException::new);
        Assertions.assertThat(afterComment)
                .hasFieldOrPropertyWithValue("id", savedComment.getId())
                .hasFieldOrPropertyWithValue("content", "content2");

    }
}