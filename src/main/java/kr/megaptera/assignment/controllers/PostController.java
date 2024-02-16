package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final ObjectMapper objectMapper;
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();
    public PostController(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }
    @GetMapping()
    public List<PostDto> getPost(){
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPostDetail(@PathVariable("id") String id){
        return postDtos.stream().filter(post -> post.getId().equals(id)).findFirst().get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String postPost(@PathVariable("title") String title, @PathVariable("content") String content){
        Long maxId = 0L;
        if(maxId != 0L) maxId = postDtos.stream().max(Comparator.comparing(PostDto::getId)).get().getId() + 1;
        PostDto postDto = new PostDto(maxId, title, content);
        postDtos.add(postDto);
        return "Complete";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void putPost(@PathVariable("id") Long id,
                        @PathVariable("title") String title,
                        @PathVariable("content") String content){
        PostDto postDto = new PostDto(id, title, content);
        postDtos = postDtos.stream().map(dto -> {
            if(dto.getId().equals(id)){
                return postDto;
            }
            else {
                return dto;
            }
        }).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") long id){
        postDtos = postDtos.stream()
                .filter(dto -> !dto.getId().equals(id))
                .collect(Collectors.toList());
    }
}
