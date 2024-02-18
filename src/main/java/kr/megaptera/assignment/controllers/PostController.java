package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin
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
    public PostDto getPostDetail(@PathVariable("id") Long id){
        return postDtos.stream().filter(post -> post.getId().equals(id)).findFirst().get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<String> postPost(@RequestBody PostDto postDto){
        PostDto addPstDto = new PostDto(++newId, postDto.getTitle(), postDto.getContent());
        postDtos.add(addPstDto);
        return ResponseEntity.ok("Complete");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public String putPost(@PathVariable("id") Long id, @RequestBody PostDto postDto){
        System.out.println("id : " + id);
        System.out.println(postDto);
        postDtos = postDtos.stream().map(dto -> {
            if(dto.getId().equals(id)){
                return postDto;
            }
            else {
                return dto;
            }
        }).collect(Collectors.toList());
        return "Put!";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") Long id){
        postDtos = postDtos.stream()
                .filter(dto -> !dto.getId().equals(id))
                .collect(Collectors.toList());
        return "Delete!";
    }
}
