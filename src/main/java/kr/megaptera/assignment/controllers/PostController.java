package kr.megaptera.assignment.controllers;

import java.util.Comparator;
import java.util.stream.Collectors;
import kr.megaptera.assignment.common.CommonMessage;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
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

@RestController
@RequestMapping("/posts")
public class PostController {

    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    // 게시글 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> list() {
        return postDtos;
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto detail(@PathVariable String id) {
        return findById(id);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return CommonMessage.POST_NOT_FOUND;
    }

    // 게시글 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto postDto) {
        postDto.addNewId(getNewPostId());
        postDtos.add(postDto);
        return CommonMessage.COMPLETE;
    }

    // 게시글 수정
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                        @RequestBody PostDto updatePostDto) {
        PostDto postDto = findById(id);
        postDtos.remove(postDto);
        postDtos.add(updatePostDto);
        sortPostDtosById();
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        PostDto postDto = findById(id);
        postDtos.remove(postDto);
    }

    public PostDto findById(String id) {
        return postDtos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(PostNotFoundException::new);
    }

    public String getNewPostId() {
        return (++newId).toString();
    }

    public void sortPostDtosById() {
        postDtos = postDtos.stream()
            .sorted(Comparator.comparing(PostDto::getId))
            .collect(Collectors.toList());
    }
}
