package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;
    private List<CommentDto> commentDtos = new ArrayList<>();

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping()
    public List<CommentDto> getComments(@RequestParam("postId") Long postId){
        return commentDtos.stream().filter(dto -> dto.getPostId() == postId).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public String postComment(@PathVariable("postId") Long postId, @RequestParam String content){
        Long maxId = commentDtos.stream().filter(dto -> dto.getPostId().equals(postId))
                                         .max(Comparator.comparing(CommentDto::getId)).get().getId() + 1;
        commentDtos.add(new CommentDto(maxId, postId, content));
        return "Complete";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void putComment(@PathVariable("id") Long id, @RequestParam("postId") Long postId, @RequestParam("content") String content){
        CommentDto commentDto = new CommentDto(id, postId, content);
        commentDtos = commentDtos.stream().map(dto -> {
            if(dto.getId() == id && dto.getPostId() == postId) return commentDto;
            else return dto;
        }).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/id")
    public void deleteComment(@PathVariable("id") String id, @RequestParam("postId") String postId){
        commentDtos = commentDtos.stream().filter(dto -> !dto.getPostId().equals(postId) && !dto.getId().equals(id)).collect(Collectors.toList());
    }
}
