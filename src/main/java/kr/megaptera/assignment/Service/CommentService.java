package kr.megaptera.assignment.Service;

import kr.megaptera.assignment.dtos.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    public List<CommentDto> getCommentList(String postId) {
        log.info("postId: {}", postId);
        List<CommentDto> commentDtosResult = new ArrayList<>();
        for(CommentDto commentDto : commentDtos) {
            if(commentDto.getPostId().equals(postId)) {
                commentDtosResult.add(commentDto);

            }
        }
        return commentDtosResult;
    }

    public String createComment(String postId, CommentDto commentDto) {
        commentDto.setId(getId());
        commentDto.setPostId(postId);
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
        commentDtos.removeIf(commentDto -> commentDto.getId().equals(id) && commentDto.getPostId().equals(postId));
    }

    private String getId() {
        return String.valueOf(newId++);
    }
}
