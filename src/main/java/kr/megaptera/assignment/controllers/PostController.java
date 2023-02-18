package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
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
    public PostDto detail(
            @PathVariable("id") String id
    ) {
        PostDto returnPostDto = null;
        for (PostDto p : postDtos) {
            if (String.valueOf(p.getId()).equals(id)) {
                returnPostDto = p;
                break;
            }
        }
        return returnPostDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestBody(required = false) PostDto PostDto
    ) {
        postDtos.add(new PostDto(++newId, PostDto.getTitle(), PostDto.getContent()));
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable("id") String id,
            @RequestBody(required = false) PostDto postDto
    ) {
        for (PostDto p : postDtos) {
            if (String.valueOf(p.getId()).equals(id)) {
                p.setTitle(postDto.getTitle());
                p.setContent(postDto.getContent());
                break;
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id") String id
    ) {
        for (PostDto p : postDtos) {
            if (String.valueOf(p.getId()).equals(id)) {
                postDtos.remove(p);
                break;
            }
        }
    }
}
