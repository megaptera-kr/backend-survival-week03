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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    private List<CommentDto> list(@RequestParam String postId) {
        return commentDtos.stream()
            .filter(i -> i.getPostId().equals(postId))
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String create(
        @RequestBody CommentDto commentDto,
        @RequestParam String postId
    ) {
        generateCommentId();
        commentDto.setId(String.valueOf(newId));
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);

        return "Complete!";
    }

    @PutMapping("{id}")
    private CommentDto update(
        @PathVariable String id,
        @RequestParam String postId,
        @RequestBody CommentDto commentDto
    ) {
        CommentDto comment = commentDtos.stream().filter(i -> i.getId().equals(id) && i.getPostId().equals(postId))
            .findFirst().get();

        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());

        return comment;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(
        @PathVariable String id,
        @RequestParam String postId
    ) {
        commentDtos.removeIf(commentDto -> commentDto.getId().equals(id) && commentDto.getPostId().equals(postId));
    }

    private void generateCommentId() {
        newId += 1L;
    }
}
