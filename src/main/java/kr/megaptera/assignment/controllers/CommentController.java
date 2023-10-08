package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping({"", "/"})
    public List<CommentDto> getComment(
            CommentDto requestCommentDto
    ) {
        // 리스트 순회로 게시물의 모든 comment 찾기
        List<CommentDto> responseCommentDtos = commentDtos
                .stream()
                .filter(
                        commentDto -> commentDto.getPostId().equals(requestCommentDto.getPostId())
                )
                .toList();

        return responseCommentDtos;
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public String postComment(
            CommentDto requestCommentDto
    ) {
        requestCommentDto.setId(this.newId++);

        this.commentDtos.add(requestCommentDto);

        return "Complete!";
    }

    @PutMapping({"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String putComment(
            CommentDto requestCommentDto
    ) {
        // 리스트 순회로 comment 찾기
        // 만약 요청정보로 comment를 찾지 못하면 NOT_FOUND 에러 발생
        CommentDto responseCommentDto = commentDtos
                .stream()
                .filter(
                        commentDto -> commentDto.getPostId().equals(requestCommentDto.getPostId())
                            && commentDto.getId().equals(requestCommentDto.getId())
                )
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));

        responseCommentDto.setContent(requestCommentDto.getContent());

        return "";
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteComment(
            CommentDto requestCommentDto
    ) {
        // 리스트 순회로 comment 삭제
        commentDtos.removeIf(commentDto -> commentDto.getPostId().equals(requestCommentDto.getPostId())
                && commentDto.getId().equals(requestCommentDto.getId()));

        return "";
    }

}
