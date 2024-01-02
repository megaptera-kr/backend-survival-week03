package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    private Long getNextId() {
        return newId++;
    }

    @GetMapping
    private List<PostDto> list() {
        return postDtos;
    }

    @GetMapping("/{id}")
    private PostDto detail(@PathVariable String id) {
        for (PostDto postDto : postDtos) {
            if (postDto.getId().equals(id)) {
                return postDto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String create(@RequestBody PostDto body) {
        body.setId("" + getNextId());
        postDtos.add(body);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void update(@PathVariable String id, @RequestBody PostDto body) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                postDtos.set(i, body);
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                postDtos.remove(i);
                break;
            }
        }
    }
}
