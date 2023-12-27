package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;
    private List<CommentDto> commentDtos = new ArrayList<>();

    private Long getNextId() {
        return newId++;
    }

    @GetMapping
    private List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (CommentDto commentDto : commentDtos) {
            if (commentDto.getPostId().equals(postId)) {
                commentDtoList.add(commentDto);
            }
        }
        return commentDtoList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String create(@RequestParam String postId, @RequestBody CommentDto body) {
        body.setId("" + getNextId());
        body.setPostId(postId);
        commentDtos.add(body);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void update(@PathVariable String id, @RequestParam String postId, @RequestBody CommentDto body) {
        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);
            if (commentDto.getId().equals(id) && commentDto.getPostId().equals(postId)) {
                commentDtos.set(i, body);
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id, @RequestParam String postId) {
        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);
            if (commentDto.getId().equals(id) && commentDto.getPostId().equals(postId)) {
                commentDtos.remove(i);
                break;
            }
        }
    }
}
