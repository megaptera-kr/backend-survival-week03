package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("http://localhost:8000")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> list() {
        return postDtos;
    }

    @GetMapping("{id}")
    public PostDto detail(
            @PathVariable String id
    ) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id))
                return postDto;
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestBody PostDto body
    ) {
        Long id = autoIncremenet();
        body.setId(String.valueOf(id));

        postDtos.add(body);

        return "Complate!";
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestBody PostDto body
    ) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                postDto.setId(body.getId());
                postDto.setTitle(body.getTitle());
                postDto.setContent(body.getContent());
            }
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id
    ) {
        for (int i = 0; i < postDtos.size(); i++) {
            PostDto postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                postDtos.remove(i);
            }
        }
    }

    private Long autoIncremenet() {
        return ++newId;
    }
}
