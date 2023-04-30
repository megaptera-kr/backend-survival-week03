package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final String COMPLETED = "Complete!";

    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();


    @GetMapping
    private List<CommentDto> getComments(@RequestParam("postId") String postId) {
        List<CommentDto> findComments = commentDtos
                .stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .toList();
        return findComments;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String createComment(@RequestParam("postId") String postId, @RequestBody CommentDto commentDto) {
        commentDtos.add(new CommentDto(incrementId(), postId, commentDto.getContent()));
        return COMPLETED;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void updateComment(@PathVariable("id") String id, @RequestParam("postId") String postId, @RequestBody CommentDto commentDto) {
        CommentDto findComment = commentDtos.stream()
                .filter(comment -> comment.getId().equals(id))
                .filter(comment -> comment.getPostId().equals(postId))
                .findFirst()
                .orElseThrow();
        findComment.setContent(commentDto.getContent());
    }

    private String incrementId() {
        newId = Long.valueOf(commentDtos.size());
        return String.valueOf(++newId);
    }
}
