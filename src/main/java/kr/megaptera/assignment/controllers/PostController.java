package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> list() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto details(@PathVariable String id) {
        PostDto postDTO = postDtos.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .get();

        return postDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto postDto) {
        postDto.setId(makeId());
        postDtos.add(postDto);

        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestBody PostDto postDto
    ) {
        postDtos = postDtos.stream()
                .map(item -> item.getId().equals(id) ? postDto : item)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        PostDto postDto = postDtos.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .get();

        postDtos.remove(postDto);
    }

    private String makeId() {
        newId += 1;
        return newId.toString();
    }
}
