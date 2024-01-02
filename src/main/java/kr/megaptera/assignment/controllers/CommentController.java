package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.Service.CommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final
    CommentService commentService;

    /**
     * 댓글 조회
     * @param postId
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentList(@RequestParam String postId) {
        return commentService.getCommentList(postId);
    }

    /**
     * 댓글 생성
     * @param postId
     * @param commentDto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        return commentService.createComment(postId, commentDto);
    }

    /**
     * 댓글 수정
     * @param id
     * @param postId
     * @param commentDto
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id,
                                @RequestParam String postId,
                                @RequestBody CommentDto commentDto) {
        commentService.updateComment(id, postId, commentDto);
    }

    /**
     * 댓글 삭제
     * @param id
     * @param postId
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id,
                                @RequestParam String postId) {
        commentService.deleteComment(id, postId);
    }

}
