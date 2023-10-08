package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping({"", "/"})
    public List<CommentDto> getComment(
            @RequestParam("postId") Long postId
    ) {
        // 리스트 순회로 게시물의 모든 comment 찾기
        List<CommentDto> responseCommentDtos = commentDtos
                .stream()
                .filter(
                        commentDto -> commentDto.getPostId().equals(postId)
                )
                .toList();

        return responseCommentDtos;
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public String postComment(
            @RequestParam("postId") Long postId,
            @RequestBody CommentDto requestCommentDto
    ) {
        CommentDto commentDto = new CommentDto(this.newId++, postId, requestCommentDto.getContent());

        this.commentDtos.add(commentDto);

        return "Complete!";
    }

    @PutMapping({"/{commentId}", "/{commentId}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String putComment(
            @PathVariable("commentId") Long commentId,
            @RequestParam("postId") Long postId,
            @RequestParam("content") String content
    ) {
        // 리스트 순회로 comment 찾기
        // 만약 요청정보로 comment를 찾지 못하면 NOT_FOUND 에러 발생
        CommentDto responseCommentDto = commentDtos
                .stream()
                .filter(
                        commentDto -> commentDto.getPostId().equals(postId)
                                && commentDto.getId().equals(commentId)
                )
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));

        responseCommentDto.setContent(content);

        return "";
    }

    @DeleteMapping({"/{commentId}", "/{commentId}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteComment(
            @PathVariable Long commentId,
            @RequestParam("postId") Long postId
    ) {
        // 리스트 순회로 comment 삭제
        commentDtos.removeIf(commentDto -> commentDto.getPostId().equals(postId)
                && commentDto.getId().equals(commentId));

        return "";
    }

}
