package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.NotFoundException;
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

@RequestMapping("comments")
@RestController
public class CommentController {

    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> getComments(
            @RequestParam("postId") String postId
    ) {
        return commentDtos.stream().filter(c -> c.getPostId().equals(postId)).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(
            @RequestParam("postId") String postId,
            @RequestBody CommentDto commentDto
    ) {
        commentDto.setId(getNewCommentId());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return "Complete!\n";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(
            @PathVariable("id") String id,
            @RequestParam("postId") String postId,
            @RequestBody CommentDto commentDto
    ) {
        commentDto.setId(Long.parseLong(id));
        commentDto.setPostId(postId);

        if (commentDtos.removeIf(c -> c.getId().equals(id))) {
            commentDtos.add(commentDto);
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable("id") String id
    ) {
        if (!commentDtos.removeIf(c -> c.getId().equals(id))) {
            throw new NotFoundException();
        }
    }

    public Long getNewCommentId() {
        this.newId++;
        return this.newId;
    }
}
