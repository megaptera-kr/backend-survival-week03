package kr.megaptera.assignment.Service;

import kr.megaptera.assignment.dtos.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private Long newId = 0L;

    private final List<CommentDto> commentDtos = new ArrayList<>();
    public List<CommentDto> getCommentList(String postId) {
        log.info("postId: {}", postId);
        for(CommentDto commentDto : commentDtos) {
            if(commentDto.getPostId().equals(postId)) {
                return commentDtos;
            }
        }
        return null;
    }

    public String createComment(String postId, CommentDto commentDto) {
        commentDto.setId("" + getId());
        commentDtos.add(commentDto);
        return "Completed!";
    }

    public void updateComment(String id, String postId, CommentDto commentDto) {
        for(CommentDto comment : commentDtos) {
            if(comment.getId().equals(id) && comment.getPostId().equals(postId)) {
                comment.setContent(commentDto.getContent());
                comment.setId(commentDto.getId());
            }
        }
    }

    public void deleteComment(String id, String postId) {
        for(CommentDto commentDto : commentDtos) {
            if(commentDto.getId().equals(id) && commentDto.getPostId().equals(postId)) {
                commentDtos.remove(commentDto);
            }
        }
    }

    private String getId() {
        return String.valueOf(newId++);
    }
}
