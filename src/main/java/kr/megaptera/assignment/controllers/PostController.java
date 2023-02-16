package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> getPosts() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id) {
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addPost(@RequestBody PostDto postDto) {
        postDto.setId(getIncrementId().toString());
        postDtos.add(postDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id, @RequestBody PostDto updatedPostDto) {
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
        postDtos.remove(postDto);

        updatedPostDto.setId(id);
        postDtos.add(updatedPostDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable String id) {
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
        postDtos.remove(postDto);
    }

    private Long getIncrementId() {
        return ++newId;
    }

}
