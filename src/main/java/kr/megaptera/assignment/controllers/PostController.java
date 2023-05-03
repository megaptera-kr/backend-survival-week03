package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

@RequestMapping("/posts")
@RestController
public class PostController {
    private Long newId = 0L;

    // 현재 객체의 인덱스가 항상 실제 ID와 같으리라는 기대를 하지 말자 -> 금요일 중 수정 예정
    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> getPosts() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(
            @PathVariable String id
    ) {
        PostDto postDto = postDtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElseThrow(NotFoundException::new);

        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(
            @RequestBody PostDto postDto
    ) {
        postDto.setId(getNewId());
        postDtos.add(postDto);
        return "Complete!\n";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(
            @PathVariable String id,
            @RequestBody PostDto postDto
    ) {
        if (postDtos.removeIf(p -> p.getId().equals(id))) {
            postDtos.add(postDto);
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable String id
    ) {
        if (!postDtos.removeIf(p -> p.getId().equals(id))) {
            throw new NotFoundException();
        }
    }

    public Long getNewId() {
        this.newId++;
        return this.newId;
    }

    // Exception handling
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void postNotFound() {
    }
}
