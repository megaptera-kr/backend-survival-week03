package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> getPostList() {
        // TODO(DEBUG): Remove this line.
        System.out.println("getPostList()");
        return this.postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id) {
        // TODO(DEBUG): Remove this line.
        System.out.println("getPost(" + id + ")");
        return this.postDtos.stream()
                .filter(postDto -> postDto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @RequestBody  : 요청 본문을 자바 객체로 변환(Deserialization)한다.
    public String createPost(@RequestBody PostDto postDto) {
        // TODO(DEBUG): Remove this line.
        System.out.println("createPost(" + postDto + ")");
        // 요청할 때 id 프로퍼티를 입력해도 무시하고 새로운 id를 부여한다.
        System.out.println(postDto.getId());
        postDto.setId(String.valueOf(newId++));
        this.postDtos.add(postDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id, @RequestBody PostDto postDto) {
        // TODO(DEBUG): Remove this line.
        System.out.println("updatePost(" + id + ", " + postDto + ")");
        // 요청할 때 RequestBody에 id 프로퍼티로 덮어쓴다.
        // PathVariable로 받은 id는 객체를 필터링하는 용도로 사용한다.
        this.postDtos.stream().filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setId(postDto.getId());
                    post.setTitle(postDto.getTitle());
                    post.setContent(postDto.getContent());
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        // TODO(DEBUG): Remove this line.
        System.out.println("deletePost(" + id + ")");
        this.postDtos.removeIf(postDto -> postDto.getId().equals(id));
    }
}
