package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping({"", "/"})
    public List<PostDto> getPosts() {
        return this.postDtos;
    }

    @GetMapping({"/{postId}", "/{postId}/"})
    public PostDto getPost(
            @PathVariable Long postId
    ) {
        // 리스트 순회로 post 찾기
        PostDto responsePostDto = this.postDtos
                .stream()
                .filter(
                        postDto -> postDto.getId().equals(postId)
                )
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        return responsePostDto;
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public String postPost(
            @RequestBody PostDto requestPostDto
    ) {
        requestPostDto.setId(this.newId++);

        this.postDtos.add(requestPostDto);

        return "Complete!";
    }

    @PutMapping({"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String putPost(
            @RequestBody PostDto requestPostDto
    ) {
        // 리스트 순회로 post 찾기
        // 만약 요청정보로 post를 찾지 못하면 NOT_FOUND 에러 발생
        PostDto responsePostDto = this.postDtos
                .stream()
                .filter(
                        postDto -> postDto.getId().equals(requestPostDto.getId())
                )
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));

        responsePostDto.setTitle(requestPostDto.getTitle());
        responsePostDto.setContent(requestPostDto.getContent());

        return "";
    }

    @DeleteMapping({"/{postId}", "/{postId}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePost(
            @PathVariable Long postId
    ) {
        // 리스트 순회로 post 삭제
        this.postDtos.removeIf(postDto -> postDto.getId().equals(postId));

        return "";
    }
}
