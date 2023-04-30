package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final String COMPLETED = "Complete!";
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    private List<PostDto> getPosts() {
        return postDtos;
    }

    @GetMapping("/{id}")
    private Optional<PostDto> getPost(@PathVariable("id") String id) {
        Optional<PostDto> findPost = postDtos.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        return findPost;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String createPost(@RequestBody PostDto postDto) {
        postDtos.add(new PostDto(incrementId(), postDto.getTitle(), postDto.getContent()));
        return COMPLETED;
    }

    private String incrementId() {
        newId = Long.valueOf(postDtos.size());
        return String.valueOf(++newId);
    }
}
