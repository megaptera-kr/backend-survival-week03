package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPosts(){
        return postDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable String id){
        Optional<PostDto> first = postDtos.stream().filter(postDto -> postDto.getId().equals(id)).findFirst();
        return first.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto postDto){
        PostDto post = new PostDto(String.valueOf(newId), postDto.getTitle(), postDto.getContent());
        newId++;
        postDtos.add(post);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id, @RequestBody PostDto postDto){
        int idx = postDtos.stream().map(PostDto::getId).toList().indexOf(id);
        postDtos.set(idx, postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable String id){
        int idx = postDtos.stream().map(PostDto::getId).toList().indexOf(id);

        if (idx == -1) {
            throw new RuntimeException("없는 글입니다.");
        }

        postDtos.remove(idx);
    }
}
