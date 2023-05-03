package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping("")
    public List<PostDto> getPosts() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getDetailPost(@PathVariable String id) {
        PostDto dto = postDtos.stream()
                .filter(postDto -> postDto.getId().equals(id))
                .findAny()
                .orElse(null);
        return dto;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String setPost(@RequestBody PostDto postDto) {
//        max + 1
        try {
            PostDto maxIdPostDto = postDtos.stream().max(Comparator.comparing(v -> v.getId())).get();
            postDto.setId(String.valueOf(Integer.parseInt(maxIdPostDto.getId()) + 1));
            postDtos.add(postDto);
        } catch (NoSuchElementException e) {
//        리스트에 값이 없을 시 id 1로 초기화
            postDto.setId("1");
            postDtos.add(postDto);
        }
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyPost(
            @PathVariable String id,
            @RequestBody PostDto postDto
    ) {
        postDtos = postDtos.stream()
                .map(matchedPost -> matchedPost.getId().equals(id)
                        ? new PostDto(postDto.getId(), postDto.getTitle(), postDto.getContent())
                        : matchedPost)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        postDtos = postDtos.stream()
                .filter(matchedPost -> !matchedPost.getId().equals(id))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.\n";
    }
}
