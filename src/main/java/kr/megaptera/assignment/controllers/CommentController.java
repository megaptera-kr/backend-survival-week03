package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping("")
    public List<CommentDto> getComments(@RequestParam String postId) {
        return commentDtos.stream()
                .filter(commentDto -> commentDto.getPostId().equals(postId))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public String setComment(
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        commentDto.setPostId(postId);
        try {
            CommentDto maxIdCommentDto = commentDtos.stream().max(Comparator.comparing(v -> v.getId())).get();
            commentDto.setId(String.valueOf(Integer.parseInt(maxIdCommentDto.getId()) + 1));
            commentDtos.add(commentDto);
        } catch (NoSuchElementException e) {
            commentDto.setId("1");
            commentDtos.add(commentDto);
        }
        return "Complete";
    }

    @PutMapping("/{id}")
    public void modifyComment(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        commentDtos = commentDtos.stream()
                .map(matchedComment -> matchedComment.getId().equals(id)
                    ? new CommentDto(commentDto.getId(), postId, commentDto.getContent())
                    : matchedComment)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public void deleteComment(
            @PathVariable String id,
            @RequestParam String postId
    ){
        commentDtos = commentDtos.stream()
                .filter(matchedComment -> !matchedComment.getId().equals(id))
                .collect(Collectors.toList());
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.\n";
    }
}
