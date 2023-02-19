package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.core.JacksonException;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentsList(@PathVariable String postId) {
        List<CommentDto> commentList = commentDtos.stream()
                                    .filter( c -> c.getPostId().equals(postId))
                                    .collect(Collectors.toList());
        return commentList;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestParam String postId, @RequestBody CommentDto requestCommentDto)  {
        newId++;
        commentDtos.add(new CommentDto(Long.toString(newId), postId, requestCommentDto.getContent()));
        return "Complete!";
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id, @RequestBody CommentDto requestCommentDto) {
        CommentDto commentDto = commentDtos.get(Integer.parseInt(id));
        commentDto.setContent(requestCommentDto.getContent());
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id) {
        commentDtos.remove(Integer.parseInt(id));
    }
}
