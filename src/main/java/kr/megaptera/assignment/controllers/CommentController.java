package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getComments(@RequestParam String postId){
        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestParam String postId, @RequestBody CommentDto commentDto){
        commentDto.setId(String.valueOf(newId));
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        newId++;
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id,
                              @RequestParam String postId,
                              @RequestBody CommentDto commentDto){
        int idx = commentDtos.stream().map(CommentDto::getId).toList().indexOf(id);
        commentDtos.set(idx, commentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable String id, @RequestParam String postId){
        int idx = commentDtos.stream().map(CommentDto::getId).toList().indexOf(id);

        if (idx == -1) {
            throw new RuntimeException("없는 글입니다.");
        }

        commentDtos.remove(idx);
    }
}
