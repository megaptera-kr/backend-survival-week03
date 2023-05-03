package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping("")
    public List<PostDto> getPostList(){
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id){
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
        return postDto;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String postPost(@RequestBody PostDto postDto){
        postDto.setId(Long.toString(getIdNum()));
        postDtos.add(postDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postPut(@PathVariable String id, @RequestBody PostDto postDto){

        PostDto postDto1 = postDtos.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        int idx = postDtos.indexOf(postDto1);
        postDtos.set(idx, postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postDelete(@PathVariable String id){
        PostDto postDto1 = postDtos.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        int idx = postDtos.indexOf(postDto1);
        postDtos.remove(idx);
    }

    public Long getIdNum(){
        newId += 1L;
        return newId ;
    }
}
