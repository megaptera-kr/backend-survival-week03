package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("http://localhost:8000")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> list(
            @RequestParam String postId
    ) {
        List<CommentDto> tempDtos = new ArrayList<>();
        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);

            if (commentDto.getPostId().equals(postId))
                tempDtos.add(commentDto);
        }
        return tempDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestParam String postId,
            @RequestBody CommentDto body
    ) {
        Long id = autoIncremenet();
        body.setId(String.valueOf(id));
        body.setPostId(postId);

        commentDtos.add(body);

        return "complate!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto body
    ) {
        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);
            if (commentDto.getPostId().equals(postId)) {
                if (commentDto.getId().equals(id)) {
                    commentDto.setId(body.getId());
                    commentDto.setContent(body.getContent());
                }
            }
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        for (int i = 0; i < commentDtos.size(); i++) {
            CommentDto commentDto = commentDtos.get(i);
            if (commentDto.getPostId().equals(postId)) {
                if (commentDto.getId().equals(id)) {
                    commentDtos.remove(i);
                }
            }
        }
    }

    private Long autoIncremenet() {
        return ++newId;
    }
}
