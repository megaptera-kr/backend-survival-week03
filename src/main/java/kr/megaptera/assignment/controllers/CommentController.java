package kr.megaptera.assignment.controllers;

import java.util.ArrayList;
import java.util.List;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CreateCommentRequest;
import kr.megaptera.assignment.dtos.UpdateCommentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8000")
@RestController
@RequestMapping("/comments")
public class CommentController {

    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestBody CreateCommentRequest request) {
        commentDtos.add(new CommentDto(newId.toString(), request.getPostId(), request.getContent()));
        newId++;
        return "Complete";
    }

    @GetMapping
    public List<CommentDto> getCommentList() {
        return commentDtos;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id, @RequestBody UpdateCommentRequest request) {
        CommentDto commentDto = commentDtos.get(Integer.parseInt(id));
        commentDto.setContent(request.getContent());
        commentDto.setPostId(request.getPostId());
        commentDto.setId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id) {
        commentDtos.remove(Integer.parseInt(id));
        if (newId > 0) {
            newId--;
        }
    }
}
