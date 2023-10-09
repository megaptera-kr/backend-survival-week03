package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> listPost() {
        return postDtos.stream().toList();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id) {
        return postDtos.stream().filter(item -> item.id().equals(id)).findFirst().orElse(null);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto postRequestDto) {
        PostDto AddedIdPostDto = new PostDto(createId(),postRequestDto.title(),postRequestDto.content());
        postDtos.add(AddedIdPostDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putPosts(@PathVariable String id, @RequestBody PostDto postRequestDto) {
        postDtos = postDtos.stream().map(item -> item.id().equals(id) ? postRequestDto : item)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        PostDto postDto = postDtos.stream().filter(item -> item.id().equals(id)).findFirst().orElse(null);
        if (postDto != null) {
            postDtos.remove(postDto);
        }
    }

    //정적 팩토리 메서드 구현
    private String createId() {
        newId++;
        return newId.toString();
    }
}