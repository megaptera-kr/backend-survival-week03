package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
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
    @PostMapping()
    public String postComment(@RequestParam("postId") Long postId, @RequestBody CommentDto commentDto){
        commentDtos.add(new CommentDto(++newId, postId, commentDto.getContent()));
        return "Complete";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void putComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto){
        commentDto.setId(id);
        commentDtos = commentDtos.stream().map(dto -> {
            if(dto.getId() == id && dto.getPostId() == commentDto.getPostId()) return commentDto;
            else return dto;
        }).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Long id, @RequestParam("postId") Long postId){
        System.out.println("id : " + id);
        commentDtos = commentDtos.stream().filter(dto -> !dto.getPostId().equals(postId) && !dto.getId().equals(id)).collect(Collectors.toList());
    }
}
