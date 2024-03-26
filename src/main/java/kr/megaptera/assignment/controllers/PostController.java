package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    // 게시물 조회 GET /posts
    @GetMapping
    public List<PostDto> list() {
        return postDtos;
    }

    // 게시물 생성 POST /posts
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto postDto) {
        postDto.setId(generateId());
        postDtos.add(postDto);
        return postDto;
    }

    // 게시물 상세조회 GET /posts/{id}
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable("id") String id) {
        PostDto postDto = new PostDto();
        for (int i = 0; i < postDtos.size(); i++) {
            postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                break;
            }
        }
        return postDto;
    }

    // 게시물 수정 PUT /posts/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PostDto edit(@PathVariable("id") String id, @RequestBody PostDto dto) {
        PostDto postDto = new PostDto();
        // 매개변수로 가져온 id로 데이터 찾기
        for (int i = 0; i < postDtos.size(); i++) {
            postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                postDto.setTitle(dto.getTitle());
                postDto.setContent(dto.getContent());
                break;
            }
        }
        return postDto;
    }

    // 게시물 삭제 DELETE /posts/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PostDto delete(@PathVariable("id") String id) {
        PostDto postDto = new PostDto();
        for (int i = 0; i < postDtos.size(); i++) {
            postDto = postDtos.get(i);
            if (postDto.getId().equals(id)) {
                // 게시글 삭제 로직
                postDtos.remove(i);
                break;
            }
        }
        return postDto;
    }

    private String generateId() {
        newId += 1;

        return newId.toString();
    }
}
