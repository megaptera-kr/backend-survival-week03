package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping()
    public List<CommentDto> getCommentList(@RequestParam String postId) {
        return this.commentDtos.stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        commentDto.setId(String.valueOf(newId++));
        commentDto.setPostId(postId);
        this.commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id,
                              @RequestParam String postId,
                              @RequestBody CommentDto commentDto) {
        this.commentDtos.stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst()
                .ifPresent(comment -> {
                    comment.setId(id);
                    comment.setContent(commentDto.getContent());
                    comment.setPostId(postId);
                });

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id) {
        this.commentDtos.removeIf(comment -> comment.getId().equals(id));
    }
}
