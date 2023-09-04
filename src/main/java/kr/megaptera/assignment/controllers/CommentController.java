package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentContentDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.NoPostIdCommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;
    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> filteredCommentDtos = new ArrayList<>();

        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);
            if (commentDto.getPostId().equals(postId)) {
                filteredCommentDtos.add(commentDto);
            }
        }

        return filteredCommentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam String postId, @RequestBody CommentContentDto commentContentDto) {
        String id = String.valueOf(newId);
        String content = commentContentDto.getContent();

        increaseId();

        commentDtos.add(new CommentDto(id, postId, content));

        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestParam String postId, @RequestBody NoPostIdCommentDto noPostIdCommentDto) {
        String commentId = noPostIdCommentDto.getId();
        String content = noPostIdCommentDto.getContent();

        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);
            if (commentDto.getId().equals(id)) {
                commentDtos.set(i, new CommentDto(commentId, postId, content));
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id, @RequestParam String postId) {
        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);
            if (commentDto.getId().equals(id)) {
                commentDtos.remove(i);
                return;
            }
        }
    }


    private void increaseId() {
        newId++;
    }
}
