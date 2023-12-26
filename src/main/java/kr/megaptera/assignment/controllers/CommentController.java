package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> getComments(@RequestParam String postId){
        List<CommentDto> foundComments = commentDtos.stream()
                .filter(c -> c.getPostId().equals(postId))
                .collect(Collectors.toList());
        return foundComments;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String writeComment(@RequestParam String postId, @RequestBody CommentDto commentDto){
        CommentDto newComment = new CommentDto(getNewId().toString(), postId, commentDto.getContent());
        commentDtos.add(newComment);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editComment(@RequestParam String postId, @PathVariable String id, @RequestBody CommentDto commentDto){
        CommentDto foundComment = commentDtos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException());
        foundComment.setContent(commentDto.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@RequestParam String postId, @PathVariable String id){
        commentDtos.removeIf(c -> c.getId().equals(id));
    }

    private Long getNewId() {
        newId += 1L;
        return newId;
    }
}
