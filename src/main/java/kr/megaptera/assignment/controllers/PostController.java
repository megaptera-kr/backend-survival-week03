package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> getLists(){
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getDetail(@PathVariable String id){
        PostDto foundPost = postDtos.stream()
                .filter(postDto -> postDto.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException());
        return foundPost;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String writePost(@RequestBody PostDto postDto){
        Long id = getNewId();
        postDto.setId(id.toString());
        postDtos.add(postDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editPost(@PathVariable String id, @RequestBody PostDto postDto){
        PostDto foundPost = postDtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException());

        System.out.println("foundPost.getId(): " + foundPost.getId());
        foundPost.setTitle(postDto.getTitle());
        foundPost.setContent(postDto.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id){
        postDtos.removeIf(p -> p.getId().equals(id));
    }

    private Long getNewId() {
        newId += 1L;
        return newId;
    }

}
