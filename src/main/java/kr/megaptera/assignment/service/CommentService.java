package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.dtos.CommentDTO;
import kr.megaptera.assignment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentDTO> list(Long postId) {
        List<Comment> byPostId = commentRepository.findByPostId(postId);
        return byPostId.stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getPost().getId(), comment.getContent()))
                .toList();
    }
}
