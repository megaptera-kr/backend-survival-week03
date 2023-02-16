package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.AddPostRequestDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
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
import java.util.Optional;

@RequestMapping("/posts")
@RestController
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping()
    public List<PostDto> getPosts(){

        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPostDatail(@PathVariable Long id){
        Optional<PostDto> findResult;
        try{
            findResult = postDtos.stream().filter(postDto -> id.equals(postDto.getId())).findFirst();
            if (!findResult.isPresent()) {
                throw new IllegalArgumentException("don't have that item");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return findResult.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String addPost(@RequestBody AddPostRequestDto addPostRequestDto){
        PostDto postDto = new PostDto(newId++, addPostRequestDto.getTitle(), addPostRequestDto.getContent());
        postDtos.add(postDto);
        return "Complete!";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping()
    public void editPost(@RequestBody PostDto postDto){
        int foundIdx = findId(postDto.getId());
        postDtos.set(foundIdx, new PostDto(postDto.getId(), postDto.getTitle(), postDto.getContent()));
    }

    private int findId(Long id) {
        return Math.toIntExact(
          postDtos.stream().filter(postDto -> id.equals(postDto.getId())).findFirst().get()
                  .getId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        int foundIdx = findId(id);
        postDtos.remove(foundIdx);
    }

}
