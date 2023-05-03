package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId){
        List<CommentDto> commentDtoList = commentDtos.stream()
                .filter(i -> i.getPostId().equals(postId))
                .toList();

        return commentDtoList;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String postComment(@RequestParam String postId,
                              @RequestBody CommentDto commentDto){
        commentDto.setId(getIdNum().toString());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);

        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putComment(@PathVariable String id,
                           @RequestParam String postId,
                           @RequestBody CommentDto commentDto){

        CommentDto commentDto1 = commentDtos.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        int idx = commentDtos.indexOf(commentDto1);
        commentDtos.set(idx, commentDto );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id,
                              @RequestParam String postId){
        CommentDto commentDto1 = commentDtos.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        int idx = commentDtos.indexOf(commentDto1);
        commentDtos.remove(idx);
    }


    public Long getIdNum(){
        newId += 1L;
        return newId ;
    }
}
