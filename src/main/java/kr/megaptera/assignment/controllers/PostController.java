package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.NoIdPostDto;
import kr.megaptera.assignment.dtos.PostDto;
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

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> list() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                return postDto;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody NoIdPostDto noIdPostDto) {
        String id = String.valueOf(newId);
        String title = noIdPostDto.getTitle();
        String content = noIdPostDto.getContent();

        increment();

        postDtos.add(new PostDto(id, title, content));
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody PostDto newPostDto) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                postDtos.set(i, newPostDto);
                return;
            }
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                postDtos.remove(i);
                return;
            }
        }
    }

    private void increment() {
        newId++;
    }
}
