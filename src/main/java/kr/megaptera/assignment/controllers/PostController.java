package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CreatePostRequest;
import kr.megaptera.assignment.dtos.PostDto;

import java.util.ArrayList;
import java.util.List;

import kr.megaptera.assignment.dtos.UpdatePostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8000")
@RestController
@RequestMapping("/posts")
public class PostController {

    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody CreatePostRequest request) {
        postDtos.add(
                new PostDto(generateId().toString(), request.getTitle(), request.getContent()));

        return "Complete!";
    }

    private Long generateId() {
        return newId++;
    }

    @GetMapping
    public List<PostDto> getPostList() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id) {
        return postDtos.get(Integer.parseInt(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id, @RequestBody UpdatePostRequest request) {
        PostDto postDto = postDtos.get(Integer.parseInt(id));
        postDto.setTitle(request.getTitle());
        postDto.setContent(request.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        postDtos.remove(Integer.parseInt(id));
        degenerateId();
    }

    private void degenerateId() {
        if (newId > 0) {
            newId--;
        }
    }
}
