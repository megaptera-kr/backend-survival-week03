package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.CommentDTO;
import kr.megaptera.assignment.exception.NoSuchCommentIdException;
import kr.megaptera.assignment.exception.NoSuchPostIdException;
import kr.megaptera.assignment.repository.CommentRepository;
import kr.megaptera.assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<CommentDTO> list(Long postId) {
        List<Comment> byPostId = commentRepository.findByPostId(postId).orElseThrow(NoSuchCommentIdException::new);
        return byPostId.stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getPost().getId(), comment.getContent()))
                .toList();
    }

    public void create(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(NoSuchPostIdException::new);
        commentRepository.save(
                Comment.builder()
                        .content(commentDTO.content())
                        .post(post)
                        .build());
    }

    public void update(Long id, Long postId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(NoSuchCommentIdException::new);
        if (!Objects.equals(comment.getPost().getId(), postId)) {
            throw new NoSuchCommentIdException();
        }
        comment.update(commentDTO.content());
        commentRepository.save(comment);
    }

    public void delete(Long id, Long postId) {
        Comment comment = commentRepository.findById(id).orElseThrow(NoSuchCommentIdException::new);
        if (!Objects.equals(comment.getPost().getId(), postId)) {
            throw new NoSuchCommentIdException();
        }
        commentRepository.delete(comment);
    }
}
