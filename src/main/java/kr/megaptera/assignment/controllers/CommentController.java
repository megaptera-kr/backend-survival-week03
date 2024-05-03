package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    // 댓글 조회
    @GetMapping
    public List<CommentDto> CommentList(@RequestParam("postId") String postId) {
        return commentDtos;
    }

    // 댓글 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto commentPost(@RequestParam("postId") String postId, @RequestBody CommentDto commentDto) {
        commentDto.setId(generateId());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return commentDto;
    }

    // 댓글 수정 PUT /comments/{id}?postId={postId}
    @PutMapping("/{id}")
    public CommentDto commentEdit(@PathVariable("id") String id,
                                  @RequestParam("postId") String postId,
                                  @RequestBody CommentDto commentDto) {
        CommentDto dto = new CommentDto();
        // 매개변수로 가져온 id로 데이터 찾기
        for (int i = 0; i < commentDtos.size(); i++) {
            dto = commentDtos.get(i);
            if (dto.getId().equals(id)) {
                dto.setContent(commentDto.getContent());
                break;
            }
        }
        return dto;
    }

    // 댓글 삭제 POST /comments/{id}?postId={postId}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentDto delete(@PathVariable("id") String id,
                             @RequestParam("postId") String postId) {
        CommentDto dto = new CommentDto();
        for (int i = 0; i < commentDtos.size(); i++) {
            dto = commentDtos.get(i);
            if (dto.getId().equals(id)) {
                // 댓글 삭제 로직
                commentDtos.remove(i);
                break;
            }
        }
        return dto;
    }

    private String generateId() {
        newId += 1;

        return newId.toString();
    }
}
