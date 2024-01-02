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
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> list() {
        return postDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto detail(@PathVariable String id) {
        for (PostDto postDto : postDtos) {
            if (postDto.getId().equals(id)) {
                return postDto;
            }
        }

        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto postDto) {
        postDto.setId("" + getNewId());
        postDtos.add(postDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody PostDto postDto, @PathVariable String id) {
        for (int i = 0; i < postDtos.size(); i++) {
            if (postDtos.get(i).getId().equals(id)) {
                postDtos.set(i, postDto);
                break;
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        for (int i = 0; i < postDtos.size(); i++) {
            if (postDtos.get(i).getId().equals(id)) {
                postDtos.remove(i);
                break;
            }
        }
    }

    private Long getNewId() {
        return newId++;
    }
}
