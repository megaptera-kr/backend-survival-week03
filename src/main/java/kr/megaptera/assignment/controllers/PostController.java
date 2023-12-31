package kr.megaptera.assignment.controllers;

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

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> getPostList() {
        return this.postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id) {
        return this.postDtos.stream()
                .filter(postDto -> postDto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto postDto) {
        postDto.setId(String.valueOf(newId++));
        this.postDtos.add(postDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id, @RequestBody PostDto postDto) {
        this.postDtos.stream().filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setId(postDto.getId());
                    post.setTitle(postDto.getTitle());
                    post.setContent(postDto.getContent());
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        this.postDtos.removeIf(postDto -> postDto.getId().equals(id));
    }
}
