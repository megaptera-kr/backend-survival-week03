package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    private List<PostDto> list() {
        return postDtos;
    }

    @GetMapping("/{id}")
    private PostDto post(@PathVariable String id) {
        return postDtos.stream().filter(i -> i.getId().equals(id))
            .findFirst().orElseThrow(PostNotFound::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String create(@RequestBody PostDto postDto) {
        generatePostId();
        postDto.setId(String.valueOf(newId));
        postDtos.add(postDto);

        return "Complete!";
    }

    @PutMapping("/{id}")
    private PostDto update(
        @PathVariable String id,
        @RequestBody PostDto postDto
    ) {
        PostDto post = postDtos.stream().filter(i -> i.getId().equals(id))
            .findFirst().get();

        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return post;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id) {
        postDtos.removeIf(postDto -> postDto.getId().equals(id));
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private PostNotFound postNotFound() {
        return new PostNotFound();
    }

    private void generatePostId() {
        newId += 1;
    }
}
