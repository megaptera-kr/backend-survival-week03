package kr.megaptera.assignment.controllers;

import java.util.Comparator;
import java.util.stream.Collectors;
import kr.megaptera.assignment.common.CommonMessage;
import kr.megaptera.assignment.dtos.CommentDto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    // 댓글 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> list(@RequestParam String postId) {
        return findListByPostId(postId);
    }

    // 댓글 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam String postId,
                            @RequestBody CommentDto commentDto) {
        commentDto.addNewId(postId, getNewCommentId());
        commentDtos.add(commentDto);
        return CommonMessage.COMPLETE;
    }

    // 댓글 수정
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                            @RequestParam String postId,
                            @RequestBody CommentDto updateCommentDto) {
        CommentDto targetCommentDto = findById(postId, id);
        commentDtos.remove(targetCommentDto);
        commentDtos.add(updateCommentDto);
        sortCommentDtosById();
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id,
                        @RequestParam String postId) {
        CommentDto targetCommentDto = findById(postId, id);
        commentDtos.remove(targetCommentDto);
    }

    public CommentDto findById(String postId, String commentId) {
        return commentDtos.stream()
            .filter(c -> c.getPostId().equals(postId) && c.getId().equals(commentId))
            .findFirst()
            .orElse(new CommentDto());
    }

    public List<CommentDto> findListByPostId(String postId) {
        return commentDtos.stream()
            .filter(c -> c.getPostId().equals(postId))
            .collect(Collectors.toList());
    }

    public String getNewCommentId() {
        return (++newId).toString();
    }

    public void sortCommentDtosById() {
        commentDtos = commentDtos.stream()
            .sorted(Comparator.comparing(CommentDto::getId))
            .collect(Collectors.toList());
    }
}
